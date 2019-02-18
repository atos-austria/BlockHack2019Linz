package com.atos.templates;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 
 * @author S. Albers
 *
 */
public class Node {
	
	protected String name;

	public Node(String name) {
		this.name = name;
	}
	
	/**
	 * Execute a shell command 
	 * 
	 * @param path
	 * @param command
	 * @return true if successful
	 */
	public boolean executeCommand(String path, String command) {
		ProcessBuilder builder = new ProcessBuilder();
		builder.command("cmd.exe", "/c", command);
		builder.directory(new File(path));
		Process process = null;
		try {
			process = builder.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errors = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			System.out.println("====================================================================================================");
			String line = "";			
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			while ((line = errors.readLine()) != null) {
				System.err.println(line);
			}
			process.waitFor();
			return true;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			if (process != null) {
				process.destroy();
			}
		}
	}
	
}
