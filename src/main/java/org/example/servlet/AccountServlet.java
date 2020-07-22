package org.example.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.JsonMapperException;
import org.example.model.Account;
import org.example.service.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.example.utils.ResponseUtils.customResponse;


public class AccountServlet  extends HttpServlet {

    private final String OK_MESSAGE = "{\"mesage\": \"OK\"}";
    private final String ERROR_MESSAGE = "{\"mesage\": \"ERROR\"}";


    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Account> accountList = new ArrayList<>();
        String  accountId = request.getParameter("id");

        if (accountId != null) {
            Account account = accountService.findById(Long.valueOf(accountId));
            accountList.add(account);
        } else {
            accountList = accountService.findAll();
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(accountList);

        customResponse(response, jsonString);
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
            Account account = mapper.readValue(jb.toString(), Account.class);
            accountService.save(account);
            customResponse(response, OK_MESSAGE);
        } catch (JsonProcessingException e) {
            customResponse(response, ERROR_MESSAGE);
            throw new JsonMapperException("Error parsing JSON request string");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String  accountId = request.getParameter("id");
        accountService.delete(Long.valueOf(accountId));

        customResponse(response, OK_MESSAGE);
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
            Account account = mapper.readValue(jb.toString(), Account.class);
            accountService.update(account);
            customResponse(response, OK_MESSAGE);
        } catch (JsonProcessingException e) {
            customResponse(response, ERROR_MESSAGE);
            throw new JsonMapperException("Error parsing JSON request string");
        }
    }
}
