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
	Timer alienSpawn;
	Rocketship rocket = new Rocketship(250, 700, 50, 50);
	ObjectManager objectManager = new ObjectManager(rocket);
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	public static boolean flawless = true;
	
	
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
	void updateGameState() { objectManager.update(); 
	if(rocket.isActive == false) {
		currentState = END;
	}
	}
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
			g.setFont(smallerFont);
			g.setColor(Color.CYAN);
			g.drawString(String.valueOf(ObjectManager.getScore()), 25, 25);
			if(flawless == true) {
				g.drawString("Flawless", 25, 40);
			}
		} else {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		}
		objectManager.draw(g);
	}
	void drawEndState(Graphics g)  {
		g.setColor(Color.RED);
		g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
		g.setFont(titleFont);
		g.setColor(Color.YELLOW);
		g.drawString("GAME OVER", 85, 100);
	}
	
	void startGame(){
		alienSpawn = new Timer(1000 , objectManager);
	    alienSpawn.start();
	    if(currentState == END) {
	    	alienSpawn.stop();
	    	setScore(0);
	    }
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
			setScore(0);
		    if (currentState == END) {
		        currentState = MENU;
		        rocket = new Rocketship(250, 700, 50, 50);
				objectManager = new ObjectManager(rocket);
				flawless = true;
				setScore(0);
		    } else {
		        currentState++;
		        setScore(0);
		        startGame();
		    }
		}   
		if(currentState == GAME) {
			if (arg0.getKeyCode()==KeyEvent.VK_UP) {
			    //System.out.println("UP");
			    if(rocket.y > 0) {
			    	rocket.up();
			    }
			}else if (arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			    //System.out.println("DOWN");
			    if(rocket.y < 720) {
			    	rocket.down();
			    }
			}else if (arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			    //System.out.println("LEFT");
			    if(rocket.x > 0) {
			    	rocket.left();
			    }
			}else if (arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			    //System.out.println("RIGHT");
			    if(rocket.x < 450) {
			    	rocket.right();
			    }
			}
			else if (arg0.getKeyCode()==KeyEvent.VK_SPACE) {
				//System.out.println("SPACE");
				objectManager.addProjectile(rocket.getProjectile());
			}
			    
			}
		}
	
	private void setScore(int i) {
		// TODO Auto-generated method stub
		
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
