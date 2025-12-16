
CREATE TABLE user_goals (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    goal_type VARCHAR(20) NOT NULL,
    target_calories INTEGER NOT NULL,
    target_protein_g DECIMAL(6, 2) NOT NULL,
    target_carbs_g DECIMAL(6, 2) NOT NULL,
    target_fats_g DECIMAL(6, 2) NOT NULL,
    start_date DATE DEFAULT now() NOT NULL,
    end_date DATE DEFAULT NULL,
    is_active BOOLEAN DEFAULT true NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);

