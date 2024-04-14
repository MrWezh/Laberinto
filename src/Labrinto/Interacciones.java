package Labrinto;
import java.util.Scanner;


public class Interacciones {

    Tablero_MecanicaJuego tablero;
    Scanner wz;
    private int puntosDeasignacion = 15;
    private boolean ganarPartida = false;
    private boolean perderPartida = false;
    
    
    public Interacciones() {
        wz = new Scanner(System.in);
        tablero = new Tablero_MecanicaJuego();
    }

    public int getPuntosDeasignacion() {
        return puntosDeasignacion;
    }

    public void setPuntosDeasignacion(int puntosDeasignacion) {
        this.puntosDeasignacion = puntosDeasignacion;
    }

    public Tablero_MecanicaJuego getTablero() {
        return tablero;
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
                this.tablero.setSize(10);
                this.tablero.getSoldado().setAtaque(1);
                this.tablero.getSoldado().setEscudo(5);
                this.tablero.getSoldado().setVida(5);

                this.tablero.getAsesino().setAtaque(3);
                this.tablero.getAsesino().setEscudo(3);
                this.tablero.getAsesino().setVida(3);
            
                this.puntosDeasignacion -= 5;

                this.tablero.setNumeroEnemigos(6);
                this.tablero.setNumeroRecompensa(6);
                this.tablero.setNumeroSalida(1);

                break;
            case 2:
                this.tablero.setSize(15);
                this.tablero.getSoldado().setAtaque(6);
                this.tablero.getSoldado().setEscudo(6);
                this.tablero.getSoldado().setVida(8);

                this.tablero.getAsesino().setAtaque(8);
                this.tablero.getAsesino().setEscudo(0);
                this.tablero.getAsesino().setVida(5);

                this.tablero.setNumeroEnemigos(14);
                this.tablero.setNumeroRecompensa(14);
                this.tablero.setNumeroSalida(2);

                break;
            case 3:
                this.tablero.setSize(20);
                this.tablero.getSoldado().setAtaque(3);
                this.tablero.getSoldado().setEscudo(6);
                this.tablero.getSoldado().setVida(24);

                this.tablero.getAsesino().setAtaque(9);
                this.tablero.getAsesino().setEscudo(7);
                this.tablero.getAsesino().setVida(5);

                this.puntosDeasignacion += 5; 

                this.tablero.setNumeroEnemigos(20);
                this.tablero.setNumeroRecompensa(20);
                this.tablero.setNumeroSalida(3);

                break;
            default:
            this.clearScreen();
        System.out.println("----------OPCIÓN INCORRECTO------------");
        elegirDificurtad();
                break;
        }

        
    }

    private void crearPersonaje() {
        this.clearScreen();
       this.indroducirNombrePJ();
       int vida = this.indroducirVidaAtaqueEscudoPJ("vida");
       int ataque = this.indroducirVidaAtaqueEscudoPJ("ataque");
       int escudo = 0;
       if(puntosDeasignacion != 0){
       escudo = this.indroducirVidaAtaqueEscudoPJ("escudo");
       }

      
       while (true){

        System.out.println("Nombre de tu personaje: "+this.tablero.getJugador().getNombre());
        System.out.println("[vida: "+vida+" | ataque: "+ataque+" | escudo: "+escudo+"]");

       System.out.println("¿Estas deacuerdo con la confuguración de tu PJ?(y/n)");
       String yesno = wz.next().toLowerCase();

       if(yesno.equals("n")){
        this.puntosDeasignacion = vida + ataque + escudo;
        this.clearScreen();
        crearPersonaje();
       }
       else if (yesno.equals("y")){
        this.tablero.getJugador().setVida(vida);
        this.tablero.getJugador().setAtaque(ataque);
        this.tablero.getJugador().setEscudo(escudo);
        break;}
       else{
        System.out.println("----------OPCIÓN INCORRECTO------------");
       }
    }this.clearScreen();
    }

    public void indroducirNombrePJ(){
        wz.nextLine();
        System.out.println("Indroduzca el nombre que tendrá tu PJ:");
        String nombre = wz.nextLine();
        
        System.out.println("-------------------------------------");
        System.out.println("¿Estas seguro que tu PJ se llamará "+nombre+"?(y/n)");
        String yesno = wz.next().toLowerCase();
        if(yesno.equals("n")){indroducirNombrePJ();}

        else if(yesno.equals("y")){this.tablero.getJugador().setNombre(nombre);}

        else {
            System.out.println("----------OPCIÓN INCORRECTO------------");
            indroducirNombrePJ();
        }
    }
    

    public int indroducirVidaAtaqueEscudoPJ(String nombre){
        int puntos = this.puntosDeasignacion;
        System.out.println("PUNTOS DE ASIGNACIÓN: "+puntos);
        System.out.println("-------------------------------------");
        
            int asignarPuntos = 0;
            

            switch (nombre) {
                case "vida":
                System.out.println("Indroduzca la "+nombre+" que tendrá tu PJ:");
                asignarPuntos = wz.nextInt();
                if (asignarPuntos > this.puntosDeasignacion-4||asignarPuntos<0) {
                    System.out.println("----------OPCIÓN INCORRECTO------------");
                    System.out.println(nombre+" máxima: "+ (this.puntosDeasignacion-4));
                    indroducirVidaAtaqueEscudoPJ("vida");
                    }else{
                        this.puntosDeasignacion -= asignarPuntos;
                        
              }
                    break;
                case "ataque":
                System.out.println("Indroduzca el "+nombre+" que tendrá tu PJ:");
                asignarPuntos = wz.nextInt();
                if (asignarPuntos > this.puntosDeasignacion||asignarPuntos<0) {
                    System.out.println("----------OPCIÓN INCORRECTO------------");
                    System.out.println(nombre+" máxima: "+ (this.puntosDeasignacion));
                    indroducirVidaAtaqueEscudoPJ("ataque");
                    }else{
                        this.puntosDeasignacion -= asignarPuntos;
                       
              }
                    break; 
                case "escudo":
                System.out.println("Indroduzca el "+nombre+" que tendrá tu PJ:");
                asignarPuntos = wz.nextInt();
                if (asignarPuntos > this.puntosDeasignacion||asignarPuntos<0) {
                    System.out.println("----------OPCIÓN INCORRECTO------------");
                    System.out.println("---------MOVIMIENTO INCORRECTO---------");
                    System.out.println(nombre+" máxima: "+ (this.puntosDeasignacion));
                    indroducirVidaAtaqueEscudoPJ("escudo");
                    }else{
                        this.puntosDeasignacion -= asignarPuntos;
                        
              } 
                    break;
                default:
                    break;
            }
            return asignarPuntos;
            
    }

    public void preguntarDireccionMovimiento(){
        wz.nextLine();
        System.out.print("Movimiento(w/a/s/d) :");
        String movimiento = wz.next().toLowerCase();

        int[] coordenadaMover = this.tablero.movimientoPJ(movimiento);


        int[] casillaActual = this.tablero.getCoordenadaPJ();

        if (coordenadaMover[0]*coordenadaMover[1] < 0||this.tablero.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] == Square.PARED) {
            
                    System.out.println("---------MOVIMIENTO INCORRECTO---------");
                    this.preguntarDireccionMovimiento();
        }

        else {
            this.tablero.getLaberinto()[casillaActual[0]][casillaActual[1]] = Square.CAMINO;
            this.tablero.setCoordenadaPJ(coordenadaMover);
            if (this.tablero.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] == Square.SALIDA) {
                this.ganarPartida = true;
            }

            this.tablero.getLaberinto()[coordenadaMover[0]][coordenadaMover[1]] = Square.PERSONAJE;
            

        }

    }
 

    private void inicialJuego() {
        this.clearScreen();
        this.tablero.GeneralTablero();
        this.tablero.generalOtros();

        while (true) {
            this.clearScreen();
            if (this.ganarPartida){
                
                this.clearScreen();
                        System.out.println();
                        System.out.println(" ░▒▓███████▓▒░  ░▒▓██████▓▒░░ ▒▓███████▓▒░░ ▒▓███████▓▒░░ ▒▓████████▓▒░▒▓█▓▒░  ░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░  ░▒▓█▓▒░▒▓█▓▒░░▒▓███████▓▒░▒▓████████▓▒░▒▓████████▓▒░░▒▓███████▓▒░");
                        System.out.println(" ░▒▓█▓▒░       ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░      ░▒▓█▓▒░  ░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░  ░▒▓█▓▒░▒▓█▓▒░▒▓█▓▒░         ░▒▓█▓▒░   ░▒▓█▓▒░       ░▒▓█▓▒░          ");
                        System.out.println(" ░▒▓█▓▒░       ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░       ░▒▓█▓▒  ▒▓█▓▒░░▒▓█▓▒░ ▒▓█▓▒  ▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░         ░▒▓█▓▒░   ░▒▓█▓▒░       ░▒▓█▓▒░          ");
                        System.out.println("  ░▒▓██████▓▒░ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓███████▓▒░ ░▒▓███████▓▒░ ░▒▓██████▓▒░   ░▒▓█▓▒▒▓█▓▒░ ░▒▓█▓▒░ ░▒▓█▓▒▒▓█▓▒░ ░▒▓█▓▒░▒▓██████▓▒░    ░▒▓█▓▒░   ░▒▓██████▓▒░  ░▒▓██████▓▒░    ");
                        System.out.println("        ░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░         ░▒▓█▓▓█▓▒░  ░▒▓█▓▒░  ░▒▓█▓▓█▓▒░  ░▒▓█▓▒░     ░▒▓█▓▒░   ░▒▓█▓▒░   ░▒▓█▓▒░             ░▒▓█▓▒░    ");
                        System.out.println("        ░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓█▓▒░         ░▒▓█▓▓█▓▒░  ░▒▓█▓▒░  ░▒▓█▓▓█▓▒░  ░▒▓█▓▒░     ░▒▓█▓▒░   ░▒▓█▓▒░   ░▒▓█▓▒░             ░▒▓█▓▒░  ▒▓██▓▒ ▒▓██▓▒ ▒▓██▓▒ ");
                        System.out.println(" ░▒▓███████▓▒░  ░▒▓██████▓▒░ ░▒▓███████▓▒░ ░▒▓█▓▒░░▒▓█▓▒ ░▒▓████████▓▒░   ░▒▓██▓▒░   ░▒▓█▓▒░   ░▒▓██▓▒░   ░▒▓█▓▒░▒▓███████▓▒    ░▒▓█▓▒░   ░▒▓████████▓▒░▒▓███████▓▒░   ▒▓██▓▒ ▒▓██▓▒ ▒▓██▓▒ ");
                        System.out.println();
                     break;
            }

            if (this.perderPartida) {

                System.out.println("                                                                                                                                                                                                          \n           " +           
                "                     ░▒▓██████▓▒░▒▓████████▓▒░▒▓███████▓▒░ ░▒▓██████▓▒░       ░▒▓██████████████▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░▒▓███████▓▒░▒▓████████▓▒░▒▓████████▓▒░      ░▒▓██████████████▓▒░ ░▒▓██████▓▒░ ░▒▓███████▓▒░                       \n" +
                "                    ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░                             \n" +
                "                    ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░                              \n" +
                "                    ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓███████▓▒░░▒▓████████▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓██████▓▒░ ░▒▓███████▓▒░  ░▒▓█▓▒░   ░▒▓██████▓▒░        ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓████████▓▒░░▒▓██████▓▒░                        \n" +
                "                    ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░                       \n" +
                "                    ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓█▓▒░             ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░  ▒▓██▓▒ ▒▓██▓▒ ▒▓██▓▒  \n" +
                "                     ░▒▓██████▓▒░  ░▒▓█▓▒░   ░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░ ▒▓██████▓▒░░▒▓████████▓▒░▒▓█▓▒░░▒▓█▓▒░ ░▒▓█▓▒░   ░▒▓████████▓▒░      ░▒▓█▓▒░░▒▓█▓▒░░▒▓█▓▒░▒▓█▓▒░░▒▓█▓▒░▒▓███████▓▒░   ▒▓██▓▒ ▒▓██▓▒ ▒▓██▓▒  ");

            }
        
            this.tablero.print();
        
        // Imprimir la información del jugador, asesino y soldado
        System.out.println(this.tablero.getCoordenadaPJ()[0]+" "+this.tablero.getCoordenadaPJ()[1]);
        
        this.preguntarDireccionMovimiento();
        }
        
    }

    



        public void clearScreen(){
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }



}
