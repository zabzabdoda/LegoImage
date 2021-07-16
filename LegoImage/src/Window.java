import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

public class Window extends JFrame {

	//                                      1064        774         10
	public Window(BufferedImage image, int width, int height, int scale) {
		getContentPane().setLayout(null);
		int scaledWidth = width/scale, scaledHeight = height/scale;
		//Scales image down to 106,77
		Image bi = image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
		//b is size 106,77
		BufferedImage b = new BufferedImage(bi.getWidth(null), bi.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D bGr = b.createGraphics();
		bGr.drawImage(bi, 0, 0, null);
		bGr.dispose();
		//window size: 1064 744
		this.setSize(width+200, height+100);
		//array size 106 774
		Can canvas = new Can(b, scaledWidth, scaledHeight);
		//canvas size: 1064 774
		canvas.setBounds(0, 0, width, height);
		getContentPane().add(canvas);
		TreeMap<String,Integer> pieces = canvas.getColors();
		DefaultListModel<String> demoList = new DefaultListModel<String>();
		Map<String, Integer> m = MapUtil.sortByValue(pieces);
		for(String lc : m.keySet()) {
			demoList.addElement(lc+": "+m.get(lc));
		}
		JList<String> list = new JList<String>(demoList);
		list.setBounds(0, 0, 175, 200);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBounds(width+5, 100, 175, 200);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		JLabel label = new JLabel("Total Pieces: "+canvas.getLength()+"");
		label.setBounds(width+10,50,150,75);
		getContentPane().add(label);
		getContentPane().add(listScroller);
	}
}
