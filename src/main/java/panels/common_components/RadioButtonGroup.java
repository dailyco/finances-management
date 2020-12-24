package panels.common_components;

import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonGroup extends JPanel {
	JRadioButton[] buttons;
	
	public RadioButtonGroup(String[] texts) {
		buttons = new JRadioButton[texts.length];
		ButtonGroup group = new ButtonGroup();
		
		setLayout(new FlowLayout());
		
		for (int i = 0; i < texts.length; i++) {
			JRadioButton radioButton;
			if (i == 0) radioButton = new JRadioButton(texts[i], true);
			else radioButton = new JRadioButton(texts[i]);
			
			buttons[i] = radioButton;
			group.add(buttons[i]);
			add(buttons[i]);
		}
	}
	
	public JRadioButton[] getButtons() {
		return buttons;
	}
}
