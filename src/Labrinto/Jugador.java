package Labrinto;

import java.util.Random;
import java.util.Scanner;

public class Jugador {

    private String Nombre;
    private int vida;
    private int ataque;
    private int escudo;
    private Random random; 
    private Scanner wz; 


    public Jugador() {
        this.random  = new Random();
        this.wz = new Scanner(System.in);
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

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
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

    public String atacar(){
        System.out.println("1.Atacar | 2.Defender | 3.Disparar");
        String opcion = wz.next(); 
        
        return opcion;
        
    }

    public void esAtacado(Enemigo e, boolean usarEscudo, boolean esFlecha){

        if (usarEscudo&&!esFlecha){
            
            if (escudo > 0){
            int dañoRecibido = this.escudo - 1;

            System.out.println("Tu escudo recibió "+1+" de daño!");
            this.setEscudo(dañoRecibido);
            
             if (getEscudo() == 0){System.out.println("Tu escudo te bloqueo una ultima ataque...");}
            
        }

        else if (getEscudo() == 0){
            int damageRecibido = e.getAtaque()/2;
            if(damageRecibido==0) damageRecibido = 1;
            System.out.println("Recibistes "+damageRecibido+" de daño! Aunque lo intentastes esquivar...");
            setVida(this.vida - damageRecibido);
        }

        }
        else{
            System.out.println("Recibistes "+e.getAtaque()+" de daño!");
            setVida(this.vida - e.getAtaque());
        }

        

    }

    public void sumarEstadistica() throws InterruptedException {


        int opcion = this.random.nextInt(3) + 1;
        

        switch (opcion) {

            case 1:
                System.out.println("Obtuviste AGUA BENDITA +1 DE VIDA!");
                this.vida += 1;

                break;
            case 2:
                System.out.println("Obtuviste FURIA +1 DE ATAQUE!");
                this.ataque += 1;
                break;
            case 3:
                System.out.println("Obtuviste PIEL DE HIERRO +1 DE ESCUDO!");
                this.escudo += 1;
                break;
            default:
                break;

        }
        Thread.sleep(1000);
    }

    @Override
    public String toString() {
        return "Jugador [Nombre=" + Nombre + ", vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo
                + "]";
    }

}
