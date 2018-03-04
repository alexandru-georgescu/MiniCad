package forme;

import java.io.IOException;

import container.Panel;
import container.Pixel;
import container.Reader;

public class Romb extends Forma {

    private Pixel centerPoint;
    private int dimensiuneOriz;
    private int dimensiuneVert;

    public Romb(final Reader reader, final String tipForma)
                throws IOException {
        super(tipForma);
        accept(reader);
    }

    /**
     * Se seteaza un rob, se salveaza punctul din centru cat si dimensiunea
     * orizontalei si a verticalei.
     * @param xCenter
     * @param yCenter
     * @param dimO
     * @param dimV
     */
    public void set(final int xCenter, final int yCenter, final int dimO,
                    final int dimV) {
        this.centerPoint = new Pixel(xCenter, yCenter);
        this.dimensiuneOriz = dimO;
        this.dimensiuneVert = dimV;
    }

    /**
     * Se calculeaza punctele rombului cu ajutorul jumatatii din orizonatala
     * respectiv veriticala, se traseaza linii din punct in punct pentru a
     * forma conturul iar apoi se culoareaza rombul propriu zis.
     * @param xCenter
     */
    public void draw(final Panel panel) {

        int oriz = Math.round(dimensiuneOriz / 2);
        int vert = Math.round(dimensiuneVert / 2);

        Pixel colt1 = new Pixel(centerPoint.getCoordX(),
                                centerPoint.getCoordY() + vert);
        Pixel colt2 = new Pixel(centerPoint.getCoordX() + oriz,
                                centerPoint.getCoordY());
        Pixel colt3 = new Pixel(centerPoint.getCoordX(),
                                centerPoint.getCoordY() - vert);
        Pixel colt4 = new Pixel(centerPoint.getCoordX() - oriz,
                                centerPoint.getCoordY());

        panel.drawLine(colt1, colt2, super.getContur());
        panel.drawLine(colt2, colt3, super.getContur());
        panel.drawLine(colt3, colt4, super.getContur());
        panel.drawLine(colt4, colt1, super.getContur());

        int interior = panel.getColor(super.getInterior().getColor(),
                                      super.getInterior().getOpacity());

        int contur = panel.getColor(super.getContur().getColor(),
                                    super.getContur().getOpacity());
        panel.floodFill(centerPoint, interior, contur);
    }

    /**
     * Metoda prin care se incepe citirea unui romb atunci
     * cand se apeleaza constructorul rombului.
     * @param reader
     */
    public void accept(final Reader reader) throws IOException {
        reader.read(this);
    }
}
