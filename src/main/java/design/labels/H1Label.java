package design.labels;

import java.awt.Font;

import javax.swing.JLabel;

public class H1Label extends JLabel {
	int fontSize = 30;
	
	public H1Label(String text) {
		super(text);
		setFont(new Font(getFont().getName(), Font.PLAIN, fontSize));
	}
}
