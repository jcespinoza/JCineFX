package JCineFX;

import EDJC.salas.SalaLayout;
import EDJC.salas.sillas.SeatState;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Disenio implements EventHandler<MouseEvent>{
    
    public static void showDesignDialog(){
        System.out.println("inicialezando");
        Stage ns = new Stage();
        System.out.println("passed stage creation");
        ns.setScene(new Scene(new ChairGrid(null, 10, 10, true)));
        System.out.println("let see N");
        ns.show();
        System.out.println("Disenio constructor donde");
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
        SillaControl s = ((SillaControl)t.getSource());
        if( s.getState() == SeatState.DISPONIBLE){
            s.setState(SeatState.OCULTO);
        }else{
            s.setState(SeatState.DISPONIBLE);
        }
    }
}