/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HttpControl;

import java.util.Optional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author guilh
 */
public class HttpUtils {

    private static HttpUtils instance;
    
    private HttpUtils() {
    }

    public synchronized static HttpUtils getInstance() {
        if (instance == null)
            instance = new HttpUtils();

        return instance;
    }

    public String getPathName(String content) {
        return content.split(" ")[1];
    }

    public String getContentType(String pathName) {
        String contentType = Optional.ofNullable(pathName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(pathName.lastIndexOf(".") + 1)).get();
        
        switch(contentType){
            case "txt":
                return "text/plain";
            case "mp3":
                return "audio/mpeg";
            case "aac":
                return "audio/aac";
            case "bin":
                return "application/octet-stream";
            case "png":
                return "image/png";
            case "jpg":
                return "image/jpeg";
            case "jpeg":
                return "image/jpeg";
            case "avi":
                return "video/x-msvideo";
            case "mpeg":
                return "video/mpeg";
            default:
                throw new NotImplementedException();
        }
    }
    
}
