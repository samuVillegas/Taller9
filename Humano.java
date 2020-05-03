
public  abstract class Humano {    
   
    private String nombre;
    private int vida;
    private int velocidad;
    private int x;
    private int y;
    private String direccion;
    
    

    public Humano(String nombre,int vida,int velocidad){
        this.nombre=nombre;
        this.vida=vida;
        this.velocidad=velocidad;
        generarUbicacion();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if(vida<0){
            this.vida = 0;
            System.out.println(this.nombre+" Ha muerto");
        }else{
            this.vida = vida;
        }       
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        int v=x;
        if(x>9){
            v=9;
        }     
        if(x<0){
            v=0;
        }
        this.x=v;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        int v=y;
        if(y>19){
            v=19;
        }     
        if(y<0){
            v=0;
        }
        this.y=v;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if(!direccion.equalsIgnoreCase("R")&& !direccion.equalsIgnoreCase("L") && !direccion.equalsIgnoreCase("U") && !direccion.equalsIgnoreCase("D")){
            this.direccion = "D";
        }else{
            this.direccion = direccion;
        }
    }

    public void generarUbicacion(){
       this.x = (int) Math.floor(Math.random()*10);
       this.y = (int) Math.floor(Math.random()*20); 
       this.direccion="D";
       Tablero.tablero[this.y][this.x]= this; 
    }
    
   public abstract void moverse();

    @Override
   public abstract String toString(); 

    void ObtenerArma(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}