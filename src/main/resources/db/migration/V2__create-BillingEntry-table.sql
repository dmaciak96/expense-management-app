create table if not exists billing_entry
(
    id UUID PRIMARY KEY,
    created_timestamp TIMESTAMP WITH TIME ZONE,
    last_updated_timestamp TIMESTAMP WITH TIME ZONE,
    version INT NOT NULL,
    name text NOT NULL,
    amount DECIMAL NOT NULL,
    billing_entry_group_id UUID UNIQUE,
    CONSTRAINT fk_billing_entry_group FOREIGN KEY (billing_entry_group_id)
        REFERENCES billing_entry_group(id)
        ON DELETE CASCADE
);