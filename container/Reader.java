package container;
import java.io.IOException;

import fileio.implementations.FileReader;
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
 * @author Alexandru Georgescu
 *
 */
public class Reader implements Constante {

      private FileReader scanner;
      private Panel panel;

      public Reader(final FileReader scanner) {
          this.scanner = scanner;
      }

      /**
       * Se citeste fundalul din interiorul inputului.
       * @param panel
       * @throws IOException
       */
      public void readCanvas(final Panel panell) throws IOException {
          this.panel = panell;
          panel.setCanvas(scanner.nextWord(), scanner.nextInt(),
                          scanner.nextInt(), scanner.nextWord(),
                          scanner.nextInt());
      }

      /**
       * Se citeste urmatorul cuvant din fiser apoi in functie de ce cuvant a
       * citit se creeaza un obiect de tipul acestuia.
       * Factory++
       * @throws IOException
       */
      public void readNext() throws IOException {
          String tip = scanner.nextWord();
          Factory maker = Factory.getFactory();
          Forma formaNoua = maker.getForm(tip, this);
          panel.addForm(formaNoua);
      }

      /**
       * Se citeste culoarea reprezentativa pentru fiecare obiect citit.
       * @param forma
       * @throws IOException
       */
      public void readColor(final Forma forma) throws IOException {
          forma.setColor(scanner.nextWord(), scanner.nextInt(),
                         scanner.nextWord(), scanner.nextInt());
      }

      /**
       * Se citeste un cerc din input.
       * @param cerc
       * @throws IOException
       */
       public void read(final Cerc cerc) throws IOException {
           cerc.set(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
           readColor(cerc);
       }

      /**
       * Se dreptungi un cerc din input.
       * @param dreptunghi
       * @throws IOException
       */
      public void read(final Dreptunghi dreptunghi) throws IOException {
          dreptunghi.set(scanner.nextInt(), scanner.nextInt(),
                         scanner.nextInt(), scanner.nextInt());
          readColor(dreptunghi);
      }

     /**
      * Se citeste o linie din input.
      * @param linie
      * @throws IOException
      */
      public void read(final Linie linie) throws IOException {
          linie.set(scanner.nextInt(), scanner.nextInt(),
                    scanner.nextInt(), scanner.nextInt());
          linie.setColor(scanner.nextWord(), scanner.nextInt(), 0, 0);
      }

     /**
      * Se citeste un patrat din input.
      * @param patrat
      * @throws IOException
      */
      public void read(final Patrat patrat) throws IOException {
          patrat.set(scanner.nextInt(), scanner.nextInt(),
                     scanner.nextInt());
          readColor(patrat);
      }

     /**
      * Se citeste un romb din input.
      * @param romb
      * @throws IOException
      */
      public void read(final Romb romb) throws IOException {
          romb.set(scanner.nextInt(), scanner.nextInt(),
                   scanner.nextInt(), scanner.nextInt());
          readColor(romb);
      }

     /**
      * Se citeste un triunghi din input.
      * @param triunghi
      * @throws IOException
      */
      public void read(final Triunghi triunghi) throws IOException {
          triunghi.set(scanner.nextInt(), scanner.nextInt(),
                       scanner.nextInt(), scanner.nextInt(),
                       scanner.nextInt(), scanner.nextInt());
          readColor(triunghi);
      }

     /**
      * Se citeste un Poligon din input.
      * Se citeste numarul de puncte apoi se incepe citirea efectiva
      * a fiecarui punct.
      * @param poligon
      * @throws IOException
      */
      public void read(final Poligon poligon) throws IOException {

          int nrPoligoane = scanner.nextInt();
          int[] x = new int[nrPoligoane];
          int[] y = new int[nrPoligoane];

          for (int i = 0; i < nrPoligoane; i++) {
              x[i] = scanner.nextInt();
              y[i] = scanner.nextInt();
          }

          poligon.set(nrPoligoane, x, y);
          readColor(poligon);
      }
}
