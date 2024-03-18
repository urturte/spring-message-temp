FROM amazoncorretto:21

WORKDIR /app

COPY build.gradle settings.gradle gradlew /app/
# Download and install Gradle
RUN ./gradlew --version

# Copy the project files
COPY . /app

# Build the project
RUN ./gradlew build

# Set the startup command
CMD ["java", "-jar", "build/libs/message.jar"]
