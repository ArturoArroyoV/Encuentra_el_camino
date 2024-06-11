public class VisualizadorDFA {
    private int estadoActual;

    public VisualizadorDFA() {
        this.estadoActual = 0;  // Estado inicial
    }

    public void actualizar(int x, int y) {
        if (x % 100 == 0 || y % 100 == 0) {
            estadoActual = (estadoActual + 1) % 5;  // Ciclo simple de estados
        }
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.drawString("Estado DFA: " + estadoActual, 10, 10);
    }
}