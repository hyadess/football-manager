package controller_and_fxml;

import javafx.scene.input.MouseEvent;
import objects.*;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginController {

    private Client client;
    private club c;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setC(club c) {
        this.c = c;
    }

    @FXML
    private TextField userText;


    @FXML
    private Button resetButton;

    @FXML
    private Button loginButton;

    @FXML
    void loginAction(ActionEvent event) {
        String clubName = userText.getText();


            c.setName(clubName);
            client.getWriteThreadClient().write_for_player_list(clubName);




    }

    @FXML
    void resetAction(ActionEvent event) {
        userText.setText(null);

    }

    public void change_reset(MouseEvent mouseEvent) {

        resetButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_reset(MouseEvent mouseEvent) {
        resetButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");

    }

    public void change_login(MouseEvent mouseEvent) {

        loginButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_login(MouseEvent mouseEvent) {
        loginButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }



}
