package fx.app;

import javafx.animation.AnimationTimer;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Butterfly extends Pane implements Runnable {

    private static final String IMG_URL = "/babochka.png";

    private int directionX = (int) (Math.random() * 3) + 1;
    private int directionY = (int) (Math.random() * 3) + 1;

    private MainApp mainApp;

    public Butterfly() {
        ImageView imageView = new ImageView(IMG_URL); //fw TODO
        imageView.setFitHeight(40);
        imageView.setFitWidth(50);
        this.setCursor(Cursor.CROSSHAIR);
        int startPositionX = (int) (Math.random() * 450);
        setTranslateX(startPositionX);
        int startPositionY = (int) (Math.random() * 360);
        setTranslateY(startPositionY);
        getChildren().add(imageView);
    }

    private void moveX() {
        if(this.getTranslateX() <= 0
                || this.getTranslateX() >= mainApp.getPane().getMaxWidth()){
            directionX = Math.negateExact(directionX);
        }
        setTranslateX(getTranslateX() + directionX);
    }

    private void moveY() {
        if(this.getTranslateY() <= 0
                || this.getTranslateY() >= mainApp.getPane().getMaxHeight()){
            directionY = Math.negateExact(directionY);
        }
        setTranslateY(getTranslateY() + directionY);
    }

    @Override
    public void run() {
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveX();
                moveY();
            }
        };
        animationTimer.start();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public synchronized void destroy(){
        mainApp.getButterflyList().remove(this);
        mainApp.getPane().getChildren().remove(this);
    }

}
