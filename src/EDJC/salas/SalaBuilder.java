/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.salas;

import JCineFX.JCineFX;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 *
 * @author Jay C Espinoza
 */
public class SalaBuilder {
    public static void fillSalaChoices(ChoiceBox box, ArrayList<String> salas) {
        try{
            int count = JCineFX.leerConf().getContadorSala() - 1;
            for(int i = 1; i <= count; i++){
                String item = "Sala ";
                SalaLayout s = leerSala(i);
                item += ( s.is3D() )?"3D":"2D";
                box.getItems().add("" + i);
                salas.add(item);
            }
        }catch(Exception ex){
            System.out.println("Cant read Salacounter from file");
            ex.printStackTrace();
        }
    }
    
    public static SalaLayout leerSala(int cod) throws IOException, ClassNotFoundException{
        File f = new File("salas/sala" + cod + ".mov");
        if( !f.exists()){
            return null;
        }else{
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
            return (SalaLayout)(oin.readObject());
        }
    }
    
    public static void escribirSala(SalaLayout sala) throws IOException{
        FileOutputStream fout = new FileOutputStream("salas/sala" + JCineFX.leerConf().getContadorSala() + ".mov");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
        JCineFX.aumentarContSala();
    }
}
