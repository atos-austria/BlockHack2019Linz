package com.atos.templates;
import java.util.List;

import multichain.command.*;
import multichain.object.*;

/**
 * 
 * @author S. Albers
 *
 */
public class Multichain {

	private RuntimeParameters runtimeParameters;
	private MultiChainCommand multiChainCommand;
	
	public Multichain(String ip, String port, String login, String password) {
		this.multiChainCommand = new MultiChainCommand(ip, port, login, password);
	}
	
	public Multichain(String ip, String port, String login, String password, String datadir, String rpcport) {
		this.runtimeParameters = new RuntimeParameters(datadir, rpcport);
		this.multiChainCommand = new MultiChainCommand(ip, port, login, password, runtimeParameters);
	}
	
	/**
	 * Get general information about a blockchain
	 * 
	 * @return the information
	 */
	public String getInformation() {
		ChainCommand chainCommand = multiChainCommand.getChainCommand();
		try {
			return chainCommand.getInfo();
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of all available MultiChain commands
	 * 
	 * @return the list of commands
	 */
	public String help() {
		ChainCommand chainCommand = multiChainCommand.getChainCommand();
		try {
			return chainCommand.help();
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get help for a specific command
	 * 
	 * @param command
	 * @return the information
	 */
	public String help(String command) {
		ChainCommand chainCommand = multiChainCommand.getChainCommand();
		try {
			return chainCommand.help(command);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of all addresses in the wallet
	 * 
	 * @return the list of addresses
	 */
	public List<String> getAddresses() {
		AddressCommand addressCommand = multiChainCommand.getAddressCommand();
		try {
			return addressCommand.getAddresses();
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		} 
	}
	
	/**
	 * Get a list of all permissions granted to addresses
	 * 
	 * @return the list of permissions
	 */
	public List<Permission> listPermissions() {
		GrantCommand grantCommand = multiChainCommand.getGrantCommand();
		try {
			return grantCommand.listPermissions();
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of specific permissions granted to addresses
	 * 
	 * @param permissions
	 * @return the list of permissions
	 */
	public List<Permission> listPermissions(int permission) {
		GrantCommand grantCommand = multiChainCommand.getGrantCommand();
		try {
			switch (permission) {
			case 1:
				return grantCommand.listPermissions(GrantCommand.CONNECT);
			case 2:
				return grantCommand.listPermissions(GrantCommand.SEND);
			case 3:
				return grantCommand.listPermissions(GrantCommand.RECEIVE);
			case 4:
				return grantCommand.listPermissions(GrantCommand.ISSUE);
			case 5:
				return grantCommand.listPermissions(GrantCommand.MINE);
			case 6:
				return grantCommand.listPermissions(GrantCommand.ACTIVATE);
			case 7:
				return grantCommand.listPermissions(GrantCommand.ADMIN);
			case 8:
				return grantCommand.listPermissions(GrantCommand.CREATE);
			default:
				return null;
			}
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of specific permissions for the specified address
	 * 
	 * @param permission
	 * @param address
	 * @return the list of permissions
	 */
	public List<Permission> listPermissions(int permission, String address) {
		GrantCommand grantCommand = multiChainCommand.getGrantCommand();
		try {
			switch (permission) {
			case 1:
				return grantCommand.listPermissions(GrantCommand.CONNECT, address);
			case 2:
				return grantCommand.listPermissions(GrantCommand.SEND, address);
			case 3:
				return grantCommand.listPermissions(GrantCommand.RECEIVE, address);
			case 4:
				return grantCommand.listPermissions(GrantCommand.ISSUE, address);
			case 5:
				return grantCommand.listPermissions(GrantCommand.MINE, address);
			case 6:
				return grantCommand.listPermissions(GrantCommand.ACTIVATE, address);
			case 7:
				return grantCommand.listPermissions(GrantCommand.ADMIN, address);
			case 8:
				return grantCommand.listPermissions(GrantCommand.CREATE, address);
			default:
				return null;
			}
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Grant a permission to an address
	 * 
	 * @param address
	 * @param permission
	 * @return the transaction ID
	 */
	public String grantPermission(String address, int permission) {
		GrantCommand grantCommand = multiChainCommand.getGrantCommand();
		try {
			switch (permission) {
			case 1:
				return grantCommand.grant(address, GrantCommand.CONNECT);
			case 2:
				return grantCommand.grant(address, GrantCommand.SEND);
			case 3:
				return grantCommand.grant(address, GrantCommand.RECEIVE);
			case 4:
				return grantCommand.grant(address, GrantCommand.ISSUE);
			case 5:
				return grantCommand.grant(address, GrantCommand.MINE);
			case 6:
				return grantCommand.grant(address, GrantCommand.ACTIVATE);
			case 7:
				return grantCommand.grant(address, GrantCommand.ADMIN);
			case 8:
				return grantCommand.grant(address, GrantCommand.CREATE);
			default:
				System.err.println("Wrong input! Please try again.");
				return null;	
			}
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Revoke a permission to an address
	 * 
	 * @param address
	 * @param permission
	 * @return the transaction ID
	 */
	public String revokePermission(String address, int permission) {
		GrantCommand grantCommand = multiChainCommand.getGrantCommand();
		try {
			switch (permission) {
			case 1:
				return grantCommand.revoke(address, GrantCommand.CONNECT);
			case 2:
				return grantCommand.revoke(address, GrantCommand.SEND);
			case 3:
				return grantCommand.revoke(address, GrantCommand.RECEIVE);
			case 4:
				return grantCommand.revoke(address, GrantCommand.ISSUE);
			case 5:
				return grantCommand.revoke(address, GrantCommand.MINE);
			case 6:
				return grantCommand.revoke(address, GrantCommand.ACTIVATE);
			case 7:
				return grantCommand.revoke(address, GrantCommand.ADMIN);
			case 8:
				return grantCommand.revoke(address, GrantCommand.CREATE);
			default:
				System.err.println("Wrong input! Please try again.");	
				return null;
			}	
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Grant write permission in a stream to an address
	 * 
	 * @param address
	 * @param streamName
	 * @return the transaction ID
	 */
	public String grantWrite(String address, String streamName) {
		GrantCommand streamCommand = multiChainCommand.getGrantCommand();
		try {
			return streamCommand.grantWrite(address, streamName);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Create a stream
	 * 
	 * @param streamName
	 * @return the transaction ID
	 */
	public String createStream(String streamName) {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			return streamCommand.create(streamName);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Publish to a stream
	 * 
	 * @param streamName
	 * @param key
	 * @param data
	 * @return the transaction ID
	 */
	public String publish(String streamName, String key, String data) {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			return streamCommand.publish(streamName, key, data);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Subscribe to a stream
	 * 
	 * @param streamName
	 * @return the message for success
	 */
	public String subscribe(String streamName) {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			streamCommand.subscribe(streamName);
			return "Successfully subscribed to stream " + streamName;
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Unsubscribe to a stream
	 * 
	 * @param streamName
	 * @return the message for success
	 */
	public String unsubscribe(String streamName) {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			streamCommand.unsubscribe(streamName);
			return "Successfully unsubscribed to stream " + streamName;
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of streams
	 * 
	 * @return the list of streams
	 */
	public List<Stream> listStreams() {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			return streamCommand.listStreams(null);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of stream items
	 * 
	 * @param streamName
	 * @return the list of stream items
	 */
	public List<StreamKeyItem> listStreamItems(String streamName) {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			return streamCommand.listStreamItems(streamName);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of stream keys
	 * 
	 * @param streamName
	 * @return the list of stream keys
	 */
	public List<StreamKey> listStreamKeys(String streamName) {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			return streamCommand.listStreamKeys(streamName);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of stream key items
	 * 
	 * @param streamName
	 * @param key
	 * @return the list of stream key items
	 */
	public List<StreamKeyItem> listStreamKeyItems(String streamName, String key) {
		StreamCommand streamCommand = multiChainCommand.getStreamCommand();
		try {
			return streamCommand.listStreamKeyItems(streamName, key);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Issue an asset
	 * 
	 * @param address
	 * @param assetName
	 * @param quantity
	 * @param unit
	 * @return the transaction ID
	 */
	public String issueAsset(String address, String assetName, float quantity, float unit) {
		IssueCommand issueCommand = multiChainCommand.getIssueCommand();
		try {
			return issueCommand.issue(address, assetName, quantity, unit);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Send an asset
	 * 
	 * @param address
	 * @param assetName
	 * @param quantity
	 * @return the transaction ID
	 */
	public String sendAsset(String address, String assetName, float quantity) {
		IssueCommand issueCommand = multiChainCommand.getIssueCommand();
		try {
			return issueCommand.sendAssetToAddress(address, assetName, quantity);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of all issued assets 
	 * 
	 * @return the list of assets
	 */
	public List<BalanceAsset> listAssets() {
		IssueCommand issueCommand = multiChainCommand.getIssueCommand();
		try {
			return issueCommand.listAssets(null);
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	/**
	 * Get a list of all asset balances
	 * 
	 * @return the list of asset balances
	 */
	public List<BalanceAsset> getAssetBalances() {
		IssueCommand issueCommand = multiChainCommand.getIssueCommand();
		try {
			return issueCommand.getAssetBalances();
		} catch (MultichainException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

}
