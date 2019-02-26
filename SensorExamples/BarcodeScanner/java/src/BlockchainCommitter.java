
import multichain.command.MultiChainCommand;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;
import multichain.command.StreamCommand;
import multichain.object.StreamKeyItem;

public class BlockchainCommitter {

    public static void main(String[] args) {
        String host = "***.***.***.***";
        String port = "****";
        String user = "****";
        String pass = "****";
        MultiChainCommand myChainCmd = new MultiChainCommand(host, port, user, pass);

        System.out.println(getInformation(myChainCmd));

        Scanner inputReader = null;
        try {
            inputReader = new Scanner(System.in);
            while(true) {
                System.out.print("Input data: ");
                String line = inputReader.nextLine();
                System.out.println(publishToStream("root", "cmd_line_input_java", ascii2hex(line), myChainCmd));
            }
        } catch (Exception ex) {
            inputReader.close();
            System.out.println("shutting down.");
        }
    }


    public static String getInformation(MultiChainCommand cmd) {
        try {
            return cmd.getChainCommand().getInfo();
        } catch (MultichainException e) {
            System.err.println(e.getMessage());
            return "***Error*** " + e.getMessage();
        }
    }

    public static String publishToStream(String streamName, String key, String data, MultiChainCommand cmd) {
        StreamCommand streamCommand = cmd.getStreamCommand();
        try {
            return "Published info in following transaction: " + streamCommand.publish(streamName, key, data);
        } catch (MultichainException e) {
            System.err.println(e.getMessage());
            return "***Error*** " + e.getMessage();
        }
    }

    public static List<StreamKeyItem> listStreamItems(String streamName, MultiChainCommand cmd) {
        StreamCommand streamCommand = cmd.getStreamCommand();
        try {
            return streamCommand.listStreamItems(streamName);
        } catch (MultichainException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    //the following functions are provided to convert the data stored in the multichain
    //all data published to the chain must be a hex string
    public static String ascii2hex(String arg) {
        return Hex.encodeHexString(arg.getBytes(Charset.defaultCharset()));
    }

    public static String hex2ascii(String arg) throws DecoderException {
        byte[] bytes = Hex.decodeHex(arg.toCharArray());
        return new String(bytes, Charset.defaultCharset());
    }
}
