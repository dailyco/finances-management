package panels.menu_home;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

import panels.common_components.ButtonGroup;

public class Menu extends JPanel {
//	String[] buttons = { "수입 결의서", "지출 결의서", "회계 보고서", "연간 보고서" };
	String[] buttons = { "수입 결의서", "지출 결의서" };
	Dimension size = new Dimension(400, 690);
	Dimension buttonGroupSize = new Dimension(300, 600);
	
	ButtonGroup menuBtns;
	
	public Menu() {
		menuBtns = new ButtonGroup(buttons);
		menuBtns.setPreferredSize(buttonGroupSize);
		
		setPreferredSize(size);
		add(menuBtns, BorderLayout.CENTER);
	}
	
	public JButton getImportBtn() {
		return menuBtns.getBtns()[0];
	}
	
	public JButton getExpenditureBtn() {
		return menuBtns.getBtns()[1];
	}

	public JButton getImExBtn() {
		return menuBtns.getBtns()[2];
	}
	
	public JButton getYearBtn() {
		return menuBtns.getBtns()[3];
	}
}
