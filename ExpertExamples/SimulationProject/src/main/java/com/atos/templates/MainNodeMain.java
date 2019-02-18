package com.atos.templates;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author S. Albers
 *
 */
public class MainNodeMain {

	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * Main program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Options options = new Options(true);
		Config config = new Config();
		boolean isLaunched = false;
		int option;
		
		while (isLaunched == false) {
			printInfo("=============================================MAIN--NODE=============================================");
			printInfo("*****CONFIGURATION*****");
			options.listOptions(options.getConfigOptions());
			
			option = readInteger();
			switch(option) {
			case 1:
				config.createMainNode();
				break;
			case 2:
				config.initMainNode();
				break;
			case 3:
				isLaunched = config.launchBlockchain();
				break;
			default: 
				printInfo("Wrong input! Please try again.");
			}
		}	
		
		Multichain multichain = config.getMultichain();
		while(true) {
			printInfo("=============================================MAIN--NODE=============================================");
			printInfo("*****MAIN MENU*****");
			options.listOptions(options.getMainMenuOptions());
			
			option = readInteger();
			switch(option) {
			case 1:
				info(multichain, options);
				break;
			case 2:
				permissions(multichain, options);
				break;
			case 3:
				streams(multichain, options);
				break;
			case 4:
				assets(multichain, options);
				break;
			default: 
				printInfo("Wrong input! Please try again.");
			}
		}
	}
	
	/**
	 * General information
	 * 
	 * @param multichain
	 * @param options
	 */
	public static void info(Multichain multichain, Options options) {
		while (true) {
			printInfo("=============================================MAIN--NODE=============================================");
			printInfo("*****GENERAL INFORMATION******");
			options.listOptions(options.getInfoOptions());
			
			int option = readInteger();
			String command;
			
			switch(option) {
			case 1: 
				printString(multichain.getInformation());
				break;
			case 2:
				printString(multichain.help());
				break;
			case 3:
				printInfo("Enter the command for which help should be displayed");
				command = readString();
				printString(multichain.help(command));
				break;
			case 4:
				printList(multichain.getAddresses());
				break;
			case 5:
				return;
			default: 
				printInfo("Wrong input! Please try again.");
			}
		}
	}

	/**
	 * Permission management
	 * 
	 * @param multichain
	 * @param options
	 */
	public static void permissions(Multichain multichain, Options options) {
		while (true) {
			printInfo("=============================================MAIN--NODE=============================================");
			printInfo("*****PERMISSION MANAGEMENT*****");
			options.listOptions(options.getPermissionOptions());
			
			int option = readInteger();
			int permission;
			String address;
			String streamName;
			
			switch(option) {
			case 1:
				printList(multichain.listPermissions());
				break;
			case 2:
				options.listOptions(options.getPermissions());
				printInfo("Enter the permission which should be listed:");
				permission = readInteger();
				printList(multichain.listPermissions(permission));
				break;
			case 3:
				options.listOptions(options.getPermissions());
				printInfo("Enter the permission which should be listed:");
				permission = readInteger();
				printInfo("Enter address of the desired node:");
				address = readString();
				printList(multichain.listPermissions(permission, address));
				break;
			case 4:
				printInfo("Enter the address to which a permission should be granted:");
				address = readString();
				options.listOptions(options.getPermissions());
				printInfo("Enter the permission to be granted:");
				permission = readInteger();
				printString("Transaction ID:", multichain.grantPermission(address, permission));
				break;
			case 5:
				printInfo("Enter the address to which a permission should be revoked:");
				address = readString();
				options.listOptions(options.getPermissions());
				printInfo("Enter the permission to be revoked:");
				permission = readInteger();
				printString("Transaction ID:", multichain.revokePermission(address, permission));
				break;
			case 6:
				printInfo("Enter the address to which write permission should be granted:");
				address = readString();
				printInfo("Enter name of the stream for which write permission should be granted:");
				streamName = readString();
				printString("Transaction ID:", multichain.grantWrite(address, streamName));
				break;
			case 7:
				return;
			default: 
				printInfo("Wrong input! Please try again.");
			}
		}
	}
	
	/**
	 * Streams
	 * 
	 * @param multichain
	 * @param options
	 */
	public static void streams(Multichain multichain, Options options) {
		while (true) {
			printInfo("=============================================MAIN--NODE=============================================");
			printInfo("*****STREAMS******");
			options.listOptions(options.getStreamOptions());
			
			int option = readInteger();
			String streamName;
			String key;
			String data;
			
			switch(option) {
			case 1:
				printInfo("Enter name of the stream that should be created:");
				streamName = readString();
				printString("Transaction ID:", multichain.createStream(streamName));
				break;
			case 2:
				printInfo("Enter name of the stream that should be used for publishing:");
				streamName = readString();
				printInfo("Enter name of key that should be used for publishing:");
				key = readString();
				printInfo("Enter data that should be published:");
				data = readString();
				printString("Transaction ID:", multichain.publish(streamName, key, data));
				break;
			case 3:
				printInfo("Enter name of the stream that should be subscribed to:");
				streamName = readString();
				printString(multichain.subscribe(streamName));
				break;
			case 4:
				printInfo("Enter name of the stream that should be unsubscribed to:");
				streamName = readString();
				printString(multichain.unsubscribe(streamName));
				break;
			case 5:
				printList(multichain.listStreams());
				break;
			case 6:
				printInfo("Enter name of the stream for which the items should be listed:");
				streamName = readString();
				printList(multichain.listStreamItems(streamName));
				break;
			case 7:
				printInfo("Enter name of the stream for which the keys should be listed:");
				streamName = readString();
				printList(multichain.listStreamKeys(streamName));
				break;
			case 8:
				printInfo("Enter name of the stream for which the key items should be listed:");
				streamName = readString();
				printInfo("Enter name of the key for which the items should be listed:");
				key = readString();
				printList(multichain.listStreamKeyItems(streamName, key));
				break;
			case 9: 
				return;
			default: 
				printInfo("Wrong input! Please try again.");
			}
		}
	}
	
	/**
	 * Assets
	 * 
	 * @param multichain
	 * @param options
	 */
	public static void assets(Multichain multichain, Options options) {
		while (true) {
			printInfo("=============================================MAIN--NODE=============================================");
			printInfo("*****ASSETS*****");
			options.listOptions(options.getAssetOptions());
			
			int option = readInteger();
			String address;
			String assetName;
			float quantity;
			float unit;
			
			switch(option) {
			case 1:
				printInfo("Enter address to which an asset should be issued:");
				address = readString();
				printInfo("Enter name of the asset:");
				assetName = readString();
				printInfo("Enter initial amount of units (e.g. 1234.56):");
				quantity = readFloat();
				printInfo("Enter the size of a unit (e.g. 0.01 for cents):");
				unit = readFloat();
				printString("Transaction ID:", multichain.issueAsset(address, assetName, quantity, unit));
				break;
			case 2:
				printInfo("Enter address to which an asset should be sent:");
				address = readString();
				printInfo("Enter name of the asset:");
				assetName = readString();
				printInfo("Enter total amount of units to be sent (e.g. 1234.56):");
				quantity = readFloat();
				printString("Transaction ID:", multichain.sendAsset(address, assetName, quantity));
				break;
			case 3:
				printList(multichain.listAssets());
				break;
			case 4:
				printList(multichain.getAssetBalances());
				break;
			case 5:
				return;
			default: 
				printInfo("Wrong input! Please try again.");	
			}
		}
	}
	
	/**
	 * Print information to the console
	 * 
	 * @param string
	 */
	public static void printInfo(String info) {
		System.out.println(info);
	}
	
	/**
	 * Print a returned string to the console
	 * 
	 * @param output
	 */
	public static void printString(String output) {
		if (output != null) {
			System.out.println("====================================================================================================");
			System.out.println(output);
		} 
	}
	
	/**
	 * Print a returned string to the console
	 * 
	 * @param info
	 * @param output
	 */
	public static void printString(String info, String output) {
		if (info != null && output != null) {
			System.out.println("====================================================================================================");
			System.out.println(info);
			System.out.println(output);
		} 
	}
	
	/**
	 * Print a returned list to the console
	 * 
	 * @param list
	 */
	public static void printList(List<?> list) {
		if (list != null) {
			System.out.println("====================================================================================================");
			for (int i=0; i<list.size(); i++) {
				System.out.println(list.get(i).toString());
			}
		} else {
			System.out.println("No items in list!");
		}	
	}
	
	/**
	 * Read a string from the console
	 * 
	 * @return the string
	 */
	public static String readString() {
		String string = scanner.next();
		return string;
	}
	
	/**
	 * Read an integer from the console
	 * 
	 * @return the integer value
	 */
	public static int readInteger() {
		int num = 0;
		try {
			num = scanner.nextInt();
		} catch (Exception e) {
			System.err.println("Only integer values allowed!");
			scanner.nextLine();
			num = readInteger();
		}
		return num;
	}
	
	/**
	 * Read a float from the console
	 * 
	 * @return the float value
	 */
	public static float readFloat() {
		float num = 0;
		try {
			num = scanner.nextFloat();
		} catch (Exception e) {
			System.err.println("Only float values allowed!");
			scanner.nextLine();
			num = readFloat();
		}
		return num;
	}
}
