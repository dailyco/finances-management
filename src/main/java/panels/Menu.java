package panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import panels.common_components.ButtonGroup;
import panels.font.TitleFont;

public class Menu extends JPanel {
//	String[] buttons = { "수입 결의서", "지출 결의서", "회계 보고서", "연간 보고서" };
	String[] buttons = { "수입 결의서", "지출 결의서" };
	Dimension size = new Dimension(400, 690);
	Dimension buttonGroupSize = new Dimension(300, 600);
	
	ButtonGroup menuBtns;
	
	public Menu(JFrame frame, ImportResoultion importPage, ExpenditureResolution expenditurePage) {
		menuBtns = new ButtonGroup(buttons);
		menuBtns.setPreferredSize(buttonGroupSize);
		Component menu = this;

		TitleFont titleFont = new TitleFont();
		menuBtns.getBtns()[0].setFont(titleFont.getTitle());
		menuBtns.getBtns()[1].setFont(titleFont.getTitle());
		
		menuBtns.getBtns()[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.remove(menu);
				frame.add(importPage);
				frame.setJMenuBar(importPage.getMenuBar());
				frame.revalidate();
				frame.repaint();
			}
		});
		menuBtns.getBtns()[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.remove(menu);
				frame.add(expenditurePage);
				frame.setJMenuBar(expenditurePage.getMenuBar());
				frame.revalidate();
				frame.repaint();
			}
		});
		
		setPreferredSize(size);
		add(menuBtns, BorderLayout.CENTER);
	}
}
