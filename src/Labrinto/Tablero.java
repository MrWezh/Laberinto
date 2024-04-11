package Labrinto;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private Square[][] laberinto;
    private int coordenadaICamino = 0;
    private int coordenadaJCamino = 0;
    private String opcionAnterior = "ARRIBA";
    private ArrayList<int[]> posicionesCamino;

    private int limiteGeneralCaminos = 0; 

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

      // Random randomInicial = new Random();

      //coordenadaJCamino = randomInicial.nextInt(this.size);
        this.laberinto[this.coordenadaICamino][this.coordenadaJCamino] = Square.CAMINO;

        this.construirCamino();
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
                    coordenadaJCamino++;
                break;
            case 3:
                if (construir(coordenadaICamino + 1, coordenadaJCamino))
                    coordenadaICamino++;
                break;

            case 4:
                if (construir(coordenadaICamino, coordenadaJCamino -1))
                    coordenadaJCamino--;
                break;

            default:
                break;
        }
        this.laberinto[coordenadaICamino][coordenadaJCamino] = Square.CAMINO;
        int[] e = {coordenadaICamino, coordenadaJCamino};
        this.posicionesCamino.add(e);

        if (this.coordenadaICamino != this.size - 1&&limiteGeneralCaminos != this.size*100) {
           // System.out.println("-------------------------------------------");
            //print();
            limiteGeneralCaminos ++; 
            construirCamino();
        }

        limiteGeneralCaminos = 0; 

    }

    private boolean construir(int i, int j) {

        if (i < 0 || i >= this.size || j < 0 || j >= this.size){return false;}

        else if (examinarCasillas(i, j)&&this.laberinto[i][j]!=Square.CAMINO) {
            return true;
        }
        else return false;

    }


    private boolean examinarCasillas(int i, int j){

        int contador = 0; 

        if (j - 1 >= 0&&this.laberinto[i][j-1] == Square.CAMINO){contador++;}
        if (j + 1 < this.size&&this.laberinto[i][j+1] == Square.CAMINO){contador++;}
        if (i - 1 >= 0&&this.laberinto[i-1][j] == Square.CAMINO){contador++;}
        if (i + 1 < this.size&&this.laberinto[i+1][j] == Square.CAMINO){contador++;}

        if (j - 1 >= 0 && i - 1 >= 0&&this.laberinto[i-1][j-1] == Square.CAMINO){contador++;}
        if (j + 1  < this.size && i - 1 >= 0&&this.laberinto[i-1][j+1] == Square.CAMINO){contador++;}
        if (j - 1 >= 0 && i + 1 < this.size&&this.laberinto[i+1][j-1] == Square.CAMINO){contador++;}
        if (j + 1 < this.size && i + 1 < this.size&&this.laberinto[i+1][j+1] == Square.CAMINO){contador++;}

        if (contador <= 2){return true; }
        else return false; 
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


        for (int[] a : posicionesCamino) {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i]);
            }System.out.println();
        }
    }

}
