package snakeTelo;


import io.vavr.Tuple2;
import org.jetbrains.annotations.NotNull;
import rasterdata.Raster;
import transforms.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TeloHada implements Telo{

    @Override
    public @NotNull Raster vykresleni(Raster raster,Had a) {
        Raster vysledek = raster;


        for (int i= 0;i<a.getHadSeznam().size();i++){
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX(),(int)a.getHadSeznam().get(i)._1.getY()+1,a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX(),(int)a.getHadSeznam().get(i)._1.getY()-1,a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX()+1,(int)a.getHadSeznam().get(i)._1.getY(),a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX()-1,(int)a.getHadSeznam().get(i)._1.getY(),a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX()-1,(int)a.getHadSeznam().get(i)._1.getY()-1,a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX()-1,(int)a.getHadSeznam().get(i)._1.getY()+1,a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX()+1,(int)a.getHadSeznam().get(i)._1.getY()+1,a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX()+1,(int)a.getHadSeznam().get(i)._1.getY()-1,a.getHadSeznam().get(i)._2);
            vysledek = vysledek.withValue((int)a.getHadSeznam().get(i)._1.getX(),(int)a.getHadSeznam().get(i)._1.getY(),a.getHadSeznam().get(i)._2);
        }



        return vysledek;
    }

    @Override
    public @NotNull List<Tuple2<Point2D,Color>> zvetseni(Had a,int vpravoLevo,int nahoruDolu,Jidlo b) {
        List<Tuple2<Point2D,Color>> vysledek = new ArrayList<>();

//        if(a.getHadSeznam().get(0)._1.getX()==b.jidlo._1.getX() && a.getHadSeznam().get(0)._1.getY()==b.jidlo._1.getY()){
        if(okoliBodu(a,b)){

            vysledek.add(new Tuple2<>(new Point2D((a.getHadSeznam().get(0)._1.getX())+vpravoLevo*3,(a.getHadSeznam().get(0)._1.getY())+nahoruDolu*3),b.getJidlo()._2));

            for (int i= 0;i<a.getHadSeznam().size();i++){
                vysledek.add(new Tuple2<>(new Point2D(
                        (a.getHadSeznam().get(i)._1.getX()),(a.getHadSeznam().get(i)._1.getY())
                ),a.getHadSeznam().get(i)._2));
            }
            return vysledek;
        }else{

        vysledek.add(new Tuple2<>(new Point2D((a.getHadSeznam().get(0)._1.getX())+vpravoLevo*3,(a.getHadSeznam().get(0)._1.getY())+nahoruDolu*3),a.getHadSeznam().get(0)._2));

        for (int i= 0;i<a.getHadSeznam().size()-1;i++){
            vysledek.add(new Tuple2<>(new Point2D(
                    (a.getHadSeznam().get(i)._1.getX()),(a.getHadSeznam().get(i)._1.getY())
            ),a.getHadSeznam().get(i)._2)); // moznost i+1 pro zachovani balev v jednotlivych clancich
        }

        return vysledek;
        }
    }

    @Override
    public @NotNull boolean kousnuti(Had a) {

        for (int i= 1;i<a.getHadSeznam().size();i++){

                if(a.getHadSeznam().get(i)._1.getX() == a.getHadSeznam().get(0)._1.getX() && a.getHadSeznam().get(i)._1.getY() == a.getHadSeznam().get(0)._1.getY()){
                    return true;
                }

        }


        return false;
    }



    private Boolean okoliBodu(Had a, Jidlo b){
     List<Point2D> hadOkoli = new ArrayList<>();
     List<Point2D> jidloOkoli = new ArrayList<>();

        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX(),(int)a.getHadSeznam().get(0)._1.getY()+1));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX(),(int)a.getHadSeznam().get(0)._1.getY()-1));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX()+1,(int)a.getHadSeznam().get(0)._1.getY()));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX()-1,(int)a.getHadSeznam().get(0)._1.getY()));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX()-1,(int)a.getHadSeznam().get(0)._1.getY()-1));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX()-1,(int)a.getHadSeznam().get(0)._1.getY()+1));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX()+1,(int)a.getHadSeznam().get(0)._1.getY()+1));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX()+1,(int)a.getHadSeznam().get(0)._1.getY()-1));
        hadOkoli.add(new Point2D((int)a.getHadSeznam().get(0)._1.getX(),(int)a.getHadSeznam().get(0)._1.getY()));

        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX(),b.getJidlo()._1.getY()));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX()+1,b.getJidlo()._1.getY()-1));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX()+1,b.getJidlo()._1.getY()+1));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX()-1,b.getJidlo()._1.getY()+1));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX()-1,b.getJidlo()._1.getY()-1));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX()+1,b.getJidlo()._1.getY()));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX()-1,b.getJidlo()._1.getY()));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX(),b.getJidlo()._1.getY()-1));
        jidloOkoli.add(new Point2D(b.getJidlo()._1.getX(),b.getJidlo()._1.getY()+1));


        for(int i = 0;i<hadOkoli.size();i++){
            for(int o = 0;o<jidloOkoli.size();o++){
                if(jidloOkoli.get(o).getX()==hadOkoli.get(i).getX() && jidloOkoli.get(o).getY()==hadOkoli.get(i).getY()){
                    return true;
                }
            }
        }

     return false;
    }

}
