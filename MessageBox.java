/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Juan Carlos Espinoza
 *
 */
public class MessageBox extends AnchorPane implements Initializable{

    public MessageBox(){
        FXMLLoader fx = new FXMLLoader(getClass().getResource("MessageBox.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}