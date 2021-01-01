package panels;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
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
import panels.font.TextFont;

public class ImportResoultion extends JPanel {
	MenuBar menubar;
	
	String[] incomes = { "십일조", "감사헌금", "선교헌금", "건축헌금", "예금이자", "부활감사", "맥추감사", "성탄감사", "추수감사", "신년감사", "기타", "일시차입금", "적립금인출", "차입금", "전년도이월금" };
	String[] names = { "강재구", "강상완", "강승현", "김명중", "김병선", "김상란", "김연심", "김옥경", "김은희", "김정현", "김진배", "김현섭", "김현숙", 
			"남보라", "남보미", "남소연", "남안숙", "남정우", "노정식", "라미경", "문충선", "문현숙", "박경식", "박양숙", "박태섭", "백선미", 
			"백선영", "백수근", "서옥희", "설개심", "신경주", "신난희", "신희숙", "심성자", "오성희", "오종순", "오효경", "이민이", "이순자", 
			"이승수", "이은자", "이호동", "임승룡", "임영순", "장야위", "전향숙", "정민완", "정순하", "정윤자", "정재희", "정현숙", "정화일", 
			"조미애", "조현순", "조희용", "진수연", "한상진", "황금자", "황은주", "황충원", "김덕순", "김구분", "도옥순", "박정남", "소정순", 
			"이점악", "이철순", "유금순", "최은자", "강수민", "강혜민", "김상호", "김선대", "김솔아", "김수환", "김유진", "김주안", "김혜연", 
			"김효진", "남소진", "문세권", "문세영", "문수휴", "문진영", "박현종", "백솔", "백진송", "백진엽", "송서현", "송아현", "송지현", 
			"신성민", "신요원", "신채원", "임영환", "임정환", "정성근", "정성설", "한의진", "허성정", "허정", "주일학교", "청소년부", "디모데", 
			"남선교회", "목녀회", "마닐라", "다니엘", "벧엘", "태국", "인도", "베트남", "동해", "안디옥" };
	final String header[] = { "종류", "이름 / 내용", "금액" };
	String contents[][] = { { "주일헌금", "전체", "0" } };
	
	Dimension dimen = Toolkit.getDefaultToolkit().getScreenSize();
	int w = (int)dimen.getWidth()/2;
	int h = (int)dimen.getHeight();
	Dimension size = new Dimension(w, h);
	Dimension dateSize = new Dimension(w-w/10, h/16);
	Dimension importKindSize = new Dimension(w, (h/13)*2);
	Dimension tableSize = new Dimension(w-w/10, (h/13)*6);
//	Dimension inputSize = new Dimension(w-w/10, (h/15)*2);
	JComboBox<String> nameBox;
	DefaultTableModel model;
	JTable table;
	JTextField kind;
	JTextField year;
	JTextField month;
	JTextField date;

	Font textFont = new TextFont().getText();

	public ImportResoultion(JFrame frame) {
		menubar = new MenuBar(Report.IMPORT, frame, this);
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
		year = new HintTextField("년", 3);
		month = new HintTextField("월", 1);
		date = new HintTextField("일", 1);

		dateLabel.setFont(textFont);
		year.setFont(textFont);
		month.setFont(textFont);
		date.setFont(textFont);

		JPanel datePanel = new JPanel();
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		datePanel.add(dateLabel);
		datePanel.add(year);
		datePanel.add(month);
		datePanel.add(date);
		datePanel.setPreferredSize(dateSize);

		return datePanel;
	}
	
	private JPanel createKindSection() {
		JPanel importBtnGroup = new RadioButtonGroup(incomes);
		importBtnGroup.setPreferredSize(importKindSize);
		for (JRadioButton rb : ((RadioButtonGroup) importBtnGroup).getButtons()) {
			rb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { kind.setText(rb.getText()); }
			});
			rb.setFont(textFont);
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
		scrollpane.setPreferredSize(tableSize);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		return scrollpane;
	}
	
	private JPanel createInputSection() {
		nameBox = new JComboBox<String>(names);
		JTextField nameOrContent = new HintTextField("이름 / 내용", 11);
		JTextField price = new HintTextField("금액", 10);
		JButton addBtn = createAddButton(nameOrContent, price);
		JButton delBtn = createDeleteButton();
		kind = new JTextField("십일조");
		kind.setEnabled(false);
		nameBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameBox.getSelectedItem().toString();
				nameOrContent.setForeground(Color.BLACK);
				nameOrContent.setText(name);
			}
		});

		nameOrContent.setFont(textFont);
		price.setFont(textFont);
		addBtn.setFont(textFont);
		delBtn.setFont(textFont);

		JPanel inputPanel1 = new JPanel();
		inputPanel1.setLayout(new BoxLayout(inputPanel1, BoxLayout.X_AXIS));
		inputPanel1.setPreferredSize(dateSize);
		inputPanel1.add(kind);
		inputPanel1.add(nameBox);
		
		JPanel inputPanel2 = new JPanel();
		inputPanel2.setLayout(new BoxLayout(inputPanel2, BoxLayout.X_AXIS));
		inputPanel2.setPreferredSize(dateSize);
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
				if (table.getSelectedRow() == -1 || table.getSelectedRow() == 0) return;
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
