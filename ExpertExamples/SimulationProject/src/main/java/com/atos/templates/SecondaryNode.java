package com.atos.templates;
/**
 * 
 * @author S. Albers
 *
 */
public class SecondaryNode extends Node implements Runnable {
	
	private String execPath;
	private String dataPath;
	private String port;
	private String rpcport;
	private String mainNodeAddress;
	
	public SecondaryNode(String name, String execpath, String multichainpath, String port, String rpcport, String mainNodeAddress) {
		super(name);
		this.execPath = execpath;
		this.dataPath = multichainpath;
		this.port = port;
		this.rpcport = rpcport;
		this.mainNodeAddress = mainNodeAddress;
	}
	
	public void run() {
		initSecondaryNode();
	}
	
	/**
	 * Create a secondary node
	 * 
	 * @return true if successful
	 */
	public boolean createSecondaryNode() {
		String command = String.format("mkdir %s", name);
		return executeCommand(dataPath, command);
	}
	
	/**
	 * Initialize a secondary node
	 */
	public void initSecondaryNode() {
		String command = 
				String.format("multichaind -datadir=%s\\%s -port=%s -rpcport=%s %s -daemon", dataPath, name, port, rpcport, mainNodeAddress);
		executeCommand(execPath, command);
	}

}
