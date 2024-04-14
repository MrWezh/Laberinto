package Labrinto;
public class Jugador {
    
    private String Nombre;
    private int vida;
    private int ataque;
    private int escudo;

    public int getEscudo() {
        return escudo;
    }
    public void setEscudo(int escudo) {
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
        System.out.println("El Protagonista ataca al Enemigo!");
        return e.esAtacado(this);
    }

    public boolean esAtacado(Enemigos e) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("El Protagonista es atacado por el Enemigo!");
        setVida(getVida() - e.getAtaque());

        if (getVida() <= 0) return true;
        else {
            return atacar(e);
        }
    }
    @Override
    public String toString() {
        return "Jugador [Nombre=" + Nombre + ", vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo
                + "]";
    }

    
    
  

    
    
}
