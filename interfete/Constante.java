package interfete;
/**
 * Interfata unde se salveaza constatele folosite in impelemtarea
 * cerintei pentru o usoara modificare in caz de vrem sa modificam
 * ceva.
 * @author Alexandru Georgescu
 */
public interface Constante {

    default void con() {
    }
    String LINE = "LINE";
    String SQUARE = "SQUARE";
    String RECTANGLE = "RECTANGLE";
    String CIRCLE = "CIRCLE";
    String TRIANGLE = "TRIANGLE";
    String DIAMOND = "DIAMOND";
    String POLYGON = "POLYGON";
    int GRAD1 = 1;
    int GRAD2 = 2;
    int GRAD3 = 3;
    int GRAD4 = 4;
    int GRAD6 = 6;
    int GRAD10 = 10;
}
