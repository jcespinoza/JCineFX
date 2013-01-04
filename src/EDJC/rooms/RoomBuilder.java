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
    
    @Deprecated
    public static RoomLayout leerSala(int cod) throws IOException, ClassNotFoundException{
        return readRoom(cod);
    }
    
    public static RoomLayout readRoom(int cod){
        try{
            File f = new File(JCineFX.ROOMS_PATH + "room" + cod + ".rm");
            if( !f.exists()){
                return null;
            }else{
                FileInputStream fin = new FileInputStream(f);
                ObjectInputStream oin = new ObjectInputStream(fin);
                return (RoomLayout)(oin.readObject());
            }
        }catch(Exception ex){return null;}
    }
    
    @Deprecated
    public static void escribirSala(RoomLayout sala) throws IOException{
        int cod = JCineFX.leerConf().getContadorSala();
        FileOutputStream fout = new FileOutputStream("salas/sala" + cod + ".mov");
        sala.setCode(cod);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
        JCineFX.aumentarContSala();
    }
    
    public static void writeRoom(RoomLayout room, String folder){
        File f = new File(folder);
        if(!f.exists())
            f.mkdir();
        try{
            int cod = room.getCode();
            FileOutputStream fout = new FileOutputStream(folder + "room" + cod + ".rm");
            room.setCode(cod);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(room);
        }catch(Exception ex){}
    }
    
    @Deprecated
    public static void escribirSalaT(RoomLayout sala, Schedule h) throws IOException{
        Room4Ticket ST = new Room4Ticket(sala, h.getCodPeli(), h.getFecha());
        FileOutputStream fout = new FileOutputStream("horarios/" + ST.getNombreArchivo());
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
    }
    
    @Deprecated
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
    
    @Deprecated
    public static void actualizarSalaT(Room4Ticket sala) throws IOException{
        File f = new File("horarios/" + sala.getNombreArchivo());
        if( f.exists()){
            f.delete();
        }
        FileOutputStream fout = new FileOutputStream(f);
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
    }
    
    public static boolean writeRooms(ArrayList<RoomLayout> rooms, String folder){
        ArrayList<RoomLayout> backup = readRooms(folder);
        File f = new File(folder);
        if(! f.exists())
            f.mkdir();
        File bak = new File("bak" + folder);
        f.renameTo(bak);
        try{
            for(RoomLayout r: rooms){
                writeRoom(r, folder);
            }
            bak.delete();
        }catch(Exception ex){
            f.delete();
            bak.renameTo(f);
            return false;
        }
        return true;
    }
    
    public static ArrayList<RoomLayout> readRooms(String folder){
        ArrayList<RoomLayout> rooms = new ArrayList<>();
        try{
            File fol = new File(folder);
            FileInputStream fin;
            ObjectInputStream oin;
            for(File file: fol.listFiles()){
                fin = new FileInputStream(file);
                oin = new ObjectInputStream(fin);
                rooms.add( (RoomLayout)(oin.readObject()) );
            }
        }catch(Exception ex){}
        return rooms;
    }
}