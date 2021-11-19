create table category
(
    category_id  UUID        not null,
    created      timestamp   not null,
    external_key UUID        not null,
    name         varchar(40) not null,
    primary key (category_id)
);
create table question
(
    question_id  UUID          not null,
    answer       varchar(2000),
    created      timestamp     not null,
    external_key UUID          not null,
    question     varchar(2000) not null,
    source       varchar(100),
    user_answer  varchar(2000),
    user_id      UUID,
    primary key (question_id)
);
create table user_profile
(
    user_id      UUID         not null,
    created      timestamp    not null,
    display_name varchar(100) not null,
    external_key UUID         not null,
    oauth_key    varchar(30)  not null,
    primary key (user_id)
);
create index IDXtg06qqtdh7gfun7j3sl899v7q on category (created);
alter table category
    add constraint UK_jcm53oix3t69vc8msosqxtcwf unique (external_key);
alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);
create index IDX1jgc9soixc0m5e87witdq3ba3 on question (created);
alter table question
    add constraint UK_rtex5c5akp4ogcea8im8dc0iw unique (external_key);
alter table question
    add constraint UK_9jpxsp4xpwniiuq9fix978jyq unique (question);
create index IDXakmwux4w2swsj69pg3ignha1v on user_profile (created);
alter table user_profile
    add constraint UK_j35xlx80xoi2sb176qdrtoy69 unique (display_name);
alter table user_profile
    add constraint UK_22o8v4jg08yk7piojnowil30o unique (external_key);
alter table user_profile
    add constraint UK_6f815wi5o4jq8p1q1w63o4mhd unique (oauth_key);
alter table question
    add constraint FKpxxc22j85sy406y8f6rmee5w6 foreign key (user_id) references user_profile;
create table category
(
    category_id  UUID        not null,
    created      timestamp   not null,
    external_key UUID        not null,
    name         varchar(40) not null,
    primary key (category_id)
);
create table question
(
    question_id  UUID          not null,
    answer       varchar(2000),
    created      timestamp     not null,
    external_key UUID          not null,
    question     varchar(2000) not null,
    source       varchar(100),
    user_answer  varchar(2000),
    user_id      UUID,
    primary key (question_id)
);
create table user_profile
(
    user_id      UUID         not null,
    created      timestamp    not null,
    display_name varchar(100) not null,
    external_key UUID         not null,
    oauth_key    varchar(30)  not null,
    primary key (user_id)
);
create index IDXtg06qqtdh7gfun7j3sl899v7q on category (created);
alter table category
    add constraint UK_jcm53oix3t69vc8msosqxtcwf unique (external_key);
alter table category
    add constraint UK_46ccwnsi9409t36lurvtyljak unique (name);
create index IDX1jgc9soixc0m5e87witdq3ba3 on question (created);
alter table question
    add constraint UK_rtex5c5akp4ogcea8im8dc0iw unique (external_key);
alter table question
    add constraint UK_9jpxsp4xpwniiuq9fix978jyq unique (question);
create index IDXakmwux4w2swsj69pg3ignha1v on user_profile (created);
alter table user_profile
    add constraint UK_j35xlx80xoi2sb176qdrtoy69 unique (display_name);
alter table user_profile
    add constraint UK_22o8v4jg08yk7piojnowil30o unique (external_key);
alter table user_profile
    add constraint UK_6f815wi5o4jq8p1q1w63o4mhd unique (oauth_key);
alter table question
    add constraint FKpxxc22j85sy406y8f6rmee5w6 foreign key (user_id) references user_profile;
