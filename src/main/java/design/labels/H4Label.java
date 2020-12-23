package design.labels;

import java.awt.Font;

import javax.swing.JLabel;

public class H4Label extends JLabel {
	int fontSize = 15;
	
	public H4Label(String text) {
		super(text);
		setFont(new Font(getFont().getName(), Font.PLAIN, fontSize));
	}
}
