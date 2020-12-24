package panels.import_resolution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import panels.common_components.HintTextField;
import panels.common_components.RadioButtonGroup;

public class ImportResoultion extends JPanel {
	String[] incomes = { "주일헌금", "십일조", "감사헌금", "선교헌금", "건축헌금", "예금이자", "부활감사", "맥추감사", "성탄감사", "추수감사", "신년감사", "기타", "일시차입금", "적립금인출", "차입금", "전년도이월금" };
	final String header[] = { "종류", "이름 / 내용", "금액" };
	String contents[][];
	
	Dimension size = new Dimension(400, 600);
	Dimension importKindSize = new Dimension(500, 90);
	DefaultTableModel model;
	JTable table;
	JTextField kind;
	
	public ImportResoultion() {
		JPanel importBtnGroup = new RadioButtonGroup(incomes);
		model = new DefaultTableModel(contents, header) {
			public boolean isCellEditable(int i, int c) {
				if (c == 0) return false;
				return true;
			}
		};
		table = new JTable(model);
		JScrollPane scrollpane = new JScrollPane(table);
		JPanel inputPanel = createInputSection();
		
		for (JRadioButton rb : ((RadioButtonGroup) importBtnGroup).getButtons()) {
			rb.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					kind.setText(rb.getText());
				}
			});
		}
		
		importBtnGroup.setPreferredSize(importKindSize);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		setPreferredSize(size);
		add(importBtnGroup, BorderLayout.NORTH);
		add(scrollpane, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
	}
	
	private JPanel createInputSection() {
		JTextField nameOrContent = new HintTextField("이름 / 내용", 8);
		JTextField price = new HintTextField("금액", 5);
		JButton addBtn = createAddButton(nameOrContent, price);
		JButton delBtn = createDeleteButton();
		kind = new JTextField("주일 헌금", 7);
		kind.setEnabled(false);
		
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));
		inputPanel.add(kind);
		inputPanel.add(nameOrContent);
		inputPanel.add(price);
		inputPanel.add(addBtn);
		inputPanel.add(delBtn);
		
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
}
