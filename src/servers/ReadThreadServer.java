package servers;
import clients.*;
import controller_and_fxml.*;
import objects.*;
import servers.*;
import util.NetworkUtil;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadThreadServer implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    private WriteServer writeServer;



    public ReadThreadServer(NetworkUtil networkUtil,WriteServer writeServer) {
        this.networkUtil = networkUtil;
        this.writeServer=writeServer;
        this.thr = new Thread(this);
        thr.start();
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
            while (true) {
                Object o = networkUtil.read();
                String s=(String)o;
                String[] bs=s.split(",");


                if (bs[0].equals("1")) //player_list_request
                {

                    new Thread(()->{
                        writeServer.giving_player_list(networkUtil,bs[1]);

                    }).start();

                }

                else if(bs[0].equals("2"))
                {
                    new Thread(()->{
                        writeServer.giving_sell_list(networkUtil);

                    }).start();
                }

                else if(bs[0].equals("3"))
                {
                    if(bs.length!= 11)
                        continue;

                    player p=String_to_player(bs);
                    new Thread(()->
                    {
                        writeServer.add_player_on_sell_list(networkUtil,p);

                    }).start();


                }

                else if(bs[0].equals("4"))
                {
                    if(bs.length!= 11)
                        continue;

                    player p=String_to_player(bs);

                    new Thread(()->{
                        writeServer.buy_player(networkUtil,p);

                    }).start();


                }

                else if(bs[0].equals("5"))
                {
                    if(bs.length!=11)
                        continue;
                    player p=String_to_player(bs);
                    new Thread(()->{
                        writeServer.add_player(networkUtil,p);

                    }).start();
                }

                else if(bs[0].equals("7"))
                {
                    if(bs.length!=11)
                        continue;
                    player p=String_to_player(bs);
                    new Thread(()->{
                        writeServer.remove_player_from_sell_list(networkUtil,p);

                    }).start();
                }

                else
                {
                    new Thread(()->{
                        writeServer.log_out(networkUtil);
                    }).start();
                }

            }
        }
        catch (IOException ioException)
        {
            ioException.printStackTrace();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}