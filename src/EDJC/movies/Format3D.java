package EDJC.movies;

/**
 *
 * @author Jay C Espinoza
 */
public enum Format3D {
    DIGITAL("Digital 3D"), EXTREME3D("Xtreme 3D"), REAL3D("Real 3D"), NONE("2D");
    
    private String descrip;
    
    private Format3D(String d){
        descrip = d;
    }
    
    public static Format3D parseFormat(String text){
        
        switch(text){
            case "NONE": case "2D":
                return Format3D.NONE;
            case "Real 3D":
                return Format3D.REAL3D;
            case "Xtreme 3D":
                return Format3D.EXTREME3D;
            case "Digital 3D":
                return Format3D.DIGITAL;
            default:
                throw new IllegalArgumentException("Tipo de Formato invalido");
        }
    }
    
    @Override
    public String toString(){
        return descrip;
    }
}
