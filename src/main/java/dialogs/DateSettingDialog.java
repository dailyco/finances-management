package dialogs;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import components.HintTextField;

public class DateSettingDialog extends JDialog {
	public DateSettingDialog(JFrame frame) {
		setSize(350, 150);
		setTitle("날짜 입력");
		setLocationRelativeTo(frame);
		
		JPanel body = createMainBody();
		add(body);
		
		setVisible(true);
	}
	
	private JTextField createTextField(String hintMessage) {
		JTextField content = new HintTextField(hintMessage);
		return content;
	}
	
	private JButton createSumitButton() {
		JButton submitButton = new JButton("입력");
		return submitButton;
	}
	
	private JPanel createMainBody() {
		// 
		JTextField year = createTextField("년");
		JTextField month = createTextField("월");
		JTextField date = createTextField("일");
		JLabel yearText = new JLabel("년");
		JLabel monthText = new JLabel("월");
		JLabel dateText = new JLabel("일");
		JButton submit = createSumitButton();
		
		//
		JPanel mainBody = new JPanel();
		mainBody.add(year);
		mainBody.add(yearText);
		mainBody.add(month);
		mainBody.add(monthText);
		mainBody.add(date);
		mainBody.add(dateText);
		mainBody.add(submit);
		
		return mainBody;
	}
}
