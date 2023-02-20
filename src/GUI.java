import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI extends JFrame implements ActionListener
{
	Engine engine;
	JPanel center, north;
	JButton[][] label = new MyButton[6][7];
	JLabel left, middle, right;
	JButton newGame;
	public GUI() 
	{
		super("Connect 4");
		
		Container pane = this.getContentPane();
		
		this.engine = new Engine();
		this.setBounds(300, 50, 700, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.left = new JLabel();
		this.left.setText("Number of moves red: "+this.engine.numRed);
		this.right = new JLabel();
		this.right.setText("Number of moves yellow: "+this.engine.numYellow);
		this.middle = new JLabel();
		this.middle.setText("On the Move: "+this.engine.move);
		this.north = new JPanel( new GridLayout(1,3));
		this.north.add(left);
		this.north.add(middle);
		this.north.add(right);
		pane.add(north,BorderLayout.NORTH);
		
		this.center = new JPanel( new GridLayout(6,7));
		for( int i=0; i<6; i++)
			for( int j=0; j<7; j++)
			{
				this.label[i][j] = new MyButton(i,j);
				this.label[i][j].addActionListener( this);
				this.label[i][j].setSize(new Dimension(110,110) );
				this.center.add( this.label[i][j]);
			}
		this.refresh();
		pane.add(center, BorderLayout.CENTER);
		
		this.newGame = new JButton();
		this.newGame.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				engine.init();
				refresh();
			}
		});
		this.newGame.setText("New game");
		
		pane.add( this.newGame, BorderLayout.SOUTH);
		
		this.setVisible( true);
	}
	public void refresh()
	{
		for( int i=0; i<6; i++)
			for( int j=0; j<7; j++)
			{
				if( this.engine.table[i][j].state == Colors.EMPTY )
				{
					Icon slika = new ImageIcon("src/images/back.png");
					this.label[i][j].setIcon( slika);
				}
				else if( this.engine.table[i][j].state == Colors.RED )
				{
					Icon slika = new ImageIcon("src/images/red.png");
					this.label[i][j].setIcon( slika);
				}
				else
				{
					Icon slika = new ImageIcon("src/images/yellow.png");
					this.label[i][j].setIcon( slika);
				}
			}
		this.left.setText("Number of moves red: "+this.engine.numRed);
		this.right.setText("Number of moves yellow: "+this.engine.numYellow);
		this.middle.setText("On the Move: ");
		Icon icon;
		if( this.engine.move == -1)
			icon = new ImageIcon("src/images/red_small.png");
		else
			icon = new ImageIcon("src/images/yellow_small.png");
		this.middle.setIcon(icon);	
	}
	public void actionPerformed(ActionEvent e) 
	{
		MyButton button = (MyButton)e.getSource();
		int x = this.engine.addChip(button.j);
		int y = button.j;
		this.refresh();
		
		if( x!=-1 )
		{
			if( this.engine.areFourConnected( this.engine.whoWasOnTheMove()))
			{
				int answer = JOptionPane.showConfirmDialog(null, "Winner is "+this.engine.whoWasOnTheMove(), "New Game?", JOptionPane.YES_NO_OPTION);
				if( answer==0)
				{
					this.engine.init();
					this.refresh();
				}
				else
				{
					System.exit(1);
				}
			}
		}
	}
}
