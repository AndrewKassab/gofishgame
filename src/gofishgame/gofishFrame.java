package gofishgame;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class gofishFrame {

	JFrame mainFrame;
	JLabel deckLabel;
	ArrayList<JLabel> computerCardLabels = new ArrayList<JLabel>();
	ArrayList<JLabel> playerCardLabels = new ArrayList<JLabel>();
	JPanel playerPanel;
	JPanel playerLabelPanel;
	JPanel computerPanel;
	JPanel cpuLabelPanel;
	Box b = null; // For creating struts
	
	public gofishFrame() {
		
		mainFrame = new JFrame();
		mainFrame.setSize(800,580);
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
		playerPanel.setPreferredSize(new Dimension(100,290));
		playerPanel.setBackground(Color.GREEN);
		playerPanel.setLayout(new BorderLayout());
		
		// Creates whitespace around border
		playerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 20));
		
		playerLabelPanel = new JPanel();
		playerLabelPanel.setPreferredSize(new Dimension(100,180));
		playerLabelPanel.setBackground(Color.BLACK);
		playerLabelPanel.setLayout(new BoxLayout(playerLabelPanel, BoxLayout.X_AXIS));
		
		playerPanel.add(playerLabelPanel, BorderLayout.SOUTH);
	}
	
	public void computerPanel() {
		
		computerPanel = new JPanel();
		computerPanel.setPreferredSize(new Dimension(100,310));
		computerPanel.setLocation(0,400);
		computerPanel.setBackground(Color.BLUE);
		computerPanel.setLayout(new BorderLayout());
		
		// Creates whitespace around border
		computerPanel.setBorder(BorderFactory.createEmptyBorder(20, 10, 0, 20)); 
		
		cpuLabelPanel = new JPanel();
		cpuLabelPanel.setPreferredSize(new Dimension(100,180)); // Contains card images
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
		
		computerPanel.add(deckLabel, BorderLayout.SOUTH);
		
	}
	
	/**
	 * Updates the amount of cards showing on the computer player's 
	 * side of the game depending on how many cards they are holding.
	 * @param hand
	 */
	public void updateComputerCards(ArrayList<Integer> hand) {
		cpuLabelPanel.removeAll();
		for (int i = 0; i < hand.size(); i++) {
			cpuLabelPanel.add(computerCardLabels.get(i));
			cpuLabelPanel.add(b.createHorizontalStrut(10));
		}
		cpuLabelPanel.revalidate();
		cpuLabelPanel.repaint();
	}
}
