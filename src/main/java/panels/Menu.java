package panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import panels.common_components.ButtonGroup;

public class Menu extends JPanel {
//	String[] buttons = { "수입 결의서", "지출 결의서", "회계 보고서", "연간 보고서" };
	String[] buttons = { "수입 결의서", "지출 결의서" };
	
	Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();	
	int w = (int)dimen.getWidth()/2;
	int h = (int)dimen.getHeight();

	Dimension size = new Dimension(w, h);
	Dimension buttonGroupSize = new Dimension(w/2, h-h/7);
	
	ButtonGroup menuBtns;
	
	public Menu(JFrame frame, ImportResoultion importPage, ExpenditureResolution expenditurePage) {
		menuBtns = new ButtonGroup(buttons);
		menuBtns.setPreferredSize(buttonGroupSize);
		Component menu = this;
		
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
