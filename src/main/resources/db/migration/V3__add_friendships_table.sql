create table friendships
(
    id        bigint auto_increment
        primary key,
    user_id   bigint not null,
    friend_id bigint not null,
    constraint friend_id
        foreign key (friend_id) references users (id),
    constraint user_id
        foreign key (user_id) references users (id)
);

