package JCineFX;

import EDJC.salas.SalaLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Disenio implements EventHandler<MouseEvent>{
    private static RandomAccessFile raf;

    public static void showDesignDialog(){
        //codigo para hacer otra ventana
        //luego de mostrar, guardar el arreglo
    }
    
    public static void guardarDisenio(SalaLayout sala) throws IOException{
        FileOutputStream fout = new FileOutputStream("salas/sala" + JCineFX.leerConf().getContadorSala() + ".mov");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
    }

    /**
     * Metodo para leer un SalaLayout de un archivo, que tiene en su nombre el codigo.
     * @param cod El codigo de la sala, este deberia estar antes de la extension .mov y despues de la palabra sala.
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public static SalaLayout readSalaLayout(int cod) throws IOException, ClassNotFoundException{
        File f = new File("salas/sala" + cod + ".mov");
        if( !f.exists()){
            return null;
        }else{
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
            return (SalaLayout)(oin.readObject());
        }
    }   

    @Override
    public void handle(MouseEvent t) {
        //change the state of the label on click
        
    }
}