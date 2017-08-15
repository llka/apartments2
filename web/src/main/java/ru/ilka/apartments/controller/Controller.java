package ru.ilka.apartments.controller;

import com.google.gson.JsonArray;
import ru.ilka.apartments.exception.CommandException;
import ru.ilka.apartments.model.command.ActionCommand;
import ru.ilka.apartments.model.command.ActionFactory;
import ru.ilka.apartments.model.entity.RestRequest;
import ru.ilka.apartments.model.logic.RestRequestLogic;
import ru.ilka.apartments.model.util.ContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
@WebServlet(urlPatterns = "/Ajax/*", name = "AjaxController")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 905242440943999308L;

    private static final String CONTENT_TYPE = "application/json";

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ActionFactory actionFactory = ContextHolder.getApplicationContext().getBean(ActionFactory.class);
        RestRequestLogic restRequestLogic = ContextHolder.getApplicationContext().getBean(RestRequestLogic.class);

        HttpServletRequest restRequest = restRequestLogic.checkCommand(request);
        ActionCommand command = actionFactory.defineCommand(restRequest);
        try {
            response.setContentType(CONTENT_TYPE);
            PrintWriter printWriter;
            try {
                printWriter = response.getWriter();
                JsonArray result = command.execute(restRequest, response);
                printWriter.println(result);
            } catch (IOException e) {
                System.out.println("Problems in ajax ru.ilka.apartments.controller " + e);
            }
        } catch (CommandException e) {
            System.out.println("Error in command layer " + e);
        }
    }
}
