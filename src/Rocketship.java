import java.awt.Color;
import java.awt.Graphics;

public class Rocketship extends GameObject { 
	
	Rocketship(int x, int y, int width, int height){
		super(x,y,width,height);
		speed = 10;
	 }
	
	void draw(Graphics g) {
		g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
	}
	
	public static void right() {
        x+=speed;
    }
	
	public static void left() {
        x-=speed;
    }
	
	public static void down() {
        y+=speed;
    }
	
	public static void up() {
        y-=speed;
    }
}