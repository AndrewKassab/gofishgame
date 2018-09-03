package gofishgame;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

interface StackInterface<T>
{   
 
    /**
     * Adds a new entry to the top of the stack. 
    */
    public void push( T newEntry );  
    
    /**
     * Removes and returns the stack top entry.
    */ 
    public T pop();   
    
    /**
     * Retrieves this stack top entry.
    */
    public T peek();   
    
    /**
     * Detects whether the stack is empty.
    */
    public boolean isEmpty(); 
    
    /**
     * Removes all entries from the stack.
    */
    public void clear();
}

/**
 * Class implementing StackInterface in order to create 
 * a stack that will act as a deck of cards.
*/
class MyStack<T> implements StackInterface<T>
{
    Stack<T> theStack;
    public MyStack()
    {
        theStack = new Stack<>();
    }
    public void push( T newEntry )
    {
        theStack.push( newEntry );
    }
    public T peek()
    {   
        return theStack.peek();
    } 
    public T pop()
    {   
        return theStack.pop();
    } 
    public boolean isEmpty()
    {   
        return theStack.empty();
    } 
    public void clear()
    {   
        theStack.clear();
    } 
}

/**
 * Go Fish game. This class contains all objects and methods 
 * to simulate a game of go fish. The user will be given a hand
 * of cards and pit against a CPU. 
 * @author PreciseMotion
 * @version 1.0
 */
public class Gofish {
    
    /**
     * Shuffles the deck of cards.
     * @return the shuffled deck
     */
    public static int[] shuffle(){ 
        
        int cards[] = new int[]{1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13};
        
        Random rand = new Random();
        
        for (int i = 0; i < 52; i++){
            int r = i + rand.nextInt(52 - i);
            
            int temp = cards[r];
            cards[r] = cards[i];
            cards[i] = temp;
            
        }
        
        return cards;
    }
    
    /**
     * Displays cards in hand
     * @param h the hand to display.
     */
    public static void currenthand(ArrayList<Integer> h){ 
        System.out.print("Your current hand is: ");
        System.out.println(h.toString());       
    }
    
