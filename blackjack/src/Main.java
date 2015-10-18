import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Main extends JFrame implements ActionListener 
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private List<Player> my_players = new ArrayList<Player>();
    private Dealer my_dealer = new Dealer();
    private int my_number_of_decks = 1;
    private Queue<Card> the_deck_queue = new LinkedList<Card>();
    private List<Card> my_deck;
    private JLabel table;
    private JButton hit_button = new JButton("Hit");
    private JButton stay_button = new JButton("Stay");
    private JButton split_button = new JButton("Split");
    private JButton dd_button = new JButton("Double Down"); 
    private JButton change_bet_button = new JButton("Change Bet");
    private JButton play_button = new JButton("Play");
    private JButton stats_button = new JButton("Stats"); 
    private JPanel card_placement;
    private JLabel current_players_name = new JLabel();
    private JLabel current_players_name2 = new JLabel();
    private JLabel current_players_name3 = new JLabel();
    private JLabel d1, d2, d3, d4, d5, d6, d7, cp_money, cp_bet, my_d_ct, my_p_ct;
    private JLabel p1, p2, p3, p4, p5, p6, p7;
    private JLabel name1, name2, name3, name4, name5, name6, name7;
    private JLabel cc1, cc2, cc3, cc4, cc5, cc6, cc7, dcc;
    String option = "yes";
    boolean wait = false;
    boolean play = false;
    private JPanel score_board = new JPanel(new GridLayout(5, 2));
    private JPanel stats_board = new JPanel(new GridLayout(9, 2));
    boolean change_bet_i = false;
    private List<Card> count_deck = new ArrayList<Card>();


    /**
     * @param args
     * @return 
     * @throws IOException 
     * @throws InterruptedException 
     */
    public Main(String s) throws IOException, InterruptedException
    {

        super(s);

        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        setSize(600, 600);
       
        card_placement = new JPanel(new GridLayout(2, 8));
        setLayout(new BorderLayout());

        setUpPanel();

        addButtons();

        playgame();
    }

    private void addButtons()
    {
        hit_button.setEnabled(false);
        stay_button.setEnabled(false);
        split_button.setEnabled(false);
        dd_button.setEnabled(false);
        change_bet_button.setEnabled(false);
        play_button.setEnabled(true);
        stats_button.setEnabled(true);

        JPanel  buttonp = new JPanel();
        buttonp.setLayout(new GridLayout(1, 5));

        buttonp.add(hit_button);
        buttonp.add(stay_button);
        buttonp.add(split_button);
        buttonp.add(dd_button);
        buttonp.add(change_bet_button);
        buttonp.add(play_button);
        buttonp.add(stats_button);


        this.add(buttonp, BorderLayout.SOUTH);

        hit_button.addActionListener(this);
        stay_button.addActionListener(this);
        split_button.addActionListener(this);
        dd_button.addActionListener(this);
        change_bet_button.addActionListener(this);
        play_button.addActionListener(this);
        stats_button.addActionListener(this);



    }

    private void setUpPanel() throws IOException
    {


        JTextField inumdecks = new JTextField();
        JTextField iname1 = new JTextField();
        JTextField iname2 = new JTextField();
        JTextField iname3 = new JTextField();
        JTextField iname4 = new JTextField();
        JTextField iname5 = new JTextField();
        JTextField iname6 = new JTextField();
        JTextField iname7 = new JTextField();

        final JComponent[] inputs = new JComponent[] {
                        new JLabel("How many decks would you like to use?"),
                        inumdecks,
                        new JLabel("1st Player"),
                        iname1,
                        new JLabel("2nd Player"),
                        iname2,
                        new JLabel("3rd Player"),
                        iname3,
                        new JLabel("4th Player"),
                        iname4,
                        new JLabel("5th Player"),
                        iname5,
                        new JLabel("6th Player"),
                        iname6,
                        new JLabel("7th Player"),
                        iname7

        };

        while(true)
        {
            int inpuit = JOptionPane.showConfirmDialog(null, inputs, "Game Setup", JOptionPane.OK_CANCEL_OPTION);
            
            if(inpuit == 2)
            {
                System.exit(0);
            }
            if(intValidation(0, 2500, inumdecks.getText()) != -1 && !iname1.getText().equals(""))
            {
                
                if(!iname1.getText().equals(""))
                {
                    my_players.add(addplayer(iname1.getText()));
                }
                if(!iname2.getText().equals(""))
                {
                    my_players.add(addplayer(iname2.getText()));
                }
                if(!iname3.getText().equals(""))
                {
                    my_players.add(addplayer(iname3.getText()));
                }
                if(!iname4.getText().equals(""))
                {
                    my_players.add(addplayer(iname4.getText()));
                }
                if(!iname5.getText().equals(""))
                {
                    my_players.add(addplayer(iname5.getText()));
                }
                if(!iname6.getText().equals(""))
                {
                    my_players.add(addplayer(iname6.getText()));
                }
                if(!iname7.getText().equals(""))
                {
                    my_players.add(addplayer(iname7.getText()));
                }
                
                my_number_of_decks = intValidation(0, 2500, inumdecks.getText());
                break;
            }
            if(iname1.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "You must enter atleast one name", "alert",
                                              JOptionPane.ERROR_MESSAGE);
            }
           
           
        }




        BufferedImage myPicture = ImageIO.read(new File("bjtable.png"));
        table = new JLabel(new ImageIcon( myPicture ));
        table.setBounds(50, 50, 500, 500);
        add(table, BorderLayout.CENTER);
  
        d1 = new JLabel();
        d2 = new JLabel();
        d3 = new JLabel();
        d4 = new JLabel();
        d5 = new JLabel();
        d6 = new JLabel();
        d7 = new JLabel();

        p1 = new JLabel();
        p2 = new JLabel();
        p3 = new JLabel();
        p4 = new JLabel();
        p5 = new JLabel();
        p6 = new JLabel();
        p7 = new JLabel();

        card_placement.add(new JLabel("Dealers Hand"));
        card_placement.add(d1);
        card_placement.add(d2);
        card_placement.add(d3);
        card_placement.add(d4);
        card_placement.add(d5);
        card_placement.add(d6);
        card_placement.add(d7);

        card_placement.add(current_players_name);
        card_placement.add(p1);
        card_placement.add(p2);
        card_placement.add(p3);
        card_placement.add(p4);
        card_placement.add(p5);
        card_placement.add(p6);
        card_placement.add(p7);


        add(card_placement, BorderLayout.NORTH);



        cp_money = new JLabel("---");
        cp_bet = new JLabel("---");
        my_d_ct = new JLabel("---");
        my_p_ct = new JLabel("---"); 




        score_board.add(new JLabel("Current Player "));
        score_board.add(current_players_name2);

        score_board.add(new JLabel("Total Money "));
        score_board.add(cp_money);

        score_board.add(new JLabel("Bet "));
        score_board.add(cp_bet);

        score_board.add(new JLabel("Dealer has "));
        score_board.add(my_d_ct);

        score_board.add(current_players_name3);
        score_board.add(my_p_ct);

        add(score_board, BorderLayout.EAST);



        //left side panel
        name1 = new JLabel();
        name2 = new JLabel();
        name3 = new JLabel();
        name4 = new JLabel();
        name5 = new JLabel();
        name6 = new JLabel();
        name7 = new JLabel();

        cc1 = new JLabel();
        cc2 = new JLabel();
        cc3 = new JLabel();
        cc4 = new JLabel();
        cc5 = new JLabel();
        cc6 = new JLabel();
        cc7 = new JLabel();
        dcc = new JLabel();
        


        stats_board.add(name1);
        stats_board.add(cc1);

        stats_board.add(name2);
        stats_board.add(cc2);

        stats_board.add(name3);
        stats_board.add(cc3);

        stats_board.add(name4);
        stats_board.add(cc4);

        stats_board.add(name5);
        stats_board.add(cc5);

        stats_board.add(name6);
        stats_board.add(cc6);

        stats_board.add(name7);
        stats_board.add(cc7);

        stats_board.add(new JLabel("Dealer "));
        stats_board.add(dcc);

            
        


        add(stats_board, BorderLayout.WEST);

        for(int r = 0; r < my_players.size(); r++)
        {
            if(r == 0)
            {
                name1.setText(my_players.get(r).getMy_name());
            }
            if(r == 1)
            {
                name2.setText(my_players.get(r).getMy_name());
            }
            if(r == 2)
            {
                name3.setText(my_players.get(r).getMy_name());
            }
            if(r == 3)
            {
                name4.setText(my_players.get(r).getMy_name());
            }
            if(r == 4)
            {
                name5.setText(my_players.get(r).getMy_name());
            }
            if(r == 5)
            {
                name6.setText(my_players.get(r).getMy_name());
            }
            if(r == 6)
            {
                name7.setText(my_players.get(r).getMy_name());
            }
        }



    }

    private int intValidationJOptionPane(int min, double max, String string)
    {
        boolean valid = false;
        int number_of_chances = 0;
        int range_number = min;
        String valnum = "hello";
        while(!valid)
        {

            valnum = JOptionPane.showInputDialog(string);
            if(valnum == null)
            {
                System.exit(0);
            }

            try
            {
                range_number = Integer.parseInt(valnum);
                valid = true;
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "INVAILD NUMBER", "alert",
                                              JOptionPane.ERROR_MESSAGE);
            }
            if(range_number <= min || range_number >= max)
            {
                JOptionPane.showMessageDialog(null, "INVAILD RANGE", "alert",
                                              JOptionPane.ERROR_MESSAGE);
                valid = false;
            }
            if(number_of_chances == 5)
            {
                JOptionPane.showMessageDialog(null, "The game is shutting down. " +
                                              " You don't understand what i number is", "alert",
                                              JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
            number_of_chances++;


        }

        return range_number;
    }



    private int intValidation(int min, int max, String str)
    {
        boolean valid = false;
        int number_of_chances = 0;
        int range_number = min;

        while(!valid)
        {


            try
            {
                range_number = Integer.parseInt(str);
                valid = true;
            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "INVAILD NUMBER", "alert",
                                              JOptionPane.ERROR_MESSAGE);
                return -1;
            }
            if(range_number < min || range_number > max)
            {
                JOptionPane.showMessageDialog(null, "INVAILD RANGE", "alert",
                                              JOptionPane.ERROR_MESSAGE);
                valid = false;
                return -1;
            }

            number_of_chances++;


        }

        return range_number;
    }















    private void playgame() throws IOException, InterruptedException
    {

        my_deck =  new ArrayList<Card>();

        for(int i = 0; i < my_number_of_decks; i++)
        {
            Deck the_deck = new Deck();
            for(int j = 0; j < the_deck.getSize(); j++)
            {
                my_deck.add(the_deck.getDeck().get(j));
            }

        }


        for(int p = 0; p < 25; p++)
        {
            Collections.shuffle(my_deck);
        }
        

        int pickaname = 0 + (int)(Math.random()* my_players.size());
       

        int split = intValidationJOptionPane(0, my_deck.size(), "Lets's Split the deck. " 
                                             + my_players.get(pickaname).getMy_name() 
                                             + " please choose a number in between 1 and " 
                                             + my_deck.size() + " : " );

      
        List<Card> firsthalf =  new ArrayList<Card>();
        List<Card> secondhalf =  new ArrayList<Card>();

        for(int j = 0; j < split; j++)
        {
            firsthalf.add(my_deck.get(j));
        }
        for(int j = split; j < my_deck.size(); j++)
        {
            secondhalf.add(my_deck.get(j));
        }
        my_deck.clear();
        for(int j = 0; j < secondhalf.size(); j++)
        {
            my_deck.add(secondhalf.get(j));
        }

        for(int j = 0; j < firsthalf.size(); j++)
        {
            my_deck.add(firsthalf.get(j));
        }

      
        setVisible(true);
        pack();
        the_deck_queue.addAll(my_deck);
        count_deck.addAll(the_deck_queue);
        change_bet_button.setEnabled(true);
        while(!play)
        {
            waitForButtonPress();
        }


        int stoping_size = (int) (my_deck.size() * 0.3);

        //game loop
        while(the_deck_queue.size() >= stoping_size && my_players.size() >= 1)
        {

            //System.out.println("#######################     Place Your Bet's   ##########################\n");

            change_bet_button.setEnabled(false);
            play_button.setEnabled(false);
            //dealers hand
            d3.setIcon(null);
            d4.setIcon(null);
            d5.setIcon(null);
            d6.setIcon(null);
            d7.setIcon(null);
            my_dealer.addCard(the_deck_queue.remove());
            my_dealer.addCard(the_deck_queue.remove());
            play = false;
            placeDealersCards(1, "down");
            placeDealersCards(2, "down");
            //pack();
            //add two face down cards to panel



            for(int i = 0; i < my_dealer.getHand().size(); i++)
            {
                if(my_dealer.getHand().get(i).getValue() == 1)
                {
                    my_dealer.getHand().get(i).setValue(11);

                }
            }

            if(my_dealer.getHand().get(0).getValue() + my_dealer.getHand().get(1).getValue() == 22)
            {
                my_dealer.getHand().get(0).setValue(1);
            }




            int  dtotal = my_dealer.getTotalHand();
            //int dtotal = 21;



            //player loop bet
            for(int i = 0; i < my_players.size(); i++)
            {                      
                my_players.get(i).setBet(my_players.get(i).getBet());
                my_players.get(i).subtractBet();
              

            }



            //display dealers hand
            placeDealersCards(1, "" + my_dealer.getHand().get(0).getId());


            if(dtotal == 21)
            {
             
                placeDealersCards(2, "" + my_dealer.getHand().get(1).getId());
                my_d_ct.setText("21");
                dcc.setText(" "+dtotal);
            }


            //deal players cards - aka players loop
            for(int i = 0; i < my_players.size(); i++)
            {


                current_players_name.setText(my_players.get(i).getMy_name() + " Hand ");
                current_players_name2.setText(my_players.get(i).getMy_name());
                current_players_name3.setText(my_players.get(i).getMy_name() + " Total ");

                cp_money.setText("" + my_players.get(i).getMy_cash());
                cp_bet.setText("" + my_players.get(i).getBet());
                pack();                

                //get 2 cards



                my_players.get(i).addCard(the_deck_queue.remove());
                if(!my_players.get(i).getSplit())
                {
                    my_players.get(i).addCard(the_deck_queue.remove());
                }


                placePlayersHands(1, ""+my_players.get(i).getHand().get(0).getId());
                placePlayersHands(2, ""+my_players.get(i).getHand().get(1).getId());

                pack();
                if(my_players.get(i).getHand().get(0).getValue() == my_players.get(i).getHand().get(1).getValue())
                {                   
                    split_button.setEnabled(true);
                }

            
                checkAce(my_players.get(i));

                if(my_players.get(i).getHand().get(0).getValue() + my_players.get(i).getHand().get(1).getValue() == 22)
                {
                    my_players.get(i).getHand().get(0).setValue(1);
                }

                int total = my_players.get(i).getTotalHand();
             
                my_p_ct.setText("" + total); 


                //add buttons
                hit_button.setEnabled(true);
                stay_button.setEnabled(true);


                option = "yes";


                while(!(option.equals("stay") || option.equals("Stay") 
                                || option.equals("s")) && total < 21 && dtotal != 21 
                                && !my_players.get(i).getDoubleDown())
                {


           
                    if(my_players.get(i).getHand().size() == 2)
                    {
                        dd_button.setEnabled(true);
                    }
                    else
                    {
                        dd_button.setEnabled(false);
                    }

                   
                    option = "yes";

                   
                    waitForButtonPress();

                  


                    //hit
                    if(option.equals("hit") || option.equals("h") ||option.equals("Hit"))
                    {
                        my_players.get(i).addCard(the_deck_queue.remove());
                    
                        checkAce(my_players.get(i));
                        total = my_players.get(i).getTotalHand();
                        checkOver21Ace(my_players.get(i), total);
                        split_button.setEnabled(false);
                        placePlayersHands(my_players.get(i).getHand().size(), 
                                          ""+my_players.get(i).getHand().get(my_players.get(i).getHand().size()-1).getId());
                    }


                    //DoubleDown
                    if(option.equals("dd") || option.equals("double down") ||option.equals("double"))
                    {
                        my_players.get(i).subtractBet();
                        my_players.get(i).doubleDown(true);
                        my_players.get(i).addCard(the_deck_queue.remove());
                      
                        checkAce(my_players.get(i));
                        total = my_players.get(i).getTotalHand();
                        checkOver21Ace(my_players.get(i), total);
                        placePlayersHands(my_players.get(i).getHand().size(), 
                                          ""+my_players.get(i).getHand().get(my_players.get(i).getHand().size()-1).getId());
                        

                    }




                    //split
                    if(option.equals("sp") || option.equals("split") ||option.equals("Split"))
                    {


                        split_button.setEnabled(false);
                        my_players.get(i).subtractBet();


                        Card card1 = my_players.get(i).getHand().remove(0);
                        Card card2 = my_players.get(i).getHand().remove(0);
                        my_players.get(i).getHand().clear();

                        Player p2 = new Player(my_players.get(i).getMy_name(), 0);
                        p2.addCard(card2);
                        p2.setSplit();
                        my_players.add(i+1, p2);

                        my_players.get(i).addCard(card1);
                        my_players.get(i).addCard(the_deck_queue.remove());
                        if(my_players.get(i).getHand().get(0).getValue() == my_players.get(i).getHand().get(1).getValue())
                        {                   
                            split_button.setEnabled(true);
                        }
                        else
                        {
                            split_button.setEnabled(false);
                        }

                        checkAce(my_players.get(i));
                        total = my_players.get(i).getTotalHand();
                        checkOver21Ace(my_players.get(i), total);

                        placePlayersHands(my_players.get(i).getHand().size(), 
                                          ""+my_players.get(i).getHand().get(my_players.get(i).getHand().size()-1).getId());
                     

                    }
                    total = my_players.get(i).getTotalHand();
                    my_p_ct.setText("" + total); 
                  
                }

                //stay

                if(i == 0)
                {
                    cc1.setText(""+my_players.get(i).getTotalHand());
                }
                if(i == 1)
                {
                    cc2.setText(""+my_players.get(i).getTotalHand());
                }
                if(i == 2)
                {
                    cc3.setText(""+my_players.get(i).getTotalHand());
                }
                if(i == 3)
                {
                    cc4.setText(""+my_players.get(i).getTotalHand());
                }
                if(i == 4)
                {
                    cc5.setText(""+my_players.get(i).getTotalHand());
                }
                if(i == 5)
                {
                    cc6.setText(""+my_players.get(i).getTotalHand());
                }
                if(i == 6)
                {
                    cc7.setText(""+my_players.get(i).getTotalHand());
                }


                hit_button.setEnabled(false);
                stay_button.setEnabled(false);
                dd_button.setEnabled(false);
                split_button.setEnabled(false);



                if(total > 21)
                {
                    my_players.get(i).busted(true);                    
                    my_p_ct.setText("YOU BUSTED!"); 
                    //my_players.get(i).Lost();
                }

             
                Thread.sleep(1000);
                p1.setIcon(null);
                p2.setIcon(null);
                p3.setIcon(null);
                p4.setIcon(null);
                p5.setIcon(null);
                p6.setIcon(null);
                p7.setIcon(null);


            }



         
            //dealers hand
            placeDealersCards(2, "" + my_dealer.getHand().get(1).getId());
            my_d_ct.setText(""+ dtotal);
            dcc.setText(""+dtotal);

            //Dealer stands on all 17s
           
            while(dtotal < 17)
            {
                Thread.sleep(1000);
                my_dealer.addCard(the_deck_queue.remove());
              
                for(int j = 0; j < my_dealer.getHand().size(); j++)
                {
                    if((my_dealer).getHand().get(j).getValue() == 1)
                    {

                        (my_dealer).getHand().get(j).setValue(11);
                    }
                }


                dtotal = 0;
                for(int j = 0; j < my_dealer.getHand().size(); j++)
                {
                    dtotal = dtotal + my_dealer.getHand().get(j).getValue();
                }


                //we have busted but have an ace 11 to change to 1
                for(int j = 0; j < my_dealer.getHand().size(); j++)
                {
                    if(my_dealer.getHand().get(j).getValue() == 11 && dtotal > 21)
                    {
                        my_dealer.getHand().get(j).setValue(1);
                    }
                }

                dtotal = 0;
                for(int j = 0; j < my_dealer.getHand().size(); j++)
                {
                    dtotal = dtotal + my_dealer.getHand().get(j).getValue();
                }


                my_d_ct.setText("" + dtotal);
                dcc.setText(""+dtotal);
                placeDealersCards(my_dealer.getHand().size(), 
                                  ""+my_dealer.getHand().get(my_dealer.getHand().size()-1).getId());




            }
           
            my_d_ct.setText("" + dtotal); 
            dcc.setText(""+dtotal);

            if(dtotal > 21)
            {
                my_dealer.busted();                
                my_d_ct.setText("BUSTED!"); 
            }

            repaint();


            //payouts round over
         

            for(int i = 0; i < my_players.size(); i++)
            {

                //TODO figure out payouts!@!!!
                
                if(my_dealer.getBusted())
                {
                    if(my_players.get(i).getTotalHand() <= 21)
                    {
                        my_players.get(i).addMoney(my_players.get(i).getBet()*2);
                        if(my_players.get(i).getDoubleDown())
                        {
                            my_players.get(i).addMoney(my_players.get(i).getBet()*2);
                        }
                    }
                    if(my_players.get(i).getTotalHand() == 21)
                    {
                        my_players.get(i).addMoney(my_players.get(i).getBet()*0.5);
                    }
                    
                }
                else //dealer did not busted
                {
                    if(!my_players.get(i).getBusted())
                    {
                        if(my_dealer.getTotalHand() < my_players.get(i).getTotalHand())
                        {
                            my_players.get(i).addMoney(my_players.get(i).getBet()*2);
                            if(my_players.get(i).getDoubleDown())
                            {
                                my_players.get(i).addMoney(my_players.get(i).getBet()*2);
                            }
                            
                        }
                        if(my_players.get(i).getTotalHand() == 21 && my_dealer.getTotalHand() != 21)
                        {
                            my_players.get(i).addMoney(my_players.get(i).getBet()*0.5);
                        }
                        if(my_players.get(i).getTotalHand() == my_dealer.getTotalHand())
                        {
                            //they tied
                            my_players.get(i).addMoney(my_players.get(i).getBet());
                            
                        }
                    }
                }
                
                
                
                


                my_players.get(i).getHand().clear();
            }


            //clean up

            //fix split mother fuckers
            int playerz = my_players.size();
            for(int j = 0; j < playerz; j++)
            {
                if(my_players.get(j).getSplit())
                {
                    for(int z = 0; z < playerz; z++)
                    {
                        if(my_players.get(j).getMy_name().equals(my_players.get(z).getMy_name()) 
                                        && !my_players.get(z).getSplit())
                        {
                            my_players.get(z).addMoney(my_players.get(j).getMy_cash());
                            my_players.remove(j);
                            j--;
                            playerz--;
                        }

                    }

                } 


            }

            //people with no money
            for(int i = 0; i < my_players.size(); i++)
            {
                my_players.get(i).setBet(my_players.get(i).getDefaultBet());
                
                
                my_players.get(i).doubleDown(false);
                my_players.get(i).busted(false);
                //my_players.get(i).setSplit();
                if(my_players.get(i).getMy_cash() <= 0)
                {
                    my_players.remove(i);
                    i--;
                }


            }

            my_dealer.getHand().clear();




            //disable buttons

            play_button.setEnabled(true);
            change_bet_button.setEnabled(true);
            play = false;

            my_d_ct.setText("---"); 
            my_p_ct.setText("---");
            cp_money.setText("---");
            cp_bet.setText("---");
            current_players_name.setText("");
            current_players_name2.setText("");
            current_players_name3.setText("");
            repaint();

            Thread.sleep(500);
            while(!play)
            {
                waitForButtonPress();
            }
            cc1.setText("");            
            cc2.setText("");           
            cc3.setText("");            
            cc4.setText("");            
            cc5.setText("");           
            cc6.setText("");           
            cc7.setText("");
            dcc.setText("");



            System.out.println("#######################     Round Over    ##########################");
            


        }

        System.out.println("#######################     Game Over    ##########################");

        System.out.println("STATS");
        System.out.println("There was : " + my_deck.size() + " cards left in the deck");

      
        
        JOptionPane.showMessageDialog(null, "GAME OVER", "Game Stats", JOptionPane.INFORMATION_MESSAGE);

        System.exit(0);




    }

    private void waitForButtonPress() throws InterruptedException
    {
        
        while(!wait)//buton pushed expect change bet
        {
            Thread.sleep(100);
            // waiting
            if(change_bet_i)
            {
                changeBet();
                change_bet_i = false;
                break;
            }
            if(play)
            {
                //play = false;
                break;
            }

        }

        wait = false;

    }

    private void placePlayersHands(int i, String name)
    {
        ImageIcon pic = new ImageIcon("cards/" + name + ".gif");
        if(i == 1)
        {
            p1.setIcon(pic);
        }

        if(i == 2)
        {
            p2.setIcon(pic);
        }

        if(i == 3)
        {
            p3.setIcon(pic);
        }

        if(i == 4)
        {
            p4.setIcon(pic);
        }

        if(i == 5)
        {
            p5.setIcon(pic);
        }

        if(i == 6)
        {
            p6.setIcon(pic);
        }

        if(i == 7)
        {
            p7.setIcon(pic);
        }

        repaint();

    }

    private void placeDealersCards(int i, String name)
    {        
        ImageIcon pic = new ImageIcon("cards/" + name + ".gif");
        if(i == 1)
        {
            d1.setIcon(pic);
        }

        if(i == 2)
        {
            d2.setIcon(pic);
        }

        if(i == 3)
        {
            d3.setIcon(pic);
        }

        if(i == 4)
        {
            d4.setIcon(pic);
        }

        if(i == 5)
        {
            d5.setIcon(pic);
        }

        if(i == 6)
        {
            d6.setIcon(pic);
        }

        if(i == 7)
        {
            d7.setIcon(pic);
        }

        repaint();
    }

   

    private void checkOver21Ace(Player player, int total)
    {
        for(int j = 0; j < player.getHand().size(); j++)
        {
            if(player.getHand().get(j).getValue() == 11 && total > 21)
            {
                player.getHand().get(j).setValue(1);
                break;
            }
        }

    }

    private void checkAce(Player i)
    {
        for(int j = 0; j < i.getHand().size(); j++)
        {
            if(i.getHand().get(j).getValue() == 1)
            {

                i.getHand().get(j).setValue(11);
            }
        }

    }


    private Player addplayer(String name) throws IOException
    {

        //int randomNum = 100 + (int)(Math.random()* 25000);
        int randomNum = 1000;
 
        return new Player(name, randomNum);
    }


    @Override
    public void actionPerformed(ActionEvent arg0)
    {
               
        if(arg0.getActionCommand().equals("Hit"))
        {
            option = "hit";
            wait = true;
        }
        if(arg0.getActionCommand().equals("Stay"))
        {
            option = "stay";
            wait = true;
        }
        if(arg0.getActionCommand().equals("Split"))
        {
            option = "split";
            wait = true;
        }
        if(arg0.getActionCommand().equals("Double Down"))
        {
            option = "dd";
            wait = true;
        }
        if(arg0.getActionCommand().equals("Play"))
        {
            play = true;
            wait = true;
        }
        if(arg0.getActionCommand().equals("Change Bet"))
        {
            change_bet_i = true;
        }
        if(arg0.getActionCommand().equals("Stats"))
        {
            displayStats();
        }



    }

    private void displayStats()
    {
        
        
        int[] cards = new int[15];
        int cardsPlayed = my_number_of_decks * 52 - the_deck_queue.size();
        int count = 0;
        for(int t = 0; t < cardsPlayed; t++)
        {
            count = count + count_deck.get(t).getCountValue();
            cards[(count_deck.get(t).getId()%13)+2]++;
        }
        
        
        
        final JComponent[] inputs = new JComponent[] {
                        new JLabel("Card Count : "),
                        new JLabel("" + count),
                        new JLabel("Cards left in deck : "),
                        new JLabel("" + the_deck_queue.size()),
                        new JLabel("# of Ace's : "),
                        new JLabel("" + cards[2]),
                        new JLabel("# of 2's : "),
                        new JLabel("" + cards[3]),
                        new JLabel("# of 3's : "),
                        new JLabel("" + cards[4]),
                        new JLabel("# of 4's : "),
                        new JLabel("" + cards[5]),
                        new JLabel("# of 5's : "),
                        new JLabel("" + cards[6]),
                        new JLabel("# of 6's : "),
                        new JLabel("" + cards[7]),
                        new JLabel("# of 7's : "),
                        new JLabel("" + cards[8]),
                        new JLabel("# of 8's : "),
                        new JLabel("" + cards[9]),
                        new JLabel("# of 9's : "),
                        new JLabel("" + cards[10]),
                        new JLabel("# of 10's : "),
                        new JLabel("" + cards[11]),
                        new JLabel("# of Jack's : "),
                        new JLabel("" + cards[12]),
                        new JLabel("# of Queen's : "),
                        new JLabel("" + cards[13]),
                        new JLabel("# of King's : "),
                        new JLabel("" + cards[14])
                                       
        };
        
        JOptionPane.showMessageDialog(null, inputs, "Game Stats", JOptionPane.INFORMATION_MESSAGE);
        
        
        
    }

    private void changeBet()
    {

        String[] players1 = new String[my_players.size()];
        for(int i = 0; i < my_players.size(); i++)
        {
            players1[i] = my_players.get(i).getMy_name();
        }


        String enter_name = (String) JOptionPane.showInputDialog(null, "Who are you?", "Change Bet",
                                                                 JOptionPane.QUESTION_MESSAGE, null, players1, players1[0]);


        //String enter_name = JOptionPane.showInputDialog("Who are you?");
        try
        {
            for(int i = 0; i < my_players.size(); i++)
            {
                if(enter_name.equals(my_players.get(i).getMy_name()))
                {
                    int anumber = intValidationJOptionPane(5, my_players.get(i).getMy_cash(), 
                                                           ""+my_players.get(i).getMy_name() 
                                                           + " you have $"
                                                           + my_players.get(i).getMy_cash()
                                                           + ". Your current bet is $"
                                                           + my_players.get(i).getBet()
                                                           + ". Enter your new bet :" );


                    my_players.get(i).setBet(anumber);
                }
            }
        }
        catch(NullPointerException e)
        {

        }








    }


}
