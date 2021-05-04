package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	Projectile p1;
	boolean tab[][];
	int x, y;
	int flatlevel;
	Tank tanks[];
	
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
			System.out.println(t + " " + dt + " " + (int)xPos + " " + (int)yPos);
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
			if(tab[p1.getX()][p1.getY()] == true) output = false;
		}
		if(p1.xPos < 0 || p1.xPos > 1079) output = false;
		return output;
	}
	
	public Map() {
		p1 = null;
		tanks = new Tank[2];
		tanks[0] = new Tank(100,51,1);
		tanks[1] = new Tank(900,51,2);
		x = 1080;
		y = 520;
		tab = new boolean[x][y];
		flatlevel = 50;
		if(true) {
			for(int n = 0; n < x; n++) {
				for(int m = 0; m < y; m++) {
					if(m <= flatlevel) tab[n][m] = true;
					else tab[n][m] = false;
				}
			}
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
				g2d.fillRect(tanks[n].xPos-3, y-2 - tanks[n].yPos ,6 ,4);
			}
			if(tanks[n].team == 2) {
				g2d.setColor(Color.green);
				g2d.fillRect(tanks[n].xPos-3, y-2 - tanks[n].yPos ,6 ,4);
			}
		}
		if(p1 != null) {
			
			g2d.setColor(Color.white);
			g2d.fillOval(p1.getX(),y - p1.getY(),4,4);
		}
		
	}
}
