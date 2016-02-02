package test;

import POJO.ParamsMatrix;

import java.util.Random;

/**
 * Created by Guardeec on 01.02.16.
 */
public class jsonTest {
    public static void main(String[] args) {
        ParamsMatrix paramsMatrix = new ParamsMatrix();
        Random random = new Random();
        int numberOfNodes = 50;
        for (int i=0; i<numberOfNodes; i++){
            paramsMatrix.addNode(i, Integer.toString(i));
        }
        for (int i=0; i<numberOfNodes; i++){
            paramsMatrix.addLink(i, random.nextInt(numberOfNodes), random.nextInt(5), random.nextFloat());
        }
        System.out.printf(paramsMatrix.getJSON());
    }
}
