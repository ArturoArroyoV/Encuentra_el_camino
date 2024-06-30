import java.awt.*;

public class Temporizador {
    private int tiempoRestante;
    private int tiempoInicial;

    public Temporizador(int tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
        this.tiempoRestante = tiempoInicial;
    }

    public void agregarTiempo(int segundos) {
        tiempoRestante += segundos;
    }

    public void decrementar() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public void reiniciar() {
        tiempoRestante = tiempoInicial;
    }

    public int getTiempoRestante() {
        return tiempoRestante;
    }

    public void pausar() {
        tiempoRestante = 0;
    }

    public void dibujar(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawString("Tiempo: " + tiempoRestante, x, y);
    }
}
