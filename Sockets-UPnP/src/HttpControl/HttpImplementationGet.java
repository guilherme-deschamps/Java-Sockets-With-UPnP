/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HttpControl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author guilh
 */
public class HttpImplementationGet implements HttpImplementation {

    HttpDirector httpDirector = new HttpDirector();

    static HttpUtils httpUtils = HttpUtils.getInstance();

    @Override
    public String execute(String data) {
        String pathName = httpUtils.getPathName(data);

        try {
            String fileContent = readFileContent(pathName);

            return constructResponse(pathName, fileContent);
        } catch (FileNotFoundException ex) {
            return "HTTP/1.1 " + HttpResponses.NOT_FOUND.getStatus();
        } catch (NotImplementedException ex) {
            return "HTTP/1.1 " + HttpResponses.NOT_IMPLEMENTED.getStatus();
        } catch (IOException ex) {
            return "HTTP/1.1 " + HttpResponses.BAD_REQUEST.getStatus();
        }
    }

    @Override
    public String constructResponse(String pathName, String fileContent) {
        File arq = new File(httpDirector.getPublicHtml() + pathName);

        if (arq.isDirectory()) {
            boolean foundIndex = false;

            for (File f : arq.listFiles()) {
                if (f.getName().equals("index.html")) {
                    arq = f;
                    foundIndex = true;
                    break;
                }
            }

            if (!foundIndex) {
                String response = "<h3>Index of " + pathName + "</h3><br><br>";
                for (File f : arq.listFiles()) {
                    response += f.getName() + "<br>";
                }
            }
        }

        String response = "HTTP/1.1 " + HttpResponses.OK.getStatus() + "\n";
        response += "Content-type: " + httpUtils.getContentType(pathName) + "\n";
        response += "Content-Length: " + arq.length() + "\n\r\n";
        response += fileContent;

        return response;
    }

    private String readFileContent(String pathName) throws FileNotFoundException, IOException {
        File arq = new File(httpDirector.getPublicHtml() + pathName);

        if (arq.exists()) {
            FileReader fileReader = new FileReader(arq);
            BufferedReader reader = new BufferedReader(fileReader);

            String line = reader.readLine();
            String content = "";
            while (line != null) {
                content += line + "\n";
                line = reader.readLine();
            }
            reader.close();
            fileReader.close();

            return content;
        } else {
            throw new FileNotFoundException();
        }
    }

}
