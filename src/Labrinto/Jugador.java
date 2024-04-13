package Labrinto;
public class Jugador {
    
    private String Nombre;
    private int vida;
    private int ataque;
    private int escudo;

    private int sumaPuntos = this.vida*this.ataque*this.escudo;

    

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
        this.vida = vida;
    }
    public int getAtaque() {
        return ataque;
    }
    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getSumaPuntos() {
        return sumaPuntos;
    }
    public void setSumaPuntos(int sumaPuntos) {
        this.sumaPuntos = sumaPuntos;
    }
    @Override
    public String toString() {
        return "Jugador [Nombre=" + Nombre + ", vida=" + vida + ", ataque=" + ataque + ", escudo=" + escudo
                + ", sumaPuntos=" + sumaPuntos + "]";
    }

    
    
  

    
    
}
