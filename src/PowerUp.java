// TODO: Will delete this, no powerup needed

package src;

import java.awt.Image;

public class PowerUp extends CarePackageAbstract{

	public PowerUp(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
		// TODO Auto-generated constructor stub
	}
	/**
	 * Checking if it collide with the player plane.
	 */
	public void update() {
		y+=speed;
		if( Game.p1.collision(x, y, width, height)){
			Game.p1.oneUp();
			Game.p1.setScore(100);
			System.out.println("one up ");
			show = false;
		}
		else if( Game.p2.collision(x, y, width, height)){
			Game.p2.oneUp();
			Game.p2.setScore(100);
			show = false;
		}
	}
}
