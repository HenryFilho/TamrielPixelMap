package pixels;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import javafx.util.Pair;

public class Main {

    public static void main(String[] args) {

        // Initialize a map with the possible colors as keys, and pairs of each region
        // name and an integer that will be usade as a counter.
        Map<Color, Pair<String, Integer>> colors = initColors();

        // Read the desired image
        BufferedImage img = readImage("assets/mapaTes.png");

        // Count how many pixels each color have. (The total is the sum of it all
        // excluding lava and water)
        int total = count(colors, img);

        // Auxiliary map with just the pairs to make the code more readable.
        Map<String, Integer> pairs = pairs(colors);

                       // Fuck Hlaalu
        int beforeMede = pairs.get("Redoran")
                       + pairs.get("Indoril")
                       + pairs.get("Telvanni")
                       // Red Year
                       + pairs.get("Vvanderfell")
                       // Accession war
                       + pairs.get("Argonia")
                       // Thalmors
                       + pairs.get("Anequina")
                       + pairs.get("Pelletine")
                       + pairs.get("Valenwood")
                       + pairs.get("Alinor");

                      // White-Gold bullshit
        int afterMede = pairs.get("Hammerfell")
                      // Civil war bullshit
                      + pairs.get("Stormcloak")
                      + pairs.get("Whiterun");

        int remainingTerritory = pairs.get("Cyrodiil")
                               + pairs.get("High Rock")
                               + pairs.get("Skyrim");

        System.out.printf("Lost before Titus I: %.2f%%\n"
                        + "Lost after Titus I: %.2f%%\n"
                        + "Remaining land: %.2f%%\n",
                        (beforeMede * 100.0 / total),
                        (afterMede * 100.0 / total),
                        (remainingTerritory * 100.0 / total));
    }

    private static Map<String, Integer> pairs(Map<Color, Pair<String, Integer>> map) {
        Map<String, Integer> pairs = new HashMap<>();
        for (Pair<String, Integer> pair : map.values())
            pairs.put(pair.getKey(), pair.getValue());
        return pairs;
    }

    private static Map<Color, Pair<String, Integer>> initColors() {

        Map<Color, Pair<String, Integer>> colors = new HashMap<>();
        colors.put(new Color(236, 218, 207), new Pair<>("Skyrim", 0));
        colors.put(new Color(239, 237, 196), new Pair<>("Whiterun", 0));
        colors.put(new Color(103, 191, 207), new Pair<>("Stormcloak", 0));
        colors.put(new Color(224, 222, 151), new Pair<>("High Rock", 0));
        colors.put(new Color(221, 157, 75), new Pair<>("Hammerfell", 0));
        colors.put(new Color(74, 33, 53), new Pair<>("Cyrodiil", 0));
        colors.put(new Color(143, 55, 53), new Pair<>("Redoran", 0));
        colors.put(new Color(240, 205, 43), new Pair<>("Indoril", 0));
        colors.put(new Color(99, 79, 54), new Pair<>("Telvanni", 0));
        colors.put(new Color(55, 42, 42), new Pair<>("Vvanderfell", 0));
        colors.put(new Color(255, 0, 5), new Pair<>("Lava", 0));
        colors.put(new Color(30, 69, 19), new Pair<>("Argonia", 0));
        colors.put(new Color(211, 209, 130), new Pair<>("Anequina", 0));
        colors.put(new Color(216, 156, 127), new Pair<>("Pelletine", 0));
        colors.put(new Color(77, 184, 78), new Pair<>("Valenwood", 0));
        colors.put(new Color(250, 238, 37), new Pair<>("Alinor", 0));
        colors.put(new Color(51, 102, 153), new Pair<>("Water", 0));

        return colors;
    }

    private static BufferedImage readImage(String path) {

        BufferedImage img = null;
        File f = null;

        try {
            f = new File(path);
            img = ImageIO.read(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return img;
    }

    private static int count(Map<Color, Pair<String, Integer>> colors, BufferedImage img) {
        int total = 0;

        Color color;
        Pair<String, Integer> pair;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                color = new Color(img.getRGB(x, y));
                pair = colors.get(color);

                colors.put(color, new Pair<>(pair.getKey(), pair.getValue() + 1));
                if (!(pair.getKey().equals("Water") || pair.getKey().equals("Lava")))
                    total++;

            }
        }

        return total;
    }

}
