package POJO;

import com.google.gson.Gson;

import java.util.*;

/**
 * Created by Guardeec on 01.02.16.
 */
public class ParamsMatrix {
    private Map<String, List> matrix;

    /*
    В конструкторе инициализируются сиписок нодов и линков
     */
    public ParamsMatrix() {
        this.matrix = new LinkedHashMap<>();
        List<Map> nodes = new LinkedList<>();
        List<Map> links = new LinkedList<>();
        this.matrix.put("nodes", nodes);
        this.matrix.put("links", links);
    }

    /*
    Добавление нода - добавляем имя
    нод добавляется в конец списка
    id возвращается
     */
    public int addNode(String name){
        Map<String, Object> node = new LinkedHashMap<>();
        node.put("id", this.matrix.get("nodes").size());
        node.put("name", name);

        node.put("position", this.matrix.get("nodes").size());
        this.matrix.get("nodes").add(node);

        return (Integer) node.get("id");
    }

    /*
    Добавление линка
    добавляется id ноды источника, id ноды таргета
    id цвета и 0...1 прозрачности
    id линка возвращается
     */
    public int addLink(int sourceId, int targetId, int color, float opacity){
        Map<String, Object> link = new LinkedHashMap<>();
        link.put("id", this.matrix.get("links").size());
        link.put("source", sourceId);
        link.put("target", targetId);

        link.put("row", sourceId);
        link.put("column", targetId);

        link.put("color", color);
        link.put("opacity", opacity);
        this.matrix.get("links").add(link);

        return (Integer) link.get("id");
    }

    /*
    Возвращает JSON матрицы для передачи в JS
     */
    public String getJSON(){
        Gson gson = new Gson();
        return gson.toJson(matrix);
    }

    /*
    Возвращает отсортированный JSON матрицы для передачи в JS
     */
    public String getJSON(boolean up, int metric, boolean stroke){

        Collections.sort(
                this.matrix.get("nodes"),
                getComparator(up, metric, stroke)
        );

        normalization();

        Gson gson = new Gson();
        return gson.toJson(matrix);
    }


    /*
    Компараторы для сортировок матрицы
     */
    //сортирует по имени строки и столбцы по возрастанию
    private Comparator<Map> getComparator(final boolean upOrDown, final int metricNumber, final boolean columnOrStroke){

        return new Comparator<Map>() {

            public boolean up = upOrDown;
            //true - по возрастанию
            //false - по убыванию
            private int metric = metricNumber;
            // 0 - по имени
            // 1 - по сумме прозрачностей
            // 2 - по цвету
            private boolean column = columnOrStroke;
            //true - брать из колонок
            //false - брать из строк

            @Override
            public int compare(Map o1, Map o2) {
            /*
            Сортировка по имени
             */
                if (metric==0){
                    String name1 = (String) o1.get("name");
                    String name2 = (String) o2.get("name");
                    if (up){
                        //по возрастанию
                        return name1.compareTo(name2)>0 ? 1 : name1.compareTo(name2)==0 ? 0 : -1;
                    }else {
                        //по убыванию
                        return name1.compareTo(name2)>0 ? -1 : name1.compareTo(name2)==0 ? 0 : 1;
                    }
                }
            /*
            сортировка по сумме прозрачностей
             */
                if (metric==1){
                    Integer id1 = (Integer) o1.get("id");
                    Integer id2 = (Integer) o2.get("id");

                    float count1 = 0;
                    float count2 = 0;
                    List<Map<String, Object>> links = (List<Map<String, Object>>) matrix.get("links");
                    for (Map<String, Object> link : links){
                        Integer source;
                        if (column){
                            //берем сумму из колонок
                            source = (Integer) link.get("source");
                        }else {
                            //берем сумму из строк
                            source = (Integer) link.get("target");
                        }
                        if (source.compareTo(id1)==0){
                            count1 += (Float) link.get("opacity");
                        }
                        if (source.compareTo(id2)==0){
                            count2 += (Float) link.get("opacity");
                        }
                    }
                    if (up){
                        //по возрастанию
                        return count1>count2 ? -1 : count1==count2 ? 0 : 1;
                    }else {
                        //по убыванию
                        return count1>count2 ? 1 : count1==count2 ? 0 : -1;
                    }
                }
            /*
            сортировка по цветам
             */
                if (metric==2){
                    Integer id1 = (Integer) o1.get("id");
                    Integer id2 = (Integer) o2.get("id");

                    float count1 = 0;
                    float count2 = 0;
                    List<Map<String, Object>> links = (List<Map<String, Object>>) matrix.get("links");
                    for (Map<String, Object> link : links){
                        Integer source;
                        if (column){
                            //берем сумму из колонок
                            source = (Integer) link.get("source");
                        }else {
                            //берем сумму из строк
                            source = (Integer) link.get("target");
                        }
                        if (source.compareTo(id1)==0){
                            count1++;
                        }
                        if (source.compareTo(id2)==0){
                            count2++;
                        }
                    }
                    if (up){
                        //по возрастанию
                        return count1>count2 ? -1 : count1==count2 ? 0 : 1;
                    }else {
                        //по убыванию
                        return count1>count2 ? 1 : count1==count2 ? 0 : -1;
                    }
                }
                return 0;
            }
        };
    }



