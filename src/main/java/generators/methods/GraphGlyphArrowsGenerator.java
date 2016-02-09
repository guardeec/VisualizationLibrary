package generators.methods;

import POJO.ParamsGlyph;
import POJO.ParamsGlyphArrows;

import java.util.Random;

/**
 * Created by guardeec on 28.01.16.
 */
public class GraphGlyphArrowsGenerator {
    /*
    генерация json для проверки графа с глифами
     */
    public static String generateGraphGlyphArrows(int numberOfNodes){
        ParamsGlyphArrows params = new ParamsGlyphArrows();
        Random random = new Random();

        //Добавляем ноды
        for (int i=0; i<numberOfNodes; i++){
            int radius = random.nextInt(15);
            if (radius<5){
                radius=5;
            }
            params.addNode(
                    Integer.toString(i),
                    radius,
                    new int[] {random.nextInt(3), random.nextInt(3), random.nextInt(3), random.nextInt(3)},
                    new int[] {random.nextInt(3), random.nextInt(3), random.nextInt(3), random.nextInt(3)}
            );
        }

        //Добавляем линки
        for (int i=0; i<numberOfNodes; i++){
            int value = 3;
            float opacity = random.nextFloat();
            if (opacity<0.3){
                opacity=0.3F;
            }
            params.addLink(
                    i,
                    random.nextInt(numberOfNodes),
                    value,
                    random.nextFloat(),
                    opacity
            );
        }
        return params.getJSON();
    }
}
