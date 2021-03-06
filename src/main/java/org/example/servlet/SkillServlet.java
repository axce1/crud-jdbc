package org.example.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.dto.JsonResponseMessage;
import org.example.exception.JsonMapperException;
import org.example.model.Skill;
import org.example.service.SkillService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.example.utils.ResponseUtils.customResponse;


@WebServlet(
        name = "SkillServlet",
        urlPatterns = {"/skills/*"}
)
public class SkillServlet extends HttpServlet {

    private SkillService skillService = new SkillService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        String[] parts = request.getPathInfo().split("/");

        if (parts.length == 0) {
            List<Skill> skillList = skillService.findAll();
            String json = gson.toJson(skillList);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage(json, 200);
            customResponse(response, respMessage);
        } else {
            Skill skill = skillService.findById(Long.valueOf(parts[1]));
            if (skill == null) {
                JsonResponseMessage respMessage =
                        new JsonResponseMessage("Not found", 404);
                customResponse(response, respMessage);
            }
            String json = gson.toJson(skill);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage(json, 200);
            customResponse(response, json);
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
            Skill skill = mapper.readValue(jb.toString(), Skill.class);
            skillService.save(skill);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("Skill created", 201);
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

        String  skillId = request.getParameter("id");
        skillService.delete(Long.valueOf(skillId));
        JsonResponseMessage respMessage =
                new JsonResponseMessage("Skill deleted", 200);
        customResponse(response, respMessage);
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
            Skill skill = mapper.readValue(jb.toString(), Skill.class);
            skillService.update(skill);
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("Skill updated", 200);
            customResponse(response, respMessage);
        } catch (JsonProcessingException e) {
            JsonResponseMessage respMessage =
                    new JsonResponseMessage("Bad request", 400);
            customResponse(response, respMessage);
            throw new JsonMapperException("Error parsing JSON request string");
        }
    }
}
