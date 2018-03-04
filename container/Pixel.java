package container;
/**
 * Clasa in care se salveaza un punct de coordonate x, y.
 * Ajuta la utilizarea mult mai compacta si rapida a coordonatelor.
 * @author Alexandru Georgescu
 *
 */
public class Pixel {

    private int coordX;
    private int coordY;

    public Pixel(final int coordX, final int coordY) {
       this.coordX = coordX;
       this.coordY = coordY;
    }

    public final int getCoordX() {
        return this.coordX;
    }

    public final int getCoordY() {
        return this.coordY;
    }
}
