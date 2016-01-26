package servlets.data;

import POJO.Params;

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
        */
        int typeOfVisualisation = Integer.parseInt(request.getParameterMap().get("type")[0]);

        String json="";
        switch (typeOfVisualisation){
            case 0: {
                json = Params.generateRandomGraph(numberOfNodes);
                break;
            }
            case 1: {
                json = Params.generateRandomGraphWithArrows(numberOfNodes);
            }
        }

        PrintWriter out = response.getWriter();
        out.append(json);
        out.close();
    }
}
