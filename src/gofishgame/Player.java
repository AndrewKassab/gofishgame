package gofishgame;

import java.util.ArrayList;

/** 
 * Class to store and retrieve the variables for a player.
*/  
public class Player{
    
    private ArrayList<Integer> cards = new ArrayList<Integer>();
    private int points;
    private String name;
    
    public Player(ArrayList<Integer> c, int p, String n){
        cards = c;
        points = p;
        name = n;
    }
    
    public ArrayList<Integer> getCards(){
        return cards;
    }
    
    public void setCards(ArrayList<Integer> c){
        cards = c;
    }
    
    public int getPoints(){
        return points;
    }
    
    public void setPoints(int p){
        points = p;
    }
    
    public String getName(){
        return name;
    }
    
    /**
     * Finds matching cards within the player's hand, 
     * removes them and increments the point value when necessary.
     */
    public void CompareHand(){  
        ArrayList<Integer> hand = cards;
        
        for (int i = 0; i < hand.size() - 1; i++){
            for (int j = i + 1; j < hand.size(); j++){
                if (hand.get(i) == hand.get(j)){
                    points++;
                    System.out.println(name + " a match with: " + hand.get(i) + " and " + hand.get(j) + ".");
                    System.out.println(name + " " + points + " point(s).");
                    hand.remove(i);
                    hand.remove(j - 1);
                    CompareHand();    
                }
            }
        } 
    }
    
    /**
     * Displays the current contents of the player's hand
     */
    public void displayHand() {
    	System.out.println("Your current hand is: ");
    	System.out.println(cards.toString());
    }
}