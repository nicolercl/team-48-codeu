package com.google.codeu.servlets;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletOutputStream;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * Takes requests that contain text and responds with an MP3 file speaking that text.
 */
@WebServlet("/a11y/tts")
public class TextToSpeech extends HttpServlet {

    private TextToSpeechClient ttsClient;

    @Override
    public void init() {
//        ttsClient = TextToSpeechClient.create();
        try {
            System.out.println("Creating...");
            ttsClient = TextToSpeechClient.create();
        }catch(Exception e){
            e.getCause().getStackTrace();
        }
    }

    /**
     * Responds with an MP3 bytestream from the Google Cloud Text-to-Speech API
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuilder buf = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buf.append(line);
        }
        String data = buf.toString();

        String text = Jsoup.clean(data, Whitelist.none());

        // Text to Speech API
        SynthesisInput input = SynthesisInput.newBuilder()
                .setText(text)
                .build();

        VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
                .setLanguageCode("en-US")
                .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                .build();

        AudioConfig audioConfig = AudioConfig.newBuilder()
                .setAudioEncoding(AudioEncoding.MP3)
                .build();

        SynthesizeSpeechResponse speechResponse = ttsClient.synthesizeSpeech(input, voice,
                audioConfig);

        ByteString audioContents = speechResponse.getAudioContent();

        // Write the response to the output file.
        String responseFromAPI = "output.mp3";

        try (OutputStream out = new FileOutputStream(new File(responseFromAPI))) {
            out.write(audioContents.toByteArray());
            System.out.println("Audio content written to file \"output.mp3\"");
        }

        // Write output file to ServletOutputStream
        response.setContentType("audio/mpeg");

        try (
                ServletOutputStream output = response.getOutputStream();
                InputStream input_holder = getServletContext().getResourceAsStream('/' + responseFromAPI); // Placeholder!
        ) {
            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = input_holder.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
    }
}