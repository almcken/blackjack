import java.util.ArrayList;
import java.util.List;


public class Deck
{
    List<Card> my_deck;
    
    
    public Deck()
    {
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        my_deck = new ArrayList<Card>();
        int id_number = 0;
        for(int i = 0; i < 4; i ++)
        {
            my_deck.add(new Card(1, "Ace", suits[i], id_number++, -1));
            my_deck.add(new Card(2, "Two", suits[i], id_number++, 1));
            my_deck.add(new Card(3, "Three", suits[i], id_number++, 1));
            my_deck.add(new Card(4, "Four", suits[i], id_number++, 1));
            my_deck.add(new Card(5, "Five", suits[i], id_number++, 1));
            my_deck.add(new Card(6, "Six", suits[i], id_number++, 1));
            my_deck.add(new Card(7, "Seven", suits[i], id_number++, 0));
            my_deck.add(new Card(8, "Eight", suits[i], id_number++, 0));
            my_deck.add(new Card(9, "Nine", suits[i], id_number++, 0));
            my_deck.add(new Card(10, "Ten", suits[i], id_number++, -1));
            my_deck.add(new Card(10, "Jack", suits[i], id_number++, -1));
            my_deck.add(new Card(10, "Queen", suits[i], id_number++, -1));
            my_deck.add(new Card(10, "King", suits[i], id_number++, -1));
            
        }
        
    }
    
    
    public List<Card> getDeck()
    {
        return my_deck;
    }
    
    public int getSize()
    {
        return my_deck.size();
        
    }
    
    public int[] getValues()
    {
        int[] values = new int[getSize()];
        for(int i = 0; i < getSize(); i++)
        {
            values[i] = my_deck.get(i).getValue();
        }
        
        
        return values;
        
    }
}
