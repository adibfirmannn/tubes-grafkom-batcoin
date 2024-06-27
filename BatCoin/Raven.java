import greenfoot.*;

public class Raven extends Actor {
    private GreenfootImage[] images;
    private int currentFrame;
    private int animationSpeed;
    private int animationCounter;
    private int speed;

    public Raven() {
        images = new GreenfootImage[4];
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("RavenFly" + (i + 1) + ".png");
            images[i].scale(100, 80);
        }
        setImage(images[0]);
        currentFrame = 0;
        animationSpeed = 8;
        speed = 1;
    }

    public void act() {
        animate();
        moveLeft();
        checkOutOfBounds();
    }

    private void animate() {
        animationCounter++;
        if (animationCounter % animationSpeed == 0) {
            currentFrame = (currentFrame + 1) % images.length;
            setImage(images[currentFrame]);
        }
    }

    private void moveLeft() {
         if(Player.isAlive()){
            setLocation(getX() - speed, getY());
        }
        
    }

    private void checkOutOfBounds() {
        if (getX() <= 0) {
            if (getWorld() != null) {
                getWorld().removeObject(this);
            }
        }
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
   
}
