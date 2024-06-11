
public class RelojTiempo {
    
    private int x,y;
    private final int TAMANO = 20;

    public RelojTiempo(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void dibujar (Graphics g) {
        g.setColor (Color.GREEN);
        g.fillRect(x,y,TAMANO,TAMANO)
            }
    public Rectangle getBounds() {
        return new Rectangle (x,y,TAMANO, TAMANO)
        }
    
    
}
