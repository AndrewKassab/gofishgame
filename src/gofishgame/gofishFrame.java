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
	private JTextField computerScore;
	private Font font1 = new Font("SansSerif", Font.BOLD, 25);
	private Box b = null; // For creating struts
	
	public gofishFrame() {
		
		mainFrame = new JFrame();
		mainFrame.setSize(900,580);
		mainFrame.setLayout(new BorderLayout());
		playerPanel();
		computerPanel();
		mainFrame.add(computerPanel, BorderLayout.NORTH);
		mainFrame.add(playerPanel, BorderLayout.SOUTH);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	public void playerPanel() {
		playerPanel = new JPanel();
		playerPanel.setPreferredSize(new Dimension(100,230));
		playerPanel.setLayout(new BorderLayout());
		playerPanel.setBackground(Color.BLUE);
		
		// Creates whitespace around border
		playerPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 20));
		
		playerLabelPanel = new JPanel();
		playerLabelPanel.setPreferredSize(new Dimension(100,160));
		playerLabelPanel.setBackground(Color.BLACK);
		playerLabelPanel.setLayout(new BoxLayout(playerLabelPanel, BoxLayout.X_AXIS));
		
		playerScore = new JTextField();
		playerScore.setText("Score: 0 ");
		playerScore.setEditable(false);
		playerScore.setFont(font1);
		playerScore.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		
		playerPanel.add(playerLabelPanel, BorderLayout.SOUTH);
		playerPanel.add(playerScore, BorderLayout.LINE_END);
	}
	
	public void computerPanel() {
		
		computerPanel = new JPanel();
		computerPanel.setPreferredSize(new Dimension(100,310));
		computerPanel.setLocation(0,400);
		computerPanel.setLayout(new BorderLayout());
		
		// Creates whitespace around border
		computerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 20)); 
		
		cpuLabelPanel = new JPanel();
		cpuLabelPanel.setPreferredSize(new Dimension(50,180)); // Contains card images
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
		
		computerScore = new JTextField();
		computerScore.setText("Score: 0");
		computerScore.setEditable(false);
		computerScore.setFont(font1);
		computerScore.setPreferredSize(new Dimension(100,100));
		
		computerPanel.add(deckLabel, BorderLayout.SOUTH);
		computerPanel.add(computerScore);
		
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
	
	public void updateScoreBoard(int score) {
		playerScore.setText("Score: " + score);
	}
}
