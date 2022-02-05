package Entities;

import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Wolf extends Ellipse {

    private Image image;
    private String position;
    private int lives;
    public Wolf(double v, double v1, double v2, double v3, Image image, String position, int lives) {
        super(v, v1, v2, v3);
        this.lives = lives;
        this.image = new Image("Resources/Wolf_Image.png", 125, 125, false, false);
        setFill(new ImagePattern(this.image));
    }

    public Wolf CreateWolf(){
        Wolf wolfNew = new Wolf(530, 460, 65, 65, image, "botRight", 2 );
        wolfNew.setPosition("botRight");
        return wolfNew;
    }

    public void MoveWolf(Wolf wolf, double stX, double stY, double endX, double endY){
        Line line = new Line(stX, stY, endX, endY);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(wolf);
        pathTransition.setPath(line);
        pathTransition.setDuration(Duration.seconds(0.5));
        pathTransition.setCycleCount(1);
        pathTransition.play();

        if(wolf.getCenterX() != endX){
            RotateTransition rotate = new RotateTransition();
            rotate.setAxis(Rotate.Y_AXIS);
            rotate.setByAngle(180);
            rotate.setCycleCount(1);
            rotate.setDuration(Duration.seconds(0.5));
            rotate.setNode(wolf);
            rotate.play();
        }

    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

