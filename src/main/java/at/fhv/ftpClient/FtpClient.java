package at.fhv.ftpClient;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.pmw.tinylog.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class FtpClient {

    private final String server;
    private final int port;
    private final String user;
    private final String password;
    private FTPClient ftp;

    FtpClient(String server, int port, String user, String password) {
        this.server = server;
        this.port = port;
        this.user = user;
        this.password = password;
    }

    private OutputStream loggerOutputStream() {
        return new OutputStream() {
            @Override
            public void write(int b) {
                Logger.info(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) {
                String str = new String(b, off, len);
                Logger.info(str.trim());
            }

            @Override
            public void write(byte[] b) {
                write(b, 0, b.length);
            }
        };
    }

    boolean open() throws IOException {
        ftp = new FTPClient();

        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(loggerOutputStream())));

        ftp.connect(server, port);
        int reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new IOException("Exception in connecting to FTP Server");
        }

        return ftp.login(user, password);
    }

    void close() throws IOException {
        ftp.disconnect();
    }

    public Collection<String> listFileNames(String path) throws IOException {
        FTPFile[] files = ftp.listFiles(path);

        return Arrays.stream(files)
                .map(FTPFile::getName)
                .collect(Collectors.toList());
    }

    public ArrayList<FTPFile> listFiles(String path) throws IOException {
        FTPFile[] files = ftp.listFiles(path);

        ArrayList<FTPFile> list = new ArrayList<>(Arrays.asList(files));
        return list;
    }


    public boolean uploadFile(File file, String path) throws IOException {
        return ftp.storeFile(path, new FileInputStream(file));
    }

    public boolean downloadFile(String source, String destination) throws IOException {
        FileOutputStream out = new FileOutputStream(destination);
        return ftp.retrieveFile(source, out);
    }

    public boolean deleteFile(String path) throws IOException {
        return ftp.deleteFile(path);
    }
}
