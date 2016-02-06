package POJO;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Guardeec on 06.02.16.
 */
public class ParamsTreeMap {
    private String name;
    private List<ParamsTreeMap> children;
    private Integer size;

    /*
    При инициализации задаем только имя, т.к не знаем будет ли эта нода конечной
     */
    public ParamsTreeMap (String name) {
        this.name = name;
    }

    /*
    Когда задаем размер проверяем проинициализированн ли размер - если да, то эта нода конечная и кидаем эксепшн
    если нет, инициализирем список дочерних эл-ов если он все еще null и добавляем элемент
     */
    public void addChildren(ParamsTreeMap paramsTreeMap) {
        if (size != null){
            throw new IllegalStateException("Children can be added only in non-endpoint nodes");
        } else {
            if(this.children==null){
                this.children = new LinkedList<>();
            }
            this.children.add(paramsTreeMap);
        }
    }

    /*
    Задаем размер предварительно проверяя что дочерних элементов нет
    Если они есть - кидаем исклбчение
     */
    public void setSize(int size){
        if (this.children != null){
            throw new IllegalStateException("Size can be initialized only in endpoint nodes");
        }else {
            this.size = size;
        }
    }

    /*
    Получить JSON представление объекта
     */
    public String getJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
