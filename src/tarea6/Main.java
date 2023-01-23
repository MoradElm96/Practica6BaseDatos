/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarea6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Morad
 */
public class Main {

    public static void main(String[] args) throws SQLException {

        //usamos la clase conexion auxiliar para poder conectarnos a la base de datos
        Connection con = Conexion.conectar();
        //para poder meter datos por teclado
        Scanner sc = new Scanner(System.in);
        String query;
        PreparedStatement ps;
        ResultSet rs;
        
        
        //1 Lista el nombre de todos los productos que hay en la tabla producto.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("1 Lista el nombre de todos los productos que hay en la tabla producto");
        query = "select nombre from producto";
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            System.out.println("Nombre producto: " + nombre);
        }

        
        //2 Lista los nombres y los precios de todos los productos de la tabla producto.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("2 Lista los nombres y los precios de todos los productos de la tabla producto.");
        query = "select nombre, precio from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }

        
        //3 Lista todas las columnas de la tabla producto.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("3 Lista todas las columnas de la tabla producto.");  
        query = "select * from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo: " + codigo + " Nombre producto: " + nombre + " Precio: " + precio + " Codigo_fabricante: " + codigo_fabricante);
        }
        
        
        
        //4 Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD).
        System.out.println("------------------------------------------------------------------------");
        System.out.println("4 Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD).");
        query = "SELECT nombre, precio*1.09, precio FROM producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precioEuros = rs.getDouble("precio");
            double precioDolares = Math.round(rs.getDouble("precio*1.09"));
            System.out.println("Nombre producto: " + nombre + " Precio en euros: " + precioEuros + "€" + " Precio en dolares: " + precioDolares + "$");
        }

        //5 Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD). Utiliza los siguientes alias para las columnas: nombre de producto, euros, dólares.  
        System.out.println("------------------------------------------------------------------------");
        System.out.println("5 Lista el nombre de los productos, el precio en euros y el precio en dólares estadounidenses (USD). Utiliza los siguientes alias para las columnas: nombre de producto, euros, dólares.");
        query = "SELECT nombre as '', precio as euros, precio*1.09 as dolares from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString(1);
            double precioEuros = rs.getDouble(2);
            double precioDolares = Math.round(rs.getDouble(3));
            System.out.println("Nombre producto: " + nombre + " Precio en euros: " + precioEuros + "€" + " Precio en dolares: " + precioDolares + "$");
        }

        
        //6 Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a mayúscula.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("6 Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a mayúscula.");
        query = "SELECT UPPER (nombre),precio from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString(1);
            double precioEuros = rs.getDouble("precio");

            System.out.println("Nombre producto: " + nombre + " Precio en euros: " + precioEuros + "€");
        }
        
        
        
        //7Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a minúscula.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("7 Lista los nombres y los precios de todos los productos de la tabla producto, convirtiendo los nombres a minúscula.");
        query = "SELECT lower (nombre),precio from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString(1);
            double precioEuros = rs.getDouble("precio");

            System.out.println("Nombre producto: " + nombre + " Precio en euros: " + precioEuros + "€");
        }
        
        
        //8 Lista el nombre de todos los fabricantes en una columna, y en otra columna obtenga en mayúsculas los dos primeros caracteres del nombre del fabricante.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("8 Lista el nombre de todos los fabricantes en una columna, y en otra columna obtenga en mayúsculas los dos primeros caracteres del nombre del fabricante.");
        query = "SELECT nombre, UPPER (substring(nombre,1,2)) from fabricante";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString(1);
            String nombreUp = rs.getString(2);
            System.out.println("Nombre fabricante: " + nombre + " Upper: " + nombreUp);
        }
        
        
        //9 Lista los nombres y los precios de todos los productos de la tabla producto, redondeando el valor del precio.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("9 Lista los nombres y los precios de todos los productos de la tabla producto, redondeando el valor del precio.");
        query = "select nombre, round (precio) from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {

            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        //10 Lista los nombres y los precios de todos los productos de la tabla producto, truncando el valor del precio para mostrarlo sin ninguna cifra decimal.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("10 Lista los nombres y los precios de todos los productos de la tabla producto, truncando el valor del precio para mostrarlo sin ninguna cifra decimal.");
        query = "select nombre, truncate(precio,0) from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {

            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + (int) precio);
        }
        
        
        //11 Lista el código de los fabricantes que tienen productos en la tabla producto.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("11 Lista el código de los fabricantes que tienen productos en la tabla producto.");
        query = "select codigo_fabricante from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo_fabricante: " + codigo_fabricante);
        }

        
        
        //12 Lista el código de los fabricantes que tienen productos en la tabla producto, eliminando los códigos que aparecen repetidos.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("12 Lista el código de los fabricantes que tienen productos en la tabla producto, eliminando los códigos que aparecen repetidos.");
        query = "select distinct codigo_fabricante from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo_fabricante: " + codigo_fabricante);
        }
        
        
        
        //13 Lista los nombres de los fabricantes ordenados de forma ascendente.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("13 Lista los nombres de los fabricantes ordenados de forma ascendente.");
        query = "SELECT nombre from fabricante order by nombre asc";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString(1);
            System.out.println("Nombre fabricante: " + nombre);
        }
        
        
        
        //14 Lista los nombres de los fabricantes ordenados de forma descendente.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("14 Lista los nombres de los fabricantes ordenados de forma descendente.");
        query = "SELECT nombre from fabricante order by nombre desc";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString(1);
            System.out.println("Nombre fabricante: " + nombre);
        }
        
        
        
        //15 Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("15 Lista los nombres de los productos ordenados en primer lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.");
        query = "select nombre,precio from producto order by nombre asc, precio desc";
        rs = ps.executeQuery(query);
        while (rs.next()) {

            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
       
        
        //16 Devuelve una lista con las 5 primeras filas de la tabla fabricante.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("16 Devuelve una lista con las 5 primeras filas de la tabla fabricante.");
        query = "SELECT *  from fabricante limit 5";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codFab = rs.getInt(1);
            String nombre = rs.getString(2);

            System.out.println("Codigo: " + codFab + " Nombre fabricante: " + nombre);
        }
        
        
        
        //17Devuelve una lista con 2 filas a partir de la cuarta fila de la tabla fabricante. La cuarta fila también se debe incluir en la respuesta.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("17 Devuelve una lista con 2 filas a partir de la cuarta fila de la tabla fabricante. La cuarta fila también se debe incluir en la respuesta.");
        query = "SELECT *  from fabricante limit 3,2"; //desde 4 a 5
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codFab = rs.getInt(1);
            String nombre = rs.getString(2);

            System.out.println("Codigo: " + codFab + " Nombre fabricante: " + nombre);
        }
        
        
        //18 Lista el nombre y el precio del producto más barato. (Utilice solamente las cláusulas ORDER BY y LIMIT)
        System.out.println("------------------------------------------------------------------------");
        System.out.println("//18 Lista el nombre y el precio del producto más barato. (Utilice solamente las cláusulas ORDER BY y LIMIT).");
        query = "select nombre, precio from producto order by precio asc limit 1";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        //19 Lista el nombre y el precio del producto más caro. (Utilice solamente las cláusulas ORDER BY y LIMIT)
        System.out.println("------------------------------------------------------------------------");
        System.out.println("19 Lista el nombre y el precio del producto más caro. (Utilice solamente las cláusulas ORDER BY y LIMIT)");
        query = "select nombre, precio from producto order by precio desc limit 1";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        //20 Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("20 Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.");
        query = "select nombre,codigo_fabricante from producto where codigo_fabricante=2";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            int codFab = rs.getInt(2);
            System.out.println("Nombre producto: " + nombre + " codigo_fabricante: " + codFab);
        }
        
        
        //21 Lista el nombre de los productos que tienen un precio menor o igual a 120€.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("21 Lista el nombre de los productos que tienen un precio menor o igual a 120€.");
        query = "select nombre,precio from producto where precio<=120";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        //22 Lista el nombre de los productos que tienen un precio mayor o igual a 400€.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("22 Lista el nombre de los productos que tienen un precio mayor o igual a 400€..");
        query = "select nombre,precio from producto where precio>=400";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        
        //23 Lista el nombre de los productos que no tienen un precio mayor o igual a 400€.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("23 Lista el nombre de los productos que no tienen un precio mayor o igual a 400€.");
        query = "select nombre,precio from producto where not precio>=400";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble(2);
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        
        //24 Lista todos los productos que tengan un precio entre 80€ y 300€. Sin utilizar el operador BETWEEN.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("24 Lista todos los productos que tengan un precio entre 80€ y 300€. Sin utilizar el operador BETWEEN.");
        query = "select * from producto where precio>=80 and precio <=300";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo: " + codigo + " Nombre producto: " + nombre + " Precio: " + precio + " Codigo_fabricante: " + codigo_fabricante);
        }

        
        //25 Lista todos los productos que tengan un precio entre 60€ y 200€. Utilizando el operador BETWEEN.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("25 Lista todos los productos que tengan un precio entre 60€ y 200€. Utilizando el operador BETWEEN.");
        query = "select * from producto where precio between 60 and 200";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo: " + codigo + " Nombre producto: " + nombre + " Precio: " + precio + " Codigo_fabricante: " + codigo_fabricante);
        }
        
        
        //26 Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("26 Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.");
        query = "select * from producto where precio >=200 and codigo_fabricante=6";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo: " + codigo + " Nombre producto: " + nombre + " Precio: " + precio + " Codigo_fabricante: " + codigo_fabricante);
        }
        
        
        //27 Lista todos los productos donde el código de fabricante sea 1, 3 o 5. Sin utilizar el operador IN.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("27 Lista todos los productos donde el código de fabricante sea 1, 3 o 5. Sin utilizar el operador IN.");
        query = "select * from producto where codigo_fabricante=1 or codigo_fabricante=3 or codigo_fabricante=5";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo: " + codigo + " Nombre producto: " + nombre + " Precio: " + precio + " Codigo_fabricante: " + codigo_fabricante);
        }
        
        
        //28 Lista todos los productos donde el código de fabricante sea 1, 3 o 5. Utilizando el operador IN.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("28 Lista todos los productos donde el código de fabricante sea 1, 3 o 5. Utilizando el operador IN.");
        query = "select * from producto where codigo_fabricante in(1,3,5)";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            int codigo_fabricante = rs.getInt("codigo_fabricante");
            System.out.println("Codigo: " + codigo + " Nombre producto: " + nombre + " Precio: " + precio + " Codigo_fabricante: " + codigo_fabricante);
        }
        
        
        
        //29 Lista el nombre y el precio de los productos en céntimos (Habrá que multiplicar por 100 el valor del precio). Cree un alias para la columna que contiene el precio que se llame céntimos.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("28 Lista todos los productos donde el código de fabricante sea 1, 3 o 5. Utilizando el operador IN.");
        query = "select nombre,precio *100 as centimos from producto";
        rs = ps.executeQuery(query);
        while (rs.next()) {

            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("centimos");

            System.out.println("Nombre producto: " + nombre + " Precio: " + precio + "centimos");
        }
        
        
        //30 Lista los nombres de los fabricantes cuyo nombre empiece por la letra S.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("30 Lista los nombres de los fabricantes cuyo nombre empiece por la letra S.");
        query = "SELECT codigo,nombre from fabricante where nombre like'S%'";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codFab = rs.getInt(1);
            String nombre = rs.getString(2);

            System.out.println("Codigo: " + codFab + " Nombre fabricante: " + nombre);
        }
        
        
        //31 Lista los nombres de los fabricantes cuyo nombre termine por la vocal e.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("31 Lista los nombres de los fabricantes cuyo nombre termine por la vocal e.");
        query = "SELECT codigo,nombre from fabricante where nombre like'%e'";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codFab = rs.getInt(1);
            String nombre = rs.getString(2);

            System.out.println("Codigo: " + codFab + " Nombre fabricante: " + nombre);
        }
        
        
        //32 Lista los nombres de los fabricantes cuyo nombre contenga el carácter w.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("31 Lista los nombres de los fabricantes cuyo nombre termine por la vocal e.");
        query = "SELECT codigo,nombre from fabricante where nombre like'%w%'";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codFab = rs.getInt(1);
            String nombre = rs.getString(2);

            System.out.println("Codigo: " + codFab + " Nombre fabricante: " + nombre);
        }
        
        
        //33 Lista los nombres de los fabricantes cuyo nombre sea de 4 caracteres.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("33 Lista los nombres de los fabricantes cuyo nombre sea de 4 caracteres.");
        query = "SELECT codigo,nombre from fabricante where length (nombre)=4";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            int codFab = rs.getInt(1);
            String nombre = rs.getString(2);

            System.out.println("Codigo: " + codFab + " Nombre fabricante: " + nombre);
        }
        
        
        //34 Devuelve una lista con el nombre de todos los productos que contienen la cadena Portátil en el nombre.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("34 Devuelve una lista con el nombre de todos los productos que contienen la cadena Portátil en el nombre.");
        query = "select * from producto where nombre like '%Portatil%'";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            System.out.println("Nombre producto: " + nombre);
        }
        
        
        //35 Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
        System.out.println("------------------------------------------------------------------------");
        System.out.println("35 Devuelve una lista con el nombre de todos los productos que contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.");
        query = "select * from producto where nombre like '%Monitor%' and precio <215";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        //36 Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
        System.out.println("------------------------------------------------------------------------");
        System.out.println("36 Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).");
        query = "select nombre,precio from producto where precio>=180 order by precio desc, nombre asc";
        rs = ps.executeQuery(query);
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            double precio = rs.getDouble("precio");
            System.out.println("Nombre producto: " + nombre + " Precio: " + precio);
        }
        
        
        //cerramos la conexion a la base de datos
        System.out.println("Fin del ejercicio");
        Conexion.cerrarConexion();

    }

}
