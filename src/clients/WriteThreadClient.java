package clients;

import objects.player;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class WriteThreadClient {
    public static NetworkUtil networkUtil;


    public String player_to_String(player p)
    {
        String ans=p.getName()+","+p.getCountry()+","+p.getAge()+","+p.getHeight()+","+p.getClub()+","+p.getPosition()+","+p.getNumber()+","+p.getSalary()+","+
                p.getSelling_price()+","+p.isOn_sell_list();

        return ans;
    }

    public String player_list_to_String(List<player> players)
    {
        String  ans="";
        for(int i=0;i<players.size();i++)
        {
            ans+=player_to_String(players.get(i));
            if(i<players.size()-1)
                ans+=",";
        }
        return ans;
    }







    public synchronized void write_for_player_list(String s)
    {
        String ans="1"+","+s;
        WriteThreadClient.write(ans);
    }

    public synchronized void write_for_sell_list()
    {
        String ans="2";
        WriteThreadClient.write(ans);
    }

    public synchronized void write_for_buy_request(player p)
    {
        String ans="4"+","+player_to_String(p);
        WriteThreadClient.write(ans);
    }

    public synchronized void write_for_sell_request(player p)
    {
        String ans="3"+","+player_to_String(p);
        WriteThreadClient.write(ans);
    }

    public synchronized void cancel_sell(player p)
    {

        String ans="7"+","+player_to_String(p);
        WriteThreadClient.write(ans);
    }

    public synchronized void add_player_request(player p)
    {
        String ans="5"+","+player_to_String(p);
        WriteThreadClient.write(ans);
    }

    public synchronized void logout_request()
    {
        String ans="6";
        WriteThreadClient.write(ans);
    }



    public static void write(Object o){
        new Thread(() -> {
            try {
                networkUtil.write(o);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void setNetworkUtil(NetworkUtil networkUtil) {
        WriteThreadClient.networkUtil = networkUtil;
    }


}
