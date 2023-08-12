import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	final int MENU = 0;
	final int GAME = 1;
	final int END = 2;
	int currentState = MENU;
	Font titleFont;
	Font smallerFont;
	Timer frameDraw; 
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	ObjectManager om = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	GamePanel(){
		if (needImage) {
		    loadImage ("space.png");
		}
		titleFont = new Font("Arial", Font.PLAIN, 48);
		smallerFont = new Font("Arial", Font.PLAIN, 12);
		frameDraw = new Timer(1000/60, this);
		frameDraw.start();
	}
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
			drawMenuState(g);
		}else if(currentState == GAME){
			drawGameState(g);
		}else if(currentState == END){
			drawEndState(g);
		}
	}
	void updateMenuState() {  }
	void updateGameState() { om.update(); }
	void updateEndState()  {  }
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);  
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("LEAGUE INVADERS", 25, 100);
		g.setFont(smallerFont);
		g.drawString("Press ENTER to start...", 178, 300);
		g.drawString("Press SPACE for instructions", 163, 400);
	}
	void drawGameState(Graphics g) {
		if (gotImage) {
			g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		om.draw(g);
	}
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 85, 100);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("action");
		repaint();
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		        currentState = MENU;
		    } else {
		        currentState++;
		    }
		}   
		if(currentState == GAME) {
			if (arg0.getKeyCode()==KeyEvent.VK_UP) {
			    System.out.println("UP");
			    if(Rocketship.y > 0) {
			    	Rocketship.up();
			    }
			}else if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			    System.out.println("DOWN");
			    if(Rocketship.y < 720) {
			    	Rocketship.down();
			    }
			}else if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			    System.out.println("LEFT");
			    if(Rocketship.x > 0) {
			    	Rocketship.left();
			    }
			}else if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			    System.out.println("RIGHT");
			    if(Rocketship.x < 450) {
			    	Rocketship.right();
			    }
			}
		}
	}
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
