package snakeTelo;

import io.vavr.Tuple2;
import transforms.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Had {
    List<Tuple2<Point2D,Color>> hadSeznam = new ArrayList<Tuple2<Point2D,Color>>();
    int sirka,vyska;

    public int getSirka() {
        return sirka;
    }

    public int getVyska() {
        return vyska;
    }

    public Had(int sirka, int vyska){
        hadSeznam.add(new Tuple2<>(new Point2D(sirka/2,vyska/2+6),new Color(255,255,255)));
        hadSeznam.add(new Tuple2<>(new Point2D(sirka/2,vyska/2+3),new Color(255,0,0)));
        hadSeznam.add(new Tuple2<>(new Point2D(sirka/2,vyska/2),new Color(0,255,0)));
        hadSeznam.add(new Tuple2<>(new Point2D(sirka/2,vyska/2-3),new Color(0,0,255)));
        hadSeznam.add(new Tuple2<>(new Point2D(sirka/2,vyska/2-6),new Color(255,255,255)));

        this.sirka = sirka;
        this.vyska = vyska;

    }




    public List<Tuple2<Point2D, Color>> getHadSeznam() {
        return hadSeznam;
    }

    public void setHadSeznam(List<Tuple2<Point2D, Color>> hadSeznam) {
        this.hadSeznam = prolezaniStran(getSirka(),getVyska(),hadSeznam);
    }

    private List<Tuple2<Point2D, Color>> prolezaniStran(int sirka, int vyska,List<Tuple2<Point2D, Color>> hadSeznam){
        List<Tuple2<Point2D,Color>> hadSeznamSrovnany = new ArrayList<Tuple2<Point2D,Color>>();

        for (int i= 0;i<hadSeznam.size();i++){
            hadSeznamSrovnany.add(new Tuple2<>(new Point2D((sirka+hadSeznam.get(i)._1.getX())%sirka,(vyska+hadSeznam.get(i)._1.getY())%vyska),hadSeznam.get(i)._2));
        }

        return hadSeznamSrovnany;
    }
}
