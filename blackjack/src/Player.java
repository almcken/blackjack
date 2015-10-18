import java.util.ArrayList;
import java.util.List;


public class Player
{

    private String my_name;
    private double my_cash;
    private List<Card> current_hand;
    private int current_bet = 5;
    private boolean split = false;
    private boolean doubleDown = false;
    private boolean busted = false;
    private int default_bet;
    
    public Player(String the_name, int the_cash)
    {
        my_name = the_name;
        my_cash =  the_cash;
        current_hand = new ArrayList<Card>();
    }
    
    public int getDefaultBet()
    {
        return default_bet;
    }
    public void busted(boolean set)
    {
        busted = set;
    }
    
    public boolean getBusted()
    {
        return busted;
    }

    public String getMy_name()
    {
        return my_name;
    }

    public double getMy_cash()
    {
        return my_cash;
    }
    public void subtractBet()
    {
        my_cash = my_cash - current_bet;
    }
    
    public void addMoney(double amount)
    {
        my_cash = my_cash + amount;
    }
    
    public void addCard(Card card)
    {
        current_hand.add(card);
    }
    
    public List<Card> getHand()
    {
        return current_hand;
    }
    
    public void setBet(int bet)
    {
        current_bet = bet;
        default_bet = bet;
    }
    
    public int getBet()
    {
        return current_bet;
    }
    public void setSplit()
    {
        split = true;
    }
    
    public boolean getSplit()
    {
        return split;
    }
    
    
    
    public int getTotalHand()
    {
        int total = 0;
        for(int i = 0; i < current_hand.size(); i++)
        {
            total = total + current_hand.get(i).getValue();
        }
        
        
            return total;
        
        
    }
    
    
    public void doubleDown(boolean set)
    {
        doubleDown = set;
        
    }
    
    public boolean getDoubleDown()
    {
        return doubleDown;
    }
    
    
}
