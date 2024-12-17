FROM amazoncorretto:21-alpine-jdk

RUN apk update && apk add wget ca-certificates

# Check available versions here: https://www.ubuntuupdates.org/package/google_chrome/stable/main/base/google-chrome-stable
ARG CHROME_VERSION="131.0.6778.139"
RUN wget --no-verbose -O /tmp/chrome.deb https://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/google-chrome-stable_${CHROME_VERSION}-1_amd64.deb \
  && apt install -y /tmp/chrome.deb \
  && rm /tmp/chrome.deb

 RUN sed -i 's/\(Exec.*\)/\1 --no-sandbox/g' /etc/chromium-browser/chrome-

 COPY target/*.jar app.jar
 ENTRYPOINT ["java","-jar","/app.jar"]