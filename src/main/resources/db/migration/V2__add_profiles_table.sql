create table profiles
(
    user_id                bigint       not null primary key,
    first_name             varchar(50)  not null,
    last_name              varchar(50)  not null,
    user_age               tinyint      unsigned not null,
    gender                 enum('M', 'F', 'N', 'O') not null,
    match_gender           boolean      not null,
    city                   varchar(100) not null,
    is_student             boolean      not null,
    school                 varchar(100) null,
    college_major          varchar(100) null,
    shared_groceries       boolean      not null,
    is_pet_friendly        boolean      not null,
    delegated_chores       boolean      not null,
    cleanliness_preference tinyint      not null check ( cleanliness_preference between 1 and 5 ),
    social_preference      tinyint      not null check (social_preference between  1 and 5),
    guest_preference       tinyint      not null check (guest_preference between  1 and 3),
    constraint profiles_users_id_fk foreign key (user_id) references users (id)
);
