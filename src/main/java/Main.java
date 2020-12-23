import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import panels.IncomePanel;
import panels.MenuPanel;

public class Main extends JFrame{
	MenuPanel menuPage = new MenuPanel();
	IncomePanel incomePage = new IncomePanel(this);
	
	Main() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w = (int) screenSize.getWidth();
		int h = (int) screenSize.getHeight();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("기쁨의 교회 재정 프로그램");
		setSize(w, h);
		
//		add(menuPage);
		add(incomePage);
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();
	}

}
