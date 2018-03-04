package container;
/**
 * ColorLevel este o clasa unde sunt incapsulate culoriile(RGB) si opactiate
 * pixelului, metodele getColor si getOpacity returneaza culoarea respectiv
 * opacitatea acesteia.
 * @author Alexandru Georgescu
 */
public class ColorLevel {
     private String culoare;
     private int opacitate;

     public ColorLevel(final String culoare, final int opacitate) {
         this.culoare = culoare;
         this.opacitate = opacitate;
     }

     public final String getColor() {
         return this.culoare;
     }

     public final int getOpacity() {
         return this.opacitate;
     }
}
