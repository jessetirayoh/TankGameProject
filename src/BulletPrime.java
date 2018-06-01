package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class BulletPrime extends GameObject {
    private int sideSpeed;
	boolean visible;
	Tank myTank;


    // TODO: Not used, will delete
	public Tank getTank(){
		return myTank;
	}

	public boolean isVisible(){
		return visible;
	}
	/**
	 * BulletPrime constructor.
	 * @param image
	 * @param x
	 * @param y
	 * @param speed
	 * @param sideSpeed
	 */
	BulletPrime( Image image, int x, int y, int speed, int sideSpeed){
		super(image, x, y, speed);
		visible = true;
		this.sideSpeed = sideSpeed;
	}
	
	public void draw(ImageObserver imageObserver, Graphics2D graphics2D) {
		if( visible ){
			graphics2D.drawImage(img, x, y, imageObserver);
		}
		update();
    }

	public void update(){

	}

}
