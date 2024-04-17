package Labrinto;

import java.util.Random;

public class Enemigos {

    private String nombre;
    private int vida;
    private int ataque;
    private int escudo;
    private Random random;

    private int[] estadisticaEnemigo;

    Enemigos() {
        this.estadisticaEnemigo = new int[3];
        this.random = new Random();

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

    public Enemigos(String nombre) {
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

    /*public boolean atacar(Jugador e) throws InterruptedException {
        Thread.sleep(500);
        
        System.out.println("---------------------------------------");
        System.out.println("El enemigo te infringió " + this.ataque + " de daño!");

        return e.esAtacado(this);
    }

    public boolean esAtacado(Jugador e) throws InterruptedException {
        Thread.sleep(500);
        // System.out.println("El Enemigo es atacado por el protagonista!");
        if (this.escudo > 0) {
            int ataca = getEscudo() - e.getAtaque();

            if (ataca < 0) {
                setVida(getVida() + ataca);
            }
            setEscudo(ataca);
        } else if (this.escudo == 0)
            setVida(getVida() - e.getAtaque());

        if (getVida() == 0)
            return true;
        else {
            System.out.println("Vida enemiga: " + this.vida);
            System.out.println("Escudo enemigo: " + this.escudo);
            return atacar(e);
        }

    }*/

    public int atacar(Jugador j){
        return (this.random.nextInt(3))+1;
    }

    public void esAtacado(Jugador j){

      

    }

    @Override
    public String toString() {
        return nombre + " [vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo + "]";
    }

}
