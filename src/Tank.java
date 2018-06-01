package src;
//import Wingman.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Observable;






//import Bullets.MyPlanes_Bullet;


public class Tank extends GameObject {
    protected boolean powerUp;
    protected int coolDown = 0, score = 0, health = 4, life = 3,spawnPointX,spawnPointY;
	private int left , right, up, down, shootkey, angle = 0, shootCoolDown = 0, shootRate;
	private boolean moveLeft, moveRight, moveUp, moveDown;
	

	public Tank(Image img, int x, int y, int speed, int left, int right, int up, int down, int shotkey) {
    	super( img, x, y, speed);
    	powerUp = false;
    	this.width = img.getWidth(null)/60;
    	this.left = left;
    	this.right = right;
    	this.up = up;
    	this.down = down;
    	shootkey = shotkey;
    	moveLeft= false;
    	moveRight = false;
    	moveUp = false;
    	moveDown= false;
    	boom = false;
    	shootRate= 50;
        spawnPointX = x;
        spawnPointY = y;
    }

    /**
     * Player score adding
     * @param s is the Score that set by enemy that died by the plane bullets.
     */
    public void setScore(int s){
    	score += s;
    }
    /**
     * 
     * @return TRUE if health and life is not less than or equal to 0, FALSE if either is 0 or less.
     */
    public boolean isLose(){
    	if(life<=0 ){
    		return true;
    	}
    	else{
    		return false;
    	}
    }
    /**
     * 
     * @param hp used by the plane reset when died.
     */
    public void setHP(int hp){
    	health=hp;
    }
    /**
     * 
     * @param dmg Taking damage by tank bullet damage.
     */
    public void enemyBulletDmg(int dmg){
	   health-= dmg;
    } 
    /**
     * 
     * @return life count.
     */
    public int getLife(){
    	return life;
    }
    /**
     * 
     * @return Score
     */
    public int getScore(){
    	return score;
    }
    /**
     * 
     * @param x setting is destroyed or not.
     */
    public void setBoom(boolean x){
    	boom = x;
    }
    /**
     * set power up
     */
    public void oneUp(){
    	this.powerUp = true;
    	shootCoolDown = 75;
    }
    public void healthUp(){
    	this.health = 4;
    }
    /**
     * 
     * @return True if the tank is not on the map and on cool down counter to respawn.
     */
    public boolean isRespawning(){
    	return (coolDown != 0 );
    }

    /**
     * Hnadle Drawing for the planes and checking health, also boom animation.
     * @param obs
     * @param g
     */
    public void draw(ImageObserver obs, Graphics2D g) {
    	shootCoolDown--;
        if(health <= 0){
        	boom = true;
    		AudioPlayer.player("snd_explosion2.wav", false);
        }
        if(health>0&&coolDown==0&&life>0){
        	boom = false;
        	g.drawImage(img, x, y, x+(img.getWidth(null)/60), y+img.getHeight(null), angle*64, 0, angle*64+64, img.getHeight(null), obs);
        	
        	if(!Game.p1.isRespawning()&&!Game.p2.isRespawning()){
        		if ( Game.p1.collision( Game.p2.x, Game.p2.y, Game.p2.width, Game.p2.height)){
        			if( Game.p1.x>x){
        				Game.p1.x+=speed*5;
        				Game.p2.x-=speed*5;
        			}else if( Game.p1.x<(this.x)){
        				Game.p1.x-=speed*5;
        				Game.p2.x+=speed*5;
        			}if( Game.p1.y>this.y){
        				Game.p1.y+=speed*5;
        				Game.p2.y-=speed*5;
        			}else if( Game.p1.y<this.y){
        				Game.p1.y-=speed*5;
        				Game.p2.y+=speed*5;
        			}
        		}
        	}
        }else if(boom==true&&coolDown==0&&life>0){
    		addExplosion( Game.getTankGame().getExplosionImg(),x,y);
    		coolDown = 180;
    		powerUp = false;
    		if(--life>=0) health = 4;
    		boom = false;
    		x=spawnPointX;
    		y=spawnPointY;
        }else{
        	coolDown--;
        }
    }
    
    /**
     * @param p p is Plane, it differentiate what plane did the shoot and who get the score/kill
     */
    public void shoot(Tank p){
		System.out.println("Shoot");
		if(powerUp){
	    	Game.getTankGame().getBullets().add(new TankBullet( Game.getTankGame().getStrongBulletImg(), speed*3, this, 0, 2));

		}else{
	    	Game.getTankGame().getBullets().add(new TankBullet( Game.getTankGame().getWeakBulletImg(), speed*2, this, 0, 1));
		}
    
    }

	@Override
	public void update(Observable obj, Object arg) {
		EventManager ge = ( EventManager ) arg;
		
		if (ge.type == 1 && !boom && coolDown==0) {
			KeyEvent e = (KeyEvent) ge.event;
			// CREDIT goes to Dylan King, I asked him on how to implement a
			// smooth animation.
			if (e.getKeyCode() == left) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveLeft = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveLeft = true;
				}
			}
			if (e.getKeyCode() == right) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveRight = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveRight = true;
				}
			}
			if (e.getKeyCode() == up) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveUp = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveUp = true;
				}
			}
			if (e.getKeyCode() == down) {
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					// x -= speed;
					moveDown = false;
				} else if (e.getID() == KeyEvent.KEY_PRESSED) {
					moveDown = true;
				}
			}
			if (e.getKeyCode() == shootkey&&shootCoolDown<=0) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					shootCoolDown = shootRate;
					System.out.println("Shoot");

					shoot(this);
				}
			}

		} 
	}
	/**
	 * @return Health point for the tank
	 */
	public int getHealth(){
		return health;
	}
	/**
	 * 
	 * @return The tank angle is currently facing.
	 */
	public int getAngle(){
		return angle;
	}
	/**
	 * @return the center-X point from the tank
	 */
	public int getTankCenterX(){
		return x+((img.getWidth(null)/60)/2);
	}
	/**
	 * @return the center-Y point from the tank
	 */
	public int getTankCenterY(){
		return y+(img.getHeight(null)/2);
	}
	
	
    public void updateMove(){
		if (moveLeft == true) {
			angle += 1;
		}
		if (moveRight == true) {
			angle -= 1;
		}
		if (moveUp == true ) {
			x+=speed*Math.cos(Math.toRadians(6*angle));
			y-=speed*Math.sin(Math.toRadians(6*angle));

		}
		if (moveDown == true) {
			x-=speed*Math.cos(Math.toRadians(6*angle));
			y+=speed*Math.sin(Math.toRadians(6*angle));
		}

		//angle = Math.abs(angle%60);
		if(angle == -1) angle = 59;
		else if(angle == 60) angle = 0;
		
		
		if (coolDown > 0) {
			moveLeft = false;
			moveRight = false;
			moveUp = false;
			moveDown = false;
			
		}
    }
}
