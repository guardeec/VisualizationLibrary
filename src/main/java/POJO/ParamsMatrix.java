package POJO;

import com.google.gson.Gson;

import java.util.*;

/**
 * Created by Guardeec on 01.02.16.
 */
public class ParamsMatrix {
    private Map<String, List> matrix;

    public ParamsMatrix() {
        this.matrix = new LinkedHashMap<>();
        List<Map> nodes = new LinkedList<>();
        List<Map> links = new LinkedList<>();
        this.matrix.put("nodes", nodes);
        this.matrix.put("links", links);
    }

    public void addNode(int id, String name){
        Map<String, Object> node = new LinkedHashMap<>();
        node.put("id", id);
        node.put("name", name);
        this.matrix.get("nodes").add(node);
    }

    public void addLink(int sourceId, int targetId, int color, float opacity, int id){
        Map<String, Object> link = new LinkedHashMap<>();
        link.put("id", id);
        link.put("source", sourceId);
        link.put("target", targetId);
        link.put("color", color);
        link.put("opacity", opacity);
        this.matrix.get("links").add(link);
    }

    public String getJSON(){
        Collections.shuffle(matrix.get("links"));
        Gson gson = new Gson();
        return gson.toJson(matrix);
    }
}
