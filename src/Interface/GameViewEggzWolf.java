package Interface;

import Entities.Egg;
import Entities.Player;
import Entities.Wolf;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Ellipse;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;


public class GameViewEggzWolf {

    private GridPane gamePane;
    private TextArea scoreArea;
    private Pane buttonPane;
    private Pane gameView;
    private Button upButton;
    private Button downButton;
    private Button leftButton;
    private Button rightButton;
    private Egg egg = new Egg(10, 10, 10, 10);
    private Group eggGroup = new Group();
    private Wolf wolf = new Wolf(50, 50, 50, 50, null, "botRight", 2);
    private Player player = new Player("Hard", 0, 5, 0);
    private MainMenu mainMenu;
    private Score score;


    public void CreateGame(Stage stage, Player player) throws Exception {
        //MAIN MENU
        this.player = player;
        wolf = wolf.CreateWolf();
        stage.setTitle("Nu Pogodi - the Java Game");

        gamePane = new GridPane();

        scoreArea = CreateScore();
        buttonPane = createMovementButtons();
        gameView =  new Pane();


        gameView.setMaxSize(700, 700);

        gameView.getChildren().add(eggGroup);
        gameView.getChildren().add(wolf);



        //TOP LEFT
        Button topLeft = CreateButton(55, 440);

        topLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 370, 380);
                wolf.setCenterX(370);
                wolf.setCenterY(380);
                wolf.setPosition("topLeft");
            }
        });
        gameView.getChildren().add(topLeft);
        //BOTTOM LEFT
        Button botLeft = CreateButton(55,555);
        botLeft.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 370, 460);
                wolf.setCenterX(370);
                wolf.setCenterY(460);
                wolf.setPosition("botLeft");
            }
        });

        gameView.getChildren().add(botLeft);
        //TOP RIGHT
        Button topRight = CreateButton(752, 440);
        topRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 530, 395);
                wolf.setCenterX(530);
                wolf.setCenterY(395);
                wolf.setPosition("topRight");
            }
        });
        //gameView.getChildren().add(CreateButton(752, 440));
        gameView.getChildren().add(topRight);
        //BOTTOM RIGHT
        Button botRight = CreateButton(752, 555);
        botRight.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 530, 460);
                wolf.setCenterX(530);
                wolf.setCenterY(460);
                wolf.setPosition("botRight");
            }
        });
        // gameView.getChildren().add(CreateButton(752, 555));
        gameView.getChildren().add(botRight);



        gameView.getChildren().add(scoreArea);

        gamePane.add(gameView, 0, 0, 2, 4    );
        createBackground();

        Scene scene = new Scene(gamePane,900, 700);

        KeyCodeCombination kb = new KeyCodeCombination(KeyCode.Q, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
        // Q A W S - CONTROLS

        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            //TOP LEFT
            if(key.getCode()== KeyCode.W){
                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 370, 380);
                wolf.setCenterX(370);
                wolf.setCenterY(380);
                wolf.setPosition("topLeft");
            }
            //BOTTOM LEFT
            if(key.getCode()== KeyCode.S){
                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 370, 460);
                wolf.setCenterX(370);
                wolf.setCenterY(460);
                wolf.setPosition("botLeft");
            }
            //TOP RIGHT
            if(key.getCode()== KeyCode.E){
                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 530, 395);
                wolf.setCenterX(530);
                wolf.setCenterY(395);
                wolf.setPosition("topRight");
            }
            //BOTTOM RIGHT
            if(key.getCode()== KeyCode.D){
                wolf.MoveWolf(wolf, wolf.getCenterX(), wolf.getCenterY(), 530, 460);
                wolf.setCenterX(530);
                wolf.setCenterY(460);
                wolf.setPosition("botRight");
            }
            if(kb.match(key)){
                System.out.println("q + shift + ctrl pressed ");
                mainMenu = new MainMenu();
                mainMenu.CreateMainMenu(stage, player );
            }
        });

        //SHIFT+ CTRL + Q - RETURN TO MAIN MENU

        stage.setScene(scene);




        EggRolling();
        Time(stage );


    }

    private void createBackground() {
        Image backgroundImage = new Image("Resources/NewBackground.png", 900, 700, false, false);
        BackgroundImage background = new BackgroundImage(backgroundImage,null ,null , BackgroundPosition.DEFAULT, null);
        gamePane.setBackground(new Background(background));
    }

    private Pane createMovementButtons(){
        GridPane gridPane = new GridPane();
        upButton = new Button("  Up ");
        upButton.setPrefHeight(100);
        downButton = new Button("Down");
        downButton.setPrefHeight(100);
        leftButton = new Button(" Left");
        leftButton.setPrefHeight(100);
        rightButton = new Button(" Right");
        rightButton.setPrefHeight(100);


        GridPane.setColumnIndex(upButton, 0);
        GridPane.setRowIndex(upButton, 0);
        GridPane.setColumnIndex(leftButton, 0);
        GridPane.setRowIndex(leftButton, 1);
        GridPane.setColumnIndex(downButton, 1);
        GridPane.setRowIndex(downButton, 0);
        GridPane.setColumnIndex(rightButton, 1);
        GridPane.setRowIndex(rightButton, 1);


        gridPane.getChildren().addAll(upButton,downButton,leftButton,rightButton);
        return gridPane;
    }

    private void CreateEggAnimation() {
        Egg egg = new Egg(10, 10, 10, 11);
        eggGroup.getChildren().add(egg);
        //egg.RollEgg(egg, 280, 400, 350, 450);
        egg.RandomRollEgg(egg, wolf, player);

    }

    public Button CreateButton(int x, int y){
        Button button = new Button();
        double radiusX = 45;
        double radiusY = 50;
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setShape(new Ellipse(radiusX, radiusY));
        button.setMinSize(2*radiusX, 2*radiusY);
        button.setMaxSize(2*radiusX, 2*radiusY);
        button.setStyle("-fx-background-color: #b40000");
        return button;
    }

    public TextArea CreateScore(){
        TextArea score = new TextArea();
        score.setMinSize(75, 75);
        score.setMaxSize(75, 75);
        score.setLayoutX(500);
        score.setLayoutY(200);
        score.setStyle("-fx-control-inner-background: #bbc3b6;");
        score.setEditable(false);
        score.setMouseTransparent(true);
        score.setText(String.valueOf(player.getScore()));
        System.out.println(player.getScore());
        return score;
    }

    public void EggRolling(){
        new Thread(() -> {
            Platform.runLater(() -> CreateEggAnimation());
            while(player.getLives()>0){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(() -> CreateEggAnimation());
            }
        }).start();
    }

    public void Time(Stage stage){
        new Thread(() -> {
            int time = 0;
            while(player.getLives()>0){
                try {
                    Thread.sleep(1000);
                    time++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                scoreArea.setText("Score:" + Integer.toString(player.getScore()) + "\n Lives: " + Integer.toString(player.getLives()) );
                player.setTimeplayed(time);
            }
        }).start();
    }




}
