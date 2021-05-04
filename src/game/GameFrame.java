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
public class GameFrame extends JFrame implements Runnable{
	
	int angleV, velocityV, ammo = 0;
	double Vx, Vy; // Vx = Math.cos(Math.toRadians(angleV))*velocityV  Vy = Math.sin(Math.toRadians(angleV))*velocityV
	String ammu;
	String[] choose;
	GameFrame pointer;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItem1, menuItem2, menuItem3;
	
	JPanel panelDown;
	JPanel movementP, velocityP, angleP, ammuP, shotP;
	Panel panelCenter;
	JMenuItem menuitem1, menuitem2;
	JMenuBar menubar;
	JSlider velocity;
	JSlider angle;
	JButton moveLeft,moveRight,shot;
	JComboBox<String> ammunition;
	
	TitledBorder title1, title2, title3, title4, title5;
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
		shotP = new JPanel();
		
		angleV = 0; 
		velocityV = 10;
		ammo = 0;
		
		velocity = new JSlider(JSlider.HORIZONTAL, 10, 30, 10);
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
		shot = new JButton("SHOT");
		ammunition = new JComboBox<String>(choose);

		//rthread = new RoundThread(map, pointer);
		
		TitledBorder title1, title2, title3, title4;
		Border blackline = BorderFactory.createLineBorder(Color.black);	
		
			
		this.setLayout(new BorderLayout());
		this.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setSize(new Dimension(1080, 520));
		panelCenter.setMaximumSize(new Dimension(1080, 520));
		panelCenter.setMinimumSize(new Dimension(1080, 520));
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
		panelDown.setLayout(new GridLayout(1,5));
		panelDown.setPreferredSize(new Dimension (pointer.getWidth(), 80));
		panelDown.add(movementP);
		movementP.add(moveLeft);
		moveLeft.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e){
			//	map.tanks[rthread.getRound()%2].updatePosLeft();
			}
		});
		movementP.add(moveRight);
		moveRight.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e){
			//	map.tanks[rthread.getRound()%2].updatePosRight();
			}
		});

		title1 = BorderFactory.createTitledBorder(blackline, "Move");
		title1.setTitleJustification(TitledBorder.CENTER);
		movementP.setBorder(title1);
		
		panelDown.add(velocityP);
		velocityP.add(velocity);
		velocity.addChangeListener(new ChangeListener () {
			
			public void stateChanged(ChangeEvent e) {
				velocityV = velocity.getValue();
			}
		});
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
				ammo = ammunition.getSelectedIndex();
				pointer.panelCenter.repaint();
			}
			
		});
		
		title4 = BorderFactory.createTitledBorder(blackline, "Ammunition");
		title4.setTitleJustification(TitledBorder.CENTER);
		ammuP.setBorder(title4);
		
		panelDown.add(shotP);
		shotP.add(shot);
		shot.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e){
			
			}
		});
		title5 = BorderFactory.createTitledBorder(blackline, "Action");
		title5.setTitleJustification(TitledBorder.CENTER);
		shotP.setBorder(title5);
		
		this.setMinimumSize(new Dimension(600, 450));
		this.setBounds(100, 100, 1080, 720);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Tanks");
		this.setPreferredSize(new Dimension(1080, 720));
		this.setSize(1080,720);	
		
	}

	@Override
	public void run() {
		Explosion exp;
		int t, radius = 30;
		double dt = 0.05;
		double time;
		int round = 0;
		  while(round <= 10){ 
			  t = 0;
			  //System.out.println("1");
			  while(t < 1000*10) {
				  
				   if(moveLeft.getModel().isPressed()) {
					   map.tanks[round%2].updatePosLeft();
				   }
				   if(moveRight.getModel().isPressed()) {
					   map.tanks[round%2].updatePosRight();
				   }
				   try {
					   Thread.sleep(20);
					   t += 20;
				   } 
				   catch (InterruptedException e) {
					   e.printStackTrace();
				   }
				   pointer.panelCenter.repaint();   
				   if(shot.getModel().isPressed()) {
					   map.addProjectile(map.tanks[round%2].xPos,map.tanks[round%2].yPos,Vx = Math.sin(Math.toRadians(angleV))*velocityV/3,Vy = Math.cos(Math.toRadians(angleV))*velocityV/3);
					   time = 0;
					   while(map.projectileIsValid()==true) {
						//   System.out.println(map.tanks[0].xPos + " " + map.tanks[1].xPos);
						   map.p1.updatePos(time, dt);
						   try {
							   Thread.sleep(1);
							   time += dt;
						   } 
						   catch (InterruptedException e) {
							   e.printStackTrace();
						   }
						   pointer.panelCenter.repaint();
					   }
					   map.p1 = null;
					   break;
				   }
				   if(map.colisionX != null) {
					   radius = 0;
					   exp = new Explosion(1,1,true);
					   if(ammo == 0) exp = new Explosion(30, 5, true);
					   if(ammo == 1) exp = new Explosion(100, 20, true);
					   if(ammo == 2) exp = new Explosion(50, 10, false);
					   if(ammo == 3) exp = new Explosion(150, 25, false);
					   map.destructive = exp.destructive;
					   if(exp.destructive == true) {
						   while(exp.radius > 0) {
						   map.radius = exp.radius;
						   try {
							   Thread.sleep(100);
						   } 
						   catch (InterruptedException e) {
							   e.printStackTrace();
						   }
						   pointer.panelCenter.repaint();
						   exp.radius -= exp.delay;
						   }
					   }
					   if(exp.destructive == false) {
						   while(radius < exp.radius) {
							   map.radius = radius;
							   try {
								   Thread.sleep(100);
							   } 
							   catch (InterruptedException e) {
								   e.printStackTrace();
							   }
							   pointer.panelCenter.repaint();
							   radius+=exp.delay;
							   }
					   }
					   
					   map.colisionX = null;
					   map.colisionY = null;
				   }
			  }
			pointer.panelCenter.repaint();
			round++;
		  }
	}

}
