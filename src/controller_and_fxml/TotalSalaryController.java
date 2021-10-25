package controller_and_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import objects.club;
import objects.player;

import java.io.IOException;

public class TotalSalaryController {

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
    Label salary;
    @FXML
    Button go_back;


    public void load()
    {
        salary.setText(String.format("Salary: %f",c.total_salary()));
    }






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




}
