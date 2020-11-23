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
public enum HttpResponses {

    OK("200 Ok"),
    BAD_REQUEST("400 Bad Request"),
    FORBIDDEN("403 Forbidden"),
    NOT_FOUND("404 Not Found"),
    IM_A_TEAPOT("418 I'm a teapot"),
    NOT_IMPLEMENTED("501 Not Implemented"),
    SERVICE_UNAVAILABLE("503 Service Unavailable");

    private final String status;

    HttpResponses(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
