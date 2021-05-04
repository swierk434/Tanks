package game;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	Map map; 
	
	Panel(Map m) {
		super();
		map = m;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		map.drawmap(g2d);
		//if(map.p1 != null) {
		//	g.setColor(Color.white);
		//	g.fillOval(map.p1.getX(),520 - map.p1.getY(),4,4);
		//}
	}
	
}