package EDJC.security;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Jay C Espinoza
 */
public final class User implements Serializable{
    private String username = "";
    private char[] cred;
    private String FullName = "";
    private String picPath = "";
    private long filePointer;
    private boolean activeCredential = true; /*Delete this field*/
    
    public User(String username){
        this.username = username;
        setCredential(true);/*delete this*/
    }
    
    public User(){
        username = "";
        FullName = "";
        cred = "000000".toCharArray();
        picPath = "";
    }
    
    public User(String username, char[] pass){
        this(username); // :D
        cred = pass;
        setCredential(true);
    }
    
    /**
     * Metodo para comparar objetos de tipo User.
     * @param obj el <code>Object</code> con el cual se va a comparar.
     * @return <code>true</code> Si <code>obj</code> es una instancia de <code>User</code>, 
     * son del mismo <code>TipoUsuario</code> tiene el mismo <code>username</code> y mismo <code>nombreCompleto</code>. 
     * @return <code> false</code> de lo contrario.
     */
    @Override
    public boolean equals(Object obj){
        boolean sameObjectType = obj instanceof User;
        boolean sameUsername = this.username.equals( ((User)obj).username);
        //boolean sameNombreCompleto = this.NombreCompleto.equals( ((User)obj).NombreCompleto);
        return sameObjectType && sameUsername;
    }
    
    public boolean compareTo(User u){
        boolean usr = u.getUsername().equals(this.username);
        boolean pas = Arrays.equals(u.getPassword(), cred);
        boolean nam = u.getFullName().equals(this.FullName);
        boolean pic = u.getPicturePath().equals(this.picPath);
        return usr && pas && nam && pic;
    }

    @Override
    public String toString() {
        return "Username: " + username + " fotoPath: " +picPath + " Nombre: " + FullName + " isActive?: " + activeCredential;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPicturePath() {
        return picPath;
    }

    public void setPicturePath(String fotoPath) {
        this.picPath = fotoPath;
    }
    
    /**
     * Metodo para establecer el <code>cred</code> de un <code>User</code>.
     * @param cred El nuevo cred.
     * @throws IllegalPasswordLengthException Si el cred tiene menos de 6 caracteres.
     */
    public void setPassword(char[] cred) throws IllegalPasswordLengthException{
        if(cred.length < 6);
            //throw new IllegalPasswordLengthException(cred.length);
        
        this.cred = cred;
    }
    public void setFullName(String nombre){
        this.FullName=nombre;
    }

    public void setCredential(boolean CredencialActiva) {
        this.activeCredential = CredencialActiva;
    }

    public void setFilePointer(long filePointer) {
        this.filePointer = filePointer;
    }
    
    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return cred;
    }
    public String getFullName(){
        return this.FullName;
    }

    public boolean isCredentialActive() {
        return activeCredential;
    }

    public long getFilePointer() {
        return filePointer;
    }
}
