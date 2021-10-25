
package objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class club implements Serializable
{
    private String name;
    private List<player> players=new ArrayList<>();
    private List<player> sell_list=new ArrayList<>();




        public void setName (String name)
        {
            this.name = name;
        }
        public String getName ()
        {
            return name;
        }


        public List<player> getPlayers () {
            return players;
        }
        public void setPlayers (List < player > players) {
            this.players = players;
        }


        public List<player> getSell_list () {
            return sell_list;
        }





        public void setSell_list (List < player > sell_list) {
            this.sell_list = sell_list;
        }


        public int getPlayer_count ()
        {
            return players.size();
        }


        public List<player> available_player_for_sale ()
        {
            List<player> ans = new ArrayList<>();
            for (player i : players) {
                if (!i.isOn_sell_list()) {
                    ans.add(i);
                }
            }
            return ans;
        }
        public void add_player (player p)
        {
            player o;
            o = new player();
            o.setClub(p.getClub());
            o.setAge(p.getAge());
            o.setHeight(p.getHeight());
            o.setCountry(p.getCountry());
            o.setSalary(p.getSalary());
            o.setName(p.getName());
            o.setNumber(p.getNumber());
            o.setPosition(p.getPosition());
            o.setSelling_price(p.getSelling_price());
            players.add(o);

        }

        public void remove_player (player p)
        {
            for (player i : players) {
                if (i.getName().equalsIgnoreCase(p.getName())) {
                    players.remove(i);
                }
            }

        }

        public void show_player_info (player p)
        {
            System.out.println("All the information about " + p.getName() + ":");
            System.out.println("Country: " + p.getCountry());
            System.out.println("Age:     " + p.getAge() + "yr");
            System.out.println("Height:  " + p.getHeight() + "m");
            System.out.println("Club:    " + p.getClub());
            System.out.println("Position:" + p.getPosition());
            System.out.println("Number:  " + p.getNumber());
            System.out.println("Salary:  " + p.getSalary());


        }


        public List<player> search_player_by_name (String s)
        {
            List<player> players = new ArrayList<>();
            player p = new player();
            p.setName("nobody");
            for (player f : this.players) {
                if (s.equalsIgnoreCase(f.getName())) {
                    players.add(f);
                    return players;

                }
            }
            players.add(p);
            return players;
        }


        public List<player> search_player_by_position (String s)
        {
            List<player> players = new ArrayList<>();
            player p = new player();
            p.setName("nobody");
            for (player f : this.players) {
                if (s.equalsIgnoreCase(f.getPosition())) {
                    players.add(f);
                }
            }
            if (players.size() == 0)
                players.add(p);
            return players;
        }


        public List<player> search_player_by_salary_range ( double high, double low)
        {
            List<player> players = new ArrayList<>();
            player p = new player();
            p.setName("nobody");
            for (player f : this.players) {
                if (low <= f.getSalary() && high >= f.getSalary()) {
                    players.add(f);

                }
            }
            if (players.size() == 0)
                players.add(p);
            return players;

        }

        public List<player> search_player_by_country (String s){


            List<player> players = new ArrayList<>();
            player p = new player();
            p.setName("nobody");
            for (player f : this.players) {
                if (s.equalsIgnoreCase(f.getCountry())) {
                    players.add(f);
                    return players;

                }
            }
            players.add(p);
            return players;


        }

        public List<String> country_wise_player_count ()
        {
            List<String> countries = new ArrayList();
            List<Integer> cont = new ArrayList();
            for (player c : players) {
                int flag = 0;
                for (int i = 0; i < countries.size(); i++) {
                    if (c.getCountry().equalsIgnoreCase(countries.get(i))) {
                        int p = cont.get(i);
                        p = p + 1;
                        cont.set(i, p);
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    countries.add(c.getCountry());
                    cont.add(1);
                }
            }
            List<String> sr = new ArrayList<>();

            for (int i = 0; i < countries.size(); i++) {
                sr.add(countries.get(i).toUpperCase(Locale.ROOT) + ":  " + cont.get(i));
            }
            return sr;
        }


        public List<player> max_salary ()
        {
            double op = 0;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getSalary() > op) {
                    op = players.get(i).getSalary();
                }
            }
            List<player> done = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getSalary() == op) {
                    done.add(players.get(i));
                }
            }
            return done;
        }

        public List<player> max_age ()
        {
            int op = 0;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getAge() > op) {
                    op = players.get(i).getAge();
                }
            }
            List<player> done = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getAge() == op) {
                    done.add(players.get(i));
                }
            }
            return done;
        }

        public List<player> max_height ()
        {
            double op = 0;
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getHeight() > op) {
                    op = players.get(i).getHeight();
                }
            }
            List<player> done = new ArrayList<>();
            for (int i = 0; i < players.size(); i++) {
                if (players.get(i).getHeight() == op) {
                    done.add(players.get(i));
                }
            }
            return done;
        }

        public double total_salary ()
        {
            double sum = 0;
            for (int i = 0; i < players.size(); i++) {
                sum += players.get(i).getSalary() * 52.0;
            }
            return sum;
        }



}
