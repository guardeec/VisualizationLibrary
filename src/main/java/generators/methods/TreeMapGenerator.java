package generators.methods;

import POJO.ParamsTreeMap;

import java.util.Random;

/**
 * Created by Guardeec on 06.02.16.
 */
public class TreeMapGenerator {
    /*
    генерация json для проверки карт деревьев
     */
    public static String generateTreeMap(int numberOfNodes){
        //корень
        ParamsTreeMap up = new ParamsTreeMap("up");
        //пятая часть от всех элементов будет дочерними корня
        for (int i=0; i<numberOfNodes/5; i++){
            ParamsTreeMap middle = new ParamsTreeMap("i"+Integer.toString(i));
            //четыре пятых от всех элементов будут конечными элементами
            for (int q=0; q<numberOfNodes/5*4; q++){
                ParamsTreeMap down = new ParamsTreeMap("q"+Integer.toString(q));
                Random random = new Random();
                //размер рандом - 1..10
                down.setSize(random.nextInt(10));
                middle.addChildren(down);
            }
            up.addChildren(middle);
        }
        return up.getJson();
    }
}
