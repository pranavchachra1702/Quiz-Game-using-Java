package com.FORMS;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.*;


public class Result extends WindowAdapter implements ActionListener {

    public Frame f = new Frame();
    Button[] b = new Button[10];
    Button previous_result_button = new Button("SEE PREVIOUS SCORES");
    Panel panel = new Panel(new GridLayout(3,3,20,20));

    Label label = new Label();

    int score = 0;

    String firstname, lastname, user_email;
    long user_phone_number;

    Image icon_for_Result_quiz = Toolkit.getDefaultToolkit().getImage("D:\\icon.jpg");

    Result(boolean[] res, String firstname, String lastname, String user_email, long user_phone_number) throws SQLException, IOException {

        this.firstname = firstname;
        this.lastname = lastname;
        this.user_email = user_email;
        this.user_phone_number = user_phone_number;

        /*calculating final score*/
        for (boolean x : res) {
            if (x) {
                score++;
            }
        }

        previous_result_button.setBounds(210,520,190,50);
        previous_result_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        previous_result_button.setFont(new Font("Roboto Condensed Light", Font.BOLD,15));
        previous_result_button.addActionListener(this);
        f.add(previous_result_button);
        panel.setBounds(0,50,600,400);
        f.add(panel);

        label.setText("The final score is : "+score);
        label.setForeground(Color.decode("#FFFFFF"));
        label.setBounds(210,450,190,70);
        label.setFont(new Font("Roboto Condensed Light", Font.BOLD,18));
        f.add(label);

        for (int i = 0; i < 10; i++) {

            if(res[i]){
                /*firstly initialize the button*/
                b[i] = new Button(Integer.toString(i+1));

                b[i].setFont(new Font("Roboto Condensed Light", Font.BOLD, 15));
                b[i].setSize(70,70);
                b[i].setBackground(Color.decode("#04ff00"));
                panel.add(b[i]);
            }

            else{
                /*firstly initialize the button*/
                b[i] = new Button(Integer.toString(i+1));

                b[i].setFont(new Font("Roboto Condensed Light", Font.BOLD, 15));
                b[i].setSize(70,70);
                b[i].setBackground(Color.decode("#ff0000"));
                panel.add(b[i]);
            }
        }

        addDataToDatabase();

        panel.setVisible(true);

        f.setLayout(null);
        f.setTitle("Quiz Result");
        f.setFont(new Font("Serif", Font.BOLD, 13));
        f.addWindowListener(this);
        f.setIconImage(icon_for_Result_quiz);
        f.setSize(600, 600);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setBackground(Color.decode("#181818"));
        f.setVisible(true);
    }

    public void addDataToDatabase() {

        /*variables used for the connection to the database*/
        String url = "jdbc:mysql://localhost:3306/quiz";
        /*jdbc ==> java database connectivity*/

        String uname = "root";
        String pass = "123456";

        /*table/ relation name : quiz_result */
        String query = "INSERT INTO quiz_result VALUES ( " + "\"" + firstname + "\"" + "," + "\"" + lastname + "\"" + "," + "\"" + user_email + "\"" + "," + "\"" + user_phone_number
                + "\"" + "," + score + " )";

        try {
            /*adding data to database*/

            //Class.forName ==> Loads and  Registers the driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Creating the connection
            /*Connection is an interface, but we need an object of it to establish the connection
            * to the database, so to get the object of this interface we use the getConnection method of
            * the DriverManger class*/
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            st.executeUpdate(query);
            st.clearBatch();
            con.close();

        } catch (Exception e) {
            System.out.println("It is a failure in the system !! " + e);
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /*on clicking the see previous result button, ask the user to enter the name and the phone number,
        * if the corresponding details found in the database , show the results else make the dialog that
        * the results does not exist*/
            new show_previous_result();

    }
}

class show_previous_result extends WindowAdapter implements ActionListener {
    Frame previous_Results_frame = new Frame("Previous Result");

