/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package JCineFX;

import EDJC.peliculas.Formato3D;
import EDJC.peliculas.GeneroPelicula;
import EDJC.peliculas.Pelicula;
import EDJC.peliculas.PeliculaBuilder;
import EDJC.peliculas.TipoClasificacion;
import EDJC.peliculas.TipoPelicula;
import EDJC.salas.Disenio;
import EDJC.salas.HorarioBuilder;
import EDJC.salas.SalaBuilder;
import EDJC.seguridad.UserBuilder;
import EDJC.seguridad.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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
    public TitledPane x4;
    public TitledPane exHorario;
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
    public Tab profileTab;
    public Button updateButton;
    public Button btnImagenUser;
    public ImageView imgUser;
    public String imagePath = "file:src/res/user-icon-big.png"; //imagen por defecto
    public AnchorPane sillasPane;
    
    
    @FXML
    public Menu userMenuItem;
    private Configuracion localConf;
    public TabPane tabs;
    private HorarioPanel hp;
    public HBox hboxHoras;
    public ChoiceBox salasChoice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            localConf = JCineFX.leerConf();
            fillUserData();
            userMenuItem.setText(localConf.getUsuarioActual());
            if( JCineFX.getCurrentUser().getUsername().equals("guest") )
                disableInput();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        SalaBuilder.fillSalaChoices(salasChoice, new ArrayList<String>());
        salasChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                loadHorarios();
                System.out.println("Salachoice changed " + t1);
            }
        });
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
        try{
            imgUser.setImage(new Image(tempPath));
        }catch(IllegalArgumentException ex){
            imgUser.setImage(new Image(imagePath));
        }
    }

    private void fillHorarioPane(){
        hp = new HorarioPanel(x4, this);
        horariosPane.setTopAnchor(hp, 0.0);
        horariosPane.setBottomAnchor(hp, 0.0);
        horariosPane.setLeftAnchor(hp, 0.0);
        horariosPane.setRightAnchor(hp, 0.0);
        horariosPane.getChildren().add(hp);
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
            
            Pelicula peli = new Pelicula(cod, dur, title, gen, clas, form);
            
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
        fc.setInitialDirectory(new File(System.getProperty("user.dir") + "/src/res/img"));
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
    
    @FXML
    private void handleMod(ActionEvent e){
        SelectionModel<Tab> tSelection = tabs.getSelectionModel();
        tSelection.select(profileTab);
    }
    
    public void loadHorarios(){
        HorarioBuilder.fillPanel(hboxHoras, salasChoice);
    }
    
    @FXML
    private void handleNewHorario(ActionEvent e){
        horariosPane.getChildren().removeAll(horariosPane.getChildren());
        fillHorarioPane();
        x4.setCollapsible(true);
        exHorario.setExpanded(true);
        x4.setCollapsible(false);
    }
}