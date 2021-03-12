import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class MainWindow extends JFrame implements ActionListener {

    private JButton sendButton = new JButton("Відправити");
    private JButton editButton = new JButton("Редагувати БД");
    private JTable dbTable = new JTable();
    private DefaultTableModel dbModel;
    private JScrollPane jScrollPane1;
    private JLabel label = new JLabel("Іменинники:");
    private SQLFunctions sqlFunctions = new SQLFunctions();

    //date and formats
    private Date date = new Date();
    private DateFormat day = new SimpleDateFormat("dd");
    private DateFormat month = new SimpleDateFormat("MM");
    private DateFormat year = new SimpleDateFormat("yyyy");


    public MainWindow(){
        //title
        super("Іменинник ППФК");

        //exit
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //size
        this.setBounds(0,0, 800, 300);
        this.setResizable(false);

        //set icon
        //ImageIcon ico = new ImageIcon("images/icon.png");
        ImageIcon ico = new ImageIcon(getClass().getClassLoader().getResource("images/icon.png"));
        this.setIconImage(ico.getImage());

        //colours
        this.getContentPane().setBackground(new Color(18, 123, 70));

        //label
        //ImageIcon PPCIco = new ImageIcon("images/PPC.png");
        ImageIcon PPCIco = new ImageIcon(getClass().getClassLoader().getResource("images/PPC.png"));
        Image PPCImg = PPCIco.getImage() ;
        Image ppcnewimg = PPCImg.getScaledInstance( 170, 170,  Image.SCALE_SMOOTH ) ;
        PPCIco = new ImageIcon(ppcnewimg);


        label.setIcon(PPCIco);
        label.setText("Іменинники:");
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(new Font("Trebuchet MS", Font.TRUETYPE_FONT, 34));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(20);
        label.setBounds(0,0,400,150);
        this.add(label);

        //sendButton
        sendButton.addActionListener(this);
        sendButton.setBounds(550,190,150,50);
        sendButton.setFocusable(false);

        //ImageIcon sendIco = new ImageIcon("images/send.png");
        ImageIcon sendIco = new ImageIcon(getClass().getClassLoader().getResource("images/send.png"));
        Image img = sendIco.getImage() ;
        Image newimg = img.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        sendIco = new ImageIcon(newimg);
        sendButton.setIcon(sendIco);


        sendButton.setBackground(new Color(125, 187, 4));
        this.add(sendButton);

        //editButton
        editButton.addActionListener(this);
        editButton.setBounds(100,190,150,50);
        editButton.setFocusable(false);

        //ImageIcon editIco = new ImageIcon("src/main/resources/images/edit.png");
        ImageIcon editIco = new ImageIcon(getClass().getClassLoader().getResource("images/edit.png"));
        Image editImg = editIco.getImage() ;
        Image neweditimg = editImg.getScaledInstance( 25, 25,  Image.SCALE_SMOOTH ) ;
        editIco = new ImageIcon(neweditimg);

        editButton.setIcon(editIco);

        editButton.setBackground(new Color(125, 187, 4));
        this.add(editButton);

        //table and scroll panel
        dbModel = new DefaultTableModel(){

            private static final long serialVersionUID = 1L;
            String[] names = {"Прізвище", "Імя", "По-батькові"};

            @Override
            public int getColumnCount() {
                return names.length;
            }

            @Override
            public String getColumnName(int index) {
                return names[index];
            }
        };


        dbTable.setModel(dbModel);

        jScrollPane1 = new JScrollPane();
        jScrollPane1.setViewportView(dbTable);


        jScrollPane1.setBounds(440,15,350,130);
        jScrollPane1.setVisible(true);

        //dbTable.setBounds(440, 15, 330,130 );


        sqlFunctions.selectBirthday("people", dbModel, Integer.parseInt(day.format(date)), Integer.parseInt(month.format(date)));


        this.add(jScrollPane1);

        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource()==sendButton){
            System.out.println("Press!");
            sqlFunctions.sendGreetings(Integer.parseInt(day.format(date)), Integer.parseInt(month.format(date)));
            sendButton.setBackground(new Color(143, 255, 0));
            sendButton.setText("Відправлено!");
            sendButton.setEnabled(false);
        }

        if(actionEvent.getSource()==editButton){
            System.out.println("Press edit button");
            EditWindow editWindow = new EditWindow();
            editWindow.setVisible(true);
        }
    }
}
