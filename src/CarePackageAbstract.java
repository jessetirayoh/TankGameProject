// TODO: Will delete this, no powerup needed

package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

import src.GameObject;

public abstract class CarePackageAbstract extends GameObject {
	boolean show = false;
	public CarePackageAbstract(Image img, int x, int y, int speed) {
		super(img, x, y, speed);
		show = true;
	}
	/**
	 * Get overridden by the child class, can't use this this class directly anyway.
	 */
	public void update() {
	}
	public boolean isShow(){
		return show;
	}
	/**
	 * This method is for drawing the items.
	 * @param obs
	 * @param g
	 */
	public void draw(ImageObserver obs, Graphics2D g) {
		if(show)
			g.drawImage(img, x, y, obs);
    }
}
