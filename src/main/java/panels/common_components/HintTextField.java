package panels.common_components;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class HintTextField extends JTextField {
	public HintTextField(final String hint, int size) {
		super(hint, size);
		setForeground(Color.LIGHT_GRAY);
		
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(hint)) {
					setText("");
					setForeground(Color.BLACK);
//					setFont(normal);
				}
			}
			
			@Override
			public void focusLost(FocusEvent e) {
				if (getText().equals(hint) || getText().length() == 0) {
					setText(hint);
					setForeground(Color.LIGHT_GRAY);
				}
				else
					setForeground(Color.BLACK);
			}
		});
	}
}