
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author dimon
 */
public class editGreetingsMenu extends javax.swing.JFrame {

    public SQLFunctions sqlFunctions = new SQLFunctions();


    public editGreetingsMenu(String gender) {

        super("Редагування "+(gender=="M"?"чоловічих":"жіночих")+" привітань");

        ImageIcon ico = new ImageIcon(getClass().getClassLoader().getResource("images/edit.png"));
        this.setIconImage(ico.getImage());

        this.gender = gender;
        initComponents(gender);
    }

    private void initComponents(String gender) {

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jTextField1 = new JTextField();
        jTextField2 = new JTextField();
        jPanel3 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTable1 = new JTable();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setLocationByPlatform(true);


        setPreferredSize(new Dimension(750, 470));
        setResizable(false);

        jPanel1.setBackground(new Color(18, 123, 70));

        jPanel2.setBackground(new Color(43, 43, 43));

        jTextField2.setText("Введіть ID:");
        jTextField2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jTextField2.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                    jTextField2.setText("");
                }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });


        jButton1.setBackground(new Color(189, 48, 0));
        jButton1.setText("Видалити привітання");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Додати привітання");
        jButton2.setBackground(new Color(87, 199, 0));
        jButton2.setFocusable(false);
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText("Напишіть"+ (gender == "M"?" чоловіче":" жіноче")+" привітання");
        jTextField1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField1.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent e){
                jTextField1.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTextField2, GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    //.addGap(34, 34, 34))
                .addGap(34, 34, 34)
                .addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );

        //DefaultTableModel tableModel;
        jTable1.setModel( tableModel = new DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "ID", "TEXT"
            }
        ) {
            Class[] types = new Class [] {
                Integer.class, String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });

        sqlFunctions.selectAllGratters(gender == "M"?"m_gratters":"w_gratters", tableModel);


        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    //int column = target.getSelectedColumn();
                    //System.out.println(row + "  "+ column);
                    jTextField2.setText(String.valueOf(jTable1.getModel().getValueAt(row, 0)));

            }
        });

        jScrollPane1.setViewportView(jTable1);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE))
        );

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        sqlFunctions.addGratter((this.gender == "M"?"m_gratters":"w_gratters"), jTextField1.getText());
        updateTable(tableModel);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        sqlFunctions.delete((this.gender == "M"?"m_gratters":"w_gratters"), jTextField2.getText());
        updateTable(tableModel);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jTextField1.setText("");
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        jTextField2.setText("");
    }

    private void updateTable(DefaultTableModel model){
        model.setRowCount(0);
        sqlFunctions.selectAllGratters(gender == "M"?"m_gratters":"w_gratters", model);
    }

    /**
     * @param args the command line arguments
     */


    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private DefaultTableModel tableModel;
    private String gender;
}
