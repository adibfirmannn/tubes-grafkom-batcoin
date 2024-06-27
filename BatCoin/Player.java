import greenfoot.*;
import java.util.List;
import java.util.ArrayList;

public class Player extends Actor {
    private boolean oldTouchingPipe = false;
    private boolean oldTouchingRaven = false;
    private static boolean dead;
    private List<Raven> passedRavens = new ArrayList<>();
    private GreenfootImage[] images;
    private int currentFrame;
    private int animationSpeed;
    private int animationCounter;
    private int speed;
    private int waveCooldown = 0;
    private int waveCooldownTime = 100;
    private int health = 100;
    private HealthBar healthBar;

    public Player(double yVel) {
        images = new GreenfootImage[6];
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("Blue_Flying " + (i + 1) + ".png");
            images[i].scale(100, 80);
        }
        setImage(images[0]); 
        currentFrame = 0;
        animationSpeed = 5; 
        dead = false;
        healthBar = new HealthBar(health);
    }

    public Player() {
        this(0);
    }

    protected void addedToWorld(World world) {
        if (world instanceof HellWorld) {
            world.addObject(healthBar, getX(), getY() - 50);
        }
    }

    public void act() {
        animate();
        movement();
        checkCollisionWithPipe();
        checkCollisionWithRaven();
        checkPassingRavens();
        checkForWaveAttack();
    
        if (getWorld() instanceof HellWorld) {
            healthBar.setLocation(getX(), getY() - 30);
        }
    
        if (dead) {
            World myWorld = getWorld();
            if (myWorld instanceof FlappyWorld) {
                ((FlappyWorld) myWorld).gameOver();
            } else if (myWorld instanceof DarkForestWorld) {
                ((DarkForestWorld) myWorld).gameOver();
            } else if (myWorld instanceof HellWorld) {
                ((HellWorld) myWorld).gameOver();
            }
            myWorld.removeObject(this);
            if (myWorld instanceof HellWorld) {
                myWorld.removeObject(healthBar);
            }
        }
    }

    private void animate() {
        animationCounter++;
        if (animationCounter % animationSpeed == 0) {
            currentFrame = (currentFrame + 1) % images.length;
            setImage(images[currentFrame]);
        }
    }

    public void movement() {
        int x = getX();
        int y = getY();

        if (Greenfoot.isKeyDown("right")) {
            x += speed;
        }
        if (Greenfoot.isKeyDown("left")) {
            x -= speed;
        }
        if (Greenfoot.isKeyDown("up")) {
            y -= speed;
        }
        if (Greenfoot.isKeyDown("down")) {
            y += speed;
        }

        setLocation(x, y);
    }

    private void checkCollisionWithPipe() {
        boolean touchingPipe = false;
        for (Pipe pipe : getWorld().getObjects(Pipe.class)) {
            if (Math.abs(pipe.getX() - getX()) < 40) {
                if (Math.abs(pipe.getY() + 30 - getY()) > 37) {
                    setDead();
                }
                touchingPipe = true;
            }
        }
        if (!oldTouchingPipe && touchingPipe && !dead) {
            Score.add(1);
        }
        oldTouchingPipe = touchingPipe;
    }
    
    private void checkCollisionWithRaven() {
        for (Raven raven : getWorld().getObjects(Raven.class)) {
            if (Math.abs(raven.getX() - getX()) < 40 && Math.abs(raven.getY() - getY()) < 40) {
                setDead();
                break;
            }
        }
    }
    
    private void checkPassingRavens() {
        List<Raven> ravens = getWorld().getObjects(Raven.class);
        for (Raven raven : ravens) {
            if (!passedRavens.contains(raven) && getX() > raven.getX()) {
                Score.add(1);
                passedRavens.add(raven);
            }
        }
    }
    
    private void checkForWaveAttack() {
        if (waveCooldown > 0) {
            waveCooldown--;
        }
    
        if (Greenfoot.isKeyDown("space") && waveCooldown == 0) {
            Wave wave = new Wave();
            wave.setRotation(getRotation()); // Set gelombang ke arah yang sama dengan pemain
            getWorld().addObject(wave, getX(), getY());
            waveCooldown = waveCooldownTime; // Reset cooldown
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        healthBar.updateHealth(health);
        if (health <= 0) {
            setDead();
        }
    }

    public static boolean isAlive() {
        return !dead;
    }

    public static boolean isDead() {
        return dead;
    }

    public void setLocation(double x, double y) {
        super.setLocation((int) x, (int) y);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static void setDead() {
        dead = true;
    }
}
