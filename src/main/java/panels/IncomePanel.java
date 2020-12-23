package panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import design.labels.H1Label;
import design.labels.H4Label;
import design.panels.PanelPage;
import dialogs.DateSettingDialog;
import dialogs.IncomeCreateDialog;

public class IncomePanel extends PanelPage {
	JFrame frame;
	int year = 0;
	int month = 0;
	int date = 0;
	
	public IncomePanel(JFrame frame) {
		this.frame = frame;
		setLayout(new GridLayout(1, 2));
		
		// 
		H1Label title = createTitle();
		H1Label completedDate = createCompletedDate();
		
		// 수입 결의서 목록 Panel
		JPanel incomeListPanel = new JPanel();
		incomeListPanel.setPreferredSize(new Dimension(getWidth()/2, getHeight()));
		incomeListPanel.add(completedDate);
		incomeListPanel.add(title);
		
		// 
		JButton dateButton = createDateButton();
		JButton incomeItemButton = createIncomeItemButton();
		
		// 수입 결의서 CUD Panel
		JPanel incomCUDPanel = new JPanel();
		incomCUDPanel.setPreferredSize(new Dimension(getWidth()/2, getHeight()));
		incomCUDPanel.setBackground(Color.LIGHT_GRAY);
		incomCUDPanel.add(dateButton);
		incomCUDPanel.add(incomeItemButton);
		
		add(incomeListPanel);
		add(incomCUDPanel);
	}
	
	private H1Label createTitle() {
		H1Label title = new H1Label("수입 결의서");
		return title;
	}
	
	private H1Label createCompletedDate() {
		H1Label completedDate = new H1Label(year + "년 " + month + "월 " + date + "일");
		completedDate.setHorizontalAlignment(JLabel.RIGHT);
		return completedDate;
	}
	
	private JButton createDateButton() {
		JButton dateCreateButton = new JButton("날짜 지정하기");
		dateCreateButton.setFocusPainted(false);
		
		dateCreateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DateSettingDialog(frame);
			}
	    });
		
		return dateCreateButton;
	}
	
	private JButton createIncomeItemButton() {
		JButton incomeItemCreateButton = new JButton("수입 입력하기");
		incomeItemCreateButton.setFocusPainted(false);
		
		incomeItemCreateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new IncomeCreateDialog(frame);
			}
	    });
		
		return incomeItemCreateButton;
	}
}
