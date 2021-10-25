package controller_and_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class BuyunsuccessfulController {


    Client client;

    public void setClient(Client client) {
        this.client = client;
    }

    @FXML
    Button go_back;

    public void go_backAction(ActionEvent actionEvent) {
        try {
            client.showBuy_a_playerPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change_goback(MouseEvent mouseEvent) {
        go_back.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_goback(MouseEvent mouseEvent) {
        go_back.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }


}
