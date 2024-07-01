import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Clase que representa un Autómata Finito Determinista (AFD)
public class AFD {
    private String estadoActual;
    private Map<String, Map<String, String>> transiciones;

    // Constructor que inicializa el AFD en el estado inicial "Q0" y define las transiciones
    public AFD() {
        estadoActual = "Q0"; // Estado inicial
        transiciones = new HashMap<>(); // Mapa para almacenar las transiciones
        definirTransiciones(); // Método para definir las transiciones
    }

    // Método para definir las transiciones del AFD
    private void definirTransiciones() {
        agregarTransicion("Q0", "mover", "Q1");
        agregarTransicion("Q1", "mover", "Q1");
        agregarTransicion("Q1", "finDelNivel", "Q4");
        agregarTransicion("Q1", "recogerReloj", "Q2");
        agregarTransicion("Q1", "puntoDeControl", "Q3");
        agregarTransicion("Q2", "puntoDeControl", "Q3");
        agregarTransicion("Q3", "finDelNivel", "Q4");
        agregarTransicion("Q4", "nuevoNivel", "Q0");
    }

    // Método para agregar una transición al AFD
    private void agregarTransicion(String estadoActual, String accion, String nuevoEstado) {
        transiciones.putIfAbsent(estadoActual, new HashMap<>()); // Añade un nuevo estado si no existe
        transiciones.get(estadoActual).put(accion, nuevoEstado); // Añade la transición al estado actual
    }

    // Método para realizar una transición según una acción
    public void transitar(String accion) {
        // Verifica si existe una transición para la acción en el estado actual
        if (transiciones.containsKey(estadoActual) && transiciones.get(estadoActual).containsKey(accion)) {
            estadoActual = transiciones.get(estadoActual).get(accion); // Realiza la transición al nuevo estado
        }
    }

    // Método para dibujar el estado actual del AFD en el panel de juego
    public void dibujar(Graphics g, int x, int y) {
        g.setColor(Color.RED); // Establece el color del texto
        g.drawString("Estado Actual: " + estadoActual, x, y); // Dibuja el estado actual en la posición especificada
    }
}



