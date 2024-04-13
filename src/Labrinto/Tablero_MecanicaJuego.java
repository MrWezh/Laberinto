package Labrinto;

public class Tablero_MecanicaJuego extends Tablero{

    private Jugador jugador;
    Enemigos soldado;//soldado vida:8 ataque:3 escudo: 4
    Enemigos asesino; //assesino vida: 6 ataque: 8
    private int numeroRecompensa;
    private int numeroEnemigos;
    private int numeroSalida;
    private boolean[][] visionPJ;

    
    
    public Tablero_MecanicaJuego() {
        jugador = new Jugador();
        soldado = new Enemigos();
        asesino = new Enemigos();
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Enemigos getSoldado() {
        return soldado;
    }

    public void setSoldado(Enemigos soldado1) {
        this.soldado = soldado1;
    }

    public Enemigos getAsesino() {
        return asesino;
    }

    public void setAsesino(Enemigos soldado2) {
        this.asesino = soldado2;
    }

    public int getNumeroRecompensa() {
        return numeroRecompensa;
    }

    public void setNumeroRecompensa(int numeroRecompensa) {
        this.numeroRecompensa = numeroRecompensa;
    }

    public int getNumeroEnemigos() {
        return numeroEnemigos;
    }

    public void setNumeroEnemigos(int numeroEnemigos) {
        this.numeroEnemigos = numeroEnemigos;
    }

    public int getNumeroSalida() {
        return numeroSalida;
    }

    public void setNumeroSalida(int numeroSalida) {
        this.numeroSalida = numeroSalida;
    }

    public void generalOtros(){
        this.visionPJ = new boolean[getSize()][getSize()];

        this.generarRecompensa();
        this.generarEnemigos();
        this.generarSalida();
        
    }

    private void generarSalida() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generarSalida'");
    }

    private void generarEnemigos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generarEnemigos'");
    }

    private void generarRecompensa() {
        
    }
    public void print() {  

        /* for (int i = 0; i < laberinto.length; i++) {
             for (int j = 0; j < laberinto.length; j++) {
                 System.out.print(this.laberinto[i][j]+" ");
             }System.out.println();
         }*/
 
         for (int i = 0; i < getLaberinto().length; i++) {
             for (int j = 0; j < getLaberinto().length; j++) {
 
                 Square symbol = this.getLaberinto()[i][j];
 
                 switch (symbol) {
                     case PARED:
                         System.out.print(" ■ ");
                         break;
 
                     case CAMINO:
                         System.out.print(" · ");
                         break;
                     case PERSONAJE:
                        System.out.print(" 人 ");
                        break;
                     case SALIDA:
                        System.out.print(" 门 ");
                        break;
                     case RECOMPENSA:
                         System.out.print(" ? ");
                        break;
                     case SOLDADO:
                         System.out.print(" 兵 ");
                        break;
                     case ASESINO:
                         System.out.print(" 杀 ");
                        break;
                 }
             }
             System.out.println();
         }

        
}

}