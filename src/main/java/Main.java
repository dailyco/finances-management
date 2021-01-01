import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import panels.ExpenditureResolution;
import panels.ImportResoultion;
import panels.Menu;
import panels.font.TitleFont;
// import panels.MonthlyResolution;

public class Main extends JFrame {
	ImportResoultion importPage  = new ImportResoultion(this);
	ExpenditureResolution expenditurePage = new ExpenditureResolution(this);
	Menu menuPage = new Menu(this, importPage, expenditurePage);
	// MonthlyResolution monthlyPage = new MonthlyResolution(this);
	
	Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();	
	int w = (int)dimen.getWidth()/2;
	int h = (int)dimen.getHeight();
	Dimension size = new Dimension(w, h);

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
