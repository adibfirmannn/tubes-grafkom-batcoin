import greenfoot.*;

public class FlappyWorld extends World {
    private int coinTimer = 0;
    private GreenfootSound backsoundDay = new GreenfootSound("Day.mp3");
    private int level;
    public Pipe Pipa1 = new Pipe();
    public Pipe Pipa2 = new Pipe();
    public Player Bat = new Player(-1.3);


    public FlappyWorld() {    
        super(600, 400, 1);
        initializeWorld();
    }

    public void initializeWorld() {
        addObject(Bat, 100, 300);
        addObject(Pipa1, 300, 175);
        addObject(Pipa2, 600, 175);
        Pipa1.setSpeed(1);
        Pipa2.setSpeed(1);
        Bat.setSpeed(1);
        level = 1;
        if (getObjects(Score.class).isEmpty()) {
            addObject(new Score(), 300, 100);
        }
    }

    public void act() {
        checkScore();
        backsoundEffect();
        checkGameOver();
        spawnCoins();
    }
    
    public void backsoundEffect(){
        if (Player.isAlive() && level == 1){
            backsoundDay.play();
        }else{
            backsoundDay.stop();
        }
    }
    
    public void checkScore() {
        if (Score.getScore() >= 15) {
            level = 2;
            backsoundDay.stop();
           Greenfoot.setWorld(new DarkForestWorld());
           
        }
    }

    public void checkGameOver() {
        if (isGameOver()) {
            gameOver();
        }
    }

    public void gameOver() {
        GameOver gameOver = new GameOver();
        addObject(gameOver, getWidth() / 2, getHeight() / 2 - 50);
    }

    public boolean isGameOver() {
        return false;
    }

    public void spawnCoins() {
        coinTimer++;
        if (coinTimer >= 500) {
            if(getObjects(Coin.class).isEmpty()){
                addObject(new Coin(), getWidth(), Greenfoot.getRandomNumber(getHeight()));
            }
            coinTimer = 0;
        }
    }
    
    public int getLevel(){
        return level;
    }
}
