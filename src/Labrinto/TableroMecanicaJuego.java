package Labrinto;

import java.util.Random;
import java.util.Scanner;

public class TableroMecanicaJuego extends Tablero {

    private Jugador jugador;
    Enemigo soldado;
    Enemigo asesino;
    private int numeroRecompensa;
    private int numeroEnemigos;
    private int numeroSalida;
    private boolean[][] visionPJ;
    private Scanner wz;
    private int escudoInicialPJ;
    private int[] coordenadaPJ = { 0, 0 };

    private int[] estacEnemigoAsesino;
    private int[] estacEnemigoSoldado;

    public TableroMecanicaJuego() {
        this.jugador = new Jugador();
        this.soldado = new Enemigo("Soldado esqueleto");
        this.asesino = new Enemigo("Asesino");

        this.estacEnemigoAsesino = new int[3];
        this.estacEnemigoSoldado = new int[3];
        wz = new Scanner(System.in);
    }

    public int[] getEstacEnemigoSoldado() {
        return estacEnemigoSoldado;
    }

    public void setEstacEnemigoSoldado(int[] estacEnemigoSoldado) {
        this.estacEnemigoSoldado = estacEnemigoSoldado;
    }

    public int[] getEstacEnemigoAsesino() {
        return estacEnemigoAsesino;
    }

    public void setEstacEnemigoAsesino(int[] estacEnemigo) {
        this.estacEnemigoAsesino = estacEnemigo;
    }

    public int getEscudoInicialPJ() {
        return escudoInicialPJ;
    }

    public void setEscudoInicialPJ(int escudoInicialPJ) {
        this.escudoInicialPJ = escudoInicialPJ;
    }

    public boolean[][] getVisionPJ() {
        return visionPJ;
    }

    public void setVisionPJ(boolean[][] visionPJ) {
        this.visionPJ = visionPJ;
    }

    public int[] getCoordenadaPJ() {
        return coordenadaPJ;
    }

