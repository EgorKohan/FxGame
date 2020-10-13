package fx.app;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class ButterflyNet extends Pane {

    private int speed = 5;

    public ButterflyNet() {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(100);
        rectangle.setHeight(100);
        rectangle.setFill(Color.RED);
        getChildren().add(rectangle);
    }

    public void catchButterfly(List<Butterfly> butterflyList) {
        setTranslateX(getTranslateX() - speed);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                setTranslateY(getTranslateX() + 10);
                for (Butterfly butterfly : butterflyList) {
                    if (getBoundsInParent().intersects(butterfly.getBoundsInParent())) {
                        butterfly.destroy();
                    }
                }
            }
        };
        animationTimer.start();
    }

    public void move() {
        if (this.getTranslateX() < 0 || this.getTranslateX() > 400) {
            speed = Math.negateExact(speed);
        }
        this.setTranslateX(getTranslateX() + speed);
    }

    public void start() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                move();
            }
        };
        animationTimer.start();
    }

}
