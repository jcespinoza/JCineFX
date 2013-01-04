/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import EDJC.movies.Format3D;
import EDJC.movies.Movie;
import EDJC.movies.MovieGenre;
import EDJC.movies.MovieType;
import EDJC.movies.Rating;
import EDJC.security.Config;
import EDJC.util.Util;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import jfxtras.labs.scene.control.ListSpinner;
import jfxtras.labs.scene.control.ListSpinnerIntegerList;
import jfxtras.labs.util.StringConverterFactory;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class AddMoviePanel extends AnchorPane implements Initializable{
    private AdminWindow father;
    private AnchorPane caller;
    private ListSpinner<Integer> lengthS;
    private ChangeListener fListener;
    //Variables for the movie
    private Movie movie; private String title; private int length;
    private MovieType type; private Format3D format; private Rating rating;
    private MovieGenre genre; private String imgPath;
    
    //FXML variables
    @FXML
    private GridPane grid;
    public TextField titleT;
    public ComboBox formatC;
    public ComboBox genreC;
    public ComboBox ratingC;
    public Button imgButton;
    public ImageView movImg;
    public Label titleMsg;
    public Label lengthMsg;
    public Label genreMsg;
    public Label formatMsg;
    public Label ratingMsg;
    public Label imgMsg;
   
    public AddMoviePanel(AdminWindow ad){
        father = ad;
        FXMLLoader fx = new FXMLLoader(getClass().getResource("/FXML/AddMoviePanel.fxml"));
        fx.setRoot(this);
        fx.setController(this);
        try{
            fx.load();
        }catch(Exception ex){}
        regFocusListener();
    }
    
    public AddMoviePanel(AdminWindow w, AnchorPane a){
        this(w);
        caller = a;
    }
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addSpinner();
    }
    
    private void addSpinner(){
        lengthS = new ListSpinner<>(new ListSpinnerIntegerList(0, 450))
                .withAlignment(Pos.CENTER)
                .withArrowDirection(ListSpinner.ArrowDirection.VERTICAL)
                .withArrowPosition(ListSpinner.ArrowPosition.SPLIT)
                .withEditable(Boolean.TRUE)
                .withStringConverter(StringConverterFactory.forInteger());
        GridPane.setConstraints(lengthS, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(5, 0, 0, 10));
        grid.add(lengthS, 1, 3);
    }
    
    @FXML
    private void handleChangePic(){
        String temp = Util.getPicture(father.conf.getLastMovPath(), father.getWindow());
        if(temp != null){
            father.conf.setLastMovPath(new File(temp).getParent());
            try{
                imgPath = (new URL("file:" + temp).toExternalForm());
                movImg.setImage(new Image(imgPath));
            }catch(Exception ex){ex.printStackTrace();}
        }
    }
    
    @FXML
    private void handleCancel(){
        //tha caller is the AnchorPane that created this object
        //as MainPanel is written it does not specify a caller, so it is null, thus causing caller not being an istance
        //of MovieListPanel, because it DOES set itself as the caller whent it creates this AddMoviePanel.
        if( caller instanceof MovieListPanel )
            father.loadMovieListPanel();
        else
            father.loadMainPanel();
    }
    
    @FXML
    private void handleAccept(){
        if ( !(checkTitle() & checkLength() & checkFormat()
                & checkGenre() & checkRating() & checkImage()) )
            return;
        createMovie();
        father.movies.add(movie);
        father.conf.incrementCounter(Config.MOVIE_COUNTER);
        MessageBox.show(father.getWindow(), "Completo", "La película se guardó exitosamente!");
        father.loadNewMoviePanel(caller);
    }
    
    private void createMovie(){
        movie = new Movie(father.conf.getMovCount(), length, title, genre, rating, format);
        if( imgPath == null)
            imgPath = movie.getDefaultImgPath();
        movie.setImgFile(imgPath);
    }
    
    private boolean checkTitle(){
        if( titleT.getText().length() < 2){
            titleMsg.setText("El titulo es demasiado corto.");
            if( titleT.getText().length() == 0)
                titleMsg.setText("El titulo no puede estar vacio.");
        }else{
            title = titleT.getText();
            return true;
        }
        return false;
    }
    
    private boolean checkLength(){
        int t = lengthS.getValue();
        if( t < 30 ){
            lengthMsg.setText("La duracion es muy corta.");
            if( t == 0)
                lengthMsg.setText("NO puede dejar la duracion de la pelicula en " + t);
        }else{
            length = t;
            return true;
        }
        return false;
    }
    
    private boolean checkFormat(){
        String ss = (String)(formatC.getValue());
        if( ss == null )
            formatMsg.setText("Debe seleccionar un formato.");
        else{
            format = Format3D.parseFormat(ss);
            return true;
        }
        return false;
    }
    
    private boolean checkGenre(){
        String ss = (String)(genreC.getValue());
        if( ss == null)
            genreMsg.setText("Debe seleccionar un Genero.");
        else{
            genre = MovieGenre.parseGenre(ss);
            return true;
        }
        return false;
    }
    
    private boolean checkRating(){
        String ss = (String)(ratingC.getValue());
        if( ss == null)
            ratingMsg.setText("Debe seleccionar un Genero.");
        else{
            rating = Rating.parseRating(ss);
            return true;
        }
        return false;
    }

    private boolean checkImage() {
        if( imgPath == null)
            imgMsg.setVisible(true);
        return true;
    }

    private void regFocusListener() {
        fListener = new ChangeListener() {
            @Override
            public void changed(ObservableValue focusProperty, Object prev, Object newValue) {
                //cast to ReadOnlyProperty, then to Node, to get the control that owns the changed property
                Node control = (Node)( ((ReadOnlyProperty)(focusProperty)).getBean() );
                boolean focus = (Boolean)newValue;

                if( control.equals(titleT))
                    handleTitleFocus(focus);
                else if( control.equals(lengthS))
                    handleLengthFocus(focus);
                else if( control.equals(formatC))
                    handleFormatFocus(focus);
                else if(control.equals(genreC))
                    handleGenreFocus(focus);
                else if(control.equals(ratingC))
                    handleRatingFocus(focus);
                else
                    handleImgFocus(focus);
            }
        };
        titleT.focusedProperty().addListener(fListener);
        lengthS.focusedProperty().addListener(fListener);
        formatC.focusedProperty().addListener(fListener);
        genreC.focusedProperty().addListener(fListener);
        ratingC.focusedProperty().addListener(fListener);
        imgButton.focusedProperty().addListener(fListener);
    }
    
    private void handleTitleFocus(boolean focus) {
        if( !focus )
            checkTitle();
        else
            titleMsg.setText("");
    }

    private void handleLengthFocus(boolean focus) {
        if( !focus )
            checkLength();
        else
            lengthMsg.setText("");
    }

    private void handleFormatFocus(boolean focus) {
        if( !focus )
            checkFormat();
        else
            formatMsg.setText("");
    }

    private void handleGenreFocus(boolean focus) {
        if( !focus )
            checkGenre();
        else
            genreMsg.setText("");
    }

    private void handleRatingFocus(boolean focus) {
        if( !focus )
            checkRating();
        else
            ratingMsg.setText("");
    }

    private void handleImgFocus(boolean focus) {
        if( !focus )
            checkImage();
        else
            imgMsg.setVisible(false);
    }
}