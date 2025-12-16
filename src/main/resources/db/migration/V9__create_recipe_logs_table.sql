CREATE TABLE recipe_logs (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID REFERENCES users(id) NOT NULL,
    recipe_id UUID REFERENCES recipes(id),
    logged_at TIMESTAMP DEFAULT now() NOT NULL,
    log_date DATE DEFAULT CURRENT_DATE NOT NULL,
    recipe_name VARCHAR(255) NOT NULL,
    servings_consumed DECIMAL(8, 2) NOT NULL DEFAULT 1.00,
    ingredients_snapshot JSONB NOT NULL,
    calories_per_serving INTEGER NOT NULL,
    protein_g_per_serving DECIMAL(8, 2),
    carbs_g_per_serving DECIMAL(8, 2),
    fat_g_per_serving DECIMAL(8, 2),
    created_at TIMESTAMP DEFAULT now()
);