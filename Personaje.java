import java.awt.*;

public class Personaje {
    private int x, y;
    

    public Personaje(int x, int y) {
        this.x = x;
        this.y = y;
        
    }

    public void moverArriba(Laberinto laberinto) {
        if (laberinto.esCamino(x - 1, y)) {
            x--;
            laberinto.marcarVisitada(x,y);
            }
    }

    public void moverAbajo(Laberinto laberinto) {
        if (laberinto.esCamino(x + 1, y)){
            x++;
            laberinto.marcarVisitada(x,y);
                }
    }

    public void moverIzquierda(Laberinto laberinto) {
        if (laberinto.esCamino(x, y - 1)){
            y--;
            laberinto.marcarVisitada(x,y);
            }
    }

    public void moverDerecha(Laberinto laberinto) {
        if (laberinto.esCamino(x, y + 1)){
            y++;
            laberinto.marcarVisitada(x,y);
            }
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
        g.setColor(color.MAGENTA);
        g.fillRect(y * 20, x * 20, 20, 20); // 25 es el tamaño de la celda
    }
}
