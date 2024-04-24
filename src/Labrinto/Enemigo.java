package Labrinto;

import java.util.Random;

public class Enemigo {

    private String nombre;
    private int vida;
    private int ataque;
    private int escudo;

    private int[] estadisticaEnemigo;

    public Enemigo() {
        this.estadisticaEnemigo = new int[3];

    }

    public int[] getEstadisticaEnemigo() {
        return estadisticaEnemigo;
    }

    public void generalEstacsEnemigo(int vida, int ataque, int escudo) {
        Random random = new Random();
        for (int i = 0; i < 1; i++){
            this.vida = random.nextInt(vida);
            if (this.vida < 5) i--;
        }

        for (int i = 0; i < 1; i++){
        this.ataque = random.nextInt(ataque);
            if (this.ataque < 2) i--;
        } 
        
        for (int i = 0; i < 1; i++){
        this.escudo = random.nextInt( escudo);
            if (this.escudo < 2) i--;
        }



    }

    public Enemigo(String nombre) {
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        if (vida < 0)
            this.vida = 0;
        else
            this.vida = vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getEscudo() {
        return escudo;
    }

    public void setEscudo(int escudo) {
        if (escudo < 0)
            this.escudo = 0;
        else
            this.escudo = escudo;
    }

    public int[] guardarEstacsEnemigo(){

        int[] estacsEnemigo = new int[3];

        estacsEnemigo[0] = this.vida; 
        estacsEnemigo[1] = this.ataque;
        estacsEnemigo[2] = this.escudo;

        return estacsEnemigo;
    }

    

    public String atacar(){

        Random random = new Random();
        
        int elecion = random.nextInt(3) +1;
        
        String eleccionEnemiga = ""+elecion; 
        
        return eleccionEnemiga;
    }
    /*
     * si usa escudo: le quita un punto al escudo independientemente de daño
     * recibido.
     * si no usa escudo: recibe todo el daño recibido.
     * si no le queda escudo, tendrá que esquivar(50 50 de recibir o no daño).
     */

    public void esAtacado(Jugador e, boolean usaEscudo){
            
        if (usaEscudo){
            if (escudo > 0){
            int dañoRecibido = this.escudo -1;

            System.out.println("Su escudo recibio "+1+" de daño!");
            this.setEscudo(dañoRecibido);
            
             if (getEscudo() == 0){System.out.println("Su escudo le bloqueo una ultima ataque...");}
            
        }

        else{

            System.out.println("Le infringistes "+e.getAtaque()+" de daño!");
            setVida(this.vida - e.getAtaque());
        }
    }else{

        System.out.println("Le infringistes "+e.getAtaque()+" de daño!");
            setVida(this.vida - e.getAtaque());

}
    }

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo + "]";
    }

}
