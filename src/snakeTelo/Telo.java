package snakeTelo;


import io.vavr.Tuple2;
import org.jetbrains.annotations.NotNull;
import rasterdata.Raster;
import transforms.Point2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface Telo {

    @NotNull Raster vykresleni(Raster raster,Had a);

    @NotNull List<Tuple2<Point2D,Color>> zvetseni(Had a,int vpravoLevo,int nahoruDolu,Jidlo b);

    @NotNull boolean kousnuti(Had a);

}
