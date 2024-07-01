import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelJuego extends JPanel {
    private Laberinto laberinto;
    private Personaje personaje;
    private Temporizador temporizador;
    private AFD afd;
    private Point puntoDeControl;
    private boolean reiniciarNivel;

    public PanelJuego() {
        inicializarNivel();

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) personaje.moverArriba(laberinto);
                if (key == KeyEvent.VK_DOWN) personaje.moverAbajo(laberinto);
                if (key == KeyEvent.VK_LEFT) personaje.moverIzquierda(laberinto);
                if (key == KeyEvent.VK_RIGHT) personaje.moverDerecha(laberinto);

                if (laberinto.esReloj(personaje.getX(), personaje.getY())) {
                    temporizador.agregarTiempo(5); // Añadir 5 segundos al tiempo
                    laberinto.removerReloj(personaje.getX(), personaje.getY());
                    afd.transitar("recogerReloj");
                }
                
                if (laberinto.esPuntoDeControl(personaje.getX(), personaje.getY())) {
                    puntoDeControl = new Point(personaje.getX(), personaje.getY());
                    laberinto.removerPuntoDeControl(personaje.getX(), personaje.getY());
                    afd.transitar("puntoDeControl");
                }else {
                    afd.transitar("mover");// Actualizar el estado del AFD
                }


                if (laberinto.esFin(personaje.getX(), personaje.getY())) {
                    afd.transitar("finDelNivel");
                    temporizador.pausar();
                    timer.stop();  // Detener el temporizador
                    mostrarMensaje("¡Felicitaciones runner, completaste el laberinto!");
                
                }

                repaint();
            }
        });

        Timer timer = new Timer(1000, e -> {
            temporizador.decrementar();
            if (temporizador.getTiempoRestante() == 0) {
                if (puntoDeControl != null) {
                    personaje.setPosition(puntoDeControl.x, puntoDeControl.y);
                } else {
                    inicializarNivel();
                }
                temporizador.reiniciar();
                mostrarMensaje("Game Over");
            }
            repaint();
        });
        timer.start();
    }

    private void inicializarNivel() {
        laberinto = new Laberinto(20, 20); // Generar un laberinto de 20x20
        personaje = new Personaje(1, 1); // Ubicación inicial del personaje
        temporizador = new Temporizador(40); // Tiempo inicial de 40 segundos
        afd = new AFD(); // Inicializar el AFD
        puntoDeControl = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        laberinto.dibujar(g);
        personaje.dibujar(g);
        temporizador.dibujar(g);
        afd.dibujar(g);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Encuentra el Camino");
        PanelJuego panelJuego = new PanelJuego();
        frame.add(panelJuego);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
