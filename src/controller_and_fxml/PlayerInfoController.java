package controller_and_fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import objects.club;
import objects.player;

import java.io.IOException;
import java.util.Locale;

public class PlayerInfoController {

    private Client client;
    private static club c;
    private player p;

    public void setClient(Client client) {
        this.client = client;
    }

    public static void setC(club c) {
        PlayerInfoController.c = c;
    }

    public void setP(player p) {
        this.p = p;
    }

    @FXML
    Label playername,position,country,club,height,age,salary,sellprice;
    @FXML
    ImageView image;

    @FXML
    Button gobackbutton;


    public void load()
    {
        playername.setText(p.getName().toUpperCase());
        position.setText(p.getPosition().toUpperCase());
        country.setText(p.getCountry().toUpperCase());
        club.setText(p.getClub().toUpperCase());
        height.setText(String.valueOf((p.getHeight())).toUpperCase()+"m");
        age.setText(String.valueOf(p.getAge()).toUpperCase()+"yr");
        salary.setText(String.valueOf(p.getSalary()));
        if(p.getSelling_price()==0)
            sellprice.setText("UNKNOWN");
        else
            sellprice.setText(String.valueOf(p.getSelling_price()));


        Image img=new Image(Client.class.getResourceAsStream("image/default.png"));
        if(Client.class.getResourceAsStream("image/"+p.getName().toLowerCase(Locale.ROOT)+".png")!=null)
            img = new Image(Client.class.getResourceAsStream("image/"+p.getName().toLowerCase(Locale.ROOT)+".png"));

        image.setImage(img);


    }









    public void gobackaction(ActionEvent actionEvent) throws IOException {


            client.showSearchAfterPage();

    }


    public void change_goback(MouseEvent mouseEvent) {
        gobackbutton.setStyle("-fx-background-color: transparent; -fx-text-fill: white");
    }

    public void revive_goback(MouseEvent mouseEvent) {
        gobackbutton.setStyle("-fx-background-color: transparent; -fx-text-fill: #D9514EFF");

    }
}
