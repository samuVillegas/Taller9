
public class ArmaLargo extends Arma {

    public ArmaLargo(String nombre, int dano) {
        super(nombre, dano, true, 5);
    }

    //Metodo con polimorfismo
    @Override
    public void recargar() {
        super.setMunicion(5);
    }

}
