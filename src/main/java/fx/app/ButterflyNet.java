package fx.app;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.List;

public class ButterflyNet extends Pane {

    public ButterflyNet(){
        setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        setWidth(60);
        setHeight(60);
    }

    public int catchButterfly(List<Butterfly> butterflyList){
        for (Butterfly butterfly : butterflyList) {
            if(this.getBoundsInLocal().intersects(butterfly.getBoundsInLocal())){

//                butterfly.destroy();
            }
        }
        return 0;
    }

}
