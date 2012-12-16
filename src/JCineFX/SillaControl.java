package JCineFX;

import EDJC.salas.sillas.SeatState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SillaControl extends AnchorPane implements Initializable{
    private SeatState state;
    private int number;
    private int row;
    @FXML
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public SillaControl(SeatState s, int row, int number){
        try{
            FXMLLoader fx = new FXMLLoader(getClass().getResource("SillaControl.fxml"));
            fx.setRoot(this);
            fx.setController(this);
            fx.load();
        }catch(Exception ex){
            System.out.println("Error" + ex);
        }
        this.number = number;
        this.row = row;
        this.state = s;
        label.setText("" + (char)(65+row) + (number + 1));

        setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        changeSillaColor(state);
    }

    public SeatState getState(){
        return state;
    }

    public int getNumber() {
        return number;
    }

    public int getRow() {
        return row;
    }
    
    public void setState(SeatState s){
        state = s;
        changeSillaColor(state);
    }
    
    private void changeSillaColor(SeatState s) {
        switch(s){
            case DISPONIBLE: case SELECCIONADO: case RESERVADO:
                label.setText(getName());
                break;
            default:
                label.setText("");
        }
        setStyle("-fx-background-color: rgb("+s.r()+","+s.g()+","+s.b()+");");
    }

    public String getName() {
        return "" + (char)(65 + row) + (number+1);
    }
}