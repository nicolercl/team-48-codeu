package com.google.codeu.servlets;

import com.google.codeu.data.Datastore;
import com.google.codeu.data.UserTest;
import com.google.gson.Gson;
import jdk.nashorn.internal.objects.NativeJSON;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Type;

import com.google.gson.reflect.TypeToken;


@WebServlet("/user-info")
public class UserInfoServlet extends HttpServlet {
    private Datastore datastore;

    @Override
    public void init() {
        datastore = new Datastore();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String userData = request.getParameter("userData");
        Gson gson = new Gson();
        Type type = new TypeToken<UserTest>() {}.getType();
        UserTest user = gson.fromJson(userData, type);

        //add user to datastore
        datastore.storeUserTest(user);
        //maybe store into a certain category too?

        String userEmail = user.getEmail();
        response.setContentType("application/json");
        String selected = "Redirecting to Personal Page...";
        String json = gson.toJson(selected);
        response.getOutputStream().println(json);
    }
}