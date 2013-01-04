/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EDJC.movies;

import java.util.Date;

/**
 *
 * @author Jay C Espinoza
 */
public class Movie {
    protected int code;
    protected int lenght;
    protected String title;
    protected MovieGenre genre;
    protected Rating rating;
    protected MovieType type;
    protected Date aditionDate;
    protected String imgFile;
    protected Format3D format3D = Format3D.NONE;

    public Movie(int cod, int d, String n, MovieGenre g, Rating c, Format3D f) {
        this.code = cod;
        this.lenght = d;
        this.title = n;
        this.genre = g;
        this.rating = c;
        this.format3D = f;
        if(f == Format3D.NONE)
            type = MovieType.MOVIE2D;
        else
            type = MovieType.MOVIE3D;
    }

    public Format3D getFormat3D() {
        return format3D;
    }
    
    public boolean is3D(){
        return (format3D != Format3D.NONE);
    }

    public int getCode() {
        return code;
    }

    public int getLenght() {
        return lenght;
    }

    public String getTitle() {
        return title;
    }

    public MovieGenre getGenre() {
        return genre;
    }

    public Rating getRating() {
        return rating;
    }

    public MovieType getType() {
        return type;
    }
    public String getImgFile() {
        return imgFile;
    }

    public void setImgFile(String imgFile) {
        this.imgFile = imgFile;
    }

    public Date getAditionDate() {
        return aditionDate;
    }

    public void setAditionDate(Date aditionDate) {
        this.aditionDate = aditionDate;
    }

    public String getDefaultImgPath() {
        return getClass().getResource("res/img/default_movie.png").getPath();
    }
    
    @Override
    public String toString(){
        return "Title: " + getTitle() +
                " Length: " + getLenght() +
                " Genre:  " + getGenre() +
                " Rating: " + getRating() +
                " Format: " + getFormat3D() +
                " is 3D: " + is3D()+
                " Picture: " + getImgFile();
    }
}