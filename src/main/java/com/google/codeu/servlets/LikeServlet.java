/*
 * Copyright 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.codeu.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.codeu.data.Datastore;
import com.google.codeu.data.Message;
import com.google.codeu.data.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.UUID;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;


@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private Datastore datastore;

    @Override
    public void init() {

        datastore = new Datastore();

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");

        UserService userService = UserServiceFactory.getUserService();

        String userEmail = userService.getCurrentUser().getEmail();
        User user = datastore.getUser(userEmail);
        String messageData = request.getParameter("message");
        String task = request.getParameter("action");

        Gson gson = new Gson();
        Type type = new TypeToken<Message>() {
        }.getType();
        Message message = gson.fromJson(messageData, type);
        UUID ID = message.getId();
        String id = ID.toString();
        if (id.toString().equals("")) {
            response.getWriter().println("");
            return;
        }
        if (task.equals("change")) {

            long likes = 0;
            String action = "";
            if (user.inLikedMessages(id)) {
                likes = message.removeLikes();
                action = "Remove";
            } else {
                likes = message.addLikes();
                action = "Add";
            }
            user.updateLikedMessages(id);       //update user liked list
            datastore.storeUser(user);          //store updated user
            datastore.updateLikes(ID, likes);   //update message likes and stored
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("likes", message.getLikes());
            jsonObject.addProperty("action", action);
            response.getOutputStream().println(jsonObject.toString());

        } else {
            String ret;
            if (user.inLikedMessages(id)) ret = "true";
            else ret = "false";
            String json = gson.toJson(ret);
            response.getOutputStream().println(json);
        }
    }
}