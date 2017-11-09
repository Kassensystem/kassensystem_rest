package dhbw.sa.databaseApplication.restApi.client;

import dhbw.sa.databaseApplication.database.entity.Item;
import dhbw.sa.databaseApplication.database.entity.Order;
import dhbw.sa.databaseApplication.database.entity.Table;
import dhbw.sa.databaseApplication.exceptions.ControllerConnectionException;
import dhbw.sa.databaseApplication.exceptions.MySQLException;
import dhbw.sa.databaseApplication.exceptions.NoContentException;
import dhbw.sa.databaseApplication.restApi.RestApiProperties;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.*;

import java.net.URI;
import java.util.ArrayList;

/**
 * ------------Erläuterung der Exceptions------------
 * Bei ResourceAccessException: Der Controller ist nicht erreichbar
 * --> HttpServerErrorException: Der DatabaseService ist nicht erreichbar.
 */

public class RestApiClient implements RestApiClient_Interface {

    private static final String REST_SERVICE_URL = RestApiProperties.getRestServiceUrl();

    /******************TEST*******************/
    public static String test()
            throws ControllerConnectionException,
            MySQLException,
            NoContentException {
        /**
         * TODO Lösung für Exceptions-Empfang finden
         */

        System.out.println("Testing connection to RestApiController...");

        RestTemplate restTemplate = new RestTemplate();
        String s = null;
        ResponseEntity<String> responseEntity;
        //s = restTemplate.getForObject(REST_SERVICE_URL + "/test", String.class);
        try {
            responseEntity = restTemplate.exchange
                    (REST_SERVICE_URL + "/test", HttpMethod.GET, null, new ParameterizedTypeReference<String>() {});
            return responseEntity.getBody();

        } catch (HttpServerErrorException e) {
            //e.printStackTrace();
            /**
             * Controller ist erreichbar aber meldet
             * eine Exception. Fehler-Code muss extrahiert werden.
             */
            System.out.println("------\nController meldet Fehler\n------");
            switch(e.getResponseBodyAsString()) {
                case "SERVICE_UNAVAILABLE":
                    throw new MySQLException();
                case "NO_CONTENT":
                    throw new NoContentException();
            }
        } catch (ResourceAccessException e) {
            /**
             * Rest-Api-Controller ist nicht erreichbar.
             */
            throw new ControllerConnectionException();
        }
        return null;
    }


    /*******************GET*******************/

    public ArrayList<Item> getAllItems() {
        System.out.println("Getting all items...");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ArrayList<Item>> responseEntity =
                restTemplate.exchange
                        (REST_SERVICE_URL + "/items", HttpMethod.GET,
                                null, new ParameterizedTypeReference<ArrayList<Item>>() {});
        ArrayList<Item> items = responseEntity.getBody();

        return items;
    }

    public ArrayList<Table> getAllTables() {
        System.out.println("Getting all tables...");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ArrayList<Table>> responseEntity =
                restTemplate.exchange
                        (REST_SERVICE_URL + "/tables", HttpMethod.GET,
                                null, new ParameterizedTypeReference<ArrayList<Table>>() {});
        ArrayList<Table> tables = responseEntity.getBody();

        return tables;
    }

    public ArrayList<Order> getAllOrders() {
        System.out.println("Getting all orders...");

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ArrayList<Order>> responseEntity =
                restTemplate.exchange
                        (REST_SERVICE_URL + "/orders", HttpMethod.GET,
                                null, new ParameterizedTypeReference<ArrayList<Order>>() {});
        ArrayList<Order> orders = responseEntity.getBody();

        return orders;
    }

    /******************POST*******************/

    public void createOrder(Order order) {
        System.out.println("Creating order...");

        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URL + "/order/", order, Order.class);
    }

    public void updateOrder(int orderID, Order order) {
        System.out.println("Updating order...");

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_SERVICE_URL + "/order/" + orderID, order);
    }

}