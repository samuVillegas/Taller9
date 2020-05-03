
public class ArmaCorto extends Arma{

    public ArmaCorto(String nombre, int dano) {
        super(nombre, dano,true,10);
    }

    //Metodo con polimorfismo
    @Override
    public void recargar() {
        super.setMunicion(10);
    }

    
    
}