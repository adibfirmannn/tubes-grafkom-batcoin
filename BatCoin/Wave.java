import greenfoot.*;

public class Wave extends Actor {
    private int speed = 5;
    private GreenfootSound waveSound;

    public Wave() {
        waveSound = new GreenfootSound("Echo.mp3");
        waveSound.play();
    }

    public void act() {
        move();
        checkCollision();
    }

    private void move() {
        setLocation(getX() + speed, getY());
    }

    private void checkCollision() {
        Raven raven = (Raven) getOneIntersectingObject(Raven.class);
        Dragon dragon = (Dragon) getOneIntersectingObject(Dragon.class);
        World world = getWorld();
        
        if (raven != null) {
            if (world != null) {
                world.removeObject(raven);
                world.removeObject(this);
                Score.add(2);
                //waveSound.stop(); // Hentikan suara ketika gelombang dihapus
            }
        } else if (dragon != null) {
            if (world != null) {
                dragon.takeDamage(10); // Kurangi darah dragon sebanyak 10
                world.removeObject(this);
                //waveSound.stop(); // Hentikan suara ketika gelombang dihapus
            }
        } else if (getX() >= getWorld().getWidth() - 1) {
            if (world != null) {
                world.removeObject(this);
                //waveSound.stop(); // Hentikan suara ketika gelombang dihapus
            }
        }
    }
}
