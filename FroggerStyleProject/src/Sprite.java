import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;
import java.awt.Color;
import java.awt.Rectangle;

public class Sprite{
	private Image img; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//position of the object
	int vx, vy;						//movement variables
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image

	// public Sprite() {
	// 	img 	= getImage("/imgs/"+"forwardFile.png"); //load the image for Tree

	// 	//alter these
	// 	width = 0;
	// 	height = 0;
	// 	x = 0;
	// 	y = 0;
	// 	vx = 0;
	// 	vy = 0;
		
	// 	tx = AffineTransform.getTranslateInstance(0, 0);
		
	// 	init(x, y); 				//initialize the location of the image
	// 								//use your variables
		
	// }

	public Sprite(int v_x, int v_y, int y_loc, int x_loc, String img_filename, int w, int h) {
		img     = getImage("/imgs/"+img_filename); //load the image for Tree
		
		width = w;
		height = h;
		x = x_loc;
		y = y_loc;
		vx = v_x;
		vy = v_y;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y);
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		
		x+=vx;
		y+=vy;	
		
		init(x,y);
		
		g2.drawImage(img, tx, null);

		if (Frame.debugging) {
			//draw hitbox only if debugging
			g.setColor(Color.green);
			g.drawRect(x, y, width, height);
		}
		
	}
	public Rectangle getHitbox() {
		return new Rectangle(x, y, width, height);
	}

	public boolean collisionsWith(Sprite other) {
		return getHitbox().intersects(other.getHitbox());
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Sprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}
	public int getX() {return x;}
	public int getY() {return y;}
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}

	public int getVX() {return this.vx;}
	public int getVY() {return this.vy;}	
	public void setVX(int vx) {this.vx = vx;}
	public void setVY(int vy) {this.vy = vy;}

}
