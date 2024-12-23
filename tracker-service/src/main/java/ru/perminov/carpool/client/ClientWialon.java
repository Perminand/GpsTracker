package ru.perminov.carpool.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.perminov.carpool.exceptions.errors.EntityNotFoundException;
import ru.perminov.carpool.model.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientWialon {
    private final static int WIALON_COUNT_EID = 300;

    private static final String HOSTAUTH = "http://hosting.wln-hst.com";
    private static final String HOSTWORK = "https://hst-api.wln-hst.com";

    @Value("${wialon.uri}")
    String wialonUrl;
    private static final String AG_URL = "/wialon/ajax.html?svc=token/login&params={\"token\":\"tokenValue\"}";
    private static final String CAR_URL = "/wialon/ajax.html?svc=core/search_items&params=";
    private RestTemplate restTemplate;

    public static void main(String[] args) throws IOException, URISyntaxException {
        ClientWialon clientWialon = new ClientWialon();

    }

    private static HttpURLConnection getHttpURLConnection(URL url, String params) throws IOException {
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        byte[] postData = params.getBytes(StandardCharsets.UTF_8);
        con.setDoOutput(true);
        con.setRequestProperty("Content-Length", Integer.toString(postData.length));
        // Запись данных запроса
        try (OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream())) {
            out.write(params);
        }
        return con;
    }

    public String getToken(String username, String password) throws URISyntaxException {
        log.info("Начали выполнение метода getToken");
        URI url = new URI(wialonUrl);
        StringBuilder sb = new StringBuilder();
        String resourcePath = "tracker-service/src/main/resources/driver/chromedriver";
        File chromedriver = new File(resourcePath);

        // Загружаем драйвер из classpath
        chromedriver.getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", chromedriver.getAbsolutePath());

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        WebDriver driver = new ChromeDriver(options);


        driver.get(String.valueOf(url));

        // Ждем полной загрузки страницы
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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

        Pattern pattern = Pattern.compile("access_token=([^&]*)");
        Matcher matcher = pattern.matcher(currentURL);
        String accessToken;
        if (matcher.find()) {
            accessToken = matcher.group(1);
        } else {
            throw new EntityNotFoundException("Пользователь wialon не найден");
        }
        driver.quit();
        return accessToken;
    }

    public List<Car> getCars(TokenWialon token, User user) throws IOException {
        List<Car> cars = new ArrayList<>();

        updateEid(token, user);
        URL url = new URL(HOSTWORK + "/wialon/ajax.html?svc=core/search_items&sid=" + user.getEid().getName() + "&params={\"spec\":{\"itemsType\":\"avl_unit\",\"propName\":\"profilefield\",\"propValueMask\":\"*\",\"sortType\":\"profilefield\"},\"force\":1,\"flags\":\"4611686018427387903\",\"from\":0,\"to\":0}");

        final HttpURLConnection con = getHttpURLConnection(url, "");

        log.info("Выполнение запроса и получение ответа");
        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            log.info("Данные получены");
            try (InputStreamReader isr = new InputStreamReader(con.getInputStream());
                 InputStream is = con.getInputStream()) {
                String jsonResponse = new String(is.readAllBytes());
                String urlOut = con.getURL().toString();
                System.out.println(con.getURL());
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray itemsArray = jsonObject.getJSONArray("items");

                List<JSONObject> items = new ArrayList<>();
                // Получаем машины
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject item = itemsArray.getJSONObject(i);
                    items.add(item);
                    long id = item.getLong("id");
                    cars.add(new Car(id));
                }
                user.getEid().setCount(user.getEid().getCount() + 1);
                return cars;
            }
        } else {
            System.out.println("Ошибка: Не удалось получить ответ.");
        }

        return null;
    }

    private void updateEid(TokenWialon token, User user) throws IOException {
        Eid eid = user.getEid();
        if (eid == null || eid.getCount() >= WIALON_COUNT_EID || eid.getEndTime().isBefore(LocalDateTime.now())) {
            log.info("Начался процесс получения eid");
            URL url = new URL(HOSTAUTH + AG_URL.replace("\"tokenValue\"", "\"" + token.getName() + "\""));
            String params = "token=" + token.getName();
            final HttpURLConnection con = getHttpURLConnection(url, params);

            log.info("Выполнение запроса и получение ответа");
            int responseCode = con.getResponseCode();

            if (responseCode == 308) {
                log.info("Данные получены");
                try (InputStreamReader isr = new InputStreamReader(con.getInputStream());
                     InputStream is = con.getInputStream()) {
                    String jsonResponse = new String(is.readAllBytes());
                    JSONObject jsonObject = new JSONObject(jsonResponse);

                    // Извлечение значения eid из JSON-ответа
                    user.setEid(new Eid(jsonObject.getString("eid")));
                }
            } else {
                System.out.println("Ошибка: Не удалось получить ответ.");
            }
        }
    }

    public void getSensors(TokenWialon tokenWialon, User user, Car c) throws IOException {
        List<Sensors> sensorsList = new ArrayList<>();

        updateEid(tokenWialon, user);
        URL url = new URL(HOSTWORK + "/wialon/ajax.html?svc=core/search_item&params={\"id\":\"" + c.getId() + "\",\"flags\":4096}&sid=" + user.getEid().getName());

        final HttpURLConnection con = getHttpURLConnection(url, "");

        log.info("Выполнение запроса и получение ответа");
        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            log.info("Данные получены");
            try (InputStreamReader isr = new InputStreamReader(con.getInputStream());
                 InputStream is = con.getInputStream()) {
                String jsonResponse = new String(is.readAllBytes());
                System.out.println(con.getURL());
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject jsonItems = jsonObject.getJSONObject("item");
                JSONObject jsonSens = jsonItems.getJSONObject("sens");


                List<JSONObject> sens = new ArrayList<>();


            }
        }
    }

    public List<Item> getItem(TokenWialon tokenWialon, User user, Car c) throws IOException {
        List<Item> items = new ArrayList<>();
        updateEid(tokenWialon, user);
        URL url = new URL(HOSTWORK + "/wialon/ajax.html?svc=messages/load_interval&params={\"itemId\":17142323,\"timeFrom\":0,\"timeTo\":0,\"flags\":0,\"flagsMask\":0,\"loadCount\":10}&sid=" + user.getEid().getName());

        final HttpURLConnection con = getHttpURLConnection(url, "");

        log.info("Выполнение запроса и получение ответа");
        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            log.info("Данные получены");

            try (InputStreamReader isr = new InputStreamReader(con.getInputStream());
                 InputStream is = con.getInputStream()) {

                String jsonResponse = new String(is.readAllBytes());

                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONArray itemsArray = jsonObject.getJSONArray("messages");

                List<JSONObject> jsonObjectList = new ArrayList<>();
                // Получаем сообщения
                for (int i = 0; i < itemsArray.length(); i++) {
                    JSONObject object = itemsArray.getJSONObject(i);
                    long time = object.getLong("t");
                    JSONObject objectP = object.getJSONObject("p");
                    long msg_number = objectP.getLong("msg_number");
                    Item item = new Item();
                    Instant instant = Instant.ofEpochSecond(time);
                    ZoneId zoneId = ZoneId.of("UTC");
                    ZonedDateTime zonedDateTime = instant.atZone(zoneId);
                    item.setCreated(zonedDateTime);
                    item.setId(msg_number);
                    item.setCar(c.getName());
                    item.setNumber(c.getNumber());
                    item.setMessage("123");
                    items.add(item);


                }
            }
        }
        user.getEid().setCount(user.getEid().getCount() + 1);
        c.setItems(items);
        return items;
    }
}