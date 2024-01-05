USE vetitdb;
#---Insertar Employees PERSON
#Insertamos usuario admin contraseña admin
INSERT INTO Person (dtype,next_available_work_time,password,username,state)
VALUES ("Employee","2024-01-01 00:00:00.000000", "$2a$10$XTXjQlNGD8l3GOt9dQITkOhFNd1mVA6.atK0i//sddOPba0iJcGfq","admin",3);

#Insertamos usuario sin roles test contraseña 123
INSERT INTO Person (dtype,next_available_work_time,password,username,state)
VALUES ("Employee","2024-01-01 00:00:00.000000", "$2a$10$syBiuNAwXndAZb9yk2LOFO5EekIBN/JDDnTzIRaLrJbTtaatBXvEK","test",0);

#---Insertar Roles para admin
INSERT INTO rols (nom,id_usuari)
VALUES ("COMMERCIAL",1);
INSERT INTO rols (nom,id_usuari)
VALUES ("HUMAN",1);
INSERT INTO rols (nom,id_usuari)
VALUES ("ACCOUNTING",1);
INSERT INTO rols (nom,id_usuari)
VALUES ("SELLER",1);
INSERT INTO rols (nom,id_usuari)
VALUES ("PURCHASE",1);
INSERT INTO rols (nom,id_usuari)
VALUES ("VETERINARIAN",1);

#---Insertar Supplier PERSON
#Insertar un Supplier para los porductos su id sera 3
INSERT INTO Person (dtype,adress,email,firstname,lastname,phone,aproach_delivery_int,bureao,state)
VALUES ("Supplier","C/Falsa, 53, Aguila, Madrid, 28005","a@cemave.es","info cemave","","911111111",2,"Cemave",3);

#Insertar un Supplier para los productos su id sera 4
INSERT INTO Person (dtype,adress,email,firstname,lastname,phone,aproach_delivery_int,bureao,state)
VALUES ("Supplier","C/Banyoles, 6 (Polígon Industrial Sot dels Pradals), Vic","a@albet.es","info albet","","931111111",1,"Albet",3);

#---Insertar Customers PERSON
#Insertar un Customer para las mascotas su id sera 5
INSERT INTO Person (dtype,adress,email,firstname,lastname,phone,state)
VALUES ("Customer","C/Alacant, Terrassa 08224, Barcelona","a@customer.es","Alfredo","López Martín","931111111",3);

#Insertar un Customer para las compras su id sera 6
INSERT INTO Person (dtype,adress,email,firstname,lastname,phone,state)
VALUES ("Customer","C/Magallanes, Terrassa 08225, Barcelona","a@customer.es","Roger","Fernández Sánchez","931111111",3);

#---Insertar Productos
#Insertar un Producto 1
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Alimento seco para gatos en crecimiento y embarazados / lactantes",0,500,"Sanabelle Kitten",46,111,10,4);

#Insertar un Producto 2
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Comida seca para perros, Pack de 4 x 1,5kg, Total 6kg",0,500,"Ultima Pequeño Adult Pollo",30,111,10,4);

#Insertar un Producto 3
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Comida seca para perros, Pack de 3 x 3kg, Total 9kg ",0,500,"Ultima Medium-Maxi Medio Pollo",41,111,10,4);

#Insertar un Producto 4
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Salmon, Atun y Verduras 3,5kg ",0,500,"Brekkies Pienso para Gatos",10,111,10,4);

#Insertar un Producto 5
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Comida seca para gatos, Pack de 4 x 1,5kg, Total 6kg",0,500,"Ultima Esterilizado Adult Salmon",38,111,10,4);

#Insertar un Producto 6
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("6 unidads Bola Brillante Perros, Pelota Interactiva para Perros,Apto paraTodas Las Razas de Perros, Brillo en la Oscuridad (D6cm) ",0,500,"Nobleza Pelota Juguete Perro",20,111,10,4);

#Insertar un Producto 7
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Pelota, Resistente, Recargable por USB ",0,500,"Pelota Interactivo para Perros",16,111,10,4);

