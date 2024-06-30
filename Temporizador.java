import java.awt.*;

public class Temporizador {
    private int tiempoRestante;

    public Temporizador(int tiempoInicial) {
        tiempoRestante = tiempoInicial;
    }

    public void decrementar() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public void agregarTiempo(int tiempo) {
        tiempoRestante += tiempo;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void reiniciar() {
        tiempoRestante = 40;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Tiempo Restante: " + tiempoRestante, 10, 10);
    }
}
