package forme;

import java.io.IOException;
import container.ColorLevel;
import container.Panel;
import container.Reader;
/**
 * Clasa care contureaza orice forma pe care trebuie sa o implementa.
 * Ajuta ca sa nu am cod dublicat + suprascrieri.
 * Am preferat sa nu folosesc interfete pentru ca am utilitati mult mai
 * bune prin extindere.
 * HINT: Puteam sa folosesc forma poligon ca sa conturez orice alta forma
 * in afara de cerc dar am zis sa nu implic fiecare clasa in poligon, in
 * caz de vreau sa extind ceva la forma respectiva ea poate sa fie
 * independenta.
 * @author Alexandru Georgescu
 *
 */
public class Forma {

    private String tipForma;
    private ColorLevel contur;
    private ColorLevel interior;

    public Forma(final String tipForma) {
        this.tipForma = tipForma;
    }

    /** @category
     * Returneaza tipul formei
     * @param culoareC
     */
    public String getForma() {
        return this.tipForma;
    }

    /**
     * Returneaza pixelul de culoare al conturului.
     * @param culoareC
     */
    public ColorLevel getContur() {
        return this.contur;
    }

    /**
     * Retruneaza pixelul de culoare al interiorului.
     * @param culoareC
     */
    public ColorLevel getInterior() {
     return this.interior;
    }

    /**
     * Seteaza culoarea conturului si interiorului pentru fiecare figura in
     * parte.
     * @param culoareC
     * @param opacitateC
     * @param culoareI
     * @param opacitateI
     */
    public void setColor(final String culoareC, final int opacitateC,
                         final String culoareI, final int opacitateI) {

        this.contur = new ColorLevel(culoareC, opacitateC);
        this.interior = new ColorLevel(culoareI, opacitateI);
    }

    /**
     * Suprascriere pentru a putea apela metoda draw din fiecare obiect fara
     * a mai casta.
     * @param culoareC
     */
    public void draw(final Panel panel) {
    }

    /**
     * Suprascriere pentru a putea apela metoda accept din fiecare obiect fara
     * a mai casta.
     * @param culoareC
     */
    public void accept(final Reader reader) throws IOException {
    }
}
