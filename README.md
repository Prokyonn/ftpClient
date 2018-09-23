# FTPClient
Console Application which downloads all files from a specific folder. If a file is sucessfully downlaoded, the file will be deleted.<br>
The application can be configured with the following mandatory arguments:
<ul>
 <li> -server
        <ul>
            <li>Specifies the address to the FTP-Server
            <br>e.g. -server "127.0.0.1"
        </ul>
    <li> -user
        <ul>
            <li>Specifies the username to login to the FTP-Server
            <br>e.g. -user "testuser"
        </ul>
    <li> -pw "test"
        <ul>
            <li>Specifies the password to login to the FTP-Server
            <br>e.g. -pw "test"
        </ul>
    <li> -source 
        <ul>
            <li>Specifies the absolute path to the folder on the FTP-Server
            <br>e.g. -source "/testfolder"
        </ul>
    <li> -destination
        <ul>
            <li>Specifies the absolute path to the local destination folder into which the files are downloaded.
            <br>e.g. -destination "C:\Users\Test\Testfolder"
        </ul>
    <li> -intervall
        <ul>
            <li>Specifies the intervall in minutes. After all files from the source folder were downloaded and deleted, the application goes into a sleepmode, before the download/delete process gets started again.
            <br>e.g. -intervall 10
        </ul>
    <li> -logs
        <ul>
            <li>Specifies the absolute path to the logfile.
            <br>e.g. -log "C:\Users\Test\logs.txt"
        </ul>
</ul>