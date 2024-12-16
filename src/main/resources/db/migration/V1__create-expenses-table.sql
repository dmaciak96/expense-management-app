create table if not exists Expense
(
    id                 uuid PRIMARY KEY UNIQUE,
    created_date       timestamp        not null,
    last_modified_date timestamp,
    version            int              not null,
    name               text             not null,
    amount             double precision not null,
    expense_group      text             not null
);