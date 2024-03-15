# Multistage Build Docker

# Stage 1# 
# Start with a base image containing Java runtime
FROM eclipse-temurin:21 as build                                    

# Add Maintainer Info
LABEL maintainer="Bruno Ferro <dev.omega.lambda@outlook.com"

# The application's jar file
ARG JAR_FILE                                               

# Add the application's jar to the container
COPY ${JAR_FILE} app.jar

# Unpackage jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

# ------------------------------------------------------------------------------

# Stage 2#
# Same Java runtime 
FROM eclipse-temurin:21

# Add volume pointing to /tmp
VOLUME [ "/tmp" ]

# Copy unpackage dependency to new container
ARG DEPENDECY=/target/dependency
COPY --from=build ${DEPENDECY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDECY}/META-INF /app/META-INF
COPY --from=build ${DEPENDECY}/BOOT-INF/classes /app

#execute the application
ENTRYPOINT ["java","-cp","app:app/lib/*","dev.omegalambda.licensingservice.LicensingServiceApplication"]   