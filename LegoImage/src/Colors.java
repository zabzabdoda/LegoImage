import java.awt.Color;
import java.awt.image.DataBuffer;
import java.awt.image.IndexColorModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Colors {

	private ArrayList<LegoColor> colors;
	private IndexColorModel colorModel;

	public Colors() {
		colors = readFile();
		colorModel = createColorModel(colors);
	}

	public LegoColor getClosest(int rgb) {
		LegoColor c = nearestColor(new Color(rgb));
		return c;
	}

	private static IndexColorModel createColorModel(ArrayList<LegoColor> colors2) {
		final int[] cmap = new int[colors2.size()];
		for (int i = 0; i < colors2.size(); i++) {
			cmap[i] = colors2.get(i).getRGB();
		}
		final int bits = (int) Math.ceil(Math.log(cmap.length) / Math.log(2));
		return new IndexColorModel(bits, cmap.length, cmap, 0, false, -1, DataBuffer.TYPE_BYTE);
	}

	public LegoColor nearestColor(Color color) {
		final byte index = ((byte[]) colorModel.getDataElements(color.getRGB(), null))[0];
		return colors.get(index);
	}

	private ArrayList<LegoColor> readFile() {
		ArrayList<LegoColor> records = new ArrayList<LegoColor>();
		try (BufferedReader br = new BufferedReader(new FileReader("colorsBuyable.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				records.add(new LegoColor(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), values[3],Integer.parseInt(values[4])));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;
	}
}
