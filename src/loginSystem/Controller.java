package loginSystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Controller {

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
        String sql = "SELECT * FROM root where (USERNAME=? and PASSWORD=?) OR (EMAIL=? and PASSWORD=?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hosting1993073.online.pro:3306/00286862_test", "00286862_test", "Y6ufde_e");            System.out.println("Working!");
            pst = con.prepareStatement(sql);
            pst.setString(1,userField.getText());
            pst.setString(2,passwordField.getText());
            pst.setString(3, userField.getText());
            pst.setString(4, passwordField.getText());
            ResultSet rs = pst.executeQuery();
            if(rs.next()) {
                System.out.println("Working account");
            }
        }
        catch (Exception ex) {
            System.out.println("Error " +ex.getMessage());
        }
    }
    @FXML
    public void registerScene(ActionEvent env) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Register.fxml"));
        rootPanelLogin.getChildren().setAll(pane);
    }
    @FXML
    public void loginScene(ActionEvent env) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
            rootPanelRegister.getChildren().setAll(pane);
        }
        catch(Exception e) {
            System.out.println("Error! " +e.getMessage());
        }
    }

    public void Register(ActionEvent env) {
        String sql = "INSERT INTO root(USERNAME, EMAIL, PASSWORD) values (?,?,?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://hosting1993073.online.pro:3306/00286862_test", "00286862_test", "Y6ufde_e");            System.out.println("Working!");
            System.out.println("Working!");
            pst = con.prepareStatement(sql);

            pst.setString(1,usernameField.getText());
            pst.setString(2,emailField.getText());
            pst.setString(3,passField.getText());

            System.out.println("Working");

            pst.executeUpdate();
            JOptionPane.showMessageDialog(null,"Succesfull!");

        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Error" + e.getMessage());
        }
    }



}