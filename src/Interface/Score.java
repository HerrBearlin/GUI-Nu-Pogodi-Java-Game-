package Interface;

import Entities.Player;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


//CLASS BASED ON CODE FOUND IN HERE: https://stackoverflow.com/questions/24565539/in-need-of-javafx-help-writing-to-a-txt-file
// and here: https://beginnersbook.com/2013/12/how-to-sort-arraylist-in-java/
public class Score {
    private Player player;


    public void addNewPlayer(String playerName, Player player) throws IOException {
        try {
            FileWriter bw = new FileWriter("src/Resources/SavedHighScores.txt", true);
            bw.append(playerName + "[" + player.getFinalScore() + "]\n");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<HighScoreEntry> ReadScores() throws IOException{
        ArrayList<HighScoreEntry> scoreList = new ArrayList<>();
        FileReader fileReader = new FileReader(new File("src/Resources/SavedHighScores.txt"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String finalpoints = " ";
        String readLine = bufferedReader.readLine();
        while(readLine != null){
            String[] score = readLine.split("\\[");
            //Array looks like this: '{PLAYER NAME} {PLAYER SCORE}'
            String playerName = score[0];
            finalpoints = score[1];
            scoreList.add(new HighScoreEntry(playerName, finalpoints));
            //loop must continue
            readLine = bufferedReader.readLine();
            Collections.sort(scoreList);
        }
        return scoreList;
    }


}

