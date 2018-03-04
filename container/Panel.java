package container;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.imageio.ImageIO;
import forme.Forma;
import interfete.Constante;
/**
 * Panel este clasa principala a proiectului, contine un vector de fome unde
 * salvez formele primite ca input pentru a le putea apela mai tarziu, aici
 * se contureaza cat si deseneaza imaginea propriu zisa.
 * @author Alexandru Georgescu
 *
 */
public class Panel implements Constante {

     private ArrayList<Forma> figuri;
     private BufferedImage imag;
     private int[][] panel;
     private String numeForma;
     private String culoare;
     private int color;
     private int opacitate;
     private int numarForme;
     private int inaltime;
     private int latime;

     public Panel(final int numarForme) {
         this.numarForme = numarForme;
         this.figuri = new ArrayList<Forma>();
     }

     public final int getInaltime() {
         return this.inaltime;
     }

     public final int getLatime() {
         return this.latime;
     }

     /**
      * @param reader: scannerul din fisier
      * Se citeste pe rand din fisier apeland metoda readNext care
      * citeste din fisier si indentifica de tip de obiect este si il
      * parseaza la implementarea respectiva, dupa citirea tuturor
      * obiectelor se trece la desenarea cat si afisearea lor.
      * @throws IOException
      */
     public void startRead(final Reader reader) throws IOException {

         for (int i = 0; i < (this.numarForme - 1); i++) {
             reader.readNext();
         }

         for (int i = 0; i < (this.numarForme - 1); i++) {
             figuri.get(i).draw(this);
             createImage();
         }
     }

     /**
      * Se seteaza fundalul propriu zis al imaginii si se initializeaza
      * matricea de pixeli la culoarea acestuia.
      * @param numeForma
      * @param latime
      * @param inaltime
      * @param culoare
      * @param opacitate
      * @throws IOException
      */
     public void setCanvas(final String numeForm, final int latim,
                           final int inaltim, final String culoar,
                           final int opacitat) throws IOException {

         this.numeForma = numeForm;
         this.inaltime = inaltim;
         this.latime = latim;
         this.culoare = culoar;
         this.opacitate = opacitat;

         Canvas canvas = Canvas.getCanvas();
         canvas.setImage(inaltime, latime);
         this.panel = canvas.getImage();

         BufferedImage picture = new BufferedImage(inaltime, latime,
                                  BufferedImage.TYPE_INT_ARGB);

         this.color = getColor(culoare, opacitate);

         for (int i = 0; i < inaltime; i++) {
             for (int j = 0; j < latime; j++) {
                  panel[i][j] = getColor(culoare, opacitate);
             }
         }
         this.imag = picture;
         createImage();
     }

     /**
      * Se proceseaza imaginea in functie de matricea de pixeli.
      * Cum imaginea este procesata invers fata de matrice se interschimba
      * I cu J in imagine pentru a contura imaginea dorita. Apoi se salveaza
      * imaginea in formatul png cu numele drawing.
      * @throws IOException
      */
     public void createImage() throws IOException {

         for (int i = 0; i < inaltime; i++) {
           for (int j = 0; j < latime; j++) {
               imag.setRGB(i, j, panel[i][j]);
           }
         }

        File output = new File("drawing.png");
        ImageIO.write(imag, "png", output);
     }

     /**
      * Primeste ca parametru un string care in primul pas este decodat
      * pentru a genera culoarea acestuia apoi se adauga peste el opacitatea
      * si se returneaza culoarea pixelului in int.
      * @param color
      * @param opacity
      * @return
      */
      public int getColor(final String colorr, final int opacity) {

         Color saveC = Color.decode(colorr);
         Color convertC = new Color(saveC.getRed(), saveC.getGreen(),
                                    saveC.getBlue(), opacity);
         return convertC.getRGB();
     }

     /**
      * Algoritmului lui Bresenham de desenat linii.
      * Determina punctele care ar trebui selectate pentru a forma o linie
      * cat mai apropiata de o linie dreapta intre doua puncte ale unui raster.
      * Se stabileste punctul de plecare apoi se calculeaza o dinstanta dintre
      * punct si punctul de final apoi pentru fiecare pas de la 0 la distanta
      * respectiva se proceseaza ce-a mai buna trasare a urmatorului pixel
      * pentru ca sa rezulte linia corespunzatoare.
      * @param start
      * @param finish
      * @param culoare
      */
     public void drawLine(final Pixel start, final Pixel finish,
                          final ColorLevel colorr) {
          boolean test = false;
          int x = start.getCoordX();
          int y = start.getCoordY();
          int deltaX = Math.abs(finish.getCoordX() - start.getCoordX());
          int deltaY = Math.abs(finish.getCoordY() - start.getCoordY());
          int sign1 = sign(finish.getCoordX() - start.getCoordX());
          int sign2 = sign(finish.getCoordY() - start.getCoordY());
          int error;

          if (deltaY > deltaX) {
              int aux = deltaY;
              deltaY = deltaX;
              deltaX = aux;
              test = true;
          }
          error = 2 * deltaY - deltaX;

          for (int i = 0; i <= deltaX; i++) {
            if (x >= 0 && y >= 0 && x < inaltime && y < latime) {
               panel[x][y] = getColor(colorr.getColor(), colorr.getOpacity());
            }

           while (error > 0) {

                if (test) {
                   x += sign1;
                } else {
                   y += sign2;
                }
                error -= GRAD2 * deltaX;
            }

            if (test) {
               y += sign2;
            } else {
               x += sign1;
            }

            error += GRAD2 * deltaY;
          }
     }

