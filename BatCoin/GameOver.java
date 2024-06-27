import greenfoot.*;

public class GameOver extends Actor {
    private GreenfootImage gameOverImage;
    private GreenfootImage playButtonImage;
    private GreenfootSound deathSound;
    
    public GameOver() {
        deathSound = new GreenfootSound("Death.mp3");
        deathSound.play();
        gameOverImage = new GreenfootImage("flappy-bird-game-over.png");
        playButtonImage = new GreenfootImage("playbutton.png");
        
        // Mengatur posisi vertikal playButtonImage
        int playButtonVerticalPosition = gameOverImage.getHeight() + 56; // Anda bisa menyesuaikan angka 20 sesuai kebutuhan
        
        // Menggabungkan kedua gambar menjadi satu
        GreenfootImage combinedImage = new GreenfootImage(gameOverImage.getWidth(), gameOverImage.getHeight() + playButtonImage.getHeight());
        combinedImage.drawImage(gameOverImage, 0, 110);
        combinedImage.drawImage(playButtonImage, (combinedImage.getWidth() - playButtonImage.getWidth()) / 2, playButtonVerticalPosition);
        
        setImage(combinedImage);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            // Reset skor dan dunia saat tombol play diklik
            //backsound.stop();
            Score.resetScore();
            Greenfoot.setWorld(new FlappyWorld());
        }
    }
}
