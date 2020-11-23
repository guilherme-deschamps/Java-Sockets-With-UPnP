/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HttpControl;

import com.sun.media.sound.InvalidDataException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.Response;

/**
 *
 * @author guilh
 */
public class HttpImplementationPost implements HttpImplementation {

    HttpDirector httpDirector = new HttpDirector();

    static HttpUtils httpUtils = HttpUtils.getInstance();

    @Override
    public String execute(String content) {
        String pathName = httpUtils.getPathName(content);

        if (pathName.equals("/teapot")) {
            return "HTTP/1.1 " + HttpResponses.IM_A_TEAPOT.getStatus();
        }

        /**
         * Removing first line and headers of request body.
         */
        content = content.substring(content.indexOf("\r\n\r\n") + 4, content.length());
        try {
            appendFileContent(pathName, content);
        } catch (InvalidDataException ex) {
            return "HTTP/1.1 " + HttpResponses.BAD_REQUEST.getStatus();
        } catch (AccessDeniedException ex) {
            return "HTTP/1.1 " + HttpResponses.FORBIDDEN.getStatus();
        } catch (IOException ex) {
            return "HTTP/1.1 " + HttpResponses.SERVICE_UNAVAILABLE.getStatus();
        }

        return constructResponse(pathName, content);
    }

    private void appendFileContent(String pathName, String content) throws IOException {

        File directory = new File(httpDirector.getPublicHtml() + pathName);
        if (!directory.exists()) {
            throw new InvalidDataException();
        }

        content = formatFileContent(content);

        File arq = new File(directory.getPath() + pathName + ".txt");
        content = readFileContent(arq) + content;
        FileWriter fileWriter = new FileWriter(arq);
        BufferedWriter writer = new BufferedWriter(fileWriter);

        writer.write(content);

        writer.close();
        fileWriter.close();
    }

    private String readFileContent(File arq) throws FileNotFoundException, IOException {
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
            return "";
        }
    }

    private String formatFileContent(String content) {
        String[] attributes = content.split("&");
        String formattedContent = "";

        for (String a : attributes) {
            formattedContent += a.split("=")[1] + ";";
        }

        return formattedContent;
    }

    @Override
    public String constructResponse(String pathName, String fileContent) {
        return "HTTP/1.1 " + HttpResponses.OK.getStatus();
    }
}
