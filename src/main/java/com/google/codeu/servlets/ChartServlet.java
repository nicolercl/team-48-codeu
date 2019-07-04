package com.google.codeu.servlets;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.codeu.data.Datastore;

import java.util.Scanner;


@WebServlet("/webchart")
public class ChartServlet extends HttpServlet {


    private JsonObject ret;
    private Datastore datastore;

    // This class could be its own file if we needed it outside this servlet
    private static class skillNum {
        String skill;
        int number;

        private skillNum(String skill, int number) {
            this.skill = skill;
            this.number = number;
        }
    }

    @Override
    public void init() {
        JsonArray learnUserNum = new JsonArray();
        JsonArray shareUserNum = new JsonArray();
        Gson gson = new Gson();
        datastore = new Datastore();
        String[] skillTypes = {"Design", "Culinary", "Music", "Sports", "Photography", "Technology", "Language"};
        HashMap<String, Integer>[] arr = datastore.getSkillsUserNum();
        for (int i = 0; i < skillTypes.length; i++) {
            String skill = skillTypes[i];
            int numL = arr[0].get(skill);
            int numS = arr[1].get(skill);
            learnUserNum.add(gson.toJsonTree(new skillNum(skill, numL)));
            shareUserNum.add(gson.toJsonTree(new skillNum(skill, numS)));
        }
        ret = new JsonObject();
        ret.add("learn", learnUserNum);
        ret.add("share", shareUserNum);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.getOutputStream().println(ret.toString());
    }


}