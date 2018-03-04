package forme;

import java.io.IOException;

import container.Panel;
import container.Pixel;
import container.Reader;

public class Cerc extends Forma {

   private Pixel centerPoint;
   private int raza;

   public Cerc(final Reader reader, final String tipForma) throws IOException {
        super(tipForma);
        accept(reader);
   }

    /**
     * Se seteaza centrul si raza cercului.
     * @param xCentru
     * @param yCentru
     * @param raza
     */
    public void set(final int xCentru, final int yCentru, final int raz) {
        this.centerPoint = new Pixel(xCentru, yCentru);
        this.raza = raz;
    }

    /** @cerc
     * Se deseneaza un cerc, se conturaza cercul in prim pas apoi urmand sa fie
     * desenat, se trimite ca parametrii culoriile stocate ca int
     * pe care vrem sa le aibe cercul (contur/interior).
     * @param panel
     */
    public void draw(final Panel panel) {
        panel.drawCircle(centerPoint, raza, super.getContur(),
                         super.getInterior());
        int interior = panel.getColor(super.getInterior().getColor(),
                                      super.getInterior().getOpacity());

        int contur = panel.getColor(super.getContur().getColor(),
                                    super.getContur().getOpacity());

        panel.floodFill(centerPoint, interior, contur);
    }

    /**
     * Metoda prin care se incepe citirea unui cerc atunci cand se apeleaza
     * constructorul cercului.
     * @param reader
     */
    public void accept(final Reader reader) throws IOException {
        reader.read(this);
    }
}
