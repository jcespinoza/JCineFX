/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.security.UserBuilder;
import EDJC.security.User;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Jay C Espinoza
 */
public class DeactivationTest {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String nonExistent = "hello!";
        UserBuilder.escribirUser(new User(nonExistent, "nadamalo".toCharArray()));
        System.out.println("Done!");
        ListUsers.main(args);
    }
}
