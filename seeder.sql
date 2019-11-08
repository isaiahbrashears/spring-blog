

use spring_blog_db;

insert into users (username, email, password) values ('Izzy', 'izzy@mail.com', 'password');
insert into users (username, email, password) values ('Derek Miller', 'derekcool@mail.com', 'password');

insert into posts (user_id, title, body) values (1,'garfield', 'he hates mondays for some reason? hes a cat so it doesnt even make sense');
insert into posts (user_id, title, body) values (1,'tabby the cat', 'he fluffy or whatever');
insert into posts (user_id, title, body) values (2,'Or (a.k.a. My Treasure)', 'Cross-group secondary access');
insert into posts (user_id, title, body) values (2,'Hotel Very Welcome', 'Optional optimal matrices');
insert into posts (user_id, title, body) values (1,'Italian for Beginners (Italiensk for begyndere)', 'Versatile modular definition');
insert into posts (user_id, title, body) values (1,'Knucklehead', 'Focused zero defect projection');
insert into posts (user_id, title, body) values (2,'Johnny Express', 'Sharable client-driven parallelism');
insert into posts (user_id, title, body) values (2,'Unknown White Male', 'Total radical system engine');
insert into posts (user_id, title, body) values (1,'Sugar Curtain, The (El telón de azúcar)', 'Robust bi-directional projection');
insert into posts (user_id, title, body) values (1,'Golem', 'Customizable static access');




insert into images (title, url, post_id) values ('cat 1', 'https://pmcvariety.files.wordpress.com/2019/08/garfield-e1565072358875.jpg?w=1000&h=563&crop=1' ,1);
insert into images (title, url, post_id) values ('cat 2', 'https://cdn2us.denofgeek.com/sites/denofgeekus/files/styles/main_wide/public/2019/08/garfield-and-friends.jpg?itok=NGvVnLsF',1);
insert into images (title, url, post_id) values ('cat 3', 'https://i.kym-cdn.com/entries/icons/original/000/029/052/creepy_garfield_cover.jpg',1);