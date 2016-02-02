package generators.methods;

import POJO.ParamsMatrix;

import java.util.Random;

/**
 * Created by guardeec on 02.02.16.
 */
public class MatrixGenerator {
    /*
    Генерация JSON для проверки графа
     */
    public static String generateMatrix(int numberOfNodes){
        ParamsMatrix paramsMatrix = new ParamsMatrix();
        Random random = new Random();

        //добавляем стоки+столбцы
        for (int i=0; i<numberOfNodes; i++){
            paramsMatrix.addNode(i, Integer.toString(i));
        }

        //добавляем ячейки
        for (int i=0; i<numberOfNodes; i++){
            paramsMatrix.addLink(i, random.nextInt(numberOfNodes), random.nextInt(5), random.nextFloat(), i);
        }

        return paramsMatrix.getJSON();
    }
}
