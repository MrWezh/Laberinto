package Labrinto;
import java.util.Scanner;

public class NormasDelJuego {

    private Scanner wz; 

    public String menuNormas(){
        this.wz = new Scanner(System.in);
        System.out.println();
        System.out.println("╔═════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║CONTROLES:                                                               ║");
        System.out.println("║-------------------------------------------------------------------------║");
        System.out.println("║w --> moverse hacia arriba.                                              ║");
        System.out.println("║a --> moverse hacia izquierda.                                           ║");
        System.out.println("║s --> moverse hacia abajo.                                               ║");
        System.out.println("║d --> moverse hacia derecha.                                             ║");
        System.out.println("║                                                                         ║");
        System.out.println("║OBJETIVO DEL JUEGO:                                                      ║");
        System.out.println("║-------------------------------------------------------------------------║");
        System.out.println("║El juego, en cada partida, generará un laberinto aleatorio.              ║");
        System.out.println("║En el cual, el jugador tendrá que buscar la salida y sobrevivir          ║");
        System.out.println("║a los peligros que se encuentre en el laberinto.                         ║");
        System.out.println("║Además de enemigos peligrosos, los jugadores tambien podrán encontrar    ║");
        System.out.println("║recompensas para aumentar sus estadísticas.                              ║");
        System.out.println("║                                                                         ║");
        System.out.println("║COMBATE:                                                                 ║");
        System.out.println("║-------------------------------------------------------------------------║");
        System.out.println("║El jugador tendrá 3 opciones cuando entra en combate:                    ║");
        System.out.println("║                                                                         ║");
        System.out.println("║Atacar ---> Si tu oponente decide defender:                              ║");
        System.out.println("║    si tiene escudo: 0% de probabilidad de acertar tu ataque             ║");
        System.out.println("║    si no tiene escudo: 25% de probabilidad de acertar tu                ║");
        System.out.println("║    de acertar tu ataque.                                                ║");
        System.out.println("║Si embargo, si tu oponente decide disparar una flecha, tendrá una        ║");
        System.out.println("║probabilidad de 75% de esquivar su ataque e infligirle daño.             ║");
        System.out.println("║                                                                         ║");
        System.out.println("║Defender --> Si tu oponente ataca:                                       ║");
        System.out.println("║    si tiene escudo: 100% de probabilidad de bloquear su ataque          ║");
        System.out.println("║    si no tiene escudo: 75% de probabilidad de esquivar su ataque        ║");
        System.out.println("║         --> Si tu oponente dispara:                                     ║");
        System.out.println("║    si tiene escudo: 25% de probabilidad de bloquear su flecha           ║");
        System.out.println("║    si no tiene escudo: 75% de probabilidad de esquivar su flehca        ║");
        System.out.println("║                                                                         ║");
        System.out.println("║Disparar --> Si tu oponente decide atacar, solamente tendrás un 25% de   ║");
        System.out.println("║de probabilidad de acertar, y tu oponente un 75% de acertar su ataque.   ║");
        System.out.println("║Si tu oponente decide defender:                                          ║");
        System.out.println("║    si tiene escudo: 50% de probabilidad de acertar tu flecha            ║");
        System.out.println("║    si no tiene escudo: 25% de probabilidad de acertar tu                ║");
        System.out.println("║    de acertar tu flecha.                                                ║");
        System.out.println("║Si embargo, si tu oponente decide atacar, tendrá una probabilidad de     ║");
        System.out.println("║25% de acertar tu flecha.                                                ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
        System.out.println("0. Volver al menú inicial");
        String a = wz.next(); 

        if (a.equals("0")){
            return a;
        }else return menuNormas(); 

    }
    
    
}
