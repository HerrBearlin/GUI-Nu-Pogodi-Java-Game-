package Entities;

import Interface.GameViewEggzWolf;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Egg extends Ellipse implements Initializable {
    protected double dx, dy;
    protected boolean eggIsTopLeft;
    protected boolean eggIsBottomLeft;
    protected boolean eggIsTopRight;
    protected boolean eggIsBottomRight;
    protected Image eggImage;
    private Wolf wolf;
    private Player player;

    public Egg(double v, double v1, double v2, double v3) {
        super(v, v1, v2, v3);
        this.eggImage = new Image("Resources/Egg_image.png", 50, 50, false, false);
        setFill(Color.TRANSPARENT);
        setFill(new ImagePattern(this.eggImage));
    }



    public void RollEgg(Egg egg, double stPosX, double stPosY, double endPosX, double endPosY, String position, Wolf wolf, Player player){
        this.wolf=wolf;
        this.player=player;
        Line line = new Line(stPosX, stPosY, endPosX, endPosY);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(egg);
        pathTransition.setPath(line);
        pathTransition.setDuration(Duration.seconds(6));
        pathTransition.setCycleCount(1);
        pathTransition.play();

        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(260);
        rotate.setCycleCount(1);
        rotate.setDuration(Duration.seconds(6));
        rotate.setNode(egg);
        rotate.play();

        rotate.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                egg.setVisible(false);
                if(position.equalsIgnoreCase(wolf.getPosition()) ){
                    player.setScore(player.getScore()+1);
                    System.out.println(player.getScore());
                }else{
                    player.setLives(player.getLives()-1);
                }
            }
        });


    }

    public void RandomRollEgg(Egg egg, Wolf wolf,Player player){
        this.player = player;
        this.wolf = wolf;
        Random random = new Random();
        int sth = random.nextInt(4)+1;
        switch(sth){
            case 1:
                //TOP RIGHT
                egg.RollEgg(egg, 620, 300, 580, 365, "topRight", wolf, player);
                break;
            case 2:
                //BOTTOM RIGHT
                egg.RollEgg(egg, 620, 385, 580, 450, "botRight", wolf, player);
                break;
            case 3:
                //TOP LEFT
                egg.RollEgg(egg, 280, 300, 320, 365, "topLeft", wolf, player);
                break;
            case 4:
                //BOTTOM LEFT
                egg.RollEgg(egg, 280, 400, 320, 450, "botLeft", wolf, player);
                break;
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }
}
