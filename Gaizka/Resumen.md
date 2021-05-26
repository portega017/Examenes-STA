# Examen STA

## Configuración

1. Crear base de datos, o importar una que te den.

2. Forward Engineer de la base de datos.

3. Añadimos las entradas al standalone/configuration/standalone.xml. IMPORTANTE No olvidarse de la parte de driver, sino no funciona nada.

   ![](https://i.imgur.com/ORHwStD.png)

   

4. Añadimos  las entradas al persistence.xml (Dentro de los paquetes de  JAVA, carpeta META-INF)

   ![](https://i.imgur.com/qayp8Mg.png)

   

5. Añadimos el modulo a la ruta: modules/system/layers/base/com/mysql/main. IMPORTANTE Buscar que exista, que la ruta corresponde y que es la misma versión, si no no lo encuentra y pasan cosas.

   ![](https://i.imgur.com/lZooYrV.png)

   

6. Nos conectamos desde eclipse a la base de datos.

7. Creamos el dynamic web project seleccionando como runtime el Wildfly.

8. Clickamos en que genere el web.xml.

9. En project facets:

   1. Activar CDI (Para EJB). En further configuration clickar en que genere beans.xml.
   2. Cambiar versión de JAVA a 11.
   3. Activar Java Server Faces (Para JSF, XHTML).
   4. Activar JPA. En further configuration elegir library provided by runtime.

10. Prueba.html para probar Wildfly.

11. Creamos los paquetes.



## Paquetes

### Data Access Layer

1. New > Create JPA entities from tables. 
2. De-seleccionar incluirlas en persistence.xml
3. Quitar relaciones bidireccionales que no correspondan.
4. Paquete dl.
5. Mirar que todos los tipos de datos correspondan.
6. Named queries. Hacer siempre la de ID (Se usa casi siempre).

### Anotación Transient

Nos permite añadir un campo a un JPA entity pero sin que se persista en la base de datos. 

Ejemplo. Queremos tener un método que devuelva el nombre abreviado de una persona (Para mostrarlo por ejemplo), pero, en la BD lo que queremos es añadir el nombre completo.

```java
@Transient // Nos permite indicar que no queremos que este modulo persista en la BD
public String getNameAbrev() {
return name.substring(0,1).concat("."); // Cogemos la primera letra del nombre y le concatenamos un punto al final
}
```



### Business Layer

##### Esqueleto básico EJB

```java
package bl;

// Fijarse en los imports que como importes mal la has liado
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless // Para indicar que no tiene estado (Independiente)
@LocalBean // Para indicar que la conexión se hace en local y con un bean
public class LogicaNegocio {

	@PersistenceContext // Para indicar que es el manejador del contexto de persistencia
	private EntityManager em;	
}
```

##### Alta (Sin claves foraneas o SelectOneMenus)

```java
// Codigos de error	
private final int OK=0;
private final int Exception=1;
private final int Exists=2;	

// Le pasas el bean que quieres dar de alta y devuelve el codigo de error
public int alta(Bean b) {
    try {
        if(em.createNamedQuery("Bean.findByName")
           .setParameter("nombre",b.getName())
           .getResultList().isEmpty()) {
            em.persist(b);
            return OK;
        }else {
            return Exists;
        }
    }catch(Exception ex) {
        ex.getCause();
        return Exception;
    }	
}
```

##### Alta (Con una única FK)

```java
// Le pasas el bean que quieres dar de alta y el indice de la FK
public int alta(Bean b, int idx) {
		try {
			Bean1 b1 = new Bean1();
            if(em.createNamedQuery("Bean.findByName")
               .setParameter("nombre", b.getName())
               .getResultList().isEmpty()) { // Comprobamos que no exista ya el elemento que queremos añadir en la base de datos
				
                if(!em.createNamedQuery("Bean1.findById")
                   .setParameter("id", idx)
                   .getResultList().isEmpty()) { // Comprobamos que exista el elemento asociado a la FK

					p.setJournalBean(
                    	(Journal) em.createNamedQuery("Journal.findById")
                        .setParameter("id", idxRevista).getSingleResult()
                    ); // Seteamos en el bean principal el elemento que hemos buscado con idx
					
                    em.persist(p); // Persistimos el elemento

                    
                    // Manejamos los codigos de errores
                    return OK;
                }else {
                    return Exception;
                }
            }else {
                return Exists;
            }
        }catch(Exception ex) {
            System.err.println(ex.getCause());
            return Exception;
        }	
}
```



##### Alta (Relación n a n con tabla intermedia)

```java
// Le pasas el bean que quieres dar de alta y los dos indices de las FK
public int alta(Bean b, int idx1, int idx2) {
    try {
        Bean1 b1 = new Bean1(); // Creamos el bean que corresponde a la tabla intermedia 
        if(!em.createNamedQuery("Bean1.findById")
              .setParameter("id", idx1)
              .getResultList().isEmpty()) { // Comprobamos que no exista el bean secundario en la base de datos
              
             if(!em.createNamedQuery("Bean2.findById")
                   .setParameter("id", idx2)
                   .getResultList().isEmpty()) { // Comprobamos que no exista el bean terciario en la base de datos
            
                    p.setFK(
                        (Bean)em.createNamedQuery("Bean.findById")
                    	.setParameter("id", idx1)
                    	.getSingleResult()
                    ); // Seteamos el bean secundario en el principal (El secundario es la clave foranea del primario). Para ello hacemos una named con el id del secundario y lo añadimos al atributo correspondiente del primario
                    
                    em.persist(b); // Persistimos el bean primario
                    
                    b1.setBean1(
                        (Bean1) em.createNamedQuery("Bean1.findById")
                    	.setParameter("id", idx1)
                    	.getSingleResult()
                    ); // Seteamos el primer campo de la tabla intermedia de la relacion n a n (En este caso hay que buscarlo en la BD primero)
                    
                    b1.setBean((Bean) b); // Seteamos el segundo campo de la tabla intermedia de la relacion n a n (En este caso se añade directamente el elemento principal ya que es el atributo que tiene la tabla)
                    
                    em.persist(b1); // Persistimos el elemento de la tabla intermedia
                    
                    // Manejamos los codigos de errores
                    return OK;
                }else {
                    return Exception;
                }
            }else {
                return Exception;
            }
    }catch(Exception ex) {
        System.err.println(ex.getCause());
        return Exception;
    }	
}
```



##### Get

```java
// Devuelve la lista de beans
public List<Beans> getBean() {
    	// Named query que devuelve todos los elementos de la tabla
		return em.createNamedQuery("Bean.findAll",Bean.class)
            	 .getResultList(); 
}
```



### Presentation Layer

##### Beans de cada tabla de la base de datos

```java
package pl.modelo;

import java.io.Serializable; // Importantisimo el serializable

// Fijarse en los imports que como importes mal la has liado
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Tabla;

@Named // Para darle nombre
@RequestScoped // Para indicar que el ciclo de vida durará un request
public class Bean implements Serializable{ // Importantisimo el serializable
	
	private final Tabla entity = new Tabla();
	private static final long serialVersionUID = 1L;
	
	public Tabla getEntity() {
		return entity;
	}

}
```

##### Bean de funciones (esqueleto)

```java
package pl;

import java.io.Serializable; // Importantisimo el serializable

// Fijarse en los imports que como importes mal la has liado
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.LogicaNegocio;

@Named // Para darle nombre
@ViewScoped // Para que el ciclo de vida durará una vista
public class AppBean implements Serializable {

	@EJB // Para indicar que se va a llamar por EJB a la logica de negocio
	private LogicaNegocio ln = new LogicaNegocio();
	private static final long serialVersionUID = 1L;
	private int codError;
    private boolean renderResp;
	private String resp;
    
    // Los getter y setter de todo lo de arriba (Excepto LogicaNegocio y el serial ese de mierda)
}
```

#### AppBean

##### Atributos mas importantes

```java
@EJB
private LogicaNegocio ln = new LogicaNegocio(); // Objeto para llamar a metodos de la logica de negocio

private static final long serialVersionUID = 1L; // Lo pone el serializable

private int codError; // Representa el codigo de error que obtienen ciertas funciones (Para controlar lo que ha pasado, si se ha dado bien de alta o no P.Ej)

private boolean renderResp; // Para mostrar la frase que nos indica que ha pasado cuando esté a true

private String resp; // La respuesta que queremos mostrar

private List<Bean> l; // Lista del Bean (IMPORTANTE No crear el objeto para que se cree como Null y asi aparezca el desplegable de primeras)

private int idxAut; // Indice para SelectOneMenu
```



##### Get (Desde la logica de negocio)

```java
// Devuelve la lista de beans
public List<Bean> getBean() {
	// Comprobamos si esta en caché, si lo esta lo devolvemos y si no se lo pedimos a la logica de negocio
    if(l == null) {
		l = ln.getBean();
	}
	return l;
}
```

##### Alta (Sin claves foráneas o selectMenus)

```java
// Le pasas el bean que quieres dar de alta
public void alta(Bean b) {
    // Llamamos a la clase que de verdad se conecta con la BD
    setCodError(ln.altaBean(b));
    
	// Dependiendo de el codigo de error mostramos una frase o otra al usuario
    if(getCodError() == 0) {
        setResp("Bean dado de alta satisfactoriamente.");
    }else {
        setResp("El Bean que quiere dar de alta ya existe.");
    }
    
    // Hacemos que la frase se muestre
    setRenderResp(true);
    
	// Borramos la cache para forzar que la lista de elementos se actualice
    l = null;
}
```

##### Alta (Con claves foráneas o selectMenu)

```java
// Le pasas el bean que quieres dar de alta
public void alta(Bean b) {
    // Llamamos a la clase que de verdad se conecta con la BD (Le pasamos el indice)
    setCodError(ln.alta(p,idx));
    
    // Dependiendo de el codigo de error mostramos una frase o otra al usuario
    if(getCodError() == 0) {
    	setResp("Bean dado de alta satisfactoriamente.");
    }else {
    	setResp("El bean que quiere dar de alta ya existe.");
    }
    
    // Hacemos que la frase se muestre
    setRenderResp(true);
    
    // Borramos la cache para forzar que la lista de elementos se actualice
    l = null;
}
```



### WebContent

##### Formulario básico (input text) (Campo obligatorio y campo no obligatorio con command button para enviar)

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:h="http://java.sun.com/jsf/html"> 

<h:head>
	<meta content="text/html"></meta>
	<title>Titulo</title>
</h:head> 
<body> 
    <h1>Titulo grande</h1>
	<h2>Titulo pequeño</h2>
    	<h:form>
		<table>
			<tr>
				<td>Elemento1</td>
				<td>
					<h:inputText id="id" value="#{Bean.entity.elemento1}" required="true" requiredMessage="¡ERROR!"/>
					<br />
                    <!--Muestra el requiredMessage de arriba cuando se produzca--> 
					<h:message for="nameAuth" style="color:red"/>
				</td>
			</tr>
			<tr>
				<td>Elemento2</td>
				<td>
					<h:inputText id="" value="#{Bean.entity.elemento2}" />
				</td>
			</tr>
			<tr>
				<h:commandButton action="#{alta(Bean.entity)}" value="Añadir" />
			</tr>
		</table>
	</h:form>
</body>
```

##### Panel Group con Output Text controlado por una variable de render

```html
<!--Muestra el outputText cuando render esté a true-->
<h:panelGroup rendered="#{appBean.render}" style="color:red">
     
    <!--OutputText sirve para mostrar una String que esté almacenada en el codigo JAVA-->
    <h:outputText value="#{appBean.resp}" />
</h:panelGroup>
```

##### Select One Menu

```html
<!--Guardamos como valor el indice del elemento que hemos seleccionado-->
<h:selectOneMenu id="id" value="#{appBean.idx}" required="true" requiredMessage="¡ERROR!" >
    
<!--El valor del desplegable sera la lista de los elementos, la etiqueta que aparecera sera el nombre de cada elemento (Para referenciar a cada elemento usaremos el atributo var)(Será como una especie de i en un for, representa cada elemento que se está recorriendo) Por ultimo, el valor de ese elemento será el bean del elemento que estamos seleccionando (Lo sacamos de la lista con el indexOf)-->
    <f:selectItems value="#{appBean.elementos}" var="e" itemLabel="#{e.nombre}" itemValue="#{e.id}"></f:selectItems>

</h:selectOneMenu>
<br />
<!--Muestra el requiredMessage de arriba cuando se produzca--> 
<h:message for="revistas" style="color:red"/>
```

##### Tabla dinámica

```html
<ui:repeat var="b" value="#{appBean.beans}">
    <tr>
        <td >#{b.referencia}</td>
        <td >#{b.nombre}</td>
        <td >#{b.tipo}</td>
        <td >#{b.precio}€</td>
    </tr>
</ui:repeat>	
```



## REST

### JaxRsActivador

Hay que incluirlo en entornos Java EE , lo definiremos en el paquete en el que queremos usar REST.

```java
package bl;

// Importantes los imports correcto para no liarla
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// Indicamos la ruta de la que van a colgar todos los recursos REST
@ApplicationPath("/rest")
public class JaxRsActivator extends Application{ // Importante que extienda de Application

}
```



### Proveedor

#### Clase

```java
package bl;

// Cuidado con los imports (No coger los de suns)
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

@Path("/api") // Indicamos el path que queremos para acceder a la clase
public class ApiRest {
	
    // Atributo para definir el protocolo de transporte por REST
    private final String protocoloProveedor = MediaType.<el que sea>;

}
```

#### Get Bean

```java
@GET // Indicamos que se accede por GET
@Produces(protocoloProveedor) // Indicamos que produce <el protocolo que sea>
@Path("/getbeans") // Indicamos el path para acceder a este metodo

public Beans getBeans() {
    
    // Creamos el objeto con el que accederemos a los datos (En este caso la clase Database es la del Marshaller, Unmarshaller, pero se puede hacer con JPA)
    Database db = new Database(); 
    
    // Try catch (En este caso es por JAXB que propaga la excepcion si algo sale mal)
    try {
        return db.cargaDatos(); // Devolvemos la lista de beans
    }catch (JAXBException e) {
        return new Beans(); // Devolvemos una lista vacia
    }
}
```

#### Post Bean

```java
@POST // Indicamos que se accede por POST
@Consumes(protocoloProveedor) // Indicamos que consume <el protocolo que sea>
@Produces(MediaType.TEXT_PLAIN) // Indicamos que produce <el protocolo que sea>
@Path("/altabean") // Indicamos el path para acceder a este metodo
public String altaBean(Bean b) { // Le pasamos el bean y devuelve un String con el estado de la operación
    Database db = new Database(); // Igual que arriba
    try {
        if(b != null) {
            // Escribimos el bean en el alamcenamiento
            db.escribir(b);
            
            // Devolvemos una frase en la que indicamos que ha ido bien y los atributos del bean.
            return "El producto " + b.getNombre() + " con precio " + b.getPrecio() + "€" + " ha sido dado de alta en la categoria " + b.getTipo();
        }else {
            return "No hay producto para dar de alta";
        }
	// Excepciones
    }catch (JAXBException e){
        return "No se ha podido dar de alta el producto";
    }catch (IOException e) {
        return "IOException";
    }
}
```



### Consumidor

#### Clase

```java
package pl.modelo;

// Cuidado con los imports
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dl.Productos;
import dl.Producto;

public class Consumidor {

    // Definimos la URL del metodo que se llama por POST
	private final String altaProdUrl = "http://localhost:8080/Practica/rest/api/altabean";
    
    // Definimos la URL del meto que se llama por GET
	private final String getProdUrl = "http://localhost:8080/Practica/rest/api/getbeans";
    
    // Definimos el protocolo de transporte
	private final String protocoloConsumidor = MediaType.APPLICATION_JSON;

}
```



#### GET Bean

```java
@GET // Indicamos que se accede por GET
@Consumes(protocoloConsumidor) // Indicamos que consume <el protocolo que sea>
public Productos getBeans() { // Devuelve la lista de beans
    return ClientBuilder //Clase que nos permite hacer clientes HTTP
        .newClient() // Indicamos que queremos crear un nuevo cliente HTTP
        .target(getProdUrl) // Indicamos la URL a la que queremos que apunte
        .request(protocoloConsumidor) // Indicamos el tipo de datos que vamos a pedirle
        .get(Productos.class); // Indicamos el metodo HTTP por el que queremos acceder (En este caso GET). Entre parentesis irá la clase a la que queremos convertir el Response del servidor
}
```



#### POST Bean

```java
@POST // Indicamos que se accede por POST
@Produces(protocoloConsumidor) // Indicamos que produce <el protocolo que sea>
public String altaProducto(Producto p) {
    String s;  // Creamos una String que sera la frase de estado
/*Objeto de tipo Response*/ Response resp = ClientBuilder //Clase que nos permite hacer clientes HTTP
        .newClient() // Indicamos que queremos crear un nuevo cliente HTTP
        .target(altaProdUrl) // Indicamos la URL a la que queremos que apunte
        .request() // Indicamos que vamos a hacer un requests
        .post(Entity.entity(p, protocoloConsumidor)); // Indicamos que vamos a acceder por menotodo post (Indicamos entre parentesis que le vamos a mandar por post y en que formato) (Hay que meter estas dos cosas dentro del objeto Entity.entity())
    
    if(resp.getStatus() == 200) { // Comprobamos que haya sido un 200 OK
        s = resp.readEntity(String.class); // Leeemos el body del Response para saber que nos ha respondido el servidor REST (Hay que indicar entre parentesis a que tipo de dato queremos que nos lo convierta)
    }else {
        s = "No se ha podido dar de alta el producto."; // Frase de error
    }
    return s; // Devolvemos la String de respuesta
}
```



## Seguridad

### Método AplicationRealm (Usuarios y contraseñas en texto plano)

1. Vamos a la carpeta del WildFly y ejecutamos bin/add-user.sh

2. Para referenciar en Java cuales son los métodos que se van a poder usar con determinados roles.

   ```java
   // Solo permite acceder a los usuarios que pertenezcan a los roles que indiques
   @RolesAllowed("list-of-roles")
   
   // Permite a todos los usuarios acceder
   @PermitAll
   
   // Deniega el acceso a todos los usuarios
   @DenyAll
   ```

   

### Método Security Domain

1. Creamos el archivo WEB-INF/jboss-web.xml en el WebContent de nuestro proyecto.

   ![](https://i.imgur.com/0qxnm3n.png)

   

2. Para referenciar en Java cuales son los métodos que se van a poder usar con determinados roles.

```java
import org.jboss.ejb3.annotation;

@SecurityDomain("fichero-local") // Tiene que ser el mismo que en el jboss-web.xml
```



#### Método Security Domain sin BD (Usuarios y contraseñas en texto plano)

1. En la carpeta del Wildfly añadimos al standalone/configuration/standalone.xml las siguientes lineas. NombreDeTuSecDom tiene que coincidir con el nombre en jboss-web.xml.

   ![](https://i.imgur.com/KjpXhkF.png)

   

2. Tendremos que crear los ficheros que contendrán los usuarios y contraseñas y los grupos de cada usuario.

   1. user.properties: Usuarios y contraseñas.
   2. roles.properties: Grupos.

   ![](https://i.imgur.com/e4LeArQ.png)

   

#### Método Security Domain con BD (Sirve para que los usuarios y contraseñas estén guardados en la BD en vez de en un archivo de texto plano )

1. En la carpeta del Wildfly añadimos al standalone/configuration/standalone.xml las siguientes lineas. NombreDeTuSecDom tiene que coincidir con el nombre en jboss-web.xml.

   ![](https://i.imgur.com/mqdLPnW.png)



### Asignar los permisos a grupos en base a URLs

![](https://i.imgur.com/kg61G8j.png)



### Utilizar el nombre del usuario autenticado

#### Desde JSF:

```html
	#{request.RemoteUser}
```

#### Desde Beans:

```java
ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

return context.getRemoteUser();
```



## Consideraciones

- Escribir **lista=null en el bean** para que se actualice todo. (Por ejemplo los desplegables).

- Los **h:commandButton siempre dentro de un h:form**, si no no va a funcionar (Nos podemos fijar en que no funciona viendo que la pagina no recarga o en el navegador en inspeccionar -> Network).

- Tipos de datos en JPA versus MySql:

  - boolean (Con minuscula) en JPA -> Tinyint(1) en MySql. OJO CON ESTO POR QUE POR DEFECTO PILLA BYTE.
  -   ![](https://i.imgur.com/azlUnoF.png)
  - 

  

## Problemas

- Siempre que trabajamos con métodos que conectan con la BD y nos dan errores **mirar al final del todo de la traza** (Es donde están los sqlErrors)

- Siempre que ponga **errores con módulos** y tal volver a **repasar** los primero pasos de la configuración, todo lo que tenga que ver con **archivos de configuración.**

- **Named Parameter not Bound.** Estas haciendo una named query que necesita parámetros sin pasarle ningún parámetro.

- **No se puede restablecer la vista.** Con recargar la pagina, full publish o reiniciar el wildfly se arregla.

- Si en el navegador en inspeccionar podemos leer el código igual que nuestro XHTML es que no se está interpretando, esto es porque no está activado JSF en project facets.

  