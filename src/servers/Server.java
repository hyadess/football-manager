package servers;

import clients.*;
import controller_and_fxml.*;
import objects.*;
import servers.*;
import util.NetworkUtil;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Server
{

    private ServerSocket serverSocket;
    public static List<player> all_players=new ArrayList<>();
    public static List<club>all_clubs=new ArrayList<>();
    public List<player>sell_list=new ArrayList<>();
    public HashMap<Integer,NetworkUtil> to_detect_networkUtil=new HashMap<>();
    public HashMap<Integer,String>to_detect_club=new HashMap<>();

    private WriteServer writeServer;



    public static void read_file() throws Exception
    {

        BufferedReader br = new BufferedReader(new FileReader("players.txt"));
        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            player s = new player();
            s.setName(tokens[0]);
            s.setCountry(tokens[1]);
            s.setAge(Integer.parseInt(tokens[2]));
            s.setHeight(Double.parseDouble(tokens[3]));
            s.setClub(tokens[4]);
            s.setPosition(tokens[5]);
            s.setNumber(Integer.parseInt(tokens[6]));
            s.setSalary(Double.parseDouble(tokens[7]));
            all_players.add(s);
        }
        br.close();
    }
    public static void assign_players_to_clubs()
    {
        for(player p : all_players)
        {
            int flag=0;
            for(club c : all_clubs)
            {
                if(p.getClub().equalsIgnoreCase(c.getName()))
                {
                    if(c.getPlayer_count()>=7)
                        flag=2;
                    else {
                        c.add_player(p);
                        flag=1;
                        break;
                    }

                }
            }
            if(flag==2)
            {
                System.out.println(p.getName()+" can't be added to "+p.getClub()+" because club has maximum player");
            }
            else if(flag==0)
            {
                club c=new club();
                c.setName(p.getClub());
                c.add_player(p);
                all_clubs.add(c);
            }
        }
    }




    public void serve(Socket clientSocket) throws IOException
    {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);

        new ReadThreadServer(networkUtil,writeServer);

    }




    Server() {
        try {
            serverSocket = new ServerSocket(33333);
            writeServer=new WriteServer(all_players,all_clubs,sell_list,to_detect_networkUtil,to_detect_club);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }



    public static void main(String args[]) throws Exception {

        read_file();
        assign_players_to_clubs();
        Server server = new Server();
    }
}
