package servers;

import objects.*;
import util.NetworkUtil;

import java.io.IOException;
import java.util.*;

public class WriteServer{


    private List<player> all_players;
    private List<club>all_clubs;
    private List<player>sell_list;
    private HashMap<Integer,NetworkUtil> to_detect_networkUtil;
    private HashMap<Integer,String>  to_detect_club;
    private int count=0;



    public WriteServer(List<player> all_players, List<club>all_clubs, List<player>sell_list, HashMap<Integer,NetworkUtil> to_detect_networkUtil,HashMap<Integer,String>to_detect_club) {

        this.all_players=all_players;
        this.all_clubs=all_clubs;
        this.sell_list=sell_list;
        this.to_detect_club=to_detect_club;
        this.to_detect_networkUtil=to_detect_networkUtil;

    }




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


    public void update_a_player_info(player p)
    {
        for(player i: all_players)
        {
            if(i.getName().equalsIgnoreCase(p.getName()))
            {
                i.setClub(p.getClub());
                i.setOn_sell_list(p.isOn_sell_list());

                break;
            }
        }
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }









    public synchronized void giving_player_list(NetworkUtil networkUtil,String requesting_club)
    {
        count++;
        List<player>players=new ArrayList<>();

        to_detect_club.put(count,requesting_club);
        to_detect_networkUtil.put(count,networkUtil);

        for(player p:all_players)
        {
            if(p.getClub().equalsIgnoreCase(requesting_club))
            {
                players.add(p);
            }
        }

        String ob="1"+","+player_list_to_String(players);
        try {
            networkUtil.write(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void giving_sell_list(NetworkUtil networkUtil)
    {
        String ob="2"+","+player_list_to_String(sell_list);
        try {
            networkUtil.write(ob);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void add_player_on_sell_list(NetworkUtil networkUtil,player p)
    {
        sell_list.add(p);
        p.setOn_sell_list(true);
        update_a_player_info(p);


        String op="2"+","+player_list_to_String(sell_list);


        for(NetworkUtil n: to_detect_networkUtil.values())
        {
            try {
                n.write(op);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void remove_player_from_sell_list(NetworkUtil networkUtil,player p)
    {
        int flag=0;
        for(player i:sell_list)
        {
            if(i.getName().equalsIgnoreCase(p.getName()))
            {
                sell_list.remove(i);

                String ob="7"+","+player_to_String(i);
                for(Integer key: to_detect_club.keySet())
                {
                    if(i.getClub().equalsIgnoreCase(to_detect_club.get(key)))
                    {
                        NetworkUtil n=to_detect_networkUtil.get(key);
                        try {
                            n.write(ob);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                flag=1;
            }
        }

        p.setOn_sell_list(false);
        update_a_player_info(p);

        if(flag==0)
            return;



        String op="2"+","+player_list_to_String(sell_list);


        for(NetworkUtil n: to_detect_networkUtil.values())
        {
            try {
                n.write(op);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void buy_player(NetworkUtil networkUtil,player p)
    {

        int flag=0;
        for(player i:sell_list)
        {
            if(i.getName().equalsIgnoreCase(p.getName()) && i.isOn_sell_list())
            {

                i.setOn_sell_list(false);
                sell_list.remove(i);




                String ob="3"+","+player_to_String(i);   //telling previous club that another club has bought him...........

                try {

                    String prev_club=i.getClub();

                    for(Integer val: to_detect_club.keySet())
                    {
                        if(to_detect_club.get(val).equalsIgnoreCase(prev_club))
                        {
                            NetworkUtil n=to_detect_networkUtil.get(val);
                            n.write(ob);

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }





                ob="done"+","+player_to_String(i);  // telling new club that your buy is successful...............

                try {
                    Integer key=getKeyByValue(to_detect_networkUtil,networkUtil);// at first find the key for the given networkUtil



                    String new_club=to_detect_club.get(key);
                    i.setClub(new_club);


                    for(Integer val: to_detect_networkUtil.keySet())
                    {
                        if(to_detect_club.get(val).equalsIgnoreCase(new_club))
                        {
                            NetworkUtil n=to_detect_networkUtil.get(val);
                            n.write(ob);
                        }
                    }



                } catch (IOException e) {
                    e.printStackTrace();
                }





                String op="2"+","+player_list_to_String(sell_list); // giving everyone the updated sell list...........
                for(NetworkUtil n: to_detect_networkUtil.values())
                {
                    try {
                        n.write(op);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                update_a_player_info(i);


                flag=1;
                break;
            }

        }
        if(flag==0)
        {
            String ob="no"+","+player_to_String(p);
            try {
                networkUtil.write(ob);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void add_player(NetworkUtil networkUtil,player p)
    {

        all_players.add(p);
       /* for(Integer i:to_detect_club.keySet())
        {
            if(p.getClub().equalsIgnoreCase(to_detect_club.get(i)))
            {
                NetworkUtil n=to_detect_networkUtil.get(i);
                giving_player_list(n,p.getClub());
            }
        }

*/

    }


    public synchronized void log_out(NetworkUtil networkUtil)
    {
        try {
            networkUtil.closeConnection();
            Integer key=getKeyByValue(to_detect_networkUtil,networkUtil);
            to_detect_club.remove(key);
            to_detect_networkUtil.remove(key);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
