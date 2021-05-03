package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ActionListener {

	int angleV, velocityV;
	String ammu;
	String[] choose;
	GameFrame pointer;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem1, menuItem2, menuItem3;
	
	JPanel panelDown;
	JPanel movementP, velocityP, angleP, ammuP;
	Panel panelCenter;
	JMenuItem menuitem1, menuitem2;
	JMenuBar menubar;
	JSlider velocity;
	JSlider angle;
	JButton moveLeft,moveRight;
	JComboBox<String> ammunition;
	
	TitledBorder title1, title2, title3, title4;
	Border blackline;
	
	Map map;
	
	public GameFrame() throws HeadlessException {
	
		String[] choose = {"pierwsza", "druga", "trzecia", "czwarta"};
		
		map = new Map();
		pointer = this;
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		
		panelDown = new JPanel();
		panelCenter = new Panel(map); //to bd panel z plansza, pewnie jako osobna klasa
		movementP = new JPanel();
		velocityP = new JPanel();
		angleP = new JPanel();
		ammuP = new JPanel();
		
		velocity = new JSlider(JSlider.HORIZONTAL, 0, 30, 5);
		angle = new JSlider(JSlider.HORIZONTAL,-90, 90 , 0);
		velocity.setMajorTickSpacing(10);
		velocity.setMinorTickSpacing(1);
		velocity.setPaintTicks(true);
		velocity.setPaintLabels(true);
		angle.setMajorTickSpacing(30);
		angle.setMinorTickSpacing(5);
		angle.setPaintTicks(true);
		angle.setPaintLabels(true);
		moveLeft = new JButton("<");
		moveRight = new JButton(">");
		ammunition = new JComboBox<String>(choose);

		TitledBorder title1, title2, title3, title4;
		Border blackline = BorderFactory.createLineBorder(Color.black);	
		
			
		this.setLayout(new BorderLayout());
		this.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setSize(new Dimension(1080, 520));
		this.add(panelDown, BorderLayout.SOUTH);
		
		
		//menu
		menubar = new JMenuBar();
		menu = new JMenu("Exit");
	//	menuitem1 = new JMenuItem("Open");
	//	menuitem2 = new JMenuItem("Save");
	//	menu.add(menuitem1);
	//	menu.add(menuitem2);
		menubar.add(menu);
		this.setJMenuBar(menubar);
		
		//reszta
		panelDown.setLayout(new GridLayout(1,4));
		panelDown.setPreferredSize(new Dimension (pointer.getWidth(), 80));
		panelDown.add(movementP);
		movementP.add(moveLeft);
		movementP.add(moveRight);
		
		title1 = BorderFactory.createTitledBorder(blackline, "Move");
		title1.setTitleJustification(TitledBorder.CENTER);
		movementP.setBorder(title1);
		panelDown.add(velocityP);
		velocityP.add(velocity);
		
		title2 = BorderFactory.createTitledBorder(blackline, "Velocity");
		title2.setTitleJustification(TitledBorder.CENTER);
		velocityP.setBorder(title2);
		panelDown.add(angleP);
		angleP.add(angle);
		angle.addChangeListener(new ChangeListener () {
			
			public void stateChanged(ChangeEvent e) {
				angleV = angle.getValue();
			}
		});
		
		title3 = BorderFactory.createTitledBorder(blackline, "Angle");   
		title3.setTitleJustification(TitledBorder.CENTER);
		angleP.setBorder(title3);
		panelDown.add(ammuP);
		ammuP.add(ammunition);
		ammunition.addItemListener(new ItemListener () {

			public void itemStateChanged(ItemEvent e) {
				
			}
			
		});
		
		title4 = BorderFactory.createTitledBorder(blackline, "Ammunition");
		title4.setTitleJustification(TitledBorder.CENTER);
		ammuP.setBorder(title4);
		
		this.setMinimumSize(new Dimension(600, 450));
		this.setBounds(100, 100, 1080, 720);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Tanks");
		this.setPreferredSize(new Dimension(1080, 720));
		this.setSize(1080,720);	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
