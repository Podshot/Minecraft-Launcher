package io.github.podshot.Podlauncher;

import io.github.podshot.Podlauncher.extras.DownloadUpdater;
import io.github.podshot.Podlauncher.files.LauncherConfig;
import io.github.podshot.Podlauncher.gui.MainGUI;

import java.io.File;
import java.net.MalformedURLException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PodLauncher {

	/**
	 * @param args
	 * @throws MalformedURLException 
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		for (String argument : args) {
			if (argument.equalsIgnoreCase("-updated")) {
				DownloadUpdater.cleanup();
			}
		}
		try {
			new UpdateChecker();
		} catch (Exception e) {
			e.printStackTrace();
		}
		File launcherFolder = new File("PodLauncher");
		if (!(launcherFolder.exists())) {
			launcherFolder.mkdir();
		}
		LauncherConfig.checkForFile();

		MainGUI mainGUI = new MainGUI();
		mainGUI.setVisible(true);

	}

	public static String getVersion() {
		return "0.0.5";
	}

	public static String getDevelopmentStage() {
		return "alpha";
	}

	public static boolean inDebugMode() {
		return true;
	}

	public static boolean isDevMode() {
		return true;
	}
}