     /**
      * Algoritmului lui Bresenham de desenat cercuri.
      * Se selecteaza un punct initial apoi se adauga o marsa de eroare de
      * tipup 3 - 2 * raza pentru o aproximare cat mai buna, apoi cat timp
      * valoarea dintre saveQ si saveP nu este mai mica se creste coordonatele
      * pixelilior saveP sau respectiv se scade saveQ pentru a genera un contur
      * de cerc in functie de eroare, eroarea ajuta la verificarea daca pixelul
      * trebuie pus mai jos sau mai sus, la fiecare iteratie se deseneaza
      * 8 pixeli 1 in fiecare jumatate de cadran.
      * @param point
      * @param raza
      * @param culoare
      * @param interior
      */
     public void drawCircle(final Pixel point, final int raza,
                            final ColorLevel colorr,
                            final ColorLevel interior) {
         int saveQ = raza;
         int saveP = 0;
         int error = GRAD3 - (GRAD2 * raza);
         int xInit = point.getCoordX();
         int yInit = point.getCoordY();

         while (saveP < saveQ) {
            drawPixels(xInit, yInit, saveP, saveQ, colorr);
            saveP++;

            if (error  <  0) {
               error += GRAD4 * saveP + GRAD6;
            } else {
               saveQ--;
               error += GRAD4 * (saveP - saveQ) + GRAD10;
            }
            drawPixels(xInit, yInit, saveP, saveQ, colorr);
         }
     }

     /**
      * Meotda puplimentara pentru conturul cercului, deseneaza culoarea collor
      * in fiecare jumatate de cadran a cerculului.
      */
     public void drawPixels(final int xInit, final int yInit, final int x,
                            final int y, final ColorLevel colorr) {

        conditii(xInit + x, yInit + y, colorr);
        conditii(xInit + y, yInit + x, colorr);
        conditii(xInit - y, yInit + x, colorr);
        conditii(xInit - x, yInit + y, colorr);
        conditii(xInit - x, yInit - y, colorr);
        conditii(xInit - y, yInit - x, colorr);
        conditii(xInit + y, yInit - x, colorr);
        conditii(xInit + x, yInit - y, colorr);
     }

     /**
      * Verifica daca 2 coordonate date cond1 si cond2 se afla in interiorul
      * imaginii, daca da atunci se suprascrie culoarea dorita in pixelul
      * respectiv.
      * @param cond1
      * @param cond2
      * @param culoare
      */
     public void conditii(final int cond1, final int cond2,
                          final ColorLevel colorr) {

        if (cond1 >= 0 && cond1 < inaltime  && cond2 >= 0 && cond2 < latime) {
           panel[cond1][cond2] = getColor(colorr.getColor(),
                                          colorr.getOpacity());
        }
     }

     /**
      * Metoda floodfill impementat cu o coada se bazeaza pe faptul ca folosind
      * o coada nu o sa mai folosim ineficient memoria si este mai greu sa o
      * depasim, in prim pas se baga pixelul primit ca parametru in stiva apoi
      * se intra in bucla cat timp coada nu este goala se scoate din coada ele-
      * mentul care urmeaza se verifica daca se afla pe matricea de pixeli
      * daca da atunci se coloreaza in culoarea care trebui iar apoi se intro-
      * duce in stiva pixelii care il inconjoara sus, jos, dreapta, stanga.
      * La final rezulta un array de pixeli colorati in functie de delimita-
      * tori si culori.
      * @param start
      * @param interior
      * @param contur
      */
     public void floodFill(final Pixel start, final int interior,
                           final int contur) {

        Queue<Pixel> coada = new LinkedList<Pixel>();
        coada.add(start);
        Pixel save;
        int x = start.getCoordX();
        int y = start.getCoordY();

        while (!coada.isEmpty()) {
           save = coada.remove();
           x = save.getCoordX();
           y = save.getCoordY();

           if (x >= 0 && y >= 0 && x < inaltime && y < latime) {
             if (panel[x][y] != contur && panel[x][y] != interior) {

                 panel[x][y] = interior;
                 coada.add(new Pixel(x - 1, y));
                 coada.add(new Pixel(x + 1, y));
                 coada.add(new Pixel(x, y - 1));
                 coada.add(new Pixel(x, y + 1));
             }
           }
         }
     }

     /**
      * Primeste ca parametru un numar, daca acesta este negativ intoarce -1
      * daca este pozitiv intoarce 1 iar daca este 0 intoarce 0.
      * As fi putut folosi metoda de sign din Integer dar nu m-am gandit si am
      * zis ca daca tot am scris si aici o metoda sa o las asa.
      * @param number
      * @return
      */
     public int sign(final int number) {
         if (number < 0) {
             return -1;
          } else if (number > 0) {
             return 1;
          } else {
             return 0;
          }
     }
     /**
      * Adauga o forma noua in vectorul de figuri.
      * @param formaNoua
      */
     public void addForm(final Forma formaNoua) {
         this.figuri.add(formaNoua);
     }

}
