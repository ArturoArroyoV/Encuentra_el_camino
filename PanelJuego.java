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
        if (puntoDeControl != null) {
            laberinto.setPuntoDeControl(puntoDeControl);
            } else {
            puntoDeControl = laberinto.generarPuntoDeControl();
            }
        if(timer != null) {
        time.restart();
            }
    }

    private void mostrarMensaje(String mensaje) {
        int opcion = JOptionPane.showOptionDialog(this, mensaje, "Mensaje", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, new Object[]{"Reiniciar", "Rendirse"}, JOptionPane.YES_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            inicializarNivel();
            } else {
            System.exit(0);
            }
        }
    private void actualizarVision(){
        int px = personaje.getX();
        int py = personaje.getY();
        laberinto.marcarVisitada(px,py);
        for(int i =-2; i<=2; i++) {
            for ( int j = -2; j<=2 ; j++) {
                if(px + i >= 0 && px + i < laberinto.getFilas() && py + j >= 0 && py + j < laberinto.getColumnas()){
                    laberinto.marcarVisitada (px + i, py + j);
                    }
                }
            }
        }
                                                  
            

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        actualizarVision();
        laberinto.dibujar(g, personaje.getX(), personaje.getY());
        personaje.dibujar(g);
        // Mover la posición de los textos del AFD y el temporizador
        temporizador.dibujar(g, 400, 50);
        afd.dibujar(g, 400, 30);
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
