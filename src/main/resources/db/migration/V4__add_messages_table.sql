create table messages
(
    id              bigint auto_increment
        primary key,
    sender_id       bigint                             not null,
    recipient_id    bigint                             not null,
    message_content text                               not null,
    time_sent       datetime default CURRENT_TIMESTAMP not null,
    constraint messages_users_id_fk
        foreign key (sender_id) references users (id),
    constraint messages_users_id_fk_2
        foreign key (recipient_id) references users (id)
);