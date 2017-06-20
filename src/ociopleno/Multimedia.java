package ociopleno;
// Generated 04-may-2017 0:42:37 by Hibernate Tools 4.3.1



/**
 * Multimedia generated by hbm2java
 */
public class Multimedia  implements java.io.Serializable {


     private String nombreArchivo;
     private Actividad actividad;

    public Multimedia() {
    }

	
    public Multimedia(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public Multimedia(String nombreArchivo, Actividad actividad) {
       this.nombreArchivo = nombreArchivo;
       this.actividad = actividad;
    }
   
    public String getNombreArchivo() {
        return this.nombreArchivo;
    }
    
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public Actividad getActividad() {
        return this.actividad;
    }
    
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public void altaMultimedia(){
        Login.getSesion().save(this);
    }



}


