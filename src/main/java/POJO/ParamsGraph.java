package POJO;

import com.google.gson.Gson;
import generators.methods.Color;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by guardeec on 27.01.16.
 */
public class ParamsGraph {
    private Map<String, List> graph;

    /*
    В конструкторе инициализуются списки нодов и линков
     */
    public ParamsGraph() {
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
        node.put("name", name);         //имя ноды
        node.put("group", group);       //тип ноды - от типа зависит изображение которое будет вешатся на ноду
        node.put("radius", radius);     //радиус ноды
        graph.get("nodes").add(node);
    }

    /*
    Добавление Линков
     */
    //
    // нужно обработать исключения
    //
    public void addLink(int source, int target, int width, float color, float opacity, boolean arrows){
        Map<String, Object> link = new LinkedHashMap<String, Object>();
        link.put("source", source);     //имя ноды - сорс линка
        link.put("target", target);     //имя ноды - таргет линка
        link.put("width", width);       //толщина линка

        Map<String, Integer> colors = new LinkedHashMap<>();
        Integer[] rgb = Color.getColor(color);
        colors.put("red", rgb[0]);
        colors.put("green", rgb[1]);
        colors.put("blue", rgb[2]);
        
        link.put("color", colors);       //цвет линка
        link.put("opacity", opacity);   //прозрачность линка
        graph.get("links").add(link);
    }

    //получить JSON представление инстанса
    //необходимо для парсинга даты в html файле
    public String getJSON(){
        Gson gson = new Gson();
        return gson.toJson(graph);
    }
}
