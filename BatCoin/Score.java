import greenfoot.*;

public class Score extends Actor {
    private static int score = 0;
    private static int highscore = 0;

    public Score() {
        // Jangan reset skor di sini
    }

    public void act() {
        World myWorld = getWorld();
        myWorld.showText("Score: " + score, 100, 50);
        myWorld.showText("Highscore: " + highscore, 100, 100);
    }

    public static void add(int num) {
        score += num;
        if (score > highscore) {
            highscore = score;
        }
    }

    public static void resetScore() {
        score = 0;
    }

    public static int getScore() {
        return score;
    }

    public static int getHighscore() {
        return highscore;
    }
}
