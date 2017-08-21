package ru.ilka.apartments.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.ilka.apartments.command.CommandType;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class RestRequestLogic {

    private static Logger logger = LogManager.getLogger(RestRequestLogic.class);

    private static final String COMMAND_PARAM = "command";
    private static final String PARAM_APARTMENT_ID = "apartmentId";
    private static final String PARAM_DURATION = "duration";

    private static final Pattern REGEX_ALL = Pattern.compile("/apartments");
    private static final Pattern REGEX_BY_ID = Pattern.compile("/apartments/([1-9]+[0-9]*)");

    private static final String DEFAULT_DURATION = "24";

    public RestRequestLogic() {
    }

    public HttpServletRequest checkCommand(HttpServletRequest request) {
        String method = request.getMethod();

        String pathInfo = request.getPathInfo();

        if (pathInfo != null) {
            RestRequest restRequest = new RestRequest(request);

            Matcher matcher;

            switch (method) {
                case "GET":
                    matcher = REGEX_BY_ID.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.SHOW_BY_ID.toString());
                        restRequest.addParameter(PARAM_APARTMENT_ID, findId(pathInfo));
                        return restRequest;
                    }

                    matcher = REGEX_ALL.matcher(pathInfo);
                    if (matcher.find()) {
                        if ("true".equals(request.getParameter("available"))) {
                            restRequest.addParameter(COMMAND_PARAM, CommandType.SHOW_AVAILABLE.toString());
                        } else {
                            restRequest.addParameter(COMMAND_PARAM, CommandType.SHOW_ALL.toString());
                        }
                        return restRequest;
                    }
                    break;
                case "POST":
                    matcher = REGEX_ALL.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.ADD.toString());
                        return restRequest;
                    }
                    break;
                case "DELETE":
                    matcher = REGEX_BY_ID.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.DELETE.toString());
                        restRequest.addParameter(PARAM_APARTMENT_ID, findId(pathInfo));
                        return restRequest;
                    }

                    matcher = REGEX_ALL.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.DELETE_ALL.toString());
                        return restRequest;
                    }
                    break;
                case "PUT":
                    matcher = REGEX_BY_ID.matcher(pathInfo);
                    if (matcher.find()) {
                        restRequest.addParameter(COMMAND_PARAM, CommandType.BOOK.toString());
                        restRequest.addParameter(PARAM_APARTMENT_ID, findId(pathInfo));
                        String durationDays = request.getParameter("days");
                        String durationHours = request.getParameter("hours");
                        logger.warn("days " + durationDays);
                        logger.warn("hours " + durationHours);
                        if (durationDays != null) {
                            restRequest.addParameter(PARAM_DURATION, String.valueOf(Integer.valueOf(durationDays) * 24));
                        } else if (durationHours != null) {
                            restRequest.addParameter(PARAM_DURATION, durationHours);
                        } else {
                            restRequest.addParameter(PARAM_DURATION, DEFAULT_DURATION);
                        }
                        return restRequest;
                    }
                    break;
                default:
                    restRequest.addParameter(COMMAND_PARAM, "");
                    return restRequest;
            }
        }
        return request;
    }

    private String findId(String str) {
        Pattern pattern = Pattern.compile("[0-9]");
        Matcher matcher = pattern.matcher(str);

        StringBuilder stringBuilder = new StringBuilder();
        while (matcher.find()) {
            stringBuilder.append(matcher.group());
        }

        return stringBuilder.toString();
    }
}
