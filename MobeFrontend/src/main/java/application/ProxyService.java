package application;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.*;
import exceptions.CompteUtilisateurAdminInexistant;
import objects.musee.Exposition;
import objects.musee.Oeuvre;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyService {
    public static HttpClient httpClient = HttpClient.newHttpClient();

    private static final String URI_AUTHENT ="http://localhost:8088/mobe/authentification";
    private static final String URI_MUSEE ="http://localhost:8088/mobe/musee";
    private static final String URI_PAIEMENT ="http://localhost:8088/mobe/paiement";

    private static final String OEUVRES_PATH = "/oeuvre";
    private static final String SALLES_PATH = "/salle";
    private static final String EXPOSITIONS_PATH = "/exposition";
    private static final String USER_CONNEXION_PATH = "/user";
    private static final String USER_CREATION_PATH = "/userCreation";
    private static final String HELLO_PATH = "/hello";
    private static final String USER_PATH = "/user";
    private static final String PAIEMENT_PATH = "/moyenPaiement";
    private static final String TRANSACTION_PATH = "/transaction";


    public static ObjectMapper objectMapper = new ObjectMapper();

    public static String connecter(String login, String mdp) throws IOException, InterruptedException, CompteUtilisateurAdminInexistant {

        /* TODO à terme, récupérer l'User à partir de son login */

        JsonFactory factory = new JsonFactory();
        StringWriter body = new StringWriter();
        JsonGenerator generator = factory.createGenerator(body);
        generator.useDefaultPrettyPrinter();
        generator.writeStartObject();
        generator.writeFieldName("login");
        generator.writeString(login);
        generator.writeFieldName("mdp");
        generator.writeString(mdp);
        generator.writeEndObject();
        generator.close();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_AUTHENT + USER_CONNEXION_PATH))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if(!response.headers().firstValue("Authorization").isEmpty()) {
            return response.headers().firstValue("Authorization").get();
        }
        else {
            System.out.println(response.headers().toString());
            throw new CompteUtilisateurAdminInexistant();
        }
    }

    public static String deconnecter(String login, String token) throws IOException, InterruptedException {

        JsonFactory factory = new JsonFactory();
        StringWriter body = new StringWriter();
        JsonGenerator generator = factory.createGenerator(body);
        generator.useDefaultPrettyPrinter();
        generator.writeStartObject();
        generator.writeEndObject();
        generator.close();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_AUTHENT + USER_CONNEXION_PATH + "/" + login))
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .DELETE()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body().toString();
    }

    public static List<RoleDTO> getRoles(String pseudo) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_AUTHENT + USER_CONNEXION_PATH + "/" + pseudo + "/roles"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body().toString());
        List<RoleDTO> rolesDTOS = objectMapper.readValue(response.body().toString(), new TypeReference<>(){});
        return rolesDTOS;
    }

    public static void ajouterOeuvre(Oeuvre oeuvre, String token, String login) throws IOException, InterruptedException {

        System.out.println(oeuvre.getDateAchat());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + OEUVRES_PATH))
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .header("login",login)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(oeuvre)))
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void supprimerOeuvre(int idOeuvre, String token, String login) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + OEUVRES_PATH + "/" + idOeuvre))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .header("login", login)
                .DELETE()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void modifierOeuvre(int idOeuvre, String token, String login) {
        /*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + OEUVRES_PATH + "/" + idOeuvre))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .header("login", login)
                .PATCH(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(exposition)))
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        */
    }

    public static void moveOeuvre(int idOeuvre, SalleDTO salle, String token, String login) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + OEUVRES_PATH + "/" + idOeuvre + "/deplacement"))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .header("login", login)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(salle)))
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static OeuvreDTO getOeuvreById(int idOeuvre, String token, String login) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + OEUVRES_PATH + "/" + idOeuvre))
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .header("login",login)
                .GET()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        OeuvreDTO oeuvre = objectMapper.readValue(response.body().toString(), new TypeReference<>(){});

        return oeuvre;
    }

    public static void ajouterExposition(Exposition exposition, String token, String login) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + EXPOSITIONS_PATH))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .header("login", login)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(exposition)))
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void supprimerExposition(int idExposition, String token, String login) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + EXPOSITIONS_PATH + "/" + idExposition))
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .header("login", login)
                .DELETE()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static SalleDTO getSalleById(int idSalle, String token, String login) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + SALLES_PATH + "/" + idSalle))
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .header("login",login)
                .GET()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        SalleDTO salle = objectMapper.readValue(response.body().toString(), new TypeReference<>(){});

        return salle;
    }

    public static List<OeuvreDTO> getOeuvres(String token, String login) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + OEUVRES_PATH))
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .header("login",login)
                .GET()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<OeuvreDTO> oeuvresDTOS = objectMapper.readValue(response.body().toString(), new TypeReference<>(){});

        return oeuvresDTOS;
    }

    public static List<SalleDTO> getSalles(String token, String login) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + SALLES_PATH))
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .header("login",login)
                .GET()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<SalleDTO> sallesDTOS = objectMapper.readValue(response.body().toString(), new TypeReference<>(){});

        return sallesDTOS;

    }

    public static List<ExpositionDTO> getExpositions(String token, String login) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URI_MUSEE + EXPOSITIONS_PATH))
                .header("Content-Type", "application/json")
                .header("Authorization",token)
                .header("login",login)
                .GET()
                .build();

        HttpResponse response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        List<ExpositionDTO> expositionsDTOS = objectMapper.readValue(response.body().toString(), new TypeReference<>(){});

        return expositionsDTOS;
    }

}