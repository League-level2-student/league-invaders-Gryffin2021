import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
	Rocketship rocketship;
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Alien> aliens = new ArrayList<Alien>();
	Random random = new Random();
	private static int score = 0;
	

	ObjectManager(Rocketship rocketship) {
		this.rocketship = rocketship;
	}

	void addProjectile(Projectile projectile) {
		projectiles.add(projectile);
	}

	void addAliens() {
		aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
	}
	
	void checkCollision() {
		for (int i = 0; i < aliens.size(); i++) {
			if(rocketship.collisionBox.intersects(aliens.get(i).collisionBox)) {
				rocketship.isActive = false;
				aliens.get(i).isActive = false;
				setScore(0);
			}
			for(int i1 = 0; i1 < projectiles.size(); i1++) {
				if(projectiles.get(i1).collisionBox.intersects(aliens.get(i).collisionBox)) {
				projectiles.get(i1).isActive = false;
				aliens.get(i).isActive = false;
				setScore(getScore() +1);
				}
			}
		}
	}

	void update() {
		checkCollision();
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).update();
			if (aliens.get(i).x > 490)
			if (aliens.get(i).y > LeagueInvaders.HEIGHT) {
				aliens.get(i).isActive = false;
				GamePanel.flawless = false;
			}
			for (int i1 = 0; i1 < projectiles.size(); i1++) {
				projectiles.get(i1).update();
				if (projectiles.get(i1).y < 0) {
					projectiles.get(i1).isActive = false;
				}
			}
		}
		rocketship.update();
		purgeObjects();
	}

	void draw(Graphics g) {
		rocketship.draw(g);
		
		for (int i = 0; i < aliens.size(); i++) {
			aliens.get(i).draw(g);
		}
		
		for (int i1 = 0; i1 < projectiles.size(); i1++) {
			projectiles.get(i1).draw(g);
		}
	}

	void purgeObjects() {
		for (int i = 0; i < aliens.size(); i++) {
			if (aliens.get(i).isActive == false) {
				aliens.remove(aliens.get(i));
			}

			for (int i1 = 0; i1 < projectiles.size(); i1++) {
				if (projectiles.get(i1).isActive == false) {
					projectiles.remove(projectiles.get(i1));
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		addAliens();
	}

	public static int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}