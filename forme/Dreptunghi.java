package forme;

import java.io.IOException;

import container.Panel;
import container.Pixel;
import container.Reader;
import interfete.Constante;

public class Dreptunghi extends Forma implements Constante {

    private Pixel startPoint;;
    private int dimensiuneInaltime;
    private int dimensiuneLatime;

    public Dreptunghi(final Reader reader, final String tipForma)
                      throws IOException {
        super(tipForma);
        accept(reader);
    }

    /**
     * Se seteaza dreptunghiul(lungimea, latimea, coordonatele de start).
     * @param xStart
     * @param yStart
     * @param dimensiuneI
     * @param dimensiuneL
     */
    public void set(final int xStart, final int yStart, final int dimensiuneI,
                    final int dimensiuneL) {

        this.startPoint = new Pixel(xStart, yStart);
        this.dimensiuneInaltime = dimensiuneI;
        this.dimensiuneLatime = dimensiuneL;
    }

    /**
     * Se deseneaza un dreptunghi, in prim pas se formeaza colturile pe care
     * trebuie sa le aibe(punctele) apoi se traseaza dreptunghiul urmand sa fie
     * colorat in final.
     * Stiu ca se vede urat, dar avem limita de 80 de caractere..
     * @param panel
     */
    public void draw(final Panel panel) {

       Pixel dreaptaSus = new Pixel(startPoint.getCoordX()
                                  + dimensiuneLatime - GRAD1,
                                    startPoint.getCoordY());

       Pixel stangaJos = new Pixel(startPoint.getCoordX(),
                                   startPoint.getCoordY()
                                 + dimensiuneInaltime - GRAD1);

       Pixel dreaptaJos = new Pixel(startPoint.getCoordX()
                                 +  dimensiuneLatime - GRAD1,
                                    startPoint.getCoordY()
                                 + dimensiuneInaltime - GRAD1);

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
     * Metoda prin care se incepe citirea unui dreptunghi atunci cand se
     * apeleaza constructorul dreptunghiului.
     * @param reader
     */
    public void accept(final Reader reader) throws IOException {
        reader.read(this);
    }
}
