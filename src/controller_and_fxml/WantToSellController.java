package controller_and_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import objects.player;

import java.io.IOException;

public class WantToSellController {
    private Client client;
    private player p;
    private String s;


    public void setP(player p) {
        this.p = p;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setS(String s) {
        this.s = s;
    }





    @FXML
    public Label player_name;

    @FXML
    Button yes,no;

    @FXML
    TextField textField;


    public void load()
    {
        player_name.setText(s);
    }



    public void yesAction(ActionEvent actionEvent) {

        p.setOn_sell_list(true);
        try {
            client.getWriteThreadClient().write_for_sell_request(p);
            p.setSelling_price(Integer.parseInt(textField.getText()));
            client.showSell_a_playerPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void noAction(ActionEvent actionEvent) {
        try {
            client.showSell_a_playerPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change_yes(MouseEvent mouseEvent) {

        yes.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_yes(MouseEvent mouseEvent) {
        yes.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");

    }

    public void change_no(MouseEvent mouseEvent) {

        no.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_no(MouseEvent mouseEvent) {
        no.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }











}
