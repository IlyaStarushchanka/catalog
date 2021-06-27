# Build your application with this image called "build"
FROM openjdk:8-alpine AS build

# Add the required packages
RUN apk update && apk upgrade && apk add bash

# Add your specifc dependencies
RUN cd /usr/local/bin && \
    wget https://services.gradle.org/distributions/gradle-5.6-all.zip && \
    /usr/bin/unzip gradle-5.6-all.zip && \
    ln -s /usr/local/bin/gradle-5.6/bin/gradle /usr/bin/gradle

# Copy your code in the build container and move into it
RUN mkdir -p /catalog
COPY . /catalog
WORKDIR /catalog

# Build your application
RUN gradle build -x test

# The container that will run
FROM openjdk:8-alpine AS run

# Choose the port to publicly expose to the internet
EXPOSE 8080

# Add required packages
RUN apk update && apk upgrade && apk add shadow

# Create a dedicated user to run your app with (for security reasons)
RUN useradd -ms /bin/bash qovery
USER qovery

# Get the build artifact (can be a folder)
COPY --from=build /catalog/build/libs/catalog-0.0.1-SNAPSHOT.jar /catalog.jar

# Set specific environment variables
ENV JAVA_OPTS=""
# Command to run your application
CMD exec java $JAVA_OPTS -jar /catalog.jar