create table if not exists users (
    id bigserial not null,
    name varchar not null,
    email varchar not null,
    primary key (id),
    UNIQUE (email)
);

create table if not exists tasks (
    id bigserial not null,
    name varchar not null,
    done bool not null,
    user_id bigserial not null,
    primary key (id),
    CONSTRAINT fk_task_user FOREIGN KEY(user_id)
    REFERENCES users(id)
);