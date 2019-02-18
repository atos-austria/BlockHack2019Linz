package com.atos.templates;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author S. Albers
 *
 */
public class Options {

	private List<String> configOptions;
	private List<String> mainMenuOptions;
	private List<String> infoOptions;
	private List<String> permissionOptions;
	private List<String> streamOptions;
	private List<String> assetOptions;
	private List<String> permissions;
	
	public Options(boolean isMainNode) {
		configOptions = new ArrayList<String>();
		mainMenuOptions = new ArrayList<String>();
		infoOptions = new ArrayList<String>();
		permissionOptions = new ArrayList<String>();
		streamOptions = new ArrayList<String>();
		assetOptions = new ArrayList<String>();
		permissions = new ArrayList<String>();
		
		setConfigOptions(isMainNode);
		setMainMenuOptions();
		setInfoOptions();
		setPermissionOptions();
		setStreamOptions();
		setAssetOptions();
		setPermissions();
	}
	
	/**
	 * Set the options for configuration
	 * 
	 * @param isMainNode
	 */
	public void setConfigOptions(boolean isMainNode) {
		if (isMainNode == true) {
			configOptions.add("Create a blockchain (main node)");
			configOptions.add("Initialize a blockchain (main node)");
			configOptions.add("Launch a blockchain");
		}
		else {
			configOptions.add("Create a secondary node");
			configOptions.add("Initialize a secondary node");
			configOptions.add("Launch a blockchain with a secondary node");
		}	
	}
	
	/**
	 * Set the options for the main menu
	 */
	public void setMainMenuOptions() {
		mainMenuOptions.add("Information");
		mainMenuOptions.add("Permission management");
		mainMenuOptions.add("Streams");
		mainMenuOptions.add("Assets");
	}
	
	/**
	 * Set the options for general information
	 */
	public void setInfoOptions() {
		infoOptions.add("General information about this blockchain");
		infoOptions.add("List all commands");
		infoOptions.add("Display help for a specific command");
		infoOptions.add("List all addresses in the wallet");
		infoOptions.add("Exit");
	}
	
	/**
	 * Set the options for permission management
	 */
	public void setPermissionOptions() {
		permissionOptions.add("List all permissions granted to addresses");
		permissionOptions.add("List specific permissions granted to addresses");
		permissionOptions.add("List specific permissions for a specified node");
		permissionOptions.add("Grant a permission");
		permissionOptions.add("Revoke a permission");
		permissionOptions.add("Grant write permission in a stream");
		permissionOptions.add("Exit");
	}
	
	/**
	 * Set the options for streams
	 */
	public void setStreamOptions() {
		streamOptions.add("Create a stream");
		streamOptions.add("Publish to a stream");
		streamOptions.add("Subscribe to a stream");
		streamOptions.add("Unsubscribe to a stream");
		streamOptions.add("List all streams");
		streamOptions.add("List items of a stream");
		streamOptions.add("List keys of a stream");
		streamOptions.add("List items for a specific key of a stream");
		streamOptions.add("Exit");
	}
	
	/**
	 * Set the options for assets
	 */
	public void setAssetOptions() {
		assetOptions.add("Issue an asset");
		assetOptions.add("Send an asset");
		assetOptions.add("List all issued assets");
		assetOptions.add("List all asset balances");
		assetOptions.add("Exit");
	}
	
	/**
	 * Set the options for permissions
	 */
	public void setPermissions() {
		permissions.add("CONNECT");
		permissions.add("SEND");
		permissions.add("RECEIVE");
		permissions.add("ISSUE");
		permissions.add("MINE");
		permissions.add("ACTIVATE");
		permissions.add("ADMIN");
		permissions.add("CREATE");
	}
	
	/**
	 * List the options
	 * 
	 * @param options
	 */
	public void listOptions(List<String> options) {
		for (int i=0; i<options.size(); i++) {
			System.out.println(i+1 + " = " + options.get(i));
		}
	}
	
	/**
	 * @return the list of options for configuration
	 */
	public List<String> getConfigOptions() {
		return configOptions;
	}
	
	/**
	 * @return the list of options for the main menu
	 */
	public List<String> getMainMenuOptions() {
		return mainMenuOptions;
	}
	
	/**
	 * @return the list of options for general information
	 */
	public List<String> getInfoOptions() {
		return infoOptions;
	}
	
	/**
	 * @return the list of options for permission management
	 */
	public List<String> getPermissionOptions() {
		return permissionOptions;
	}
	
	/**
	 * @return the list of options for streams
	 */
	public List<String> getStreamOptions() {
		return streamOptions;
	}
	
	/**
	 * @return the list of options for assets
	 */
	public List<String> getAssetOptions() {
		return assetOptions;
	}
	
	/**
	 * @return the list of permissions
	 */
	public List<String> getPermissions() {
		return permissions;
	}
}
