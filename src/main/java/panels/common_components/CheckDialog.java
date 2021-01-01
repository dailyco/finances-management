package panels.common_components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import panels.menu_home.Menu;

public class CheckDialog extends JDialog {
	JButton confirm = new JButton("확인");
	JButton cancel = new JButton("취소");
	JLabel tempMessage = new JLabel("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀", JLabel.CENTER);
	JLabel message1 = new JLabel("정말로 홈으로 돌아가시겠습니까?", JLabel.CENTER);
	JLabel message2 = new JLabel("지금까지 입력한 정보들이 모두 날아갑니다.", JLabel.CENTER);
	
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
