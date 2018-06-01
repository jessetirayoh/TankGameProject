// TODO: Will delete this, no powerup needed

package src;

import java.awt.Image;

public class HealthUp extends CarePackageAbstract{

	public HealthUp(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Checking if it collide with the player plane.
	 */
	public void update() {
		y+=speed;
		if( Game.p1.collision(x, y, width, height)){
			Game.p1.healthUp();;
			System.out.println("health up ");
			show = false;
		}
		else if( Game.p2.collision(x, y, width, height)){
			Game.p2.healthUp();
			show = false;
		}

	}

}