    TextField textField_for_name = new TextField();
    TextField textField_for_phone = new TextField();

    /*Label to show the score of the user which is found from the database*/
    Label query_result_label = new Label();

    show_previous_result(){

        Button submit_button = new Button("SUBMIT");

        Label label_for_name = new Label("Enter the name : ");
        label_for_name.setFont(new Font("Roboto Condensed Light", Font.BOLD,15));
        label_for_name.setForeground(Color.decode("#FFFFFF"));
        label_for_name.setBounds(50,70,130,70);

        Label label_for_phone = new Label("Enter Phone No.: ");
        label_for_phone.setFont(new Font("Roboto Condensed Light", Font.BOLD,15));
        label_for_phone.setForeground(Color.decode("#FFFFFF"));
        label_for_phone.setBounds(50,170,130,70);

        textField_for_name.setBounds(190,95,170,20);


        textField_for_phone.setEchoChar('*');
        textField_for_phone.setBounds(190,195,170,20);


        submit_button.setBounds(200,280,80,30);
        submit_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit_button.setFont(new Font("Roboto Condensed Light",Font.BOLD,15));
        submit_button.addActionListener(this);

        previous_Results_frame.add(submit_button);
        previous_Results_frame.add(label_for_name);
        previous_Results_frame.add(label_for_phone);
        previous_Results_frame.add(textField_for_phone);
        previous_Results_frame.add(textField_for_name);
        previous_Results_frame.setBackground(Color.decode("#121212"));
        previous_Results_frame.setResizable(false);
        previous_Results_frame.setSize(500,500);
        previous_Results_frame.setLayout(null);
        previous_Results_frame.setLocationRelativeTo(null);
        previous_Results_frame.addWindowListener(this);
        previous_Results_frame.setVisible(true);
    }

    @Override
    public void windowClosing(WindowEvent e) {
        previous_Results_frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /*if search for another user then first make the label text as null*/
        query_result_label.setText(null);

        query_result_label.setBounds(140,350,300,60);
        query_result_label.setFont(new Font("Roboto Condensed Light", Font.BOLD, 15));
        query_result_label.setForeground(Color.decode("#FFFFFF"));

        /*On clicking the submit button, fetch the text from the firstname and the phone number
        * and search the database whether any such record exists or not, if yes, then show the result, else
        * show a dialog box*/
        String firstname = textField_for_name.getText();
        String number = textField_for_phone.getText();

        /*variables used for the connection to the database*/
        String url = "jdbc:mysql://localhost:3306/quiz";
        String uname = "root";
        String pass = "123456";
        String query = "select * from quiz_result";

        /*Label which is to be added inside the dialog box !!*/
        Label label_for_dialog_box = new Label("No such user exists !",Font.CENTER_BASELINE);
        label_for_dialog_box.setFont(new Font("Roboto Condensed Light",Font.BOLD,15));
        label_for_dialog_box.setBounds(100,70,100,30);

        int flag=0;

        try{
            Connection con = DriverManager.getConnection(url, uname, pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while(rs.next()){
                if(firstname.equalsIgnoreCase(rs.getString(1)) && number.equalsIgnoreCase(rs.getString(4))){

                    int user_score = rs.getInt(5);
                    String database_name = rs.getString(1);
                    query_result_label.setText("The score of "+database_name+" is : "+user_score);
                    previous_Results_frame.add(query_result_label);
                    flag=1;
                    break;

                }
            }
            if(flag==0){
                Dialog d = new Dialog(previous_Results_frame,"Error");

                d.setSize(200,200);
                d.add(label_for_dialog_box);
                d.setLocationRelativeTo(null);
                d.setVisible(true);

                d.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        d.setVisible(false);
                    }
                });
            }
            st.clearBatch();
            con.close();
            rs.close();

        }catch (Exception e1){
            System.out.println("It is a failure in the system !!" + e1);
        }
    }
}
