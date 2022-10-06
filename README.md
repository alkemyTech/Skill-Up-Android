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


## Arquitectura

La aplicación utiliza el patrón de arquitectura MVVM (Model-View-ViewModel).