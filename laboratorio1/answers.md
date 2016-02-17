# Laboratorio 1 Moviles
## Camilo Arevalo y Hector Delgado
### Punto 1
##### Listas
Permite elementos repetidos, generalmente ordenados. La busqueda es lenta pero la insersion de datos en la mitad es mucho mas rapida que un array.
> dato1 -> dato2-> dato3

* Utiles para el almacenamiento de informacion de la cual se desconoce el tamaño, placas de carros que ingresan a un parqueadero.

##### Sets
Sirve para acceder a una coleccion sin elementos repetidos que pueden estar desordenados.
* Lenguajes formales basan sus especificaciones en conjuntos, si uno quisiera hacer un compilador o interprete, deberia usar esta estructura.

##### Maps
Estructura de datos agrupados en clave => valor, la clave es unica y se emplea para acceder al valor, el acceso a los datos es rapido.
* Crear facturas y usar los codigos de los productos para traer el precio y el nombre del producto.

### Punto 2
##### Interfaz
La interfaz es una implementacion que sirve como template para que luego otra clase puede heradar sus atributos y metodos e implementarlas a su necesidad.

```java
public interface shape{
	void draw();
}

public class rectangle implements shape{
	public void draw(){
		system.out.println("rectangle :D");
	}
}
```
##### Clase abstracta

Una clase abstracta es parecida una interfaz con la diferencia que puedes agregar implementacion en los metodos.

```java
public abstract class shape{
	public void draw(){
		system.out.println("soy una figura :D");
	}
}

public class rectangle implements shape{
 //aqui ya se asume que tiene la funcion draw implementada
}
```
##### static field
Los campos estaticos son campos que se asocian con la clase en lugar de un objeto.

```java
public class rectangulo{
    private int lado1;
    private static int lado2;
	public void draw(){
	    this.lado1=lado2;
	    system.out.println("soy cuadrado :D");
	}
}

```