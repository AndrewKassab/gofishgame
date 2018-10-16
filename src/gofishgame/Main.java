package gofishgame;

import graphics.GofishFrame;

public class Main {

	/**
	 * The Main driver for our game of Go Fih.
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		GofishFrame frame = new GofishFrame();
		Gofish game = new Gofish(frame);

	}

}
