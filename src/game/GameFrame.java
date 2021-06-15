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
	
	GameFrame pointer;
	MenuFrame master;
	int angleV, velocityV, ammo = 0;
	double Vx, Vy; // Vx = Math.cos(Math.toRadians(angleV))*velocityV  Vy = Math.sin(Math.toRadians(angleV))*velocityV
	String ammu;
	String[] choose;
	JMenuBar menubar;
	JMenu menu;
	JMenuItem menuitem1, menuitem2, menuitem3, menuitem4;
	
	int number;
	JPanel panelDown;
	JPanel movementP, velocityP, angleP, ammuP, shotP;
	Panel panelCenter;

	JSlider velocity;
	JSlider angle;
	JButton moveLeft,moveRight,shot;
	JComboBox<String> ammunition;
	
	TitledBorder title1, title2, title3, title4, title5;
	Border blackline;
	
	Map map;
	boolean SHOT;
	
	public GameFrame(MenuFrame m, String player1, String player2, String terrain, int number_tanks, int life, Color c1, Color c2) throws HeadlessException { //t1 = new Thread(gameframe,player1,player2,terrain,number_tanks,life);
		String[] choose = {"bum", "BUM", "BUUM", "BUUUUM"};
		
		number = number_tanks;
		pointer = this;
		master = m;
		SHOT = false;
		map = new Map(player1,player2,terrain,number_tanks,life,c1,c2);
		pointer = this;
		
		panelDown = new JPanel();
		panelCenter = new Panel(map); 
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
		menu = new JMenu("Game");
		menuitem1 = new JMenuItem("Exit");
		//menuitem2 = new JMenuItem("Restart");
		menuitem3 = new JMenuItem("Pause");
		menuitem4 = new JMenuItem("Resume");
		
		menu.add(menuitem1);
		//menu.add(menuitem2);
		menu.add(menuitem3);
		menu.add(menuitem4);
		menuitem1.addActionListener(new ActionListener () {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				master.t1.stop();
				pointer.setVisible(false);
				pointer = null;
				master.setVisible(true);
			}
		});
		/*menuitem2.addActionListener(new ActionListener () {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e){
				pointer.setVisible(false);
				pointer = null;
				master.t1.stop();
				master.setVisible(true);
			}
		});*/
		menuitem3.addActionListener(new ActionListener () {
			@SuppressWarnings({ "removal" })
			public void actionPerformed(ActionEvent e){
				try {
					master.t1.suspend();
				}
				catch(Exception e1) {
					
				}
				pointer.panelCenter.repaint(); 
				pointer.panelCenter.revalidate();
			}
		});
		menuitem4.addActionListener(new ActionListener () {
				@SuppressWarnings({ "removal" })
				public void actionPerformed(ActionEvent e){
					try {
						master.t1.resume();
					}
					catch(Exception e1) {
						
					}
					pointer.panelCenter.repaint(); 
					pointer.panelCenter.revalidate();	
			}
		});
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
				pointer.panelCenter.revalidate();
			}
			
		});
		
		title4 = BorderFactory.createTitledBorder(blackline, "Ammunition");
		title4.setTitleJustification(TitledBorder.CENTER);
		ammuP.setBorder(title4);
		
		panelDown.add(shotP);
		shotP.add(shot);
		shot.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e){
				SHOT = true;
			}
		});
		title5 = BorderFactory.createTitledBorder(blackline, "Action");
		title5.setTitleJustification(TitledBorder.CENTER);
		shotP.setBorder(title5);
		this.setBackground(Color.gray);
		this.setMinimumSize(new Dimension(600, 450));
		this.setBounds(100, 100, 1080, 720);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
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
		boolean end = false;
		  while(round <= 50 && end == false){ 
			 // try {
			//	   Thread.sleep(200);
			//   } 
			 //  catch (InterruptedException e) {
			//	   e.printStackTrace();
			//   } 
			  t = 0;
			 // System.out.println(round);
			  map.player = (round%2) +1;
			  while(t < 1000*30) {
				  if(map.tanks[round%(number*2)].HP <= 0) {
					 // System.out.println(round%(number*2)+ " " +map.tanks[round%(number*2)].HP);
					 break;
				   }
				  map.timer = 30 - (int)t/1000;
				   if(moveLeft.getModel().isPressed()) {
					   map.tanks[round%(number*2)].moveLeft();
					   //System.out.println(map.tanks[round%(number*2)].xPos + " " + map.tanks[round%(number*2)].yPos);
				   }
				   if(moveRight.getModel().isPressed()) {
					   map.tanks[round%(number*2)].moveRight();
					   //System.out.println(map.tanks[round%(number*2)].xPos + " " + map.tanks[round%(number*2)].yPos);
				   }
				   try {
					   Thread.sleep(20);
					   t += 20;
				   } 
				   catch (InterruptedException e) {
					   e.printStackTrace();
				   }
				   try {
				   pointer.panelCenter.repaint();  
				   pointer.panelCenter.revalidate();
				   }
				   catch(Exception e) {
					   
				   }
				   if(SHOT == true) {
					   SHOT = false;
					   map.addProjectile(map.tanks[round%(number*2)].xPos,map.tanks[round%(number*2)].yPos,
							   Vx = Math.sin(Math.toRadians(angleV))*velocityV/3,
							   Vy = Math.cos(Math.toRadians(angleV))*velocityV/3);
					   time = 0;
					   while(map.projectileIsValid()==true) {
						   //System.out.println(map.tanks[0].xPos + " " + map.tanks[1].xPos);
						   map.p1.updatePos(time, dt);
						   try {
							   Thread.sleep(1);
							   time += dt;
						   } 
						   catch (InterruptedException e) {
							   e.printStackTrace();
						   }
						   try {
							   pointer.panelCenter.repaint();  
							   pointer.panelCenter.revalidate();
							   }
							   catch(Exception e) {
								   
							   }
					   }
					   
					   map.p1 = null;
					   break; 
				
				   		}
			  		}
				   if(map.colisionX != null) {
					   radius = 0;
					   exp = new Explosion(1,1,true);
					   if(ammo == 0) exp = new Explosion(30, 5, true);
					   if(ammo == 1) exp = new Explosion(100, 20, true);
					   if(ammo == 2) exp = new Explosion(50, 10, false);
					   if(ammo == 3) exp = new Explosion(150, 25, false);
					   map.destructive = exp.destructive;
					   map.damage(map.colisionX, map.colisionY, 200, exp.radius);
					   if(exp.destructive == true) {
						   map.removeTerrain(map.colisionX,map.colisionY, exp.radius);
						   map.drawmapbackbround();
						   map.fall();
						 //  map.printmap();
						   while(exp.radius > 0) {
						   map.radius = exp.radius;
						   try {
							   Thread.sleep(100);
						   } 
						   catch (InterruptedException e) {
							   e.printStackTrace();
						   }
						   try {
							   pointer.panelCenter.repaint();  
							   pointer.panelCenter.revalidate();
							   }
							   catch(Exception e) {
								   
							   }
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
							   try {
								   pointer.panelCenter.repaint(); 
								   pointer.panelCenter.revalidate();
								   }
								   catch(Exception e) {
									   
								   }
							   radius+=exp.delay;
							   }
					   }
					   map.colisionX = null;
					   map.colisionY = null;
					   end = map.end();
					 //  if(end == true)System.out.println("TRUE");
					 //  if(end == false)System.out.println("FALSE");	
				   }
			  try {
				   pointer.panelCenter.repaint();
				   pointer.panelCenter.revalidate();
				   }
				   catch(Exception e) {
					   
				   }
			   
			round++;
		  }
		  try {
			   pointer.panelCenter.repaint(); 
			   pointer.panelCenter.revalidate();
			   }
			   catch(Exception e) {
				   
			   }
	}

}
