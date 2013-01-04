package EDJC.security;

import EDJC.movies.Movie;
import EDJC.rooms.RoomLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Juan Carlos Espinoza
 *
 */
public class Config implements Serializable{
    public static final int MOVIE_COUNTER = 1;
    public static final int ROOM_COUNTER = 2;
    public static final double DISCOUNT_CHILD = 0.5;
    public static final double DISCOUNT_ADULT = 1;
    public static final double DISCOUNT_OLD = 0.35;
    private User user;
    private int roomCount;
    private int movCount;
    private int selectedRoom;
    private String lastUserPath;
    private String lastMovPath;
    private double priceDigital;
    private double price3D;
    private double priceNormal;
    
    public Config(){}

    public Config(User user, int roomCount, int movCount, String lastPath) {
        this.user = user;
        this.roomCount = roomCount;
        this.movCount = movCount;
        this.lastUserPath = lastPath;
    }

    public User getUser() {return user;}

    public void setUser(User user) {
        this.user = user;
    }

    public double getPriceDigital() {return priceDigital;}

    public void setPriceDigital(double price) {
        this.priceDigital = price;
    }
    
    public double getPrice3D() {return price3D;}

    public void setPrice3D(double price) {
        this.price3D = price;
    }
    
    public double getPriceNormal() {return priceNormal;}

    public void setPriceNormal(double price) {
        this.priceNormal = price;
    }

    public int getRoomCount() {return roomCount;}

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getMovCount() {return movCount;}

    public void setMovCount(int movCount) {
        this.movCount = movCount;
    }

    public int getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(int selectedRoom) {
        this.selectedRoom = selectedRoom;
    }
    
    public static int getMinimumCount(){
        return 1;
    }
    
    public void incrementCounter(int type){
        switch(type){
            case MOVIE_COUNTER:
                movCount++;
                break;
            case ROOM_COUNTER:
                roomCount++;
                break;
        }
    }

    public String getLastUserPath() {return lastUserPath;}

    public void setLastUserPath(String lastUserPath) {
        this.lastUserPath = lastUserPath;
    }

    public String getLastMovPath() {return lastMovPath;}

    public void setLastMovPath(String lastMovPath){
        this.lastMovPath = lastMovPath;
    }

    
    public static Config getDefault(){
        Config conf = new Config();
        User s = new User("guest", "password".toCharArray());
        conf.setUser(s);
        conf.setLastUserPath(System.getProperty("user.dir"));
        conf.setLastMovPath( conf.getLastUserPath() );
        conf.setMovCount(getMinimumCount());
        conf.setRoomCount(getMinimumCount());
        conf.setPrice3D(110.0);
        conf.setPriceDigital(90);
        conf.setPriceNormal(70);
        return conf;
    }
    
    public static int getSafeCode(int type, ArrayList<? extends Object> list){
        int max = 1;
        if(type == MOVIE_COUNTER){
            ArrayList<Movie> movs = (ArrayList<Movie>)(list);
            for(Movie m: movs){
                if(m.getCode() > max)
                    max = m.getCode();
            }
            return max +1;
        }else if(type == ROOM_COUNTER){
            ArrayList<RoomLayout> rooms = (ArrayList<RoomLayout>)(list);
            for(RoomLayout r: rooms){
                if(r.getCode() > max)
                    max = r.getCode();
            }
            return max +1;
        }else{
            throw new IllegalArgumentException("No such type");
        }
    }
    
    public static boolean saveToDisk(String path, Config con){
        try{
            File f = new File(path);
            FileOutputStream fous = new FileOutputStream(f);
            ObjectOutputStream ous = new ObjectOutputStream(fous);
            ous.writeObject(con);
        }catch(Exception ex){return false;}
        
        return true;
    }
    
    public static Config loadFromDisk(String path){
        Config c = null;
        try{
            File f = new File(path);
            FileInputStream fous = new FileInputStream(f);
            ObjectInputStream ous = new ObjectInputStream(fous);
            c = (Config)(ous.readObject());
        }catch(Exception ex){return null;}
        return c;
    }
    
    @Override
    public String toString(){
        return "Configuration:\n"+
                user.getUsername() + " " +
                "MCounter: " + this.movCount +
                " RCounter: " + this.roomCount +
                " Selected Room: " + this.selectedRoom;
    }
}