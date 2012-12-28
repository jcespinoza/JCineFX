/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.rooms;

import JCineFX.JCineFX;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.control.ChoiceBox;

/**
 *
 * @author Jay C Espinoza
 */
public class RoomBuilder {
    public static void fillSalaChoices(ChoiceBox box, ArrayList<String> salas) {
        try{
            box.getItems().removeAll(box.getItems());
            int count = JCineFX.leerConf().getContadorSala() - 1;
            for(int i = 1; i <= count; i++){
                String item = "Sala ";
                RoomLayout s = leerSala(i);
                item += ( s.is3D() )?"3D":"2D";
                box.getItems().add("" + i);
                salas.add(item);
            }
        }catch(Exception ex){
            System.out.println("Cant read Salacounter from file");
            ex.printStackTrace();
        }
    }
    
    public static RoomLayout leerSala(int cod) throws IOException, ClassNotFoundException{
        File f = new File("salas/sala" + cod + ".mov");
        if( !f.exists()){
            return null;
        }else{
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
            return (RoomLayout)(oin.readObject());
        }
    }
    
    public static void escribirSala(RoomLayout sala) throws IOException{
        int cod = JCineFX.leerConf().getContadorSala();
        FileOutputStream fout = new FileOutputStream("salas/sala" + cod + ".mov");
        sala.setCodigo(cod);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
        JCineFX.aumentarContSala();
    }
    
    public static void escribirSalaT(RoomLayout sala, Schedule h) throws IOException{
        Room4Ticket ST = new Room4Ticket(sala, h.getCodPeli(), h.getFecha());
        FileOutputStream fout = new FileOutputStream("horarios/" + ST.getNombreArchivo());
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
    }
    
    public static Room4Ticket leerSalaT(String filename) throws IOException, ClassNotFoundException{
        File f = new File("horarios/" + filename);
        if( !f.exists()){
            return null;
        }else{
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
            return (Room4Ticket)(oin.readObject());
        }
    }
    
    public static void actualizarSalaT(Room4Ticket sala) throws IOException{
        File f = new File("horarios/" + sala.getNombreArchivo());
        if( f.exists()){
            f.delete();
        }
        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
    }
}