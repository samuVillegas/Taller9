   

import java.util.ArrayList;
import java.util.Scanner;


public class JugadorNormal extends Humano{
    
    private String skin;
    private int nivel;
    protected ArrayList<Arma> armas= new ArrayList<>();

    public JugadorNormal(String nombre, String skin) {
        super(nombre, 100, 1);
        this.skin=skin;
        this.nivel=1;
    }
    
    
    public String getSkin() {
        return skin;
    }

    public void setSkin(String skin) {
        this.skin = skin;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    
     
    //Metodo con polimorfismo de la clase padre.
    //Permite los movimientos del usuario en el Juego.   
    @Override
    public void moverse() {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingrese direccion: ");
        String D = entrada.next();
        
        final int incremento=super.getVelocidad();
        
        int posX=super.getX();
        int posY=super.getY();
        int op;
        
        switch(D){
            case "D":               
                op=posX+incremento;
                super.setX(op);              
            break;
            case "A":
                op=posX-incremento;
                super.setX(op);
            break;
            
             case "W":
                op=posY-incremento;
                super.setY(op);
            break;
            case "S":
                op=posY+incremento;
                super.setY(op);
            break;
            case "M":
                
               System.out.println("Ha ingresado el manú de armas\n1. Obtener Armas\n2. Cambiar Arma\n3. Seguir jugando");
               int pre=entrada.nextInt();
               while(pre!=3){
                   switch (pre) {
                       case 1: 
                           System.out.println("Armas\n1. Nombre: Machete  Daño: 3\n2. Nombre: Escopeta Daño: 5\n3. Nombre: Metralla Daño: 7");
                           int e=entrada.nextInt();
                           ObtenerArma(e);
                           break;
                       case 2: CambiarArma();
                           break;
                       default:
                           System.out.println("Número ingresado incorrecto.");
                           break;
                   }
                   
                    System.out.println("Ha ingresado el manú de armas\n1. Obtener Armas\n2. Cambiar Arma\n3. Seguir jugando");
                   pre=entrada.nextInt();
               }
            break;
        }
        if(Tablero.tablero[super.getY()][super.getX()]==null){
            Tablero.tablero[posY][posX]=null;
            Tablero.tablero[super.getY()][super.getX()]=this;
        }else{
            String nombre= Tablero.tablero[super.getY()][super.getX()].getNombre();
            
            if(nombre.equals("Vida")){
                super.setVida(super.getVida()+95);
                Tablero.tablero[posY][posX]=null;
                Tablero.tablero[super.getY()][super.getX()]=this;
            }else{
                super.setX(posX);
                super.setY(posY);
            }
            
            
        }
    } 
    
     
    //Permite obtener un arma y agregarla dependiendo el nivel
    public void ObtenerArma(int tipo){

        switch (tipo) {
            case 1:
                agregarArma(new ArmaCorto("Machete",3));
                System.out.println("Arma nueva: "+armas.get(armas.size()-1).getNombre());
                break;
            case 2:     
                if(this.nivel>2){
                    agregarArma(new ArmaLargo("Escopeta", 5));   
                    System.out.println("Arma nueva: "+armas.get(armas.size()-1).getNombre());
                }else{
                    System.out.println("Debes tener un nivel mayor a 3");
                }                         
                break;
                case 3:     
                if(this.nivel>5){
                    agregarArma(new ArmaLargo("Metralladora", 7));   
                    System.out.println("Arma nueva: "+armas.get(armas.size()-1).getNombre());
                }else{
                    System.out.println("Debes tener un nivel mayor a 5");
                }                         
                break;
            default:
                System.out.println("Numero erroneo");
                break;
            }  
        
        
    }
    
     //Permite el cambio de estado de las armas. 
    public void CambiarArma(){
        int n = armas.size();
        int i =armaActiva();
        
        if(n>1){
            if(i==n-1){
                armas.get(0).setEstado(true);
                armas.get(n-1).setEstado(false);
                System.out.println("Arma cambiada a: "+armas.get(0).getNombre());
            }else{
                armas.get(i+1).setEstado(true);
                armas.get(i).setEstado(false);
                System.out.println("Arma cambiada a: "+armas.get(i+1).getNombre());
            }
        }else{
            System.out.println("Solo cuentas con un arma");
        }
        
        
    }
    
     //Verifica que arma esta activa y devuelve su ubicación
     public int armaActiva(){
        int ubi=0;
        for (int i=0;i<armas.size();i++) {
            if(armas.get(i).getEstado()){
                ubi=i;
            }
        }
        return ubi;
        
    }
    //Agrega un arma al array de armas.
    public void agregarArma(Arma a){ 
        if(armas.size()>0){
            for (Arma arma : armas) {
                arma.setEstado(false);
            }
        }    
        armas.add(a);   
    }

    //Metodo toString con Polimorfismo.
    @Override
    public String toString() {       
        return "Nombre: "+super.getNombre()+" Nivel: "+this.nivel+" Vida: "+super.getVida()+" Velocidad: "+super.getVelocidad()+" posX: "+super.getX()+" posY: "+super.getY()+" Skin: "+this.skin;
    }
    
}

    