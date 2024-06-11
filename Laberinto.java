public class Laberinto {
    private final int[][] laberinto;
    private final int filas, columnas;
    private final int TAMANO = 50;

    public Laberinto(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.laberinto = new int[filas][columnas];
    }
}
