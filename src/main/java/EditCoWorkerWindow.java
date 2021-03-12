import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.util.Locale;

public class EditCoWorkerWindow extends JFrame {
    private JButton addButton;
    private JTextField IDTextField;
    private JButton deleteButton;
    private JTextField lastTextField;
    private JTextField nameTextField;
    private JTextField fatherTextField;
    private JRadioButton manRadioButton;
    private JRadioButton womanRadioButton;
    private JTextField dayTextField;
    private JTextField monthTextField;
    private JTextField yearTextField;
    private JTable table1;
    private DefaultTableModel tableModel;
    JPanel mainPanel;
    private JPanel buttonsPanel;
    private JPanel tablePanel;
    private JScrollPane scrollPanel;
    private JLabel labelgroup;
    private JTextField emailTextField;
    private SQLFunctions sqlFunctions = new SQLFunctions();

    public EditCoWorkerWindow() {

        tableModel = new DefaultTableModel() {

            private static final long serialVersionUID = 1L;
            String[] names = {"ID", "Імя", "Прізвище", "По-батькові", "Стать", "День", "Місяць", "Рік", "Email"};

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public String getColumnName(int index) {
                return names[index];
            }
        };
        table1.setModel(tableModel);
        sqlFunctions.selectAllPeople("people", tableModel);

        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                JTable target = (JTable) e.getSource();
                int row = target.getSelectedRow();
                IDTextField.setText(String.valueOf(table1.getModel().getValueAt(row, 0)));

            }
        });

        dayTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                dayTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        yearTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                yearTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        monthTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                monthTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        nameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                nameTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        lastTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

                lastTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        fatherTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                fatherTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        IDTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                IDTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        emailTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                emailTextField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


        manRadioButton.setSelected(true);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sqlFunctions.delete("people", IDTextField.getText());
                updateTable(tableModel);
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                sqlFunctions.addPeople("people",
                        nameTextField.getText(),
                        lastTextField.getText(),
                        fatherTextField.getText(),
                        (manRadioButton.isSelected() ? "M" : "W"),
                        Integer.parseInt(dayTextField.getText()),
                        Integer.parseInt(monthTextField.getText()),
                        Integer.parseInt(yearTextField.getText()),
                        emailTextField.getText()
                );
                updateTable(tableModel);
            }
        });
    }

    private void updateTable(DefaultTableModel model) {
        model.setRowCount(0);
        sqlFunctions.selectAllPeople("people", model);
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(15, 15, 15, 15), -1, -1));
        mainPanel.setBackground(new Color(-15566010));
        mainPanel.putClientProperty("html.disable", Boolean.FALSE);
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 4, new Insets(10, 5, 10, 5), -1, -1));
        buttonsPanel.setBackground(new Color(-16739451));
        buttonsPanel.setEnabled(true);
        mainPanel.add(buttonsPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setBackground(new Color(-16740093));
        addButton.setHorizontalAlignment(0);
        addButton.setText("Додати");
        buttonsPanel.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setBackground(new Color(-4390902));
        deleteButton.setForeground(new Color(-16777216));
        deleteButton.setText("Видалити");
        buttonsPanel.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastTextField = new JTextField();
        lastTextField.setHorizontalAlignment(0);
        lastTextField.setText("Прізвище:");
        buttonsPanel.add(lastTextField, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameTextField = new JTextField();
        nameTextField.setHorizontalAlignment(0);
        nameTextField.setText("Імя:");
        buttonsPanel.add(nameTextField, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fatherTextField = new JTextField();
        fatherTextField.setHorizontalAlignment(0);
        fatherTextField.setText("По батькові:");
        buttonsPanel.add(fatherTextField, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        manRadioButton = new JRadioButton();
        manRadioButton.setBackground(new Color(-16739451));
        manRadioButton.setEnabled(true);
        manRadioButton.setForeground(new Color(-16777216));
        manRadioButton.setHorizontalAlignment(0);
        manRadioButton.setText("чоловіча");
        buttonsPanel.add(manRadioButton, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(167, 20), null, 0, false));
        dayTextField = new JTextField();
        dayTextField.setHorizontalAlignment(0);
        dayTextField.setText("день");
        buttonsPanel.add(dayTextField, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(86, 30), null, 0, false));
        monthTextField = new JTextField();
        monthTextField.setHorizontalAlignment(0);
        monthTextField.setText("місяць");
        buttonsPanel.add(monthTextField, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(86, 30), null, 0, false));
        yearTextField = new JTextField();
        yearTextField.setHorizontalAlignment(0);
        yearTextField.setText("рік");
        buttonsPanel.add(yearTextField, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(86, 30), null, 0, false));
        womanRadioButton = new JRadioButton();
        womanRadioButton.setBackground(new Color(-16739451));
        womanRadioButton.setEnabled(true);
        womanRadioButton.setForeground(new Color(-16777216));
        womanRadioButton.setHorizontalAlignment(0);
        womanRadioButton.setText("жіноча");
        buttonsPanel.add(womanRadioButton, new com.intellij.uiDesigner.core.GridConstraints(6, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(153, 20), null, 0, false));
        IDTextField = new JTextField();
        IDTextField.setText("ID:");
        buttonsPanel.add(IDTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, 30), null, 0, false));
        emailTextField = new JTextField();
        emailTextField.setHorizontalAlignment(0);
        emailTextField.setText("Email");
        buttonsPanel.add(emailTextField, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        labelgroup = new JLabel();
        labelgroup.setBackground(new Color(-1709330));
        Font labelgroupFont = this.$$$getFont$$$("Arial Rounded MT Bold", -1, 18, labelgroup.getFont());
        if (labelgroupFont != null) labelgroup.setFont(labelgroupFont);
        labelgroup.setForeground(new Color(-16777216));
        labelgroup.setHorizontalAlignment(0);
        labelgroup.setText("СТАТЬ");
        buttonsPanel.add(labelgroup, new com.intellij.uiDesigner.core.GridConstraints(6, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(86, 16), null, 0, false));
        tablePanel = new JPanel();
        tablePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tablePanel.setBackground(new Color(-16739451));
        mainPanel.add(tablePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollPanel = new JScrollPane();
        scrollPanel.setBackground(new Color(-8413810));
        tablePanel.add(scrollPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        table1.setBackground(new Color(-15165374));
        Font table1Font = this.$$$getFont$$$(null, -1, 12, table1.getFont());
        if (table1Font != null) table1.setFont(table1Font);
        table1.setForeground(new Color(-16777216));
        table1.setGridColor(new Color(-16777216));
        scrollPanel.setViewportView(table1);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(manRadioButton);
        buttonGroup.add(womanRadioButton);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }

}
