package gofishgame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.applet.*;

import javax.imageio.ImageIO;

/**
 * Object class for a playing card
 * @author PreciseMotion
 * @version 2.0
 */
public class Card {
	
	String card;
	BufferedImage img = null;
	
	public Card(String c, String path) {
		card = c;
		BufferedImage tempImage = null;
		try {
			tempImage = ImageIO.read(getClass().getResource(path));
		} catch (Exception e) {
			System.out.println("Error loading image - " + path + " " + e.getMessage());
			System.exit(0);
		}
		img = tempImage;
	}
	
	public String getCard() {
		return card;
	}

	public void setCard(String c) {
		this.card = c;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}	
	
}
