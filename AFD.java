
public class AFD {
    private final int [][] tablaTransiciones;
    this.estadoActual = 0;

public AFD( int [][] tablaTransiciones) {
    this.tablaTransiciones = tablaTransiciones;
    this.estadoActual = 0;
    }
public void transicionar(int entrada){
    estadoActual = tablaTransiciones[estadoActual][entrada];
}
public void dibujar (Graphics g){
    //Dibujar los estados y transiciones del AFD
    g.setColor(Color.RED);
    g.drawString("Estado actual: " + estadoActual, 10, 20);
    }
}
