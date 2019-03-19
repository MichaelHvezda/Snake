package autoPlay;

import io.vavr.Tuple2;
import org.jetbrains.annotations.NotNull;
import snakeTelo.Had;
import snakeTelo.Jidlo;
import snakeTelo.Telo;
import snakeTelo.TeloHada;
import transforms.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AutoPlay {

    List<Tuple2<Integer,Integer>> krokyHada = new ArrayList<>();
    Had had;
    Jidlo jidlo;


    public Tuple2<Integer,Integer> predikce(Had had, Jidlo jidlo){
        List<Tuple2<Integer,Integer>> predikceKroku = new ArrayList<>();
        if(getKrokyHada().isEmpty()|| !(getHad().getHadSeznam().get(0)._2.equals(had.getHadSeznam().get(0)._2))){
//            new AutoPlay(had,jidlo);
            setHad(had);
            setJidlo(jidlo);


            int xHada = (int)getHad().getHadSeznam().get(0)._1.getX();
            int yHada = (int)getHad().getHadSeznam().get(0)._1.getY();
            int xJidla = (int)getJidlo().getJidlo()._1.getX();
            int yJidla = (int)getJidlo().getJidlo()._1.getY();


            int krokyX = (int)((xHada-xJidla));
            int krokyY = (int)((yHada-yJidla));
            if (krokyX!=0&&krokyY!=0){
                for(int i = krokyY; 0>i;i+=3){
                    predikceKroku.add(dolu());
                    //System.out.println("0 1");
                }

                for(int i = krokyY; 0<i;i-=3){
                    predikceKroku.add(nahoru());
                    //System.out.println("0 -1");
                }

                for(int i = krokyX; 0>i;i+=3){
                    predikceKroku.add(vpravo());
                   // System.out.println("1 0");
                }

                for(int i = krokyX; 0<i;i-=3){
                    predikceKroku.add(vlevo());
                    //System.out.println("-1 0");
                }
            }else {
                if(krokyX<0){
                    predikceKroku.add(vpravo());
                }else {
                    predikceKroku.add(vlevo());
                }

            }









/*

            Tuple2<Integer,Integer> hlavaHada = new Tuple2<>(xHada,yHada);
            Tuple2<Integer,Integer> jidloHada = new Tuple2<>(xJidla,yJidla);

                while (hlavaHada._2>jidloHada._2+1 && predikceKroku.size()<10){
                    hlavaHada = posunHlavy(hlavaHada,nahoru()._1,nahoru()._2);
                    predikceKroku.add(nahoru());
                    System.out.println("0 -1");
                }
                while (hlavaHada._2<jidloHada._2-2 && predikceKroku.size()<10){
                    jidloHada = posunHlavy(hlavaHada,dolu()._1,dolu()._2);
                    predikceKroku.add(dolu());
                    System.out.println("0 1");
                }



                while (hlavaHada._1<jidloHada._1+1 && predikceKroku.size()<10){
                    hlavaHada = posunHlavy(hlavaHada,vpravo()._1,vpravo()._2);
                    predikceKroku.add(vpravo());
                    System.out.println("1 0");
                }

                while (hlavaHada._1>jidloHada._1-2 && predikceKroku.size()<10){
                    jidloHada = posunHlavy(hlavaHada,vlevo()._1,vlevo()._2);
                    predikceKroku.add(vlevo());
                    System.out.println("-1 0");
                }
//*/


         //   predikceKroku.add(predikceKroku.get(predikceKroku.size()-1));

            setKrokyHada(predikceKroku);


            return getPrvniKrokHada();


        }




        return getPrvniKrokHada();
    }

    public void clearKrokyHada(){
        krokyHada.clear();
    }

    private Had getHad() {
        return had;
    }


    public AutoPlay(){


    }

    private AutoPlay(Had had, Jidlo jidlo){
        this.had=had;
        this.jidlo=jidlo;
    }

    public void setHad(Had had) {
        this.had = had;
    }

    public void setJidlo(Jidlo jidlo) {
        this.jidlo = jidlo;
    }

    private Jidlo getJidlo() {
        return jidlo;
    }



    private void setKrokyHada(List<Tuple2<Integer, Integer>> krokyHada) {
        this.krokyHada = krokyHada;
    }

    private List<Tuple2<Integer, Integer>> getKrokyHada() {

        return krokyHada;
    }

    private Tuple2<Integer, Integer> getPrvniKrokHada() {
        Tuple2<Integer, Integer> vysledek;
        if(!(getKrokyHada().isEmpty())){
            vysledek = new Tuple2<>(getKrokyHada().get(0)._1,getKrokyHada().get(0)._2);
        }else{
            predikce(getHad(),getJidlo());
            vysledek = getPrvniKrokHada();
        }
        List<Tuple2<Integer,Integer>> progressHada = new ArrayList<Tuple2<Integer,Integer>>();
        for (int i =1;i<getKrokyHada().size();i++){
            progressHada.add(new Tuple2<>(getKrokyHada().get(i)._1,getKrokyHada().get(i)._2));
        }
        setKrokyHada(progressHada);


        return vysledek;





    }
    private  @NotNull Tuple2<Integer,Integer> posunHlavy(@NotNull Tuple2<Integer,Integer> a, int vpravoLevo, int nahoruDolu) {
        return new Tuple2<>((a._1)+vpravoLevo*3,(a._2)+nahoruDolu*3);
    }


    private Tuple2<Integer,Integer> nahoru(){

        return new Tuple2<Integer,Integer>(0,-1);
    }

    private Tuple2<Integer,Integer> vpravo(){

        return new Tuple2<Integer,Integer>(1,0);
    }

    private Tuple2<Integer,Integer> dolu(){

        return new Tuple2<Integer,Integer>(0,1);
    }

    private Tuple2<Integer,Integer> vlevo(){

        return new Tuple2<Integer,Integer>(-1,0);
    }
}
