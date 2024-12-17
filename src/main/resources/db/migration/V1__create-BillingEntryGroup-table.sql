create table if not exists billing_entry_group
(
    id UUID PRIMARY KEY,
    created_timestamp TIMESTAMP WITH TIME ZONE,
    last_updated_timestamp TIMESTAMP WITH TIME ZONE,
    version INT NOT NULL,
    name text NOT NULL,
    type text NOT NULL
);