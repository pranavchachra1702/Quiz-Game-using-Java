package com.FORMS;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

public class Quiz extends WindowAdapter implements ActionListener, ItemListener {

    public int[] Correct_option_array = {3, 1, 4, 1, 1, 2, 3, 2, 1, 4};
    public int[] resp = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    String firstname, lastname, user_email;
    long user_phone_number;

    int n = 10, i = 0;
    public boolean[] res = new boolean[n];

    Frame f = new Frame();
    Image icon_for_main_quiz = Toolkit.getDefaultToolkit().getImage("D:\\icon.jpg");

    Checkbox o1, o2, o3, o4;
    CheckboxGroup g = new CheckboxGroup();

    /*List of options*/
    String[] question = new String[n];
    String[] opt1 = {"echo(\"Hello World\")", "#This is a comment", "Myvar", "x=5", ".py", "len()", "toUpperCase", "repl()", "LIST", "stop"};
    String[] opt2 = {"p(\"Hello World\")", "/*This is a comment*/", "_myvar", "x=int(5)", ".pt", "trim()", "upperCase()", "replace()", "TUPLE", "return"};
    String[] opt3 = {"print(\"Hello World\")", "//This is a comment", "my_var", "Both answers are correct", ".html", "strip()", "upper()", "replaceString()", "DICTIONARY", "exit"};
    String[] opt4 = {"echo \"Hello World\"", "<--! This is a comment -->", "my-var", "None of the above", ".xml", "ptrim()", "uppercase()", "switch()", "SET", "break",};

    /*buttons and labels*/
    Button b1 = new Button("PREVIOUS");
    Button b2 = new Button("NEXT");
    Label l, L;

    {
        question[0] = "What is a correct syntax to output \"Hello World\" in Python?";
        question[1] = "How do you insert COMMENTS in Python code?";
        question[2] = "Which one is NOT a legal variable name?";
        question[3] = "How do you create a variable with the numeric value 5?";
        question[4] = "What is the correct file extension for Python files?";
        question[5] = "Which method can be used to remove whitespaces from a string?";
        question[6] = "Which method can be used to return a string in upper case letters?";
        question[7] = "Which method can be used to replace parts of a string?";
        question[8] = "Which collection is ordered,changeable,and allows duplicate members?";
        question[9] = "Which statement is used to stop a loop?";
    }

