import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditWindow extends JFrame implements ActionListener {

    private JButton editMensGreetings = new JButton("Редагувати чоловічі привітання");
    private JButton editWomensGreetings = new JButton("Редагувати жіночі привітання");
    private JButton editCollegeStaff = new JButton("Редагувати персонал коледжу");
    private JLabel label = new JLabel("Меню редагування");
    private ImageIcon ico = new ImageIcon(getClass().getClassLoader().getResource("images/edit.png"));



    public EditWindow() {
        //title
        super("Редагувати БД");

        //on.exit
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //size
        this.setBounds(200, 200, 450, 450);
        this.setResizable(false);

        //set icon
        this.setIconImage(ico.getImage());

        //colours
        this.getContentPane().setBackground(new Color(18, 123, 70));

        //label
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setFont(new Font("Trebuchet MS", Font.TRUETYPE_FONT, 30));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIconTextGap(50);
        label.setBounds(50, 10, 350, 50);
        this.add(label);

        //editMensGreetings
        editMensGreetings.addActionListener(this);
        editMensGreetings.setBounds(100, 200, 250, 70);
        editMensGreetings.setFocusable(false);
        // ImageIcon sendIco = new ImageIcon("src/main/resources/images/send.png");
        //  editMensGreetings.setIcon(sendIco);
        editMensGreetings.setBackground(new Color(125, 187, 4));
        this.add(editMensGreetings);

        //editWomensGreetings
        editWomensGreetings.addActionListener(this);
        editWomensGreetings.setBounds(100, 100, 250, 70);
        editWomensGreetings.setFocusable(false);
        //ImageIcon editIco = new ImageIcon("src/main/resources/images/edit.png");
        //editWomensGreetings.setIcon(editIco);
        editWomensGreetings.setBackground(new Color(125, 187, 4));
        this.add(editWomensGreetings);

        //editCollegeStaff
        editCollegeStaff.addActionListener(this);
        editCollegeStaff.setBounds(100, 300, 250, 70);
        editCollegeStaff.setFocusable(false);
        //ImageIcon editIco = new ImageIcon("src/main/resources/images/edit.png");
        //editCollegeStaff.setIcon(editIco);
        editCollegeStaff.setBackground(new Color(125, 187, 4));
        this.add(editCollegeStaff);


        this.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(actionEvent.getSource()==editMensGreetings){
            System.out.println("Press mens greetings!");
            editGreetingsMenu editGreetingsMenu = new editGreetingsMenu("M");
            editGreetingsMenu.setVisible(true);
        }

        if(actionEvent.getSource()==editWomensGreetings){
            System.out.println("Press womens greetings!");
            editGreetingsMenu editGreetingsMenu = new editGreetingsMenu("W");
            editGreetingsMenu.setVisible(true);
        }

        if(actionEvent.getSource()==editCollegeStaff){
            {
                System.out.println("Press editCollegeStaff!");
                JFrame editCoWorkerWindow = new JFrame("EditCoWorkerWindow");
                editCoWorkerWindow.setContentPane(new EditCoWorkerWindow().mainPanel);
                editCoWorkerWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                editCoWorkerWindow.pack();
                editCoWorkerWindow.setIconImage(ico.getImage());
                editCoWorkerWindow.setTitle("Редагування БД працівників");
                editCoWorkerWindow.setVisible(true);
            }
        }

    }


}
