package gofishgame;

import java.util.ArrayList;

/** 
 * Object Class for a Go Fish player. A player has a 
 * hand of cards and point value.
 * TODO: Add select card methods 
 * @author PreciseMotion
 * @version 3.0
*/  
public class Player{
    
  protected ArrayList<Card> cards;
  protected int points;
  
  public Player() {}
  public Player(ArrayList<Card> c){
    cards = c;
    points = 0;
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
          System.out.println("You have a match with: " + cards.get(i).getCard() 
                              + "," + cards.get(j).getCard() + ".");
          cards.remove(i);
          cards.remove(j - 1);
          CompareHand();    
        }
      }
    } 
      
  }
  
}

class CPU extends Player {

  public CPU(ArrayList<Card> c) {
    super(c);
  }

  /**
   * Finds matching cards within the CPU's hand, 
   * removes them and increments the point value when necessary.
   */
  public void CompareHand(){  
      
    ArrayList<Card> hand = cards;

    for (int i = 0; i < hand.size() - 1; i++){
      for (int j = i + 1; j < cards.size(); j++){
        if (cards.get(i).getCard().equals(cards.get(j).getCard())){
          points++;
          System.out.println("The CPU has a match with: " + cards.get(i).getCard() 
                              + "," + cards.get(j).getCard() + ".");
          cards.remove(i);
          cards.remove(j - 1);
          CompareHand();    
        }
      }
    } 
      
  }
}
