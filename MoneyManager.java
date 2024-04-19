import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.html.HTMLEditorKit;


import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.sql.Statement;

import javax.swing.text.*;
class UserEntryValidator {

    public JTextField createNumericTextField() {
        JTextField textField = new JTextField();
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumericDocumentFilter());
        return textField;
    }

    static class NumericDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.insert(offset, string);

            if (isNumeric(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            StringBuilder sb = new StringBuilder();
            sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
            sb.replace(offset, offset + length, text);

            if (isNumeric(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean isNumeric(String str) {
            return str.matches("-?\\d+(\\.\\d+)?");
        }
    }
}

class CurrentUser {
    private String user_aadhar = null;
    private String user_PAN = null;
    private String user_id = null;
    private String user_password = null;
    private String user_honorific = null;
    private String user_fname = null;
    private String user_mname = null;
    private String user_lname = null;
    private Long user_ceb;

    // Setter :
    public void setUseraadhar(String user_aadhar) {
        this.user_aadhar = user_aadhar;
    }
    public void setUserPAN(String user_PAN) {
        this.user_PAN = user_PAN;
    }
    public void setUserid(String user_id) {
        this.user_id = user_id;
    }
    public void setUserpassword(String user_password) {
        this.user_password = user_password;
    }
    public void setUserhonorific(String user_honorific) {
        this.user_honorific = user_honorific;
    }
    public void setUserfname(String user_fname) {
        this.user_fname = user_fname;
    }
    public void setUsermname(String user_mname) {
        this.user_mname = user_mname;
    }
    public void setUserlname(String user_lname) {
        this.user_lname = user_lname;
    }
    public void setUserceb(Long user_ceb) {
        this.user_ceb = user_ceb;
    }
    
    // Fetcher : 
    public String fetchUseraadhar() {
        return this.user_aadhar;
    }
    public String fetchUserPAN() {
        return this.user_PAN;
    }
    public String fetchUserid() {
        return this.user_id;
    }
    public String fetchUserpassword() {
        return this.user_password;
    }
    public String fetchUserhonorific() {
        return this.user_honorific;
    }
    public String fetchUserfname() {
        return this.user_fname;
    }
    public String fetchUsermname() {
        return this.user_mname;
    }
    public String fetchUserlname() {
        return this.user_lname;
    }
    public Long fetchUserceb() {
        return this.user_ceb;
    }
}
public class MoneyManager {
    private static JFormattedTextField jftfa, jftfb, from, to;
    private static JTextField login_field = null, f_name = null, m_name = null, l_name = null, newpassword = null, cnfpassword = null;
    private static JPasswordField password_field = null, passwordField = null, passwordFieldII = null;
    private static JButton registration_button = null, login_button = null, resetbutton = null, nextbutton = null, submitButton = null, debit_button = null, credit_button = null, next_button = null, delete_button = null, showResults = null, reSearch = null;
    private static JComboBox<String> honorific_combo_box = null;
    private static MaskFormatter mff = null, mfg = null;
    private static JTable transactionTable = null;
    private static JScrollPane transactionScrollPane = null;
    private static CurrentUser cu = new CurrentUser();

    public static void main(String[] args) {
        MoneyManager initiate = new MoneyManager();
        initiate.loginPage(null);
        //initiate.registrationPortal(null);
        // loggedInPage(null);
    }
    public void loginPage(Runnable callback) {
        JFrame frame = new JFrame("Money Manager : Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);

        vSpace(panel, new JTextPane(), 40);

        JTextPane heading = new JTextPane();
        heading.setContentType("text/html");
        heading.setEditable(false);
        heading.setText("<html><center><div style='font-size: 45px;'>MONEY MANAGER</div></center></html>");
        panel.add(heading);

        vSpace(panel, new JTextPane(), 0);

        shiftByPixel(panel, new JTextPane(), 180);

        JTextPane programmed_by = new JTextPane();
        programmed_by.setContentType("text/html");
        programmed_by.setEditable(false);
        programmed_by.setText("<html><center><div style='font-size: 15px;'><i>[ By : Ishika Yadav ]</i></div></center></html>");
        panel.add(programmed_by);

        vSpace(panel, new JTextPane(), 100);

        JTextPane user_id = new JTextPane();
        user_id.setContentType("text/html");
        user_id.setEditable(false);
        user_id.setText("<html><center><div style='font-size: 25px;'>User ID</div></center></html>");
        panel.add(user_id);

        shiftByPixel(panel, new JTextPane(), 500);

        vSpace(panel, new JTextPane(), 20);

        login_field = new JTextField();
        login_field.setMargin(new Insets(0, 30, 0, 0));
        login_field.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        login_field.setPreferredSize(new Dimension(500, 45));
        panel.add(login_field);
        login_field.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                // if password has character > 5
                String loginText = login_field.getText().trim();
                String passwordText = new String(password_field.getPassword()).trim();
                login_button.setEnabled(!loginText.isEmpty() && !passwordText.isEmpty());
                registration_button.setEnabled(!(!loginText.isEmpty() || !passwordText.isEmpty()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String loginText = login_field.getText().trim();
                String passwordText = new String(password_field.getPassword()).trim();
                login_button.setEnabled(!loginText.isEmpty() && !passwordText.isEmpty());
                registration_button.setEnabled(!(!loginText.isEmpty() || !passwordText.isEmpty()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 100);

        vSpace(panel, new JTextPane(), 30);
        
        JTextPane user_password = new JTextPane();
        user_password.setContentType("text/html");
        user_password.setEditable(false);
        user_password.setText("<html><center><div style='font-size: 25px;'>User Password</div></center></html>");
        panel.add(user_password);

        shiftByPixel(panel, new JTextPane(), 410);

        vSpace(panel, new JTextPane(), 20);

        password_field = new JPasswordField();
        password_field.setMargin(new Insets(0, 30, 0, 0));
        password_field.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        password_field.setPreferredSize(new Dimension(500, 45));
        panel.add(password_field);
        password_field.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                // if password has character > 5
                String loginText = login_field.getText().trim();
                String passwordText = new String(password_field.getPassword()).trim();
                login_button.setEnabled(!loginText.isEmpty() && !passwordText.isEmpty());
                registration_button.setEnabled(!(!loginText.isEmpty() || !passwordText.isEmpty()));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String loginText = login_field.getText().trim();
                String passwordText = new String(password_field.getPassword()).trim();
                login_button.setEnabled(!loginText.isEmpty() && !passwordText.isEmpty());
                registration_button.setEnabled(!(!loginText.isEmpty() || !passwordText.isEmpty()));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
             
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 100);

        vSpace(panel, new JTextPane(), 30);

        registration_button = new JButton("New User ? Register");
        registration_button.setEnabled(true);
        registration_button.setPreferredSize(new Dimension(200, 35));
        panel.add(registration_button);
        registration_button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                registrationPortal(null);
                frame.dispose();
            }
        });

        shiftByPixel(panel, new JTextPane(), -125);

        login_button = new JButton("Login >");
        login_button.setEnabled(true);
        login_field.setText("2212C9E1");
        password_field.setText("Rayyan@mm");
        login_button.setPreferredSize(new Dimension(200, 35));
        panel.add(login_button);
        login_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String user_entered_id = null, user_entered_password = null, url = null, username = null, password = null, actual_password = null;
                Connection con = null;
                user_entered_id = login_field.getText().trim();
                user_entered_password =  new String(password_field.getPassword()).trim();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    url = "jdbc:mysql://localhost:3306/saving";
                    username = "IshikaYadav";
                    password = "Ishika@mysql";
                    con = DriverManager.getConnection(url, username, password);
                    String query = "select * from userdata where `User ID` = '"+user_entered_id+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        actual_password = rs.getString("User Password");
                        
                    }
                    if(actual_password==null) {
                        JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:black;'>"+"<b>Login Failed : No Such User With "+user_entered_id+" User Id Exists</b></div></html>", "Failure", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    con.close();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
                setGlassPaneView(frame, true);
                showLoadingScreen("Logging in...", 1500);
                setGlassPaneView(frame, false);
                if(user_entered_password.equals(actual_password)) {
                    cu.setUserid(user_entered_id);
                    cu.setUserpassword(actual_password);
                    loggedInPage(null);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:Red;'>"+"<b>Login Failed : Incorrect Password</b></div></html>", "Failure", JOptionPane.WARNING_MESSAGE);
                    login_field.setText("");
                    password_field.setText("");
                }
                System.out.println(user_entered_password);
                
                
                
        /*
            `User ID` varchar(6) primary key,
            `User Password` varchar(100) not null,

         */
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 100);



        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }
    public static void loggedInPage(Runnable callback) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/saving";
            String username = "IshikaYadav";
            String password = "Ishika@mysql";
            Connection con = DriverManager.getConnection(url, username, password);
            String query = "select * from userdata where `User ID` = '"+cu.fetchUserid()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                cu.setUserhonorific(rs.getString("User Honorific"));
                cu.setUserfname(rs.getString("User FName"));
                cu.setUsermname(rs.getString("User MName"));
                cu.setUserlname(rs.getString("User LName"));
                
                
            }
            con.close();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
        JFrame frame = new JFrame("Money Manager : Registration Portal");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        
        vSpace(panel, new JTextPane(), 15 );

        JTextPane heading = new JTextPane();
        heading.setEditable(false);
        heading.setContentType("text/html");
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'>Welcome <strong>"+cu.fetchUserhonorific()+" "+cu.fetchUserfname()+" "+cu.fetchUsermname()+" "+cu.fetchUserlname()+"</strong></div></html>");
        panel.add(heading);

        vSpace(panel, new JTextPane(), 50);

        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 10);

        shiftByPixel(panel, new JTextPane(), 1120);

        JButton logout = new JButton("Log Out");
        logout.setEnabled(true);
        logout.setPreferredSize(new Dimension(100, 35));
        logout.setBackground(new Color(199, 54, 59));
        logout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(logout);
        logout.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setGlassPaneView(frame, true);
                showLoadingScreen("Logging Out...", 1500);
                setGlassPaneView(frame, false);
                cu.setUserid("");
                cu.setUserhonorific("");
                cu.setUserfname("");
                cu.setUsermname("");
                cu.setUserlname("");
                new MoneyManager().loginPage(null);
                frame.dispose();
            }
            
        });

        vSpace(panel, new JTextPane(), 100);

        JButton debit_button = new JButton("Debit");
        debit_button.setEnabled(true);
        debit_button.setPreferredSize(new Dimension(220, 50));
        debit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(debit_button);
        debit_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                debit(null);
                frame.dispose();
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 100);

        JButton ministatement_button = new JButton("Mini Statement");
        ministatement_button.setEnabled(true);
        ministatement_button.setPreferredSize(new Dimension(220, 50));
        ministatement_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(ministatement_button);
        ministatement_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mini_Statement_Module_Window_I(null);
                    frame.dispose();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 100);

        JButton changepassword_button = new JButton("Change Password");
        changepassword_button.setEnabled(true);
        changepassword_button.setPreferredSize(new Dimension(220, 50));
        changepassword_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(changepassword_button);
        changepassword_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                changePasswordWindow1(null);  
                frame.dispose();
            }
            
        });

        vSpace(panel, new JTextPane(), 150);

        JButton credit_button = new JButton("Credit");
        credit_button.setEnabled(true);
        credit_button.setPreferredSize(new Dimension(220, 50));
        credit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(credit_button);
        credit_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    credit(null);
                    frame.dispose();
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 100);

        JButton balanceenquiry_button = new JButton("Balance Enquiry");
        balanceenquiry_button.setEnabled(true);
        balanceenquiry_button.setPreferredSize(new Dimension(220, 50));
        balanceenquiry_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(balanceenquiry_button);
        balanceenquiry_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    balanceEnquiry(null);
                    frame.dispose();
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 100);

        JButton deleteaccount_button = new JButton("Delete Account");
        deleteaccount_button.setEnabled(true);
        deleteaccount_button.setPreferredSize(new Dimension(220, 50));
        deleteaccount_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(deleteaccount_button);
        deleteaccount_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAccountWindow(null);
                frame.dispose();
            }
            
        });

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    public static void mini_Statement_Module_Window_I(Runnable callback) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame("Welcome To ATM-Mini-Statement Facility");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        // panel.setBorder(BorderFactory.createTitledBorder("Input Panel"));

        vSpace(panel, new JTextPane(), 15);
        
        JTextPane heading = new JTextPane();
        heading.setContentType("text/html");
        heading.setEditable(false);
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>MINI-STATEMENT FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, heading, 15);
        
        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 30);
        
        // --------------------------------------------------------------------------------
        JTextPane balanceDisplayLabel = new JTextPane();
        balanceDisplayLabel.setContentType("text/html");
        balanceDisplayLabel.setEditable(false);
        balanceDisplayLabel.setText("<html><div style='margin-left: 0px; font-size: 30px;'><strong>Enter <i> From Date </i> and <i> Till Date </i></strong></div></html>");
        panel.add(balanceDisplayLabel);

        vSpace(panel, new JTextPane(), 75);

        JTextPane miniStatementFromAndToLabel = new JTextPane();
        miniStatementFromAndToLabel.setContentType("text/html");
        miniStatementFromAndToLabel.setEditable(false);
        miniStatementFromAndToLabel.setText("<html><div style='width: 1160px; margin-left: 0px; font-size: 20px;'><strong>"+new MoneyManager().retNBSP(75)+"From Date"+new MoneyManager().retNBSP(48)+"Till Date</strong></div></html>");
        panel.add(miniStatementFromAndToLabel);

        vSpace(panel, new JTextPane(), 10);

        try {   
            mff = new MaskFormatter("## / ## / ####");
            from = new JFormattedTextField(mff);
            from.setEnabled(true);
            from.setPreferredSize(new Dimension(200, 35));
            from.setMargin(new Insets(0, 45, 0, 0));
            from.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
            from.getDocument().addDocumentListener(new DocumentListener() {
                @Override
            public void insertUpdate(DocumentEvent e) {
                
                if(from.getText().trim().replaceAll("\\s", "").length()==10 && to.getText().trim().replaceAll("\\s", "").length()==10) {
                    showResults.setEnabled(true);
                } else if(from.getText().trim().replaceAll("\\s", "").length()==10) {
                    to.requestFocusInWindow();
                } else {
                    showResults.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        panel.add(from);

        shiftByPixel(panel, new JTextPane(), -10);

        try {   
            mfg = new MaskFormatter("## / ## / ####");
            to = new JFormattedTextField(mfg);
            to.setEnabled(true);
            to.setPreferredSize(new Dimension(200, 35));
            to.setMargin(new Insets(0, 45, 0, 0));
            to.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
            to.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(from.getText().trim().replaceAll("\\s", "").length()==10 && to.getText().trim().replaceAll("\\s", "").length()==10) {
                        showResults.setEnabled(true);
                    } else if(to.getText().trim().replaceAll("\\s", "").length()==10) {
                        // from.requestFocusInWindow();
                    } else {
                        showResults.setEnabled(false);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {}

                @Override
                public void changedUpdate(DocumentEvent e) {}
        });
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        panel.add(to);
    
        vSpace(panel, new JTextPane(), 20);
        
        showResults = new JButton("Show Results 🔍");
        showResults.setPreferredSize(new Dimension(611, 45));
        showResults.setEnabled(false);
        showResults.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        showResults.setVisible(true);
        showResults.setToolTipText("Click to View Mini-Statement Between the Selected Dates");
        showResults.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mini_Statement_Module_Window_II(null);
                    frame.dispose();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(showResults);
        
        vSpace(panel, new JTextPane(), 5);

        JButton backtoMainMenu = new JButton("< Back");
        backtoMainMenu.setEnabled(true);
        backtoMainMenu.setVisible(true);
        backtoMainMenu.setPreferredSize(new Dimension(611, 45));
        backtoMainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backtoMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
        });
        panel.add(backtoMainMenu);

        vSpace(panel, new JTextPane(), 10);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        from.requestFocusInWindow();
        frame.setVisible(true);
    }

    public static void mini_Statement_Module_Window_II(Runnable callback) throws ClassNotFoundException, SQLException {
        JFrame frame = new JFrame("Money Manager : Mini-Statement Facility");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);

        vSpace(panel, new JTextPane(), 15);
        
        JTextPane heading = new JTextPane();
        heading.setContentType("text/html");
        heading.setEditable(false);
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>MINI-STATEMENT FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, heading, 15);
        
        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 30);
        
        // --------------------------------------------------------------------------------
        JTextPane balanceDisplayLabel = new JTextPane();
        balanceDisplayLabel.setContentType("text/html");
        balanceDisplayLabel.setEditable(false);
        balanceDisplayLabel.setText("<html><div style='margin-left: 0px; font-size: 30px;'><strong>Enter <i> From Date </i> and <i> Till Date </i></strong></div></html>");
        panel.add(balanceDisplayLabel);

        vSpace(panel, new JTextPane(), 20);
    
        List<String> transactionNumber = new ArrayList<>();
        List<String> transactionDate = new ArrayList<>();
        List<String> transactionTime = new ArrayList<>();
        List<String> transactionMode = new ArrayList<>();
        List<Long> transactionAmount = new ArrayList<>();

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/saving";
        String username = "IshikaYadav";
        String password = "Ishika@mysql";
        Connection con = DriverManager.getConnection(url, username, password);

        String query = "select * from record where `User ID` = '"+cu.fetchUserid()+"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        final ResultSet[] resultSetWrapper = {rs};

        LocalDate fromDate = LocalDate.parse(from.getText().trim().replaceAll("\\s", ""), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate toDate = LocalDate.parse(to.getText().trim().replaceAll("\\s", ""), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        // LocalDate transactionDate = LocalDate.parse("02/03/2024", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        if(fromDate.isAfter(toDate)) {
            setGlassPaneView(frame, true);
            JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:Red;'>"+"Entered Date Seems Logically Invalid.<br />Make Sure Your Enter The Range Correctly.</div></html>", "Invalid Date Entered", JOptionPane.WARNING_MESSAGE);
            from.setText("");
            to.setText("");
            setGlassPaneView(frame, false);
            from.requestFocusInWindow();
        }
        else {
            showResults.setVisible(false);
            from.setVisible(false);
            to.setVisible(false);
            balanceDisplayLabel.setVisible(false);
            // Clear existing data from lists
            transactionNumber.clear();
            transactionDate.clear();
            transactionTime.clear();
            transactionMode.clear();
            transactionAmount.clear();
            try {
                while(resultSetWrapper[0].next()) {
                    if((LocalDate.parse(resultSetWrapper[0].getString("Transaction Date").trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).isAfter(fromDate) || LocalDate.parse(resultSetWrapper[0].getString("Transaction Date").trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).isEqual(fromDate)) && (LocalDate.parse(resultSetWrapper[0].getString("Transaction Date").trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).isBefore(toDate) || LocalDate.parse(resultSetWrapper[0].getString("Transaction Date").trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy")).isEqual(toDate))) {
                        transactionNumber.add(resultSetWrapper[0].getString("Transaction Number"));
                        transactionDate.add(resultSetWrapper[0].getString("Transaction Date"));
                        transactionTime.add(resultSetWrapper[0].getString("Transaction Time"));
                        transactionAmount.add(resultSetWrapper[0].getLong("Transaction Amount"));
                        transactionMode.add(resultSetWrapper[0].getString("Transaction Mode"));
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } 
        }
        

        vSpace(panel, new JTextPane(), 10);

        String[] columnNames = {"Serial Number", "User ID", "Transaction Number", "Transaction Date", "Transaction Time", "Transaction mode", "Transaction Ammount"};
        Object[][] rowData = new Object[transactionNumber.size()][columnNames.length];

        
        transactionTable = new JTable(rowData, columnNames);
        transactionTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        transactionTable.getColumnModel().getColumn(1).setPreferredWidth(170);
        transactionTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        transactionTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        transactionTable.getColumnModel().getColumn(4).setPreferredWidth(170);
        transactionTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        transactionTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        transactionTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(Color.WHITE); // Even rows
                } else {
                    c.setBackground(new Color(227, 235, 247)); // Odd rows
                }
                return c;
            }
        });

        transactionTable.setFont(new Font(transactionTable.getFont().getFontName(), transactionTable.getFont().getStyle(), 15));
        transactionScrollPane = new JScrollPane(transactionTable);
        transactionScrollPane.getViewport().setPreferredSize(new Dimension(1170, 400));
        panel.add(transactionScrollPane);
        panel.revalidate(); // Revalidate the panel to ensure proper layout
        panel.repaint(); // Repaint the panel to reflect changes
        vSpace(panel, new JTextPane(), 7);

        JButton backtoMainMenu = new JButton("< Back");
        backtoMainMenu.setEnabled(true);
        backtoMainMenu.setPreferredSize(new Dimension(200, 35));
        backtoMainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backtoMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
        });
        panel.add(backtoMainMenu);
        shiftByPixel(panel, new JTextPane(), 25);
        reSearch = new JButton("Search Again 🔍");
        reSearch.setEnabled(true);
        reSearch.setPreferredSize(new Dimension(200, 35));
        reSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    mini_Statement_Module_Window_I(null);
                    frame.dispose();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        panel.add(reSearch);

        vSpace(panel, new JTextPane(), 7);

        JTextPane footer = new JTextPane();
        footer.setContentType("text/html");
        footer.setEditable(false);
        footer.setText("<html><div style='margin-left: 0px; width: 1256px; height: 20px; background-color: #000; border: 1px solid black; padding: 3px;'></div></html>");
        panel.add(footer);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        if(transactionNumber.isEmpty()) {
            setGlassPaneView(frame, true);
            showLoadingScreen("Fetching Data...", 3000);
            JOptionPane.showMessageDialog(null, "<html><div style='font-size:12pt; color:Black;'>"+"No Transactions in Between <b>"+fromDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"</b> and <b>"+toDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+"</b> is Found !</div></html>", "No Transaction", JOptionPane.INFORMATION_MESSAGE);
            setGlassPaneView(frame, false);
            loggedInPage(null);
            frame.dispose();
        } else {
            setGlassPaneView(frame, true);
            showLoadingScreen("Fetching Data...", 3000);
            
            for (int i = 0; i < transactionNumber.size(); i++) {
                rowData[i][0] = i + 1; // Serial Number
                rowData[i][1] = cu.fetchUserid();
                rowData[i][2] = transactionNumber.get(i); // Withdraw/Deposit
                rowData[i][3] = transactionDate.get(i); // Transaction Date
                rowData[i][4] = transactionTime.get(i); // Transaction Time
                rowData[i][5] = transactionMode.get(i); // Transaction Status
                rowData[i][6] = transactionAmount.get(i); // Transaction Status
            }
            setGlassPaneView(frame, false);
        }
    }

    public static void deleteAccountWindow(Runnable callback) {
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/saving";
                String username = "IshikaYadav";
                String password = "Ishika@mysql";
                Connection con = DriverManager.getConnection(url, username, password);
                String query = "select * from userdata where `User ID` = '"+cu.fetchUserid()+"'";
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    cu.setUserceb(rs.getLong("Current Evaluated Balance")); 
                }
                con.close();
            } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
            }
        }
        JFrame frame = new JFrame("Money Manager : Delete Account");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);

        vSpace(panel, new JTextPane(), 15);
        
        JTextPane heading = new JTextPane();
        heading.setContentType("text/html");
        heading.setEditable(false);
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>ACCOUNT DELETION FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, heading, 15);
        
        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 95);
        
        JTextPane password_text = new JTextPane();
        password_text.setContentType("text/html");
        password_text.setEditable(false);
        password_text.setText("<html><div style='margin-left: 0px; font-size: 30px;'><strong>ENTER YOUR PASSWORD FIRST</strong></div></html>");
        panel.add(password_text);

        vSpace(panel, new JTextPane(), 20);

        JTextField password = new JTextField();
        password.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        password.setPreferredSize(new Dimension(500, 80));
        password.setFont(new Font("Arial", Font.BOLD, 26));
        password.setMargin(new Insets(0, 60, 0, 0));
        panel.add(password);
        password.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(password.getText().trim().replaceAll("\\s", "").length()>=8) {
                    delete_button.setEnabled(true);
                } else {
                    delete_button.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               if(password.getText().trim().replaceAll("\\s", "").length()>=8) {
                delete_button.setEnabled(true);
               }  else {
                delete_button.setEnabled(false);
               }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               if(password.getText().trim().replaceAll("\\s", "").length()>=8) {
                delete_button.setEnabled(true);
               }  else {
                delete_button.setEnabled(false);
               }
            }
        });
        
        vSpace(panel, new JTextPane(), 20);

        JButton back_to_main_menu = new JButton("< Back");
        back_to_main_menu.setEnabled(true);
        back_to_main_menu.setPreferredSize(new Dimension(200, 45));
        back_to_main_menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back_to_main_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
        });
        panel.add(back_to_main_menu);

        shiftByPixel(panel, new JTextPane(), -127);
        
        delete_button = new JButton("DELETE !");
        delete_button.setEnabled(false);
        delete_button.setPreferredSize(new Dimension(200, 45));
        delete_button.setBackground(new Color(199, 54, 59));
        delete_button.setForeground(new Color(255, 255, 255));
        delete_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGlassPaneView(frame, true);
                showLoadingScreen("Verifying...", 1500);
                setGlassPaneView(frame, false);
                if(password.getText().trim().replaceAll("\\s", "").equals(cu.fetchUserpassword())) {
                    if(cu.fetchUserceb()>0) {
                        JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:Black;'>"+"<b>You have ₹ "+cu.fetchUserceb()+" in your account please debit this amount to delete the account</b></div></html>", "Alert", JOptionPane.WARNING_MESSAGE);
                        loggedInPage(null);
                        frame.dispose();
                        return;
                    }
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to DELETE your account...?", "Confiramtion", JOptionPane.YES_NO_OPTION);
                    if(choice==JOptionPane.YES_OPTION) {
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        String url = "jdbc:mysql://localhost:3306/saving";
                        String username = "IshikaYadav";
                        String password = "Ishika@mysql";
                        try {
                            Connection con = DriverManager.getConnection(url, username, password);
                            String query = "delete from userdata where `User ID` = '"+cu.fetchUserid()+"'";
                            Statement st  = con.createStatement();
                            st.executeUpdate(query);
                            con.close();
                            JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:Red;'>"+"<b>Sad To See You Go :-(</b></div></html>", "BYE BYE", JOptionPane.WARNING_MESSAGE);
                            new MoneyManager().loginPage(null);
                            frame.dispose();
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        loggedInPage(null);
                        frame.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:Red;'>"+"<b>Verification Failed : Incorrect Password</b></div></html>", "Failure", JOptionPane.WARNING_MESSAGE);
                    password.setText("");
                    password.requestFocusInWindow();
                }
            }
        });
        panel.add(delete_button);

        vSpace(panel, new JTextPane(), 20);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        password.requestFocusInWindow();
        frame.setVisible(true);
    }

    public static void changePasswordWindow1(Runnable callback) {
        JFrame frame = new JFrame("Money Manager : Change Password");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);

        vSpace(panel, new JTextPane(), 15);
        
        JTextPane heading = new JTextPane();
        heading.setContentType("text/html");
        heading.setEditable(false);
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>PASSWORD CHANGING FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, heading, 15);
        
        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 95);
        
        JTextPane enterATMPinText = new JTextPane();
        enterATMPinText.setContentType("text/html");
        enterATMPinText.setEditable(false);
        enterATMPinText.setText("<html><div style='margin-left: 0px; font-size: 30px;'><strong>ENTER OLD PASSWORD</strong></div></html>");
        panel.add(enterATMPinText);

        vSpace(panel, new JTextPane(), 20);

        JTextField oldpassword = new JTextField();
        oldpassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        oldpassword.setPreferredSize(new Dimension(500, 80));
        oldpassword.setFont(new Font("Arial", Font.BOLD, 26));
        oldpassword.setMargin(new Insets(0, 60, 0, 0));
        panel.add(oldpassword);
        oldpassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(oldpassword.getText().trim().replaceAll("\\s", "").length()>=8) {
                    next_button.setEnabled(true);
                } else {
                    next_button.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               if(oldpassword.getText().trim().replaceAll("\\s", "").length()>=8) {
                    next_button.setEnabled(true);
               }  else {
                    next_button.setEnabled(false);
               }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               if(oldpassword.getText().trim().replaceAll("\\s", "").length()>=8) {
                    next_button.setEnabled(true);
               }  else {
                    next_button.setEnabled(false);
               }
            }
        });
        
        vSpace(panel, new JTextPane(), 20);

        JButton back_to_main_menu = new JButton("< Back");
        back_to_main_menu.setEnabled(true);
        back_to_main_menu.setPreferredSize(new Dimension(200, 45));
        back_to_main_menu.setForeground(new Color(249, 54, 59));
        back_to_main_menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back_to_main_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
        });
        panel.add(back_to_main_menu);

        shiftByPixel(panel, new JTextPane(), -127);
        
        next_button = new JButton("Proceed >");
        next_button.setEnabled(false);
        next_button.setPreferredSize(new Dimension(200, 45));
        next_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        next_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGlassPaneView(frame, true);
                showLoadingScreen("Verifying...", 1500);
                setGlassPaneView(frame, false);
                if(oldpassword.getText().trim().replaceAll("\\s", "").equals(cu.fetchUserpassword())) {
                    changePasswordWindow2(null);
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:Red;'>"+"<b>Verification Failed : Incorrect Password</b></div></html>", "Failure", JOptionPane.WARNING_MESSAGE);
                    oldpassword.setText("");
                    oldpassword.requestFocusInWindow();
                }
            }
        });
        panel.add(next_button);

        vSpace(panel, new JTextPane(), 20);

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        oldpassword.requestFocusInWindow();
        frame.setVisible(true); 
    }

    public static void changePasswordWindow2(Runnable callback) {
        JFrame frame = new JFrame("Money Manager : Change Password");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);

        vSpace(panel, new JTextPane(), 15);
        
        JTextPane heading = new JTextPane();
        heading.setContentType("text/html");
        heading.setEditable(false);
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>PASSWORD CHANGING FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, heading, 15);
        
        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 40);
        
        JTextPane enterNewPasswordText = new JTextPane();
        enterNewPasswordText.setContentType("text/html");
        enterNewPasswordText.setEditable(false);
        enterNewPasswordText.setText("<html><div style='margin-left: 0px; font-size: 20px;'><strong>Enter New Password</strong></div></html>");
        panel.add(enterNewPasswordText);

        vSpace(panel, new JTextPane(), 20);

        newpassword = new JTextField();
        newpassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        newpassword.setPreferredSize(new Dimension(500, 80));
        newpassword.setFont(new Font("Arial", Font.BOLD, 26));
        newpassword.setMargin(new Insets(0, 60, 0, 0));
        panel.add(newpassword);
        newpassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(newpassword.getText().trim().replaceAll("\\s", "").length()>=8 && newpassword.getText().trim().replaceAll("\\s", "").equals(cnfpassword.getText().trim().replaceAll("\\s", ""))) {
                    next_button.setEnabled(true);
                } else {
                    next_button.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               if(newpassword.getText().trim().replaceAll("\\s", "").length()>=8 && newpassword.getText().trim().replaceAll("\\s", "").equals(cnfpassword.getText().trim().replaceAll("\\s", ""))) {
                    next_button.setEnabled(true);
               }  else {
                    next_button.setEnabled(false);
               }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               if(newpassword.getText().trim().replaceAll("\\s", "").length()>=8 && newpassword.getText().trim().replaceAll("\\s", "").equals(cnfpassword.getText().trim().replaceAll("\\s", ""))) {
                    next_button.setEnabled(true);
               }  else {
                    next_button.setEnabled(false);
               }
            }
        });
        vSpace(panel, new JTextPane(), 20);

        JTextPane confirmNewPasswordText = new JTextPane();
        confirmNewPasswordText.setContentType("text/html");
        confirmNewPasswordText.setEditable(false);
        confirmNewPasswordText.setText("<html><div style='margin-left: 0px; font-size: 20px;'><strong>Confirm New Password</strong></div></html>");
        panel.add(confirmNewPasswordText);

        vSpace(panel, new JTextPane(), 20);

        cnfpassword = new JTextField();
        cnfpassword.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        cnfpassword.setPreferredSize(new Dimension(500, 80));
        cnfpassword.setFont(new Font("Arial", Font.BOLD, 26));
        cnfpassword.setMargin(new Insets(0, 60, 0, 0));
        panel.add(cnfpassword);
        cnfpassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(cnfpassword.getText().trim().replaceAll("\\s", "").length()>=8 && cnfpassword.getText().trim().replaceAll("\\s", "").equals(newpassword.getText().trim().replaceAll("\\s", ""))) {
                    next_button.setEnabled(true);
                } else {
                    next_button.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
               if(cnfpassword.getText().trim().replaceAll("\\s", "").length()>=8 && cnfpassword.getText().trim().replaceAll("\\s", "").equals(newpassword.getText().trim().replaceAll("\\s", ""))) {
                    next_button.setEnabled(true);
               }  else {
                    next_button.setEnabled(false);
               }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
               if(cnfpassword.getText().trim().replaceAll("\\s", "").length()>=8 && cnfpassword.getText().trim().replaceAll("\\s", "").equals(newpassword.getText().trim().replaceAll("\\s", ""))) {
                    next_button.setEnabled(true);
               }  else {
                    next_button.setEnabled(false);
               }
            }
        });
        vSpace(panel, new JTextPane(), 20);
        
        JButton backtoMainMenu = new JButton("< Go Back To Main Menu");
        backtoMainMenu.setEnabled(true);
        backtoMainMenu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backtoMainMenu.setPreferredSize(new Dimension(200, 45));
        backtoMainMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
        });
        panel.add(backtoMainMenu);

        shiftByPixel(panel, new JTextPane(), -127);

        next_button = new JButton("Update >");
        next_button.setEnabled(false);
        next_button.setPreferredSize(new Dimension(200, 45));
        next_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(next_button);
        next_button.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userGivenNEWPIN = cnfpassword.getText().replaceAll("\\s", "");
                String userOldPIN = cu.fetchUserpassword();
                try {
                    if(userGivenNEWPIN.equals(userOldPIN)!=true) {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        String url = "jdbc:mysql://localhost:3306/saving";
                        String username = "IshikaYadav";
                        String password = "Ishika@mysql";
                        Connection con = DriverManager.getConnection(url, username, password);
                        String query = "update userdata set `User Password`='"+userGivenNEWPIN+"' where `User ID`='"+cu.fetchUserid()+"'";
                        Statement st = con.createStatement();
                        st.executeUpdate(query);
                        con.close();
                        setGlassPaneView(frame, true);
                        showLoadingScreen("Processing...", 3000);
                        JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color: Green;'>"+"<b>Password Changed Successfully</b></div></html>", "Success", JOptionPane.INFORMATION_MESSAGE);
                        setGlassPaneView(frame, false);
                        loggedInPage(null);
                        frame.dispose();
                    } else {
                        setGlassPaneView(frame, true);
                        showLoadingScreen("Processing...", 3000);
                        JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color: Red;'>"+"<b>New Password Cannot Be Same as Old Password</b></div></html>", "Failure", JOptionPane.WARNING_MESSAGE);
                        setGlassPaneView(frame, false);
                        newpassword.setText("");
                        cnfpassword.setText("");
                    }
                } catch (SQLException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                
                
            }
        });

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        newpassword.requestFocusInWindow();
        frame.setVisible(true);
        
    }
    public static void debit(Runnable callback) {
        JFrame frame = new JFrame("Money Manager : Registration Portal");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        
        vSpace(panel, new JTextPane(), 15 );

        JTextPane heading = new JTextPane();
        heading.setEditable(false);
        heading.setContentType("text/html");
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>DEBIT FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, new JTextPane(), 50);

        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 95);

        JTextPane eat1 = new JTextPane();
        eat1.setContentType("text/html");
        eat1.setEditable(false);
        eat1.setText("<html><div style='margin-left: 0px; font-size: 30px;'><strong>ENTER AMOUNT TO BE DEBITED</strong></div></html>");
        panel.add(eat1);

        vSpace(panel, new JTextPane(), 10);

        JTextPane eat2 = new JTextPane();
        eat2.setContentType("text/html");
        eat2.setEditable(false);
        eat2.setText("<html><div style='margin-left: 0px; font-size: 15px;'><i>(Accepted Denominations 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000)</i></div></html>");
        panel.add(eat2);

        vSpace(panel, new JTextPane(), 10);

        JTextField numericField =  new UserEntryValidator().createNumericTextField();
        numericField.setPreferredSize(new Dimension(500, 80));
        numericField.setFont(new Font("Arial", Font.BOLD, 26));
        numericField.setMargin(new Insets(0, 100, 0, 30));
        numericField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        panel.add(numericField);
        numericField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(numericField.getText().trim().replaceAll("\\s", " ").length()>=1 && (Long.parseLong(numericField.getText().trim().replaceAll("\\s", " "))>0)) {
                    debit_button.setEnabled(true);
                } else {
                    debit_button.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(numericField.getText().trim().replaceAll("\\s", " ").length()>=1 && (Long.parseLong(numericField.getText().trim().replaceAll("\\s", " "))>0)) {
                    debit_button.setEnabled(true);
                } else {
                    debit_button.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(numericField.getText().trim().replaceAll("\\s", " ").length()>=1 && (Long.parseLong(numericField.getText().trim().replaceAll("\\s", " "))>0)) {
                    debit_button.setEnabled(true);
                } else {
                    debit_button.setEnabled(false);
                }
            }
            
        });

        vSpace(panel, new JTextPane(), 20);

        JButton back_to_main_menu = new JButton("< Back");
        back_to_main_menu.setEnabled(true);
        back_to_main_menu.setPreferredSize(new Dimension(200, 45));
        back_to_main_menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(back_to_main_menu);
        back_to_main_menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
            
        });

        shiftByPixel(panel, new JTextPane(), -127);

        debit_button = new JButton("Debit");
        debit_button.setEnabled(false);
        debit_button.setPreferredSize(new Dimension(200, 45));
        debit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(debit_button);
        debit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {         
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/saving";
                    String username = "IshikaYadav";
                    String password = "Ishika@mysql";
                    Connection con = DriverManager.getConnection(url, username, password);
                    String query = "select * from userdata where `User ID` = '"+cu.fetchUserid()+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        cu.setUserceb(rs.getLong("Current Evaluated Balance"));

                        
                        
                    }
                    if(Long.parseLong(numericField.getText().trim().replaceAll("\\s", ""))<=cu.fetchUserceb()) {
                        query = "update `userdata` set `Current Evaluated Balance` = "+(cu.fetchUserceb()-Long.parseLong(numericField.getText().trim().replaceAll("\\s", "")))+" where `User ID` = '"+cu.fetchUserid()+"'";
                        st = con.createStatement();
                        st.executeUpdate(query);

                        // NOW TRANSACTION HISTORY 

                        String countQuery = "select count(*) as maxCurrentRow from record";
                        Statement countStatement = con.createStatement();
                        ResultSet countResultSet = countStatement.executeQuery(countQuery);
                        int maxCurrentRow = 0;
                        if (countResultSet.next()) {
                            maxCurrentRow = countResultSet.getInt("maxCurrentRow");
                        }

                        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        String attributes = "`User ID`, `Serial Number`, `Transaction Number`, `Transaction Date`, `Transaction Time`, `Transaction Mode`, `Transaction Amount`";
                        String values = "?,?,?,?,?,?,?";
                        query = "insert into record("+attributes+") values ("+values+")";
                        PreparedStatement ps = con.prepareStatement(query);
                        ps.setString(1, cu.fetchUserid());
                        ps.setInt(2, maxCurrentRow+1);
                        ps.setString(3, provideTransactionnumber());
                        ps.setString(4, date);
                        ps.setString(5, time);
                        ps.setString(6, "Debit");
                        ps.setLong(7, Long.parseLong(numericField.getText().trim().replaceAll("\\s", "")));
                        ps.executeUpdate();
                        
                        con.close();

                        setGlassPaneView(frame, true);
                        showLoadingScreen("Payment Processing...", 1500);
                        showLoadingScreen("<html><div style='font-size:14pt; color:Green;'>"+"<b>Debited Successfully</b></div></html>", 1000);
                        setGlassPaneView(frame, false);
                        loggedInPage(null);
                        frame.dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, "<html><div style='font-size:14pt; color:Red;'>"+"<b>Debit Failed : Insufficient Balance</b></div></html>", "Failure", JOptionPane.WARNING_MESSAGE);
                        loggedInPage(null);
                        frame.dispose();
                    }
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
            
        });

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void credit(Runnable callback) {
        JFrame frame = new JFrame("Money Manager : Registration Portal");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        
        vSpace(panel, new JTextPane(), 15 );

        JTextPane heading = new JTextPane();
        heading.setEditable(false);
        heading.setContentType("text/html");
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>CREDIT FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, new JTextPane(), 50);

        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 95);

        JTextPane eat1 = new JTextPane();
        eat1.setContentType("text/html");
        eat1.setEditable(false);
        eat1.setText("<html><div style='margin-left: 0px; font-size: 30px;'><strong>ENTER AMOUNT TO BE CREDITED</strong></div></html>");
        panel.add(eat1);

        vSpace(panel, new JTextPane(), 10);

        JTextPane eat2 = new JTextPane();
        eat2.setContentType("text/html");
        eat2.setEditable(false);
        eat2.setText("<html><div style='margin-left: 0px; font-size: 15px;'><i>(Accepted Denominations 1, 2, 5, 10, 20, 50, 100, 200, 500, 2000)</i></div></html>");
        panel.add(eat2);

        vSpace(panel, new JTextPane(), 10);

        JTextField numericField =  new UserEntryValidator().createNumericTextField();
        numericField.setPreferredSize(new Dimension(500, 80));
        numericField.setFont(new Font("Arial", Font.BOLD, 26));
        numericField.setMargin(new Insets(0, 100, 0, 30));
        numericField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        panel.add(numericField);
        numericField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                if(numericField.getText().trim().replaceAll("\\s", " ").length()>=1 && (Long.parseLong(numericField.getText().trim().replaceAll("\\s", " "))>0)) {
                    credit_button.setEnabled(true);
                } else {
                    credit_button.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(numericField.getText().trim().replaceAll("\\s", " ").length()>=1 && (Long.parseLong(numericField.getText().trim().replaceAll("\\s", " "))>0)) {
                    credit_button.setEnabled(true);
                } else {
                    credit_button.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(numericField.getText().trim().replaceAll("\\s", " ").length()>=1 && (Long.parseLong(numericField.getText().trim().replaceAll("\\s", " "))>0)) {
                    credit_button.setEnabled(true);
                } else {
                    credit_button.setEnabled(false);
                }
            }
            
        });

        vSpace(panel, new JTextPane(), 20);

        JButton back_to_main_menu = new JButton("< Back");
        back_to_main_menu.setEnabled(true);
        back_to_main_menu.setPreferredSize(new Dimension(200, 45));
        back_to_main_menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(back_to_main_menu);
        back_to_main_menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
            
        });

        shiftByPixel(panel, new JTextPane(), -127);

        credit_button = new JButton("Credit");
        credit_button.setEnabled(false);
        credit_button.setPreferredSize(new Dimension(200, 45));
        credit_button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(credit_button);
        credit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/saving";
                    String username = "IshikaYadav";
                    String password = "Ishika@mysql";
                    Connection con = DriverManager.getConnection(url, username, password);
                    String query = "select * from userdata where `User ID` = '"+cu.fetchUserid()+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        cu.setUserceb(rs.getLong("Current Evaluated Balance"));

                        
                        
                    }
                    query = "update `userdata` set `Current Evaluated Balance` = "+(cu.fetchUserceb()+Long.parseLong(numericField.getText().trim().replaceAll("\\s", "")))+" where `User ID` = '"+cu.fetchUserid()+"'";
                    st = con.createStatement();
                    st.executeUpdate(query);

                    // NOW TRANSACTION HISTORY 

                    String countQuery = "select count(*) as maxCurrentRow from record";
                    Statement countStatement = con.createStatement();
                    ResultSet countResultSet = countStatement.executeQuery(countQuery);
                    int maxCurrentRow = 0;
                    if (countResultSet.next()) {
                        maxCurrentRow = countResultSet.getInt("maxCurrentRow");
                    }
                    String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                    String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    String attributes = "`User ID`, `Serial Number`, `Transaction Number`, `Transaction Date`, `Transaction Time`, `Transaction Mode`, `Transaction Amount`";
                    String values = "?,?,?,?,?,?,?";
                    query = "insert into record("+attributes+") values ("+values+")";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, cu.fetchUserid());
                    ps.setInt(2, maxCurrentRow+1);
                    ps.setString(3, provideTransactionnumber());
                    ps.setString(4, date);
                    ps.setString(5, time);
                    ps.setString(6, "Credit");
                    ps.setLong(7, Long.parseLong(numericField.getText().trim().replaceAll("\\s", "")));
                    ps.executeUpdate();
                    
                    con.close();
                    setGlassPaneView(frame, true);
                    showLoadingScreen("Payment Processing...", 1500);
                    showLoadingScreen("<html><div style='font-size:14pt; color:Green;'>"+"<b>Credited Successfully</b></div></html>", 1000);
                    setGlassPaneView(frame, false);
                    loggedInPage(null);
                    frame.dispose();
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
            
        });

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    public static void balanceEnquiry(Runnable callback) {
        JFrame frame = new JFrame("Money Manager : Balance Enquiry Portal");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        
        vSpace(panel, new JTextPane(), 15 );

        JTextPane heading = new JTextPane();
        heading.setEditable(false);
        heading.setContentType("text/html");
        heading.setText("<html><div style='margin-left: 0px; font-size: 40px;'><strong>BALANCE ENQUIRY FACILITY</strong></div></html>");
        panel.add(heading);

        vSpace(panel, new JTextPane(), 50);

        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setText("<html><div style='margin-left: 0px; width: 1180px; background-color: #adafb3; border: 1px solid black; padding: 5px; '>"+new MoneyManager().retNBSP(330)+"<font size=5 color='black'>User Id : <strong>"+cu.fetchUserid()+"</strong></font></div></html>");
        panel.add(bar);

        vSpace(panel, new JTextPane(), 95);

        JTextPane eat1 = new JTextPane();
        eat1.setContentType("text/html");
        eat1.setEditable(false);
        eat1.setText("<html><div style='margin-left: 0px; font-size: 30px;'><strong>YOUR CURRENT BALANCE</strong></div></html>");
        panel.add(eat1);

        vSpace(panel, new JTextPane(), 20);


        JTextField numericField =  new JTextField();
        numericField.setPreferredSize(new Dimension(500, 80));
        numericField.setFont(new Font("Arial", Font.BOLD, 26));
        numericField.setMargin(new Insets(0, 100, 0, 30));
        numericField.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
        panel.add(numericField);
        
        vSpace(panel, new JTextPane(), 20);

        JButton back_to_main_menu = new JButton("< Back");
        back_to_main_menu.setEnabled(true);
        back_to_main_menu.setPreferredSize(new Dimension(500, 50));
        back_to_main_menu.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(back_to_main_menu);
        back_to_main_menu.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loggedInPage(null);
                frame.dispose();
            }
            
        });

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        setGlassPaneView(frame, true);
        showLoadingScreen("Fetching...", 2000);
        setGlassPaneView(frame, false);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/saving";
            String username = "IshikaYadav";
            String password = "Ishika@mysql";
            Connection con = DriverManager.getConnection(url, username, password);
            String query = "select * from userdata where `User ID` = '"+cu.fetchUserid()+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                numericField.setText(new String("INR ")+String.valueOf(rs.getLong("Current Evaluated Balance")));
                
            }
            numericField.setEditable(false);
            con.close();
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
    }
    public void registrationPortal(Runnable callback) {
/*
    `User ID` varchar(6) primary key,
    `User Password` varchar(6) not null,
    `Honrefic`
    `User FName` varchar(20) not null,
    `User MName` varchar(20) not null,
    `User LName` varchar(20) not null,
    `Current Evaluated Balance` bigint not null
*/
        JFrame frame = new JFrame("Money Manager : Registration Portal");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);
        
        vSpace(panel, new JTextPane(), 15 );

        JTextPane heading = new JTextPane();
        heading.setEditable(false);
        heading.setContentType("text/html");
        heading.setText("<html><center><div style='font-size: 40px;'>WELCOME TO REGISTRATION PORTAL</div></center></html>");
        panel.add(heading);

        vSpace(panel, new JTextPane(), 50);

        JTextPane bar = new JTextPane();
        bar.setContentType("text/html");
        bar.setEditable(false);
        bar.setBackground(Color.decode("#adafb3"));
        bar.setText(new MoneyManager().verticalSpace(20));
        bar.setEnabled(false); // Added later On
        panel.add(bar);

        vSpace(panel, new JTextPane(), 60);

        JTextPane label_honorific = new JTextPane();
        label_honorific.setContentType("text/html");
        label_honorific.setEditable(false);
        label_honorific.setText("<html><body><div width = '1536' style = 'font-size: 18px; '>"+retNBSP(20)+"<b>Select Honorific <font color = 'red'>*</font>"+retNBSP(70)+"Aadhar Number <font color = 'red'>*</font>"+retNBSP(70)+"PAN Number <font color = 'red'>*</font></b></div></body></html>");
        panel.add(label_honorific);

        vSpace(panel, new JTextPane(), 15);

        String[] honorificStrings = {"Select Honorific", "Ms", "Mrs", "Mr"};
        honorific_combo_box = new JComboBox<>(honorificStrings);
        honorific_combo_box.setSelectedItem("Select Honorific");
        honorific_combo_box.setPreferredSize(new Dimension(200, 35));
        panel.add(honorific_combo_box);
        honorific_combo_box.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 150);

        try {
            jftfa = new JFormattedTextField(new MaskFormatter("#### #### ####"));
            jftfa.setPreferredSize(new Dimension(200, 35));
            jftfa.setMargin(new Insets(0, 40, 0, 0));
            jftfa.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
            jftfa.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                    Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                    Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                    Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                    Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                    nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
                    System.out.println(jftfa.getText().replaceAll("\\s", "").length());
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                    Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                    Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                    Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                    Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                    nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    
                }
                
            });
        } catch(ParseException e) {
            e.printStackTrace();
        }
        panel.add(jftfa);

        shiftByPixel(panel, new JTextPane(), 150);

        try {
            jftfb = new JFormattedTextField(new MaskFormatter("UUUUU####U"));
            jftfb.setPreferredSize(new Dimension(200, 35));
            jftfb.setMargin(new Insets(0, 40, 0, 0));
            jftfb.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
            jftfb.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                    Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                    Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                    Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                    Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                    nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
                    System.out.println(jftfb.getText().replaceAll("\\s", "").length());

                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                    Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                    Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                    Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                    Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                    nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    
                }
                
            });
        } catch(ParseException e) {
            e.printStackTrace();
        }
        panel.add(jftfb);

        vSpace(panel, new JTextPane(), 50);

        JTextPane label_name = new JTextPane();
        label_name.setContentType("text/html");
        label_name.setEditable(false);
        label_name.setText("<html><body><div style = 'font-size: 18px;'><b>First Name <font color = 'red'>*</font>"+retNBSP(80)+"Middle Name"+retNBSP(80)+"Last Name <font color = 'red'>*</font></b></div></body></html>");
        panel.add(label_name);


        vSpace(panel, new JTextPane(), 15);


        f_name = new JTextField();
        f_name.setPreferredSize(new Dimension(200, 35));
        f_name.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        f_name.setMargin(new Insets(0,30, 0, 30));
        panel.add(f_name);
        f_name.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 150);

        m_name = new JTextField();
        m_name.setPreferredSize(new Dimension(200, 35));
        m_name.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        m_name.setMargin(new Insets(0,30, 0, 30));
        panel.add(m_name);
        

        shiftByPixel(panel, new JTextPane(), 150);

        l_name = new JTextField();
        l_name.setPreferredSize(new Dimension(200, 35));
        l_name.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        l_name.setMargin(new Insets(0,30, 0, 30));
        panel.add(l_name);
        l_name.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                Boolean flag_1 = !((String) honorific_combo_box.getSelectedItem()).equals("Select Honorific");
                Boolean flag_2 = !(f_name.getText().trim().isEmpty());
                Boolean flag_3 = !(l_name.getText().trim().isEmpty());
                Boolean flag_4 = (jftfa.getText().replaceAll("\\s", "").length()==12);
                Boolean flag_5 = (jftfb.getText().replaceAll("\\s", "").length()==10);
                nextbutton.setEnabled(flag_1 && flag_2 && flag_3 && flag_4 && flag_5);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                
            }
            
        });

        vSpace(panel, new JTextPane(), 75);

        resetbutton = new JButton("Reset");
        resetbutton.setEnabled(true);
        resetbutton.setPreferredSize(new Dimension(300, 40));
        resetbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(resetbutton);
        resetbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                honorific_combo_box.setSelectedIndex(0);
                f_name.setText("");
                m_name.setText("");
                l_name.setText("");
            }
            
        });

        shiftByPixel(panel, new JTextPane(), 530);        

        nextbutton = new JButton("Next >");
        nextbutton.setEnabled(false);
        nextbutton.setPreferredSize(new Dimension(300, 40));
        nextbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(nextbutton);
        nextbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                cu.setUserhonorific((String) honorific_combo_box.getSelectedItem());
                cu.setUseraadhar(jftfa.getText().replaceAll("\\s", ""));
                cu.setUserPAN(jftfb.getText().replaceAll("\\s", ""));
                cu.setUserfname(f_name.getText().trim());
                cu.setUsermname(m_name.getText().trim());
                cu.setUserlname(l_name.getText().trim());
                intakePassword(null);
                frame.dispose();
            }
        });

        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

    }

    public static void intakePassword(Runnable callback) {
        JFrame frame = new JFrame("Money Manager : Password");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);
        frame.setContentPane(panel);

        JTextPane heading = new JTextPane();
        heading.setContentType("text/html");
        heading.setEditable(false);
        heading.setText("<html><center><div style='margin-left: 220px; margin-right: 220px; margin-top: 0; font-size: 35px;'>SECURITY LAYER PANEL</div></center></html>");
        panel.add(heading);
        
        // vSpace(panel, new JTextPane(), 20);
        shiftByPixel(panel, heading, 800);
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        editorPane.setPreferredSize(new Dimension(400, 115));
        editorPane.setEditorKit(new HTMLEditorKit());
        editorPane.setText("<html><li>Password Should Must be At Least of <strong>8 Charcaters</strong></li><br /><li>Password Should Not Be Common as '<strong>12345678</strong>' '<strong>111222333</strong>', '<strong>ABCDEFGH</strong>', '<strong>MyPassword</strong>', etc</li></html>");
        panel.add(editorPane);
        editorPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLineBorder(Color.BLACK, 3)));
            
        JTextPane rowI = new JTextPane();
        rowI.setContentType("text/html");
        rowI.setEditable(false);
        rowI.setText("<html><div style='font-size: 20px; margin-left: 260px; margin-right: 890px;'>Your Auto Generated 'Customer ID' is</div></html>");
        panel.add(rowI);
            
            
        vSpace(panel, new JTextPane(), 10);
            
        JTextField jtf = new JTextField();
        jtf.setMargin(new Insets(0, 30, 0, 30));
        jtf.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        jtf.setPreferredSize(new Dimension(500, 45));
        jtf.setText(generateId(cu.fetchUserPAN(), cu.fetchUseraadhar()));
        jtf.setEditable(false);
        panel.add(jtf);
        
        shiftByPixel(panel, new JTextPane(), 450);
        
        vSpace(panel, new JTextPane(), 25);
        
        JTextPane PasswordLabel = new JTextPane();
        PasswordLabel.setContentType("text/html");
        PasswordLabel.setEditable(false);
        PasswordLabel.setText("<html><div style='font-size: 20px; margin-left: 145px; margin-right: 910px;'>Set Your Password *</div></html>");
        panel.add(PasswordLabel);
        
        
        vSpace(panel, new JTextPane(), 10);
        
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500, 45)); // Set preferred size
        Font font = new Font("Arial", Font.ROMAN_BASELINE, 16);
        passwordField.setFont(font);
        passwordField.setMargin(new Insets(10, 30, 10, 30));
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(new String(passwordField.getPassword()).equals(new String(passwordFieldII.getPassword())) && new String(passwordField.getPassword()).length()>=8) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(new String(passwordField.getPassword()).equals(new String(passwordFieldII.getPassword())) && new String(passwordField.getPassword()).length()>=8) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(new String(passwordField.getPassword()).equals(new String(passwordFieldII.getPassword())) && new String(passwordField.getPassword()).length()>=8) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }
        });
        panel.add(passwordField);
        
        shiftByPixel(panel, new JTextPane(), 450);

        vSpace(panel, new JTextPane(), 25);

        JTextPane PasswordLabelII = new JTextPane();
        PasswordLabelII.setContentType("text/html");
        PasswordLabelII.setEditable(false);
        PasswordLabelII.setText("<html><div style='font-size: 20px; margin-left: 192px; margin-right: 910px;'>Re-Enter Your Password *</div></html>");
        panel.add(PasswordLabelII);
        
        vSpace(panel, new JTextPane(), 10);
        
        passwordFieldII = new JPasswordField();
        passwordFieldII.setPreferredSize(new Dimension(500, 45)); // Set preferred size
        passwordFieldII.setFont(font);
        passwordFieldII.setMargin(new Insets(10, 30, 10, 30));
        passwordFieldII.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(new String(passwordField.getPassword()).equals(new String(passwordFieldII.getPassword())) && new String(passwordField.getPassword()).length()>=8) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if(new String(passwordField.getPassword()).equals(new String(passwordFieldII.getPassword())) && new String(passwordField.getPassword()).length()>=8) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if(new String(passwordField.getPassword()).equals(new String(passwordFieldII.getPassword())) && new String(passwordField.getPassword()).length()>=8) {
                    submitButton.setEnabled(true);
                } else {
                    submitButton.setEnabled(false);
                }
            }
        });
        panel.add(passwordFieldII);
        
        shiftByPixel(panel, new JTextPane(), 450);
        
        vSpace(panel, new JTextPane(), 30);
        
        
        
        JButton resetButton = new JButton("Reset");
        resetButton.setEnabled(true);
        resetButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        resetButton.setPreferredSize(new Dimension(200, 35));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.setText("");
                passwordFieldII.setText("");
            }
        });
        panel.add(resetButton);

        shiftByPixel(panel, new JTextPane(), -75);

        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setPreferredSize(new Dimension(150, 35));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                JOptionPane.showMessageDialog(null, "<html><body>"+"<p>Do Not Forget Your Customer ID and Password:</p>"+"<p><b>Customer ID :</b> " + generateId(cu.fetchUserPAN(), cu.fetchUseraadhar())+"</p>"+"<p><b>Password : </b>"+new String(passwordField.getPassword()));
                cu.setUserid(generateId(cu.fetchUserPAN(), cu.fetchUseraadhar()));
                cu.setUserpassword(new String(passwordField.getPassword()));
                passwordField.setText("");
                passwordFieldII.setText("");
                jtf.setText("");

                // Control Termination of This Method
                try {
                    insertNewUser(null);
                } catch (ClassNotFoundException | SQLException e1) {
                    e1.printStackTrace();
                }
                frame.dispose();
            }
        });
        panel.add(submitButton);
        
        shiftByPixel(panel, new JTextPane(), 453);
        
        vSpace(panel, new JTextPane(), 100);
        
        frame.pack();

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        frame.setVisible(true);
    }

    public static void insertNewUser(Runnable callback) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/saving";
        String username = "IshikaYadav";
        String password = "Ishika@mysql";
        Connection con = DriverManager.getConnection(url, username, password);
        /*
            `User ID` varchar(6) primary key,
            `User Password` varchar(100) not null,
            `User Aadhar Number` char(12) not null, 
            `User PAN Number` char(10) not null,
            `User Honorific`
            `User FName` varchar(20) not null,
            `User MName` varchar(20) not null,
            `User LName` varchar(20) not null,
            `Current Evaluated Balance` bigint not null
         */
        String attributes = "`User ID`, `User Password`, `User Aadhar Number`, `User PAN Number`, `User Honorific`, `User FName`, `User MName`, `User LName`, `Current Evaluated Balance`";
        String values = "?,?,?,?,?,?,?,?,?";
        String query = "insert into userdata("+attributes+") values ("+values+")";
        PreparedStatement ps = con.prepareStatement(query);        
        ps.setString(1, cu.fetchUserid());
        ps.setString(2, cu.fetchUserpassword());
        ps.setString(3, cu.fetchUseraadhar());
        ps.setString(4, cu.fetchUserPAN());
        ps.setString(5, cu.fetchUserhonorific());
        ps.setString(6, cu.fetchUserfname());
        ps.setString(7, cu.fetchUsermname());
        ps.setString(8, cu.fetchUserlname());
        ps.setLong(9, 0);
        ps.executeUpdate();
        con.close();
        System.out.println("Values Inserted In `saving` Table");
        new MoneyManager().loginPage(null);
    }

    public static void shiftByPixel(JPanel panel, JTextPane jtextpane, int pixel) {
        jtextpane = new JTextPane();
        jtextpane.setContentType("text/html");
        jtextpane.setEditable(false);
        jtextpane.setText(new MoneyManager().shiftPixel(pixel));
        panel.add(jtextpane);
    }

    public static void vSpace(JPanel panel, JTextPane jtextpane, int size) {
        jtextpane = new JTextPane();
        jtextpane.setContentType("text/html");
        jtextpane.setEditable(false);
        jtextpane.setText(new MoneyManager().verticalSpace(size));
        jtextpane.setEnabled(false); // Added later On
        panel.add(jtextpane);
    }

    public String shiftPixel(int pixel) {
        return "<html><div style='font-size: 18px; margin-left: 160px; margin-right: "+pixel+";'></div></html>";
    }

    public String verticalSpace(int vSpace) {
        return "<html><center><div style='height:"+vSpace+"px; width: 1536px; background-color:balck;'></div></center></html>";
    }

    public String retNBSP(int times) {
        String str = new String();
        for(int i=1; i<=times; i++) {
            str = str+"&nbsp;";
        }
        return str;
    }
    public static String generateId(String PAN_Number, String Aadhar_Number) {
        String combinedInput = PAN_Number + Aadhar_Number;
        
        try {
            // Using SHA-256 for simplicity; you can choose a different algorithm based on your requirements
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(combinedInput.getBytes());

            // Take the first 8 bytes of the hash and convert them to an 8-character string
            String Id = hashBytesToString(hashedBytes).substring(0, 8);

            // Convert to uppercase
            return Id.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String hashBytesToString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
    private static void setGlassPaneView(JFrame parentFrame, Boolean setVisibility) {
        // Create the blurred glass pane
        JPanel glassPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(0, 0, 0, 100)); // Adjust the transparency level here
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        glassPane.setOpaque(false);

        // Attach the glass pane to the parent frame
        parentFrame.setGlassPane(glassPane);
        glassPane.setVisible(setVisibility);
    }
    private static void showLoadingScreen(String message, int duration) {
        JDialog loadingDialog = new JDialog((JFrame) null, true);
        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        loadingDialog.setUndecorated(true);

        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setFont(new Font("Dialog", Font.BOLD, 15));

        loadingDialog.getContentPane().add(messageLabel);
        loadingDialog.setSize(200, 80);
        loadingDialog.setLocationRelativeTo(null);

        Timer timer = new Timer(duration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadingDialog.dispose();
            }
        });
        // Set timer to not repeat
        timer.setRepeats(false);
        // Start the timer
        timer.start();
        // Show the loading dialog
        loadingDialog.setVisible(true);
    }
    private static String provideTransactionnumber() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/saving";
        String username = "IshikaYadav";
        String password = "Ishika@mysql";
        Connection con = DriverManager.getConnection(url, username, password);
        String countQuery = "select count(*) as maxCurrentRow from record";
        Statement countStatement = con.createStatement();
        ResultSet countResultSet = countStatement.executeQuery(countQuery);
        int maxCurrentRow = 0;
        if (countResultSet.next()) {
            maxCurrentRow = countResultSet.getInt("maxCurrentRow");
        }
        
        String query = "select * from record where `Serial Number`="+String.valueOf(maxCurrentRow);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        String lastRefId = null;
        while(rs.next()) {
            lastRefId = rs.getString("Transaction Number");
        }
        con.close();
        // System.out.println("Ye hai Problem"+lastRefId);
        if(lastRefId!=null) {
            // System.out.println("ATM" + String.format("%012d", Long.parseLong(new BigInteger(lastRefId.substring(3)).add(BigInteger.ONE).toString())));
            // Return Statment Below is Just a Combination of Many Steps Which Increments the Last REF ID by 1
            return "IRI" + String.format("%012d", Long.parseLong(new BigInteger(lastRefId.substring(3)).add(BigInteger.ONE).toString()));
        }
        return "IRI000000000001";

        
    }
}
