package com.google.codeu.servlets;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@WebServlet("/translate")
public class TranslationServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get the request parameters.
        String originalText = request.getParameter("text");
        String languageCode = request.getParameter("languageCode");
        Locale loc = new Locale(languageCode);
        String isoLocale = Locale.getDefault().getLanguage();

        // Do the translation.
        Translate translate = TranslateOptions.getDefaultInstance().getService();
        Translation translation =
                translate.translate(originalText, Translate.TranslateOption.targetLanguage(isoLocale));
        String translatedText = translation.getTranslatedText();

        // Output the translation.
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println(translatedText);
    }
}