import javax.swing.*;

public class LeagueInvaders {
	JFrame frame;
	GamePanel panel;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	public static void main(String[] args) {
		LeagueInvaders spaceInvader = new LeagueInvaders();
		spaceInvader.setup();
	}	
	LeagueInvaders() {
		frame = new JFrame();
		panel = new GamePanel();
	}
	void setup() {
		frame.add(panel);
		frame.addKeyListener(panel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
