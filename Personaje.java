import java.awt.*;

public class Personaje {
    private int x;
    private int y;
    private Color color;

    public Personaje(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.BLUE; // Color del personaje
    }

    public void moverArriba(Laberinto laberinto) {
        if (laberinto.esCamino(x - 1, y)) x--;
    }

    public void moverAbajo(Laberinto laberinto) {
        if (laberinto.esCamino(x + 1, y)) x++;
    }

    public void moverIzquierda(Laberinto laberinto) {
        if (laberinto.esCamino(x, y - 1)) y--;
    }

    public void moverDerecha(Laberinto laberinto) {
        if (laberinto.esCamino(x, y + 1)) y++;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void dibujar(Graphics g) {
        g.setColor(color);
        g.fillRect(y * 25, x * 25, 25, 25); // 25 es el tamaño de la celda
    }
}
