package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.DownloadUpdater;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.ErrorGUI;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.File;
import java.io.PrintStream;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PodLauncher {

	/**
	 * Method for setting up PodLauncher
	 * @param args Arguments for PodLauncher, '-updated' is currently the only one supported
	 * @throws Exception
	 */
	public static void main(String[] args) {
		// Start System.out wrapper
		System.setOut(new PrintStream(System.out) {
			// Sets the Out stream to pass through this inner class
			public void println(String s) {
				// Most commonly used PrintStream method
				if (s.equals("Error: Could not find or load main class net.minecraft.client.main.Main")) {
					// Check if the message matches a common error
					MainGUI.getInstance().resetLaunchProfileButton();
					// Re-enables the Launch Profile Button (since I disable it when it is first clicked)
				}
				super.println(s);
				// Calls the original PrintStream so the message is still printed to the console
			}
		});
		// End System.out wrapper
		
		// Sets the UI Look and Feel from Java's default layout to the OS specific one
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			new ErrorGUI(e);
		}
		// Loops through the specified arguments
		for (String argument : args) {
			// If an argument equals '-updated' cleanup the files left over from the update process
			if (argument.equalsIgnoreCase("-updated")) {
				DownloadUpdater.cleanup();
			}
		}
		// Checks for an update
		try {
			new UpdateChecker();
		} catch (Exception e) {
			new ErrorGUI(e);
		}
		// Checks to see if the folder 'PodLauncher' exists in the current working directory
		// If not create the folder
		File launcherFolder = new File("PodLauncher");
		if (!(launcherFolder.exists())) {
			launcherFolder.mkdir();
		}
		// Checks if the config.json file is present
		LauncherConfig.checkForFile();

		// Sets up the MainGUI and makes it visible
		MainGUI mainGUI = new MainGUI();
		mainGUI.setVisible(true);

	}

	/**
	 * Gets the PodLauncher Version number
	 * @return The Version number
	 */
	public static String getVersion() {
		return "0.0.7.2";
	}

	/**
	 * Gets the PodLauncher Development Stage
	 * @return The stage PodLauncher is in
	 */
	public static String getDevelopmentStage() {
		return "alpha";
	}

	/**
	 * Gets if PodLauncher should print debug messages (Best if true when ran from source)
	 * @return True if PodLauncher is in Debug mode, false otherwise
	 */
	public static boolean inDebugMode() {
		return true;
	}

	/**
	 * Gets if PodLauncher should cancel update messages
	 * @return True if PodLauncher is in Development mode, false otherwise
	 */
	public static boolean isDevMode() {
		return true;
	}
}
