package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by guardeec on 27.01.16.
 */
@WebServlet(name = "GraphGlyph")
public class GraphGlyph extends HttpServlet {
    /*
    Сервлет для отображения неориентированного графа с глифами
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("GraphGlyph.html");
        view.forward(request, response);
    }
}
