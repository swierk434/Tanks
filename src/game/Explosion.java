package game;

public class Explosion {
	int radius, delay;
	boolean destructive;
	
	public Explosion(int r, int d, boolean des) {
		radius = r;
		delay = d;
		destructive = des;
	}
}
