import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class StaticSprite{
	private Image img; 	
	private AffineTransform tx;
	
	int dir = 0; 					//0-forward, 1-backward, 2-left, 3-right
	int width, height;
	int x, y;						//movement variables
	double scaleWidth = 1.0;		//change to scale image
	double scaleHeight = 1.0; 		//change to scale image

	public StaticSprite(String path) {
		img = getImage(path); //load the image

		//alter these
		width = 100;
		height = 100;
		x = -width;
		y = 600;
		
		tx = AffineTransform.getTranslateInstance(0, 0);
		
		init(x, y);
		
	}

	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;	
		
		init(x,y);
		g2.drawImage(img, tx, null);
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(scaleWidth, scaleHeight);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = StaticSprite.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
