

public abstract class  Arma {   
    private String nombre;
    private int dano;
    private int municion;
    private boolean estado;

    public Arma (String nombre,int dano,boolean estado,int municion){
        this.nombre=nombre;
        this.dano=dano;
        this.estado=true;
        this.municion=municion;
    }
    
    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public int getMunicion() {
        return municion;
    }

    public void setMunicion(int municion) {
        this.municion = municion;
    }

    //Metodo abstracto utilizado en la clase Juego
    public abstract void recargar();    

}