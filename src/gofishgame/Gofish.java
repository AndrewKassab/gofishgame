package gofishgame;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import graphics.CustomOutputStream;
import graphics.GofishFrame;

/**
 * Go Fish game. This class contains all objects and methods 
 * to simulate a game of go fish. The user will be given a hand
 * of cards and will play against a CPU. 
 * TODO: Remove the need for Thread.sleep() and interrupted exceptions
 * by using buttons in the GUI.
 * @author PreciseMotion
 * @version 3.0
 */
public class Gofish {
    
	private GofishFrame frame;
	private MyStack<Card> deck = new MyStack<Card>(); // Creates stack for deck of cards
	private ArrayList<Card> hand1 = new ArrayList<Card>(); // Creates ArrayList for Player's hand
	private ArrayList<Card> hand2 = new ArrayList<Card>(); // Creates ArrayList for CPU's hand
	private PrintStream printStream;
	private int indexCpuChosen = 0;
	private String pchosen;
	private int indexPlayerChosen = 0;
	private Player player;
	private Player computer;
	
	/**
	 * The constructor. Sets values for each variable and prepares the
	 * player's and their hands for the game execution.
	 * @throws InterruptedException 
	 */
	public Gofish(GofishFrame frame) throws InterruptedException {
		
		this.frame = frame;
		
		printStream = new PrintStream(new CustomOutputStream(frame.getOutputArea()));
		System.setOut(printStream);
		
		initCards();
        gameLoop(); // Begins game
		
	}
	
	/**
	 * Handles initialization of the card deck and each player's hand.
	 * Sets up the game for it to begin.
	 */
	public void initCards() {
		
		createShuffledDeck();
		
		for (int i = 0; i < 7; i++){ // Player draws 7 cards
            hand1.add(deck.pop());
            frame.updateCardCount();
        }
        
        for (int i = 0; i < 7; i++){ // CPU draws 7 cards
            hand2.add(deck.pop());
            frame.updateCardCount();
        }            
        
        player = new Player(hand1,true);     
        frame.updatePlayerCards(hand1);
        
        player.CompareHand(); // Check for matches
        frame.updatePlayerCards(hand1);
        
        computer = new Player(hand2,false); 
        
        computer.CompareHand(); // Check for matches
        
        System.out.println("------------------------------------------------");
        frame.updateScoreboard(player.getPoints(), computer.getPoints());
        frame.updateComputerCards(hand2);
        
	}
	
	/**
	 * Handles the loop to execute the game.
	 * @throws InterruptedException pause game for user to process results
	 */
	public void gameLoop() throws InterruptedException {
		
		//do { 

			//if (hand1.isEmpty()) {
				// draw card method
			//}
            playerTurn();
            
            frame.updatePlayerCards(hand1);
            frame.updateComputerCards(hand2);
            Thread.sleep(3000); // Allow player to process results
           
            computerTurn();

            frame.updatePlayerCards(hand1);
            frame.updateComputerCards(hand2);
            
        //} while (!(deck.isEmpty())); // While the deck still has cards
        // ActionListener for when the deck is empty, and both hands are empty, call gameOver
        // gameOver();

	}
	
	public void playerTurn() {
		
		// If player has cards
		if (!(hand1.isEmpty())){
		            	
		boolean cont = false;
		                
		    do{  
		                	
			    // Player requests card
		        pchosen = JOptionPane.showInputDialog("Which card would you like to " +
                    "inquire your opponent for?");

                // Makes sure player has requested card
	            for (int i = 0; i < hand1.size(); i++){ 
	        	    if (Character.toUpperCase(pchosen.charAt(0)) == hand1.get(i).getCard().charAt(0))
                    {  
	        		    indexPlayerChosen = i;               
                        cont = true;
                        break;
		            }
	            }
		                    
	            if (cont != true){
	        	    System.out.println("Please try again, you do not have a " + pchosen + ".");
	            }
		                  
		    } while (cont != true); // Until player requests a valid card
		               
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
                    System.out.println("There is a match.\nYour opponent gives up their " 
                        + SeekingCard.getCard() + "."); 
                    
                    frame.updateScoreboard(player.getPoints(), computer.getPoints());
		                    
		            System.out.println("--------------------------------------------------");
                }
                else { // If CPU did not have requested card
                    System.out.println("Your opponent does not have a " + SeekingCard.getCard() 
                        + ".\nSorry, go fish.");
                    hand1.add(deck.pop());
                    frame.updateCardCount();
                    System.out.println("You drew a " + hand1.get(hand1.size()- 1).getCard() + ".");
                    player.setCards(hand1);
                    player.CompareHand();
                    frame.updateScoreboard(player.getPoints(), computer.getPoints());
		                    
                    System.out.print("--------------------------------------------------\n");
                }
            }
		    
