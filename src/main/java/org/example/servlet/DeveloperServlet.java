package org.example.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.dto.JsonResponseMessage;
import org.example.exception.JsonMapperException;
import org.example.model.Account;
import org.example.model.Developer;
import org.example.service.DeveloperService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.example.utils.ResponseUtils.customResponse;

@WebServlet(
        name = "DevelopServlet",
        urlPatterns = {"/developers/*"}
)
public class DeveloperServlet extends HttpServlet {

    private DeveloperService developerService = new DeveloperService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String[] parts = request.getPathInfo().split("/");

        if (parts.length == 0) {
            List<Developer> developerList = developerService.findAll();
            String json = gson.toJson(developerList);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage(json, 200);
            customResponse(response, respMessage);
        } else {
            Developer developer = developerService.findById(Long.valueOf(parts[1]));
            if (developer == null) {
                JsonResponseMessage respMessage =
                        new JsonResponseMessage("Not found", 404);
                customResponse(response, respMessage);
            }
            String json = gson.toJson(developer);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage(json, 200);
            customResponse(response, respMessage);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String line;
        StringBuilder jb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        try {
            Developer developer = mapper.readValue(jb.toString(), Developer.class);
            developerService.save(developer);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("User created", 201);
            customResponse(response, respMessage);
        } catch (JsonProcessingException e) {
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("Bad request", 400);
            customResponse(response, respMessage);
            throw new JsonMapperException("Error parsing JSON request string");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String[] parts = request.getPathInfo().split("/");

        if (parts.length == 0) {
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("Not found", 404);
            customResponse(response, respMessage);
        } else {
            developerService.delete(Long.valueOf(parts[1]));
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("User deleted", 200);
            customResponse(response, respMessage);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String line;
        StringBuilder jb = new StringBuilder();
        ObjectMapper mapper = new ObjectMapper();

        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Developer developer = mapper.readValue(jb.toString(), Developer.class);
            developerService.update(developer);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("User updated", 200);
            customResponse(response, respMessage);
        } catch (JsonProcessingException e) {
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("Bad request", 400);
            customResponse(response, respMessage);
            throw new JsonMapperException("Error parsing JSON request string");
        }
    }
}
