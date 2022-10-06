# Alkemy Bank Base


[![N|Solid](https://landing.alkemy.org/hs-fs/hubfs/LOGO%20ALKEMY%20AZUL%20CON%20NEGRO.png?width=300&height=83&name=LOGO%20ALKEMY%20AZUL%20CON%20NEGRO.png)]()



Estructura que se utilizara para crear el proyecto en la aceleración de Kotlin

## Tools

- Kotlin 1.3.x
- Android Studio 2021.2.1 Patch 1 o superior


## Requerimientos

- Compile Sdk 32
- MinSdk 26
- TargetSdk 32



## Firebase

Para poder compilar la app es necesario que crees un proyecto de prueba en Firebase ya que se utilizan servicios de mensajería para su funcionamiento. Los pasos a seguir son:

Ingresar en https://console.firebase.google.com/ con una cuenta de Google.
Creá un proyecto de prueba, bajá google-services.json y colocalo en app/.
Guía: https://firebase.google.com/docs/android/setup?hl=es

## Librerias Recomendadas

- [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=es-419)
- [Retrofit](https://square.github.io/retrofit/)
- [Navigation Components](https://developer.android.com/guide/navigation/navigation-getting-started?hl=es-419)
- [View Model y Live Data](https://developer.android.com/topic/libraries/architecture/livedata?hl=es-419)
- [Glide](https://github.com/bumptech/glide)
- [Inyeccion de dependencias con Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419)




## Code Standards
- Mantener la Guia de estilos de Kotlin (https://developer.android.com/kotlin/style-guide?hl=es-419)
- El código debe estar siempre comentado, de preferencia en inglés.
- Mantener la estructura de paquetes brindada para tener un mejor orden de los archivos


## Git Standards
- Siempre crear una rama para el desarrollo
- La rama debe tener el formato: feature/{jiraTicket#}.
- El formato del pull request debe ser: {jiraTicket#}: {jiraTitle}.
- El formato del commit debe ser: {jiraTicket#}: {commitDescription}.
- Los pull request deben contener unicamente los cambios que el ticket solicita
- Los pull request solo deben enviarse a la rama desarrollo

## Cómo colaborar en el proyecto

*   Fork del repositorio
*   Clonar el repositorio
*   Actualizar la rama master
*   Crear una rama
*   Hacer los cambios
*   Hacer un Pull Request

### Fork del repositorio

El primer paso es hacer "Fork" del repositorio.

### Clonar el repositorio

Después de tener el repositorio en nuestra cuenta, seleccionar la dirección del repositorio "SSH o HTTP" y clonar:

`$ git clone https://github.com/User/NombreRepo.git`

Dentro de la carpeta que genera, comprobar la URL del repositorio:

`$ git remote -v`

Antes de realizar modificaciones agregar la URL del repositorio original del proyecto:

`$ git remote add upstream https://github.com/User/RepoOriginal(Forkeado)`

Comprobar

`$ git remote -v`

### Actualizar la rama Master

Antes de empezar a trabajar, obtener los últimos cambios del Repo Original:

`$ git pull -r upstream master`

### Crear una Rama

Para crear una rama usar la opción "checkout" de git:

`$ git checkout -b feature-nombre-rama`

### Hacer cambios

Realizar todos los cambios que se desea hacer al proyecto.

Agregar los archivos y hacer un commit

Después de realizar el commit hacer el push hacia nuestro repositorio indicando la rama que hemos creado.

`$ git push origin feature-nombre-rama`

### Hacer un Pull Request

Hacer click en "Compare & Pull Request"

Escribir cambios del Pull Request.

Si todo está bien, enviar con el botón "Send Pull Request".

Esperar a que el duelo del repositorio lo revise, acepte y mezcle en la rama correspondiente.


## Arquitectura

La aplicación utiliza el patrón de arquitectura MVVM (Model-View-ViewModel).




