package slotMachineGUI_CW;

import java.awt.*;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import javax.swing.*;

public class GUI extends JFrame{
	private JButton btn1 ;
	private JButton lb1 ;
	private JButton btn2 ;
	private  JButton lb2 ;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JPanel p4;
	private JPanel main;	
	GridBagConstraints gbc = new GridBagConstraints();
	
	GUI(){
		//setLayout();
		setTitle("SLOT MACHINE");
		
		main = new JPanel(new GridBagLayout());
		add(main);
		gbc.insets = new Insets(1, 1, 1, 1);
		
		

		
		
		
		lb2= new JButton("a");
		//p1 = new JPanel(new GridLayout(5,5));
		p1 = new JPanel();
		p1.add(lb2);
		p1.setBackground(randomColor());
		
		
		gbc.gridx=0; 
		gbc.gridy=0;
	//	gbc.gridheight = 5;
	//	gbc.fill = GridBagConstraints.VERTICAL;
		main.add(p1,gbc);

		
		btn1 = new JButton("b");
		p2 = new JPanel();
		p2.add(btn1);
		p2.setBackground(randomColor());
		
		gbc.gridx=1;
		gbc.gridy=1;
		//gbc.gridheight = 1;
		main.add(p2,gbc);
		
		lb1= new JButton("c");
		p3 = new JPanel();
		p3.add(lb1);
		p3.setBackground(randomColor());
		
		gbc.gridx=2;
		gbc.gridy=2;
		//gbc.gridheight = 1;
		main.add(p3,gbc);
		
		btn2 = new JButton("d");
		p4 = new JPanel();
		p4.add(btn2);
		p4.setBackground(randomColor());
		
		gbc.gridx=3;
		gbc.gridy=3;
		gbc.gridwidth =3;
		//gbc.gridheight = 1;
	//	gbc.fill= GridBagConstraints.HORIZONTAL;
		
		main.add(p4,gbc);
		//btn1.setText("click me once");
		//btn2.setText("click ne twice");
		event e = new event();
		p1.addMouseListener(e);
		p2.addMouseListener(e);
		p3.addMouseListener(e);
		p4.addMouseListener(e);
		main.addMouseListener(e);
 
		
	}
	
	public class event implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			p1.setBackground(randomColor());
			p2.setBackground(randomColor());
			p3.setBackground(randomColor());
			p4.setBackground(randomColor());
			main.setBackground(randomColor());
			
			
		}

		public void mousePressed(MouseEvent e) {
			p1.setBackground(randomColor());
			p2.setBackground(randomColor());
			p3.setBackground(randomColor());
			p4.setBackground(randomColor());
			main.setBackground(randomColor());

		}

		public void mouseReleased(MouseEvent e) {
			p1.setBackground(randomColor());
			p2.setBackground(randomColor());
			p3.setBackground(randomColor());
			p4.setBackground(randomColor());
			main.setBackground(randomColor());

		}

		public void mouseEntered(MouseEvent e) {
			p1.setBackground(randomColor());
			p2.setBackground(randomColor());
			p3.setBackground(randomColor());
			p4.setBackground(randomColor());
			main.setBackground(randomColor());

	
		}

		public void mouseExited(MouseEvent e) {
			p1.setBackground(randomColor());
			p2.setBackground(randomColor());
			p3.setBackground(randomColor());
			p4.setBackground(randomColor());
	
		}
	}
	
	public Color randomColor(){
		int r = (int) (Math.random()*256);
		int g = (int) (Math.random()*256);
		int b = (int) (Math.random()*256);
		return(new Color(r,g,b));
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GUI a = new GUI();
		a.setSize(500 ,500);
		a.setDefaultCloseOperation(EXIT_ON_CLOSE);
		a.setVisible(true);
		a.pack();
	
	}

}
