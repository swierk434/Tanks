package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	Map map; 
	BufferedImage image;
	Graphics2D graphics;
	
	Panel(Map m) {
		super();
		//this.setBackground(Color.gray);
		map = m;
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
			if(image == null) {
				image = (BufferedImage) this.createImage(1080, 520);
				graphics = image.createGraphics();
				graphics.setColor(Color.white);
				graphics.fillRect(0, 0, 1080, 520);
				map.mapgc = graphics;
				map.drawmapbackbround();
			}
			g2d.drawImage(image, 0, 0, this);		
			map.drawmap(g2d);
		}
	}
	
