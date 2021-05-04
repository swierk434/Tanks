package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	Projectile p1;
	boolean tab[][];
	boolean destructive;
	int x, y;
	int flatlevel;
	Tank tanks[];
	Integer colisionX, colisionY, radius;
	
	public class Tank {
		int xPos, yPos, team;
		
		public Tank(int x, int y, int t){
			xPos = x; 
			yPos = y;
			team = t;
		}
		
		public void updatePos(int x, int y) {
			xPos = x; 
			yPos = y;
		}
		public void updatePosRight() {
			xPos +=1; 
		}
		public void updatePosLeft() {
			xPos -=1;
		}
	}
	public class Projectile{
		public static final double g = 0.1;
		double xVel, yVel;
		double xPos, yPos;
		
		
		public Projectile(int x, int y, double vx, double vy){
			xPos = x; 
			yPos = y;
			xVel = vx;
			yVel = vy;
		}
		
		public void updatePos(double t, double dt) {
			xPos += xVel*dt; 
			//System.out.println(t + " " + dt + " " + (int)xPos + " " + (int)yPos);
			yPos += (-g*t+yVel)*dt;
		}
		int getX(){
			return (int)xPos;
		}
		int getY(){
			return (int)yPos;
		}
	}
	public void addProjectile(int x, int y, double vx, double vy) {
		p1 = new Projectile(x,y,vx,vy);
	}
	
	public boolean projectileIsValid() {
		boolean output = true;
		if(p1.getX() >= 0 && p1.getX() <= 1079 && p1.getY() >= 0 && p1.getY() <= 519 ) {
			if(tab[p1.getX()][p1.getY()] == true) {
			output = false;
			colisionX = p1.getX();
			colisionY = p1.getY();
			//System.out.println(colisionX + " " + colisionY);
			}
		}
		if(p1.xPos < 0 || p1.xPos > 1079) output = false;
		return output;
	}
	
	public Map() {
		destructive = false;
		colisionX = null;
		colisionY = null;
		radius = null;
		p1 = null;
		tanks = new Tank[2];
		tanks[0] = new Tank(100,151,1);
		tanks[1] = new Tank(900,151,2);
		x = 1080;
		y = 520;
		tab = new boolean[x][y];
		flatlevel = 150;
		if(true) {
			for(int n = 0; n < x; n++) {
				for(int m = 0; m < y; m++) {
					if(m <= flatlevel) tab[n][m] = true;
					else tab[n][m] = false;
				}
			}
		/*	for(int n = 400; n < 500; n++) {
				for(int m = 0; m < 300; m++) {
					tab[n][m] = true;
				}
			}*/		
		}
		/*if(false){	
		}
		if(false) {
		}*/
	}
	public void printmap(){
		for(int m = y-1; m >= 0; m--){
			 for(int n = 0; n < x; n++){
				if(tab[n][m] == true) System.out.print(1);
				if(tab[n][m] == false) System.out.print(0);
			}
			System.out.print("\n");
		}
	}
	
	public void drawmap(Graphics2D g2d) {
		for(int m = y-1; m >= 0; m--){
			for(int n = 0; n < x; n++){
				if(tab[n][m] == true) {
					g2d.setColor(Color.black);
					g2d.fillRect(n, y - m ,1 ,1);
				}
				if(tab[n][m] == false) {
					g2d.setColor(Color.blue);
					g2d.fillRect(n, y - m ,1 ,1);	
				}
			}
		}
		for(int n = 0; n < 2; n++)	{
			if(tanks[n].team == 1) {
				g2d.setColor(Color.red);
				g2d.fillRect(tanks[n].xPos-5, y-4 - tanks[n].yPos ,10 ,6);
			}
			if(tanks[n].team == 2) {
				g2d.setColor(Color.green);
				g2d.fillRect(tanks[n].xPos-5, y-4 - tanks[n].yPos ,10 ,6);
			}
		}
		if(p1 != null) {
			g2d.setColor(Color.white);
			g2d.fillOval(p1.getX(),y - p1.getY(),4,4);
		}
		if(colisionX != null && radius != null && radius > 0) {
			if(destructive == false) {
				g2d.setColor(Color.cyan);
				g2d.drawOval((int)colisionX-radius/2,y-radius/2-(int)colisionY,radius,radius);
			}
			if(destructive == true) {
				g2d.setColor(Color.yellow);
				g2d.fillOval((int)colisionX-radius/2,y-radius/2-(int)colisionY,radius,radius);
			}	
		}		
	}
}
