package generators.servlets;

import generators.methods.GraphArrowsGenerator;
import generators.methods.GraphGenerator;
import generators.methods.GraphGlyphArrowsGenerator;
import generators.methods.GraphGlyphGenerator;

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
            количество нодов задаётся в запросе
        */
        int numberOfNodes = Integer.parseInt(request.getParameterMap().get("nodes")[0]);
        /*
            в запросе задаётся какой набор данных необходим:
            0 - неориентированный граф
            1 - ориентированный граф
            2 - неориентированный граф с глифами
            3 - ориентированный граф с глифами
        */
        int typeOfVisualisation = Integer.parseInt(request.getParameterMap().get("type")[0]);

        String json="";
        switch (typeOfVisualisation){
            case 0: {
                json = GraphGenerator.generateGraph(numberOfNodes);
                break;
            }
            case 1: {
                json = GraphArrowsGenerator.generateGraphArrows(numberOfNodes);
                break;
            }
            case 2:
                json = GraphGlyphGenerator.generateGraphGlyph(numberOfNodes);
                break;
            case 3:
                json = GraphGlyphArrowsGenerator.generateGraphGlyphArrows(numberOfNodes);
                break;
        }

        PrintWriter out = response.getWriter();
        out.append(json);
        out.close();
    }
}
