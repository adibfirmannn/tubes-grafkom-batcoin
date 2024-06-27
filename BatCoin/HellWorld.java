import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class HellWorld extends World {
    private int coinTimer = 0;
    private GreenfootSound backsoundNight = new GreenfootSound("Night.mp3");
    private int level;
    public Player Bat = new Player(-1.3);
    public Dragon dragon; // Tidak perlu inisialisasi di sini

    private List<GreenfootImage> backgroundImages;
    private int currentBackgroundIndex = 0;
    private int backgroundTimer = 0;
    private int backgroundChangeInterval = 20; 
    private GreenfootSound NewLevelSound;

    public HellWorld() {    
        super(600, 400, 1); 
        initializeBackgroundImages();
        NewLevelSound = new GreenfootSound("NewLevel.mp3");
        NewLevelSound.play();
        prepare();
    }
    
    private void prepare() {
        // Tambahkan objek Player dan Dragon setelah jeda
        addObject(Bat, 100, 300);
        dragon = new Dragon();
        addObject(dragon, getWidth() - 10, Greenfoot.getRandomNumber(getHeight())); // Mulai dari luar layar kanan
        
        // Set kecepatan dan lainnya
        Bat.setSpeed(1);
        dragon.setSpeed(1);
        
        level = 3;
        if (getObjects(Score.class).isEmpty()) {
            addObject(new Score(), 300, 100);
        }
    }

    public void initializeBackgroundImages() {
        backgroundImages = new ArrayList<>();
        // Muat gambar-gambar latar belakang yang membentuk animasi GIF
        for (int i = 0; i <= 7; i++) { // Ada 8 frame dalam animasi GIF (frame_0.png sampai frame_7.png)
            GreenfootImage img = new GreenfootImage("frame_" + i + ".png");
            img.scale(600, 400); // Mengubah ukuran gambar sesuai dengan ukuran dunia
            backgroundImages.add(img);
        }
        setBackground(backgroundImages.get(0));
    }

    public void act() {
        updateBackground();
        backsoundEffect();
        checkGameOver();
        spawnCoins();
    }
    
    public void backsoundEffect() {
        if (Player.isAlive() && level == 3) {
            if (!backsoundNight.isPlaying()) {
                backsoundNight.play();
            }
        } else if(!Player.isAlive()){
            backsoundNight.stop();
        } 
    }

    public void updateBackground() {
        backgroundTimer++;
        if (backgroundTimer >= backgroundChangeInterval) {
            currentBackgroundIndex = (currentBackgroundIndex + 1) % backgroundImages.size();
            setBackground(backgroundImages.get(currentBackgroundIndex));
            backgroundTimer = 0;
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
    
    public void gameWinner() {
        Winner winner = new Winner();
        addObject(winner, getWidth() / 2, getHeight() / 2 - 50);
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

    public void dragonDefeated() {
        gameWinner();
        backsoundNight.stop();
        Greenfoot.stop();
    }
}
