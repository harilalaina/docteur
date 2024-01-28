create database dokotera with owner = postgres;

\c dokotera;

create table param(
	id_param serial primary key,
	nom_param varchar(255)
);

insert into param(nom_param) values
	('loha'),
	('lelo'),
	('kibo'),
	('tenda'),
	('caca'),
	('température'),
	('fatigue');

create table aretina(
	id_aretina serial primary key,
	nom_aretina varchar(255)
);

insert into aretina(nom_aretina) values
	('grippe'),
	('maux de tête'),
	('rhume'),
	('vavony'),
	('fièvre');

create table aretina_params(
	id_aretina int references aretina(id_aretina),
	id_param int references param(id_param),
	inf int not null,
	sup int not null
);


insert into aretina_params values
	(1,1,5,7),
	(1,2,4,8),
	(1,6,5,10),

	(2,1,1,7),

	(3,2,2,6),
	(3,1,5,7),

	(4,3,4,8),

	(5,6,4,9),
	(5,7,3,7);



create table fanafody(
	id_fanafody serial primary key,
	nom_fanafody varchar(255),
	prix_fanafody double precision
);

insert into fanafody(nom_fanafody, prix_fanafody) values 
	('Paracétamol', 300),
	('Doliprane', 1000),
	('Efferalgan', 600),
	('Vitamine C', 500),
	('Charbon', 500),
	('Bedelix', 1000),
	('Smecta', 1500),
	('Gavison', 2000),
	('Fervex', 2500),
	('GRT', 1000),
	('Notilium', 3000);

create table fanafody_params(
	id_fanafody int references fanafody(id_fanafody),
	id_param int references param(id_param),
	eff_fanafody int not null
);

insert into fanafody_params values
	(1,1,2),

	(2,1,4),
	(2,6,2),

	(3,1,3),
	(3,6,1),

	(4,2,2),
	(4,4,2),
	(4,7,2),

	(5,3,3),

	(6,3,4),
	(6,5,3),

	(7,3,5),
	(7,5,4),

	(8,3,3),

	(9,1,2),
	(9,2,3),
	(9,6,1),
	(9,7,2),

	(10,1,3),
	(10,2,4),
	(10,4,3),
	(10,6,2),

	(11,3,4);


create table patient(
	id_patient serial primary key,
	nom_patient varchar(255),
	age_patient int not null
);

insert into patient(nom_patient, age_patient) values
	('Jean', 33),
	('Marie', 25),
	('John', 23),
	('Bruno', 27);

create table patient_params(
	id_patient int references patient(id_patient),
	id_param int references param(id_param),
	niveau_param int not null
);

insert into patient_params values 
	(1,1,6),
	(1,2,7),
	(1,6,4),

	(2,3,4),

	(3,3,6),
	(3,5,5),

	(4,6,7),
	(4,7,5);

create or replace view v_aretina_params as
select
	ap.id_aretina,
	a.nom_aretina,
	ap.id_param,
	p.nom_param,
	ap.inf,
	ap.sup
from
	aretina_params as ap 
left join 
	aretina as a on a.id_aretina = ap.id_aretina
left join
	param as p on p.id_param = ap.id_param
group by
	ap.id_aretina,a.nom_aretina,ap.id_param,p.nom_param,ap.inf,ap.sup
order by
	ap.id_aretina;


create or replace view v_patient_params as 
select
	pp.id_patient,
	p.nom_patient,
	pp.id_param,
	par.nom_param,
	pp.niveau_param
from
	patient_params as pp 
left join
	patient as p on p.id_patient = pp.id_patient
left join
	param as par on par.id_param = pp.id_param
group by
	pp.id_patient,
	p.nom_patient,
	pp.id_param,
	par.nom_param,
	pp.niveau_param
order by
	pp.id_patient;



create or replace view v_fanafody_params as 
select
	fp.id_fanafody,
	f.nom_fanafody,
	fp.id_param,
	p.nom_param,
	fp.eff_fanafody,
	f.prix_fanafody
from
	fanafody_params as fp
left join
	fanafody as f on f.id_fanafody = fp.id_fanafody
left join
	param as p on p.id_param = fp.id_param
group by
	fp.id_fanafody,
	f.nom_fanafody,
	fp.id_param,
	p.nom_param,
	fp.eff_fanafody,
	f.prix_fanafody
order by
	fp.id_fanafody;



insert into patient(nom_patient, age_patient) values ('Bob', 23);

insert into aretina(nom_aretina) values
    ('Diarhée');

insert into aretina_params values
    (6,3,4,8),
    (6,5,5,9);

insert into patient_params values
    (5,1,7),
    (5,2,6),
    (5,6,8);