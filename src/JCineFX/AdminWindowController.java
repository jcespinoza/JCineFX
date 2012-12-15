/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.peliculas.Formato3D;
import EDJC.peliculas.GeneroPelicula;
import EDJC.peliculas.Pelicula;
import EDJC.peliculas.Pelicula2D;
import EDJC.peliculas.Pelicula3D;
import EDJC.peliculas.PeliculaBuilder;
import EDJC.peliculas.TipoClasificacion;
import EDJC.peliculas.TipoPelicula;
import EDJC.seguridad.UserBuilder;
import EDJC.seguridad.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Jay C Espinoza
 */
public class AdminWindowController implements Initializable {
    @FXML
    public AnchorPane horariosPane;
    public TextField usernameField;
    public TextField nombreField;
    public PasswordField oldPassField;
    public PasswordField newPass1Field;
    public PasswordField newPass2Field;
    //Sala tab
    public TextField filasField;
    public TextField colsField;
    public ChoiceBox tipoSalaCombo;
    //Pelicula Tab
    public TextField tituloField;
    public TextField duracionField;
    public ComboBox formatoCombo;
    public ComboBox generoCombo;
    public ComboBox clasiCombo;
    private String defaultPeliImg = "file:src/res/img/default_movie.png";
    public String peliImg = "file:src/res/img/default_movie.png";
    public ImageView imgPelicula;
    //UserIngo tab
    public Button updateButton;
    public Button btnImagenUser;
    public ImageView imgUser;
    public String imagePath = "file:src/res/user-icon-big.png"; //imagen por defecto
    public AnchorPane sillasPane;
    
    
    @FXML
    public MenuButton opMenu;
    private Configuracion localConf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            localConf = JCineFX.leerConf();
            fillUserData();
            if( JCineFX.getCurrentUser().getUsername().equals("guest") )
                disableInput();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        fillHorarioPane();
    }
    
    @FXML
    private void handleExit(ActionEvent e){
        Platform.exit();
    }
    
    @FXML
    private void handleLogOut(ActionEvent e){
        System.out.println("Loggin out...");
    }
    
    @FXML
    private void handleUpdateButton(ActionEvent e){
        try {
            boolean oldPassOk = false;
            boolean newPassOk = false;
            boolean passLength = true;
            if( oldPassField.getText().equals( new String(JCineFX.getCurrentUser().getPassword())) )
                oldPassOk = true;
            if( newPass1Field.getText().equals( newPass2Field.getText() ))
                newPassOk = true;
            if( newPass1Field.getText().length() < 6 )
                passLength = false;
            
            if( oldPassOk && newPassOk && passLength ){
                UserBuilder.desactivarUsuario(localConf.getUsuarioActual());
                Usuario us = new Usuario();
                us.setUsername( usernameField.getText() );
                us.setPassword( newPass1Field.getText().toCharArray());
                us.setFotoPath(imagePath);
                us.setNombreCompleto( nombreField.getText() );
                UserBuilder.escribirUser(us);
                System.out.println("Saving user. Succes!");
            }else{
                //si algo estaba mal, tomar acciones basandose en los boolean de alla arriba
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to find file");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void disableInput(){
        usernameField.setEditable(false);
        nombreField.setEditable(false);
        oldPassField.setEditable(false);
        newPass1Field.setEditable(false);
        newPass2Field.setEditable(false);
        updateButton.setDisable(true);
        btnImagenUser.setDisable(true);
    }

    private void fillUserData() throws ClassNotFoundException, IOException {
        usernameField.setText(JCineFX.getCurrentUser().getUsername());
        nombreField.setText(JCineFX.getCurrentUser().getNombreCompleto());
        oldPassField.setText(new String(JCineFX.getCurrentUser().getPassword()));
        String tempPath = JCineFX.getCurrentUser().getFotoPath();
        System.out.println("TempFile:" + tempPath);
        try{
            imgUser.setImage(new Image(tempPath));
            System.out.println("Succesfuly set the image:" + tempPath);
        }catch(IllegalArgumentException ex){
            System.out.println("Failed to set the image:" + tempPath);
            imgUser.setImage(new Image(imagePath));
        }
    }

    private void fillHorarioPane(){
        horariosPane.getChildren().add(new TestPane(horariosPane));
    }
    
    @FXML
    private void handleSaveMovie(ActionEvent e){
        try {
            
            int cod                 = JCineFX.getMovCounter();
            String title            = tituloField.getText();
            int dur                 = Integer.parseInt(duracionField.getText());
            TipoPelicula tipo       = TipoPelicula.parseTipo( (String)(formatoCombo.getValue()) );
            Formato3D form          = Formato3D.parseFormato( (String)formatoCombo.getValue() );
            GeneroPelicula gen      = GeneroPelicula.parseGenero( ( (String)generoCombo.getValue() ) );
            TipoClasificacion clas  = TipoClasificacion.parseClas( (String)clasiCombo.getValue() );
            Date adicion            = new Date();
            
            Pelicula peli;
            if(tipo == TipoPelicula.PELICULA2D)
                peli = new Pelicula2D(cod, dur, title, gen, clas);
            else
                peli = new Pelicula3D(cod, dur, title, gen, clas, form);
            
            peli.setFechaAdicion(adicion);
            peli.setImgArchivo(peliImg);
            PeliculaBuilder.escribirPelicula(peli);
            JCineFX.setMovCounter(1); //aumenta el contador de peliculas en 1
            JCineFX.aumentarContMov();
            cancelPelicula(e);//limpia los campos despues de guardar la pelicula
        }catch(NumberFormatException ex){
            System.out.println("Duracion contains invalid characters");
        }catch(IllegalArgumentException ex){
            System.out.println("Not enough information");
        }catch (IOException ex) {
            System.out.println("Failed to save movie");
            ex.printStackTrace();
        }
    }
    
    @FXML
    private void cancelPelicula(ActionEvent e){
        tituloField.setText(null);
        duracionField.setText(null);
        formatoCombo.setValue(null);
        generoCombo.setValue(null);
        clasiCombo.setValue(null);
        imgPelicula.setImage(new Image(defaultPeliImg));
    }
    
    @FXML
    private void handleBuscarImg(ActionEvent e){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        String temp = fc.showOpenDialog( imgPelicula.getScene().getWindow() ).getPath();
        if( temp != null){
            peliImg = "file:" + temp;  
        }
        imgPelicula.setImage(new Image(peliImg) );
    }
    
    @FXML
    private void handleChangeUserImg(ActionEvent e){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        String temp = fc.showOpenDialog( imgUser.getScene().getWindow() ).getPath();
        if( temp != null ){
            imagePath = "file:" + temp;
        }
        imgUser.setImage( new Image(imagePath));
    }
    
    @FXML
    private void handleDiseniar(ActionEvent e){
        try{
            int filas = Integer.parseInt(filasField.getText());
            int cols = Integer.parseInt(colsField.getText());
            String tipo = (String)(tipoSalaCombo.getValue());
            boolean _3D = tipo.equals("3D");
            Disenio dis = new Disenio(filas, cols, _3D);
            dis.showDesignDialog();
        }catch(NumberFormatException ex){
            System.out.println("There were no numbers");
        }catch(NullPointerException ex){
            System.out.println("Tipo de Sala not selected");
        }
    }
}