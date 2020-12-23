package design.panels;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PanelPage extends JPanel {
	int w;
	int h;
	
	public PanelPage() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		w = (int) screenSize.getWidth();
		h = (int) screenSize.getHeight();
		
		setPreferredSize(new Dimension(w, h));
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
}
