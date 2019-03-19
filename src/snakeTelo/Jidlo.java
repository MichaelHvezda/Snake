package snakeTelo;

import io.vavr.Tuple2;
import rasterdata.Raster;
import transforms.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jidlo {
    Tuple2<Point2D,Color> jidlo;
    public Jidlo(int sirka, int vyska){
       jidlo = new Tuple2<>(new Point2D(getRandomNumberInRange(0,sirka/3)*3,getRandomNumberInRange(0,vyska/3)*3),new Color(getRandomNumberInRange(10,255),getRandomNumberInRange(10,255),getRandomNumberInRange(10,255)));
    }

    public Tuple2<Point2D,Color> getJidlo() {
            return jidlo;
    }

    public Raster vyklesleniJidla(Raster raster,Jidlo a) {
        Raster vysledek = raster;



        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX(),(int)a.getJidlo()._1.getY(),a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX()+1,(int)a.getJidlo()._1.getY(),a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX()-1,(int)a.getJidlo()._1.getY(),a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX()+1,(int)a.getJidlo()._1.getY()-1,a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX()-1,(int)a.getJidlo()._1.getY()-1,a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX()+1,(int)a.getJidlo()._1.getY()+1,a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX()-1,(int)a.getJidlo()._1.getY()+1,a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX(),(int)a.getJidlo()._1.getY()+1,a.getJidlo()._2);
        vysledek = vysledek.withValue((int)a.getJidlo()._1.getX(),(int)a.getJidlo()._1.getY()-1,a.getJidlo()._2);






        return vysledek;
    }



    private static int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
