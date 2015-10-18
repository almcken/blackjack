import java.io.IOException;


public class blackjack
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        System.out.print("");
        try
        {
            new Main("BlackJack 1.0   |   By: Al McKenzie");
        }
        catch (IOException e1)
        {
            
            e1.printStackTrace();
        }
        catch (InterruptedException e1)
        {
            
            e1.printStackTrace();
        }
        
       

    }

}
