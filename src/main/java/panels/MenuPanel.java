package panels;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

import design.buttons.BigMenuButton;
import design.buttons.SmallMenuButton;
import design.panels.PanelPage;

public class MenuPanel extends PanelPage{
	public MenuPanel() {
		setLayout(new GridBagLayout());
		
		// 메뉴 버튼들 생성
		SmallMenuButton income = new SmallMenuButton("수입 결의서");
		SmallMenuButton expenditure = new SmallMenuButton("지출 결의서");
		BigMenuButton accounting = new BigMenuButton("회계 보고서 생성");
		BigMenuButton annualReport = new BigMenuButton("연간 보고서");
		
		// 수입/지출 결의서 버튼 Panel
		JPanel incomeExpenditurePanel = new JPanel();
		incomeExpenditurePanel.setLayout(new GridLayout(2, 1, 0, 20));
		incomeExpenditurePanel.setPreferredSize(new Dimension(200, 200));
		incomeExpenditurePanel.add(income);
		incomeExpenditurePanel.add(expenditure);
		
		// 전체 메뉴 버튼 Panel
		JPanel menuButtonsPanel = new JPanel();
		menuButtonsPanel.setLayout(new GridLayout(1, 3, 50, 0));
		menuButtonsPanel.setPreferredSize(new Dimension(700, 200));
		menuButtonsPanel.add(incomeExpenditurePanel);
		menuButtonsPanel.add(accounting);
		menuButtonsPanel.add(annualReport);
		
		add(menuButtonsPanel);
	}
}
