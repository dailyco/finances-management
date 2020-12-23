package dialogs;

import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import components.RadioButtonGroup;
import components.HintTextField;

public class IncomeCreateDialog extends JDialog {
	String[] incomes = { "주일헌금", "십일조", "감사헌금", "선교헌금", "건축헌금", "예금이자", "부활감사", "맥추감사", "성탄감사", "추수감사", "신년감사", "기타", "일시차입금", "적립금인출", "차입금", "전년도이월금" };
	String[] names = { "강재구", "김병선", "김정현", "남안숙", "박현종", "백솔", "백수근", "백진엽", "이승아", "임영순", "진수현", "황충원" };
	
	JFrame frame;
	int w = 400;
	int h = 500;
	boolean nameOpen = false;
	
	public IncomeCreateDialog(JFrame frame) {
		this.frame = frame;
		setSize(w, h);
		setTitle("수입 입력");
		setLocationRelativeTo(frame);
		
		// 
		RadioButtonGroup incomeList = new RadioButtonGroup(incomes);
		JPanel body = createMainBody();
		
		incomeList.setPreferredSize(new Dimension(w, 150));
		
		add(incomeList, BorderLayout.NORTH);
		add(body);
		
		setVisible(true);
	}
	
	private JTextField createTextField(String hintMessage) {
		JTextField content = new HintTextField(hintMessage);
		return content;
	}
	
	private JButton createNameListButton() {
		JButton nameListButton = new JButton("이름 선택");
		
		nameListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!nameOpen) {
					nameOpen = true;
					RadioButtonGroup nameList = new RadioButtonGroup(names);
					nameList.setPreferredSize(new Dimension(w, 150));
					add(nameList);
					setVisible(true);
				}
			}
	    });
		
		return nameListButton;
	}
	
	private JButton createSumitButton() {
		JButton submitButton = new JButton("입력");
		return submitButton;
	}
	
	private JPanel createMainBody() {
		// 
		JTextField content = createTextField("이름 / 내용");
		JTextField price = createTextField("금액");
		JButton nameList = createNameListButton();
		JButton submit = createSumitButton();
		
		// 
		JPanel mainBody = new JPanel();
		mainBody.add(content);
		mainBody.add(nameList);
		mainBody.add(price);
		mainBody.add(submit);
		
		return mainBody;
	}
}