#Insertar un Producto 8
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("verde",0,500,"Tierbox Pelota de olfateo",32,111,10,4);

#Insertar un Producto 9
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Nudo algodón para Perros 15 cm",0,500,"Arquivet, Mordedor de Cuerda Trenzada",2,111,10,4);

#Insertar un Producto 10
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Antiparasitario Externo, recomendado para perro",0,500,"Exspot 6 Pip. X 1 Ml",10,111,10,3);

#Insertar un Producto 11
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Sistema Reproductor, recomendado para bovino y conejo",0,500,"Fertagyl 10 X 5 Ml",10,111,10,3);

#Insertar un Producto 12
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Antiparasitario Interno, recomendado para gato y perro ",0,500,"Panacur Pasta Perros Y Gatos 1 Jer",10,111,10,3);

#Insertar un Producto 13
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Vacunas Virus, recomendado para bovino,equino,gato,hurón,ovino y perro ",0,500,"Nobivac Rabia 10x1 D (jeringas Sin Cargo)",10,111,10,3);

#Insertar un Producto 14
INSERT INTO Products (description,interest,minim_quantity,name,price,product,quantity,supplier)
VALUES ("Loción, recomendado para perro ",0,500,"Granada Locion Dermatologica 200 Ml",10,111,10,3);

#---Insertar Mascotas
#Insertar una Mascota 1
INSERT INTO pets (dob,name,species,customers_id,estat)
VALUES ("2023-12-12","felix","gato",5,"true");

#Insertar una Mascota 2
INSERT INTO pets (dob,name,species,customers_id,estat)
VALUES ("2023-12-12","box","perro",5,"true");

#Insertar una Mascota 3
INSERT INTO pets (dob,name,species,customers_id,estat)
VALUES ("2023-12-12","hamtaro","hamster",6,"true");

#Insertar una Mascota 4
INSERT INTO pets (dob,name,species,customers_id,estat)
VALUES ("2023-12-12","nevado","perro",5,"true");

#Insertar una Mascota 5
INSERT INTO pets (dob,name,species,customers_id,estat)
VALUES ("2023-12-12","onix","lombriz",6,"true");

#---Insertar Visits
#Insertar Visit 1
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("10","No come","2023-12-14 00:00:00.000000",1,3);

#Insertar Visit 2
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("10","desparasitar","2023-12-14 10:00:00.000000",1,1);

#Insertar Visit 3
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("10","No bebe","2024-01-18 08:00:00.000000",1,2);

#Insertar Visit 4
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("10","revision","2024-01-10 15:00:00.000000",1,5);

#Insertar Visit 5
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("15","vacuna","2024-01-09 14:00:00.000000",1,4);

#Insertar Visit 6
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("10","desparasitar","2024-09-14 11:30:00.000000",1,5);

#Insertar Visit 7
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("10","desparasitar","2024-10-14 11:00:00.000000",1,2);

#Insertar Visit 8
INSERT INTO visit (concept,observations,scheduled_date,employee_id,pet_id)
VALUES ("10","revision peso, oido, etc","2024-01-08 12:30:00.000000",1,3);

