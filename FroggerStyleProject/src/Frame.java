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
	boolean safe = false;
	int waveTimer = 5; //each wave of enemies is 20s
	long ellapseTime = 0;
	Font timeFont = new Font("Courier", Font.BOLD, 70);
	int level = 0;
	StaticSprite[][] backRows = new StaticSprite[6][6];
	StaticSprite[][] lavaRows = new StaticSprite[8][40];
	StaticSprite[][] waterRows = new StaticSprite[4][20];
	Sprite[][] maleTeachers = new Sprite[2][6];
	Sprite[][] femaleTeachers = new Sprite[2][6];
	Sprite[][] bigController = new Sprite[2][6];
	Sprite[][] smallController = new Sprite[2][6];

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
		safe = false;
		for (int i = 0; i < backRows.length; i++) {
			for (StaticSprite staticSprite : backRows[i]) {
				staticSprite.paint(g);
			}
		}
		for (int i = 0; i < lavaRows.length; i++) {
			for (StaticSprite staticSprite : lavaRows[i]) {
				if (staticSprite.collisionsWith(player)){
					player.setX(300-16);
					player.setY(500);
				}
				staticSprite.paint(g);
			}
		}
		for (int i = 0; i < waterRows.length; i++) {
			for (StaticSprite staticSprite : waterRows[i]) {
				if (staticSprite.collisionsWith(player)){
					player.setVX(Math.max(-2, Math.min(2, player.getVX())));
					player.setVY(Math.max(-2, Math.min(2, player.getVY())));
				}
				staticSprite.paint(g);
			}
		}
		for (Sprite big : bigController[0]) {
			if (big.getX() > 560) {big.setX(-40);}
			big.paint(g);
		}
		for (Sprite big : bigController[1]) {
			if (big.getX() > 560) {big.setX(-40);}
			big.paint(g);
		}
		for (Sprite small : smallController[0]) {
			if (small.getX() > 560) {small.setX(-40);}
			small.paint(g);
		}
		for (Sprite small : smallController[1]) {
            if (small.getX() < -40) {small.setX(560);}
            small.paint(g);
        }
		
		
		if (player.getX() < 0 && player.getVX() < 0) {player.setVX(0);}
		if (player.getX() > 568 && player.getVX() > 0) {player.setVX(0);}
		if (player.getY() > 540 && player.getVY() > 0) {player.setVY(0);}
		if (player.getY() <= 0 && player.getVY() < 0) {player.setVY(0);}
		player.paint(g);
		for (int i = 0; i < maleTeachers.length; i++) {
			for (Sprite maleTeacher : maleTeachers[i]) {
				if (maleTeacher.getX() > 560) {maleTeacher.setX(-40);}
				maleTeacher.paint(g);
			}
		}
		for (int i = 0; i < femaleTeachers.length; i++) {
			for (Sprite femaleTeacher : femaleTeachers[i]) {
				if (femaleTeacher.getX() < -40) {femaleTeacher.setX(560);}
				femaleTeacher.paint(g);
			}
		}
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
				lavaRows[i][j] = new StaticSprite(j*16, i*16 + 136, "/imgs/Lava.png", 16, 16);
			}
		}
		for (int i = 0; i < waterRows.length; i++) {
			for (int j = 0; j < waterRows[0].length; j++) {
				waterRows[i][j] = new StaticSprite(j*32, i*32 + 336, "/imgs/water.png", 32, 32);
			}
		}
		for (int i = 0; i < maleTeachers[0].length; i++) {
			maleTeachers[0][i] = new Sprite(2, 0, 400, 100*i+10, "male_teacher.png", 25, 32);
			maleTeachers[1][i] = new Sprite(2, 0, 336, 100*i+10, "male_teacher.png", 25, 32);
		}
		for (int i = 0; i < femaleTeachers[0].length; i++) {
			femaleTeachers[0][i] = new Sprite(-1, 0, 432, 100*i+10, "female_teacher.png", 25, 32);
			femaleTeachers[1][i] = new Sprite(-1, 0, 368, 100*i+10, "female_teacher.png", 25, 32);
		}
		for (int i = 0; i < bigController[0].length; i++) {
			bigController[0][i] = new Sprite(0, 0, 232, 100*i+10, "large_controller.png", 38, 32);
			bigController[1][i] = new Sprite(0, 0, 136, 100*i+10, "large_controller.png", 38, 26);
		}
		for (int i = 0; i < smallController[0].length; i++) {
			smallController[0][i] = new Sprite(-1, 0, 200, 100*i+10, "little_controller.png", 23, 13);
			smallController[1][i] = new Sprite(-1, 0, 178, 100*i+10, "little_controller.png", 23, 13);
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
