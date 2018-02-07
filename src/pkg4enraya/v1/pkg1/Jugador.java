package pkg4enraya.v1.pkg1;

public class Jugador {

    private int id;
    private String nom;
    public static int numJugadors = 0;
    private char fitxa;

    public Jugador(String nom, char f) {
        fitxa = f;
        this.id = numJugadors + 1;
        this.nom = nom;
        numJugadors++;
    }

    public char getFitxa() {
        return fitxa;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

}
