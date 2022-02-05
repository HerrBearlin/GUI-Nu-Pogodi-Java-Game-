package Entities;

public class Player {

    public String difficulty;
    public int score;
    public int lives;
    public int timeplayed;
    public Player(String difficulty, int score, int lives, int timeplayed) {
        this.difficulty = difficulty;
        this.score = score;
        this.lives = lives;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTimeplayed() {
        return timeplayed;
    }

    public void setTimeplayed(int timeplayed) {
        this.timeplayed = timeplayed;
    }

    public int getFinalScore(){
        int finalscore = 0;
        if(this.difficulty.equalsIgnoreCase("Easy")){
            finalscore = this.score*1*timeplayed;
            return finalscore;
        }
        if(this.difficulty.equalsIgnoreCase("Medium")){
            finalscore = this.score*2*timeplayed;
            return finalscore;
        }
        if(this.difficulty.equalsIgnoreCase("Hard")){
            finalscore = this.score*3*timeplayed;
            return finalscore;
        }
        else return finalscore;
    }
}

