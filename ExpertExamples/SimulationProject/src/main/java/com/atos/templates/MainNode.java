package com.atos.templates;
/**
 * 
 * @author S. Albers
 *
 */
public class MainNode extends Node implements Runnable {
	
	private String execPath;
	
	public MainNode(String name, String execpath) {
		super(name);
		this.execPath = execpath;
	}
	
	public void run() {
		initMainNode();
	}
	
	/**
	 * Create a main node
	 * 
	 * @return true if successful
	 */
	public boolean createMainNode() {
		String command = String.format("multichain-util create %s", name);
		return executeCommand(execPath, command);
	}
	
	/**
	 * Initialize a main node
	 */
	public void initMainNode() {
		String command = String.format("multichaind %s -daemon", name);
		executeCommand(execPath, command);
	}
}
