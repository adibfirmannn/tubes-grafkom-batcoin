import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class HealthBar extends Actor {
    private int maxHealth;
    private int currentHealth;
    private GreenfootImage healthBarImage;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        healthBarImage = new GreenfootImage(100, 10);
        updateImage();
    }

    public void act() {
        updateImage();
    }

    public void updateHealth(int newHealth) {
        this.currentHealth = newHealth;
    }

    private void updateImage() {
        healthBarImage.clear();
        healthBarImage.setColor(Color.RED);
        int healthBarWidth = (int) ((double) currentHealth / maxHealth * 100);
        healthBarImage.fillRect(0, 0, healthBarWidth, 10);
        setImage(healthBarImage);
    }
}
