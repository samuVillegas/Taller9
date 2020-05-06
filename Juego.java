import java.util.ArrayList;
import java.util.Scanner;

public class Juego {

    ArrayList<Humano> arr;
    private int numEnemigos = 1;
    private int muertesEnemigos = 0;

    //Añade el participante
    public Juego() {
        arr = new ArrayList<>();
    }

    //Metodo principal para el juego
    public void jugar() {
        
        Scanner scan = new Scanner(System.in);       
        System.out.println("Bienvenido a Caza Dragones.");
        System.out.print("Ingresa tu nombre: ");
        String nombre= scan.next();
        System.out.print("Ingresa tu Skin: ");
        String skin = scan.next();
        arr.add(new JugadorNormal(nombre, skin));
        System.out.println("Los controles son: W:arriba, S:abajo, D: derecha, A: izquierda, M: Menú. Luego preciona Enter");

        Tablero t1 = new Tablero();
        agregarEnemigos(numEnemigos);
        while (true) {
            t1.MostrarTabla();
            mostrarPersonajes();
            System.out.println("Ingresa M Para IR al menú de Armas. De lo contrario haga caso omiso del mensaje");

            for (int i = 0; i < arr.size(); i++) {
                arr.get(i).moverse();
            }
            int n = Cercania();
            if (n == 1) {
                break;
            } else if (n == 2) {
                muertesEnemigos++;
                if (muertesEnemigos == numEnemigos) {
                    numEnemigos++;
                    agregarEnemigos(numEnemigos);
                    JugadorNormal j1 = (JugadorNormal) arr.get(0);
                    j1.setNivel(numEnemigos);
                    agregarVidas(numEnemigos);
                    muertesEnemigos = 0;
                }

            }
        }
        System.out.println("Haz Perdido");

    }

    //Verifica si el usuaario esta rodeado por un enemigo, de ser asi pasa al metodo ataque.
    //Luego devuelve un entero dependiendo del resultado de la pelea
    public int Cercania() {

        int valor = 0;
        int posX = arr.get(0).getX();
        int posY = arr.get(0).getY();

        for (int i = -1; i < 2; i++) {

            for (int j = -1; j < 2; j++) {
                int x = posX - i;
                int y = posY - j;

                if (verificar(x, y)) {
                    if (verificarExistencia(x, y)) {
                        if (i != 0 || j != 0) {
                            String tipo = Tablero.tablero[y][x].getNombre();

                            if (!tipo.equals("Vida")) {
                                int n = Ataque((JugadorNormal) Tablero.tablero[posY][posX], (NPC) Tablero.tablero[y][x]);
                                if (n == 1) {
                                    valor = 1;
                                }
                                 if (n == 2) {
                                    valor = 2;
                                    break;
                                }
                            }

                        }
                    }
                }

            }

        }

        return valor;
    }
    
    
    //Verifica que el la posicion a buscar exista en la matriz
    public boolean verificar(int a, int b) {
        boolean ver = true;
        if (a < 0 || b < 0) {
            ver = false;
        }
        if (a > 9 || b > 19) {
            ver = false;
        }

        return ver;
    }

    //Verifica la existencia de otro objeto
    public boolean verificarExistencia(int x, int y) {
        return Tablero.tablero[y][x] != null;
    }

    //Realiza el ataque para ambos Humanos
    //Nota: El ataque producido es la suma del daño del arma + la tirada de dados o random.
    
    public int Ataque(JugadorNormal h1, NPC h2) {
        int valor = 0;

        int dano_arma1 = 0;
        int dano_arma2 = 0;
        int municion1 = 0;

        //Ataque con dado  
        int ataque1 = (int) Math.floor(Math.random() * 7 + 1);
        int ataque2 = (int) Math.floor(Math.random() * 7 + 1);

        //Ataque con arma
        if (!h1.armas.isEmpty()) {
            
            int ubi_arma = ((JugadorNormal) h1).armaActiva();
            municion1 = h1.armas.get(ubi_arma).getMunicion();

            if (municion1 > 0) {
                dano_arma1 = h1.armas.get(ubi_arma).getDano();
                h1.armas.get(ubi_arma).setMunicion(municion1 - 1);
            } else {
                h1.armas.get(ubi_arma).recargar();
                System.out.println("Recargando...");
            }

        }
        
        int municion2 = h2.arma.getMunicion();

        if (municion2 > 0) {
            dano_arma2 = h2.arma.getDano();
            h2.arma.setMunicion(municion1 - 1);
        } else {
            
            h2.arma.recargar();
        }

        int suma_ataque1 = ataque1 + dano_arma1;
        int suma_ataque2 = ataque2 + dano_arma2;

        h1.setVida(h1.getVida() - suma_ataque2);
        h2.setVida(h2.getVida() - suma_ataque1);

        if (h2.getVida() == 0) {
            Tablero.tablero[h2.getY()][h2.getX()] = null;
            arr.remove(h2);
            valor = 2;
        }
        if (h1.getVida() == 0) {
            valor = 1;
        }
        return valor;
    }
     //Permite agregar enemigos, lo hace dependiendo el nivel.
    public void agregarEnemigos(int num) {
        for (int i = 0; i < num; i++) {
            arr.add(new NPC("Dragon", "Enemigo", "Te voy a matar"));
        }
    }

    //Agrega vidas al tablero más no al ArrayPrincipal dado que no es un agente permanente.
    //Nota: Tambien lo hace dependiendo el nivel.
    public void agregarVidas(int num) {
        for (int i = 0; i < num; i++) {
            NPC npc = new NPC("Vida", "Amigo", "Ve por mi");
        }
    }
    
        //Permite mostrar los personajes existentes en el ArrayLIst con algunos datos.
    public void mostrarPersonajes() {

        for (Humano humano : arr) {
            String str = "";
            if (humano instanceof JugadorNormal) {
                JugadorNormal j1 = ((JugadorNormal) humano);

                if(!j1.armas.isEmpty()){
                    str = "[" + j1.toString() + " Cantidad de armas: " + j1.armas.size() + " Arma activa: "
                        + j1.armaActiva() + " Municion: " + j1.armas.get(j1.armaActiva()).getMunicion()+" ]";
                }else{
                    str= "[ "+j1.toString()+" Cantidad de armas: 0. Obtenga el arma en el menú ]";
                }
                

            } else {
                str = "[" + humano.toString() + " Cantidad de armas: " + 1+" ]";
            }

            System.out.println(str);

        }

    }

}
