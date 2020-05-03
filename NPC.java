
public class NPC extends Humano{
    
    private String tipo;
    private String mensaje;
    protected Arma arma=new ArmaCorto("Navaja",2);

    public NPC(String nombre,String tipo,String mensaje) {
        super(nombre, 100, 1);
        this.tipo=tipo;
        this.mensaje=mensaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    //Metodo con polimorfismo de la clase padre.
    //Mueve a los NPC aleatoriamente.

    @Override
    public void moverse() {
        
        int i= (int) Math.floor(Math.random()*4);
        final int INCREMENTO= super.getVelocidad();
        int posX=super.getX();
        int posY=super.getY();
        
        if(i==1){
            super.setX(super.getX()+INCREMENTO);
        }
        if(i==2){
            super.setX(super.getX()-INCREMENTO);
        }
        if(i==3){
            super.setY(super.getY()+INCREMENTO);
        }
        if(i==4){
            super.setY(super.getY()-INCREMENTO);
        }
        
        if(Tablero.tablero[super.getY()][super.getX()]==null){
            Tablero.tablero[posY][posX]=null;
            Tablero.tablero[super.getY()][super.getX()]=this;
        }else{       
            super.setX(posX);
            super.setY(posY);
        
        }
  
    }
    
    //Metodo con polimorfismo
    
     @Override
    public String toString() {       
        return "Nombre: "+super.getNombre()+" Tipo: "+this.tipo+" Vida: "+super.getVida()+" Velocidad: "+super.getVelocidad()+" posX: "+super.getX()+" posY: "+super.getY()+" Mensaje: "+this.mensaje;
    }
}
