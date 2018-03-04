package container;
/**
 * Canvas reprezinta clasa care stocheaza tipul fundalului si are
 * propitatea de a se apela o singura data pe durata executiei.
 * @author Alexandru Georgescu
 */
public final class Canvas {

    private int[][] image;
    private static Canvas instance = new Canvas();

    private Canvas() {
    }

    public static Canvas getCanvas() {
        return instance;
    }

    public void setImage(final int inaltime, final int lungime) {
        image = new int[inaltime][lungime];
    }

    public int[][] getImage() {
        return image;
    }
}
