
insert into Area( country_code,deleted_flag, flag, name, parent_area, parent_area_id) values (1,0, 'Crvena-plava-bela', 'Kosovo','Srbija',17500)
insert into Area( country_code,deleted_flag, flag, name, parent_area, parent_area_id) values (2,0,'Crvena-plava-bela', 'Vojvodina', 'Srbija', 175001)
insert into Area( country_code,deleted_flag, flag, name, parent_area, parent_area_id) values (3,0,'Crvno-zuta', 'Katalonija', 'Spanija', 33301)
insert into Area( country_code,deleted_flag, flag, name, parent_area, parent_area_id) values (4,0,'Crveno-zuta', 'Baskija', 'Spanija', 33300)
insert into Area( country_code,deleted_flag, flag, name, parent_area, parent_area_id) values (8,0,'crveno-plava-bela','Sibir', 'Rusija', 23)

insert into Team(address,club_colors, deleted_flag, name, AREA) values('Ljutice bogdana','crveno-bela', 'false', 'Crvena Zvezda',2)
insert into Team(address,club_colors, deleted_flag, name, AREA) values('Spens','crveno-bela', 'false', 'Vojvodina',1)
insert into Team(address,club_colors, deleted_flag, name, AREA) values('Cair','crvena', 'false', 'Radnicki Nis',2)
insert into Team(address,club_colors, deleted_flag, name, AREA) values('Baskija arena','bordo', 'false', 'Atletiko Bilbao',4)
insert into Team(address,club_colors, deleted_flag, name, AREA) values('Kamp Nou','bordo-plava', 'false', 'Barcelona',3)

insert into Player(deleted_flag, first_name, last_name, area) values (0,'Milan', 'Pavkov', 1)
insert into Player(deleted_flag, first_name, last_name, area) values (0,'Adrijan', 'Aduriz', 4)
insert into Player(deleted_flag, first_name, last_name, area) values (0,'Xavi', 'Hernandes',3)
insert into Player(deleted_flag, first_name, last_name, area) values (0,'Marko', 'Poletanovic',2)
insert into Player(deleted_flag, first_name, last_name, area) values (0,'Alberto', 'Munain', 4)

insert  into Player_Teams(player_id, teams_id) values (1,2)
insert  into Player_Teams(player_id, teams_id)  values (1,4)
insert into Player_Teams(player_id, teams_id)  values(2,5)
insert into Player_Teams(player_id, teams_id)  values(4,1)
insert into Player_Teams(player_id, teams_id)  values(5,5)

insert into Area_Teams(area_area_id, teams_id) values(1,4)
insert into Area_Teams (area_area_id, teams_id) values(2,3)
insert into Area_Teams (area_area_id, teams_id) values(4,5)
insert into Area_Teams  (area_area_id, teams_id)values(2,1)

