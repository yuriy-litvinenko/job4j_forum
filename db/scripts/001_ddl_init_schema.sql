create table if not exists posts
(
    id          serial primary key,
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now()
);

create table if not exists authorities
(
    id   serial primary key,
    name varchar(200)
);

create table if not exists users
(
    id       serial primary key,
    username varchar(200),
    password varchar
);

create table if not exists user_authority
(
    user_id int not null references users (id),
    role_id int not null references authorities (id)
);
