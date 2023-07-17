ARG JAVA_VERSION=17
FROM mcr.microsoft.com/azure-functions/java:4-java$JAVA_VERSION-build AS installer-env

COPY . /src/java-function-app
RUN apt-get update
RUN apt-get install npm -y
RUN npm install -g azure-functions-core-tools@3 --unsafe-perm true
RUN cd /src/java-function-app && \
    mkdir -p /home/site/wwwroot && \
    mvn clean package && \
    cd ./target/azure-functions/ && \
    cd $(ls -d */|head -n 1) && \
    cp -a . /home/site/wwwroot

FROM mcr.microsoft.com/azure-functions/java:4-java$JAVA_VERSION-appservice

ENV AzureWebJobsScriptRoot=/home/site/wwwroot \
    AzureFunctionsJobHost__Logging__Console__IsEnabled=true

COPY --from=installer-env ["/home/site/wwwroot", "/home/site/wwwroot"]
