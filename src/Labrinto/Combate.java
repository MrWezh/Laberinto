package Labrinto;

import java.util.Random;
import java.util.Scanner;

public class Combate {

    private Enemigo enemigo;
    private Jugador jugador;
    private Scanner wz;

    public Combate(Enemigo a, Jugador b) {
        this.enemigo = a;
        this.jugador = b;
        this.wz = new Scanner(System.in);
    }

    public void combate() {
        // this.clearScreen();
        System.out.println(this.enemigo.toString());
        System.out.println(this.jugador.toString());

        String eleccionJugador = "";
        String eleccionEnemigo = "";
        Enemigo e = this.enemigo;

        eleccionJugador = this.jugador.atacar();

        Random random = new Random();

        int a = random.nextInt(3) + 1;

        eleccionEnemigo = "" + a;

        if (eleccionJugador.equals("1") || eleccionJugador.equals("2") || eleccionJugador.equals("3")) {
            this.imprimirEleccionCombate(eleccionJugador);
            this.imprimirEleccionCombate(eleccionEnemigo);
        }
        /*
         * Cuando coincidan los ataques(enemigo y jugador), ninguno de los dos recibirán
         * daño.
         */

        if (eleccionJugador.equals(eleccionEnemigo)) {
            switch (eleccionEnemigo) {
                case "1":
                    System.out.println("Se escucha un brillo sonido causado por el choque vuestras espadas...");
                    break;

                case "2":
                    System.out.println("Intercambias las miradas mientras alzais vuestros escudos...");
                    break;

                case "3":
                    System.out.println("Ambos habeis esquivado la flecha del otro...");
                    break;
                default:
                    break;
            }
        } else {
            this.situacionesCombate(eleccionJugador, eleccionEnemigo, e);
        }

    }

    public void situacionesCombate(String eleccionJugador, String eleccioEnemiga, Enemigo enemigo) {
        // 1: artacar; 2: defender; 3: disparar

        Random random = new Random();

        switch (eleccionJugador) {
            case "1":

                /*
                 * Si el enemigo/jugador ataca:
                 * si su oponente decide disparar, tendrá un 75% de probabilidad de esquivar la
                 * flecha y infrigir daño.
                 * 
                 * en el caso de que el oponente decida defender:
                 * si el oponente aún tiene escudo--> un 100% de bloquear el ataque(el oponente).
                 * si el oponente no tiene más escudo --> 75% de esquivar el ataque(el oponente).
                 */

                if (eleccioEnemiga.equals("3")) {

                    int probabilidad = random.nextInt(100) + 1;

                    if (probabilidad <= 75) {
                        System.out.println("Esquivaste su flecha!");
                        enemigo.esAtacado(this.jugador, false);
                    } else {
                        System.out.println("No pudistes esquivar su flecha...");
                        this.jugador.esAtacado(enemigo, false);
                    }
                }

                else if (eleccioEnemiga.equals("2")) {
                    if (this.enemigo.getEscudo() != 0) {

                        System.out.println("Ha bloqueado tu ataque...");
                        this.enemigo.esAtacado(this.jugador, true);
                    } else {
                        int probabilidad = random.nextInt(100) + 1;

                        if (probabilidad <= 75) {
                            System.out.println("El enemigo esquivó tu ataque...");
                        } else {
                            System.out.println("Acertaste el ataque!");
                            this.enemigo.esAtacado(jugador, false);
                        }

                    }
                }
                break;

            /*
             * Si el enemigo/jugador ataca y su oponente defiende, el oponente tendrá
             * una probabilidad de 100% en bloquear el ataque del enemigo/jugador.
             * Si el enemigo/jugador se queda sin escudo, la proxima vez que utilize el
             * escudo, se
             * considerará como "esquivar". en dicha ocasión, el enemigo/jugador tendrá un
             * 25% de
             * probabilidad de acertar su ataque.
             * 
             * Pero si el enemigo/jugador decide defender, y su oponente dispara una flecha:
             * si le queda escudo, el oponente dentrá un 25% de acertar, si no, tendrá un 50%
             * de acertar.
             */

            case "2":
                if (eleccioEnemiga.equals("1")) {
                    if (this.jugador.getEscudo() != 0) {
                        System.out.println("Bloqueaste su ataque de espada!");
                        this.jugador.esAtacado(enemigo, true);
                    }

                    else {
                        int probabilidad = random.nextInt(100) + 1;

                        if (probabilidad <= 25) {
                            System.out.println("No pudistes esquivar su ataque...");
                            this.jugador.esAtacado(this.enemigo, true);
                        } else {
                            System.out.println("Esquivastes su ataque!");
                        }
                    }
                } else if (eleccioEnemiga.equals("3")) {
                    int probabilidad = random.nextInt(100) + 1;

                    if (this.jugador.getEscudo() != 0) {
                        if (probabilidad <= 25) {
                            System.out.println("No pudistes bloquear sus flechas...");
                            this.jugador.esAtacado(this.enemigo, false);
                        } else {
                            System.out.println("Bloqueastes sus flechas!");
                            this.jugador.esAtacado(this.enemigo, true);
                        }

                    } else {
                        if (probabilidad <= 50) {
                            System.out.println("No pudistes esquivar sus flechas...");
                            this.jugador.esAtacado(this.enemigo, true);
                        } else {
                            System.out.println("Esquivastes sus flechas!");
                        }
                    }
                }
                break;

            case "3":
                if (eleccioEnemiga.equals("1")) {

                    int probabilidad = random.nextInt(100) + 1;

                    if (probabilidad <= 75) {
                        System.out.println("No pudo esquivar tus flechas!");
                        enemigo.esAtacado(this.jugador, false);
                    } else {
                        System.out.println("Esquivó tus flechas...");
                        this.jugador.esAtacado(enemigo, false);
                    }
                }

                else if (eleccioEnemiga.equals("2")) {

                    int probabilidad = random.nextInt(100) + 1;

                    if (this.enemigo.getEscudo() != 0) {
                        if (probabilidad <= 50) {
                            System.out.println("No pudo bloquear tus flechas!");
                            enemigo.esAtacado(this.jugador, false);
                        } else {
                            System.out.println("Bloqueó tus flechas...");
                            enemigo.esAtacado(this.jugador, true);
                        }
                    }

                    else {
                        if (probabilidad <= 75) {
                            System.out.println("No pudo esquivar tus flechas!");
                            enemigo.esAtacado(this.jugador, false);
                        } else {
                            System.out.println("Esquivó tus flechas...");
                            enemigo.esAtacado(this.jugador, true);
                        }
                    }

                }
                break;
            default:
                break;
        }

    }

    public void imprimirEleccionCombate(String opcion) {

        switch (opcion) {
            case "1":
                System.out.println();
                System.out.println("══|>>>>>>>");
                System.out.println();

                break;

            case "2":

                System.out.println("  ___ ");
                System.out.println(" | _ |");
                System.out.println(" | @ |");
                System.out.println(" \\_-_/");
                System.out.println();

                break;

            case "3":
                System.out.println();
                System.out.println(">>----->");
                System.out.println();
            default:
                break;
        }
    }

}
