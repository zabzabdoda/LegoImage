
public class LegoColor {

	private int r, g, b;
	private String name;
	private int id;

	public LegoColor(int r, int g, int b, String name, int id) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.name = name;
		this.id = id;
	}

	public int getRGB() {
		return 65536 * r + 256 * g + b;
	}

	public int getRed() {
		return r;
	}

	public int getGreen() {
		return g;
	}

	public int getBlue() {
		return b;
	}

	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}

}
