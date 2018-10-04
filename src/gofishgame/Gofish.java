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
     * Creates a standard shuffled deck of 52 cards.
     * @return the shuffled deck in the form of a stack.
     */
    public static MyStack<Card> createShuffledDeck(){ 
        
        Card aceSpades = new Card("A","/AS.png");
        Card aceClubs = new Card("A","/ace_of_clubs.png");
        Card aceDiamonds = new Card("A","/ace_of_diamonds.png");
        Card aceHearts = new Card("A","/AH.png");
        Card twoSpades = new Card("2","/2_of_spades.png");
        Card twoClubs = new Card("2","/2_of_clubs.png");
        Card twoDiamonds = new Card("2","/2_of_diamonds.png");
        Card twoHearts = new Card("2","/2_of_hearts.png");
        Card threeSpades = new Card("3","/3_of_spades.png");
        Card threeClubs = new Card("3","/3_of_clubs.png");
        Card threeDiamonds = new Card("3","/3_of_diamonds.png");
        Card threeHearts = new Card("3","/3_of_hearts.png");
        Card fourSpades = new Card("4","/4_of_spades.png");
        Card fourClubs = new Card("4","/4_of_clubs.png");
        Card fourDiamonds = new Card("4","/4_of_diamonds.png");
        Card fourHearts = new Card("4","/4_of_hearts.png");
        Card fiveSpades = new Card("5","/5_of_spades.png");
        Card fiveClubs = new Card("5","/5_of_clubs.png");
        Card fiveDiamonds = new Card("5","/5_of_diamonds.png");
        Card fiveHearts = new Card("5","/5_of_hearts.png");
        Card sixSpades = new Card("6","/6_of_spades.png");
        Card sixClubs = new Card("6","/6_of_clubs.png");
        Card sixDiamonds = new Card("6","/6_of_diamonds.png");
        Card sixHearts = new Card("6","/6_of_hearts.png");
        Card sevenSpades = new Card("7","/7_of_spades.png");
        Card sevenClubs = new Card("7","/7_of_clubs.png");
        Card sevenDiamonds = new Card("7","/7_of_diamonds.png");
        Card sevenHearts = new Card("7","/7_of_hearts.png");
        Card eightSpades = new Card("8","/8_of_spades.png");
        Card eightClubs = new Card("8","/8_of_clubs.png");
        Card eightDiamonds = new Card("8","/8_of_diamonds.png");
        Card eightHearts = new Card("8","/8_of_hearts.png");
        Card nineSpades = new Card("9","/9_of_spades.png");
        Card nineClubs = new Card("9","/9_of_clubs.png");
        Card nineDiamonds = new Card("9","/9_of_diamonds.png");
        Card nineHearts = new Card("9","/9_of_hearts.png");
        Card tenSpades = new Card("10","/10_of_spades.png");
        Card tenClubs = new Card("10","/10_of_clubs.png");
        Card tenDiamonds = new Card("10","/10_of_diamonds.png");
        Card tenHearts = new Card("10","/10_of_hearts.png");
        Card jackSpades = new Card("J","/jack_of_spades.png");
        Card jackClubs = new Card("J","/jack_of_clubs.png");
        Card jackDiamonds = new Card("J","/jack_of_diamonds.png");
        Card jackHearts = new Card("J","/jack_of_hearts.png");
        Card queenSpades = new Card("Q","/queen_of_spades.png");
        Card queenClubs = new Card("Q","/queen_of_clubs.png");
        Card queenDiamonds = new Card("Q","/queen_of_diamonds.png");
        Card queenHearts = new Card("Q","/queen_of_hearts.png");
        Card kingSpades = new Card("K","/king_of_spades.png");
        Card kingClubs = new Card("K","/king_of_clubs.png");
        Card kingDiamonds = new Card("K","/king_of_diamonds.png");
        Card kingHearts = new Card("K","/king_of_hearts.png");
        
        Card deckArray[] = {aceSpades,aceClubs,aceHearts,aceDiamonds,twoSpades,twoClubs,
        		twoHearts,twoDiamonds,threeSpades,threeClubs,threeHearts,threeDiamonds,fourSpades,
        		fourClubs,fourHearts,fourDiamonds,fiveSpades,fiveClubs,fiveHearts,fiveDiamonds,
        		sixSpades,sixClubs,sixHearts,sixDiamonds,sevenSpades,sevenClubs,sevenHearts,sevenDiamonds,
        		eightSpades,eightClubs,eightHearts,eightDiamonds,nineSpades,nineClubs,nineHearts,nineDiamonds,
        		tenSpades,tenClubs,tenHearts,tenDiamonds,jackSpades,jackClubs,jackHearts,jackDiamonds, 
        		queenSpades, queenClubs, queenHearts, queenDiamonds, kingSpades,kingClubs,kingHearts,kingDiamonds};
        
        MyStack<Card> deck = new MyStack<Card>();
        
        Random rand = new Random();
        
        for (int i = deckArray.length - 1; i > 0; i--) {
        	int index = rand.nextInt(i+1);
        	Card a = deckArray[index];
        	deckArray[index] = deckArray[i];
        	deckArray[i] = a;
        }
        
        for (int i = 0; i < 52; i++) {
        	deck.push(deckArray[i]);
        }
        
        return deck;
    }
    

    
    /**
     * The main method, creates the players, deck, and their cards. 
     * Handles the functionality for the Go Fish game.
     * @param args
     * @throws InterruptedException Thread is put to sleep for player to process
     */
    public static void main(String[] args) throws InterruptedException {
        
        
        MyStack<Card> deck = new MyStack<Card>(); // Creates stack for deck of cards
        ArrayList<Card> hand1 = new ArrayList<Card>(); // Creates ArrayList for Player's hand
        ArrayList<Card> hand2 = new ArrayList<Card>(); // Creates ArrayList for CPU's hand
        gofishFrame frame = new gofishFrame();
        
        String cpuchosen;
        int indexCpuChosen = 0;
        String pchosen;
        int indexPlayerChosen = 0;
        String name1 = "You have"; 
        String name2 = "The CPU has";
        
        deck = createShuffledDeck();
        
        for (int i = 0; i < 7; i++){ // Player draws 7 cards
            hand1.add(deck.pop());
        }
        
        for (int i = 0; i < 7; i++){ // CPU draws 7 cards
            hand2.add(deck.pop());
        }            
        
        Player player = new Player(hand1, 0,name1); // Create Player
        
        player.displayHand();
        
        frame.updatePlayerCards(hand1);
        
        player.CompareHand(); // Check for matches
        
        frame.updatePlayerCards(hand1);
        frame.updateScoreBoard(player.getPoints());
        
        System.out.println("----------------------------------------------");
        
        hand1 = player.getCards();    
        
        Player computer = new Player(hand2, 0,name2); // Create CPU
        
        computer.CompareHand(); // Check for matches
        frame.updateComputerCards(hand2);
        
        System.out.println("----------------------------------------------");
        
        hand2 = computer.getCards();
               
        player.displayHand();       
        
        // TODO: Try catch block for nullpointer??
        do {
            if (!(hand1.isEmpty())){
                boolean cont = false;
                do{
                     pchosen = JOptionPane.showInputDialog("Which card would you like to inquire your opponent for?"); // Player requests card
                     for (int i = 0; i < hand1.size(); i++){  
                         if (Character.toUpperCase(pchosen.charAt(0)) == hand1.get(i).getCard().charAt(0)){  // Makes sure player has requested card
                             indexPlayerChosen = i;               
                             cont = true;
                             break;
                         }
                     }
                     if (cont != true){
                         System.out.println("Please try again, you do not have a " + pchosen);
                     }
                } while (cont != true); // Until player requests a card that is in their hand
               
            
                int current = player.getPoints();
                Card SeekingCard = hand1.get(indexPlayerChosen);
            
                for (int i = 0; i < hand2.size(); i++){ // Checks CPU's hand for card
                    if ( hand1.get(indexPlayerChosen).getCard().equals(hand2.get(i).getCard()) ){
                        player.incrementPoints();   
                        hand1.remove(indexPlayerChosen);
                        hand2.remove(i);
                        break;
                    }
                }
                if (player.getPoints() > current){ // If CPU had requested card
                    System.out.println("There is a match, your opponent gives up their " + SeekingCard.getCard() + "."); 
                    frame.updateScoreBoard(player.getPoints());
                    player.displayHand();
                    System.out.println("----------------------------------------------");
                }
                else { // If CPU did not have requested card
                    System.out.println("Your opponent does not have a " + SeekingCard.getCard() + ". Sorry, go fish");
                    hand1.add(deck.pop());
                    System.out.println("You drew a " + hand1.get(hand1.size()- 1).getCard());
                    player.displayHand();
                    System.out.print("\n");
                    player.setCards(hand1);
                    player.CompareHand();
                    frame.updateScoreBoard(player.getPoints());
                    player.displayHand();
                    System.out.print("----------------------------------------------\n");
                }
            }
            else { // If player's hand is empty
                hand1.add(deck.pop());
                System.out.println("You drew a " + hand1.get(hand1.size()- 1).getCard());
                player.displayHand();
                System.out.println("----------------------------------------------");
            }
            
            frame.updatePlayerCards(hand1);
            frame.updateComputerCards(hand2);
            
            Thread.sleep(3000); // Allow player to process results
            
            if (!(hand2.isEmpty())){ // If CPU's hand is not empty
                
                int currentChosen = indexCpuChosen;
                
                if (hand2.size() == 1){ // If there is only one card in CPU's hand, selects that. 
                    indexCpuChosen = 0;
                }
                else{
                    do{   // Loops until card selected is not the same as what the CPU selected last turn     
                        indexCpuChosen = ThreadLocalRandom.current().nextInt(0, hand2.size()); // Selects random card from CPU's hand                        
                    } while (indexCpuChosen == currentChosen); 
                }
                
                System.out.println("Your opponent is looking for a " + hand2.get(indexCpuChosen).getCard());
                Card CardWanted = hand2.get(indexCpuChosen);
            
                int current = computer.getPoints();
            
                for (int i = 0; i < hand1.size(); i++){ // Check if player has requested card
                    if ( hand2.get(indexCpuChosen).getCard().equals(hand1.get(i).getCard())){
                        computer.incrementPoints();   
                        hand2.remove(indexCpuChosen);
                        hand1.remove(i);
                        break;
                    }
                }
                if (computer.getPoints() > current){ // If player has requested card
                    System.out.println("You've given up your " + CardWanted.getCard()); 
                    System.out.println("The CPU now has " + computer.getPoints() + " point(s).");
                    player.displayHand();                    
                    System.out.println("----------------------------------------------");
                }
                else { // If player does not have requested card
                    System.out.println("Too bad. Your opponent fishes a card from the deck.");
                    hand2.add(deck.pop());
                    computer.CompareHand();
                    System.out.println("----------------------------------------------");
                }
            }
            else if (!deck.isEmpty()){ // If CPU's hand is empty
                hand2.add(deck.pop());
                System.out.println("The CPU draws a card");
                System.out.print("\n");
            }
            
            frame.updatePlayerCards(hand1);
            frame.updateComputerCards(hand2);
            
        } while (!(deck.isEmpty())); // While the deck still has cards
        
        System.out.println("The deck is empty! The game has finished!");
        System.out.println("You have " + player.getPoints());
        System.out.println("The CPU has " + computer.getPoints());
        
        Thread.sleep(1000);
        
        if (player.getPoints() > computer.getPoints()){ // Determines the winner
            System.out.println("Congratulations! You are the winner!");
        }
        else if (computer.getPoints() > player.getPoints()){
            System.out.println("Looks like you lost!");
        }
        else System.out.println("Its a draw!");
        
        
    }
    
}
