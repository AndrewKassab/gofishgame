package gofishgame;

import graphics.GofishFrame;

public class Main {

	/**
	 * The Main driver for our game of Go Fish.
	 * @author PreciseMotion
	 * @version 3.0
	 * @param args
	 * @throws InterruptedException thread.sleep()
	 */
	public static void main(String[] args) throws InterruptedException {
		
		GofishFrame frame = new GofishFrame();
		Gofish game = new Gofish(frame);

	}

}
