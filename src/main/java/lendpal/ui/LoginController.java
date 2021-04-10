package lendpal.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    TextField emailField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    @FXML
    Button registerButton;

    private String email;

    private String password;

    private String userId;

    private LendPalDataAccess access;

    public LoginController() {

    }

    public void setLendPalDataAccess(LendPalDataAccess access) {
        this.access = access;
    }

    @FXML
    void loginButtonClicked() throws IOException {
        // System.out.println("Hei");
        System.out.println(emailField.getText());
        System.out.println(passwordField.getText());
        if (access.checkUserCredentials(emailField.getText(), passwordField.getText())) {
            Stage oldStage = (Stage) loginButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LendPalApp.fxml"));
            Parent root = (Parent) loader.load();
            LendPalAppController appController = loader.getController();
            appController.setLendPalDataAccess(access);
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root, 900, 500));
            newStage.setTitle("LendPal");
            newStage.show();
            oldStage.close();
        }
        else {
            emailField.clear();
            passwordField.clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PopUp.fxml"));
            Parent popUpRoot = (Parent) loader.load();
            PopUpController popUpController = loader.getController();
            popUpController.setMessage("Epost eller passord er feil.");
            Stage popUp = new Stage();
            popUp.setScene(new Scene(popUpRoot));
            popUp.setTitle("Feilmelding");
            popUp.show();
        }
    }



}
