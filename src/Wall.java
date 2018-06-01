package src;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

public class Wall extends GameObject {
	boolean breakableWall;
	private int coolDown, width, height;
	private Rectangle wallRect;
	public Wall(Image img, int x, int y, boolean weakWall ) {
		super(img, x, y, 0);
		coolDown = 0;
		breakableWall = weakWall;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		boom=false;
		wallRect = new Rectangle(x,y,width,height);
	}

	public boolean isDestroyAble(){
		return breakableWall;
	}
	public boolean isRespawning(){
		return coolDown == 0;
	}
	public void breakWall(){
		coolDown = 700;
	}
	public Rectangle getWallRectangle(){
		return wallRect;
	}
	/**
	 * This method is for drawing the wall!
	 * @param obs
	 * @param g
	 */
	@Override
	public void draw(ImageObserver obs, Graphics2D g) {
		if(coolDown == 0) {
			g.drawImage(img, x, y, obs);
			update();
		}else{
			coolDown--;
		}
    }
	/**
	 * This method is called from the draw method.
	 */
	public void update() {		
		if ( Game.p1.collision(this.x, this.y, width, height)){
			if( Game.p1.x>(x)){ // wont work if animationDelay did Game.p1.x>(x+this.width), since it will think that my tank is < the side, and it will not bounce back.
				Game.p1.x+=3;
			}
			else if( Game.p1.x<(this.x)){
				Game.p1.x-=3;
			}
			if( Game.p1.y>(this.y)){
				Game.p1.y+=3;
			}
			else if( Game.p1.y<this.y){
				Game.p1.y-=3;
			}
		}
		if ( Game.p2.collision(this.x, this.y, width, height)){
			if( Game.p2.x>(x)){
				Game.p2.x+=3;
			}
			else if( Game.p2.x<(this.x)){
				Game.p2.x-=3;
			}
			if( Game.p2.y>(this.y)){
				Game.p2.y+=3;
			}
			else if( Game.p2.y<this.y){
				Game.p2.y-=3;
			}
		}
	}
}
