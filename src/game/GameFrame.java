package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements ActionListener {

	String[] choose;
	GameFrame pointer;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem1, menuItem2, menuItem3;
	
	JPanel panelDown;
	JPanel panelCenter; //to bd panel z plansza, pewnie jako osobna klasa
	JPanel movementP;
	JPanel velocityP;
	JPanel angleP;
	JPanel ammuP;
	JMenuItem menuitem1, menuitem2;
	JMenuBar menubar;
	JSlider velocity;
	JSlider angle;
	JButton moveLeft;
	JButton moveRight;
	JComboBox<String> ammunition;
	
	TitledBorder title1, title2, title3, title4;
	Border blackline;
	
	public GameFrame() throws HeadlessException {
	
		String[] choose = {"pierwsza", "druga", "trzecia", "czwarta"};
		
		pointer = this;
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		
		panelDown = new JPanel();
		panelCenter = new JPanel(); //to bd panel z plansza, pewnie jako osobna klasa
		movementP = new JPanel();
		velocityP = new JPanel();
		angleP = new JPanel();
		ammuP = new JPanel();
		
		velocity = new JSlider(JSlider.HORIZONTAL, 0, 30, 5);
		angle = new JSlider(JSlider.HORIZONTAL);
		moveLeft = new JButton("<");
		moveRight = new JButton(">");
		ammunition = new JComboBox<String>(choose);

		TitledBorder title1, title2, title3, title4;
		Border blackline = BorderFactory.createLineBorder(Color.black);	
		
		
		
		//jakiœ layout nwm jaki jeszcze	
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
		
		title3 = BorderFactory.createTitledBorder(blackline, "Angle");   
		title3.setTitleJustification(TitledBorder.CENTER);
		angleP.setBorder(title3);
		panelDown.add(ammuP);
		ammuP.add(ammunition);
		
		title4 = BorderFactory.createTitledBorder(blackline, "Ammunition");
		title4.setTitleJustification(TitledBorder.CENTER);
		ammuP.setBorder(title4);
		
		this.setMinimumSize(new Dimension(600, 450));
		this.setBounds(100, 100, 1080, 720);
		this.setTitle("I love coding <3");
		this.pack();
		this.setPreferredSize(new Dimension(1080, 720));
		this.setSize(1080,720);	
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		GameFrame frame = new GameFrame();
		frame.setVisible(true);
		
	}

}
