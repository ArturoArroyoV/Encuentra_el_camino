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
 private void iniciarTemporizador() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                } else {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }