/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.sockets.upnp.HttpControl;

/**
 *
 * @author guilh
 */
public class HttpDirector {

    private HttpImplementation context;

    public String execute(String requestData) {

        switch (requestData.split(" ")[0]) {
            case "GET":
                context = new HttpImplementationGet();
                break;

            case "POST":
                context = new HttpImplementationGet();
                break;
        }

        context.execute(requestData);
        return requestData;
    }

}
