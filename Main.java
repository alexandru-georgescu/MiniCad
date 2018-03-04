import java.io.IOException;

import container.Panel;
import container.Reader;
import fileio.implementations.FileReader;
/**
 * Punctul de intrare in program, se initializeaza un obiect de scanare
 * si se initializeaza programul in sine, se incepe citirea si apoi se
 * executa printarea formelor.
 * @author Alexandru Georgescu
 *
 */
public final class Main {
    private  Main() {
    }

    public static void main(final String[] args) throws IOException {

        FileReader scan = new FileReader(args[0]);
        Reader reader = new Reader(scan);
        Panel panou = new Panel(scan.nextInt());
        reader.readCanvas(panou);
        panou.startRead(reader);
    }
}
