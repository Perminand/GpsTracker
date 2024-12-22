FROM xubuntu

RUN apt update && apt install -y wget ca-certificates

RUN apt install -y openjdk-21-jdk

# Check available versions here: https://www.ubuntuupdates.org/package/google_chrome/stable/main/base/google-chrome-stable
ARG CHROME_VERSION="131.0.6778.139"
RUN wget --no-verbose -O /tmp/chrome.deb https://dl.google.com/linux/chrome/deb/pool/main/g/google-chrome-stable/google-chrome-stable_${CHROME_VERSION}-1_amd64.deb
RUN apt install -y /tmp/chrome.deb

RUN rm /tmp/chrome.deb

COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]