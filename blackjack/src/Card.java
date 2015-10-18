
public class Card
{

    private int my_value;
    private String my_type;
    private String my_suit;
    private int my_id;
    private int count_value;
    
    public Card(int the_val, String type, String the_suit, int the_id, int the_cv)
    {
        my_value = the_val;
        my_type =  type;
        my_suit = the_suit;
        my_id = the_id;
        count_value = the_cv;
    }
    
    public int getCountValue()
    {
        return count_value;
    }
    
    public int getValue()
    {
        return my_value;
    }
    
    public String getType()
    {
        return my_type;
    }
    
    public void setValue(int the_value)
    {
        my_value = the_value;
    }
    
    public String getSuit()
    {
        return my_suit;
    }
    
    public String toString()
    {
        return my_type + " of " + my_suit;
    }
    public int getId()
    {
        return my_id;
    }
    
    
}
