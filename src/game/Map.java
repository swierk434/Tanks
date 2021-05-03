package game;

import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
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
	}
	public class Projectile{
		public static final double g = 9.81;
		double xVel, yVel;
		double xPos, yPos;
		
		
		public Projectile(int x, int y, int vx, int vy){
			xPos = x; 
			yPos = y;
			xVel = vx;
			yVel = vy;
		}
		
		public void updatePos(double t, double dt) {
			xPos += xVel*dt; 
			yPos += (-g*t+yVel)*dt;
		}
		int getX(){
			return (int)xPos;
		}
		int getY(){
			return (int)yPos;
		}
	}

	boolean tab[][];
	int x, y;
	int flatlevel;
	Tank tanks[];
	
	public Map() {
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
	}
}
