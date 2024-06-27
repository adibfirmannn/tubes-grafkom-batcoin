import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Dragon extends Actor {
    private int health = 100;
    private int fireCooldown = 0;
    private GreenfootImage[] images;
    private int currentFrame;
    private int animationSpeed = 15;
    private int animationCounter = 0;
    private int moveSpeed = 1;
    private int direction = 1; // 1 untuk bergerak ke bawah, -1 untuk bergerak ke atas
    private int directionChangeTimer = 100; // Timer untuk mengganti arah
    private HealthBar healthBar;
    private int location;

    public Dragon() {
        images = new GreenfootImage[3];
        for (int i = 0; i < images.length; i++) {
            images[i] = new GreenfootImage("flying_dragon " + (i + 1) + ".png");
            images[i].scale(150, 100);
        }
        setImage(images[0]); 
        currentFrame = 0;
        healthBar = new HealthBar(health);
    }
    
     protected void addedToWorld(World world) {
        world.addObject(healthBar, getX(), getY() - 50);
    }

    public void act() {
        if (location < 100) {
            moveDragonIn();
        } else {
            animate();
            moveDragon();
            shootFire();
            fireCooldown--;
            directionChangeTimer--;
            healthBar.setLocation(getX(), getY() - 50);
            if (directionChangeTimer <= 0) {
                direction = -direction; // Ubah arah
                directionChangeTimer = Greenfoot.getRandomNumber(100) + 50; // Reset timer dengan nilai acak
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

    private void moveDragon() {
        int y = getY();
        y += direction * moveSpeed; // Gerakkan naga berdasarkan arah dan kecepatan
        if (y < 50 || y > getWorld().getHeight() - 50) {
            direction = -direction; // Ubah arah jika mencapai batas atas atau bawah
        }
        setLocation(getX(), y);
    }

    private void moveDragonIn() {
            location += 1;
            animate();
            setLocation(getX() - 1, getY());
 
         // Gerakkan naga masuk ke layar perlahan
    }

    private void shootFire() {
        if (fireCooldown <= 0) {
            getWorld().addObject(new Fire(), getX() - 50, getY());
            fireCooldown = 100;
            
        }
    }

    public void takeDamage(int damage) {
    health -= damage;
    healthBar.updateHealth(health);
    if (health <= 0) {
        World world = getWorld();
        if (world != null) {
            world.removeObject(this);
            if (world instanceof HellWorld) {
                ((HellWorld) world).dragonDefeated();
            }
        }
    }
}

    
    public void setSpeed(int speed){
        this.moveSpeed = speed;
    }
}
