import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Temporizador {
    private int tiempoRestante;
    private Timer timer;

    public Temporizador(int tiempoInicial) {
        this.tiempoRestante = tiempoInicial;
        this.timer = new Timer();
        iniciarTemporizador();
    }
