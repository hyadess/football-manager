package clients;

import controller_and_fxml.Client;
import javafx.application.Platform;
import objects.*;
import util.NetworkUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadClient implements Runnable{

    private Thread thr;
    private NetworkUtil networkUtil;
    private static club c;
    private Client client;

    List<player>sell_list=new ArrayList<>();





    public ReadThreadClient(NetworkUtil networkUtil, Client client, club c)
    {
        this.networkUtil = networkUtil;
        this.client=client;
        this.c=c;
        this.thr = new Thread(this);
        thr.start();
    }

    public void set_clients_club(club c)
    {
        client.setC(c);
    }



    public List<player> String_to_player_list(String[] s)
    {
        List<player> players= new ArrayList<>();
        for(int i=1;i<s.length;i+=10)
        {
            player p=new player();
            p.setName(s[i]);
            p.setCountry(s[i+1]);
            p.setAge(Integer.parseInt(s[i+2]));
            p.setHeight(Double.parseDouble(s[i+3]));
            p.setClub(s[i+4]);
            p.setPosition(s[i+5]);
            p.setNumber(Integer.parseInt(s[i+6]));
            p.setSalary(Double.parseDouble(s[i+7]));
            p.setSelling_price(Integer.parseInt(s[i+8]));
            p.setOn_sell_list(Boolean.parseBoolean(s[i+9]));
            players.add(p);
        }

        return players;


    }

    public player String_to_player(String[] s)
    {
        player p=new player();
        p.setName(s[1]);
        p.setCountry(s[2]);
        p.setAge(Integer.parseInt(s[3]));
        p.setHeight(Double.parseDouble(s[4]));
        p.setClub(s[5]);
        p.setPosition(s[6]);
        p.setNumber(Integer.parseInt(s[7]));
        p.setSalary(Double.parseDouble(s[8]));
        p.setSelling_price(Integer.parseInt(s[9]));
        p.setOn_sell_list(Boolean.parseBoolean(s[10]));

        return p;


    }





    public void run() {
        try {

            while(true)
            {
                Object o = networkUtil.read();
                String s=(String) o;
                System.out.println(s);
                String[] bs=s.split(",");


                        if (bs[0].equals("1"))
                        {
                          new Thread(()->{
                                c.setPlayers(String_to_player_list(bs));
                                //set_clients_club(c);
                          }).start();

                        }

                        else if(bs[0].equals("2"))
                        {
                          new Thread(()->{

                                sell_list=String_to_player_list(bs);

                                c.setSell_list(sell_list);



                                //set_clients_club(c);
                          }).start();



                        }

                        else if(bs[0].equals("3"))
                        {

                           new Thread(()->{
                                player p=String_to_player(bs);
                                c.remove_player(p);


                          }).start();

                        }

                        else if(bs[0].equals("done"))
                        {
                            System.out.println("sucess");
                            new Thread(()->{
                                player p=String_to_player(bs);
                                p.setClub(c.getName());
                                c.add_player(p);
                                //set_clients_club(c);
                                client.setBuy_successful(true);


                            }).start();


                        }

                        else if(bs[0].equals("7"))
                        {
                            new Thread(()->{
                                player p=String_to_player(bs);
                                for(player i:c.getPlayers())
                                {
                                    if(i.getName().equalsIgnoreCase(p.getName()))
                                    {
                                        i.setOn_sell_list(false);
                                    }
                                }

                            }).start();
                        }

                        Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {

                                            if(bs[0].equals("1"))
                                            {
                                                try {
                                                    client.showMainPage();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            else if( bs[0].equals("2") && client.sell_list_now)
                                            {
                                                try {
                                                    client.showBuy_a_playerPage();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            else if( bs[0].equals("2") && client.cancel_sell_now)
                                            {
                                                try {
                                                    client.showCancelsellPage();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            else if((bs[0].equals("3") || bs[0].equals("done")) && client.player_list_now)
                                            {
                                                try {
                                                    client.showMainPage();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            else if((bs[0].equals("3") || bs[0].equals("done")) && client.sell_list_now)
                                            {
                                                try {
                                                    client.showBuy_a_playerPage();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            else if(bs[0].equals("done") && client.want_to_buy)
                                            {
                                                try {
                                                    client.want_to_buy=false;
                                                    client.showBuy_a_playerPage();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                            else if(bs[0].equals("no"))
                                            {
                                                try {
                                                    client.showBuyunsucessfulPage();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }

                                        }
                                    });









            }











        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}



