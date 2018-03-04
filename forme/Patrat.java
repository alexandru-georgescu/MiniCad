package forme;

import java.io.IOException;

import container.Panel;
import container.Pixel;
import container.Reader;
import interfete.Constante;

public class Patrat extends Forma implements Constante {

    private Pixel startPoint;
    private int dimensiune;

    public Patrat(final Reader reader, final String tipForma)
                  throws IOException {
        super(tipForma);
        accept(reader);
    }

    /**
     * Se seteaza punctul de incepere a patratului cat si dimensiunea laturii.
     * @param xStart
     * @param yStart
     * @param dimensiune
     */
    public void set(final int xStart, final int yStart, final int dimen) {
        this.startPoint = new Pixel(xStart, yStart);
        this.dimensiune = dimen;
    }

    /**
     * Se deseneaza un patrat, in prim pas se formeaza colturile pe care
     * trebuie sa le aibe(punctele) apoi se traseaza patratul urmand sa fie
     * colorat in final.
     * @param Panel
     */
    public void draw(final Panel panel) {

        Pixel dreaptaSus = new Pixel(this.startPoint.getCoordX()
                                   + dimensiune - GRAD1,
                                     this.startPoint.getCoordY());

        Pixel stangaJos = new Pixel(this.startPoint.getCoordX(),
                                    this.startPoint.getCoordY()
                                  + dimensiune - GRAD1);

        Pixel dreaptaJos = new Pixel(this.startPoint.getCoordX()
                                   + dimensiune - GRAD1,
                                     this.startPoint.getCoordY()
                                   + dimensiune - GRAD1);

        panel.drawLine(startPoint, dreaptaSus, super.getContur());
        panel.drawLine(dreaptaSus, dreaptaJos, super.getContur());
        panel.drawLine(startPoint, stangaJos, super.getContur());
        panel.drawLine(stangaJos, dreaptaJos, super.getContur());

        Pixel save = new Pixel(startPoint.getCoordX() + GRAD2,
                               startPoint.getCoordY() + GRAD3);
        int interior = panel.getColor(super.getInterior().getColor(),
                 super.getInterior().getOpacity());

        int contur = panel.getColor(super.getContur().getColor(),
                 super.getContur().getOpacity());

        panel.floodFill(save, interior, contur);
    }

    /**
     * Metoda prin care se incepe citirea unui patrat atunci cand se apeleaza
     * constructorul patratului.
     * @param reader
     */
    public void accept(final Reader reader) throws IOException {
        reader.read(this);
    }
}
