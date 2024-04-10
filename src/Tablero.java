import java.util.ArrayList;
import java.util.Random;

public class Tablero {

    private Square[][] laberinto;
    private int coordenadaXCamino;
    private int coordenadaYCamino;
    private String opcionAnterior = "ARRIBA";
    private ArrayList<int[]> posicionesCamino;

    private int size = 10;

    public void GeneralTablero() {

        laberinto = new Square[this.size][this.size];
        this.posicionesCamino = new ArrayList<>();
        this.inical();

    }

    public void inical() {

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                this.laberinto[i][j] = Square.BOARD;
            }
        }

        Random randomOpcion = new Random();

        String[] opciones = { "ARRIBA", "ABAJO", "IZQUIERDA", "DERECHA" };

        while (true) {

            if (this.coordenadaXCamino == this.size - 1 ||
                    this.coordenadaYCamino == this.size - 1) {
                break;
            }

            int seleccion = randomOpcion.nextInt(opciones.length);

            elegirDireccion(opciones[seleccion]);
        }

    }

    public void elegirDireccion(String opcion) {

        switch (opcion) {

            case "ARRIBA":

                if (!opcionAnterior.equals("ABAJO"))
                    generalCamino(-1, 0, "ARRIBA");

                break;
            case "ABAJO":
                if (!opcionAnterior.equals("ARRIBA"))
                    generalCamino(+1, 0, "ABAJO");

                break;
            case "IZQUIERDA":
                if (!opcionAnterior.equals("DERECHA"))
                    generalCamino(0, -1, "IZQUIERDA");

                break;
            case "DERECHA":
                if (!opcionAnterior.equals("IZQUIERDA") || !opcionAnterior.equals("ARRIBA"))
                    generalCamino(0, +1, "DERECHA");

                break;
        }

    }

    public void generalCamino(int x, int y, String opcion) {

        this.coordenadaXCamino += x;
        if (this.coordenadaXCamino < 0 || this.coordenadaXCamino >= this.size) {
            this.coordenadaXCamino -= x;
        }
        this.coordenadaYCamino += y;
        if (this.coordenadaYCamino < 0 || this.coordenadaYCamino >= this.size) {
            this.coordenadaYCamino -= y;
        }
        else {
            this.laberinto[this.coordenadaXCamino][this.coordenadaYCamino] = Square.CAMINO;
            posicionesCamino.add(new int[]{x,y});
        }

        this.opcionAnterior = opcion;

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
