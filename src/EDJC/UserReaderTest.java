/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.seguridad.UserBuilder;
import java.io.IOException;

/**
 *
 * @author Jay C Espinoza
 */
public class UserReaderTest {
    public static void main(String[] args) throws IOException {
        System.out.println(UserBuilder.leerUser("guest"));
    }
}
