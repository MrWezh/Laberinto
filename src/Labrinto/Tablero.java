package Labrinto;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private Square[][] laberinto;
    private int coordenadaICamino = 0;
    private int coordenadaJCamino = 0;
    private ArrayList<int[]> posicionesCamino;
    private int limiteGeneralCaminos = 0; 

    //los usuarios tendran 3 niveles a elegir, 10 facil, 15 medio y 20 dificil.
    private int size = 20;

    public void GeneralTablero() {

        laberinto = new Square[this.size][this.size];
        this.posicionesCamino = new ArrayList<>();
       
        for (int i = 0; i < this.size; i++) {

            for (int j = 0; j < this.size; j++) {
                this.laberinto[i][j] = Square.PARED;
            }
        }

      // Random randomInicial = new Random();

      //coordenadaJCamino = randomInicial.nextInt(this.size);
        this.laberinto[this.coordenadaICamino][this.coordenadaJCamino] = Square.CAMINO;

        this.construirCamino(true);
        this.removerIguales();

        for (int i = 0; i < 3; i++) {
            this.extenderCamino();    
        }
        
    }

    private void removerIguales() {
        int[] posicionesGuardados = {-1, -1};
        for (int i = posicionesCamino.size() - 1; i >= 0; i--) {
            int[] e = posicionesCamino.get(i);
            if (posicionesGuardados[0] == e[0] && posicionesGuardados[1] == e[1]) {
                posicionesCamino.remove(i);
            } else {
                posicionesGuardados[0] = e[0];
                posicionesGuardados[1] = e[1];
            }
        }
    } 

 /**
     * @return
     */
    private void construirCamino(boolean guardarCoordenadasCaminos) {
        Random randomDireccion = new Random();

        // 1: subir, 2: girar izquierda, 3: bajar, 4: girar derecha
        int opcion = randomDireccion.nextInt(4);

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
        
        if (guardarCoordenadasCaminos){int[] e = {coordenadaICamino, coordenadaJCamino};
        this.posicionesCamino.add(e);
}
        if (this.coordenadaICamino != this.size - 1&&this.coordenadaJCamino >= 0&&this.limiteGeneralCaminos != this.size*2) {
           // System.out.println("-------------------------------------------");
            //print();
            limiteGeneralCaminos ++; 
            construirCamino(true);
        }

        else limiteGeneralCaminos = 0; 

    }

    private boolean construir(int i, int j) {

        if (i < 0 || i >= this.size || j < 0 || j >= this.size){return false;}

       
        else if (examinarCasillas(i, j)&&this.laberinto[i][j]!=Square.CAMINO) {
            return true;
        }
        else {return false;}

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


    public void extenderCamino(){


        for (int i = posicionesCamino.size() - 1; i >= 0; i--)  {

            int[] e = posicionesCamino.get(i);
            this.examinacionHorizontalVertical(e[0], e[1]);
            this.laberinto[coordenadaICamino][coordenadaJCamino] = Square.CAMINO;
            this.construirCamino(false);
        }
        

    }
   // Verificar si las casillas de arriba, abajo, izquierda o derecha son posibles de extender como camino
   
public void examinacionHorizontalVertical(int i, int j) {

    if (i - 1 >= 0 && this.laberinto[i - 1][j] == Square.PARED && examinarCasillas(i - 1, j)) {
         i --;

    } else if (j - 1 >= 0 && this.laberinto[i][j - 1] == Square.PARED && examinarCasillas(i, j - 1)) {
        j --;

    } else if (i + 1 < this.size && this.laberinto[i + 1][j] == Square.PARED && examinarCasillas(i + 1, j)) {
        i ++;

    } else if (j + 1 < this.size && this.laberinto[i][j + 1] == Square.PARED && examinarCasillas(i, j + 1)) {
        j ++;

    } 

    this.coordenadaICamino = i; this.coordenadaJCamino = j; 
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


      /*  for (int[] a : posicionesCamino) {
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i]);
            }System.out.println();
        }*/
    }



}
