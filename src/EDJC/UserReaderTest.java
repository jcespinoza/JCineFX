/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.security.User;
import EDJC.security.UserBuilder;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Jay C Espinoza
 */
public class UserReaderTest {
    public static void main(String[] args) throws IOException {
        System.out.println(UserBuilder.leerUser("guest"));
        
        ArrayList<User> list = UserBuilder.readUsers("cinefilos.usr");
        
        for(User u: list){
            System.out.println(u);
        }
    }
}
