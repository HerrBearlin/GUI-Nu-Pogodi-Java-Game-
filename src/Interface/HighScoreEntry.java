package Interface;

import java.io.Serializable;

public class HighScoreEntry implements Comparable<HighScoreEntry>{

    protected String playerName;
    protected String finalScore;

    public HighScoreEntry(String playerName, String finalScore) {
        this.playerName = playerName;
        this.finalScore = finalScore;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    @Override
    public String toString() {
        return playerName + "  [" + finalScore  ;
    }


    @Override
    public int compareTo(HighScoreEntry o) {
        return 0;
    }
}

