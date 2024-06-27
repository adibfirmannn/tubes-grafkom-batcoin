import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Pipe extends Actor
{
    private int speed;
    
    public Pipe(){
        GreenfootImage image = getImage();
        image.scale(512, 900);
    }
    
    public void act() 
    {
        if(Player.isAlive()){
            setLocation(getX() - speed, getY());
        }
        if(getX() <= 1){
            setLocation(getX() + 700, 75 + Greenfoot.getRandomNumber(225));
        }
    }
    
    public void setSpeed(int speed){
        this.speed = speed;
    }
}