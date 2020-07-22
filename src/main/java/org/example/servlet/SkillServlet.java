package org.example.servlet;

import org.example.model.Skill;
import org.example.service.SkillService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SkillServlet extends HttpServlet {
    private SkillService skillService = new SkillService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Skill> skillList = skillService.findAll();

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(skillList);
        out.flush();
    }
}
