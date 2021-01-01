import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import panels.import_resolution.ExpenditureResolution;
import panels.import_resolution.ImportResoultion;
import panels.menu_home.Menu;

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
