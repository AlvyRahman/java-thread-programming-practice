import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

class DownloaderThread extends Thread{
    private String url;
    private String fileName;

    public DownloaderThread(String url, String fileName)
    {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run()
    {
        System.out.println("Starting downloading " + this.fileName);
        try {
            URI uri = new URI(this.url);
            URL resource = uri.toURL();
            URLConnection connection = resource.openConnection();
            InputStream input = connection.getInputStream();

            File file = new File(this.fileName);
            Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            input.close();
            System.out.println(this.fileName + " download completed!");
        } catch (URISyntaxException | IllegalArgumentException | IOException e) {
            throw new RuntimeException("Failed to donwload  " + this.fileName, e);
        }
    }
}

class HeartBeatThread extends Thread{
    private volatile boolean running = true;

    @Override
    public void run()
    {
        String[] dots = {
            "*",
            "**",
            "***"
        };

        while (running) {
            for(String dot : dots) {
                System.out.println(dot);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown()
    {
        this.running = false;
    }
}

public class downloader {
    public static void main(String[] args) {
        DownloaderThread dt1 = new DownloaderThread("https://w3.org", "file-1");
        DownloaderThread dt2 = new DownloaderThread("https://w3.org", "file-2");
        HeartBeatThread ht = new HeartBeatThread();

        dt1.setName("Download-Thread-1");
        dt2.setName("Download-Thread-2");
        ht.setName("HeartBeat-Thread");

        dt1.start();
        dt2.start();
        ht.start();

        try {
            dt1.join();
            dt2.join();

            ht.shutdown();
            ht.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
