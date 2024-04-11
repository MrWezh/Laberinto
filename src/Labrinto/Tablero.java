package Labrinto;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private Square[][] laberinto;
    private int coordenadaICamino = 0;
    private int coordenadaJCamino = 0;
    private String opcionAnterior = "ARRIBA";
    private ArrayList<int[]> posicionesCamino;

    private boolean[] puedeGirar;

    private int size = 10;

    public void GeneralTablero() {

        laberinto = new Square[this.size][this.size];
        this.posicionesCamino = new ArrayList<>();
        this.puedeGirar = new boolean[this.size];
        this.inical();

    }

    public void inical() {

        for (int i = 0; i < this.size; i++) {
            this.puedeGirar[i] = false;
            for (int j = 0; j < this.size; j++) {
                this.laberinto[i][j] = Square.PARED;
            }
        }

        Random randomInicial = new Random();

        coordenadaJCamino = randomInicial.nextInt(this.size);
        this.laberinto[this.coordenadaICamino][this.coordenadaJCamino] = Square.CAMINO;

        while (true) {

            if (this.coordenadaICamino == this.size - 1 ||
                    this.coordenadaJCamino == this.size - 1) {
                break;
            }

            construirCamino();
            System.out.println("-------------------------------------------");
            print();
        }

    }

    /**
     * @return
     */
    private void construirCamino() {
        Random randomDireccion = new Random();

        // 1: subir, 2: girar izquierda, 3: bajar, 4: girar derecha
        int opcion = randomDireccion.nextInt(this.size);

        switch (opcion) {
            case 1:
                if (construir(coordenadaICamino - 1, coordenadaJCamino))
                    coordenadaICamino--;
                break;
            case 2:
                if (construir(coordenadaICamino, coordenadaJCamino +1))
                    coordenadaICamino--;
                break;
            case 3:
                if (construir(coordenadaICamino + 1, coordenadaJCamino))
                    coordenadaICamino--;
                break;

            case 4:
                if (construir(coordenadaICamino, coordenadaJCamino -1))
                    coordenadaICamino--;
                break;

            default:
                break;
        }
        this.laberinto[coordenadaICamino][coordenadaJCamino] = Square.CAMINO;

    }

    private boolean construir(int i, int j) {

        if (i < 0 || i >= this.size || j < 0 || j >= this.size){return false;}

        else if (examinarCasillas()) {
            return true;
        }
        return false;

    }


    private boolean examinarCasillas(){

        int contador = 0; 

        if (j - 1 >= 0&&this.laberinto[i][j-1] == Square.CAMINO){contador++;}
        if (j + 1 < y&&this.laberinto[i][j+1] == Square.CAMINO){contador++;}
        if (i - 1 >= 0&&this.laberinto[i-1][j] == Square.CAMINO){contador++;}
        if (i + 1 < x&&this.laberinto[i+1][j] == Square.CAMINO){contador++;}

        if (j - 1 >= 0 && i - 1 >= 0&&this.laberinto[i-1][j-1] == Square.CAMINO){contador++;}
        if (j + 1  < y && i - 1 >= 0&&this.laberinto[i-1][j+1] == Square.CAMINO){contador++;}
        if (j - 1 >= 0 && i + 1 < x&&this.laberinto[i+1][j-1] == Square.CAMINO){contador++;}
        if (j + 1 < y && i + 1 < x&&this.laberinto[i+1][j+1] == Square.CAMINO){contador++;}

        if (contador <= 2){return true; }
        else return false; 
    }

    public void elegirDireccion(String opcion) {

        switch (opcion) {
            case "ARRIBA":

                break;

            case "ABAJO":

                break;
            case "IZQUIERDA":

                break;

            case "DERECHA":
                break;

            default:
                break;
        }

    }

    public void print() {
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto.length; j++) {

                Square symbol = this.laberinto[i][j];

                switch (symbol) {
                    case PARED:
                        System.out.print(" ■ ");
                        break;

                    case CAMINO:
                        System.out.print(" . ");
                        break;
                }
            }
            System.out.println();
        }
    }

}
