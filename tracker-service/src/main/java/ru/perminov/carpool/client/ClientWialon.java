package ru.perminov.carpool.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ClientWialon {
    String clientId = "GpsTracker";
    String accessType = "256";
    String activationTime = "0";
    String duration = "0";
    String lang = "ru";
    String flags = "0";


    public void getToken(String username, String password) throws IOException, URISyntaxException {
        URI url = new URI("https://hosting.wln-hst.com/login.html?client_id=GpsTracker&access_type=-1&lang=ru");

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Андрей\\Desktop\\chrome-win64\\chrome.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate();


//            System.out.println(document);

            // Проверка, найдена ли форма
//            if (authForm != null) {
//                System.out.println("Форма найдена.");
//            } else {
//                System.out.println("Форма не найдена.");
//            }

//            System.out.println(document);
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        ClientWialon clientWialon = new ClientWialon();
        clientWialon.getToken("ntks_test", "ntks_test");
    }
}
