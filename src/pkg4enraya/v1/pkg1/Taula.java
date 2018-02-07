package pkg4enraya.v1.pkg1;

import java.util.*;

public class Taula {

    private Scanner teclat = new Scanner(System.in);
    private char[][] taula;
    private final char[] SIMBOL = new char[]{'X', 'O'};
    private String J1;
    private String J2;
    private static final int DIRECCION[][] = {{1, 0}, {0, 1}, {1, 1}, {1, -1}};

    Taula() {
        System.out.print("Nom del jugador 1: ");
        J1 = teclat.nextLine();
        if (J1.equals("")) {
            J1 = "Jugador 1";
        }
        System.out.print("Nom del jugador 2: ");
        J2 = teclat.nextLine();
        if (J2.equals("")) {
            J2 = "Jugador 2";
        }
        taula = new char[6][7];
    }

    private boolean posarFicha(int k) {
        int valorX = 0;
        int valorY = 0;
        imprimeix();
        System.out.println("El jugador " + (k == 0 ? J1 : J2) + " introdueix una columna (" + SIMBOL[k] + ") entre 1 i 7: ");
        int x = demanarFicha() - 1;
        for (int i = 0; i < taula.length; i++) {
            if (taula[i][x] == '\u0000') {
                taula[i][x] = SIMBOL[k];
                valorY = i;
                valorX = x;
                break;
            }
        }
        return comprovarVictoria(valorX, valorY);
    }

    private int demanarFicha() {
        boolean erroni = false;
        int x = 0;
        do {
            if (erroni == true) {
                System.out.println("Valor no valid torna introduïr entre 1 i 7.");
            }
            try {
                x = teclat.nextInt();

            } catch (InputMismatchException e) {
                teclat.nextLine();
                erroni = true;
            }
            if (x < 1 || x > 7) {
                erroni = true;
            } else {
                erroni = taula[5][x - 1] != '\u0000';
            }
        } while (erroni == true);
        return x;
    }

    public void juga() {
        for (int i = 2; i < 44; i++) {
            if ((i % 2) == 0) {
                if (posarFicha(0)) {
                    imprimeix();
                    System.out.println("Ha guanyat el jugador " + J1);
                    break;
                }
            } else if (posarFicha(1)) {
                imprimeix();
                System.out.println("Ha guanyat el jugador " + J2);
                break;
            }
            if (i == 43) {
                imprimeix();
                System.out.println("Taules");
            }
        }
    }

    private void imprimeix() {
        System.out.println("_________________");
        for (int i = 5; i >= 0; i--) {
            System.out.print("| ");
            for (int j = 0; j < 7; j++) {
                if (taula[i][j] == '\u0000') {
                    System.out.print(" ");
                } else {
                    System.out.print(taula[i][j]);
                }
                System.out.print(" ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("| 1 2 3 4 5 6 7 |");
        System.out.println();
    }

    private boolean comprovarVictoria(int x, int y) {
        boolean guanya = false;
        for (int i = 0; i < DIRECCION.length && (!guanya); i++) {
            guanya = cerca4EnLinea(x, y, DIRECCION[i][1], DIRECCION[i][0]);
        }
        return guanya;
    }

    private boolean cerca4EnLinea(int xIns, int yIns, int incX, int incY) {
        int linea = 0;
        for (int i = -3; (i <= 3) && (linea < 4); i++) {
            if ((xIns + (i * incX)) < 0 || (yIns + (i * incY)) < 0 || (xIns + (i * incX)) > (taula[0].length - 1) || (yIns + (i * incY)) > (taula.length - 1)) {
                continue;
            }
            if (taula[yIns][xIns] == taula[yIns + (i * incY)][xIns + (i * incX)]) {
                linea++;
            } else {
                linea = 0;
            }
        }
        return (linea == 4);
    }
}
