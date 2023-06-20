## Spring Boot

Proyecto Spring con las depedencias / starters:
* H2 
* Spring Data JPA
* Spring Web
* Spring Boot DevTools

Aplicacion API REST con acceso a base de datos H2 para persistir la información.

El acceso se puede realizar desde Postman o Navegador.

## Entidad Book

1. Book
2. BookRepository
3. BookService
4. BookController para poder acceder desde una URL
   1. Buscar todos los libros
   2. Buscar un solo libro
   3. Crear un nuevo libro
   4. Actualizar un libro existente
   5. Borrar un libro
   6. Borrar todos los libros


##Archivo application.properties
1. Por seguridad aplicamos las configuraciones:
   server.error.include-message=never
   server.error.include-stacktrace=never
   Esto sirve para no exponer los errores al front, cuando hay un error en el path de una peticion, se devuelve un archivo JSON con el valor 404.