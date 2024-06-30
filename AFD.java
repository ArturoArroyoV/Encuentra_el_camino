import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class AFD {
    private String estadoActual;
    private Map<String, Map<String, String>> transiciones;

    public AFD() {
        estadoActual = "Q0";
        transiciones = new HashMap<>();
        definirTransiciones();
    }

    private void definirTransiciones() {
        agregarTransicion("Q0", "mover", "Q1");
        agregarTransicion("Q1", "mover", "Q2");
        agregarTransicion("Q2", "mover", "Q3");
        agregarTransicion("Q3", "finDelNivel", "Q4");
        agregarTransicion("Q4", "nuevoNivel", "Q0");
    }

    private void agregarTransicion(String estadoActual, String accion, String nuevoEstado) {
        transiciones.putIfAbsent(estadoActual, new HashMap<>());
        transiciones.get(estadoActual).put(accion, nuevoEstado);
    }

    public void transitar(String accion) {
        if (transiciones.containsKey(estadoActual) && transiciones.get(estadoActual).containsKey(accion)) {
            estadoActual = transiciones.get(estadoActual).get(accion);
        }
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString("Estado Actual: " + estadoActual, 10, 30);
    }
}
