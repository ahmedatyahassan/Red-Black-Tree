/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package redblacktree;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.omg.CORBA.INTERNAL;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class gui extends JFrame implements ActionListener{
	Tree obj=new Tree();
	
	JPanel p1=new JPanel();
	JPanel p2=new JPanel();
	
	JButton b1=new JButton("insert");
	JButton b2=new JButton("delete");
	JButton b3=new JButton("clear");
	 
	
	JTextField t1=new JTextField();
	JTextField t2=new JTextField();
	JTextField t3=new JTextField();

	 
    public   gui() {
    	 runn();	
    }
    
    public void runn() {
    	setTitle("Red Black Tree");
		setVisible(true);
		setSize(1200,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		//this.setLocation(10, 500);
		p1.setBackground(Color.white);
		p2.setBackground(Color.cyan);
		p1.setLayout(null);p2.setLayout(null);
	    p1.setBounds(0, 0,1200,60);
	    p2.setBounds(0,60,1200,640);
	    this.add(p1);
	    this.add(p2);
	 
	    t1.setBounds(10, 10, 70, 30);p1.add(t1);
	    b1.setBounds(75,10 , 70, 30);p1.add(b1);
	   
	    
	    t2.setBounds(200, 10, 70, 30);p1.add(t2);
	    b2.setBounds(270,10 , 70, 30);p1.add(b2);
	    b2.addActionListener(this);
	    b1.addActionListener(this);
	    b3.setBounds(470,10 , 70, 30);p1.add(b3);
	    b3.addActionListener(this);
	    

    	
    }
    
	 public void GuiprinterHelper(Node root,int x,int y,int num) {
		 
		if (root!=null)	
		{ 
		///////////////////////to print parent
	     int key =root.key;
		 int color= root.color;
		 JButton b;
		 
		 String s=String.valueOf(key);
		 if (key==0)
		       b=new JButton("null");
		 else 
			  b=new JButton(s);
		      
		 
		 
		 if(color!=0)
			 b.setBackground(Color.red);
		 else 
			 b.setBackground(Color.BLACK);
		 
		 b.setBounds(x, y, 55, 55);
		 b.setForeground(Color.white);  
		 p2.add(b);
		 
		 
		 
		 
	
		
		 GuiprinterHelper(root.left,x-num,y+80,num/2);
		 GuiprinterHelper(root.right,x+num,y+80,num/2);
		
		}
		 
		
	 
	 
	 
	 
	 
 };

 public void Guiprinter() {
		GuiprinterHelper(obj.root,500,70,300);
	}; 

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		if (e.getSource()==b1)
		{
			String inputOfb1=t1.getText();
		    int key =Integer.parseInt(inputOfb1);
			obj.insert(key);
			t1.setText("");
			p2.removeAll();
			Guiprinter();	
		
			
		}
		else if (e.getSource()==b2)
		{
			String inputOfb2=t2.getText().toString();
			int k=Integer.parseInt(inputOfb2);
			obj.delete(k);
			t2.setText("");
			p2.removeAll();
			Guiprinter();
	
		}
		
		else if (e.getSource()==b3) 
		{
			
			obj.clear();
			p2.removeAll();
			Guiprinter();
		    
		}
		
		
	}
	

    
}
