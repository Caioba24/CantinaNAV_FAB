package com.cantinatoshio.app;

import com.cantinatoshio.app.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class getData extends Thread
{

    String id;
    String data = "";
    StringBuilder sb = new StringBuilder();
    String request;

    public getData(String request)
    {
        this.request = request;
    }


    @Override
    public void run()
    {

        try {
            URL url = new URL(request);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //propriedades da conexão
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String response;
                response = bufferedReader.readLine();

                //em vez de while, use if
                if (response != null) {
                    sb.append(response);
                }
            }
            data = sb.toString();

            switch (request)
            {
                case Api.URL_LAST_IDPEDIDO:
                    if (!(data.isEmpty()))
                    {
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray jsonArray = jsonObject.getJSONArray("LastID");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject ids = jsonArray.getJSONObject(i);
                            id = ids.getString("IDPedido");
                            new Pedido().setIdPedido(Integer.parseInt(id));
                            //System.out.println("SAINDO DA GET DATA");
                            //System.out.println("SEU ID É " + id);
                        }
                    }
                break;
                case Api.URL_CLIENTE_PEDIDOS:
                    if(!(data.isEmpty()))
                    {
                        System.out.println("SAÍDA DOS DADOS");
                        System.out.println(data);
                        ArrayList<Pedido> pedidos = new ArrayList<>();
                        JSONObject jsonObject = new JSONObject(data);
                        JSONArray jsonArray = jsonObject.getJSONArray("Pedidos");
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            pedidos.add(new Pedido(
                                    obj.getInt("IDPedido"),
                                    obj.getInt("IDCliente"),
                                    obj.getString("DataPedido"),
                                    obj.getDouble("ValorPedido")
                            ));
                        }
                        new Cliente().setPedidos(pedidos);

                    }
            }
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }

    }


}
