import java.util.Random;
public class ANSI_Colors {
    //BG_Colors
    final String RED_BG = "\u001b[41m";
    final String GREEN_BG = "\u001b[42m";
    final String YELLOW_BG = "\u001b[43m";
    final String BLUE_BG = "\u001b[44m";
    final String MAGENTA_BG = "\u001b[45m";
    final String CYAN_BG = "\u001b[46m";
    final String[] COLOR_BG_ARRAY = {RED_BG, GREEN_BG, YELLOW_BG, BLUE_BG, MAGENTA_BG, CYAN_BG};
    public int colorBackgroundRandomizer() {
        final int MAX = COLOR_BG_ARRAY.length - 1;
        final int MIN = 0;
        double rand = (Math.random() * (MAX - MIN + 1) + MIN);
        return (int) rand;
    }
}
