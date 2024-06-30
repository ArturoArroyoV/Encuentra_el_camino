public class Main {
        public static void main(String[] args) {
        char[][] laberinto = {
            // Cargar el laberinto desde un archivo
        };
        JFrame frame = new JFrame("Juego del Laberinto");
        JuegoLaberinto juego = new JuegoLaberinto(laberinto);
        frame.add(juego);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}