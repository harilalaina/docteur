create database doc with owner = postgres;

\c doc;

create table param(
	id_param serial primary key,
	nom_param varchar(255)
);

insert into param(nom_param) values
	('Kibo'),
	('Caca'),
	('Andoha'),
	('Temperature'),
	('Fatigue'),
	('Lelo');

create table aretina(
	id_aretina serial primary key,
	nom_aretina varchar(255)
);

insert into aretina(nom_aretina) values
	('Grippe '),
	('Vavony'),
	('Indigestion');
	
create table aretina_params(
	id_aretina int references aretina(id_aretina),
	id_param int references param(id_param),
	inf int not null,
	sup int not null
);

insert into aretina_params values
	(1,4,3,5),
	(1,6,5,8),
	(1,3,5,7),

	(2,1,5,7),
	(2,4,3,6),

	(3,1,5,8),
	(3,2,6,8),
	(3,5,3,7);

create table patient(
	id_patient serial primary key,
	nom_patient varchar(255),
	age_patient int not null
);

create table patient_params(
	id_patient int references patient(id_patient),
	id_param int references param(id_param),
	niveau_param int not null
);

insert into patient(nom_patient, age_patient) values
	('Rakoto', 33);

insert into patient_params values 
	(1,4,5),
	(1,6,7),
	(1,3,6),
	(1,1,6);



create table fanafody(
	id_fanafody serial primary key,
	nom_fanafody varchar(255),
	prix_fanafody double precision
);

create table fanafody_params(
	id_fanafody int references fanafody(id_fanafody),
	id_param int references param(id_param),
	eff_fanafody int not null
);

insert into fanafody(nom_fanafody, prix_fanafody) values 
	('F1', 20000),
	('F2', 15000),
	('F3', 40000);

insert into fanafody_params values
	(1,4,2),
	(1,6,3),
	(1,3,3),
	(1,1,1),


	(2,1,3),
	(2,3,1),
	(2,2,2),

	(3,5,2),
	(3,6,2),
	(3,3,3),
	(3,4,2),
	(3,1,2);



insert into patient(nom_patient, age_patient) values
    ('Rasoa', 25);

insert into patient_params values
    (2,1,4),
    (2,2,4),
    (2,3,4),
    (2,4,4),
    (2,5,4),
    (2,6,4);

insert into patient(nom_patient, age_patient) values
    ('Rajao', 19);

insert into patient_params values
    (3,1,5),
    (3,2,5),
    (3,3,5),
    (3,4,5),
    (3,5,5),
    (3,6,9);

insert into patient(nom_patient, age_patient) values
    ('Razefa', 54);

insert into patient_params values
    (4,1,8),
    (4,2,4),
    (4,3,6),
    (4,4,5),
    (4,5,3),
    (4,6,7);
