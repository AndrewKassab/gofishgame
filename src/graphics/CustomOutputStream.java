package graphics;

import java.io.IOException;
import java.io.OutputStream;
import javax.swing.JTextArea;

/**
 * Handles creation of output stream which redirects
 * the standard output into a text box.
*/
public class CustomOutputStream extends OutputStream {
	
	private JTextArea textArea;
	
	public CustomOutputStream(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	@Override
	public void write(int b) throws IOException {
		// redirects data to the text area
		textArea.append(String.valueOf((char)b));
		// scrolls the text area to the end of the data
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
}
