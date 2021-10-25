package controller_and_fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import objects.club;
import objects.player;
import objects.tableObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuyAPlayerController {






    private Client client;
    private List<tableObject> tableObjects;
    private static club c;
    private List<player>players;


    public void setClient(Client client)
    {
        this.client = client;
    }

    public void setTableObjects(List<tableObject> tableObjects)
    {
        this.tableObjects = tableObjects;
    }

    public static void setC(club c) {
        BuyAPlayerController.c = c;
    }

    public void setPlayers(List<player> players) {
        this.players = players;
        player_to_tableObject();
    }

    public void player_to_tableObject() {
        List<tableObject>tableObjects=new ArrayList<>();
        for(player i: players)
        {
            if(!i.getClub().equalsIgnoreCase(c.getName()))
                tableObjects.add(new tableObject(i.getName().toUpperCase()));
        }
        setTableObjects(tableObjects);
    }





    @FXML
    private TableView tableView;

    TableColumn<tableObject, String> firstNameCol;

    ObservableList<tableObject> data;

    @FXML
    Button go_back;


    private void initializeColumns()
    {
        firstNameCol = new TableColumn<>();
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("player_name"));
        firstNameCol.setMinWidth(962);

        //firstNameCol.setStyle("-fx-padding: 80px; -fx-font-size:28px; -fx-font-family: Verdana;");


        firstNameCol.setCellFactory(tc->{
            TableCell<tableObject, String> cell = new TableCell<tableObject, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                    setStyle("-fx-background-color: #2A2B2DFF; -fx-padding: 50px; -fx-font-size:22px; -fx-font-family: Verdana; -fx-border-color: white; -fx-text-fill: #D9514EFF");
                }

            };
            cell.setOnMouseClicked(e -> {
                        if (! cell.isEmpty()) {

                            String userId = cell.getItem();
                            try {
                                client.sell_list_now=false;
                                client.showWant_to_buyPage(userId);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }


            );
            cell.setOnMouseEntered(e->{
                if (! cell.isEmpty()) {
                    cell.setStyle("-fx-background-color: #D9514EFF; -fx-padding: 50px; -fx-font-size:22px; -fx-font-family: Verdana; -fx-border-color: white; -fx-text-fill: white");
                }

            });


            cell.setOnMouseExited(e->{
                if (! cell.isEmpty()) {
                    cell.setStyle("-fx-background-color: #2A2B2DFF; -fx-padding: 50px; -fx-font-size:22px; -fx-font-family: Verdana; -fx-border-color: white; -fx-text-fill: #D9514EFF");
                }
            });
            return cell ;



        });

        tableView.getColumns().add(firstNameCol);
    }


    public void load() {

        initializeColumns();

        data = FXCollections.observableArrayList(tableObjects);
        tableView.setEditable(true);
        tableView.setItems(data);






    }




    public void go_backAction(ActionEvent actionEvent) {
        try {
            client.sell_list_now=false;
            client.showMainPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void change_goback(MouseEvent mouseEvent) {
        go_back.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_goback(MouseEvent mouseEvent) {
        go_back.setStyle("-fx-background-color: transparent; -fx-text-fill: black");
    }



}
