


import autoPlay.AutoPlay;
import io.vavr.Tuple2;
import rasterdata.*;

import org.jetbrains.annotations.NotNull;

import snakeTelo.*;
import textMaker.TextMaker;
import textMaker.Texter;
import transforms.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 * trida pro kresleni na platno: zobrazeni pixelu
 *
 * @author PGRF FIM UHK
 * @version 2017
 */

/**
 * Default mod is Waiting for Mod
 * Line - active by press L, draw point by press button on mouse and after that draw line
 * Dragged - active by press K, click on point and by moving mouse you making a line
 * Poli - active by press J, complite poligom made in Line mod, after that change mod on Line again
 * Circle - active by press H, click on point and by moving mouse you making circle and after that you may deside a sector of circle
 * Poligom - active by press P, draw line and poligom from start
 * Seed - active by press O, active seed algorithm
 */


public class Canvas {

	private final JFrame frame;
	private final JPanel panel;
	private final BufferedImage img;
	private Had hadStvoreni;
	private Telo had;
	private Had hadatko;
	private Jidlo jidlo;
	private Texter texter;
	private @NotNull Raster<Color> raster;
	private @NotNull Raster<Color> pomraster;
	private final @NotNull Presenter<Graphics, Color> presenter;
	private Color cervena = new Color(255,0,0);
	private Color bila = new Color(255,255,255);
	private Color black = new Color(0,0,0);
	GameOver gameOver;
	int nahoruDolu =1;
	int vlevoPravo =0;
	int autoPlayInt = 0;
	int gameOverScore =0;
	AutoPlay autoPlay;

	public Canvas(final int width, final int height) {
		frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.setTitle("UHK FIM PGRF : " + this.getClass().getName());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	//	List<Point2D> seznamStatic = new ArrayList<Point2D>();
		List<Tuple2<Point2D,Color>> hhhad = new ArrayList<>();




		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);



/*
		raster = new FastRaster<>(img,
				color -> color.getRGB(), 	      //Color::getRGB,
				intColor -> new Color(intColor)); //Color::new);
		lineMaker = new HeWhoDrawsLinesNaively<>();
		presenter = new FastPresenter<>();
		lineMader = new HeWhoDrawsLinesRightly<>();
		/*/

		raster = new VavrRaster<>(width,height,black);
		presenter = new SlowPresenter<>(Color::getRGB);
		hadStvoreni = new Had(width,height);
		had = new TeloHada();
		pomraster=raster;
		jidlo = new Jidlo(width,height);
		autoPlay = new AutoPlay();
		texter = new TextMaker();
		raster = texter.textToText(raster ,new Point2D(10,10),"Score:",bila,5);
		pomraster = texter.textToText(pomraster ,new Point2D(10,10),"Score:",bila,5);

//*/
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				present(g);
			}
		};

		panel.setPreferredSize(new Dimension(width, height));

		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		panel.requestFocus();
		panel.requestFocusInWindow();


		raster = had.vykresleni(raster,hadStvoreni);
		raster = jidlo.vyklesleniJidla(raster,jidlo);

		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				tlacitka(width, height);
			}
		}, 0, 50, TimeUnit.MILLISECONDS); // execute every 60 seconds



	}

	public void clear() {
		Graphics gr = img.getGraphics();
		gr.setColor(black);
		gr.fillRect(0, 0, img.getWidth(), img.getHeight());
	}

	public void present(final Graphics graphics) {

		presenter.present(graphics,raster);
	}

	public void draw() {
		clear();
		raster = raster.withValue(10,10,bila);




	}

	public void start() {
		draw();
		panel.repaint();

	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Canvas(800,800)::start);

	}

	private void tlacitka(int width, int height) {
		raster=pomraster;
		panel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent o) {

				if (o.getKeyCode() == KeyEvent.VK_UP) {
					vlevoPravo=0;
					nahoruDolu=-1;

					System.out.println("w");
					zruseniAutoPlay();
				}

				if (o.getKeyCode() == KeyEvent.VK_DOWN) {
					vlevoPravo=0;
					nahoruDolu=1;

					System.out.println("s");
					zruseniAutoPlay();
				}

				if (o.getKeyCode() == KeyEvent.VK_LEFT) {
					vlevoPravo=-1;
					nahoruDolu=0;
					System.out.println("a");
					zruseniAutoPlay();
				}

				if (o.getKeyCode() == KeyEvent.VK_RIGHT) {
					vlevoPravo=1;
					nahoruDolu=0;
					System.out.println("d");
					zruseniAutoPlay();
				}

				if (o.getKeyCode() == KeyEvent.VK_L) {
					vlevoPravo=0;
					nahoruDolu=0;
					System.out.println("d");
					zruseniAutoPlay();
				}


				if (o.getKeyCode() == KeyEvent.VK_SPACE) {

					autoPlayInt =1;
				}


			}
		});
		if(autoPlayInt==1){
			Tuple2<Integer,Integer> stranyXY;
			stranyXY = autoPlay.predikce(hadStvoreni,jidlo);
			vlevoPravo=stranyXY._1;
			nahoruDolu=stranyXY._2;
			//System.out.println("AutoPlay");
		}

		int velikostHadaPred = hadStvoreni.getHadSeznam().size();
		hadStvoreni.setHadSeznam(had.zvetseni(hadStvoreni,vlevoPravo,nahoruDolu,jidlo));
		int velikostHadaPo = hadStvoreni.getHadSeznam().size();



		if (velikostHadaPred != velikostHadaPo){
			jidlo = new Jidlo(width,height);
			System.out.println(hadStvoreni.getHadSeznam().size());
		}
		raster = had.vykresleni(raster,hadStvoreni);
		raster = jidlo.vyklesleniJidla(raster,jidlo);

		if(had.kousnuti(hadStvoreni)){
			konecHry(width, height);
		}
		panel.repaint();


	}

	private void konecHry(int width, int height){
		gameOverScore++;
		System.err.print("Game over ");
		System.out.println(gameOverScore);
		gameOver = new GameOver(width,height);
		raster=gameOver.prohralJsi(raster,gameOver);
	}

	private void zruseniAutoPlay(){
		autoPlay.clearKrokyHada();
		autoPlayInt =0;
	}


}//*/