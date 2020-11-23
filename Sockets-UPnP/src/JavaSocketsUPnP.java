/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.dosse.upnp.UPnP;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import HttpControl.HttpDirector;

/**
 *
 * @author guilh
 */
public class JavaSocketsUPnP {

    static UpnpUtils upnpUtils = UpnpUtils.getInstance();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        HttpDirector httpDirector = new HttpDirector();

        ServerSocket server = new ServerSocket(80);
        server.setReuseAddress(true);

        while (true) {
            System.out.println("Waiting connection...");
            try (Socket conn = server.accept();) {
                System.out.println("Connected with " + conn.getInetAddress().getHostAddress());

                InputStream in = conn.getInputStream();

                byte[] requestBytes = new byte[1024];
                int qttBytes = in.read(requestBytes);
                System.out.println("Passou: " + qttBytes);
                String requestContent = new String(requestBytes, 0, qttBytes);

                String response = httpDirector.execute(requestContent);

                OutputStream out = conn.getOutputStream();
                out.write(response.getBytes());
            }
        }
    }
}

//}
