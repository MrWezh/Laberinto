package Labrinto;

public class Enemigos {

    private int vida; 
    private int ataque;
    private int escudo;
    

    public int getVida() {
        return vida;
    }
    public void setVida(int vida) {
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
        this.escudo = escudo;
    }
    @Override
    public String toString() {
        return "Enemigos [vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo + "]";
    }

    
    
}
