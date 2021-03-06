package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


@SuppressWarnings("serial")
public class MenuFrame extends JFrame{

	MenuFrame pointer;
	GameFrame gameframe;
	JPanel panelSetUp, panelUp, panelDown;
	JPanel panelBlank1, panelBlank2;
	JPanel panelTerrain;
	JPanel pod5, pod4, pod3, pod2, pod1, pan_clr;
	JLabel title;
	JLabel podpis1, podpis2;
	JTextField par1, par2;
	JLabel podpis3, podpis4, podpis5;
	JSlider par4, par5;
	JButton start, p_clr, p_bclr;
	JRadioButton terrain1, terrain2, terrain3;
	Color color1, color2;
	TitledBorder title1;
	Border blackline;
	Thread t1;
	
	String player1, player2,terrain;  
	int number_tanks, life;
	
	public MenuFrame() throws HeadlessException {
		color1 = Color.red;
		color2 = Color.green;
		blackline = BorderFactory.createLineBorder(Color.black);
		player1 = "player1";
		player2 = "player2";
		terrain = "Flat";
		number_tanks = 3;
		life = 100;
		pointer = this;
		panelSetUp  = new JPanel();
		panelUp = new JPanel();
		panelDown = new JPanel();
		panelBlank1 = new JPanel();
		panelBlank2 = new JPanel();
		title = new JLabel("Tanks - Early Access    E3 Gameplay Trailer");
		podpis1 =new JLabel("Player 1");
		par1 = new JTextField("player1");
		par1.setMaximumSize(new Dimension(800,50));
		par1.setPreferredSize(new Dimension(800,50));
		par1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				player1 = par1.getText();
			}
		});
		
		pod1 = new JPanel();
		pod1.add(podpis1);
		podpis2 =new JLabel("Player 2");
		par2 = new JTextField("player2");
		par2.setMaximumSize(new Dimension(800,50));
		par2.setPreferredSize(new Dimension(800,50));
		par2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				player2 = par2.getText();
				System.out.println(player2);
			}
		});
		
		pod2 = new JPanel();
		pod2.add(podpis2);
		podpis3 = new JLabel("Terrain");
		pod3 = new JPanel();
		pod3.add(podpis3);
		
		pan_clr = new JPanel();
		pan_clr.setBorder(blackline);
		p_clr = new JButton("Team1 color");
		p_clr.setBackground(Color.red);
		p_bclr = new JButton("Team2 color");
		p_bclr.setBackground(Color.green);
		p_bclr.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
				color2 = JColorChooser.showDialog(null, "Choose Color", Color.black);
				p_bclr.setBackground(color2);
				}
				catch(Exception e1){
					
				}
			}
		});
		p_clr.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				try {
				color1 = JColorChooser.showDialog(null, "Choose Color", Color.WHITE);
				p_clr.setBackground(color1);
				}
				catch (Exception e1) {
					
				}
			}	
		});
		pan_clr.add(p_clr);
		pan_clr.add(p_bclr);
		
		
		///3
			panelTerrain = new JPanel(new GridLayout(1,3));
			panelTerrain.setMaximumSize(new Dimension(800,50));
			panelTerrain.setPreferredSize(new Dimension(800,50));
			terrain1 = new JRadioButton("Flat"); 
			terrain2 = new JRadioButton("Hills"); 
			terrain3 = new JRadioButton("Mountains");
			ButtonGroup group = new ButtonGroup();
			group.add(terrain1);
			group.add(terrain2);
			group.add(terrain3);
			terrain1.addActionListener(buttonListener);
			terrain2.addActionListener(buttonListener);
			terrain3.addActionListener(buttonListener);
			panelTerrain.add(terrain1);
			panelTerrain.add(terrain3);
			panelTerrain.add(terrain2);
			
			
		podpis4 =new JLabel("Number of tanks in team");
		par4 = new JSlider(JSlider.HORIZONTAL, 1, 5, 3);
		par4.setMaximumSize(new Dimension(800,50));
		par4.setPreferredSize(new Dimension(800,50));
		par4.addChangeListener(new ChangeListener () {
			
			public void stateChanged(ChangeEvent e) {
				number_tanks = par4.getValue();
			}
		});
		
		pod4 = new JPanel();
		pod4.add(podpis4);
		podpis5 =new JLabel("Starting tanks hit points");
		pod5 = new JPanel();
		pod5.add(podpis5);
		par5 = new JSlider(JSlider.HORIZONTAL, 50, 150, 100);
		par5.setMaximumSize(new Dimension(800,50));
		par5.setPreferredSize(new Dimension(800,50));
		par5.addChangeListener(new ChangeListener () {
			
			public void stateChanged(ChangeEvent e) {
				life = par5.getValue();
			}
		});

		start = new JButton("Start");
		title1 = new TitledBorder("Game parameters");
	
		
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
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 15)));
			panelSetUp.add(pan_clr);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 15)));
			panelSetUp.add(pod3);
			panelSetUp.add(Box.createRigidArea(new Dimension(0, 5)));
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
			start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameframe = new GameFrame(pointer, par1.getText(), par2.getText(), terrain, number_tanks, life, color1, color2);
					gameframe.setVisible(true);	
					t1 = new Thread(gameframe);
					t1.start();
					pointer.setVisible(false);
				}
			});	
			
		this.setTitle("Parametry gry");
		this.setBackground(new Color(204, 255, 204));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension(1080, 720));
		this.setMaximumSize(this.getPreferredSize());
		this.setMinimumSize(this.getPreferredSize());
	}
	
	ActionListener buttonListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			terrain = ((JRadioButton) e.getSource()).getText();
		}
	};
}
