public class Personaje {
    private int x, y;
    private final int TAMANO = 50;

    public Personaje(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, TAMANO, TAMANO);
    }

    public void moverArriba() {
        y -= TAMANO;
    }

    public void moverAbajo() {
        y += TAMANO;
    }

    public void moverIzquierda() {
        x -= TAMANO;
    }

    public void moverDerecha() {
        x += TAMANO;
    }
}