    public void setCoordenadaPJ(int[] coordenadaPJ) {
        this.coordenadaPJ = coordenadaPJ;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Enemigo getSoldado() {
        return soldado;
    }

    public void setSoldado(Enemigo soldado1) {
        this.soldado = soldado1;
    }

    public Enemigo getAsesino() {
        return asesino;
    }

    public void setAsesino(Enemigo soldado2) {
        this.asesino = soldado2;
    }

    public int getNumeroRecompensa() {
        return numeroRecompensa;
    }

    public void setNumeroRecompensa(int numeroRecompensa) {
        this.numeroRecompensa = numeroRecompensa;
    }

    public int getNumeroEnemigos() {
        return numeroEnemigos;
    }

    public void setNumeroEnemigos(int numeroEnemigos) {
        this.numeroEnemigos = numeroEnemigos;
    }

    public int getNumeroSalida() {
        return numeroSalida;
    }

    public void setNumeroSalida(int numeroSalida) {
        this.numeroSalida = numeroSalida;
    }

    public void generalEnemigosYRecompensas() {
        this.visionPJ = new boolean[getSize()][getSize()];

        if (numeroSalida == 1)
            this.generar(getSize() / 2 + 3, 1, Square.SALIDA, numeroSalida);
        if (numeroSalida == 2) {
            this.generar(getSize() / 2 + 3, 3, Square.SALIDA, numeroSalida / 2);
            this.generar(getSize() / 2 + 3, 1, Square.SALIDA, numeroSalida / 2);
        }
        if (numeroSalida == 3) {
            this.generar(getSize() / 2 + 3, 1, Square.SALIDA, 1);
            this.generar(getSize() / 2 + 3, 2, Square.SALIDA, 1);
            this.generar(getSize() / 2 + 3, 3, Square.SALIDA, 1);
        }
        this.generar(0, 1, Square.RECOMPENSA, numeroRecompensa);

        Square[] enemigo = { Square.ASESINO, Square.SOLDADO };

        this.generar(3, 1, enemigo[0], numeroEnemigos / 2);
        this.generar(3, 1, enemigo[1], numeroEnemigos / 2);

    }

    private void generar(int rango, int seleccionarZona, Square symbol, int numeroVeces) {
        Random random = new Random();

        for (int i = 0; i < numeroVeces; i++) {
            int cualCamino = random.nextInt(getPosicionesCamino().size());

            int[] caminoRandom = getPosicionesCamino().get(cualCamino);

            int filas = caminoRandom[0];
            int columnas = caminoRandom[1];
            boolean randomCamino = false;

            /*
             * si dividimos el tablero en 4 zonas, cuando selecionarzona es 1 está
             * seleccionando la zona de abajo derecha; si es 2 abajo izquierda; 3 arriba
             * derecha
             *
             * arriba izquierda| arriba derecha
             * -------------------------------
             * abajo izqueirda| abajo derecha
             */
            if (seleccionarZona == 1)
                randomCamino = filas >= rango && columnas >= rango;
            if (seleccionarZona == 2)
                randomCamino = filas >= rango && columnas < rango;
            if (seleccionarZona == 3)
                randomCamino = filas < rango && columnas >= rango;

            if (getLaberinto()[filas][columnas] == Square.CAMINO && randomCamino)
                getLaberinto()[filas][columnas] = symbol;
            else
                i--;
        }

    }

    public int[] movimientoPJ() throws InterruptedException {

        System.out.print("Movimiento(w/a/s/d) :");
        String movimiento = wz.next().toLowerCase();

        int[] mover = { 0, 0 };

        switch (movimiento) {
            case "w":
                mover[0] = -1;
                break;

            case "a":
                mover[1] = -1;

                break;

            case "s":
                mover[0] = 1;

                break;
            case "d":
                mover[1] = 1;

                break;
            default:
                break;
        }
        int filas = coordenadaPJ[0] + mover[0];
        int columnas = coordenadaPJ[1] + mover[1];
        int[] coordenadasMover = { filas, columnas };

        if (filas < 0 || columnas < 0 ||
                filas >= this.getSize() ||
                columnas >= this.getSize() ||
                this.getLaberinto()[filas][columnas] == Square.PARED) {

            System.out.println("---------MOVIMIENTO INCORRECTO---------");
            Thread.sleep(500);
            this.clearScreen();
            this.print();
            return movimientoPJ();
        } else
            return coordenadasMover;
    }

    /*
     * Metodo para cuando el PJ tenga interacciones en la mapa(tocar una pared, un
     * enemigo o una recompensa).
     */
    public boolean interaccionPJ(int[] coordenadaMover) throws InterruptedException {

        if (this.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] == Square.SOLDADO) {

            int[] estacEnemigo = this.estacEnemigoSoldado;

            while (true) {
            clearScreen();
            System.out.println(this.soldado.toString());
                this.combate("soldado");

                if (this.jugador.getVida() == 0){
                    System.out.println("Te han matado...");
                    Thread.sleep(1000);
                    break;}
                else if (this.soldado.getVida() == 0){
                    System.out.println("Has matado a tu enemigo!");
                    Thread.sleep(1000);
                    break;
                }
                System.out.print("Teclea cualquer letra para seguir: ");String a = wz.nextLine();
            }
            this.soldado.setEstadisticaEnemigo(estacEnemigo);

            
            System.out.print("Teclea cualquier letra para continuar: ");
            String a = wz.nextLine();

            // Thread.sleep(2000);

        } else if (this.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] == Square.ASESINO) {
            int[] estacEnemigo = this.estacEnemigoAsesino;
           
            while (true) {
                clearScreen();
            System.out.println(this.asesino.toString());
                this.combate("asesino");

                if (this.jugador.getVida() == 0){
                    System.out.println("Te han matado...");
                    Thread.sleep(1000);
                    break;}
                else if (this.asesino.getVida() == 0){
                    System.out.println("Has matado a tu enemigo!");
                    Thread.sleep(1000);
                    break;
                }
                this.asesino.setEstadisticaEnemigo(estacEnemigo);
                
                System.out.print("Teclea cualquer letra para seguir: ");String a = wz.nextLine();
            }

            /*
             * como solo he creado un solo objeto para cada tipo de enemigos, para poder
             * reutilizar los enemigos,he guardados las estadísticas
             * de estos. Cada vez que termine un combate, indroducimos las estadísticas
             * guardadas a los enemigos que han entrado en combate.
             */
            this.asesino.setEstadisticaEnemigo(estacEnemigo);

            wz.nextLine();
            System.out.print("Teclea cualquier letra para continuar: ");
            String a = wz.next();
            // Thread.sleep(2000);

        }

