package com.carclean.dao;

import com.carclean.beans.Veiculo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Fernando Benitez on 27/10/2015.
 */
public class VeiculoService {
    private static final String URL = "https://openws.herokuapp.com/veiculos";
    private static final String API_KEY = "?apiKey=2035742ce7143a5139c3e46a629d1c15";

    public List<Veiculo> getAll() {
        List<Veiculo> veiculo = new ArrayList();
        HttpURLConnection urlConnection = null;

        try {
            java.net.URL url = new URL(URL+API_KEY);

            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.useDelimiter("\\A").next();

            Gson gson = new Gson();
            veiculo = gson.fromJson(conteudo, new TypeToken<List<Veiculo>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            urlConnection.disconnect();
        }
        return veiculo;
    }

    public void post(Veiculo veiculo) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL+API_KEY);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            Writer w = new BufferedWriter(new OutputStreamWriter(out));

            Gson gson = new Gson();
            String json = gson.toJson(veiculo);

            w.write(json);
            w.close();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            Scanner s = new Scanner(in);
            String conteudo = s.nextLine();
            in.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
    }

    public void delete(String id) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(URL+"/"+id+API_KEY);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("DELETE");

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            urlConnection.disconnect();
        }
    }
}

