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

public class SearchBySalaryRangeController {
    private Client client;
    private static club c;
    private static List<player>search_list;
    public void setClient(Client client) {
        this.client = client;
    }

    public static void setC(club c) {
        SearchBySalaryRangeController.c = c;
    }

    public static void setSearch_list(List<player> search_list) {
        SearchBySalaryRangeController.search_list = search_list;
    }

    @FXML
    TextField lowrange,highrange;


    @FXML
    Button search,reset;




    public void resetaction(ActionEvent actionEvent) {
        lowrange.setText(null);
        highrange.setText(null);

    }

    public void searchaction(ActionEvent actionEvent) {

        String low_range=lowrange.getText();
        String high_range=highrange.getText();
        try {
            search_list=c.search_player_by_salary_range(Double.parseDouble(high_range),Double.parseDouble(low_range));
            client.setSearch_list(search_list);
            client.showSearchAfterPage();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void change_reset(MouseEvent mouseEvent) {
        reset.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_reset(MouseEvent mouseEvent) {
        reset.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }

    public void change_search(MouseEvent mouseEvent) {
        search.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_search(MouseEvent mouseEvent) {
        search.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");
    }


}
