/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC;

import EDJC.rooms.RoomLayout;
import JCineFX.JCineFX;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Jay C Espinoza
 */
public class ListRooms {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int nu = JCineFX.leerConf().getContadorSala() - 1;
        System.out.println("Total Salas: " + nu);
        for(int i = 1; i <= nu; i++){
            FileInputStream fil = new FileInputStream("salas/sala" + i + ".mov");
            ObjectInputStream oin = new ObjectInputStream(fil);
            RoomLayout sal = (RoomLayout)(oin.readObject());
            System.out.println("Sala: " + i);
            System.out.println(sal.toString());
            System.out.println("\n");
        }
    }
}
