package ru.perminov.carpool.client;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Driver;

public class ClientWialon {
    String clientId = "GpsTracker";
    String accessType = "256";
    String activationTime = "0";
    String duration = "0";
    String lang = "ru";
    String flags = "0";


    public void getToken(String username, String password) throws IOException, URISyntaxException {
        URI url = new URI("https://hosting.wln-hst.com/login.html?client_id=GpsTracker&access_type=-1&lang=ru");
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions fo = new FirefoxOptions()
        FirefoxDriver driver = new FirefoxDriver()
        driver.get(String.valueOf(url));




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