    /*Constructor*/
    public Quiz(String firstname, String lastname, String user_email, long user_phone_number) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.user_email = user_email;
        this.user_phone_number = user_phone_number;
    }

    void Quiz_Stage(int i) {
        Panel p = new Panel();
        p.setBounds(20, 40, 710, 70);

        /*Setting the icon image*/
        f.setIconImage(icon_for_main_quiz);
        f.setBackground(Color.decode("#181818"));

        l = new Label("QUESTION" + " N0. " + (i + 1));
        l.setForeground(Color.decode("#FFFFFF"));

        b1.setFont(new Font("Roboto Condensed Light", Font.BOLD, 15));
        b1.setEnabled(false);

        b2.setFont(new Font("Roboto Condensed Light", Font.BOLD, 15));

        l.setBounds(10, 10, 710, 30);
        l.setAlignment(Label.CENTER);
        l.setFont(new Font("Roboto Condensed Light", Font.BOLD, 23));
        p.add(l);
        p.setLayout(null);
        p.setBackground(Color.DARK_GRAY);
        p.add(l);
        Panel P = new Panel();
        P.setBounds(20, 120, 710, 100);
        L = new Label(question[i]);
        L.setFont(new Font("Serif", Font.BOLD, 23));
        L.setForeground(Color.decode("#FFFFFF"));
        L.setBounds(10, 10, 710, 30);
        // L
        L.setMaximumSize(new Dimension(20, 10));
        P.setMaximumSize(new Dimension(20, 10));
        P.add(L);
        P.setLayout(null);
        f.setTitle("Quiz");
        f.setSize(750, 550);
        f.add(P);
        f.add(p);
        options(f, i);
        buttons(f);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setLayout(null);
        f.addWindowListener(this);
        f.setVisible(true);
    }

    void options(Frame f, int i) {
        Panel P = new Panel();
        P.setBounds(20, 230, 710, 200);

        o1 = new Checkbox(opt1[i], g, false);
        o2 = new Checkbox(opt2[i], g, false);
        o3 = new Checkbox(opt3[i], g, false);
        o4 = new Checkbox(opt4[i], g, false);


        o1.setBounds(10, 10, 690, 40);
        o2.setBounds(10, 60, 690, 40);
        o3.setBounds(10, 110, 690, 38);
        o4.setBounds(10, 158, 690, 35);
        o1.setBackground(Color.pink);
        o3.setBackground(Color.pink);
        o2.setBackground(Color.magenta);
        o4.setBackground(Color.magenta);
        P.add(o1);
        P.add(o2);
        P.add(o3);
        P.add(o4);
        o1.setFont(new Font("SansSerif", Font.LAYOUT_LEFT_TO_RIGHT, 23));
        o2.setFont(new Font("Helvetica", Font.LAYOUT_LEFT_TO_RIGHT, 23));
        o3.setFont(new Font("Helvetica", Font.LAYOUT_LEFT_TO_RIGHT, 23));
        o4.setFont(new Font("Helvetica", Font.LAYOUT_LEFT_TO_RIGHT, 23));

        o1.addItemListener(this);
        o2.addItemListener(this);
        o3.addItemListener(this);
        o4.addItemListener(this);
        P.setLayout(null);
        P.setVisible(true);
        P.setLayout(null);

        f.add(P);

        f.setLayout(null);
        f.setVisible(true);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        /*if an option is selected, change the color*/
        if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == o1) {
            resp[i] = 1;
            o1.setBackground(Color.green);
            o2.setBackground(Color.magenta);
            o3.setBackground(Color.pink);
            o4.setBackground(Color.magenta);
        }
        if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == o2) {
            resp[i] = 2;
            o1.setBackground(Color.pink);
            o2.setBackground(Color.green);
            o3.setBackground(Color.pink);
            o4.setBackground(Color.magenta);
        }
        if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == o3) {
            resp[i] = 3;
            o1.setBackground(Color.pink);
            o2.setBackground(Color.magenta);
            o3.setBackground(Color.green);
            o4.setBackground(Color.magenta);
        }
        if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == o4) {
            resp[i] = 4;
            o1.setBackground(Color.pink);
            o2.setBackground(Color.magenta);
            o3.setBackground(Color.pink);
            o4.setBackground(Color.green);
        }


        if (e.getSource() == o1 && 1 == Correct_option_array[i] && e.getStateChange() == ItemEvent.SELECTED) {
            res[i] = true;
        } else if (e.getSource() == o2 && 2 == Correct_option_array[i] && e.getStateChange() == ItemEvent.SELECTED) {
            res[i] = true;
        } else if (e.getSource() == o3 && 3 == Correct_option_array[i] && e.getStateChange() == ItemEvent.SELECTED) {
            res[i] = true;
        } else if (e.getSource() == o4 && 4 == Correct_option_array[i] && e.getStateChange() == ItemEvent.SELECTED) {
            res[i] = true;
        }
    }

    public void actionPerformed(ActionEvent e) {

        if ((e.getSource() == b1 && i == 0) || (e.getSource() == b2 && i == (n - 1))) {

        }

        /*when the user is on the last question and presses submit button*/
        if (e.getSource() == b2 && b2.getLabel().equals("SUBMIT")) {

            /*showing the result on submit button*/
            f.dispose();

            try {
                Result result = new Result(res, firstname, lastname, user_email, user_phone_number);
            } catch (SQLException | IOException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == b1) {
            i--;
            b2.setLabel("NEXT");
            o1.setBackground(Color.pink);
            o2.setBackground(Color.magenta);
            o3.setBackground(Color.pink);
            o4.setBackground(Color.magenta);
            b2.setBackground(Color.yellow);
            if (i == 0) {
                b1.setEnabled(false);
            }
            l.setText("QUESTION" + " N0. " + (i + 1));
            L.setText(question[i]);
            o1.setLabel(opt1[i]);
            o2.setLabel(opt2[i]);
            o3.setLabel(opt3[i]);
            o4.setLabel(opt4[i]);
            if (resp[i] == 1) {
                o1.setState(true);
                o1.setBackground(Color.green);
            }
            if (resp[i] == 2) {
                o2.setState(true);
                o2.setBackground(Color.green);
            }
            if (resp[i] == 3) {
                o3.setState(true);
                o3.setBackground(Color.green);
            }
            if (resp[i] == 4) {
                o4.setState(true);
                o4.setBackground(Color.green);
            }

        } else if (e.getSource() == b2) {

            i++;
            o1.setBackground(Color.pink);
            o2.setBackground(Color.magenta);
            o3.setBackground(Color.pink);
            o4.setBackground(Color.magenta);
            b2.setBackground(Color.yellow);
            b1.setEnabled(true);

            if (i == 9) {

                b2.setBackground(Color.red);
                b2.setLabel("SUBMIT");
            }


            l.setText("QUESTION" + " N0. " + (i + 1));
            L.setText(question[i]);
            o1.setLabel(opt1[i]);
            o2.setLabel(opt2[i]);
            o3.setLabel(opt3[i]);
            o4.setLabel(opt4[i]);
            if (resp[i] == 1) {
                o1.setState(true);
                o1.setBackground(Color.green);
            }
            if (resp[i] == 2) {
                o2.setState(true);
                o2.setBackground(Color.green);
            }
            if (resp[i] == 3) {
                o3.setState(true);
                o3.setBackground(Color.green);
            }
            if (resp[i] == 4) {
                o4.setState(true);
                o4.setBackground(Color.green);
            }
        }
    }

    public void buttons(Frame f) {

        b1.setBounds(45, 470, 100, 50);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b1.setBackground(Color.yellow);
        f.add(b1);
        f.setLayout(null);
        f.setVisible(true);

        b2.setBounds(610, 470, 100, 50);
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setBackground(Color.yellow);
        f.add(b2);
        f.setLayout(null);
        f.setVisible(true);
        f.add(b1);
        f.add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
    }
    public void windowClosing(WindowEvent e) {
        f.dispose();
    }
}