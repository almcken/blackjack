import java.util.ArrayList;
import java.util.List;


public class Dealer
{
    private String my_name = "The Dealer";
    private int my_cash = Integer.MAX_VALUE;
    private List<Card> current_hand;
    private boolean busted =  false;
    
    
    public Dealer()
    {
        current_hand = new ArrayList<Card>();
                
    }
    
    public String getMy_name()
    {
        return my_name;
    }

    public int getMy_cash()
    {
        return my_cash;
    }
    
    public void addCard(Card card)
    {
        current_hand.add(card);
    }
    
    public List<Card> getHand()
    {
        return current_hand;
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
    
    public void busted()
    {
        busted  = true;
    }
    
    public boolean getBusted()
    {
        return busted;
    }
    
    
}
