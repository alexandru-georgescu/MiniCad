package forme;


import java.io.IOException;

import container.Panel;
import container.Pixel;
import container.Reader;

public class Poligon extends Forma {

    private Pixel[] puncte;
    private int nrPuncte;

    public Poligon(final Reader reader, final String tipForma)
                   throws IOException {
        super(tipForma);
        accept(reader);
    }

    /**
     * Se seteaza un vector de puncte pentru reprezentarea poigonuluii.
     * @param nrPuncte
     * @param xPoint
     * @param yPoint
     */
     public void set(final int nrPunct, final int[] xPoint,
                     final int[] yPoint) {

        this.nrPuncte = nrPunct;
        puncte = new Pixel[nrPuncte];

        for (int i = 0; i < nrPuncte; i++) {
            puncte[i] = new Pixel(xPoint[i], yPoint[i]);
        }
     }

    /**
     * Se trage din fiecare punct in urmatorul punct care urmeaza o linie
     * pentru a genera conturul polinomului, apoi se calculeaza centru de
     * greutate cu ajutorul caruia o sa deseneze interiorul poligonului,
     * se trimite ca punct de incepere centru de greutate, urmand ca
     * poligonul sa fie desenat.
     * @param panel
     */
    public void draw(final Panel panel) {

        for (int i = 0; i < nrPuncte - 1; i++) {
            panel.drawLine(puncte[i], puncte[i + 1], super.getContur());
        }
        panel.drawLine(puncte[nrPuncte - 1], puncte[0], super.getContur());

        int interior = panel.getColor(super.getInterior().getColor(),
                                      super.getInterior().getOpacity());

        int contur = panel.getColor(super.getContur().getColor(),
                     super.getContur().getOpacity());

        int x = 0;
        int y = 0;

        for (int i = 0; i < nrPuncte; i++) {
            x += puncte[i].getCoordX();
            y += puncte[i].getCoordY();
        }
        x = Math.round(x / nrPuncte);
        y = Math.round(y / nrPuncte);
        panel.floodFill(new Pixel(x, y), interior, contur);
     }

    /**
     * Metoda prin care se incepe citirea unui poligonului atunci
     * cand se apeleaza constructorul poligonului.
     * @param reader
     */
    public void accept(final Reader reader) throws IOException {
        reader.read(this);
    }
}
