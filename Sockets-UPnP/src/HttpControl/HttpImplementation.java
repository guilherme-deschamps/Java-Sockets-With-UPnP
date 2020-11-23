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
public interface HttpImplementation {
    
    public String execute(String data);
    
    public String constructResponse(String pathName, String fileContent);
    
}
