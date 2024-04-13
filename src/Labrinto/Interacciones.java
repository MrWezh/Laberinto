package Labrinto;
import java.util.Scanner;

public class Interacciones {

    Tablero_MecanicaJuego talero;
    Scanner wz;
    private int puntosDeasignacion = 15;
    
    
    public Interacciones() {
        wz = new Scanner(System.in);
        talero = new Tablero_MecanicaJuego();
    }

    public int getPuntosDeasignacion() {
        return puntosDeasignacion;
    }

    public void setPuntosDeasignacion(int puntosDeasignacion) {
        this.puntosDeasignacion = puntosDeasignacion;
    }

    public Tablero_MecanicaJuego getTalero() {
        return talero;
    }
    
    public void menuInical(){
        
        System.out.println("1. EMPEZAR");
        System.out.println("2. ¿COMO JUAGAR?");
        System.out.println("0. SALLIR");
        

        int opcion = wz.nextInt();

        switch (opcion) {
            case 1:
            this.elegirDificurtad();
            //this.crearPersonaje();
            this.inicialJuego();
            
           
                break;
            case 2:
                break;

            case 0:
                System.exit(0);
                
            default:
            this.clearScreen();
                System.out.println("----------OPCIÓN INCORRECTO------------");
                menuInical();
                break;
        }
    }

    private void elegirDificurtad() {
        

        System.out.println("Elige una dificultad para tu partida: ");
        System.out.println("---------------------------------------");
        System.out.println("1.FACIL");
        System.out.println("2.NORMAL");
        System.out.println("3.DIFÍCIL");

        int opcion = wz.nextInt();

        switch (opcion) {
            case 1:
                this.talero.setSize(10);
                this.talero.getSoldado().setAtaque(1);
                this.talero.getSoldado().setEscudo(2);
                this.talero.getSoldado().setVida(4);

                this.talero.getAsesino().setAtaque(3);
                this.talero.getAsesino().setEscudo(3);
                this.talero.getAsesino().setVida(3);



                break;
            case 2:
                this.talero.setSize(15);
                this.talero.getSoldado().setAtaque(3);
                this.talero.getSoldado().setEscudo(4);
                this.talero.getSoldado().setVida(8);

                this.talero.getAsesino().setAtaque(8);
                this.talero.getAsesino().setEscudo(1);
                this.talero.getAsesino().setVida(5);
                break;
            case 3:
                this.talero.setSize(20);
                this.talero.getSoldado().setAtaque(3);
                this.talero.getSoldado().setEscudo(6);
                this.talero.getSoldado().setVida(10);

                this.talero.getAsesino().setAtaque(9);
                this.talero.getAsesino().setEscudo(6);
                this.talero.getAsesino().setVida(2);

                this.puntosDeasignacion += 5; 
                break;
            default:
            this.clearScreen();
        System.out.println("----------OPCIÓN INCORRECTO------------");
        elegirDificurtad();
                break;
        }

        
    }

    private void crearPersonaje() {
        int puntos = this.puntosDeasignacion - this.talero.getJugador().getSumaPuntos();
        System.out.println("PUNTOS DE ASIGNACIÓN: "+puntos);
        while (true) {
            System.out.println("Indroduzca la vida que tendrá tu PJ:");
            int vidaPJ = wz.nextInt();
            if (vidaPJ <= this.puntosDeasignacion-2) {}            
        }
    }

    private void inicialJuego() {
        this.clearScreen();
        this.talero.GeneralTablero();
        this.talero.print();
        
        // Imprimir la información del jugador, asesino y soldado
        System.out.println(this.talero.getJugador().toString());
        System.out.println(this.talero.getAsesino().toString());
        System.out.println(this.talero.getSoldado().toString());
    }
    



        public void clearScreen(){
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }



}