        else if (this.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] == Square.RECOMPENSA) {
            this.clearScreen();
            System.out.print("Recibiendo recompensa.");
            for (int i = 0; i < 6; i++) {

                Thread.sleep(300);
                System.out.print(".");
            }
            this.clearScreen();
            this.getJugador().sumarEstadistica();
            this.escudoInicialPJ = this.jugador.getEscudo();

        }

        if (this.getJugador().getVida() <= 0) {
            return true;
        } else
            return false;
    }

    /*
     * Metodo más optimo de examinar las casillas vecinas.
     */

    public void descubrirCasillas(int i, int j) {

        int[][] opciones = {
                { -1, 0 },
                { 0, -1 },
                { -1, -1 },
                { 1, 0 },
                { 0, 1 },
                { 1, 1 },
                { -1, 1 },
                { 1, -1 }
        };

        for (int k = 0; k < opciones.length; k++) {
            int filaVecina = i + opciones[k][0];
            int columnaVecina = j + opciones[k][1];

            if (filaVecina >= 0 && columnaVecina >= 0 && filaVecina < this.visionPJ.length
                    && columnaVecina < this.visionPJ[0].length) {
                this.visionPJ[filaVecina][columnaVecina] = true;
            }
        }

    }

    /*
     * Metodo movimiento. Recibe las coordenadas acutuales para poder quitar el PJ
     * de la casilla y posteriormente ponerlo en la casilla que el jugador eligió.
     */

    public boolean moverse(int[] casillaActual, int[] coordenadaMover) throws InterruptedException {

        this.getLaberinto()[casillaActual[0]][casillaActual[1]] = Square.CAMINO;
        this.setCoordenadaPJ(coordenadaMover);
        if (this.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] == Square.SALIDA) {
            return true;

        } else {

            this.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] = Square.PERSONAJE;
            this.descubrirCasillas(coordenadaMover[0], coordenadaMover[1]);
            if (this.jugador.getEscudo() < this.escudoInicialPJ)
                this.jugador.setEscudo(this.jugador.getEscudo() + 1);
            return false;
        }
    }

    public void print() {

        System.out.println(this.jugador.toString());
        System.out.print("╔");
        for (int i = 0; i < getSize() * 3; i++) {
            System.out.print("═");
        }
        System.out.println("╗");

        for (int i = 0; i < getLaberinto().length; i++) {
            System.out.print("║");
            for (int j = 0; j < getLaberinto().length; j++) {

                if (!this.visionPJ[i][j]) {
                    System.out.print(" # ");
                }

                else {
                    Square symbol = this.getLaberinto()[i][j];

                    switch (symbol) {
                        case PARED:
                            System.out.print(" ■ ");
                            break;

                        case CAMINO:
                            System.out.print("   ");
                            break;
                        case PERSONAJE:
                            System.out.print(" 人");
                            break;
                        case SALIDA:
                            System.out.print(" 门");
                            break;
                        case RECOMPENSA:
                            System.out.print(" ? ");
                            break;
                        case SOLDADO:
                            System.out.print(" 兵");
                            break;
                        case ASESINO:
                            System.out.print(" 杀");
                            break;
                    }
                }
            }
            System.out.println("║");
        }
        System.out.print("╚");
        for (int i = 0; i < getSize() * 3; i++) {
            System.out.print("═");
        }
        System.out.println("╝");

        System.out.println(this.asesino.toString());
        System.out.println(this.soldado.toString());
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void combate(String opcion) {
        // this.clearScreen();
        System.out.println(this.jugador.toString());

        String eleccionJugador = "";
        String eleccionEnemigo = "";
        Enemigo e = this.asesino;

        switch (opcion) {
            case "asesino":
                eleccionJugador = this.jugador.atacar();
                eleccionEnemigo = this.asesino.atacar();

                break;

            case "soldado":
                eleccionJugador = this.jugador.atacar();
                eleccionEnemigo = this.soldado.atacar();
                e = this.soldado;

                break;

            default:
                break;
        }

        this.imprimirEleccionCombate(eleccionJugador);
        this.imprimirEleccionCombate(eleccionEnemigo);

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

    public void situacionesCombate(String eleccionJugador, String eleccioEnemiga, Enemigo tipo) {
        // 1: artacar; 2: defender; 3: disparar

        switch (eleccionJugador) {
            case "1":
                if (eleccioEnemiga.equals("3")) {
                    System.out.println("Esquivaste su flecha!");
                    tipo.esAtacado(this.jugador, false, true);
                } else if (eleccioEnemiga.equals("2")) {
                    System.out.println("Ha bloqueado tu ataque...");
                    tipo.esAtacado(this.jugador, true, false);
                }
                break;

            case "2":
                if (eleccioEnemiga.equals("1")) {
                    if (this.jugador.getEscudo() != 0)System.out.println("Bloqueaste su ataque de espada!");
                    this.jugador.esAtacado(tipo, true, false);
                } else if (eleccioEnemiga.equals("3")) {
                    System.out.println("No pudistes bloquear sus flechas...");
                    this.jugador.esAtacado(tipo, false, true);
                }
                break;
            case "3":
                if (eleccioEnemiga.equals("1")) {
                    System.out.println("Esquivó tus flechas...");
                    this.jugador.esAtacado(tipo, false, true);
                } else if (eleccioEnemiga.equals("2")) {
                    System.out.println("NO pudo bloquear tus flechas!");
                    tipo.esAtacado(this.jugador, true, true);

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