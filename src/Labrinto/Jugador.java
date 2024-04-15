package Labrinto;

import java.util.Random;

public class Jugador {
    
    private String Nombre;
    private int vida;
    private int ataque;
    private int escudo;

    public int getEscudo() {
        return escudo;
    }
    public void setEscudo(int escudo) {
        if (escudo < 0) this.escudo = 0;
        else this.escudo = escudo;
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
        if (vida < 0) this.vida = 0;
        else this.vida = vida;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public boolean atacar(Enemigos e) throws InterruptedException {
        Thread.sleep(500);
         System.out.println("Infringistes "+this.ataque+" de daño!");
        
        return e.esAtacado(this);
        
    }

    public boolean esAtacado(Enemigos e) throws InterruptedException {
        Thread.sleep(500);
        //System.out.println("El Protagonista es atacado por el Enemigo!");
        
        if (this.escudo > 0) {
            int ataca = getEscudo() - e.getAtaque();
            setEscudo(ataca);
            if (ataca < 0) setVida(getVida() - e.getAtaque() + ataca);
    
        }
        if(this.escudo == 0) setVida(getVida() - e.getAtaque());

        if (getVida() == 0) return true;
        else {
            return atacar(e);
        }
    }

    public void sumarEstadistica()throws InterruptedException{
    
        
        Random random = new Random();

        int opcion = random.nextInt(3)+1; 

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

              
                
        } Thread.sleep(1000);
    }
    @Override
    public String toString() {
        return "Jugador [Nombre=" + Nombre + ", vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo
                + "]";
    }

    
    
  

    
    
}
