
USE `superheroes`;


SET FOREIGN_KEY_CHECKS=0;


truncate table `heroes`;



insert into `heroes`
(`id`, `hero_name`, `first_name`, `last_name`, `power`, `created_at`, `updated_at`)
values
    ( '75523408-c8c7-11ee-8b95-0242ac120002', 'Spiderman', 'Peter', 'Parker', 'Spider', now(), null ),
    ( 'd40319f6-c8e7-11ee-a234-0242ac140002', 'Superman', 'Clark', 'Kent', 'Super', now(), null),
    ( 'd4031b9a-c8e7-11ee-a234-0242ac140002', 'Batman', 'Bruce', 'Wayne', 'Money', now(), null),
    ( 'd4031bec-c8e7-11ee-a234-0242ac140002', 'Captain America', 'Steven ', 'Rogers', 'SuperSoldier', now(), null);

commit;


SET FOREIGN_KEY_CHECKS=1 ;
