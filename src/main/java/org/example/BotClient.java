package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

public class BotClient {
    public static final String API_SERVICE_DOMAIN = "https://d5dc9tpogca5a0mflma8.zj2i1qoy.apigw.yandexcloud.net";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String sendToBot(String message, String userId) throws Exception {

        RequestBody body = new RequestBody(message, userId);
        String jsonBody = mapper.writeValueAsString(body);


        HttpPost post = new HttpPost(API_SERVICE_DOMAIN);
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity(jsonBody, StandardCharsets.UTF_8));


        try (CloseableHttpClient client = HttpClients.createDefault()) {
            return client.execute(post, response -> {
                //System.out.println("Request: " + jsonBody);
                String raw_json = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                JsonNode rootNode = mapper.readTree(raw_json);
                if (rootNode.has("response")) {
                    return rootNode.get("response").asText();
                }
                return "Error in json";
            });
        }
    }
}
