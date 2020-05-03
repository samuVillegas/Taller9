public class Tablero {
    
    public static Humano tablero[][]= new Humano[20][10];
    
    
    //Muestra la tabla de juego con sus participantes.
    // X vacio , Dragon: enemigo ,NombreUsuario y Vidas.
    public void MostrarTabla(){
        for (int x=0; x < tablero.length; x++) {
            System.out.print("|");
            
            for (int y=0; y < tablero[x].length; y++) {
                
                if(tablero[x][y]!=null){
                    System.out.print (tablero[x][y].getNombre());
                }else{
                    System.out.print ("X");
                }
                
            
                if (y!=tablero[x].length-1) 
                    System.out.print("\t");
            }
        
            System.out.println("|");
        }
    }
    
    
}
