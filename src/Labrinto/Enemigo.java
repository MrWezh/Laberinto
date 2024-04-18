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

    public void setEstadisticaEnemigo(int[] estadisticaEnemigo) {
        this.estadisticaEnemigo = estadisticaEnemigo;
        this.vida = estadisticaEnemigo[0];
        this.ataque = estadisticaEnemigo[1];
        this.escudo = estadisticaEnemigo[2];

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

    public void esAtacado(Jugador e, boolean usarEscudo, boolean esFlecha){

        if (usarEscudo&&!esFlecha){
            
            if (escudo > 0){
            int dañoRecibido = this.escudo -1;

            System.out.println("Su escudo recibio "+1+" de daño!");
            this.setEscudo(dañoRecibido);
            
             if (getEscudo() == 0){System.out.println("Su escudo le bloqueo una ultima ataque...");}
            
        }

        else if (getEscudo() == 0){

            int damageRecibido = e.getAtaque()/2; 
            if (damageRecibido == 0){damageRecibido = 1;}
            System.out.println("Le infringistes "+damageRecibido+" de daño! Aunque lo intentó esquivar...");
            setVida(this.vida - damageRecibido);
        }

        }
        else{
            System.out.println("Le infringistes "+e.getAtaque()+" de daño!");
            this.setVida(this.vida - e.getAtaque());
        }

    }

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo + "]";
    }

}
