package ru.ilka.apartments.controller;

import com.google.gson.JsonArray;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ru.ilka.apartments.exception.CommandException;
import ru.ilka.apartments.command.ActionCommand;
import ru.ilka.apartments.command.ActionFactory;
import ru.ilka.apartments.rest.RestRequestLogic;
import ru.ilka.apartments.util.ContextHolder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Ajax/*", name = "AjaxController")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 905242440943999308L;
    private static final String CONTENT_TYPE = "application/json";

    private static Logger logger = LogManager.getLogger(Controller.class);

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
            PrintWriter printWriter = response.getWriter();
            JsonArray result = command.execute(restRequest, response);
            printWriter.println(result);
        } catch (IOException e) {
            logger.error("Problems in controller ", e);
        } catch (CommandException e) {
            logger.error("Problems in logic ", e);
            response.setContentType(CONTENT_TYPE);
            PrintWriter printWriter = response.getWriter();
            Throwable prevCause = e;
            Throwable cause = e.getCause();
            while (prevCause != null) {
                if (cause.getCause() == null) {
                    break;
                }
                prevCause = cause;
                cause = cause.getCause();
            }
            JsonArray result = new JsonArray();
            result.add("Ups " + cause.getMessage());
            printWriter.println(result);
        }
    }
}
