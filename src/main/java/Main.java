import java.awt.Dimension;

import javax.swing.JFrame;

import panels.import_resolution.ImportResoultion;
import panels.menu_home.Menu;

public class Main extends JFrame{
	Menu menuPage = new Menu();
	ImportResoultion importPage  = new ImportResoultion();
	
	Dimension size = new Dimension(500, 600);
	
	Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("기쁨의 교회 재정 프로그램");
		setPreferredSize(size);
		
		add(menuPage);
//		add(importPage);
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

}
