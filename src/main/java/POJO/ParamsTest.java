package POJO;

import com.google.gson.Gson;

import java.util.*;

/**
 * Created by guardeec on 26.01.16.
 */
public class ParamsTest {
    private Map<String, List> graph;

    /*
    В конструкторе инициализуются списки нодов и линков
     */
    public ParamsTest() {
        graph = new LinkedHashMap<String, List>();
        List<Map> nodes = new LinkedList<Map>();
        List<Map> links = new LinkedList<Map>();
        graph.put("nodes", nodes);
        graph.put("links", links);
    }

    /*
    Добавление ноды
     */
    //
    //  нужно обработать исключения
    //
    public void addNode(String name, int group, int radius){
        Map<String, Object> node = new LinkedHashMap<String, Object>();
        node.put("name", name);
        node.put("group", group);
        node.put("radius", radius);

        Random random = new Random();
        List<Map> proportions = new LinkedList<>();
        for (int i=0; i<4; i++){
            Map<String, Integer> map = new LinkedHashMap<>();
            map.put("group", random.nextInt(4));
            map.put("groupOut", random.nextInt(4));
            map.put("value", 1);
            map.put("radius", radius);
            proportions.add(map);
        }

        node.put("proportions", proportions);

        graph.get("nodes").add(node);
    }

    /*
    Добавление Линков
     */
    //
    // нужно обработать исключения
    //
    public void addLink(int source, int target, int value, int color, float opacity, boolean arrows){
        Map<String, Object> link = new LinkedHashMap<String, Object>();
        link.put("source", source);
        link.put("target", target);
        link.put("value", value);
        link.put("color", color);
        link.put("opacity", opacity);
        link.put("length", 200);
        link.put("name", Integer.toString(source)+"to"+Integer.toString(target));
        link.put("arrows",arrows);
        Map<String, Object> targetNode = (Map<String, Object>) graph.get("nodes").get(target);

        //радиус в линке нужен для правильного отображения положения стрелочек ориентирванного графа
        //радиус берётся от ноды таргета
        int radius = (int) targetNode.get("radius");
        link.put("radius", radius);

        graph.get("links").add(link);
    }

    //получить JSON представление инстанса
    //необходимо для парсинга даты в html файле
    public String getJSON(){
        Gson gson = new Gson();
        return gson.toJson(graph);
    }

    /*
    Generators
     */

    /*
    Неориентированный граф
     */
    public static String generateRandomGraph(int numberOfNodes){
        ParamsTest params = new ParamsTest();
        Random random = new Random();

        /*
        Добавляем ноды
         */
        for (int i=0; i<numberOfNodes; i++){
            int radius = random.nextInt(15);
            if (radius<5){
                radius=5;
            }
            params.addNode(
                    Integer.toString(i),    //имя ноды (в д.с. порядковый номер)
                    random.nextInt(10),     //группа ноды (от 1ого до 10, д.б. не > количества иконок)
                    radius                  //размер ноды (от 5 до 15, что бы не сликом броское)
            );
        }

        /*
        Добавляем линки
         */
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
                    i,                              //имя ноды сорса
                    random.nextInt(numberOfNodes),  //имя ноды таргета
                    value,                          //толщина линии (не д.б. 0)
                    color,                          //цвет линии (см. номера цветов в html)
                    opacity,                        //прозрачность линии (не д.б. < 30% что бы различались)
                    false                           //флаг ориентированности графа
            );
        }
        return params.getJSON();
    }

    /*
    Ориентированный граф
     */
    public static String generateRandomGraphWithArrows(int numberOfNodes){
        Params params = new Params();
        Random random = new Random();

        /*
        Добавляем ноды
         */
        for (int i=0; i<numberOfNodes; i++){
            int radius = random.nextInt(10);
            if (radius<5){
                radius=5;
            }
            params.addNode(
                    Integer.toString(i),    //имя ноды (в д.с. порядковый номер)
                    random.nextInt(10),     //группа ноды (от 1ого до 10, д.б. не > количества иконок)
                    radius                  //размер ноды (от 5 до 15, что бы не сликом броское)
            );
        }

        /*
        Добавляем линки
         */
        for (int i=0; i<numberOfNodes; i++){
            int value = 3;
            float opacity = random.nextFloat();
            if (opacity<0.3){
                opacity=0.3F;
            }
            int color = 5;
            params.addLink(
                    i,                              //имя ноды сорса
                    random.nextInt(numberOfNodes),  //имя ноды таргета
                    value,                          //толщина линии (в ориентированном графе д.б. ОДИНАКОВА у всех линков)
                    color,                          //цвет линии (в ориентированном графе д.б. ОДИНАКОВ во всех линках)
                    opacity,                        //прозрачность
                    true                            //флаг оринентированности
            );
        }
        return params.getJSON();
    }
}
