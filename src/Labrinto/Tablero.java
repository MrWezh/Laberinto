package Labrinto;

import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private Square[][] laberinto;
    private int coordenadaICamino = 0;
    private int coordenadaJCamino = 0;
    private ArrayList<int[]> posicionesCamino;
    private int limiteGeneralCaminos = 0;

    public Square[][] getLaberinto() {
        return laberinto;
    }

    public void setLaberinto(Square[][] laberinto) {
        this.laberinto = laberinto;
    }

    public int getCoordenadaICamino() {
        return coordenadaICamino;
    }

    public void setCoordenadaICamino(int coordenadaICamino) {
        this.coordenadaICamino = coordenadaICamino;
    }

    public int getCoordenadaJCamino() {
        return coordenadaJCamino;
    }

    public void setCoordenadaJCamino(int coordenadaJCamino) {
        this.coordenadaJCamino = coordenadaJCamino;
    }

    public ArrayList<int[]> getPosicionesCamino() {
        return posicionesCamino;
    }

    public void setPosicionesCamino(ArrayList<int[]> posicionesCamino) {
        this.posicionesCamino = posicionesCamino;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // los usuarios tendran 3 niveles a elegir, 10x10 facil, 15x15 medio y 20x20
    // dificil.
    private int size = 20;

    public void GeneralTablero() {

        laberinto = new Square[this.size][this.size];
        this.posicionesCamino = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {

            for (int j = 0; j < this.size; j++) {

                this.laberinto[i][j] = Square.PARED;
            }
        }
        this.coordenadaICamino = 0;
        this.coordenadaJCamino = 0;
        this.laberinto[0][0] = Square.PERSONAJE;

        this.construirCamino();
        for (int i = 0; i < 3; i++) {
            this.extenderCamino();
        }

    }

    /**
     * @return
     */
    private void construirCamino() {
        Random randomDireccion = new Random();

        // 1: subir, 2: girar izquierda, 3: bajar, 4: girar derecha
        int opcion = randomDireccion.nextInt(4);

        switch (opcion) {
            case 1:
                if (construir(coordenadaICamino - 1, coordenadaJCamino))
                    coordenadaICamino--;
                break;
            case 2:
                if (construir(coordenadaICamino, coordenadaJCamino + 1))
                    coordenadaJCamino++;
                break;
            case 3:
                if (construir(coordenadaICamino + 1, coordenadaJCamino))
                    coordenadaICamino++;
                break;

            case 4:
                if (construir(coordenadaICamino, coordenadaJCamino - 1))
                    coordenadaJCamino--;
                break;

            default:
                break;
        }
        // solo guardar cuando no es camino para no pintar otra vez una casilla que ya
        // es camino y para evitar coordenadas repetidas de camonos.
        if (this.laberinto[coordenadaICamino][coordenadaJCamino] == Square.PARED) {
            this.laberinto[coordenadaICamino][coordenadaJCamino] = Square.CAMINO;
            int[] e = { coordenadaICamino, coordenadaJCamino };
            this.posicionesCamino.add(e);
        }

        // this.size * 2 para limitar la generación de camino, ya que hay situaciones
        // que el generador de camino se queda estancado y entra en un bucle infinito.
        
        if (this.coordenadaICamino != this.size - 1 && this.coordenadaJCamino >= 0
                && this.limiteGeneralCaminos != this.size * 2) {
            // System.out.println("-------------------------------------------");
            // print();
            limiteGeneralCaminos++;
            construirCamino();
        }

        else
            limiteGeneralCaminos = 0;

    }

    /*
     * la función/metodo construir sirve para ver si las casillas escogidas por el
     * metodo construirCamino ,generados aleatoriamente,
     * es una casillas varida(con casilla me refiero a coordenadas de la matriz).
     */
    private boolean construir(int i, int j) {

        if (i < 0 || i >= this.size || j < 0 || j >= this.size) {
            return false;
        }

        else if (examinarCasillas(i, j) && this.laberinto[i][j] == Square.PARED) {
            return true;
        } else {
            return false;
        }

    }

    /*
     * para evitar que los caminos se compactan, antes de poner pintar el camino,
     * miramos si a su alrededor hay más de 3 caminos o no.
     * 
     */

    private boolean examinarCasillas(int i, int j) {
        int contador = 0;

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

            // Verificar si las coordenadas vecinas están dentro de los límites del
            // laberinto
            if (filaVecina >= 0 && filaVecina < this.size && columnaVecina >= 0 && columnaVecina < this.size) {
                // Verificar si la casilla vecina es CAMINO o PERSONAJE
                if (this.laberinto[filaVecina][columnaVecina] == Square.CAMINO ||
                        this.laberinto[filaVecina][columnaVecina] == Square.PERSONAJE) {
                    contador++;
                }
            }
        }

        // Devolver true si el contador es menor o igual a 2, de lo contrario false
        return contador <= 2;
    }

    /*
     * Recorrer toda la rista de camino que se han guardados en el Arraylist
     * posicionesCaminos para "mirar" si se puede extender mas caminos utilizandos
     * la función
     * examinacionHorizontalVertical.
     */
    public void extenderCamino() {

        for (int i = posicionesCamino.size() - 1; i >= 0; i--) {

            int[] e = posicionesCamino.get(i);
            this.examinacionHorizontalVertical(e[0], e[1]);
            this.laberinto[coordenadaICamino][coordenadaJCamino] = Square.CAMINO;
            this.construirCamino();
        }

    }
    // Verificar si las casillas de arriba, abajo, izquierda o derecha son posibles
    // de extender como camino

    public void examinacionHorizontalVertical(int i, int j) {

        boolean sepuedeExtender = true;

        if (i - 1 >= 0 && this.laberinto[i - 1][j] == Square.PARED && examinarCasillas(i - 1, j)) {
            i--;

        } else if (j - 1 >= 0 && this.laberinto[i][j - 1] == Square.PARED && examinarCasillas(i, j - 1)) {
            j--;

        } else if (i + 1 < this.size && this.laberinto[i + 1][j] == Square.PARED && examinarCasillas(i + 1, j)) {
            i++;

        } else if (j + 1 < this.size && this.laberinto[i][j + 1] == Square.PARED && examinarCasillas(i, j + 1)) {
            j++;

        } else
            sepuedeExtender = false;

        // esta restricción sirve para evitar guardar coordenadas inecesarias o
        // repetidas.
        if (sepuedeExtender) {
            this.coordenadaICamino = i;
            this.coordenadaJCamino = j;
            int[] guardarCoordenadaCamino = { this.coordenadaICamino, this.coordenadaJCamino };
            this.posicionesCamino.add(guardarCoordenadaCamino);
        }
    }

}
