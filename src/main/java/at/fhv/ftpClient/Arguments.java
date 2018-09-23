package at.fhv.ftpClient;

import com.beust.jcommander.Parameter;

public class Arguments {
    @Parameter(names = {"-server", "-address"}, description = "Server Address")
    private String server;
    @Parameter(names = {"-user", "-username"}, description = "FTP Username")
    private String user;
    @Parameter(names = {"-pw", "-password"}, description = "FTP Password", password = true)
    private String password;
    @Parameter(names = {"-source"}, description = "Path on FTP Server")
    private String sourcePath;
    @Parameter(names = {"-destination"}, description = "Path on Client")
    private String destinationPath;
    @Parameter(names = {"-intervall"}, description = "Intervalltime in ms")
    private int intervall;
    @Parameter(names = {"-log"}, description = "Path to the Logfile")
    private String logFilePath;

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public int getIntervall() {
        return intervall;
    }

    public void setIntervall(int intervall) {
        this.intervall = intervall;
    }

    public String getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }
}
