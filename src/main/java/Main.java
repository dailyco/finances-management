import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import panels.resolution.ExpenditureResolution;
import panels.resolution.ImportResoultion;
import panels.menu_home.Menu;
import panels.resolution.MonthlyResolution;

public class Main extends JFrame implements ActionListener {
	Menu menuPage = new Menu();
	ImportResoultion importPage  = new ImportResoultion(this);
	ExpenditureResolution expenditurePage = new ExpenditureResolution(this);
	MonthlyResolution monthlyPage = new MonthlyResolution(this);
	
	Dimension size = new Dimension(500, 690);
	
	Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("기쁨의 교회 재정 프로그램");
		setPreferredSize(size);
		
		menuPage.getImportBtn().addActionListener(this);
		menuPage.getExpenditureBtn().addActionListener(this);
		menuPage.getImExBtn().addActionListener(this);
		menuPage.getYearBtn().addActionListener(this);
		
		add(menuPage);

		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();

		if (button == menuPage.getImportBtn()) {
			remove(menuPage);
			add(importPage);
			setJMenuBar(importPage.getMenuBar());
			revalidate();
			repaint();
		}

		if (button == menuPage.getExpenditureBtn()){
			remove(menuPage);
			add(expenditurePage);
			setJMenuBar(expenditurePage.getMenuBar());
			revalidate();
			repaint();
		}

		if (button == menuPage.getImExBtn()) {
			remove(menuPage);
			add(monthlyPage);
			setJMenuBar(monthlyPage.getImportMenuBar());
			revalidate();
			repaint();
		}

		if (button == menuPage.getYearBtn()) {
			remove(menuPage);
//			add(expenditurePage);
			revalidate();
			repaint();
		}
	}

}
