package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Observer;


public abstract class GameObject implements Observer{
	public int x, y, speed, height, width, planeLoop = 0;
	Rectangle bbox ;
	protected boolean boom;
	protected Image[] imgs = new Image[3];
	protected Image img;
	/**
	 * Constructor for Image array
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 */
	public GameObject( Image[] img, int x, int y, int speed){
		this.imgs = img;
		this.x = x;
		this.y = y;
		height = img[planeLoop].getHeight(null);
		width = img[planeLoop].getWidth(null);

		this.speed = speed;
	}
	/**
	 * Constructor for Image without array IE: bullets
	 * @param img
	 * @param x
	 * @param y
	 * @param speed
	 */
	public GameObject( Image img, int x, int y, int speed){
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		height = img.getHeight(null);
		width = img.getWidth(null);
	}
	/**
	 * Collision checking method.
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @return
	 */
	 public boolean collision(int x, int y, int w, int h) {
	        bbox = new Rectangle(this.x, this.y, this.width, this.height);
	        Rectangle otherBBox = new Rectangle (x,y,w,h);
	        if(this.bbox.intersects(otherBBox)&&!boom) { 
	            return true;
	        }
	        return false;
	 }
	 public int getX(){
	    	return x;
	 }
	/* public void setX(int xCoor){
	    	x = xCoor;
	 }*/
	public int getY() {
		return y;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	/**
	 * For making explosion scene
	 * @param boomPlanes
	 * @param a
	 * @param b
	 */
	// TODO: Change variable names of boomPlanes
	public void addExplosion(Image[] boomPlanes, int a, int b) {
		Game.getTankGame().getExplosion().add(new Explosion(boomPlanes, a, b, 0));
	}

	public void update(Observable obj, Object arg) {

	}
	
	public void draw(ImageObserver obs, Graphics2D g){
		
	}
}
