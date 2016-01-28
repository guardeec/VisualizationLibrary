package POJO;

import com.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by guardeec on 28.01.16.
 */
public class ParamsGlyphArrows {
    private Map<String, List> graph;

    /*
    В конструкторе инициализуются списки нодов и линков
     */
    public ParamsGlyphArrows() {
        graph = new LinkedHashMap<>();
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
    public void addNode(String name, int radius, int[] colorIn, int[] colorOut){
        Map<String, Object> node = new LinkedHashMap<>();
        node.put("name", name);     //имя ноды
        node.put("radius", radius); //радиус всей ноды

        List<Map> proportions = new LinkedList<>();
        /*
        каждая нода это глиф
        глиф состоит из двух пайчартов - обычного и доната
        каждый пайчарт состоит из четырёх пропорционально равных кусочков
         */
        for (int i=0; i<4; i++){                                //i - номер кусочка пайчарта по часовой
            Map<String, Integer> map = new LinkedHashMap<>();
            map.put("colorIn", colorIn[i]);                     //цвет внутреннего кусочка пайчарта
            map.put("colorOut", colorOut[i]);                   //цвет внешнего кусочка пайчарта
            map.put("value", 1);                                //д.б. единица, что бы кусочки пропорционально равнялись друг другу
            map.put("radius", radius);                          //радиус всей ноды на основе которой в d3 вычисляются размеры кусочков (дублируется для доступа на разных уровнях контейнера в d3)
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
    public void addLink(int source, int target, int width, int color, float opacity){
        Map<String, Object> link = new LinkedHashMap<String, Object>();
        link.put("source", source);
        link.put("target", target);
        link.put("width", width);
        link.put("color", color);
        link.put("opacity", opacity);
        link.put("name", Integer.toString(source)+"to"+Integer.toString(target));
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
}
