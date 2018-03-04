===============================IMPLEMENTARE====================================
===============================================================================
================================STRUCTURA======================================
Componente: 3 pachete + 1 default(main)
  - Container: 
      - Cavas: clasa in care se formeaza fundalul imaginii.
      - ColorLevel: clasa in care se stocheaza un pixel(culoare+opacitate).
      - Panel: clasa "principala" a programului, contine functionalitatea 
       intregului program. Metoda de drawLine, initializare etc..
      - Pixel: clasa care stocheaza pozitia unui pixel forma: (x, y).
      - Reader: clasa care citeste fiecare obiect si parseaza fiecare citire
       in functie de tipul obiectului.
  - Forme:
     - Reprezinta incapsularea fiecararei figuri gemetrice cat si modalitatea
      de construire a cestora.
      - Cerc
      - Dreptunghi
      - Linie
      - Patrat
      - Romb
      - Triunghi
     - Reprezinta clasa care tine toate obiectele, retine elementele comune pe
      care le are orice forma: culoare, contur, etc + metode care sunt supra-
      scrise in figurile geometrice pentru a nu mai fi nevoie de castare la un
      anumit tip de obiect, castul facanduse automat.
      - Forma
  - Interfete:
      - Constate: reprezinta multimea constantelor de tip string si numeric,
       ajuta a buna organizare a datelor, cat si schimbarea lor foarte rapida.
=================================STRUCTURA=====================================
===============================================================================
==============================FUNCTIONALITATE==================================
  - La rularea programului se initializeaza un obiect de tip Reader pentru ci-
  tirea din fisier, se incepe functionalitatea, se citeste numarul de obiecte
  apoi canvas care este mereu pe a doua linie deci reprezinta o citire standard.
  In pasul urmator se citeste urmatorul cuvant din fisier si cu ajutorul lui
  se ia decizia in care se alege ce tip de obiect este, de exp daca citeste
  cerc atunci o sa intializeze un cerc si citirea lui, apoi se salveaza intr-un
  vector de figuri, dupa citirea fiecarei figuri se incepe parcurgerea figuri-
  lor.
   - Atunci cand o figura este selectata pentru a fi desenata se apeleaza 
   metoda draw a fiecarei figuri care reprezinta implementarea pentru figura 
   respectiva, de exemplu daca urmeaza desenam un patrat atunci se vor forma
   cele 4 colturi, avem deja unul deci se creeaza restul de 3, daca am avea 
   un punct (10,10) atunci coltul din dreapta o sa vina (10 + latura, 10) etc.
   se face asta pentru fiecare punct, dupa ce am creat punctele se apeleaza
   din punct in urmatorul punct metoda din panel de drawLine care trage o linie
   dintr-un punct in celalat, implementand metoda lui Bresenham care isi
   construieste linia in functie de niste erori, am observat ca poate fi putin
   eronata, deoarece nu este o eroare standard si sunt alti algoritmi care
   au alta eroare(am incercat sa implementez si alti 3 algoritmi si am obsevat
   ca erau usor diferiti(1-2pix). Respectiv pentru trasarea cercului am folosit
   tot metoda lui Bresenham, care se folosete de erori si aproximari in functie
   de cadranele cercului, etc.
   - Dupa ce conturul figurii a fost realizat se trece la partea de desenare, 
   pentru fiecare figura in parte se transmite centrul acesteia in afara de
   patrat si dreptunghi, acolo nu aveam nevoie de centru pentru ca stiam coltul
   cel mai din stanga deci cu o adaugare minima la punctul respectiv gaseam
   o pozitie in el, deci ceva mult mai simplist, oarecum benefic.
   - Algoritmul de colorare: Flood fill, ala pe care-l face toata lumea,
   am ales varianta cu o coada pentru ca m-am gandit ca daca foloseam varianta
   recursiva(1. unde era distractia, 2. sigur iesea din memorie, pentru ca da
   stiva nu prea vrea, stiva primeste prea multe, stiva da pe afara, java face
   urat, segmentation fault.. etc :)) ), bun si trimit ca parametru un punct
   din interiorul figurii, culoarea pe care o vrea si limita acesteia, in prim
   pas se creeaza coada, normal, se baga in coada pixelul trimis apoi se intra
   intr-o bucla cat timp coada nu este goala, se scoate urmatorul element de la
   coada si se verifica daca el apartine imaginii(adica daca nu iese pe afara)
   si daca culoarea pe care o are este diferita de culoare de delimitare, daca
   da atunci se coloreaza pixelul respectiv cu culoarea(cacofonia vietii) pe 
   care o vrem si apoi se adauga in coada pixelii din prejur, cel de sus, jos
   stanga, dreapta si se repeta bucla pana cand nu mai sunt elemente in coada,
   adica pana cand toate elementele din prejur au culoare conturului sau culo-
   rii pe care o vrea in interior si nu are cum sa mai bage in coada.
   - Dupa ce se aplica flood fill pe fiecare figura se incepe creearea
   fisierului care tine imaginea, cum matricea de pixeli a mea este scrisa
   intr-o imagine cu ajutorul clasei BufferIage, imaginea rezultatata din buff-
   er era rotita invers pentru ca sunt scrisi diferiti pixelii fata de cum am
   eu matricea deci ca sa o intorc cum trebuie (inaltimea devine latime ) si
   invers iar atunci cand se parcuge bucla de inseare: avem paramtrul inaltime
   i si latime j deci in loc sa adaugam (i, j) adaugam (j, i).
   
   Timp de lucru: 8 ore.
==============================FUNCTIONALITATE==================================
===============================================================================

   Georgescu Alexandru Ionut
   323 CD.

===============================================================================
===============================IMPLEMENTARE====================================
