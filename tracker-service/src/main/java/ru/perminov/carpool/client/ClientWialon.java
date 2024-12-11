package ru.perminov.carpool.client;

import jakarta.persistence.ElementCollection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ClientWialon {
    String clientId = "GpsTracker";
    String accessType = "256";
    String activationTime = "0";
    String duration = "0";
    String lang = "ru";
    String flags = "0";


    public void getToken(String username, String password) throws IOException {
        URL url = new URL("https://hosting.wln-hst.com/login.html?client_id=GpsTracker&access_type=-1&lang=ru");

        try {
            // Загрузка веб-страницы с помощью Jsoup
            Document document = Jsoup.connect(url.toExternalForm()).get();
            Elements el = document.getAllElements();

            // Поиск элемента, который вызывает функцию getMainTemplate
            Element getMainTemplateElement = document.getAllElements().get(14);
            getMainTemplateElement.executeJavaScript("getMainTemplate()");
            System.out.println(getMainTemplateElement);


             // Проверка, найден ли элемент
            if (getMainTemplateElement != null) {
                System.out.println("Элемент найден.");
            } else {
                System.out.println("Элемент не найден.");
            }


//            System.out.println(document);

            // Проверка, найдена ли форма
//            if (authForm != null) {
//                System.out.println("Форма найдена.");
//            } else {
//                System.out.println("Форма не найдена.");
//            }

//            System.out.println(document);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        ClientWialon clientWialon = new ClientWialon();
        clientWialon.getToken("ntks_test", "ntks_test");
    }
}
