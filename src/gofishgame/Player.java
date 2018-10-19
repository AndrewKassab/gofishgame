package gofishgame;

import java.util.ArrayList;

/** 
 * Object Class for a Go Fish player. A player has a 
 * hand of cards and point value.
 * @author PreciseMotion
 * @version 3.0
*/  
public class Player{
    
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int points;
    private String message; // Used for compareHand() method
    
    public Player(ArrayList<Card> c, boolean isPlayer){
    	cards = c;
        points = 0;
        
        if (isPlayer) {
        	message = "You have";
        }
        else message = "The CPU has";
    }
    
    public ArrayList<Card> getCards(){
        return cards;
    }
    
    public void setCards(ArrayList<Card> c){
        cards = c;
    }
    
    public int getPoints(){
        return points;
    }
   
    /**
     * Increments player point value up by one
    */
    public void incrementPoints(){
        points++;
    }
    
    
    /**
     * Finds matching cards within the player's hand, 
     * removes them and increments the point value when necessary.
     */
    public void CompareHand(){  
        
    	ArrayList<Card> hand = cards;
        
        for (int i = 0; i < hand.size() - 1; i++){
            for (int j = i + 1; j < cards.size(); j++){
                if (cards.get(i).getCard().equals(cards.get(j).getCard())){
                    points++;
                    System.out.println(message + " a match with: " + cards.get(i).getCard() 
                        + "," + cards.get(j).getCard() + ".");
                    cards.remove(i);
                    cards.remove(j - 1);
                    CompareHand();    
                }
            }
        } 
        
    }
    
}
