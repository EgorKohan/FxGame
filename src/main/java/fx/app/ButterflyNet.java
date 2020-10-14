package fx.app;

import javafx.animation.AnimationTimer;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ButterflyNet extends Pane {

    private int speed = 5;
    private final AtomicInteger count = new AtomicInteger();
    private static final String URL_TO_IMG = "scoop_net_PNG6.png";

    public ButterflyNet() {
        ImageView imageView = new ImageView(URL_TO_IMG);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        getChildren().add(imageView);
    }

    public int catchButterfly(List<Butterfly> butterflyList) {
        setTranslateX(getTranslateX() - speed);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                setTranslateY(getTranslateY() + 10);
                for (Butterfly butterfly : butterflyList) {
                    if (getBoundsInParent().intersects(butterfly.getBoundsInParent())) {
                        butterfly.destroy();
                        count.incrementAndGet();
                    }
                }
                if (getTranslateY() >= 400) {
                    stop();
                    setTranslateY(0);
                }
            }
        };
        animationTimer.start();
        return count.get();
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
                if (getTranslateY() == 0) {
                    move();
                }
            }
        };
        animationTimer.start();
    }

    public int getCount() {
        return count.get();
    }
}
