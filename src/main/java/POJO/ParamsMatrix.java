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

    public void addLink(int sourseId, int targetId, int value){
        Map<String, Object> link = new LinkedHashMap<>();
        link.put("sourse", sourseId);
        link.put("target", targetId);
        link.put("value", value);
        this.matrix.get("links").add(link);
    }

    public String getJSON(){
        Gson gson = new Gson();
        return gson.toJson(matrix);
    }

}
