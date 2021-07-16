import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.TreeMap;

public class Can extends Canvas {

	private int[][] pixels;
	public static final int PIXEL_SIZE = 9;
	private Colors colors;

	public Can(BufferedImage image, int width, int height) {
		pixels = new int[width][height];

		colors = new Colors();
		for (int i = 0; i < pixels[0].length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				pixels[j][i] = colors.getClosest(image.getRGB(j, i)).getRGB();
			}
		}
	}

	public int[][] renderedPixels() {
		return pixels;
	}

	public void paint(Graphics g) {
		super.paint(g);
		this.setBackground(Color.BLACK);
		for (int i = 0; i < pixels[0].length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				renderShape(g, j, i, pixels[j][i]);
			}
		}
	}

	private void renderShape(Graphics g, int x, int y, int rgb) {
		g.setColor(new Color(rgb));
		g.fillRect(x + (x * PIXEL_SIZE), y + (y * PIXEL_SIZE), PIXEL_SIZE+1, PIXEL_SIZE+1);
		g.setColor(Color.BLACK);
		g.drawRect(x + (x * PIXEL_SIZE), y + (y * PIXEL_SIZE), PIXEL_SIZE+1, PIXEL_SIZE+1);
	}
	
	public TreeMap<String,Integer> getColors(){
		TreeMap<String,Integer> pieces = new TreeMap<String,Integer>();
		for (int i = 0; i < pixels[0].length; i++) {
			for (int j = 0; j < pixels.length; j++) {
				try {
					pieces.put(colors.getClosest(pixels[j][i]).getName(), pieces.get(colors.getClosest(pixels[j][i]).getName())+1);
				}catch(NullPointerException ex) {
					pieces.put(colors.getClosest(pixels[j][i]).getName(), 1);
				}
			}
		}
		return pieces;
	}

	public int getLength() {
		return pixels[0].length * pixels.length;
	}
}

