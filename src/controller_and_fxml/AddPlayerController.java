package controller_and_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import objects.club;
import objects.player;

import java.io.IOException;

public class AddPlayerController {


    private Client client;
    private club c;

    private player p=new player();

    public void setClient(Client client) {
        this.client = client;
    }

    public void setC(club c) {
        this.c = c;
    }




    @FXML
    TextField name,country,position,age,height,number,salary,sell_price;

    @FXML
    Button go_back,add;









    public void go_backAction(ActionEvent actionEvent) {
        try {
            client.showMainPage();
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

    public void addAction(ActionEvent actionEvent) {
        p.setName(name.getText());
        p.setCountry(country.getText());
        p.setPosition(position.getText());
        p.setAge(Integer.parseInt(age.getText()));
        p.setHeight(Double.parseDouble(height.getText()));
        p.setNumber(Integer.parseInt(number.getText()));
        p.setSalary(Double.parseDouble(salary.getText()));
        p.setSelling_price(Integer.parseInt(sell_price.getText()));
        p.setClub(c.getName());

        c.add_player(p);
        try {
            client.getWriteThreadClient().add_player_request(p);
            client.showMainPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change_add(MouseEvent mouseEvent) {
        add.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_add(MouseEvent mouseEvent) {
        add.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }
}
