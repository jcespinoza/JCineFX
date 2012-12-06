package JCineFX;

import EDJC.seguridad.InvalidPasswordException;
import EDJC.seguridad.UserBuilder;
import EDJC.seguridad.Usuario;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class LoginRegisterController implements Initializable {
    private Configuracion conf;
    private Usuario usuario;
    
    @FXML
    public TitledPane titledLogin;
    public TitledPane titledRegister;
    public Label editLabel;
    public ImageView registerPicture;
    public String imgPath = "file:src/res/user-icon-big.png";
    public TextField userLog;
    public PasswordField passLog;
    public TextField userReg;
    public TextField nameReg;
    public PasswordField pass1Reg;
    public PasswordField pass2Reg;
    public Button registerButton;
    public Button loginButton;
    public ChangeListener cListener;
    //Notification Labels
    public Label wrongPassLog;
    public Label userMsgReg;
    public Label pass1MsgReg;
    public Label pass2MsgReg;
    
    @FXML
    private void handleRegisterButton(ActionEvent e){
        titledLogin.setCollapsible(true);
        titledRegister.setExpanded(true);
        titledLogin.setCollapsible(false);
    }
    
    @FXML
    private void handleLoginButton(ActionEvent e){
        boolean user = true, pass = true;
        if(userLog.getText().length() <= 1){
            System.out.println("Missing username");
            user = false;
        }
        if(passLog.getText().length() <= 1){
            System.out.println("Missing pass");
            pass = false;
        }
        if(!user && !pass)
            return;
            
        if(validateUser(e)){
            try{
                JCineFX.actualizarConf(usuario);
                System.out.println("Updated! Now Configuration is: " + JCineFX.leerConf());
            }catch(Exception ex){
                System.out.println("Could not update configuracion before opening Adminn window");
            }
            showAdminWindow(e);
        }else{
            System.out.println("Wring credes");
        }
    }
    
    @FXML
    private void handleFotoClick(MouseEvent e){
        //Codigo para poner la imagen aqui
        FileChooser fc = new FileChooser();
        String url = null;
        try {
            url = fc.showOpenDialog(editLabel.getScene().getWindow()).toURI().toURL().toString();
            Image img = new Image(url);
            registerPicture.setImage(img);
            registerPicture.setSmooth(true);
            imgPath = url;
        } catch (MalformedURLException ex) {
            System.out.println("Error was: " + ex);
        }
    }
    
    @FXML
    private void handleCancelarReg(ActionEvent e){
        titledLogin.setCollapsible(true);
        titledLogin.setExpanded(true);
        titledLogin.setCollapsible(false);
        cleanRegFields();
    }
    
    private void cleanRegFields(){
        userReg.setText(null);
        pass1Reg.setText(null);
        pass2Reg.setText(null);
        nameReg.setText(null);
        imgPath = "file:src/res/user-icon-big.png";
        try {
            registerPicture.setImage(new Image(imgPath));
        }catch (Exception ex) {
            System.out.println("Image was wrong: " + ex);
            File test = new File(imgPath);
            System.out.println("exists? " + test.exists());
            System.out.println(imgPath);
            System.out.println("File: " + test.getAbsolutePath());
        }
    }
    
    @FXML
    private void handleRegisterReg(ActionEvent e){
        Usuario temp = new Usuario();
        String user = userReg.getText();
        String name = nameReg.getText();
        char[] pass1 = pass1Reg.getText().toCharArray();
        char[] pass2 = pass2Reg.getText().toCharArray();
        if(!Arrays.equals(pass1, pass2)){
            System.out.println("Passwords dont match!");
            return;
        }
        String picture = imgPath;

        try{
            temp.setUsername(user);
            temp.setPassword(pass1);
            temp.SetNombreCompleto(name);
            temp.setFotoPath(imgPath);
            
            Usuario result = UserBuilder.leerUser(user);
            if(result == null){
                UserBuilder.escribirUser(temp);
                cleanRegFields();
                System.out.println("sAved succesfuly!");
                handleCancelarReg(e);
            }else{
                cleanRegFields();
            }
        }catch(InvalidPasswordException ex){
            System.out.println(ex.getMessage());
        }catch(IOException ex){
            System.out.println("IOException while trying to read or write User." + ex);
        }
    }
    
    @FXML
    private void handleEntered(MouseEvent e){
        String image = JCineFX.class.getResource("edit.png").toExternalForm();
        editLabel.setStyle("-fx-background-image: url('" + image + "');"+
                        "-fx-background-repeat:round;" +
                        "-fx-border-radius: 2px;" +
                        "-fx-border-color: white;");
    }
    
    @FXML
    private void handleExited(MouseEvent e){
        editLabel.setStyle(null);
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        titledLogin.setCollapsible(false);
        userLog.setPromptText("Ingrese su username");
        passLog.setPromptText("Ingrese su contraseña");
        userReg.setPromptText("Ingrese su username");
        nameReg.setPromptText("Ingrese su nombre completo");
        pass1Reg.setPromptText("Ingrese una contraseña");
        pass2Reg.setPromptText("Confirme su contraseña");
        loginButton.setDefaultButton(true);
        
        //Inicializa el changeListener nates de asignarselo a los controles
        initFocusListener();
        
        //Add focus change listener for input fields
        userLog.focusedProperty().addListener(cListener);
        passLog.focusedProperty().addListener(cListener);
        
        userReg.focusedProperty().addListener(cListener);
        pass1Reg.focusedProperty().addListener(cListener);
        pass2Reg.focusedProperty().addListener(cListener);
    }    

    private boolean validateUser(ActionEvent e) {
        String user = userLog.getText();
        char[] pass = passLog.getText().toCharArray();
        Usuario us = new Usuario(user, pass);
        Usuario result;
        try{
            result = UserBuilder.leerUser(user);
        }catch(IOException ex){
            ex.printStackTrace();
            return false;
        }
        if(result != null && (Arrays.equals(pass, result.getPassword()))){
            usuario = us;
            return true;
        }
        System.out.println("Didn't find it!");
        return false;
    }

    private void showAdminWindow(ActionEvent e) {
        try{
            Parent p = FXMLLoader.load(getClass().getResource("AdminWindow.fxml"));
            Stage st = new Stage();
            Scene sce = new Scene(p);
            st.setScene(sce);
            //st.initOwner(lblTitle.getScene().getWindow());
            //st.initModality(Modality.APPLICATION_MODAL);
            st.show();
            st.setResizable(false);
            ((Node)e.getSource()).getScene().getWindow().hide();
        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    private void initFocusListener() {
        cListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue focusProperty, Object previous, Object newValue) {
                //cast to ReadOnlyProperty, then to Node, to get the control that owns the changed property
                Node control = (Node)( ((ReadOnlyProperty)(focusProperty)).getBean() );
                boolean focus = (Boolean)newValue;
                
                //check wich control triggered the event and call the corresponding method
                if(control.equals(userLog)){
                    handleUserLogFocus(focus);
                }else if(control.equals(passLog)){
                    handlePassLogfocus(focus);
                }else if(control.equals(userReg)){
                    handleUserRegFocus(focus);
                }else if(control.equals(pass1Reg)){
                    handlePass1RegFocus(focus);
                }else if(control.equals(pass2Reg)){
                    handlePass2RegFocus(focus);
                }
            }
        };
    }
    
    private void handleUserLogFocus(Boolean hasFocus){
        System.out.println("userLog focus: " + hasFocus);
    }
    
    private void handlePassLogfocus(boolean hasFocus) {
        System.out.println("passLog focus: " + hasFocus);
    }

    private void handleUserRegFocus(boolean hasFocus) {
        System.out.println("userReg focus: " + hasFocus);
    }

    private void handlePass1RegFocus(boolean hasFocus) {
        System.out.println("pass1Reg focus: " + hasFocus);
    }

    private void handlePass2RegFocus(boolean hasFocus) {
        System.out.println("pass2Reg focus: " + hasFocus);
    }
}