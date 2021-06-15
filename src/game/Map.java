package game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Map {
	Random rand;
	Projectile p1;
	boolean tab[][];
	boolean destructive;
	int x, y;
	int flatlevel;
	Tank tanks[];
	Integer colisionX, colisionY, radius;
	Graphics2D mapgc;
	Integer winner;
	int n1, n2, n3;
	String Player1, Player2;
	Integer timer;
	String timers;
	Integer player;
	String players;
	String winners;
	
	public class Tank {
		int xPos, yPos, team; 
		Integer HP;
		
		public Tank(int x, int y, int t){
			xPos = x; 
			HP = y;
			int tmp = 519;
			while(tab[xPos][tmp] == false && tmp >= 0) {
				tmp--;
			}
			yPos = tmp +1;
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
		public void moveRight() {
		if(xPos >= 5 && xPos <= 1074 && yPos >= 1 && yPos <= 518) {
			if(tab[xPos+1][yPos+1]==false) {
				int tmp = yPos+1;
				while(tab[xPos+1][tmp] == false && tmp >= 0) {
					tmp--;
				}
				yPos = tmp+1;
				xPos++;
				}
			}
		}
		public void moveLeft() {
			if(xPos >= 6 && xPos <= 1075 && yPos >= 1 && yPos <= 518) {
				if(tab[xPos-1][yPos+1]==false) {
					int tmp = yPos+1;
					while(tab[xPos-1][tmp] == false && tmp > 0) {
						tmp--;
					}
					yPos = tmp+1;
					xPos--;
					}
				}
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
	
	public double r(int x1, int y1, int x0, int y0){
		return Math.sqrt((double)(x1-x0)*(x1-x0)+(double)(y1-y0)*(y1-y0));
	}
	
	public void removeTerrain(Integer x, Integer y, int radius) {
		for(int n = x - radius; n <= x + radius; n++) {
			for(int m = y - radius; m <= y + radius; m++) {
				if(r(n,m,x,y) <= (double)radius) {
				//	System.out.println(n + " " + m);
					try{
						tab[n][m]=false;
					}
					catch(Exception e) {
						
					}
				}
			}	
		}
	}
	public void fall() {
		for (Tank t : tanks) {
				while(tab[t.xPos][t.yPos-1] == false && t.yPos >= 2) {
					t.yPos--;
				}
			}
	}
	public void damage(int xx, int yy, int dmg, int rad) {
		for (Tank t : tanks) {
			//System.out.println((int)r(t.xPos,t.yPos,xx,yy) + " " + rad + " " + dmg);
			if(r(t.xPos,t.yPos,xx,yy) <= rad) {
				t.HP -= (int)((1-r(t.xPos,t.yPos,xx,yy)/rad) * dmg);
				//System.out.println(t.HP);
			}
		}
	}
	
	public boolean end() {
		boolean tmp1 = false, tmp2 = false;
		for (Tank t : tanks) {
			if(t.team == 1) {
				if(t.HP > 0) {
					tmp1 = true; 
					//System.out.println("team1");
				}
			}
			if(t.team == 2) {
				if(t.HP > 0) {
					tmp2 = true;
					//System.out.println("team2");
				}
			}
		}
		//if(tmp1 == true) System.out.println("tmp1 = true"); 
		//if(tmp2 == true) System.out.println("tmp2 = true");
		//if(tmp2 == false) System.out.println("tmp2 = false");
		if(tmp1 == true && tmp2 == true) {
			return false;
		}
		else {
			if(tmp1 == true)winner = 1;
			if(tmp2 == true)winner = 2;
			if(tmp1 == false && tmp2 == false)winner = 0;
			return true;
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
		if(p1.xPos < 0 || p1.xPos > 1079 || p1.yPos < 0 || p1.yPos > 519 ) {
			output = false;
		}
		return output;
	}
	
	public Map(String player1, String player2, String terrain, int number_tanks, int life) {
		Player1 = player1;
		Player2 = player2;
		//System.out.println(player1 +" "+ player2);
		player = 1;
		timer = 10;
		winner = null;
		rand = new Random();
		n1 = rand.nextInt(1000);
		n2 = rand.nextInt(1000);
		n3 = rand.nextInt(1000);
		mapgc = null;
		destructive = false;
		colisionX = null;
		colisionY = null;
		radius = null;
		p1 = null;
		tanks = new Tank[number_tanks*2];
		x = 1080;
		y = 520;
		tab = new boolean[x][y];
		flatlevel = 150;
		if(terrain == "Flat") {
			for(int n = 0; n < x; n++) {
				for(int m = 0; m < y; m++) {
					if(m <= (int)(200)) tab[n][m] = true;
					else tab[n][m] = false;
				}
			}
		}
		if(terrain == "Mountains"){
			for(int n = 0; n < x; n++) {
				for(int m = 0; m < y; m++) {
					if(m <= (int)(201 + 200*ImprovedNoise.noise((double)n/200+n1,(double)n/200+n2,(double)n/200+n3) 
					+ 100*ImprovedNoise.noise((double)n/1000+n1,(double)n/1000+n2,(double)n/1000+n3))) tab[n][m] = true;
					else tab[n][m] = false;
				}
			}
		}
		if(terrain == "Hills") {
			for(int n = 0; n < x; n++) {
				for(int m = 0; m < y; m++) {
					if(m <= (int)(200 + 200*ImprovedNoise.noise((double)n/1000+n1,(double)n/1000+n2,(double)n/1000+n3))) tab[n][m] = true;
					else tab[n][m] = false;
				}
			}
		}
	//	tanks[0] = new Tank(100,life,1);
	//	tanks[1] = new Tank(900,life,2);
	//	tanks[2] = new Tank(300,life,1);
	//	tanks[3] = new Tank(400,life,2);
		for(int n = 0; n < number_tanks*2; n+=2) {
			tanks[n] = new Tank(40+rand.nextInt(1000),life,1);
			tanks[n+1] = new Tank(40+rand.nextInt(1000),life,2);
		}
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
	public void drawmapbackbround() {
		for(int m = y-1; m >= 0; m--){
			for(int n = 0; n < x; n++){
				if(tab[n][m] == true) {
					mapgc.setColor(Color.black);
					mapgc.fillRect(n, y - m ,1 ,1);
				}
				if(tab[n][m] == false) {
					mapgc.setColor(Color.blue);
					mapgc.fillRect(n, y - m ,1 ,1);	
				}
			}
		}
	}
	
	public void drawmap(Graphics2D g2d) {
		for(Tank t : tanks)	{
			if(t.team == 1) {
				if(t.HP > 0) {
				g2d.setColor(Color.red);
				g2d.fillRect(t.xPos-5, y-4 - t.yPos ,10 ,6);
				g2d.drawString(t.HP.toString(), t.xPos-10, y-10 - t.yPos);
				}
			}
			if(t.team == 2) {
				if(t.HP > 0) {
				g2d.setColor(Color.green);
				g2d.fillRect(t.xPos-5, y-4 - t.yPos ,10 ,6);
				g2d.drawString(t.HP.toString(), t.xPos-10, y-10 - t.yPos);
				}
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
		timers = "Time Left: "+ timer.toString();
		if(winner == null) {
			g2d.setColor(Color.white);
			g2d.drawString(timers, 10 , 15);
			if(player == 1)players = "Round: "+ Player1;
			if(player == 2)players = "Round: "+ Player2;
			g2d.setColor(Color.white);
			g2d.drawString(players, 10 , 30);
		}
		if(winner != null) {
			if(winner == 1) {
				winners = Player1 + " won!";
				g2d.setColor(Color.white);
				g2d.drawString(winners, 10 , 30);
			}
			if(winner == 2) {
				winners = Player2 + " won!";
				g2d.setColor(Color.white);
				g2d.drawString(winners, 10 , 30);
			}
			if(winner == 0) {
				winners = "Draw";
				g2d.setColor(Color.white);
				g2d.drawString(winners, 10 , 30);
			}
		}
	}
}
