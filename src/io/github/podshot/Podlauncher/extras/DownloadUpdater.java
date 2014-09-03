package io.github.podshot.Podlauncher.extras;

import io.github.podshot.Podlauncher.UpdateChecker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadUpdater {

	public static void start() {
		URL downloadURL = UpdateChecker.getUpdateURL();
		File updateF = new File("PodLauncher" + File.separator + "update");
		if (!(updateF.exists())) {
			updateF.mkdirs();
		}
		download(downloadURL);
	}

	private static void download(URL downloadURL) {
		try {
			ReadableByteChannel rbc = Channels.newChannel(downloadURL.openStream());
			FileOutputStream fos = new FileOutputStream("PodLauncher" + File.separator + "update" + File.separator + "Updater.jar");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static void run() throws IOException {
		Runtime runtime = Runtime.getRuntime();

		if (UpdateChecker.isACanidateUpdate()) {
			Process proc = runtime.exec("java -jar \"PodLauncher" + File.separator + "update" + File.separator + "Updater.jar\" -canidate");
		} else {
			Process proc = runtime.exec("java -jar \"PodLauncher" + File.separator + "update" + File.separator + "Updater.jar\"");
		}
		System.exit(0);

	}

	public static void cleanup() {
		File updateF = new File("PodLauncher" + File.separator + "update");
		if (updateF.exists()) {
			for (File file : updateF.listFiles()) {
				file.delete();
			}
			updateF.delete();
		}
	}

}