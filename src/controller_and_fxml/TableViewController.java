package controller_and_fxml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import objects.club;
import objects.player;
import objects.tableObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableViewController {

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
        TableViewController.c = c;
    }

    public void setPlayers(List<player> players) {
        this.players = players;
        player_to_tableObject();
    }

    public void player_to_tableObject() {
        List<tableObject>tableObjects=new ArrayList<>();
        for(player i: players)
        {

            tableObjects.add(new tableObject(i.getName().toUpperCase()));
        }
        setTableObjects(tableObjects);
    }



    @FXML
    ImageView imageView;


    @FXML
    private TableView tableView;

    TableColumn<tableObject, String> firstNameCol;

    ObservableList<tableObject> data;



    @FXML
    private ComboBox<String> combo_box,search_box;




    @FXML
    private Button buy,sell,add,log_out,all,cancel_sell;

    private boolean init = true;

    private void initializeColumns()
    {
        firstNameCol = new TableColumn<>();
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("player_name"));
        firstNameCol.setMinWidth(820);

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
                        client.player_list_now=false;
                        client.showPlayerInfoPage(userId);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            });


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

    public void initialize_combo_box()
    {
        combo_box.setCellFactory(tc->{
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                    setStyle("-fx-background-color: #D9514EFF; -fx-padding: 5px; -fx-font-size:22px; -fx-font-family: 'Tw Cen MT';-fx-border-color: #D9514EFF");
                }


            };
            /*cell.setOnMouseClicked(e -> {

                    }


            );*/
            return cell ;



        });

    }

    public void initialize_search_box()
    {
        search_box.setCellFactory(tc->{
            ListCell<String> cell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty) ;
                    setText(empty ? null : item);
                    setStyle("-fx-background-color: #D9514EFF; -fx-padding: 5px; -fx-font-size:22px; -fx-font-family: 'Tw Cen MT';-fx-border-color: #D9514EFF");
                }


            };
            /*cell.setOnMouseClicked(e -> {

                    }


            );*/
            return cell ;



        });

    }


    public void load() {
        if (init) {
            initializeColumns();
            initialize_combo_box();
            initialize_search_box();
            init = false;
        }


        data = FXCollections.observableArrayList(tableObjects);
        tableView.setEditable(true);
        tableView.setItems(data);



        ObservableList<String> list = FXCollections.observableArrayList("MAXIMUM AGE", "MAXIMUM HEIGHT", "MAXIMUM SALARY", "TOTAL SALARY");
        combo_box.setItems(list);

        ObservableList<String> list1 = FXCollections.observableArrayList("BY NAME", "BY POSITION", "BY SALARY RANGE","BY COUNTRY");
        search_box.setItems(list1);

        Image img = new Image(Client.class.getResourceAsStream("image/"+c.getName()+".png"));
        imageView.setImage(img);


    }










    public void buyAction(ActionEvent actionEvent) {
        client.sell_list_now=true;
        client.player_list_now=false;
        client.getWriteThreadClient().write_for_sell_list();


    }

    public void sellAction(ActionEvent actionEvent) {

        try {
            client.player_list_now=false;
            client.showSell_a_playerPage();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addAction(ActionEvent actionEvent) {
        try {
            client.player_list_now=false;
            client.showAdd_playerPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






    public void searchAction(ActionEvent actionEvent) {

        String userId = search_box.getValue();
        try {
            int i=-1;
            if(userId.equals("BY NAME"))
            {
                i=1;

            }
            else if(userId.equals("BY POSITION"))
            {
                i=2;

            }
            else if(userId.equals("BY SALARY RANGE"))
            {
                i=3;

            }
            else if(userId.equals("BY COUNTRY"))
            {
                i=4;
            }

            client.player_list_now=false;
            if(i==1)
            {
                client.showSearch_by_namePage();
                return;

            }
            else if(i==2)
            {
                client.showSearch_by_positionPage();
                return;
            }
            else if(i==3)
            {
                client.showSearch_by_salary_rangePage();
                return;
            }
            else if(i==4)
            {
                client.showSearch_by_countryPage();
                return;
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void comboAction(ActionEvent actionEvent) {

        String userId = combo_box.getValue();
        try {
            client.player_list_now=false;
            if(userId.equals("MAXIMUM AGE"))
            {
                client.setSearch_list(c.max_age());
                client.showSearchAfterPage();
                return;
            }
            else if(userId.equals("MAXIMUM HEIGHT"))
            {
                client.setSearch_list(c.max_height());
                client.showSearchAfterPage();
                return;
            }
            else if(userId.equals("MAXIMUM SALARY"))
            {
                client.setSearch_list(c.max_salary());
                client.showSearchAfterPage();
                return;
            }
            else if(userId.equals("TOTAL SALARY"))
            {
                client.showTotal_salaryPage();
            }


        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void allAction(ActionEvent actionEvent) throws IOException {
        client.showMainPage();
    }

    public void log_outAction(ActionEvent actionEvent) throws Exception {

        client.player_list_now=false;
        client.getWriteThreadClient().logout_request();
        client.showLoginPage();
    }

    public void cancel_sellAction(ActionEvent actionEvent) {
        client.player_list_now=false;
        client.cancel_sell_now=true;
        client.getWriteThreadClient().write_for_sell_list();


    }





    public void change_buy(MouseEvent mouseEvent) {

        buy.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_buy(MouseEvent mouseEvent) {
        buy.setStyle("-fx-background-color: transparent; -fx-text-fill: black");

    }

    public void change_sell(MouseEvent mouseEvent) {

        sell.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_sell(MouseEvent mouseEvent) {
        sell.setStyle("-fx-background-color: transparent; -fx-text-fill: black");
    }
    public void change_add(MouseEvent mouseEvent) {

        add.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_add(MouseEvent mouseEvent) {
        add.setStyle("-fx-background-color: transparent; -fx-text-fill: black");

    }

    public void change_logout(MouseEvent mouseEvent) {
        log_out.setStyle("-fx-background-color: transparent; -fx-text-fill: white");

    }

    public void revive_logout(MouseEvent mouseEvent) {
        log_out.setStyle("-fx-background-color: transparent; -fx-text-fill: black");
    }

    public void change_all(MouseEvent mouseEvent) {
        all.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_all(MouseEvent mouseEvent) {
        all.setStyle("-fx-background-color: transparent; -fx-text-fill: black");
    }


    public void change_cancelsell(MouseEvent mouseEvent) {
        cancel_sell.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_cancelsell(MouseEvent mouseEvent) {
        cancel_sell.setStyle("-fx-background-color: transparent; -fx-text-fill: black");
    }
}

