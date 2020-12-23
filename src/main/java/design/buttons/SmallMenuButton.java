package design.buttons;

//import java.awt.Color;
import javax.swing.JButton;

public class SmallMenuButton extends JButton {
	public SmallMenuButton (String text) {
		super(text);
		setSize(200, 90);
		setFocusPainted(false);
//		setBackground(new Color());
	}
}
