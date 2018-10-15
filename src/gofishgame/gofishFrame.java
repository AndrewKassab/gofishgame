package gofishgame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class gofishFrame {

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
	
	public gofishFrame() {
		
		mainFrame = new JFrame("Go Fish!");
		mainFrame.setSize(900,580);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.BLACK));
		playerPanel();
		computerPanel();
		mainFrame.add(playerPanel, BorderLayout.SOUTH);
		mainFrame.add(computerPanel, BorderLayout.NORTH);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public void playerPanel() {
		playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(100,200));
		playerPanel.setLayout(new BorderLayout());
		
		// Creates whitespace around border
		playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 20));
		
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
	 * TODO: Move textArea handling to its own method
	 */
	public void computerPanel() {
		
		computerPanel = new JPanel();
		computerPanel.setPreferredSize(new Dimension(100,330));
		computerPanel.setLocation(0,400);
		computerPanel.setLayout(new BorderLayout());
		
		// Creates whitespace around border
		computerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 20)); 
		
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
		
		computerScore = new JTextArea();
		computerScore.setText("Score: 0");
		computerScore.setEditable(false);
		computerScore.setPreferredSize(new Dimension(130,50));
		computerScore.setFont(font1);
		computerScore.setBackground(mainFrame.getBackground());
		
		outputTextArea = new JTextArea();
		outputTextArea.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		outputTextArea.setFont(font2);
		outputTextArea.setEditable(false);
		outputTextArea.setLineWrap(true);
		outputTextArea.setText("");
		
		outputScrollPane = new JScrollPane(outputTextArea);
		outputScrollPane.setPreferredSize(new Dimension(350,200));
		
		computerPanel.add(deckLabel, BorderLayout.CENTER);
		computerPanel.add(computerScore, BorderLayout.LINE_START);
		computerPanel.add(outputScrollPane, BorderLayout.EAST);
		
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
