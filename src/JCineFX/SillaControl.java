package JCineFX;

import EDJC.salas.sillas.SeatState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Jay C Espinoza
 */
public class SillaControl extends AnchorPane implements Initializable{
    private SeatState state;
    private int number;
    private int row;
    private Label label;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //initialize the button with its state
    }
    
    public SillaControl(SeatState s, int row, int number){
        super();
        this.number = number;
        this.row = row;
        this.state = s;
        label = new Label("" + (char)(65+row) + (number + 1));
        setTopAnchor(label, 0.0);
        setBottomAnchor(label, 0.0);
        setLeftAnchor(label, 0.0);
        setRightAnchor(label, 0.0);
        
        getChildren().add(label);
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
    
    private void changeSillaColor(SeatState state) {
        switch(state){
            case DISPONIBLE: case SELECCIONADO: case RESERVADO:
                label.setText(getName());
                break;
            default:
                label.setText("");
        }
        setId(state.toString());
    }

    public String getName() {
        return "" + (char)(65 + row) + (number+1);
    }
}