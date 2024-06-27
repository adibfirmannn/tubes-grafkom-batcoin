import greenfoot.*;

public class Coin extends Actor {
    private int speed;
    private GreenfootSound collectCoin = new GreenfootSound("Points.mp3");
    public Coin() {
        // Mengatur gambar untuk koin
        setImage(new GreenfootImage("goldCoin.png"));
    }
    
    public void act() {
        // Perilaku koin, misalnya, bergerak ke kiri atau berinteraksi dengan pemain
        if(Player.isAlive()){
            moveLeft();
        }
        
        if (getWorld() != null) {
            checkForPlayer();
        }
    }
    
    
    
    private void moveLeft() {
        setLocation(getX() - 1 , getY());
        if (getX() == 0) {
            //setLocation(getX() + 700, 75 + Greenfoot.getRandomNumber(225));
            getWorld().removeObject(this);  
        }
    }
    
    private void checkForPlayer() {
        if (isTouching(Player.class)) {
            Score.add(5); // Tambahkan 5 poin ke skor saat pemain menyentuh koin
            collectCoin.play();
            getWorld().removeObject(this);
        }
    }
    
    private void checkForPipe() {
        int chance = 1;
    while (chance == 1) {
        int newY = 75 + Greenfoot.getRandomNumber(225);
        int newX = getX() + 700;
        setLocation(newX, newY);
        if (!isTouching(Pipe.class)) {
            chance = 0; 
        } else {
            setLocation(getX() - 700, getY());
        }
    }
    }
}
