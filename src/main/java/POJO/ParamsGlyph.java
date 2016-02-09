package POJO;

import com.google.gson.Gson;
import generators.methods.Color;

import java.util.*;

/**
 * Created by guardeec on 26.01.16.
 */
public class ParamsGlyph {
    private Map<String, List> graph;

    /*
    В конструкторе инициализуются списки нодов и линков
     */
    public ParamsGlyph() {
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
            map.put("radius", radius);                          //радиус всей ноды на основе которой в d3 вычисляются hfpvths recjxrjd
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
    public void addLink(int source, int target, int width, float color, float opacity){
        Map<String, Object> link = new LinkedHashMap<>();
        link.put("source", source);     //имя ноды - начала линка
        link.put("target", target);     //имя ноды - конец линка
        link.put("width", width);       //толщина линка

        Map<String, Integer> colors = new LinkedHashMap<>();
        Integer[] rgb = Color.getColor(color);
        colors.put("red", rgb[0]);
        colors.put("green", rgb[1]);
        colors.put("blue", rgb[2]);
        link.put("color", colors);

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
