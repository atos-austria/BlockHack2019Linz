package com.atos.templates;
import java.util.Scanner;

/**
 * 
 * @author S. Albers
 *
 */
public class Config {
	
	private Scanner scanner;
	
	private String execPath;
	private String dataPath; 
	
	private MainNode mainNode;
	private SecondaryNode secondaryNode;
	private String mainNodeName;
	private String secondaryNodeName;
	private String secondaryNodePort;
	private String mainNodeRpcPort;
	private String secondaryNodeRpcPort;
	private String mainNodeAddress;
	
	private String ip;
	private String login;
	private String password;
	private Multichain multichain;
	
	public Config() {
		scanner = new Scanner(System.in);
	}
	
	/**
	 * Create a main node
	 */
	public void createMainNode() {
		printInfo("Enter name of blockchain (the main node) that should be created:");
		mainNodeName = readString();
		execPath = System.getenv("MULTICHAIN");
		
		mainNode = new MainNode(mainNodeName, execPath);
		mainNode.createMainNode();
	}
	
	/**
	 * Create a secondary node
	 */
	public void createSecondaryNode() {
		printInfo("Enter name of the secondary node that should be created:");
		secondaryNodeName = readString();
		dataPath = System.getenv("CHAINDATA");
		
		secondaryNode = new SecondaryNode(secondaryNodeName, null, dataPath, null, null, null);
		if (secondaryNode.createSecondaryNode() != false) {
			printInfo("Secondary node succesfully created.");
		}
	}
	
	/**
	 * Initialize a main node
	 */
	public void initMainNode() {
		printInfo("Enter name of blockchain (the main node) that should be initialized:");
		mainNodeName = readString();
		execPath = System.getenv("MULTICHAIN");
		
		Thread thread = new Thread(new MainNode(mainNodeName, execPath));
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.err.println("Thread interrupted.");
		}
	}
	
	/**
	 * Initialize a secondary node
	 */
	public void initSecondaryNode() {
		printInfo("Enter name of the secondary node that should be initialized:");
		secondaryNodeName = readString();
		printInfo("Enter port number for the secondary node (e.g. 10255):");
		secondaryNodePort = readString();
		printInfo("Enter rpc port number for the secondary node (e.g. 10254):");
		secondaryNodeRpcPort = readString();
		printInfo("Enter the address of the first node:");
		mainNodeAddress = readString();
		execPath = System.getenv("MULTICHAIN");
		dataPath = System.getenv("CHAINDATA");
		
		Thread thread = new Thread(new SecondaryNode(secondaryNodeName, execPath, dataPath, secondaryNodePort, secondaryNodeRpcPort, mainNodeAddress));
		thread.start();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			System.err.println("Thread interrupted.");
		}
	}
	
	/**
	 * Launch a blockchain (main node)
	 * 
	 * @return true if successful
	 */
	public boolean launchBlockchain() {
		printInfo("Enter IP address, (e.g. localhost):");
		ip = readString();
		printInfo("Enter rpc port number (see params.dat):");
		mainNodeRpcPort = readString();
		printInfo("Enter login name (see multichain.conf):");
		login = readString();
		printInfo("Enter password (see multichain.conf):");
		password = readString();
		
		multichain = new Multichain(ip, mainNodeRpcPort, login, password);
		try {
			if (multichain.getAddresses() != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Launching blockchain failed! Check inputs.");
			return false;
		}
	}
	
	/**
	 * Launch a blockchain with a secondary node
	 * 
	 * @return true if successful
	 */
	public boolean launchBlockchainWithSecondaryNode() {
		printInfo("Enter IP address, (e.g. localhost):");
		ip = readString();
		printInfo("Enter rpc port number of the secondary node:");
		secondaryNodeRpcPort = readString();
		printInfo("Enter login name (see multichain.conf):");
		login = readString();
		printInfo("Enter password (see multichain.conf):");
		password = readString();
		
		multichain = new Multichain(ip, secondaryNodeRpcPort, login, password);
		try {
			if (multichain.getAddresses() != null) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.err.println("Launching blockchain failed! Check inputs.");
			return false;
		}
	}
	
	/**
	 * Print information to the console
	 * 
	 * @param string
	 */
	public void printInfo(String string) {
		System.out.println(string);
	}
	
	/**
	 * Read a string from the console
	 * 
	 * @return the string
	 */
	public String readString() {
		String string = scanner.next();
		return string;
	}
	
	/**
	 * @return the multichain object
	 */
	public Multichain getMultichain() {
		return multichain;
	}
}
