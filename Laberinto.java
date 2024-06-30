import java.awt.*;
import java.util.Random;
import java.util.Stack;

public class Laberinto {
    private int filas;
    private int columnas;
    private int[][] grid;
    private final int CAMINO = 0;
    private final int PARED = 1;
    private final int INICIO = 2;
    private final int FIN = 3;
    private final int RELOJ = 4;
    private final int PUNTO_DE_CONTROL = 5;

    public Laberinto(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        grid = new int[filas][columnas];
        generarLaberinto();
    }

    private void generarLaberinto() {
        // Inicializar todo el grid como paredes
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                grid[i][j] = PARED;
            }
        }
        // Definir la posición inicial y final
        grid[1][1] = INICIO;
        grid[filas - 2][columnas - 2] = FIN;

        // Usar un algoritmo de backtracking para generar el laberinto
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(1, 1));

        while (!stack.isEmpty()) {
            Point current = stack.peek();
            int x = current.x;
            int y = current.y;

            // Obtener vecinos no visitados
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

        // Colocar el reloj en un lugar aleatorio en el camino
        colocarElementoAleatorio(RELOJ);

        // Colocar el punto de control en un lugar aleatorio en el camino
        colocarElementoAleatorio(PUNTO_DE_CONTROL);
    }

    private void colocarElementoAleatorio(int elemento) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(filas);
            y = rand.nextInt(columnas);
        } while (grid[x][y] != CAMINO);
        grid[x][y] = elemento;
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
        return grid[x][y] == CAMINO || grid[x][y] == INICIO || grid[x][y] == FIN;
    }

    public boolean esFin(int x, int y) {
        return grid[x][y] == FIN;
    }

    public boolean esReloj(int x, int y) {
        return grid[x][y] == RELOJ;
    }

    public boolean esPuntoDeControl(int x, int y) {
        return grid[x][y] == PUNTO_DE_CONTROL;
    }

    public void removerReloj(int x, int y) {
        if (grid[x][y] == RELOJ) {
            grid[x][y] = CAMINO;
        }
    }

    public void dibujar(Graphics g) {
        int ancho = getWidth() / columnas;
        int alto = getHeight() / filas;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (grid[i][j] == PARED) {
                    g.setColor(Color.WHITE); // Pared
                } else if (grid[i][j] == CAMINO || grid[i][j] == INICIO) {
                    g.setColor(Color.BLACK); // Camino
                } else if (grid[i][j] == FIN) {
                    g.setColor(Color.GREEN); // Fin del nivel
                } else if (grid[i][j] == RELOJ) {
                    g.setColor(Color.MAGENTA); // Reloj
                } else if (grid[i][j] == PUNTO_DE_CONTROL) {
                    g.setColor(Color.YELLOW); // Punto de control
                }
                g.fillRect(j * ancho, i * alto, ancho, alto);
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    private int getWidth() {
        return 500;
    }

    private int getHeight() {
        return 500;
    }
}
