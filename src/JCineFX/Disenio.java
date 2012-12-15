package JCineFX;

import EDJC.salas.SalaLayout;
import EDJC.salas.sillas.SeatState;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Disenio{
    private final SalaLayout layout;
    private static EventHandler<MouseEvent> clickHandler;
    
    public Disenio(int filas, int cols, boolean is3D){
        layout = new SalaLayout(filas, cols, is3D);
        layout.makeAll(SeatState.DISPONIBLE);
        clickHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                SillaControl s = ((SillaControl)t.getSource());
                System.out.println("I'm " + s.getName() + ". My previous state was " + s.getState());
                if( s.getState() == SeatState.DISPONIBLE){
                    s.setState(SeatState.OCULTO);
                }else{
                    s.setState(SeatState.DISPONIBLE);
                }
                layout.setSilla(s.getRow(), s.getNumber(), s.getState());
            }
        };
    }
    
    public void showDesignDialog(){
        final Stage ns = new Stage();
        VBox content = new VBox();
        content.setAlignment(Pos.CENTER);
        content.getChildren().add(new Text("Pantalla"));
        ChairGrid chairs = new ChairGrid(layout, true);
        content.getChildren().add(chairs);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {
                try {
                    guardarDisenio(layout);
                    ns.hide();
                } catch (IOException ex) {
                    System.out.println("Error");
                }
            }
        });
        content.getChildren().add(saveButton);
        ns.setScene(new Scene(content));
        ns.show();
    }
    
    public void guardarDisenio(SalaLayout sala) throws IOException{
        FileOutputStream fout = new FileOutputStream("salas/sala" + JCineFX.leerConf().getContadorSala() + ".mov");
        ObjectOutputStream out = new ObjectOutputStream(fout);
        out.writeObject(sala);
        JCineFX.aumentarContSala();
        System.out.println("sala saved");
    }

    /**
     * Metodo para leer un SalaLayout de un archivo, que tiene en su nombre el codigo.
     * @param cod El codigo de la sala, este deberia estar antes de la extension .mov y despues de la palabra sala.
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public SalaLayout readSalaLayout(int cod) throws IOException, ClassNotFoundException{
        File f = new File("salas/sala" + cod + ".mov");
        if( !f.exists()){
            return null;
        }else{
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
            return (SalaLayout)(oin.readObject());
        }
    }   

    public static EventHandler<MouseEvent> getHandler(){
        return clickHandler;
    }
}