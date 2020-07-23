package org.example.utils;

import com.google.gson.Gson;
import org.example.dto.JsonResponseMessage;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseUtils {

    public static void customResponse(
            HttpServletResponse response, JsonResponseMessage jsonResponse) throws IOException {

        Gson gson = new Gson();
        String json = gson.toJson(jsonResponse);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(jsonResponse.getStatusCode());
        out.print(json);
        out.flush();
    }
    public static void customResponse(
            HttpServletResponse response, String string) throws IOException {

        Gson gson = new Gson();
        String json = gson.toJson(string);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(json);
        out.flush();
    }
}
