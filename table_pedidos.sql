create table pedidos(Cliente VARCHAR (6),
Articulo VARCHAR (6),
Unidades FLOAT,
Fecha DATE,
CONSTRAINT FK_Cliente FOREIGN KEY (Cliente) REFERENCES clientes(Código),
CONSTRAINT FK_Articulo FOREIGN KEY (Articulo) REFERENCES articulos(Codigo)); 
);


--tabla historial---

CREATE TABLE historica (
    Cliente varchar(6),
    Proveedor varchar(6),
    Articulo varchar(6),
unidades int,
fechas date,
CONSTRAINT FK_Cliente FOREIGN KEY (Cliente) REFERENCES clientes(Código),
 CONSTRAINT FK_Proveedor FOREIGN KEY (Proveedor) REFERENCES proveedores(Código),
CONSTRAINT FK_Articulo FOREIGN KEY (Articulo) REFERENCES articulos(Codigo)); 


-- table clientes --

create table clientes(
	Código varchar(6) primary key,
	NIF varchar(9),
	Apellidos varchar(35),
	Nombre varchar(15),
	Domicilio varchar(40),
	Código_Postal varchar(5),
	Localidad varchar(20),
	Teléfono varchar(9),
	Móvil varchar(9),
	Fax varchar(9),
	E_mail varchar(20),
	Total_Ventas float  
);

-- table proveedores --

create table proveedores(
	Código varchar(6) primary key,
	NIF varchar(9),
	Apellidos varchar(35),
	Nombre varchar(15),
	Domicilio varchar(40),
	Código_Postal varchar(5),
	Localidad varchar(20),
	Teléfono varchar(9),
	Móvil varchar(9),
	Fax varchar(9),
	E_mail varchar(20),
	Total_Ventas float  
);

--tabla articulos----

create table articulos(
Codigo varchar(6) Primary key,
Descripcion varchar(25),
Stock float,
Stock_minimo float,
Precio_de_compra float,
Precio_de_venta float
);