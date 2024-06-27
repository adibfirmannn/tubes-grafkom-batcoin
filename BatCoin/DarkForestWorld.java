import greenfoot.*;

public class DarkForestWorld extends World {
    private int coinTimer = 0;
    private GreenfootSound backsoundNight = new GreenfootSound("darkforest.mp3");
    private int level;
    private int ravenTimer = 0;
    public Player Bat = new Player(-1.3);
    private GreenfootSound NewLevelSound;

    public DarkForestWorld() {    
        super(600, 400, 1);
        initializeWorld();
        NewLevelSound = new GreenfootSound("NewLevel.mp3");
        NewLevelSound.play();
    }

    public void initializeWorld() {
        addObject(Bat, 100, 300);
        Bat.setSpeed(1);
        level = 2;
        if (getObjects(Score.class).isEmpty()) {
            addObject(new Score(), 300, 100);
        }
            
    }

    public void act() {
        backsoundEffect();
        checkGameOver();
        spawnCoins();
        spawnRavens();
        checkScore();
    }

    public void backsoundEffect() {
        if (Player.isAlive() && level == 2) {
            if (!backsoundNight.isPlaying()) {
                backsoundNight.play();
            }
        }else{
            backsoundNight.stop();
        }
    }

    
    public void checkScore() {
        if (Score.getScore() >= 30) {
            level = 3;
            backsoundNight.stop();
            Greenfoot.setWorld(new HellWorld());
        }
    }

    public void checkGameOver() {
      if (isGameOver()) {
        gameOver();
        backsoundNight.stop(); 
      }
    }

    public void gameOver() {
        GameOver gameOver = new GameOver();
        addObject(gameOver, getWidth() / 2, getHeight() / 2 - 50);
      
    }

    public boolean isGameOver() {
        return !Player.isAlive();
    }

    public void spawnCoins() {
        coinTimer++;
        if (coinTimer >= 500) {
            if (getObjects(Coin.class).isEmpty()) {
                addObject(new Coin(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
            }
            coinTimer = 0;
        }
    }

    public void spawnRavens() {
        ravenTimer++;
        if (ravenTimer >= 300) {
            Raven raven = new Raven();
            addObject(raven, getWidth(), Greenfoot.getRandomNumber(getHeight()));
            raven.setSpeed(1); // Set speed if needed
            ravenTimer = 0;
        }
    }

    public int getLevel() {
        return level;
    }
}
