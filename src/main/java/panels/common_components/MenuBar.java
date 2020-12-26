package panels.common_components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class MenuBar extends JMenuBar {
	JFileChooser fileChooser = new JFileChooser();
	JMenu home = new JMenu("홈");
	JMenu fileMenu = new JMenu("파일");
	JMenuItem save = new JMenuItem("저장");
	JMenuItem open = new JMenuItem("불러오기");
	
	public enum Report {
		IMPORT,
		EXPENDITURE
	}

	public MenuBar(Report kind) {
		home.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("클릭?"); //프린트
				// dialog 띄워주고 - 취소 or 홈으로 가기
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openFile(kind);
			}
		});
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveFile(kind);
			}
		});
		
		fileMenu.add(open);
		fileMenu.add(save);
		
		add(home);
		add(fileMenu);
	}
	
	void openFile(Report kind) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("excel file", "xlsx", "xls");
		fileChooser.setFileFilter(filter);

		int ret = fileChooser.showOpenDialog(null);
		if (ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

//		try {
			System.out.println(fileChooser.getSelectedFile()); // 프린트
			try {
				HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(fileChooser.getSelectedFile()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 여기서부터 가지고 온 파일 읽어들이기
			
//			imagePanel.inputimage = ImageIO.read(fileChooser.getSelectedFile());
//			imagePanel.width = imagePanel.inputimage.getWidth();
//			imagePanel.height = imagePanel.inputimage.getHeight();
//			imagePanel.repaint();
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// 파일 읽어들여서 데이터로 가공하기
	}
	
	void saveFile(Report kind) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("excel file" , "xlsx", "xls");
		fileChooser.setFileFilter(filter);
		
		int ret = fileChooser.showSaveDialog(null);
		if (ret != JFileChooser.APPROVE_OPTION){
			JOptionPane.showMessageDialog(null, "파일이 저장되지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		// 데이터 가공해서 파일로 만들어주기
//		try {
			
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}