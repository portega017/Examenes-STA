## Examen STA

### Configuración

1. Crear base de datos, o importar una que te den.
2. Forward Engineer de la base de datos.
3. Añadimos al standalone.xml
4. Añadimos al persistence.xml
5. Añadimos el modulo a la ruta: modules/system/layers/base
6. Nos conectamos desde eclipse a la base de datos.
7. En project facets:
   1. Activar CDI (Para EJB). En further configuration clickar en que genere beans.xml.
   2. Cambiar version de JAVA a 11.
   3. Activar Java Server Faces (Para JSF, XHTML).
   4. Activar JPA. En further configuration elegir library provided by runtime.
8. Prueba.html para probar Wildfly.



### Paquetes

#### Data Access Layer

1. New > Create JPA entities from tables. 
2. De-seleccionar incluirlas en persistence.xml
3. Quitar relaciones bidireccionales que no correspondan.
4. Paquete dl.
5. Mirar que todos los tipos de datos correspondan.
6. Named queries.



#### Business Layer

##### Esqueleto básico EJB

```java
package bl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class LogicaNegocio {

	@PersistenceContext
	private EntityManager em;	
}
```

##### Alta de algo

```java
	private final int OK=0;
	private final int Exception=1;
	private final int AuthorExists=2;	

	public int altaAutor(Author a) {
		try {
	if(em.createNamedQuery("Author.findByName").setParameter("nombre",a.getName()).getResultList().isEmpty()) {
				em.persist(a);
				return OK;
			}else {
				return AuthorExists;
			}
		}catch(Exception ex) {
			ex.getCause();
			return Exception;
		}	
	}
```



#### Presentation Layer

##### Beans de cada tabla de la base de datos

```java
package pl.modelo;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dl.Tabla;

@Named
@RequestScoped
public class Bean implements Serializable{
	
	private final Tabla entity = new Tabla();
	private static final long serialVersionUID = 1L;
	
	public Tabla getEntity() {
		return entity;
	}

}
```

##### Bean de funciones esqueleto

```java
package pl;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import bl.LogicaNegocio;

@Named
@ViewScoped
public class AppBean implements Serializable {

	@EJB
	private LogicaNegocio ln = new LogicaNegocio();
	private static final long serialVersionUID = 1L;
	private int codError;
    
    public int getCodError() {
		return codError;
	}

	public void setCodError(int codError) {
		this.codError = codError;
	}
}
```

##### Alta



#### WebContent

##### Formulario básico (input text)

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
					<h:inputText id="" value="#{Bean.entity.elemento1}"/>
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



### Problemas

- Si no sale nada de JSF, puede ser que esté desactivado en project facets.