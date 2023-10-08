package com.FORMS;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class QuizForm extends WindowAdapter implements WindowListener, ActionListener {

    Frame QuizFormFrame = new Frame();

    /*variables to fetch all the details*/
    public String firstname, lastname, user_email;
    public long user_phone_number;

    Panel main_panel = new Panel(null);
    ScrollPane scp = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);

    /*Creating the text filed for first name*/
    TextField first_name_text = new TextField();

    /*Creating the text filed for last name*/
    TextField last_name_text = new TextField();

    /*Creating the text field for email*/
    TextField email = new TextField();

    /*Creating the text field for phone number*/
    TextField phone_number = new TextField();

    /*main method*/
    public static void main(String[] args) {
        QuizForm obj = new QuizForm();
        try {
            obj.quizFormMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void quizFormMethod() throws IOException {

        /*setting icon image*/
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\icon.jpg");

        /*for creating the scroll bar facility firstly create panel, in that panel add all the components, then create a scroll pane, add the panel to the scroll pane then finally add
        the scroll pane to the main frame
        * */

        main_panel.setBounds(10, 0, 260, 380);
        main_panel.setBackground(Color.PINK);

        scp.setSize(390, 250);
        scp.setWheelScrollingEnabled(false);


        QuizFormFrame.setIconImage(icon);
        QuizFormFrame.setTitle("User Details");
        QuizFormFrame.setSize(400, 260);
        QuizFormFrame.setVisible(true);
        QuizFormFrame.setLayout(new BorderLayout(7, 7));
        QuizFormFrame.setLocationRelativeTo(null);
        QuizFormFrame.setResizable(false);
        QuizFormFrame.setFont(new Font("Serif", Font.BOLD, 13));


        first_name_text.setBounds(90, 40, 170, 20);

        /*creating the first name label*/
        Label label_for_first_name_text = new Label("First Name : ");
        label_for_first_name_text.setBounds(20, 40, 65, 20);


        last_name_text.setBounds(90, 100, 170, 20);

        /*creating the last name label*/
        Label label_for_last_name_text = new Label("Last Name : ");
        label_for_last_name_text.setBounds(20, 100, 65, 20);

        /*Creating the text field for email address*/

        email.setBounds(90, 160, 170, 20);

        /*Creating the label for the email*/
        Label email_label = new Label("Email : ");
        email_label.setBounds(20, 160, 65, 20);

        /*Creating the text field for phone number*/

        phone_number.setBounds(90, 220, 170, 20);

        /*Creating the label for the email*/
        Label phone_label = new Label("Phone No.:");
        phone_label.setBounds(20, 220, 65, 20);

        /*Creating the text field for college name*/
        TextField college_name = new TextField();
        college_name.setBounds(90, 280, 170, 20);

        /*Creating the label for the college_name*/
        Label college_label = new Label("College :");
        college_label.setBounds(20, 280, 65, 20);

        /*Adding submit button*/
        Button b = new Button("SUBMIT");
        b.setBounds(140, 340, 70, 30);
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(this);

        main_panel.add(first_name_text);
        main_panel.add(label_for_first_name_text);
        main_panel.add(last_name_text);
        main_panel.add(label_for_last_name_text);
        main_panel.add(email);
        main_panel.add(email_label);
        main_panel.add(phone_number);
        main_panel.add(phone_label);
        main_panel.add(college_name);
        main_panel.add(college_label);
        main_panel.add(b);

        scp.add(main_panel);

        QuizFormFrame.add(scp);
        QuizFormFrame.addWindowListener(this);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        QuizFormFrame.dispose();
    }

    /*method for handling the button clicked event*/
    @Override
    public void actionPerformed(ActionEvent e) {
        /*extract all the details of the user entered in the form*/
        firstname = first_name_text.getText();
        lastname = last_name_text.getText();
        user_email = email.getText();
        user_phone_number = Long.parseLong(phone_number.getText());

        // after this open the MAIN QUIZ WINDOW
        QuizFormFrame.dispose();

        Quiz obj2 = new Quiz(firstname, lastname, user_email, user_phone_number);
        obj2.Quiz_Stage(obj2.i);
    }
}