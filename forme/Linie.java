package forme;

import java.io.IOException;

import container.ColorLevel;
import container.Panel;
import container.Pixel;
import container.Reader;

public class Linie extends Forma {

    private Pixel startPoint;
    private Pixel finalPoint;
    private ColorLevel contur;

    public Linie(final Reader reader, final String tipForma)
                 throws IOException {
        super(tipForma);
        accept(reader);
    }

    /**
     * Se seteaza coordonatele liniei.
     * @param xStart
     * @param yStart
     * @param xFinal
     * @param yFinal
     */
    public void set(final int xStart, final int yStart, final int xFinal,
                    final int yFinal) {
        this.startPoint = new Pixel(xStart, yStart);
        this.finalPoint = new Pixel(xFinal, yFinal);
    }

    /**
     * Se returneaza conturul liniei.
     */
    public ColorLevel getContur() {
        return this.contur;
    }

    /**
     * Se seteaza culoarea pentru linie.
     * @param culoare
     * @param opacitate
     * @param dont
     * @param need
     */
    public void setColor(final String culoare, final int opacitate,
                         final int dont, final int need) {
        this.contur = new ColorLevel(culoare, opacitate);
    }

    /**
     * Se trage linie din punctul start in punctul final cu culoarea care se
     * gaseste in contur.
     * @param Panel
     */
    public void draw(final Panel panel) {
        panel.drawLine(startPoint, finalPoint, contur);
    }

    /**
     * Metoda prin care se incepe citirea unei linii atunci cand se apeleaza
     * constructorul liniei.
     * @param reader
     */
    public void accept(final Reader reader) throws IOException {
        reader.read(this);
    }
}
