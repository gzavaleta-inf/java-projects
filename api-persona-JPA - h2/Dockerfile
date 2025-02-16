#A partir de que imagen vamos a crear la imagen
FROM openjdk:17
#Para la carpeta temporal
VOLUME /tmp

#Para subir la imagen en alguna carpeta dentro de docker, solo cuando se trabaja con ambientes
#/ENV IMG_PATH=/img
#Para crear el directorio dentro del contenedor
#RUN mkdir -p /img
#Para agregar metadatod <clave>=<valor>
#LABEL authors="Administrador"

#Puerto donde lo vamos a exponer
EXPOSE 8080
|
#Agregamos el archivo ejecutable
ADD target/api-persona-JPA-2.4.5.jar app.jar

#Comandos que se van a ejecutar cuando se inicie la imagen
ENTRYPOINT ["java", "-jar", "/app.jar"]