import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class Laberinto {
    private static final int PARED = 0;
    private static final int CAMINO = 1;
    private static final int INICIO = 2;
    private static final int FIN = 3;
    private static final int RELOJ = 4;
    private static final int PUNTO_DE_CONTROL = 5;

    private int[][] grid;
    private int filas;
    private int columnas;
    private boolean[][] visitadas;

    public Laberinto(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        grid = new int[filas][columnas];
        visitadas = new boolean[filas][columnas];
        generarLaberinto();
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    private void generarLaberinto() {
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(1, 1));
        grid[1][1] = CAMINO;

        while (!stack.isEmpty()) {
            Point actual = stack.peek();
            int x = actual.x;
            int y = actual.y;

            Point[] vecinos = obtenerVecinosNoVisitados(x, y);
            if (vecinos.length > 0) {
                Point vecino = vecinos[new Random().nextInt(vecinos.length)];
                int nx = vecino.x;
                int ny = vecino.y;

                grid[nx][ny] = CAMINO;
                grid[(x + nx) / 2][(y + ny) / 2] = CAMINO;
                stack.push(vecino);
            } else {
                stack.pop();
            }
        }
        colocarRelojes();
        colocarFin();
    }

    private void colocarRelojes() {
        int numRelojes = (filas * columnas) / 50;
        Random rand = new Random();
        for (int i = 0; i < numRelojes; i++) {
            int x, y;
            do {
                x = rand.nextInt(filas - 2) + 1;
                y = rand.nextInt(columnas - 2) + 1;
            } while (!esCamino(x, y) || tieneCaminosDetras(x, y));
            grid[x][y] = RELOJ;
        }
    }

    private void colocarFin() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(filas - 2) + 1;
            y = rand.nextInt(columnas - 2) + 1;
        } while (!esCamino(x, y) || tieneCaminosDetras(x, y) || (x == 1 && y == 1));
        grid[x][y] = FIN;
    }

    public Point generarPuntoDeControl() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(filas - 2) + 1;
            y = rand.nextInt(columnas - 2) + 1;
        } while (!esCamino(x, y) || tieneCaminosDetras(x, y));
        grid[x][y] = PUNTO_DE_CONTROL;
        return new Point(x, y);
    }

    public void setPuntoDeControl(Point puntoDeControl) {
        grid[puntoDeControl.x][puntoDeControl.y] = PUNTO_DE_CONTROL;
    }

    private boolean tieneCaminosDetras(int x, int y) {
        return (x > 1 && grid[x - 2][y] == CAMINO) || (x < filas - 2 && grid[x + 2][y] == CAMINO) ||
                (y > 1 && grid[x][y - 2] == CAMINO) || (y < columnas - 2 && grid[x][y + 2] == CAMINO);
    }

    private Point[] obtenerVecinosNoVisitados(int x, int y) {
        Point[] vecinos = new Point[4];
        int count = 0;

        if (x - 2 > 0 && grid[x - 2][y] == PARED) {
            vecinos[count++] = new Point(x - 2, y);
        }
        if (x + 2 < filas - 1 && grid[x + 2][y] == PARED) {
            vecinos[count++] = new Point(x + 2, y);
        }
        if (y - 2 > 0 && grid[x][y - 2] == PARED) {
            vecinos[count++] = new Point(x, y - 2);
        }
        if (y + 2 < columnas - 1 && grid[x][y + 2] == PARED) {
            vecinos[count++] = new Point(x, y + 2);
        }

        Point[] result = new Point[count];
        System.arraycopy(vecinos, 0, result, 0, count);
        return result;
    }

    public boolean esCamino(int x, int y) {
        return grid[x][y] == CAMINO || grid[x][y] == INICIO || grid[x][y] == FIN || grid[x][y] == RELOJ || grid[x][y] == PUNTO_DE_CONTROL;
    }

    public boolean esReloj(int x, int y) {
        return grid[x][y] == RELOJ;
    }

    public void removerReloj(int x, int y) {
        if (esReloj(x, y)) {
            grid[x][y] = CAMINO;
        }
    }

    public boolean esPuntoDeControl(int x, int y) {
        return grid[x][y] == PUNTO_DE_CONTROL;
    }

    public void removerPuntoDeControl(int x, int y) {
        if (esPuntoDeControl(x, y)) {
            grid[x][y] = CAMINO;
        }
    }

    public boolean esFin(int x, int y) {
        return grid[x][y] == FIN;
    }

    public void marcarVisitada(int x, int y) {
        visitadas[x][y] = true;
    }

    public boolean esVisitada(int x, int y) {
        return visitadas[x][y];
    }

    public void dibujar(Graphics g, int personajeX, int personajeY) {
        int tileSize = 20;
        int rangoVision = 2;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (Math.abs(i - personajeX) <= rangoVision && Math.abs(j - personajeY) <= rangoVision) {
                    visitadas[i][j] = true;
                }

                if (visitadas[i][j]) {
                    switch (grid[i][j]) {
                        case CAMINO:
                            g.setColor(Color.WHITE);
                            break;
                        case PARED:
                            g.setColor(Color.BLACK);
                            break;
                        case INICIO:
                            g.setColor(Color.GREEN);
                            break;
                        case FIN:
                            g.setColor(Color.RED);
                            break;
                        case RELOJ:
                            g.setColor(Color.YELLOW);
                            break;
                        case PUNTO_DE_CONTROL:
                            g.setColor(Color.BLUE);
                            break;
                    }
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }
}