#---Insertar Purchase B_BILL
INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-15 00:00:00.000000",50,100,"2023-01-05 00:00:00.000000",1,1,3,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-15 00:00:00.000000",10,80,"2023-01-05 00:00:00.000000",1,1,3,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-15 00:00:00.000000",20,30,"2023-01-06 00:00:00.000000",1,2,3,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-23 00:00:00.000000",30,40,"2024-01-07 00:00:00.000000",1,3,3,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-23 00:00:00.000000",50,200,"2024-01-08 00:00:00.000000",1,4,3,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-23 00:00:00.000000",15,80,"2024-01-09 00:00:00.000000",1,5,3,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-23 00:00:00.000000",12,30,"2024-01-20 00:00:00.000000",1,6,3,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-23 00:00:00.000000",18,50,"2024-01-10 00:00:00.000000",1,7,3,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-23 00:00:00.000000",60,300,"2024-01-11 00:00:00.000000",1,8,3,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-23 00:00:00.000000",5,100,"2024-01-12 00:00:00.000000",1,9,3,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",70,200,"2024-01-13 00:00:00.000000",1,10,3,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",30,90,"2024-01-14 00:00:00.000000",1,11,4,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",23,30,"2024-01-02 00:00:00.000000",1,12,4,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",80,500,"2024-01-03 00:00:00.000000",1,13,4,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",10,90,"2024-01-04 00:00:00.000000",1,14,4,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-11 00:00:00.000000",70,700,"2024-01-05 00:00:00.000000",1,1,3,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-01 00:00:00.000000",50,500,"2024-01-17 00:00:00.000000",1,2,3,1);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-01 00:00:00.000000",80,800,"2024-01-18 00:00:00.000000",1,3,3,1);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-01 00:00:00.000000",20,200,"2024-01-19 00:00:00.000000",1,4,3,1);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-01 00:00:00.000000",20,200,"2024-01-21 00:00:00.000000",1,5,3,1);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",40,400,"2024-01-09 00:00:00.000000",1,6,3,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",30,300,"2024-01-10 00:00:00.000000",1,7,3,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",40,200,"2024-01-08 00:00:00.000000",1,8,3,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",80,400,"2024-01-08 00:00:00.000000",1,9,3,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2023-12-30 00:00:00.000000",90,500,"2024-01-08 00:00:00.000000",1,10,3,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-08 00:00:00.000000",100,100,"2024-01-09 00:00:00.000000",1,11,4,2);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-08 00:00:00.000000",60,600,"2024-01-11 00:00:00.000000",1,12,4,2);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-08 00:00:00.000000",10,100,"2023-12-12 00:00:00.000000",1,13,4,2);

INSERT INTO b_bill (dtype,operation_date,quantity,total,scheduled_delivery_date,id_employee,id_product,id_supplier,semana)
VALUES ("Purchase","2024-01-08 00:00:00.000000",20,200,"2024-01-14 00:00:00.000000",1,14,4,2);

#---Insertar Sells Producto B_BILL
INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2024-01-08 00:00:00.000000",1,10,1,3,3,5,2);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2024-01-08 00:00:00.000000",1,46,1,1,3,6,2);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-30 00:00:00.000000",1,16,1,5,3,5,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-30 00:00:00.000000",1,20,1,4,3,6,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-30 00:00:00.000000",1,10,1,8,3,5,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-11 00:00:00.000000",1,36,1,9,3,6,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-11 00:00:00.000000",1,30,1,2,3,5,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-17 00:00:00.000000",1,10,1,10,4,6,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-18 00:00:00.000000",1,10,1,11,4,5,51);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-16 00:00:00.000000",1,10,1,12,4,6,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-17 00:00:00.000000",1,10,1,13,4,5,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2024-01-08 00:00:00.000000",1,10,1,14,4,6,2);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-08 00:00:00.000000",1,10,1,3,3,5,49);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-12-04 00:00:00.000000",1,16,1,5,3,6,49);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-11-28 00:00:00.000000",1,16,1,7,3,5,48);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-11-30 00:00:00.000000",1,41,1,3,3,6,48);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_product,id_supplier,id_customer,semana)
VALUES ("Sell","2023-11-22 00:00:00.000000",1,46,1,1,3,5,47);

#---Insertar Sells Visit B_BILL
INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_customer,id_visit,semana)
VALUES ("Sell","2023-12-30 00:00:00.000000",1,10,1,5,1,52);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_customer,id_visit,semana)
VALUES ("Sell","2023-12-14 00:00:00.000000",1,10,1,6,2,50);

INSERT INTO b_bill (dtype,operation_date,quantity,total,id_employee,id_customer,id_visit,semana)
VALUES ("Sell","2023-12-14 10:30:00.000000",1,10,1,5,1,50);