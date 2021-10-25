package controller_and_fxml;

import clients.ReadThreadClient;
import clients.WriteThreadClient;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.*;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Client extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    private ReadThreadClient readthreadClient;
    private WriteThreadClient writeThreadClient;
    private static club c=new club();
    private static List<player> search_list=new ArrayList<>();
    public boolean want_to_buy=false;
    private boolean buy_successful=false;
    private boolean sell_successful=false;
    public boolean sell_list_now=false;
    public boolean player_list_now=false;
    public boolean cancel_sell_now=false;






    public static void setC(club c) {
        Client.c = c;
    }

    public static club getC() {
        return c;
    }

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    public static void setSearch_list(List<player> search_list) {
        Client.search_list = search_list;
    }

    public void setBuy_successful(boolean p)
    {
        this.buy_successful=p;
    }

    public void setSell_successful(boolean sell_successful) {
        this.sell_successful = sell_successful;
    }

    public WriteThreadClient getWriteThreadClient() {
        return writeThreadClient;
    }

    public void write(Object o)
    {
        WriteThreadClient.write(o);
    }



    public player find_player_from_name(List<player> all, String s)
    {
        player p=new player();
        for(player i:all)
        {
            if(i.getName().equalsIgnoreCase(s))
                p=i;
        }
        return p;
    }







    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;

        showLoginPage();
        //showMainPage();
    }


    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        readthreadClient=new ReadThreadClient(networkUtil,this,c);
        writeThreadClient= new WriteThreadClient();
        WriteThreadClient.setNetworkUtil(networkUtil);
    }










    public void showLoginPage() throws Exception
    {
        connectToServer();
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setClient(this);
        controller.setC(c);


        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showMainPage() throws IOException
    {
        player_list_now=true;
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tableview.fxml"));
        Parent root = loader.load();


        // Loading the controller
        TableViewController controller = loader.getController();


        controller.setClient(this);
        controller.setC(c);
        controller.setPlayers(c.getPlayers());
        search_list=c.getPlayers();

        controller.load();



        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }






    public void showSearch_by_namePage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Searchbyname.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchbynameContoller controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setSearch_list(search_list);


        // Set the primary stage
        stage.setTitle(c.getName());
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showSearch_by_positionPage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Searchbyposition.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchbypositionController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setSearch_list(search_list);

        // Set the primary stage
        stage.setTitle(c.getName());
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void showSearch_by_salary_rangePage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Searchbysalaryrange.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchBySalaryRangeController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setSearch_list(search_list);


        // Set the primary stage
        stage.setTitle(c.getName());
        stage.setScene(new Scene(root));
        stage.show();

    }

    public void showSearch_by_countryPage() throws IOException

    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchbycountry.fxml"));
        Parent root = loader.load();

        // Loading the controller
        SearchByCountryController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setSearch_list(search_list);


        // Set the primary stage
        stage.setTitle(c.getName());
        stage.setScene(new Scene(root));
        stage.show();
    }



    public void showSearchAfterPage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tableview.fxml"));
        Parent root = loader.load();

        // Loading the controller
        TableViewController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setPlayers(search_list);

        controller.load();


        // Set the primary stage
        stage.setTitle(c.getName());
        stage.setScene(new Scene(root));
        stage.show();
    }




    public void showPlayerInfoPage(String s) throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Playerinfo.fxml"));
        Parent root = loader.load();

        // Loading the controller
        PlayerInfoController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setP(find_player_from_name(c.getPlayers(),s));

        controller.load();


        // Set the primary stage
        stage.setTitle(c.getName());
        stage.setScene(new Scene(root));
        stage.show();
    }








    public void showSell_a_playerPage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sellaplayer.fxml"));
        Parent root = loader.load();


        // Loading the controller
        SellaplayerController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setPlayers(c.available_player_for_sale());


        controller.load();



        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showBuy_a_playerPage() throws IOException
    {
        sell_list_now=true;
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("buyaplayer.fxml"));
        Parent root = loader.load();

        //sell_list_request ob=new sell_list_request(c.getName());
        //networkUtil.write(ob);

        // Loading the controller
        BuyAPlayerController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setPlayers(c.getSell_list());


        controller.load();



        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showWant_to_buyPage(String s) throws IOException
    {
        want_to_buy=true;
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("wanttobuy.fxml"));
        Parent root = loader.load();


        // Loading the controller
        WantToBuyController controller = loader.getController();


        controller.setClient(this);
        controller.setP(find_player_from_name(c.getSell_list(),s));
        controller.setS(s);


        controller.load();



        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showWant_to_sellPage(String s) throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("wanttosell.fxml"));
        Parent root = loader.load();


        // Loading the controller
        WantToSellController controller = loader.getController();


        controller.setClient(this);
        controller.setP(find_player_from_name(c.available_player_for_sale(),s));
        controller.setS(s);


        controller.load();



        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void showCancelsellPage() throws IOException
    {
        cancel_sell_now=true;
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("cancelsell.fxml"));
        Parent root = loader.load();

        //sell_list_request ob=new sell_list_request(c.getName());
        //networkUtil.write(ob);

        // Loading the controller
        CancelsellController controller = loader.getController();

        controller.setClient(this);
        controller.setC(c);
        controller.setPlayers(c.getSell_list());


        controller.load();



        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    public void showAdd_playerPage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addplayer.fxml"));
        Parent root = loader.load();


        // Loading the controller
        AddPlayerController controller = loader.getController();


        controller.setClient(this);
        controller.setC(c);






        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }





    public void showTotal_salaryPage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("total_salary.fxml"));
        Parent root = loader.load();


        // Loading the controller
        TotalSalaryController controller = loader.getController();


        controller.setClient(this);
        controller.setC(c);

        controller.load();






        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void showBuyunsucessfulPage() throws IOException
    {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("buyunsuccessful.fxml"));
        Parent root = loader.load();


        // Loading the controller
        BuyunsuccessfulController controller = loader.getController();


        controller.setClient(this);







        // Set the primary stage
        stage.setTitle(c.getName());
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }




    public static void main(String[] args) {

        launch(args);
    }
}

