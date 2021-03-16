import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
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
    private JTable table1;
    private DefaultTableModel tableModel;
    JPanel mainPanel;
    private JPanel buttonsPanel;
    private JPanel tablePanel;
    private JScrollPane scrollPanel;
    private JLabel labelgroup;
    private JTextField emailTextField;
    private JSpinner spinner1;
    private JSpinner spinner2;
    private JSpinner spinner3;
    private SQLFunctions sqlFunctions = new SQLFunctions();

    private Date date = new Date();
    private DateFormat day = new SimpleDateFormat("dd");
    private DateFormat month = new SimpleDateFormat("MM");
    private DateFormat year = new SimpleDateFormat("yyyy");

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
                LocalDate today = LocalDate.now();
                LocalDate birthday;
                Period p = Period.between(today, today);

                if (isValid(spinner1.getValue() + "/" + spinner2.getValue() + "/" + spinner3.getValue())) {
                    birthday = LocalDate.of((Integer) spinner3.getValue(), (Integer) spinner2.getValue(), (Integer) spinner1.getValue());
                    p = Period.between(birthday, today);
                }

                if (isValid(spinner1.getValue() + "/" + spinner2.getValue() + "/" + spinner3.getValue())
                        && p.getYears() >= 18
                        && p.getYears() <= 90
                        && isValidEmailAddress(emailTextField.getText())
                        && !lastTextField.getText().equals("")
                        && !nameTextField.getText().equals("")
                        && !fatherTextField.getText().equals("")
                        && !lastTextField.getText().matches(".*\\d.*")
                        && !nameTextField.getText().matches(".*\\d.*")
                        && !fatherTextField.getText().matches(".*\\d.*")) {

                    sqlFunctions.addPeople("people",
                            nameTextField.getText(),
                            lastTextField.getText(),
                            fatherTextField.getText(),
                            (manRadioButton.isSelected() ? "M" : "W"),
                            (Integer) spinner1.getValue(),
                            (Integer) spinner2.getValue(),
                            (Integer) spinner3.getValue(),
                            emailTextField.getText()
                    );
                    updateTable(tableModel);
                } else if (nameTextField.getText().equals("") || nameTextField.getText().matches(".*\\d.*")) {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Імя введено некоректно!!", "Увага!", JOptionPane.WARNING_MESSAGE);
                } else if (lastTextField.getText().equals("") || lastTextField.getText().matches(".*\\d.*")) {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Призвіще введено некоректно!!", "Увага!", JOptionPane.WARNING_MESSAGE);
                } else if (fatherTextField.getText().equals("") || fatherTextField.getText().matches(".*\\d.*")) {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f, "По-батькові введено некоректно!!", "Увага!", JOptionPane.WARNING_MESSAGE);
                } else if (!(isValidEmailAddress(emailTextField.getText()))) {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Email введено не коректно!", "Увага!", JOptionPane.WARNING_MESSAGE);
                } else {
                    JFrame f = new JFrame();
                    JOptionPane.showMessageDialog(f, "Дату народження введено некоректно! \nЯкщо ви ввели все правильно,\nможливо працівнику ще немає 18, чи більше 90.", "Увага!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void updateTable(DefaultTableModel model) {
        model.setRowCount(0);
        sqlFunctions.selectAllPeople("people", model);
    }

    public boolean isValid(String dateStr) {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
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
        buttonsPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 6, new Insets(10, 5, 10, 5), -1, -1));
        buttonsPanel.setBackground(new Color(-16737636));
        buttonsPanel.setEnabled(true);
        mainPanel.add(buttonsPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        addButton = new JButton();
        addButton.setBackground(new Color(-16740093));
        addButton.setHorizontalAlignment(0);
        addButton.setText("Додати");
        buttonsPanel.add(addButton, new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteButton = new JButton();
        deleteButton.setBackground(new Color(-4390902));
        deleteButton.setForeground(new Color(-16777216));
        deleteButton.setText("Видалити");
        buttonsPanel.add(deleteButton, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 6, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(87, 16), null, 0, false));
        womanRadioButton = new JRadioButton();
        womanRadioButton.setBackground(new Color(-16737636));
        womanRadioButton.setEnabled(true);
        womanRadioButton.setForeground(new Color(-16777216));
        womanRadioButton.setHorizontalAlignment(0);
        womanRadioButton.setText("Жіноча");
        buttonsPanel.add(womanRadioButton, new com.intellij.uiDesigner.core.GridConstraints(6, 4, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 20), null, 0, false));
        IDTextField = new JTextField();
        IDTextField.setText("");
        buttonsPanel.add(IDTextField, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(60, 20), null, 0, false));
        spinner2 = new JSpinner();
        buttonsPanel.add(spinner2, new com.intellij.uiDesigner.core.GridConstraints(5, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 40), new Dimension(50, 40), null, 0, false));
        spinner3 = new JSpinner();
        buttonsPanel.add(spinner3, new com.intellij.uiDesigner.core.GridConstraints(5, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(70, 40), new Dimension(70, 40), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Bahnschrift", -1, 16, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-16777216));
        label1.setText("ID для видалення");
        buttonsPanel.add(label1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Bahnschrift", -1, 16, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-16777216));
        label2.setText("Прізвище:");
        buttonsPanel.add(label2, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lastTextField = new JTextField();
        lastTextField.setHorizontalAlignment(0);
        lastTextField.setText("");
        buttonsPanel.add(lastTextField, new com.intellij.uiDesigner.core.GridConstraints(2, 2, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Bahnschrift", -1, 16, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-16777216));
        label3.setText("Імя:");
        buttonsPanel.add(label3, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Bahnschrift", -1, 16, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-16777216));
        label4.setText("По батькові:");
        buttonsPanel.add(label4, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fatherTextField = new JTextField();
        fatherTextField.setHorizontalAlignment(0);
        fatherTextField.setText("");
        buttonsPanel.add(fatherTextField, new com.intellij.uiDesigner.core.GridConstraints(4, 2, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        nameTextField = new JTextField();
        nameTextField.setHorizontalAlignment(0);
        nameTextField.setText("");
        buttonsPanel.add(nameTextField, new com.intellij.uiDesigner.core.GridConstraints(3, 2, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        spinner1 = new JSpinner();
        buttonsPanel.add(spinner1, new com.intellij.uiDesigner.core.GridConstraints(5, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, new Dimension(50, 40), new Dimension(50, 40), null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$(null, -1, 12, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-16777216));
        label5.setText("День");
        buttonsPanel.add(label5, new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setForeground(new Color(-16777216));
        label6.setText("Місяць");
        buttonsPanel.add(label6, new com.intellij.uiDesigner.core.GridConstraints(5, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$(null, -1, 12, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-16777216));
        label7.setText("Рік");
        buttonsPanel.add(label7, new com.intellij.uiDesigner.core.GridConstraints(5, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailTextField = new JTextField();
        emailTextField.setHorizontalAlignment(0);
        emailTextField.setText("");
        buttonsPanel.add(emailTextField, new com.intellij.uiDesigner.core.GridConstraints(7, 2, 1, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$("Bahnschrift", -1, 16, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-16777216));
        label8.setText("Email:");
        buttonsPanel.add(label8, new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        labelgroup = new JLabel();
        labelgroup.setBackground(new Color(-1709330));
        Font labelgroupFont = this.$$$getFont$$$("Bahnschrift", -1, 16, labelgroup.getFont());
        if (labelgroupFont != null) labelgroup.setFont(labelgroupFont);
        labelgroup.setForeground(new Color(-16777216));
        labelgroup.setHorizontalAlignment(0);
        labelgroup.setText("Стать:");
        buttonsPanel.add(labelgroup, new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(86, 16), null, 0, false));
        manRadioButton = new JRadioButton();
        manRadioButton.setBackground(new Color(-16737636));
        manRadioButton.setEnabled(true);
        manRadioButton.setForeground(new Color(-16777216));
        manRadioButton.setHorizontalAlignment(0);
        manRadioButton.setText("Чоловіча");
        buttonsPanel.add(manRadioButton, new com.intellij.uiDesigner.core.GridConstraints(6, 2, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(80, 20), null, 0, false));
        tablePanel = new JPanel();
        tablePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        tablePanel.setBackground(new Color(-16739451));
        mainPanel.add(tablePanel, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        scrollPanel = new JScrollPane();
        scrollPanel.setBackground(new Color(-8413810));
        tablePanel.add(scrollPanel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        table1.setBackground(new Color(-6688852));
        Font table1Font = this.$$$getFont$$$(null, -1, 12, table1.getFont());
        if (table1Font != null) table1.setFont(table1Font);
        table1.setForeground(new Color(-16777216));
        table1.setGridColor(new Color(-16777216));
        scrollPanel.setViewportView(table1);
        label2.setLabelFor(lastTextField);
        label3.setLabelFor(nameTextField);
        label4.setLabelFor(fatherTextField);
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
