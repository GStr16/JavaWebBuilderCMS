package BackUp08012020.loginSystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {

    static Mail mail = new Mail();
    static AES aes = new AES();
    static RandomNumberGenerator x = new RandomNumberGenerator();

    String secretKey = "JavaWebBuilderCMS";
    boolean isOkay = false;
    boolean isVerified = false;


    // Login form
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;

    // Register form
    @FXML
    private PasswordField passField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;

    @FXML
    private AnchorPane rootPanelLogin;
    @FXML
    private AnchorPane rootPanelRegister;

    static Connection con = null;
    static PreparedStatement pst = null;
    public void hello(ActionEvent env) {

        try {
            String sql = "SELECT * FROM root where (EMAIL=? and PASSWORD=?) or (USERNAME=? and PASSWORD=?)";

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://hosting1993073.online.pro:3306/00286862_test", "00286862_test", "Y6ufde_e");

            pst = conn.prepareStatement(sql);

//            @FXML
//            private TextField userField;
//            @FXML
//            private PasswordField passwordField;


            String user = AES.encrypt(userField.getText(), secretKey);
            String pass = AES.encrypt(passwordField.getText(), secretKey);

            System.out.println(user + "" + pass);

            System.out.println(pass);

            System.out.println(user);

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hosting1993073.online.pro:3306/00286862_test", "00286862_test", "Y6ufde_e");

            pst = con.prepareStatement(sql);

            pst.setString(1,user);
            pst.setString(2,pass);
            pst.setString(3,user);
            pst.setString(4,pass);


            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                System.out.println("Working account");
            }


//            System.out.println(aes.decrypt(pass, secretKey));
//            System.out.println(aes.decrypt(email, secretKey));
//            System.out.println(aes.decrypt(user, secretKey));
        }
        catch (Exception ex) {
            System.out.println("Error " +ex.getMessage());
        }
    }
    @FXML
    public void registerScene(ActionEvent env) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("register.fxml"));
        rootPanelLogin.getChildren().setAll(pane);
    }
    @FXML
    public void loginScene(ActionEvent env) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("login.fxml"));
            rootPanelRegister.getChildren().setAll(pane);
        }
        catch(Exception e) {
            System.out.println("Error! " +e.getMessage());
        }
    }

    public void Register(ActionEvent env) {

        try {
            int randNumber = x.randomGenerator();


            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hosting1993073.online.pro:3306/00286862_test", "00286862_test", "Y6ufde_e");

            isOkay = false;
            String sql = "INSERT INTO root_test(email, username, number, verified, password) values (?,?,?,?,?)";

                String email = emailField.getText();

                if (passField.getText().equals("") && emailField.getText().equals("")) {

                    System.out.println("Error " + isOkay);
                } else {
                    if (mail.isValid(email)) {
                        isOkay = true;
                        System.out.println(isOkay);
                    }
                }
                if (isOkay == true)
                {
                    pst = con.prepareStatement(sql);
                    pst.setString(1, aes.encrypt(emailField.getText(), secretKey));
                    pst.setString(2, aes.encrypt(usernameField.getText(), secretKey));
                    pst.setString(3, aes.encrypt(Integer.toString(randNumber), secretKey));
                    pst.setString(4, aes.encrypt(Boolean.toString(isVerified), secretKey));
                    pst.setString(5, aes.encrypt(passField.getText(), secretKey));

                    System.out.println("Working");

                    pst.executeUpdate();
                }
        }
        catch (Exception e) {
            System.out.println("Your Email are existing!");
        }
    }
}