            // If player's hand is empty
	        else { 
                hand1.add(deck.pop());
                frame.updateCardCount();
                System.out.println("You drew a " + hand1.get(hand1.size()- 1).getCard());
		                
                System.out.println("--------------------------------------------------");
            }
					
	}
	
	public void computerTurn() {
        if (!(hand2.isEmpty())){ // If CPU's hand is not empty
            
            int currentChosen = indexCpuChosen;
            
            if (hand2.size() == 1){ // If there is only one card in CPU's hand, selects that. 
                indexCpuChosen = 0;
            }
            else{
                // Loops until card selected is not the same as what the CPU selected last turn     
                do{ 
                    // Selects random card from CPU's hand                        
                    indexCpuChosen = ThreadLocalRandom.current().nextInt(0, hand2.size());
                } while (indexCpuChosen == currentChosen); 
            }
            
            System.out.println("Your opponent is looking for a " 
                + hand2.get(indexCpuChosen).getCard() + ".");
            Card CardWanted = hand2.get(indexCpuChosen);
        
            int current = computer.getPoints();

            // Check if player has requested card 
            for (int i = 0; i < hand1.size(); i++){ 
                if ( hand2.get(indexCpuChosen).getCard().equals(hand1.get(i).getCard())){
                    computer.incrementPoints();   
                    hand2.remove(indexCpuChosen);
                    hand1.remove(i);
                    break;
                }
            }
            // If player has requested card
            if (computer.getPoints() > current){ 
                System.out.println("You've given up your " + CardWanted.getCard() + "."); 
                frame.updateScoreboard(player.getPoints(), computer.getPoints());                    
                System.out.println("--------------------------------------------------");
            }
            // If player does not have requested card
            else{ 
                System.out.println("Your opponent draws a card.");
                hand2.add(deck.pop());
                frame.updateCardCount();
                computer.CompareHand();
                frame.updateScoreboard(player.getPoints(), computer.getPoints());
                System.out.println("--------------------------------------------------");
            }
        }
        else if (!deck.isEmpty()){ // If CPU's hand is empty
            hand2.add(deck.pop());
            frame.updateCardCount();
            System.out.println("The CPU draws a card.");
            System.out.println("--------------------------------------------------");
        }
	}

    /**
     * Handles the end of the game, determines and displays
     * the winner.
     * @throws InterruptedException 
    */
    public void gameOver() throws InterruptedException{
        System.out.println("The deck is empty!\nThe game has finished!");
        
        Thread.sleep(1000);
        
        // Determines the winner 
        if (player.getPoints() > computer.getPoints()){ 
            System.out.println("Congratulations!\nYou are the winner!");
        }
        else if (computer.getPoints() > player.getPoints()){
            System.out.println("Looks like you lost!");
        }
        else System.out.println("Its a draw!");
        
    }
	
    /**
     * Creates a standard shuffled deck of 52 cards.
     * @return the shuffled deck in the form of a stack.
     */
    public void createShuffledDeck(){ 
        
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
        		sixSpades,sixClubs,sixHearts,sixDiamonds,sevenSpades,sevenClubs,sevenHearts,
                sevenDiamonds, eightSpades,eightClubs,eightHearts,eightDiamonds,nineSpades,
                nineClubs,nineHearts,nineDiamonds,tenSpades,tenClubs,tenHearts,tenDiamonds,
                jackSpades,jackClubs,jackHearts,jackDiamonds, queenSpades, queenClubs, 
                queenHearts, queenDiamonds, kingSpades,kingClubs,kingHearts,kingDiamonds};
        
        Random rand = new Random();
       
        // puts card into deck
        for (int i = deckArray.length - 1; i > 0; i--) {
        	int index = rand.nextInt(i+1);
        	Card a = deckArray[index];
        	deckArray[index] = deckArray[i];
        	deckArray[i] = a;
        }
        
        // pushes the array into a stack for the deck
        for (int i = 0; i < 52; i++) {
        	deck.push(deckArray[i]);
        }
        
    }
    
    
}
