package container;

import java.io.IOException;

import forme.Cerc;
import forme.Dreptunghi;
import forme.Forma;
import forme.Linie;
import forme.Patrat;
import forme.Poligon;
import forme.Romb;
import forme.Triunghi;
import interfete.Constante;
/**
 * Factory
 * Aloca o noua forma in program.
 * @author Alexandru Georgescu
 */
public final class Factory implements Constante {

    private static Factory make = new Factory();

    private Factory() {
    }

    public static Factory getFactory() {
        return make;
    }

    public Forma getForm(final String tip, final Reader reader)
                         throws IOException {

        if (tip.equals(LINE)) {
            return new Linie(reader, tip);
         } else if (tip.equals(SQUARE)) {
            return new Patrat(reader, tip);
         } else if (tip.equals(RECTANGLE)) {
            return new Dreptunghi(reader, tip);
         } else if (tip.equals(CIRCLE)) {
            return new Cerc(reader, tip);
         } else if (tip.equals(TRIANGLE)) {
            return new Triunghi(reader, tip);
         } else if (tip.equals(DIAMOND)) {
            return new Romb(reader, tip);
         } else if (tip.equals(POLYGON)) {
            return new Poligon(reader, tip);
         } else {
            return null;
         }
    }
}
