package panels.common_components;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panels.Menu;

public class CheckDialog extends JDialog {
	JButton confirm = new JButton("확인");
	JButton cancel = new JButton("취소");
	JLabel tempMessage = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀", JLabel.CENTER);
	JLabel message1 = new JLabel("홈으로 이동하시겠습니까?\n", JLabel.CENTER);
	JLabel message2 = new JLabel("지금까지 입력한 정보가 모두 사라집니다.", JLabel.CENTER);
	
	CheckDialog(JFrame frame, String title) {
		super(frame, title, true);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(confirm);
		buttonPanel.add(cancel);
        
        JPanel panel = new JPanel();
        panel.add(tempMessage, BorderLayout.CENTER);
        panel.add(message1, BorderLayout.CENTER);
        panel.add(message2, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(panel);
		setSize(350, 150);
		setLocationRelativeTo(frame);
	}
}
