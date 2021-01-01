package panels.common_components;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonGroup extends JPanel {
	Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();	
	int w = (int)dimen.getWidth()/2;
	int h = (int)dimen.getHeight();
	
	Dimension buttonSize = new Dimension(w/2, h/7);
	JButton[] buttons;
	
	public ButtonGroup(String[] texts) {
		setLayout(new GridBagLayout());
		
		buttons = new JButton[texts.length];
		GridBagConstraints[] constraints = new GridBagConstraints[texts.length];
		
		for (int i = 0; i < texts.length; i++) {
			buttons[i] = new JButton(texts[i]);
			buttons[i].setPreferredSize(buttonSize);
			buttons[i].setFocusPainted(false);
			
			constraints[i] = new GridBagConstraints();
			constraints[i].gridx = 0;
			constraints[i].gridy = i;
			constraints[i].insets = new Insets(20, 0, 0, 0);
			
			add(buttons[i], constraints[i]);
		}
	}
	
	public JButton[] getBtns() {
		return buttons;
	}
}
