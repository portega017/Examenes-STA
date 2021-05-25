# Examen STA

## Configuración

1. Crear base de datos, o importar una que te den.

2. Forward Engineer de la base de datos.

3. Añadimos las entradas al standalone/configuration/standalone.xml. IMPORTANTE No olvidarse de la parte de driver, sino no funciona nada.

   ![image-20210525093145338](/home/g2jz/.config/Typora/typora-user-images/image-20210525093145338.png)

   

4. Añadimos  las entradas al persistence.xml (Dentro de los paquetes de  JAVA, carpeta META-INF)

   ![image-20210525093226514](/home/g2jz/.config/Typora/typora-user-images/image-20210525093226514.png)

   

5. Añadimos el modulo a la ruta: modules/system/layers/base/com/mysql/main. IMPORTANTE Buscar que exista, que la ruta corresponde y que es la misma versión, si no no lo encuentra y pasan cosas.

   ![image-20210525093021052](/home/g2jz/.config/Typora/typora-user-images/image-20210525093021052.png)

   

6. Nos conectamos desde eclipse a la base de datos.

7. Creamos el dynamic web project seleccionando como runtime el Wildfly.

8. Clickamos en que genere el web.xml.

9. En project facets:

   1. Activar CDI (Para EJB). En further configuration clickar en que genere beans.xml.
   2. Cambiar version de JAVA a 11.
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

##### Alta (Con una unica FK)

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



##### Alta (Relacion n a n con tabla intermedia)

```java
// Le pasas el bean que quieres dar de alta y los dos indices de las FK
public int alta(Bean b, int idx1, int idx2) {
    try {
        Bean1 b1 = new Bean1(); // Creamos el bean que corresponde a la tabla intermedia 
        
        if(em.createNamedQuery("Bean.findByName")
           .setParameter("nombre", b.getName())
           .getResultList().isEmpty()) { // Comprobamos que no exista el bean principal en la base de datos
            
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
        }else {
            return Exists;
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

private List<Bean> l = new ArrayList<Bean>(); // Lista del Bean

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

##### Alta (Sin claves foraneas o selectMenus)

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

##### Alta (Con claves foraneas o selectMenu)

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
    <f:selectItems value="#{appBean.elementos}" var="e" itemLabel="#{e.nombre}" itemValue="#{appBean.elementos.indexOf(e)}"></f:selectItems>

</h:selectOneMenu>
<br />
<!--Muestra el requiredMessage de arriba cuando se produzca--> 
<h:message for="revistas" style="color:red"/>
```



## Seguridad





## Problemas

- Si en el navegador en inspeccionar podemos leer el codigo igual que nuestro xhtml es que no se está interpretando, esto es porque no está activado JSF en project facets.

- Importante escribir listas=null en el bean para que se actualice todo. (Por ejemplo los desplegables).

- Importante los h:commandButton siempre detro de un h:form, si no no va a funcionar (Nos podemos fijar en que no funciona viendo que la pagina no recarga o en el navegador en inspeccionar -> Network).

- Siempre que trabajamos con metodos que conectan con la BD y nos dan errores mirar al final del todo de la traza (Es donde estan los sqlSyntaxErrors)

- Siempre que ponga errores con modulos y tal volver a repasar los primero pasos de la configuracion, todo lo que tenga que ver con archivos de configuracion.

- Named Parameter not Bound. Estas haciendo una named query que necesita parametros sin pasarle ningun parametro.

- No se puede restablecer la vista. Con recargar la pagina, full publish o reinciar el wildfly se arregla.

  