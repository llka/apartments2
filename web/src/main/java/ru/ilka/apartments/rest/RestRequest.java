package ru.ilka.apartments.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
public class RestRequest extends HttpServletRequestWrapper {

    private HashMap<String,String> params = new HashMap();

    public RestRequest(HttpServletRequest request) {
        super(request);
    }

    public String getParameter(String name) {
        if (params.get(name) != null ) {
            return params.get(name);
        }
        HttpServletRequest req = (HttpServletRequest) super.getRequest();
        return req.getParameter(name);
    }

    public void addParameter(String name,String value ) {
        params.put(name,value);
    }
}

