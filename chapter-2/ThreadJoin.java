import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

class FileDownloader extends Thread{
    private String fileName;
    private String url;

    public FileDownloader(String fileName, String url)
    {
        this.fileName = fileName;
        this.url = url;
    }

    @Override
    public void run()
    {
        System.out.println();
        System.out.println("File: "+fileName+" Download started from "+url);

        try {
            URI uri = new URI(this.url);
            URL resourcesToDownload = uri.toURL();
            URLConnection connection = resourcesToDownload.openConnection();

            InputStream input = connection.getInputStream();

            File file = new File(this.fileName);
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            input.close();

        } catch (MalformedURLException e) {
            System.out.println();
            System.out.println("The URL is not working.");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.out.println();
            System.out.println("Could not find the URL. Please recheck.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println();
            System.out.println("An IO issue occured.");
            e.printStackTrace();
        }
    }

    public void displayCompletionMessage()
    {
        System.out.println();
        System.out.println("File: "+fileName+" Downloaded successfully");
    }
}

class DownloadingHeartBeat extends Thread {
    private volatile boolean running = true;

    @Override
    public void run()
    {
        while (running) {
            for(int i = 0; i < 3; i++) {
                System.out.println("* Download in progress *");

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println();
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopRunnig()
    {
        this.running = false;
    }
}

public class ThreadJoin {
    public static void main(String[] args) {
        FileDownloader fd1 = new FileDownloader("eicar.com.txt", "https://www.eicar.org/download/");
        FileDownloader fd2 = new FileDownloader("100MB.bin", "https://ash-speed.hetzner.com/");
        
        DownloadingHeartBeat hb = new DownloadingHeartBeat();

        fd1.start();
        fd2.start();
        hb.start();

        try {
            fd1.join();
            fd2.join();

            hb.stopRunnig();
            hb.join();

            fd1.displayCompletionMessage();
            fd2.displayCompletionMessage();
        } catch (Exception e) {
            System.out.println();
            System.out.println("Something wrong in MAIN try catch");
        }
    }
}
