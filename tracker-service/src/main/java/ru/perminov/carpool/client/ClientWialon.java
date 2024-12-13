package ru.perminov.carpool.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;


public class ClientWialon {
    private String url;
    String clientId = "GpsTracker";
    String accessType = "256";
    String activationTime = "0";
    String duration = "0";
    String lang = "ru";
    String flags = "0";

    public ClientWialon(){};

    public ClientWialon(String clientId, String accessType, String activationTime, String duration, String lang, String flags) {
        this.clientId = clientId;
        this.accessType = accessType;
        this.activationTime = activationTime;
        this.duration = duration;
        this.lang = lang;
        this.flags = flags;
    }

    public String getToken(String username, String password) throws URISyntaxException {
        URI url = new URI("https://hosting.wln-hst.com/login.html?client_id=GpsTracker&access_type=-1&lang=ru");
        StringBuilder sb = new StringBuilder();
        String resourcePath = "tracker-service/src/main/resources/driver/chromedriver";
        File chromedriver = new File(resourcePath);

        // Загружаем драйвер из classpath
        chromedriver.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);


        driver.get(String.valueOf(url));

        // Ждем полной загрузки страницы
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("auth-form")));

        // Находим форму на странице
        WebElement form = driver.findElement(By.id("auth-form"));

        WebElement usernameField = form.findElement(By.id("login"));
        usernameField.sendKeys(username);

        WebElement passwordField = form.findElement(By.id("password"));
        passwordField.sendKeys(password);

        // Находим и нажимаем кнопку "Войти"
        WebElement submitButton = form.findElement(By.cssSelector("input[value='Войти']"));
        submitButton.click();

        String currentURL = driver.getCurrentUrl();
        String accessToken = currentURL.split("&")[2].split("=")[1];
        System.out.println(accessToken);
        driver.quit();

        return accessToken;







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
