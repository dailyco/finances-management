import java.awt.Dimension;

import javax.swing.JFrame;

import panels.ExpenditureResolution;
import panels.ImportResoultion;
import panels.Menu;

public class Main extends JFrame {
	ImportResoultion importPage  = new ImportResoultion(this);
	ExpenditureResolution expenditurePage = new ExpenditureResolution(this);
	Menu menuPage = new Menu(this, importPage, expenditurePage);
	
	
	Dimension size = new Dimension(500, 690);
	
	Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("기쁨의 교회 재정 프로그램");
		setPreferredSize(size);
		
		add(menuPage);

		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}
}
