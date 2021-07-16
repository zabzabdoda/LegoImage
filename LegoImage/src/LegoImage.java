import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LegoImage {

	public static void main(String[] args) {
		try {
			//file size: 1064,774
			BufferedImage tylerImage = ImageIO.read(new File("r2d2.png"));
			//scaled down size 106,77
			Window w = new Window(tylerImage, tylerImage.getWidth(), tylerImage.getHeight(),20);
			w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			w.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
