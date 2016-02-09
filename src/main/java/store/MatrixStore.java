package store;

import POJO.ParamsMatrix;
import POJO.net.Host;
import POJO.net.Net;
import POJO.net.Router;
import generators.net.NetGenerator;

import java.util.List;
import java.util.Random;

/**
 * Created by guardeec on 03.02.16.
 */
/*
Временное хранилище синглтон для матриц
в проследствии будет заменено
 */
public class MatrixStore {
    private static MatrixStore ourInstance = new MatrixStore(10
    );

    public static MatrixStore getInstance() {
        return ourInstance;
    }

    private ParamsMatrix paramsMatrixObject;

    /*
    В конструкторе генерируем новую матрицу
     */
    private MatrixStore(int numberOfNodes) {
        /*
        Обычный генератор
         */

        /*
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
        */





        /*
        Генератор похожий на сеть
         */

        Net net = Net.generate(numberOfNodes, 10);
        Random random = new Random();

        paramsMatrixObject = new ParamsMatrix();

        //добавляем роутер
        for (int i=0; i<net.getNetwork().size(); i++){

            Router router = net.getNetwork().get(i);
            paramsMatrixObject.addNode(router.getName());
            List<Host> hosts = net.getNetwork().get(i).getHosts();
            float color = random.nextFloat();

            //добавляем хосты роутера
            for (int q = 0 ; q < hosts.size(); q++){
                Host host = hosts.get(q);
                paramsMatrixObject.addNode(host.getName());

                paramsMatrixObject.addLink(router.getId(), host.getId(), color, random.nextFloat());
                paramsMatrixObject.addLink(host.getId(), router.getId(), color, random.nextFloat());

                for (int z=0; z<q; z++){
                    paramsMatrixObject.addLink(host.getId(), host.getId()-z, color, random.nextFloat());
                    paramsMatrixObject.addLink(host.getId()-z, host.getId(), color, random.nextFloat());
                }
            }
        }


    }

    /*
    Получить JSON матрицы
     */
    public String getMatrixFromStore(){
        return paramsMatrixObject.getJSON();
    }

    /*
    Получить JSON матрицы в соответствии с сортировкой
     */
    public String getSortedMatrixFromStore(boolean up, int metric, boolean stroke){
        return paramsMatrixObject.getJSON(up, metric, stroke);
    }

    /*
    Генерация новой матрицы осуществляется вызовом конструктора в котором генерируется новая матрица
     */
    public void generateNewMatrix(){
        ourInstance = new MatrixStore(30);
    }




}
