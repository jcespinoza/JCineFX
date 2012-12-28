package EDJC.security;

/**
 *
 * @author Jay C Espinoza
 */
public final class User{
    private String username = "";
    private char[] cred;
    private String NombreCompleto = "";
    private String fotoPath = "";
    private long filePointer;
    private boolean CredencialActiva = true; /*Delete this field*/

    public String getFotoPath() {
        return fotoPath;
    }

    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }
    
    public User(String username){
        this.username = username;
        setCredencialActiva(true);
    }
    
    public User(){
        username = "";
        NombreCompleto = "";
        cred = "000000".toCharArray();
        fotoPath = "";
    }
    
    public User(String username, char[] pass){
        this(username); // :D
        cred = pass;
        setCredencialActiva(true);
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

    @Override
    public String toString() {
        return "Username: " + username + " fotoPath: " +fotoPath + " Nombre: " + NombreCompleto + " isActive?: " + CredencialActiva;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Metodo para establecer el <code>cred</code> de un <code>User</code>.
     * @param cred El nuevo cred.
     * @throws IllegalPasswordLengthException Si el cred tiene menos de 6 caracteres.
     */
    public void setPassword(char[] cred) throws IllegalPasswordLengthException{
        if(cred.length < 6)
            throw new IllegalPasswordLengthException(cred.length);
        
        this.cred = cred;
    }
    public void setNombreCompleto(String nombre){
        this.NombreCompleto=nombre;
    }

    public void setCredencialActiva(boolean CredencialActiva) {
        this.CredencialActiva = CredencialActiva;
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
    public String getNombreCompleto(){
        return this.NombreCompleto;
    }

    public boolean isCredencialActiva() {
        return CredencialActiva;
    }

    public long getFilePointer() {
        return filePointer;
    }
}
