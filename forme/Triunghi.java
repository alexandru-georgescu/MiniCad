package forme;

import java.io.IOException;

import container.Panel;
import container.Pixel;
import container.Reader;
import interfete.Constante;

public class Triunghi extends Forma implements Constante {

    private Pixel firstPoint;
    private Pixel secondPoint;
    private Pixel thirdPoint;

    public Triunghi(final Reader reader, final String tipForma)
                    throws IOException {
        super(tipForma);
        accept(reader);
    }

    /**
     * Se seteaza un triungi, se citeste fiecare punct al acestuia.
     * @param xF = coordoana x a primului punct
     * @param yF = y ..
     * @param xS = coordonata x al punctului doi..
     * @param yS = etc..
     * @param xT
     * @param yT
     */
     public void set(final int xF, final int yF, final int xS, final int yS,
                     final int xT, final int yT) {

        this.firstPoint = new Pixel(xF, yF);
        this.secondPoint = new Pixel(xS, yS);
        this.thirdPoint = new Pixel(xT, yT);
     }

    /**
     * Se trage cate o linie dintr-un punct in puctul urmator pentru a se
     * realiza conturul iar apoi se deseneaza triunghiu propriu zis.
     * @param da
     */
    public void draw(final Panel panel) {

        panel.drawLine(firstPoint, secondPoint, super.getContur());
        panel.drawLine(secondPoint, thirdPoint, super.getContur());
        panel.drawLine(thirdPoint, firstPoint, super.getContur());

        int interior = panel.getColor(super.getInterior().getColor(),
                                      super.getInterior().getOpacity());

        int contur = panel.getColor(super.getContur().getColor(),
                                    super.getContur().getOpacity());

        int x = Math.round((firstPoint.getCoordX() + secondPoint.getCoordX()
                          + thirdPoint.getCoordX()) / GRAD3);

        int y = Math.round((firstPoint.getCoordY() + secondPoint.getCoordY()
                          + thirdPoint.getCoordY()) / GRAD3);

        panel.floodFill(new Pixel(x, y), interior, contur);
    }

    /**
     * Metoda prin care se incepe citirea unui triunghi atunci cand se
     * apeleaza constructorul triunghiului.
     * @param reader
     */
    public void accept(final Reader reader) throws IOException {
        reader.read(this);
    }
}
