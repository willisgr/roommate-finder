create table profiles
(
    user_id                bigint       not null primary key,
    first_name             varchar(255) not null,
    last_name              varchar(255) not null,
    city                   varchar(255) not null,
    is_student             bool         not null,
    college_major          varchar(255) null,
    shared_groceries       bool         not null,
    is_pet_friendly        bool         not null,
    delegated_chores       bool         not null,
    cleanliness_preference int          not null,
    social_preference      int          not null,
    guest_preference       int          not null,
    constraint profiles_users_id_fk foreign key (user_id) references users (id)
);
