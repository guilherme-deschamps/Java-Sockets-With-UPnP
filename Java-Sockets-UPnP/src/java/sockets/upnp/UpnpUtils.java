/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.sockets.upnp;

import com.dosse.upnp.UPnP;

/**
 *
 * @author guilh
 */
public class UpnpUtils {

    private static UpnpUtils instance;
    
    private int port = 56003;

    private UpnpUtils() {
    }

    public synchronized static UpnpUtils getInstance() {
        if (instance == null)
            instance = new UpnpUtils();

        return instance;
    }

    public boolean openUPnPPort() {
        boolean portOpened = false;

        System.out.println("Attempting UPnP port forwarding");
        if (UPnP.isUPnPAvailable()) {
            if (UPnP.openPortTCP(port)) {
                System.out.println("UPnP port forwarding enabled.");
                portOpened = true;
            } else {
                System.out.println("UPnP port forwarding failed.");
            }
        } else {
            System.out.println("UPnP is not available.");
        }

        return portOpened;
    }
    
    public int getPort(){
        return port;
    }
    
}
