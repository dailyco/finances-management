package panels.menu_home;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;

import panels.common_components.ButtonGroup;

public class Menu extends JPanel {
	String[] buttons = { "수입 결의서", "지출 결의서", "회계 보고서", "연간 보고서" };
	Dimension size = new Dimension(400, 600);
	Dimension buttonGroupSize = new Dimension(300, 500);
	
	public Menu() {
		ButtonGroup menuBtns = new ButtonGroup(buttons);
		menuBtns.setPreferredSize(buttonGroupSize);
		
		setPreferredSize(size);
		add(menuBtns, BorderLayout.CENTER);
	}
}
