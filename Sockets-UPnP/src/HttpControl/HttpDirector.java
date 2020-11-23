/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HttpControl;

/**
 *
 * @author guilh
 */
public class HttpDirector {

    private HttpImplementation context;

    private String publicHtml = "public_html";

    public String execute(String requestData) {

        String httpMethod = requestData.split(" ")[0];
        switch (httpMethod) {
            case "GET":
                context = new HttpImplementationGet();
                break;

            case "POST":
                context = new HttpImplementationPost();
                break;

            default:
                return "HTTP/1.1 " + HttpResponses.NOT_IMPLEMENTED.getStatus();
        }

        return context.execute(requestData);
    }

    public String getPublicHtml() {
        return publicHtml;
    }

}
