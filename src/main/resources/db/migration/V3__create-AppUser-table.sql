create table if not exists app_user
(
    id UUID PRIMARY KEY,
    created_timestamp TIMESTAMP WITH TIME ZONE,
    last_updated_timestamp TIMESTAMP WITH TIME ZONE,
    version INT NOT NULL,
    email TEXT NOT NULL,
    password TEXT NOT NULL,
    first_name TEXT NOT NULL,
    last_name TEXT NOT NULL,
    enabled BOOLEAN NOT NULL,
    user_type TEXT NOT NULL,
    photo bytea
);

alter table billing_entry_group
    add column if not exists user_id UUID;

alter table billing_entry
    add column if not exists user_id UUID;

