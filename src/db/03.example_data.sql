
USE `superheroes`;


SET FOREIGN_KEY_CHECKS=0;


truncate table `heroes`;



insert into `heroes`
(`id`, `hero_name`, `first_name`, `last_name`, `power`, `created_at`, `updated_at`)
values
    (uuid(), 'Spiderman', 'Peter', 'Parker', 'Spider', now(), null);
    (uuid(), 'Superman', 'Clark', 'Kent', 'Super', now(), null);
    (uuid(), 'Batman', 'Bruce', 'Wayne', 'Money', now(), null);
    (uuid(), 'Captain America', 'Steven ', 'Rogers', 'SuperSoldier', now(), null);

commit;


SET FOREIGN_KEY_CHECKS=1 ;
