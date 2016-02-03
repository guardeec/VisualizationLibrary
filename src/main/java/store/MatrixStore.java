package store;

import POJO.ParamsMatrix;

import java.util.Random;

/**
 * Created by guardeec on 03.02.16.
 */
public class MatrixStore {
    private static MatrixStore ourInstance = new MatrixStore(30
    );

    public static MatrixStore getInstance() {
        return ourInstance;
    }

    private ParamsMatrix paramsMatrixObject;

    private MatrixStore(int numberOfNodes) {
        Random random = new Random();

        paramsMatrixObject = new ParamsMatrix();
        //добавляем стоки+столбцы
        for (int i=0; i<numberOfNodes; i++){
            paramsMatrixObject.addNode(Integer.toString(i));
        }

        //добавляем ячейки
        for (int i=0; i<numberOfNodes; i++){
            paramsMatrixObject.addLink(random.nextInt(numberOfNodes), random.nextInt(numberOfNodes), random.nextInt(5), random.nextFloat());
        }
    }

    public String getMatrixFromStore(){
        return paramsMatrixObject.getJSON();
    }

    public String getSortedMatrixFromStore(boolean up, int metric, boolean stroke){
        return paramsMatrixObject.getJSON(up, metric, stroke);
    }

    public void generateNewMatrix(){
        ourInstance = new MatrixStore(30);
    }




}
