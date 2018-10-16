package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import gofishgame.Card;

/**
 * TODO: Add in continue button instead of using jOptionPane
 * @author PreciseMotion
 *
 */
public class GofishFrame {

	private JFrame mainFrame;
	private JLabel deckLabel;
	private ArrayList<JLabel> computerCardLabels = new ArrayList<JLabel>();
	private ArrayList<JLabel> playerCardLabels = new ArrayList<JLabel>();
	private JPanel playerPanel;
	private JPanel playerLabelPanel;
	private JTextField playerScore;
	private JPanel computerPanel;
	private JPanel cpuLabelPanel;
	private JTextArea computerScore;
	private JTextArea outputTextArea;
	private JScrollPane outputScrollPane;
	private Font font1 = new Font("SansSerif", Font.BOLD, 25); // used for score text
	private Font font2 = new Font("SansSerif", Font.BOLD, 18); // used for output window
	private Box b = null; // For creating struts
	
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
		
		playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5)); 

		playerLabelPanel = new JPanel();
		playerLabelPanel.setPreferredSize(new Dimension(100,140));
		playerLabelPanel.setBackground(Color.BLACK);
		playerLabelPanel.setLayout(new BoxLayout(playerLabelPanel, BoxLayout.X_AXIS));
		
		playerScore = new JTextField();
		playerScore.setText("Player Score: 0 ");
		playerScore.setEditable(false);
		playerScore.setBackground(mainFrame.getBackground());
		playerScore.setBorder(null);
		playerScore.setFont(font1);
		
		playerPanel.add(playerLabelPanel, BorderLayout.SOUTH);
		playerPanel.add(playerScore, BorderLayout.LINE_END);
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
	
		cpuLabelPanel = new JPanel();
		cpuLabelPanel.setPreferredSize(new Dimension(50,170)); // Contains card images
		cpuLabelPanel.setBackground(Color.BLACK);
		cpuLabelPanel.setLayout(new BoxLayout(cpuLabelPanel, BoxLayout.X_AXIS));
		computerPanel.add(cpuLabelPanel, BorderLayout.NORTH); // Add panel to top of current panel
		BufferedImage deck = null;
		
		// TODO: Change deck and card backing images later
		
		try {
			deck = ImageIO.read(getClass().getResource("/back.png"));
		} catch (Exception e) {
			System.out.println("Failed to load deck image - " + e.getMessage());
		}
		
		deckLabel = new JLabel(new ImageIcon(deck));
		deckLabel.setPreferredSize(new Dimension(50,100));
		
		// Creates 7 cards to display
		for (int i = 0; i < 7; i++) {
			computerCardLabels.add(new JLabel(new ImageIcon(deck)));
			cpuLabelPanel.add(computerCardLabels.get(i));
			cpuLabelPanel.add(b.createHorizontalStrut(10));
		}
		
		// Create extra cards but don't display, incase CPU holds over 7 cards.
		for (int i = 0; i < 5; i++) {
			computerCardLabels.add(new JLabel(new ImageIcon(deck)));
		}
		
		
		
		computerPanel.add(deckLabel, BorderLayout.CENTER);
		computerPanel.add(computerScore, BorderLayout.LINE_START);
		computerPanel.add(outputScrollPane, BorderLayout.EAST);
		
	}
	
	public void createText() {
		
		computerScore = new JTextArea();
		computerScore.setText("Score: 0");
		computerScore.setEditable(false);
		computerScore.setPreferredSize(new Dimension(130,50));
		computerScore.setFont(font1);
		computerScore.setBackground(mainFrame.getBackground());
		
		outputTextArea = new JTextArea();
		outputTextArea.setFont(font2);
		outputTextArea.setEditable(false);
		outputTextArea.setLineWrap(true);
		outputTextArea.setText("");
		
		outputScrollPane = new JScrollPane(outputTextArea);
		outputScrollPane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GRAY));
		outputScrollPane.setPreferredSize(new Dimension(350,250));
		
	}
	
	/**
	 * Updates the amount of cards showing on the computer player's 
	 * side of the game depending on how many cards they are holding.
	 * @param hand
	 */
	public void updateComputerCards(ArrayList<Card> hand) {
		cpuLabelPanel.removeAll();
		for (int i = 0; i < hand.size(); i++) {
			cpuLabelPanel.add(computerCardLabels.get(i));
			cpuLabelPanel.add(b.createHorizontalStrut(10));
		}
		cpuLabelPanel.revalidate();
		cpuLabelPanel.repaint();
	}
	
	/**
	 * Updates the cards being displayed on the player's side.
	 * @param hand
	 */
	public void updatePlayerCards(ArrayList<Card> hand) {
		playerLabelPanel.removeAll();
		playerCardLabels.clear();
		BufferedImage tempImage = null;
		for (int i = 0; i < hand.size(); i++) {
			playerCardLabels.add(new JLabel(new ImageIcon(hand.get(i).getImg())));
			playerLabelPanel.add(playerCardLabels.get(i));
			playerLabelPanel.add(b.createHorizontalStrut(10));
		}
		playerLabelPanel.revalidate();
		playerLabelPanel.repaint();
	}
	
	/**
	 * Updates the scoreboard with appropriate values
	 * @param pScore The player's current score
	 * @param cScore The CPU's current score
	 */
	public void updateScoreBoard(int pScore, int cScore) {
		playerScore.setText("Score: " + pScore);
		computerScore.setText("Score: " + cScore);
	}
	
	/**
	 * Updates text in output text field so user can keep track of the game
	 * @param text Text being inserted into the output field
	 */
	public void updateOutput(String text) {
		outputTextArea.setText(outputTextArea.getText() + text + "\n");
	}
	
	/**
	 * Used to access the output text area for stdout redirection
	 * @return
	 */
	public JTextArea getOutputArea() {
		return outputTextArea;
	}
}