    /*
    Нормализация матрицы
    Необходимо применять для нодов и линков перед формированием JSON
    также ВСЕГДА после действий нарушающих порядок элементов в списках
    JS принимает значения по порядку - т.е.
    все id объектов в списках должны быть упорядочены
     */
    private void normalization(){
        //обновляем позиции нодов
        updateNodesPosition();
        //обновляем позиции линков
        updateLinksPosition();
        //нормализируем списки
        Collections.sort(matrix.get("nodes"), normalizator);
        Collections.sort(matrix.get("links"), normalizator);
    }
    //компаратор для нормализаци
    private Comparator<Map> normalizator = new Comparator<Map>() {
        @Override
        public int compare(Map o1, Map o2) {
            Integer id1 = (Integer) o1.get("id");
            Integer id2 = (Integer) o2.get("id");
            //компаратор сортирует по id по возрастанию
            return id1.compareTo(id2)>0 ? 1 : id1.compareTo(id2)==0 ? 0 : -1;
        }
    };
    //обновление позиций нодов - необходимо применять после сортировки и перед нормализацией
    private void updateNodesPosition(){
        List<Map<String, Integer>> nodes = (List<Map<String, Integer>>) matrix.get("nodes");
        //компараторы для сортировки сортируют положение ноды в списке
        //но не изменяют поле "позиция" в матрице
        //эта функция обновляет после позиция в соответствии с сортировкой
        for (int i=0; i< nodes.size(); i++){
            Map<String, Integer> node = nodes.get(i);
            node.put("position", i);
        }
    }
    //обновление позиций линков - необходимо применять после обновления позиций нодов и перед нормализацией
    private void updateLinksPosition(){
        List<Map<String, Integer>> nodes = (List<Map<String, Integer>>) matrix.get("nodes");
        List<Map<String, Integer>> links = (List<Map<String, Integer>>) matrix.get("links");
        //компараторы для сортировки сортируют положение ноды в списке
        //но не изменяют положение линков в матрице
        //эта функция обновляет позиции линков в соответствии с отсортированными нодами
        for (Map<String, Integer> link : links){
            Integer row = null;
            Integer column = null;
            for (Map<String, Integer> node : nodes){
                if(node.get("id").compareTo(link.get("source"))==0){
                    row = node.get("position");
                }
                if(node.get("id").compareTo(link.get("target"))==0){
                    column = node.get("position");
                }
            }
            link.put("row", row);
            link.put("column", column);
        }
    }
}
