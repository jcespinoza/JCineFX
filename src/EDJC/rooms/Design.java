package EDJC.rooms;

import EDJC.rooms.seats.SeatState;
import JCineFX.ChairGrid;
import JCineFX.SillaControl;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Design{
    private final RoomLayout layout;
    private static EventHandler<MouseEvent> clickHandler;
    
    public Design(int filas, int cols, boolean is3D){
        layout = new RoomLayout(filas, cols, is3D);
        layout.makeAll(SeatState.AVAILABLE);
        clickHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                SillaControl s = ((SillaControl)t.getSource());
                if( s.getState() == SeatState.AVAILABLE){
                    s.setState(SeatState.HIDDEN);
                }else{
                    s.setState(SeatState.AVAILABLE);
                }
                layout.setSeat(s.getRow(), s.getNumber(), s.getState());
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
        Button saveButton = new Button("Guardar Disenio");
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
        System.out.println("Got here");
        ns.show();
    }
    
    public void guardarDisenio(RoomLayout sala) throws IOException{
        RoomBuilder.escribirSala(sala);
        System.out.println("Sala Guardada");
    }

    /**
     * Metodo para leer un RoomLayout de un archivo, que tiene en su nombre el codigo.
     * @param cod El codigo de la sala, este deberia estar antes de la extension .mov y despues de la palabra sala.
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public RoomLayout readSalaLayout(int cod) throws IOException, ClassNotFoundException{
        return RoomBuilder.leerSala(cod);
    }   

    public static EventHandler<MouseEvent> getHandler(){
        return clickHandler;
    }
}