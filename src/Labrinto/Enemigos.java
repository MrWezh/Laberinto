package Labrinto;

public class Enemigos {

    private String nombre;
    private int vida; 
    private int ataque;
    private int escudo;
    

    public Enemigos(String nombre) {
        this.nombre = nombre;
    }
    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
        if (vida < 0 ) this.vida=0;
        else this.vida = vida;
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
        this.escudo = escudo;
    }

    public boolean atacar(Jugador e) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("El Enemigo ataca al protagonista!");
        return e.esAtacado(this);
    }

    public boolean esAtacado(Jugador e) throws InterruptedException {
        Thread.sleep(500);
        System.out.println("El Enemigo es atacador por el protagonista!");
        setVida(getVida() - e.getAtaque());

        if (getVida() <= 0) return true;
        else {
            return atacar(e);
        }

    }

    @Override
    public String toString() {
        return "Enemigos [vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo + "]";
    }

    
    
}
