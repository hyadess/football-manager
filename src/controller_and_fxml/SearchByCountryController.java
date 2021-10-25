package controller_and_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import objects.club;
import objects.player;

import java.io.IOException;
import java.util.List;

public class SearchByCountryController {

    private Client client;
    private static club c;
    private static List<player> search_list;

    public void setClient(Client client) {
        this.client = client;
    }

    public static void setC(club c) {
        SearchByCountryController.c = c;
    }

    public static void setSearch_list(List<player> search_list) {
        SearchByCountryController.search_list = search_list;
    }

    @FXML
    TextField namefield;

    @FXML
    Button searchbutton,resetbutton;






    public void resetname(ActionEvent actionEvent) {
        namefield.setText(null);
    }

    public void searchname(ActionEvent actionEvent) {

        String player_name=namefield.getText();
        try {
            search_list=c.search_player_by_country(player_name);
            client.setSearch_list(search_list);
            client.showSearchAfterPage();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void change_reset(MouseEvent mouseEvent) {
        resetbutton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_reset(MouseEvent mouseEvent) {
        resetbutton.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }

    public void change_search(MouseEvent mouseEvent) {
        searchbutton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_search(MouseEvent mouseEvent) {
        searchbutton.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }

}
