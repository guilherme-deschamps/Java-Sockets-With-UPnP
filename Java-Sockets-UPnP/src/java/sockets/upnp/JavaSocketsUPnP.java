/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.sockets.upnp;

import com.dosse.upnp.UPnP;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sockets.upnp.HttpControl.HttpDirector;

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

        boolean portOpened = upnpUtils.openUPnPPort();
        int port = upnpUtils.getPort();
        HttpDirector httpDirector = new HttpDirector();

        if (portOpened) {
            ServerSocket server = new ServerSocket(port);
            server.setReuseAddress(true);

            while (true) {
                System.out.println("Waiting connection...");
                try (Socket conn = server.accept();) {
                    System.out.println("Connected with " + conn.getInetAddress().getHostAddress());

                    InputStream in = conn.getInputStream();
                    byte[] requestData = new byte[1024];
                    in.read(requestData);

                    String response = httpDirector.execute(new String(requestData));

//                    OutputStream out = conn.getOutputStream();
//                    out.write(dadosBrutos);
                }
            }
        }
    }

}
