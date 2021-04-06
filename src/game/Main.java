package game;

import javax.swing.SwingUtilities;

public class Main{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			GameFrame frame1 = new GameFrame();
			frame1.setVisible(true);	
			MenuFrame frame2 = new MenuFrame();
			frame2.setVisible(true);
			}
		});
	}

}
