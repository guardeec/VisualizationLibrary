package generators.methods;

import POJO.ParamsGlyph;

import java.util.Random;

/**
 * Created by guardeec on 27.01.16.
 */
public class GraphGlyphGenerator {
    /*
    генерация json для проверки графа с глифами
     */
    public static String generateGraphGlyph(int numberOfNodes){
        ParamsGlyph params = new ParamsGlyph();
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
            int value = random.nextInt(10);
            if (value==0){
                value=1;
            }
            float opacity = random.nextFloat();
            if (opacity<0.3){
                opacity=0.3F;
            }
            int color = random.nextInt(10);
            params.addLink(
                    i,
                    random.nextInt(numberOfNodes),
                    value,
                    color,
                    opacity
            );
        }
        return params.getJSON();
    }
}
