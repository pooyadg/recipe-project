
    create table category (
       category_id bigint not null,
        name varchar(255),
        primary key (category_id)
    ) engine=InnoDB

    create table hibernate_sequence (
       next_val bigint
    ) engine=InnoDB

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    insert into hibernate_sequence values ( 1 )

    create table ingredient (
       id bigint not null,
        amount decimal(19,2),
        description varchar(255),
        recipe_recipe_id bigint,
        unit_of_measure_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table notes (
       id bigint not null,
        notes longtext,
        recipe_recipe_id bigint,
        primary key (id)
    ) engine=InnoDB

    create table recipe (
       recipe_id bigint not null,
        cook_time integer,
        description varchar(255),
        difficulty varchar(255),
        directions longtext,
        image longblob,
        prep_time integer,
        servings integer,
        source varchar(255),
        url varchar(255),
        notes_id bigint,
        primary key (recipe_id)
    ) engine=InnoDB

    create table recipe_category (
       recipe_id bigint not null,
        category_id bigint not null,
        primary key (recipe_id, category_id)
    ) engine=InnoDB

    create table unit_of_measure (
       id bigint not null,
        unit varchar(255),
        primary key (id)
    ) engine=InnoDB

    alter table ingredient 
       add constraint FKntlm01dnsftggyxh9tqovw6mk 
       foreign key (recipe_recipe_id) 
       references recipe (recipe_id)

    alter table ingredient 
       add constraint FK15ttfoaomqy1bbpo251fuidxw 
       foreign key (unit_of_measure_id) 
       references unit_of_measure (id)

    alter table notes 
       add constraint FK30kb6nxlj607ug8v1bguqpwwf 
       foreign key (recipe_recipe_id) 
       references recipe (recipe_id)

    alter table recipe 
       add constraint FK37al6kcbdasgfnut9xokktie9 
       foreign key (notes_id) 
       references notes (id)

    alter table recipe_category 
       add constraint FKqsi87i8d4qqdehlv2eiwvpwb 
       foreign key (category_id) 
       references category (category_id)

    alter table recipe_category 
       add constraint FKcqlqnvfyarhieewfeayk3v25v 
       foreign key (recipe_id) 
       references recipe (recipe_id)
