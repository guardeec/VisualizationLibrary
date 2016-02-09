package generators.methods;

import POJO.ParamsGraph;

import java.util.Random;

/**
 * Created by guardeec on 27.01.16.
 */
public class GraphGenerator {
    /*
    генерация json для проверки простого неориентированного графа
     */
    public static String generateGraph(int numberOfNodes){
        ParamsGraph params = new ParamsGraph();
        Random random = new Random();

        //Добавляем ноды
        for (int i=0; i<numberOfNodes; i++){
            int radius = random.nextInt(15);
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
            int value = random.nextInt(10);
            if (value==0){
                value=1;
            }
            float opacity = random.nextFloat();
            if (opacity<0.3){
                opacity=0.3F;
            }
            params.addLink(
                    i,
                    random.nextInt(numberOfNodes),
                    value,
                    random.nextFloat(),
                    opacity,
                    false
            );
        }
        return params.getJSON();
    }
}