    /**
     * The main method, creates the players, deck, and their cards. 
     * Handles the functionality for the Go Fish game.
     * @param args
     * @throws InterruptedException Thread is put to sleep for player to process
     */
    public static void main(String[] args) throws InterruptedException {
        
        
        MyStack<Integer> deck = new MyStack<Integer>(); // Creates stack for deck of cards
        ArrayList<Integer> hand1 = new ArrayList<Integer>(); // Creates ArrayList for Player's hand
        ArrayList<Integer> hand2 = new ArrayList<Integer>(); // Creates ArrayList for CPU's hand
        gofishFrame frame = new gofishFrame();
        Card aceSpades = new Card("A","/ace_of_spades.png");
        
        int points1 = 0;
        int points2 = 0;
        int cpuchosen = 20;
        int pchosen = 20;
        String name1 = "You have"; 
        String name2 = "The CPU has";
        
        int cards[] = new int[52];
        cards = shuffle(); 
        
        for (int i = 0; i < 52; i++){ // Places shuffled cards into the deck stack
            deck.push(cards[i]);
        }
        
        for (int i = 0; i < 7; i++){ // Player draws 7 cards
            hand1.add(deck.pop());
        }
        
        for (int i = 0; i < 7; i++){ // CPU draws 7 cards
            hand2.add(deck.pop());
        }            
        
        Player player = new Player(hand1, points1,name1); // Create Player
        
        currenthand(hand1);
        
        player.CompareHand(); // Check for matches
        
        System.out.println("----------------------------------------------");
        
        hand1 = player.getCards();
        points1 = player.getPoints();      
        
        Player computer = new Player(hand2, points2,name2); // Create CPU
        
        computer.CompareHand(); // Check for matches
        frame.updateComputerCards(hand2);
        
        System.out.println("----------------------------------------------");
        
        hand2 = computer.getCards();
        points2 = computer.getPoints();
               
        currenthand(hand1);       
        
        do {
            if (!(hand1.isEmpty())){
                boolean cont = false;
                do{
                     pchosen = Integer.parseInt(JOptionPane.showInputDialog("Which card would you like to inquire your opponent for?") ); // Player requests card
                     for (int i = 0; i < hand1.size(); i++){  
                         if (pchosen == hand1.get(i)){  // Makes sure player has requested card
                             pchosen = i;               
                             cont = true;
                             break;
                         }
                     }
                     if (cont != true){
                         System.out.println("Please try again, you do not have a " + pchosen);
                     }
                } while (cont != true); // Until player requests a card that is in their hand
               
            
                int current = points1;
                int SeekingCard = hand1.get(pchosen);
            
                for (int i = 0; i < hand2.size(); i++){ // Checks CPU's hand for card
                    if ( hand1.get(pchosen) == hand2.get(i)  ){
                        points1++;   
                        hand1.remove(pchosen);
                        hand2.remove(i);
                        break;
                    }
                }
                if (points1 > current){ // If CPU had requested card
                    System.out.println("There is a match, your opponent gives up their " + SeekingCard + "."); 
                    System.out.println("You currently have " + points1 + " point(s).");
                    currenthand(hand1);
                    System.out.println("----------------------------------------------");
                }
                else { // If CPU did not have requested card
                    System.out.println("Your opponent does not have a " + SeekingCard + ". Sorry, go fish");
                    hand1.add(deck.pop());
                    System.out.println("You drew a " + hand1.get(hand1.size()- 1));
                    currenthand(hand1);
                    System.out.print("\n");
                    player.setCards(hand1);
                    player.setPoints(points1);
                    player.CompareHand();
                    points1 = player.getPoints();
                    currenthand(hand1);
                    System.out.print("----------------------------------------------\n");
                }
            }
            else { // If player's hand is empty
                hand1.add(deck.pop());
                System.out.println("You drew a " + hand1.get(hand1.size()- 1));
                currenthand(hand1);
                System.out.println("----------------------------------------------");
            }
            
            frame.updateComputerCards(hand2);
            Thread.sleep(3000); // Allow player to process results
            
            if (!(hand2.isEmpty())){ // If CPU's hand is not empty
                
                int currentchosen = cpuchosen;
                
                if (hand2.size() == 1){ // If there is only one card in CPU's hand, selects that. 
                    cpuchosen = 0;
                }
                else{
                    do{   // Loops until card selected is not the same as what the CPU selected last turn     
                        cpuchosen = ThreadLocalRandom.current().nextInt(0, hand2.size()); // Selects random card from CPU's hand                        
                    } while (cpuchosen == currentchosen); 
                }
                
                System.out.println("Your opponent is looking for a " + hand2.get(cpuchosen));
                int CardWanted = hand2.get(cpuchosen);
            
                int current = points2;
            
                for (int i = 0; i < hand1.size(); i++){ // Check if player has requested card
                    if ( hand2.get(cpuchosen) == hand1.get(i)  ){
                        points2++;   
                        hand2.remove(cpuchosen);
                        hand1.remove(i);
                        break;
                    }
                }
                if (points2 > current){ // If player has requested card
                    System.out.println("You've given up your " + CardWanted); 
                    System.out.println("The CPU now has " + points2 + " point(s).");
                    currenthand(hand1);
                    System.out.println("----------------------------------------------");
                }
                else { // If player does not have requested card
                    System.out.println("Too bad. Your opponent fishes a card from the deck.");
                    hand2.add(deck.pop());
                    computer.setCards(hand2);
                    computer.setPoints(points2);
                    computer.CompareHand();
                    points2 = computer.getPoints();
                    System.out.println("----------------------------------------------");
                }
            }
            else if (!deck.isEmpty()){ // If CPU's hand is empty
                hand2.add(deck.pop());
                System.out.println("The CPU draws a card");
                System.out.print("\n");
            }
            
            frame.updateComputerCards(hand2);
            
        } while (!(deck.isEmpty())); // While the deck still has cards
        
        System.out.println("The deck is empty! The game has finished!");
        System.out.println("You have " + points1);
        System.out.println("The CPU has " + points2);
        
        Thread.sleep(1000);
        
        if (points1 > points2){ // Determines the winner
            System.out.println("Congratulations! You are the winner!");
        }
        else if (points2 > points1){
            System.out.println("Looks like you lost!");
        }
        else System.out.println("Its a draw!");
        
        
    }
    
}
