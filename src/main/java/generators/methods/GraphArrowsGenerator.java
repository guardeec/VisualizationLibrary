package generators.methods;

import POJO.ParamsGraphArrows;

import java.util.Random;

/**
 * Created by guardeec on 27.01.16.
 */
public class GraphArrowsGenerator {
    /*
    генерация json для проверки ориентированного графа
     */
    public static String generateGraphArrows(int numberOfNodes){
        ParamsGraphArrows params = new ParamsGraphArrows();
        Random random = new Random();

        //Добавляем ноды
        for (int i=0; i<numberOfNodes; i++){
            int radius = random.nextInt(10);
            if (radius<5){
                radius=5;
            }
            params.addNode(
                    Integer.toString(i),
                    random.nextInt(10),
                    radius
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
