/**
 * 
 */
package guiFramework;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import structureDefinition.Matrix;

/**
 * @author Ovkaric
 *
 */
public class GuiManager {
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static boolean condition=true;
	private static int WIDTH = screenSize.width-5;
	private static int HEIGHT = screenSize.height-85;
	private static CanvasManager cm;
	private static Timer timer;
	private static boolean began= false;
	
	private static JButton stop;
	private static JButton step;
	private static JButton start;
	
	public static void main(String[] args) {

		cm = new CanvasManager(WIDTH,HEIGHT);
		JFrame frame = new JFrame("Game Of Life");
		cm.setBounds(0,0,WIDTH, HEIGHT);
		cm.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		
		cm.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Point point = MouseInfo.getPointerInfo().getLocation();
				Point point2 = cm.getLocationOnScreen();
				int x =(int)((point.x-point2.x)/10);
				int y =(int)((point.y-point2.y)/10);
				if(Matrix.getCell(x, y).getState()){
					cm.fill(x, y, Color.WHITE);
				} else {
					cm.fill(x, y, Color.BLACK);
				}
				Matrix.setCell(x, y);
				GuiManager.repaintCanvas();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			});
		
		
		JScrollPane scroll = new JScrollPane(cm);
		DragScrollHandler.createDragScrollHandlerFor(cm);
		
		step = new JButton("STEP");
		step.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stepTimer();
			}
		});
		
		stop = new JButton("STOP");
		stop.setVisible(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopTimer();
				stop.setVisible(false);
				start.setVisible(true);
				step.setVisible(true);
			}
		});
		start = new JButton("START");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!began){
					beginTimer();
				}else{
					startTimer();
				}
				step.setVisible(false);
				start.setVisible(false);
				stop.setVisible(true);
			}
		});
		
		

		
		JMenuBar menuBar = new JMenuBar();
		//JMenu menu = new JMenu("File");
		JMenu operations = new JMenu("Operations");
		JMenu help = new JMenu("Help");
		
		JMenuItem slow = new JMenuItem("SlowDown");
		slow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.setDelay(timer.getDelay()+40);
			}
		});
		
		JMenuItem speed = new JMenuItem("SpeedUp");
		speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.setDelay(timer.getDelay()-40);
			}
		});
		
		JMenuItem rand = new JMenuItem("Generate Random Matrix");
		rand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Matrix.GenerateRandomMatrix(WIDTH/10, HEIGHT/10);
				repaintCanvas();
				stopTimer();
				stop.setVisible(false);
				start.setVisible(true);
				step.setVisible(true);
			}
		});
		JMenuItem empt = new JMenuItem("Generate Empty Matrix");
		empt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Matrix.GenerateVoidMatrix(WIDTH/10, HEIGHT/10);
				repaintCanvas();
				stopTimer();
				stop.setVisible(false);
				start.setVisible(true);
				step.setVisible(true);
			}
		});
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JMenuItem aboutitem = new JMenuItem("About");
		aboutitem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Author :    Stanko Ovkaric\n"
					+   "Webpage:	 www.VicariousMirror.com"
								,"About", 1);

			}
		});
		/*
		JMenuItem save = new JMenuItem("Save structure");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Matrix.save(JOptionPane.showInputDialog(null,"Plase insert a name for the new structure!","Save structure",1), Matrix.getSubMatrix());
			}
		});
		JMenuItem load = new JMenuItem("Load structure");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.showDialog(null, "ASDF");
				Matrix.load(0, 0, fc.getSelectedFile().getAbsolutePath());
				repaintCanvas();
			}
		});
		*/
		//menuBar.add(menu);
		menuBar.add(operations);
		menuBar.add(help);
		menuBar.add(start);
		menuBar.add(step);
		menuBar.add(stop);
		
		
		help.add(aboutitem);
		
		//menu.add(save);
		//menu.add(load);
		//menu.addSeparator();
		
		
		operations.add(rand);
		operations.add(empt);
		operations.addSeparator();
		operations.add(slow);
		operations.add(speed);
		operations.addSeparator();
		operations.add(exit);
		
		frame.setJMenuBar(menuBar);
		
		frame.add(scroll, BorderLayout.CENTER);
		frame.setExtendedState(Frame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Matrix.GenerateVoidMatrix(WIDTH/10, HEIGHT/10);
		repaintCanvas();
}
		
		
	
	
	public static void stopTimer(){timer.stop();}
	public static void stepTimer(){checkUpdate();}
	public static void startTimer(){timer.start();}
	    
	public static void beginTimer(){
		began=true;
		timer = new Timer(80, new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  checkUpdate();
		      }
		    });
		timer.start();
	}
	
	public static void checkUpdate(){
		Matrix.checkMatrix();
		Matrix.updateMatrix();
    	repaintCanvas();
	}
	
	public static void repaintCanvas(){
		for (int j = 0; j < WIDTH/10; j++) {
			for (int i = 0; i < HEIGHT/10; i++) {
				if(Matrix.getMatrix()[j][i].getState()){
					cm.fill(j, i, Color.BLACK);
				} else {
				    cm.fill(j, i, Color.WHITE);
				}
			}
		}
		
	}
}
