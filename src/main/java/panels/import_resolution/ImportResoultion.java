package panels.import_resolution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import panels.common_components.HintTextField;
import panels.common_components.MenuBar;
import panels.common_components.RadioButtonGroup;
import panels.common_components.MenuBar.Report;

public class ImportResoultion extends JPanel {
	MenuBar menubar = new MenuBar(Report.IMPORT, this);
	
	String[] incomes = { "십일조", "감사헌금", "선교헌금", "건축헌금", "예금이자", "부활감사", "맥추감사", "성탄감사", "추수감사", "신년감사", "기타", "일시차입금", "적립금인출", "차입금", "전년도이월금" };
	String[] names = { "김효진", "김유진", "김하영", "오종순", "김현섭" };
	final String header[] = { "종류", "이름 / 내용", "금액" };
	String contents[][] = { { "주일헌금", "전체", "0" } };
	
	Dimension size = new Dimension(400, 680);
	Dimension importKindSize = new Dimension(500, 100);
	JComboBox<String> nameBox;
	DefaultTableModel model;
	JTable table;
	JTextField kind;
	JTextField year;
	JTextField month;
	JTextField date;
	
	public ImportResoultion(JFrame frame) {
		JPanel datePanel = createDateSection();
		JPanel importBtnGroup = createKindSection();
		JScrollPane tablePanel = createTableSection();
		JPanel inputPanel = createInputSection();
		
		setPreferredSize(size);
		add(datePanel, BorderLayout.NORTH);
		add(importBtnGroup, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
	}
	
	private JPanel createDateSection() {
		JLabel dateLabel = new JLabel("날짜 입력: ");
		year = new HintTextField("년", 5);
		month = new HintTextField("월", 4);
		date = new HintTextField("일", 4);
		
		JPanel datePanel = new JPanel();
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		datePanel.add(dateLabel);
		datePanel.add(year);
		datePanel.add(month);
		datePanel.add(date);
		
		return datePanel;
	}
	
	private JPanel createKindSection() {
		JPanel importBtnGroup = new RadioButtonGroup(incomes);
		importBtnGroup.setPreferredSize(importKindSize);
		for (JRadioButton rb : ((RadioButtonGroup) importBtnGroup).getButtons()) {
			rb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					kind.setText(rb.getText());
				}
			});
		}
		
		return importBtnGroup;
	}
	
	private JScrollPane createTableSection() {
		model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				if (c == 0) return false;
				return true;
			}
		};
		table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		return scrollpane;
	}
	
	private JPanel createInputSection() {
		nameBox = new JComboBox<String>(names);
		JTextField nameOrContent = new HintTextField("이름 / 내용", 11);
		JTextField price = new HintTextField("금액", 9);
		JButton addBtn = createAddButton(nameOrContent, price);
		JButton delBtn = createDeleteButton();
		kind = new JTextField("십일조", 7);
		kind.setEnabled(false);
		nameBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameBox.getSelectedItem().toString();
				nameOrContent.setForeground(Color.BLACK);
				nameOrContent.setText(name);
			}
		});
		
		JPanel inputPanel1 = new JPanel();
		inputPanel1.setLayout(new BoxLayout(inputPanel1, BoxLayout.X_AXIS));
		inputPanel1.add(kind);
		inputPanel1.add(nameBox);
		
		JPanel inputPanel2 = new JPanel();
		inputPanel2.setLayout(new BoxLayout(inputPanel2, BoxLayout.X_AXIS));
		inputPanel2.add(nameOrContent);
		inputPanel2.add(price);
		inputPanel2.add(addBtn);
		inputPanel2.add(delBtn);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 1, 0, 5));
		inputPanel.add(inputPanel1);
		inputPanel.add(inputPanel2);
		
		
		return inputPanel;
	}
	
	private JButton createAddButton(JTextField nameOrContent, JTextField price) {
		JButton addBtn = new JButton("추가");
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputData[] = new String[3];
				
				inputData[0] = kind.getText();
				inputData[1] = nameOrContent.getText();
				inputData[2] = price.getText();
				model.addRow(inputData);
				
				nameOrContent.setText("");
				nameOrContent.requestFocus();
				price.setText("");
				price.requestFocus();
				kind.requestFocus();
			}
		});
		
		return addBtn;
	}
	
	private JButton createDeleteButton() {
		JButton delBtn = new JButton("삭제");
		delBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1) return;
				model.removeRow(table.getSelectedRow());
			}
		});
		
		return delBtn;
	}
	
	public MenuBar getMenuBar() {
		return menubar;
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
	
	public String getDate() {
		return year.getText() + "년 " + month.getText() + "월 " + date.getText() + "일\n";
	}
}
