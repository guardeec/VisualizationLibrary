package generators.servlets;

import store.MatrixStore;
import generators.methods.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by guardeec on 25.01.16.
 */
/*
сервлет для отправки сгенерированного json
впоследствии весь пакет generators будет заменен на модуль импорта данных визуализации
 */
@WebServlet(name = "GetJson")
public class GetJson extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
            в запросе задаётся какой набор данных необходим:
            0 - неориентированный граф
            1 - ориентированный граф
            2 - неориентированный граф с глифами
            3 - ориентированный граф с глифами
            4 - матрица
            5 - карта деревьев
        */
        int typeOfVisualisation = Integer.parseInt(request.getParameterMap().get("type")[0]);
        String json="";
        switch (typeOfVisualisation){
            case 0: {
                int numberOfNodes = Integer.parseInt(request.getParameterMap().get("nodes")[0]);
                json = GraphGenerator.generateGraph(numberOfNodes);
                break;
            }
            case 1: {
                int numberOfNodes = Integer.parseInt(request.getParameterMap().get("nodes")[0]);
                json = GraphArrowsGenerator.generateGraphArrows(numberOfNodes);
                break;
            }
            case 2: {
                int numberOfNodes = Integer.parseInt(request.getParameterMap().get("nodes")[0]);
                json = GraphGlyphGenerator.generateGraphGlyph(numberOfNodes);
                break;
            }
            case 3:{
                int numberOfNodes = Integer.parseInt(request.getParameterMap().get("nodes")[0]);
                json = GraphGlyphArrowsGenerator.generateGraphGlyphArrows(numberOfNodes);
                break;
            }
            case 4:{
                //в случае матрицы необходимо получить параметры сортировки
                boolean up = Boolean.parseBoolean(request.getParameterMap().get("up")[0]);
                int metric = Integer.parseInt(request.getParameterMap().get("metric")[0]);
                boolean stroke = Boolean.parseBoolean(request.getParameterMap().get("stroke")[0]);
                json = MatrixStore.getInstance().getSortedMatrixFromStore(up, metric, stroke);
                break;
            }
            case 5:{
                json = TreeMapGenerator.generateTreeMap(Integer.parseInt(request.getParameterMap().get("nodes")[0]));
                break;
            }
        }

        PrintWriter out = response.getWriter();
        out.append(json);
        out.close();
    }
}
