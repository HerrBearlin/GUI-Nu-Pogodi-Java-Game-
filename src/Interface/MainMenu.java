package Interface;

import Entities.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

import static Interface.Score.ReadScores;

public class MainMenu extends Application {
    private GameViewEggzWolf game;
    private Player player;
    private Score score;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Main Menu");

        Button newGameButton = new Button("New Game");
        Button highScoreButton = new Button("High Score");
        Button exitButton = new Button("Exit");

        //LAYOUT OF BUTTONS
        newGameButton.setLayoutX(200);
        newGameButton.setLayoutY(200);
        newGameButton.setPrefSize(400, 40);
        highScoreButton.setLayoutX(200);
        highScoreButton.setLayoutY(240);
        highScoreButton.setPrefSize(400, 40);
        exitButton.setLayoutX(200);
        exitButton.setLayoutY(280);
        exitButton.setPrefSize(400, 40);

        VBox buttonBox = new VBox();
        buttonBox.setSpacing(10);

        buttonBox.getChildren().add(newGameButton);
        buttonBox.getChildren().add(highScoreButton);
        buttonBox.getChildren().add(exitButton);
        buttonBox.setPrefSize(400, 200);


        //BUTTONS ACTIONS
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    newGameButton.setText("Starting new game...");
                    CreateDifficultyButtons(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                highScoreButton.setText("Showing high score...");
                CreateHighScoreList(stage);
            }
        });

        exitButton.setOnAction(actionEvent -> {
            exitButton.setText("Exiting game...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });


        Scene scene = new Scene(buttonBox, 400, 160);
        stage.setScene(scene);
        stage.show();


    }

    public void CreateMainMenu(Stage stage, Player player) {
        stage.setTitle("Main Menu");
        player.setTimeplayed(0);
        player.setScore(0);
        Button newGameButton = new Button("New Game");
        Button highScoreButton = new Button("High Score");
        Button exitButton = new Button("Exit");

        //LAYOUT OF BUTTONS
        newGameButton.setLayoutX(200);
        newGameButton.setLayoutY(200);
        newGameButton.setPrefSize(400, 40);
        highScoreButton.setLayoutX(200);
        highScoreButton.setLayoutY(240);
        highScoreButton.setPrefSize(400, 40);
        exitButton.setLayoutX(200);
        exitButton.setLayoutY(280);
        exitButton.setPrefSize(400, 40);

        VBox buttonBox = new VBox();
        buttonBox.setSpacing(10);

        buttonBox.getChildren().add(newGameButton);
        buttonBox.getChildren().add(highScoreButton);
        buttonBox.getChildren().add(exitButton);
        buttonBox.setPrefSize(400, 200);


        //BUTTONS ACTIONS
        newGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    CreateDifficultyButtons(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        highScoreButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                highScoreButton.setText("Showing high score...");
                CreateHighScoreList(stage);
            }
        });

        exitButton.setOnAction(actionEvent -> {
            exitButton.setText("Exiting game...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        });


        Scene scene = new Scene(buttonBox, 400, 160);
        stage.setScene(scene);
        stage.show();

    }

    public void CreateDifficultyButtons(Stage stage) {

        stage.setTitle("Difficulty Choice");

        Button easyButton = new Button("Easy");
        Button mediumButton = new Button("Medium");
        Button hardButton = new Button("Hard");

        //LAYOUT OF BUTTONS
        easyButton.setLayoutX(200);
        easyButton.setLayoutY(200);
        easyButton.setPrefSize(200, 40);
        mediumButton.setLayoutX(200);
        mediumButton.setLayoutY(240);
        mediumButton.setPrefSize(200, 40);
        hardButton.setLayoutX(200);
        hardButton.setLayoutY(280);
        hardButton.setPrefSize(200, 40);

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);

        buttonBox.getChildren().add(easyButton);
        buttonBox.getChildren().add(mediumButton);
        buttonBox.getChildren().add(hardButton);
        buttonBox.setPrefSize(400, 200);

        Scene scene = new Scene(buttonBox, 400, 160);
        stage.setScene(scene);
        stage.show();

        easyButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    easyButton.setText("Setting Easy mode...");
                    game = new GameViewEggzWolf();
                    player = new Player("Easy", 0, 5, 0);
                    game.CreateGame(stage, player);
                    GameOver(stage, player);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        mediumButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    mediumButton.setText("Setting Medium mode...");
                    game = new GameViewEggzWolf();
                    player = new Player("Medium", 0, 4, 0);
                    game.CreateGame(stage, player);
                    GameOver(stage, player);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hardButton.setOnAction(actionEvent -> {
            try {
                hardButton.setText("Setting Medium mode...");
                game = new GameViewEggzWolf();
                player = new Player("Hard", 0, 3, 0);
                game.CreateGame(stage, player);
                GameOver(stage, player);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void CreateHighScoreList(Stage stage) {
        ListView scoreList = new ListView();
        try {
            scoreList.getItems().addAll(ReadScores());
            scoreList.setMinSize(100, 500);
            scoreList.setMaxSize(150, 550);
        } catch (IOException e) {
            e.printStackTrace();
        }
        KeyCodeCombination kb = new KeyCodeCombination(KeyCode.Q, KeyCombination.SHIFT_DOWN, KeyCombination.CONTROL_DOWN);
        Scene scene = new Scene(scoreList, 300, 400);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            if(kb.match(key)){
                System.out.println("q + shift + ctrl pressed ");
                CreateMainMenu(stage, player );
            }
        });
        stage.setScene(scene);
        stage.show();

    }

    public void CreateEndGamePopup(Stage stage) {
        System.out.println("Game OVer");
        TextArea textArea = new TextArea();
        VBox vBox = new VBox();
        vBox.setSpacing(20);

        Button continueButton = new Button("Save your game and quit.");
        textArea.setMaxSize(300, 200);
        continueButton.setMaxSize(300, 100);
        vBox.getChildren().addAll(textArea, continueButton);

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String playerName = textArea.getText();
                score = new Score();
                try {
                    score.addNewPlayer(playerName, player);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        });
        Scene scene = new Scene(vBox, 300, 300    );
        stage.setScene(scene);
        stage.show();
    }

    public void GameOver(Stage stage, Player player){
        new Thread(() -> {
            while(player.getLives()>0){
                try {
                    Thread.sleep(1000);
                    System.out.println("Game Is Working");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(player.getLives()<=0){
                Platform.runLater(() ->CreateEndGamePopup(stage));
            }
        }).start();
    }

}
