// import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
// import java.awt.GridLayout;
import java.awt.Point;
// import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
// import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
// import java.awt.Component;
// import java.awt.Graphics2D;
// import java.awt.Image;
// import java.awt.Panel;
// import java.awt.geom.AffineTransform;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.List;
// import javax.swing.JLabel;


public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {

	public static boolean debugging = true;
	
	//Timer related variables
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	StaticSprite[][] backRows = new StaticSprite[6][6];
	StaticSprite[][] lavaRows = new StaticSprite[4][40];
	StaticSprite[][] waterRows = new StaticSprite[2][20];
	Sprite[] maleTeachers = new Sprite[6];
	Sprite[] femaleTeachers = new Sprite[6];
	Sprite player = new Sprite(0, 0, 500, 300-16, "Student.png", 16, 20);
	
	
	Font myFont = new Font("Courier", Font.BOLD, 40);
	// SimpleAudioPlayer backgroundMusic = new SimpleAudioPlayer("scifi.wav", false);
//	Music soundBang = new Music("bang.wav", false);
//	Music soundHaha = new Music("haha.wav", false);
	
	//frame width/height
	int width = 600;
	int height = 600;	
	

	public void paint(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < backRows.length; i++) {
			for (StaticSprite staticSprite : backRows[i]) {
				staticSprite.paint(g);
			}
		}
		for (int i = 0; i < lavaRows.length; i++) {
			for (StaticSprite staticSprite : lavaRows[i]) {
				staticSprite.paint(g);
			}
		}
		for (int i = 0; i < waterRows.length; i++) {
			for (StaticSprite staticSprite : waterRows[i]) {
				staticSprite.paint(g);
			}
		}
		if (player.getX() < 0 && player.getVX() < 0) {player.setVX(0);}
		if (player.getX() > 568 && player.getVX() > 0) {player.setVX(0);}
		if (player.getY() > 540 && player.getVY() > 0) {player.setVY(0);}
		if (player.getY() <= 0 && player.getVY() < 0) {player.setVY(0);}
		player.paint(g);
		
		
	}
		
	public static void main(String[] arg) {
		new Frame();
	}	
	
	public Frame(){
		JFrame f = new JFrame("School Day");
		f.setSize(new Dimension(width, height));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
 		f.addMouseListener(this);
		f.addKeyListener(this);

		for (int i = 0; i < backRows.length; i++) {
			for (int j = 0; j < backRows[0].length; j++) {
				backRows[i][j] = new StaticSprite(j*100, i*100, "/imgs/math_background.png", 100, 100);
			}
		}
		for (int i = 0; i < lavaRows.length; i++) {
			for (int j = 0; j < lavaRows[0].length; j++) {
				lavaRows[i][j] = new StaticSprite(j*16, i*16 + 400, "/imgs/Lava.png", 16, 16);
			}
		}
		for (int i = 0; i < waterRows.length; i++) {
			for (int j = 0; j < waterRows[0].length; j++) {
				waterRows[i][j] = new StaticSprite(j*32, i*32 + 200, "/imgs/water.png", 32, 32);
			}
		}

		
	
		// backgroundMusic.play();
	
		
		//the cursor image must be outside of the src folder
		//you will need to import a couple of classes to make it fully 
		//functional! use eclipse quick-fixes
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("torch.png").getImage(),
				new Point(0,0),"custom cursor"));	
		
		
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		
	
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
//		 TODO Auto-generated method stub
		

		switch (arg0.getKeyCode()) {
			case (87): // W
				if (player.getX() <= 0) {break;}
				player.setVY(-3);
				break;
			case(65): // A
				// if (locked || gameOver) {break;}
				player.setVX(-3);
				break;
			case(83): // S
				// if (locked || gameOver) {break;}
				player.setVY(3);
				break;
			case(68): // D
				if (player.getX() >= 600-16) {break;}
				player.setVX(3);				
				break;
			// case (32):
			// 	if (gameOver) {break;}
			// 	//Mounting and dismounting
			// 	if (canLock) {
			// 		//Mounting
			// 		System.out.println("Mounting");
			// 		locked = true;
			// 		canLock = false;
			// 		break;
			// 	}
			// 	if (locked) {
			// 		//Dismounting
			// 		System.out.println("Dismount");
			// 		locked = false;
			// 		player.setY(player.getY() - 30);
			// 		break;
			// 	}
				
			// 	break;
			// case (80): //P
			// 	if (gameOver || player.isCompleted()) {
			// 		this.reset();
			// 		this.lives.clear();
			// 		for (int l = 0; l < 3; l++) { this.lives.add(new LifeImage(l*40, 10));}
			// 		break;
			// 	}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
			case (87): // W
				// if (locked || gameOver) {break;}
				player.setVY(0);
				break;
			case(65): // A
				// if (player.getX() <= 0) {break;}
				player.setVX(0);
				break;
			case(83): // S
				// if (locked || gameOver) {break;}
				player.setVY(0);
				break;
			case(68): // D
				// if (locked || gameOver) {break;}
				player.setVX(0);				
				break;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
