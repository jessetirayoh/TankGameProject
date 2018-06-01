package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class TankBullet extends BulletPrime {
	private int bulletDMG, angle;
	private int sizeX, sizeY;
	public TankBullet( Image img, int speed, Tank tank, int sideSpeed, int damage) {
		super(img, tank.x+((tank.getWidth()/2)-(img.getWidth(null)/120)), tank.y+(img.getHeight(null)/2), speed, sideSpeed);
		bulletDMG= damage;
		sizeX = img.getWidth(null)/60;
		sizeY = img.getHeight(null);
		myTank = tank;
		angle = myTank.getAngle();
		visible = true;
	}
	public void destroyBullet(){
		visible = false;
	}
	
	@Override
	public void update(){
		
		x+=speed*Math.cos(Math.toRadians(6*angle));
		y-=speed*Math.sin(Math.toRadians(6*angle));

		if( Game.p1.collision(x, y, sizeX, sizeY)&& visible &&myTank != Game.p1&&!Game.p1.isRespawning()) {
            visible = false;
        	Game.p1.enemyBulletDmg(bulletDMG);
        	Game.p2.setScore(30);
        }else if( Game.p2.collision(x, y, sizeX, sizeY)&& visible &&myTank != Game.p2&&!Game.p2.isRespawning()) {
            visible = false;
        	Game.p2.enemyBulletDmg(bulletDMG);
        	Game.p1.setScore(30);
        }
        else{
        	for( int i = 0; i < Game.getTankGame().getWall().size()-1; i++){
				Wall tempWall = Game.getTankGame().getWall().get(i);
        		if(tempWall.getWallRectangle().intersects(x, y, width/60, height)&&tempWall.isRespawning()){
        			if(tempWall.isDestroyAble()){
        				tempWall.breakWall();
    		    		addExplosion( Game.getTankGame().getWallExplosionImg(),tempWall.x,tempWall.y);
    		    		AudioPlayer.player("snd_explosion1.wav", false);
        			}
		    		visible =false;
        		}
        	}
       }
		if(!visible ){
    		addExplosion( Game.getTankGame().getWallExplosionImg(),x,y);
    		AudioPlayer.player("snd_explosion1.wav", false);
		}
	}
	
	@Override
	public void draw( ImageObserver imageObserver, Graphics2D graphics2D ) {
        graphics2D.drawImage(img, x, y, x+(img.getWidth(null)/60), y+img.getHeight(null), angle*24, 0, angle*24+24, img.getHeight(null), imageObserver );
        update();
	}
	
}
