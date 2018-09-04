package gofishgame;

import java.util.ArrayList;

/** 
 * Class to store and retrieve the variables for a player.
*/  
public class Player{
    
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int points;
    private String name;
    
    public Player(ArrayList<Card> c, int p, String n){
        cards = c;
        points = p;
        name = n;
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
        ArrayList<Card> hand = cards;
        
        for (int i = 0; i < hand.size() - 1; i++){
            for (int j = i + 1; j < hand.size(); j++){
                if (hand.get(i).getCard().equals(hand.get(j).getCard())){
                    points++;
                    System.out.println(name + " a match with: " + hand.get(i).getCard() + " and " + hand.get(j).getCard() + ".");
                    System.out.println(name + " " + points + " point(s).");
                    hand.remove(i);
                    hand.remove(j - 1);
                    CompareHand();    
                }
            }
        } 
    }
    
    /**
     * Displays cards in hand
     */
    public void displayHand(){ 
        System.out.print("Your current hand is: ");
        for (int i = 0; i < cards.size(); i++) {
        	System.out.print(cards.get(i).getCard() + ", ");
        }
        System.out.println();
    }
}