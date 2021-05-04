package game;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class Main{
	
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())){
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (Exception e ) {
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
			GameFrame frame1 = new GameFrame();
			frame1.setVisible(true);	
			//MenuFrame frame2 = new MenuFrame();
			//frame2.setVisible(true);
			Thread t1 = new Thread(frame1);
			t1.start();
			}
		});
	}

}
