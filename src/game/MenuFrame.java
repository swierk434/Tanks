package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class MenuFrame extends JFrame implements ActionListener {

	JPanel panelSetUp;
	JPanel panelUp;
	JPanel panelDown;
	JPanel panelBlank1;
	JPanel panelBlank2;
	JPanel panelTerrain;
	JPanel pod5, pod4, pod3, pod2, pod1;
	JLabel title;
	JLabel podpis1;
	JTextField par1;
	JLabel podpis2;
	JTextField par2;
	JLabel podpis3, podpis4, podpis5;
	JSlider par4, par5;
	JButton start, terrain1, terrain2, terrain3;
	TitledBorder title1;
	Border blackline;
	
	public MenuFrame() throws HeadlessException {
		
		panelSetUp  = new JPanel();
		panelUp = new JPanel();
		panelDown = new JPanel();
		panelBlank1 = new JPanel();
		panelBlank2 = new JPanel();
		title = new JLabel("Tanks - Computer Game");
		podpis1 =new JLabel("Player 1");
		par1 = new JTextField("player1");
		par1.setMaximumSize(new Dimension(800,50));
		par1.setPreferredSize(new Dimension(800,50));
		pod1 = new JPanel();
		pod1.add(podpis1);
		podpis2 =new JLabel("Player 2");
		par2 = new JTextField("player2");
		par2.setMaximumSize(new Dimension(800,50));
		par2.setPreferredSize(new Dimension(800,50));
		pod2 = new JPanel();
		pod2.add(podpis2);
		podpis3 = new JLabel("Terrain");
		pod3 = new JPanel();
		pod3.add(podpis3);
		///3
			panelTerrain = new JPanel(new GridLayout(1,3));
			panelTerrain.setMaximumSize(new Dimension(800,50));
			panelTerrain.setPreferredSize(new Dimension(800,50));
			terrain1 = new JButton("Flat"); 
			terrain2 = new JButton("Hills"); 
			terrain3 = new JButton("Mountains");
			panelTerrain.add(terrain1);
			panelTerrain.add(terrain3);
			panelTerrain.add(terrain2);
			
			
		podpis4 =new JLabel("Number of tanks in team");
		par4 = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
		par4.setMaximumSize(new Dimension(800,50));
		par4.setPreferredSize(new Dimension(800,50));
		pod4 = new JPanel();
		pod4.add(podpis4);
		podpis5 =new JLabel("Starting tanks hit points");
		pod5 = new JPanel();
		pod5.add(podpis5);
		par5 = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
		par5.setMaximumSize(new Dimension(800,50));
		par5.setPreferredSize(new Dimension(800,50));
		start = new JButton("Start");
		title1 = new TitledBorder("Game parameters");
		blackline = BorderFactory.createLineBorder(Color.black);
	
		
		///
		this.setLayout(new BorderLayout());
			panelBlank1.setPreferredSize(new Dimension(300, 300));
		
		this.add(panelBlank1, BorderLayout.EAST);
			panelBlank2.setPreferredSize(new Dimension(300, 300));
			
		this.add(panelBlank2, BorderLayout.WEST);
			title.setFont(new Font("TimesRoman", Font.BOLD, 15));
			title.setForeground(new Color(51, 0 ,51));
		
		this.add(panelUp, BorderLayout.NORTH);
			panelUp.add(title);
		
		this.add(panelSetUp, BorderLayout.CENTER);
		
		
			panelSetUp.setLayout(new BoxLayout(panelSetUp, BoxLayout.Y_AXIS));
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 10)));
			panelSetUp.setBorder(title1);
			panelSetUp.add(pod1);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 15)));
			panelSetUp.add(par1);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 30)));
			panelSetUp.add(pod2);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 15)));
			panelSetUp.add(par2);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 30)));
			panelSetUp.add(pod3);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 15)));
			panelSetUp.add(panelTerrain);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 30)));
			panelSetUp.add(pod4);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 15)));
			panelSetUp.add(par4);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 30)));
			panelSetUp.add(pod5);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 15)));
			panelSetUp.add(par5);
			
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 30)));
				par4.setMajorTickSpacing(1);
				par4.setMinorTickSpacing(1);
				par4.setPaintTicks(true);
				par4.setPaintLabels(true);
				par5.setMajorTickSpacing(10);
				par5.setMinorTickSpacing(5);
				par5.setPaintTicks(true);
				par5.setPaintLabels(true);
				
		
		this.add(panelDown, BorderLayout.SOUTH);
			panelDown.add(start);
			start.setBackground(new Color(153, 0, 153));
			start.setSize(200, 100);
			
		this.setTitle("Parametry gry");
		this.setBackground(new Color(204, 255, 204));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setPreferredSize(new Dimension(1080, 720));
		this.setMaximumSize(this.getPreferredSize());
		this.setMinimumSize(this.getPreferredSize());
	}

	public static void main(String[] args) {
		MenuFrame frame1 = new MenuFrame();
		frame1.setVisible(true);
		GameFrame frame2 = new GameFrame();
		frame2.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
