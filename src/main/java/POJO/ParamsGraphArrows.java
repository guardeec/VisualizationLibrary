package POJO;

import com.google.gson.Gson;
import generators.methods.Color;

import java.util.*;
import java.util.List;

/**
 * Created by guardeec on 19.01.16.
 */
public class ParamsGraphArrows {

    private Map<String, List> graph;

    /*
    В конструкторе инициализуются списки нодов и линков
     */
    public ParamsGraphArrows() {
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

        graph.get("nodes").add(node);
    }

    /*
    Добавление Линков
     */
    //
    // нужно обработать исключения
    //
    public void addLink(int source, int target, int width, float color, float opacity){
        Map<String, Object> link = new LinkedHashMap<String, Object>();
        link.put("source", source);
        link.put("target", target);
        link.put("width", width);

        Map<String, Integer> colors = new LinkedHashMap<>();
        Integer[] rgb = Color.getColor(color);
        colors.put("red", rgb[0]);
        colors.put("green", rgb[1]);
        colors.put("blue", rgb[2]);
        link.put("color", colors);

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
