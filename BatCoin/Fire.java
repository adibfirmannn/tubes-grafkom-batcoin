import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fire extends Actor {
    private GreenfootImage[] images;
    private int currentFrame;
    private int animationSpeed = 2;
    private int animationCounter = 0;
    private GreenfootSound fireSound;

    public Fire() {
        images = new GreenfootImage[2];
        images[0] = new GreenfootImage("fireball 1.png");
        images[1] = new GreenfootImage("fireball 2.png");

        for (GreenfootImage img : images) {
            img.scale(50, 50); // Mengubah ukuran gambar api
        }

        setImage(images[0]);
        currentFrame = 0;
        fireSound = new GreenfootSound("FireBall.mp3");
        fireSound.play();
    }

    public void act() {
        animate();
        move(-3); // Sesuaikan kecepatan api
        if (isAtEdge()) {
            getWorld().removeObject(this);
        } else {
            checkCollision();
        }
    }

    private void animate() {
        animationCounter++;
        if (animationCounter % animationSpeed == 0) {
            currentFrame = (currentFrame + 1) % images.length;
            setImage(images[currentFrame]);
        }
    }

    private void checkCollision() {
        Player player = (Player) getOneIntersectingObject(Player.class);
        if (player != null) {
            player.takeDamage(10);
            getWorld().removeObject(this);
        }
    }
}
