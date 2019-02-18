package app;

import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import multichain.command.MultiChainCommand;
import multichain.command.MultichainException;
import multichain.command.StreamCommand;
import multichain.object.StreamKeyItem;

public class SimpleExample {

    public static void main(String[] args) {
        String host = "***.***.***.***";
        String port = "****";
        String user = "****";
        String pass = "****";
        MultiChainCommand myChainCmd = new MultiChainCommand(host, port, user, pass);

        //Use this method to check if your connection is working
        //There will be some Informations about your Chain printed to the console
        //Please check if the name of the chain is the one for your Team!
        System.out.println("Getting connection info from multichain");
        System.out.println(getInformation(myChainCmd));

        //Here we are writing to the root stream which is already initiated for you
        //you can also create other streams and publish information to them
        System.out.println("Writing some data to stream");
        System.out.println(publishToStream("root", "mySampleKey", ascii2hex("this is some example data..."), myChainCmd));

        //Here we are reading all published information from the root stream
        System.out.println("Querying multichain");
        List < StreamKeyItem > resultList = listStreamItems("root", myChainCmd);
        for (StreamKeyItem k: resultList) {
            System.out.println(k.toString());
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

    public static List < StreamKeyItem > listStreamItems(String streamName, MultiChainCommand cmd) {
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