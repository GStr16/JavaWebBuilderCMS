package loginSystemBackup04012020.loginSystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Controller {

    Mail checkerEmail = new Mail();
    AES aes = new AES();

    String secretKey = "JavaWebBuilderCMS";
    boolean isOkay = false;

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


    static PreparedStatement pst = null;
    public void hello(ActionEvent env) {
        try {


            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:mysql://hosting1993073.online.pro:3306/00286862_test", "00286862_test", "Y6ufde_e");

            String sql = "SELECT * FROM root where (EMAIL=? and PASSWORD=?)";
            String pass = aes.encrypt(passField.getText(), secretKey);
            String email = aes.encrypt(emailField.getText(), secretKey);
            String user = aes.encrypt(usernameField.getText(), secretKey);

            System.out.println(pass);
            System.out.println(email);
            System.out.println(user);


            pst = conn.prepareStatement(sql);

            pst.setString(1, email);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                System.out.println("Working account");
            }


            System.out.println(aes.decrypt(pass, secretKey));
            System.out.println(aes.decrypt(email, secretKey));
            System.out.println(aes.decrypt(user, secretKey));
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

        isOkay = false;
        String sql = "INSERT INTO root(EMAIL, PASSWORD) values (?,?)";
        try {

            if( passField.getText().equals("") && usernameField.getText().equals("") && emailField.getText().equals(""))
                System.out.println("Error "+isOkay);
            else {
                isOkay = true;
                System.out.println(isOkay);
            }

            if (isOkay == true) {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = null;
                con = DriverManager.getConnection("jdbc:mysql://hosting1993073.online.pro:3306/00286862_test", "00286862_test", "Y6ufde_e");

                pst = con.prepareStatement(sql);


                pst.setString(1, aes.encrypt(emailField.getText(), secretKey) );
                pst.setString(2, aes.encrypt(passField.getText(), secretKey) );


                System.out.println(aes.encrypt(emailField.getText(), secretKey) );
                System.out.println((aes.encrypt(passField.getText(), secretKey) ));
                System.out.println("Decription:");
                String pass = aes.encrypt(passField.getText(), secretKey);
                String email = aes.encrypt(emailField.getText(), secretKey);

                System.out.println(aes.decrypt(pass, secretKey));
                System.out.println(aes.decrypt(email, secretKey));


                System.out.println("Working");

                pst.executeUpdate();
//                JOptionPane.showMessageDialog(null, "Succesfull!");

            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }



}