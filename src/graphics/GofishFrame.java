package graphics;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import gofishgame.Card;
import gofishgame.MyStack;

/**
 * This class handles all GUI components of the Gofish game. 
 * TODO: Turn card image JButtons into JButtons to use instead of JOptionPane.
 * @author PreciseMotion
 * @version 3.0
 */
public class GofishFrame {

	private JFrame mainFrame;
	private JLabel deckLabel;
    // TODO: Turn into buttons instead of Buttons
	private ArrayList<JLabel> computerCardLabels = new ArrayList<JLabel>();
	private ArrayList<JButton> playerCardButtons = new ArrayList<JButton>();
	private JPanel playerPanel;
	private JPanel playerButtonPanel;
	private JTextField playerScore;
	private JTextArea cardCounterText;
	private int cardCounter = 52;
	private JPanel computerPanel;
	private JPanel cpuButtonPanel;
	private JTextArea computerScore;
	private JTextArea outputTextArea;
	private JScrollPane outputScrollPane;
	private Font font1 = new Font("SansSerif", Font.BOLD, 25); // used for score text
	private Font font2 = new Font("SansSerif", Font.BOLD, 17); // used for output window
	private Box b = null; // For creating struts
	
	private ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			buttonPressed(evt);
		}
	};

	/**
	 * The Constructor. Creates a new JFrame and calls the methods required to 
	 * create all swing components that will be present on the frame.
	 */
	public GofishFrame() {
		mainFrame = new JFrame("Go Fish!");
		mainFrame.setSize(900,580);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		createText();
		playerPanel();
		computerPanel();
		mainFrame.add(playerPanel, BorderLayout.SOUTH);
		mainFrame.add(computerPanel, BorderLayout.NORTH);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Creates the player panel and handles all components belonging to it.
	 */
	public void playerPanel() {
		
        playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(100,200));
		playerPanel.setLayout(new BorderLayout());
        // Creates whitespace around border
		playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5)); 

        // Holds card images
		playerButtonPanel = new JPanel();
		playerButtonPanel.setPreferredSize(new Dimension(100,140));
		playerButtonPanel.setBackground(Color.BLACK);
		playerButtonPanel.setLayout(new BoxLayout(playerButtonPanel, BoxLayout.X_AXIS));
		
        // Score counter for player
		playerScore = new JTextField("Player Score: 0 ");
		playerScore.setEditable(false);
		playerScore.setBackground(mainFrame.getBackground());
		playerScore.setBorder(null);
		playerScore.setFont(font1);
		
		playerPanel.add(playerButtonPanel, BorderLayout.SOUTH);
		playerPanel.add(playerScore, BorderLayout.EAST);
		playerPanel.add(cardCounterText, BorderLayout.BEFORE_FIRST_LINE);
	}
	
	/**
	 * Creates the CPU panel and handles all components belonging to it.
	 */
	public void computerPanel() {
		
		computerPanel = new JPanel();
		computerPanel.setPreferredSize(new Dimension(100,330));
		computerPanel.setLocation(0,400);
		computerPanel.setLayout(new BorderLayout());
		// Creates whitespace around border
		computerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5)); 
	
        // Holds card images
		cpuButtonPanel = new JPanel();
		cpuButtonPanel.setPreferredSize(new Dimension(50,170)); 
		cpuButtonPanel.setBackground(Color.BLACK);
		cpuButtonPanel.setLayout(new BoxLayout(cpuButtonPanel, BoxLayout.X_AXIS));
		computerPanel.add(cpuButtonPanel, BorderLayout.NORTH); // Add panel to top of current panel
		BufferedImage deck = null;
	
        // Loads image for deck  of cards located in center.
		try {
			deck = ImageIO.read(getClass().getResource("/back.png"));
		} catch (Exception e) {
			System.out.println("Failed to load deck image - " + e.getMessage());
		}
		
        // Sets up the deck image
		deckLabel = new JLabel(new ImageIcon(deck));
		deckLabel.setPreferredSize(new Dimension(50,100));
		
		// Creates 7 cards to display
		for (int i = 0; i < 7; i++) {
			computerCardLabels.add(new JLabel(new ImageIcon(deck)));
			cpuButtonPanel.add(computerCardLabels.get(i));
			cpuButtonPanel.add(b.createHorizontalStrut(10));
		}
		
		// Create extra cards but don't display, incase CPU holds over 7 cards.
		for (int i = 0; i < 5; i++) {
			computerCardLabels.add(new JLabel(new ImageIcon(deck)));
		}

		computerPanel.add(deckLabel, BorderLayout.CENTER);
		computerPanel.add(computerScore, BorderLayout.LINE_START);
		computerPanel.add(outputScrollPane, BorderLayout.EAST);
	}
    
    /**
     * Handles creation of all text related objects on the panels
    */
	public void createText() {
		
		computerScore = new JTextArea("Score: 0");
		computerScore.setEditable(false);
		computerScore.setPreferredSize(new Dimension(130,50));
		computerScore.setFont(font1);
		computerScore.setBackground(mainFrame.getBackground());
		
		cardCounterText = new JTextArea("Deck: " + cardCounter);
		cardCounterText.setFont(font1);
		cardCounterText.setBackground(null);
		
		outputTextArea = new JTextArea();
		outputTextArea.setFont(font2);
		outputTextArea.setEditable(false);
		outputTextArea.setLineWrap(true);
		outputTextArea.setText("");
		
		outputScrollPane = new JScrollPane(outputTextArea);
		outputScrollPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		outputScrollPane.setPreferredSize(new Dimension(450,300));
		
	}
	
	/**
	 * Handles the event in which a button on the player panel
	 * is pressed. (The player is selecting a card to prompt for)
	 * @param evt button pressed
	 */
	public void buttonPressed(ActionEvent evt) {
		
	}
	
	/**
	 * Updates the amount of cards showing on the computer player's 
	 * side of the game depending on how many cards they are holding.
	 * @param hand The hand of cards being updated
	 */
	public void updateComputerCards(ArrayList<Card> hand) {
		cpuButtonPanel.removeAll();
		for (int i = 0; i < hand.size(); i++) {
			cpuButtonPanel.add(computerCardLabels.get(i));
			cpuButtonPanel.add(b.createHorizontalStrut(10));
		}
		cpuButtonPanel.revalidate();
		cpuButtonPanel.repaint();
	}
	
	/**
	 * Updates the cards being displayed on the player's side.
	 * @param hand The hand of cards being updated
	 */
	public void updatePlayerCards(ArrayList<Card> hand) {
		playerButtonPanel.removeAll();
		playerCardButtons.clear();
		BufferedImage tempImage = null;
		for (int i = 0; i < hand.size(); i++) {
			playerCardButtons.add(new JButton(new ImageIcon(hand.get(i).getImg())));
			playerCardButtons.get(i).setBorder(BorderFactory.createEmptyBorder());
			playerCardButtons.get(i).setContentAreaFilled(false);
			playerCardButtons.get(i).addActionListener()
			playerButtonPanel.add(playerCardButtons.get(i));
			playerButtonPanel.add(b.createHorizontalStrut(10));
		}
		playerButtonPanel.revalidate();
		playerButtonPanel.repaint();
	}
	
	/**
	 * Updates the scoreboard with appropriate values
	 * @param pScore The player's current score
	 * @param cScore The CPU's current score
	 */
	public void updateScoreboard(int pScore, int cScore) {
		playerScore.setText("Score: " + pScore);
		computerScore.setText("Score: " + cScore);
	}
	
	/**
	 * Updates the card count for the deck whenever a card is 
	 * popped from the stack.
	 * @param <T>
	 */
	public void updateCardCount() {
		cardCounter--;
		cardCounterText.setText("Deck: " + cardCounter );
	}
	
	/**
	 * Used to access the output text area for stdout redirection
	 * @return the output box
	 */
	public JTextArea getOutputArea() {
		return outputTextArea;
	}
}
