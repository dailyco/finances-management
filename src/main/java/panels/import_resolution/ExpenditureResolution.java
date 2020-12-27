package panels.import_resolution;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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

public class ExpenditureResolution extends JPanel {
	MenuBar menubar = new MenuBar(Report.EXPENDITURE, this);
	
	String[] expenditures = { "관리", "교육", "봉사", "예배", "선교", "자본관리", "기타" };
	String[][] detailExpenditures = { { "공과금", "도서 인쇄비", "보험료", "비품", "사무비", "상회비", "수도 광열비", "시설관리 유지비", "정원 관리비", "연료비", "잡비", "중식비", "카페 재료비", "지급이자", "차량 유지비", "통신비", "회의비" }, { "교육 활동비", "교육 훈련비", "도서비", "부서협의회비", "장학금" }, { "경조비", "사회봉사비", "접대비", "행사비" }, { "강사료", "목회자 생활비", "목회 활동비", "상여금", "찬양대비", "연금 및 의보" }, { "국내 선교비", "국외 선교비", "새신자 교육비", "심방비" }, { "건물 임차료", "건축", "제적립 예금", "차량", "원금상환", "토지" }, { "예비비" } };
	final String header[] = { "분류", "항목", "내용", "금액" };
	String tableContents[][];
	
	HashMap<String, String[]> expenditureComboBox = new HashMap<String, String[]>() {
		{
			for (int i = 0; i < expenditures.length; i++)
				put(expenditures[i], detailExpenditures[i]);
		}
	};
	DefaultComboBoxModel comboBoxModel;
	JComboBox<String> detailKind;
	DefaultTableModel model;
	JTable table;
	JTextField kind;
	
	JTextField year;
	JTextField month;
	JTextField date;
	
	Dimension size = new Dimension(400, 630);
	Dimension dateSize = new Dimension(400, 35);
	Dimension expenditureKindSize = new Dimension(500, 50);
	Dimension inputSize = new Dimension(400, 80);
	
	public ExpenditureResolution(JFrame frame) {
		
		JPanel datePanel = createDateSection();
		JPanel expenditureBtnGroup = createKindSection();
		JScrollPane tablePanel = createTableSection();
		JPanel inputPanel = createInputSection();	
		
		setPreferredSize(size);
		add(datePanel, BorderLayout.NORTH);
		add(expenditureBtnGroup, BorderLayout.NORTH);
		add(tablePanel, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
	}
	
	private JPanel createDateSection() {
		JLabel dateLabel = new JLabel("날짜 입력: ");
		year = new HintTextField("년", 5);
		month = new HintTextField("월", 4);
		date = new HintTextField("일", 4);
		
		JPanel datePanel = new JPanel();
		datePanel.setPreferredSize(dateSize);
		datePanel.setLayout(new BoxLayout(datePanel, BoxLayout.X_AXIS));
		datePanel.add(dateLabel);
		datePanel.add(year);
		datePanel.add(month);
		datePanel.add(date);
		
		return datePanel;
	}
	
	private JPanel createKindSection() {	
		JPanel expenditureBtnGroup = new RadioButtonGroup(expenditures);
		expenditureBtnGroup.setPreferredSize(expenditureKindSize);
		for (JRadioButton rb : ((RadioButtonGroup) expenditureBtnGroup).getButtons()) {
			rb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					kind.setText(rb.getText());
					comboBoxModel = new DefaultComboBoxModel(expenditureComboBox.get(rb.getText()));
					detailKind.setModel(comboBoxModel);
				}
			});
		}
		
		return expenditureBtnGroup;
	}
	
	private JScrollPane createTableSection() {
		model = new DefaultTableModel(tableContents, header) {
			public boolean isCellEditable(int i, int c) {
				if (c == 0 ||c == 1) return false;
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
		comboBoxModel = new DefaultComboBoxModel(expenditureComboBox.get("관리"));
		kind = new JTextField("관리");
		detailKind = new JComboBox(comboBoxModel);
		JTextField content = new HintTextField("내용", 11);
		JTextField price = new HintTextField("금액", 7);
		JButton addBtn = createAddButton(content, price);
		JButton delBtn = createDeleteButton();
		kind.setEnabled(false);
		detailKind.setPreferredSize(new Dimension(150, 10));
		
		JPanel inputPanel1 = new JPanel();
		inputPanel1.setLayout(new BoxLayout(inputPanel1, BoxLayout.X_AXIS));
		inputPanel1.add(kind);
		inputPanel1.add(detailKind);
		
		JPanel inputPanel2 = new JPanel();
		inputPanel2.setLayout(new BoxLayout(inputPanel2, BoxLayout.X_AXIS));
		inputPanel2.add(content);
		inputPanel2.add(price);
		inputPanel2.add(addBtn);
		inputPanel2.add(delBtn);
		
		JPanel inputPanel = new JPanel(new GridLayout(2, 1, 0, 5));
		inputPanel.setPreferredSize(inputSize);
		inputPanel.add(inputPanel1);
		inputPanel.add(inputPanel2);
		
		return inputPanel;
	}
	
	private JButton createAddButton(JTextField content, JTextField price) {
		JButton addBtn = new JButton("추가");
		
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String inputData[] = new String[4];
				
				inputData[0] = kind.getText();
				inputData[1] = detailKind.getSelectedItem().toString();
				inputData[2] = content.getText();
				inputData[3] = price.getText();
				model.addRow(inputData);
				
				content.setText("");
				content.requestFocus();
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
