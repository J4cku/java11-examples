package io.github.j4cku.java11.http;

import static java.util.stream.Collectors.toList;

import com.google.gson.Gson;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Example {

    public static void syncGet(String uri) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build();

        HttpResponse<String> response =
            client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public static void asyncGet(String uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uri))
            .build();

        CompletableFuture<HttpResponse<String>> responseCompletableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        responseCompletableFuture.whenComplete((resp, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println(resp.body());
                System.out.println(resp.statusCode());
            }
        }).join();
    }

    public static void asyncPost() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        Gson gson = new Gson();
        Foo foo = new Foo("name", "url");
        String jsonBody = gson.toJson(foo);

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://httpbin.org/post"))
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .whenComplete((resp, t) -> {
                if (t != null) {
                    t.printStackTrace();
                } else {
                    System.out.println(resp.body());
                    System.out.println(resp.statusCode());
                }
            }).join();
    }

    public static void downloadFile() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://labs.consol.de/"))
            .GET()
            .build();

        Path tempFile = Files.createTempFile("consol-labs-home", ".html");
        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(tempFile));
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public static void uploadFile() throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("http://localhost:8080/upload/"))
            .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("/tmp/files-to-upload.txt")))
            .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        System.out.println(response.statusCode());
    }

    public static void proxy() throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .proxy(ProxySelector.of(new InetSocketAddress("127.0.0.1", 1080)))
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://www.google.com"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public static void basicAuth() throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .authenticator(new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("username", "password".toCharArray());
                }
            })
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://labs.consol.de"))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());
    }

    public static void http2() throws Exception {
        HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .version(HttpClient.Version.HTTP_2)
            .build()
            .sendAsync(HttpRequest.newBuilder()
                    .uri(new URI("https://http2.akamai.com/demo"))
                    .GET()
                    .build(),
                HttpResponse.BodyHandlers.ofString())
            .whenComplete((resp, t) -> {
                if (t != null) {
                    t.printStackTrace();
                } else {
                    System.out.println(resp.body());
                    System.out.println(resp.statusCode());
                }
            }).join();
    }

    public void getURIs(List<URI> uris) {
        HttpClient client = HttpClient.newHttpClient();
        List<HttpRequest> requests = uris.stream()
            .map(HttpRequest::newBuilder)
            .map(HttpRequest.Builder::build)
            .collect(toList());

        CompletableFuture.allOf(requests.stream()
            .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
            .toArray(CompletableFuture<?>[]::new))
            .join();
    }

    public static void main(String[] args) throws Exception {
//        syncGet("https://biezhi.me");
//        asyncGet("https://biezhi.me");
        asyncPost();
//        http2();
    }

}
