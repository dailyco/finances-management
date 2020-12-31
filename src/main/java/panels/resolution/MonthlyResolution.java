package panels.resolution;

import panels.common_components.ButtonGroup;
import panels.common_components.MenuBar;

import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MonthlyResolution extends JPanel {
    MenuBar importMenubar = new MenuBar(MenuBar.Report.IMPORT, this);
    MenuBar expenditureMenubar = new MenuBar(MenuBar.Report.EXPENDITURE, this);

    String[] buttons = { "주간 결의서", "월간 보고서", "수입지출 보고서 생성" };
    Dimension size = new Dimension(400, 690);
    Dimension buttonGroupSize = new Dimension(300, 600);
    panels.common_components.ButtonGroup menuBtns;

//    DefaultTableModel model;

    public MonthlyResolution(JFrame frame) {

//        JScrollPane tablePanel = createTableSection();

        menuBtns = new ButtonGroup(buttons);
        menuBtns.setPreferredSize(buttonGroupSize);
        setPreferredSize(size);
        add(getImportBtn(), BorderLayout.WEST);
        add(getExpenditureBtn(), BorderLayout.EAST);
        add(getSubmitBtn(), BorderLayout.CENTER);
    }

//    private JScrollPane createTableSection() {
//        model = new DefaultTableModel(tableContents, header) {
//            public boolean isCellEditable(int i, int c) {
//                if (c == 0 ||c == 1) return false;
//                return true;
//            }
//        };
//        table = new JTable(model);
//        JScrollPane scrollpane = new JScrollPane(table);
//        table.getTableHeader().setReorderingAllowed(false);
//        table.getTableHeader().setResizingAllowed(false);
//
//        return scrollpane;
//    }

    public MenuBar getImportMenuBar() {
        return importMenubar;
    }

    public MenuBar getExpenditureMenubar() {
        return expenditureMenubar;
    }

    public JButton getImportBtn() {
        return menuBtns.getBtns()[0];
    }

    public JButton getExpenditureBtn() {
        return menuBtns.getBtns()[1];
    }

    public JButton getSubmitBtn() {
        return menuBtns.getBtns()[2];
    }
}
