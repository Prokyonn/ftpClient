package at.fhv.ftpClient;

import com.beust.jcommander.JCommander;
import org.apache.commons.net.ftp.FTPFile;
import org.pmw.tinylog.Configurator;
import org.pmw.tinylog.Level;
import org.pmw.tinylog.Logger;
import org.pmw.tinylog.writers.ConsoleWriter;
import org.pmw.tinylog.writers.FileWriter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        if (!arguments.getSourcePath().endsWith("/")) {
            arguments.setSourcePath(arguments.getSourcePath() + "/");
        }
        if (!arguments.getDestinationPath().endsWith("/")) {
            arguments.setDestinationPath(arguments.getDestinationPath() + "/");
        }

        initLogger(arguments.getLogFilePath());

        boolean isRunning = true;

        do {
            FtpClient ftpClient = new FtpClient(arguments.getServer(), 21, arguments.getUser(), arguments.getPassword());
            try {
                boolean isConnected = ftpClient.open();

                if (isConnected) {
                    ArrayList<FTPFile> files = ftpClient.listFiles(arguments.getSourcePath());

                    for (FTPFile file : files) {
                        if (file.isFile()) {
                            String fileSource = arguments.getSourcePath() + file.getName();
                            String fileDestination = arguments.getDestinationPath() + file.getName();

                            File tmpFile = new File(fileDestination);
                            if (!tmpFile.exists()) {
                                boolean success = ftpClient.downloadFile(fileSource, fileDestination);
                                if (success) {
                                    boolean successDelete = ftpClient.deleteFile(fileSource);
                                    if (!successDelete) {
                                        Logger.warn(fileSource + " could not be deleted.");
                                    }
                                }
                            } else {
                                Logger.warn(fileDestination + " could not be downloaded, due to a naming conflict.");
                            }
                        }
                    }
                    ftpClient.close();

                    Logger.info("Application going to sleep for " + arguments.getIntervall() + " minute(s).");
                    Thread.sleep(arguments.getIntervall() * 60 * 1000);
                } else {
                    Logger.error("Incorrect Credentials");
                    isRunning = false;
                }
            } catch (IOException | InterruptedException e) {
                Logger.error(e);
                e.printStackTrace();
                isRunning = false;
            }
        } while (isRunning);
    }

    private static void initLogger(String logFilePath) {
        Configurator.defaultConfig().writer(new FileWriter(logFilePath, false, true)).addWriter(new ConsoleWriter()).level(Level.INFO).activate();
    }
}
