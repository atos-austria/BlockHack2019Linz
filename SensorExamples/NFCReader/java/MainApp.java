package nfc_acr122;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.smartcardio.*;


public class MainApp {

	public static void main(String[] args) {
		List<CardTerminal> terminals = new ArrayList<CardTerminal>();
		Card card = null;
		CardChannel channel = null;
		
		try {
			// Display the list of terminals
			TerminalFactory factory = TerminalFactory.getDefault();
			terminals = factory.terminals().list();
			System.out.println("Terminals: " + terminals);
		} catch (Exception e) {
			System.out.println("Problem listing the terminals: " + e.toString());
			System.exit(-1);
		}

		// Use the first terminal
		CardTerminal terminal = terminals.get(0);

		
		// try to read cards while (true) {
		while (true) {

			try {
				// Connect with the card
				card = terminal.connect("*");
				//System.out.println("card: " + card);
				//System.out.println("ATR: " + Arrays.toString(card.getATR().getBytes()));
				channel = card.getBasicChannel();
				
				// Start with something simple, read UID
		        byte[] baReadUID = new byte[5];
		        baReadUID = new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00 };
		        String uid_string = send(baReadUID, channel);
		        System.out.println("UID: " + uid_string);
	
				// Disconnect the card
				card.disconnect(false);
				
			} catch (javax.smartcardio.CardNotPresentException e) {
				//System.out.println("No card present");
			} catch (Exception e) {
				System.out.println("Problem connecting card: " + e.toString());
				System.exit(-1);
			}

			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public static String send(byte[] cmd, CardChannel channel) {

	    String res = "";

	    byte[] baResp = new byte[258];
	    ByteBuffer bufCmd = ByteBuffer.wrap(cmd);
	    ByteBuffer bufResp = ByteBuffer.wrap(baResp);

	    // output = The length of the received response APDU
	    int output = 0;

	    try {
	    	output = channel.transmit(bufCmd, bufResp);
	    } catch (CardException ex) {
	        ex.printStackTrace();
	    }

	    for (int i = 0; i < output; i++) {
	        res += String.format("%02X", baResp[i]);
	        // The result is formatted as a hexadecimal integer
	    }

	    return res;
	}
}
