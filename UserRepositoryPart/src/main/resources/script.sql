CREATE TABLE IF NOT EXISTS public.user_model(
    user_id SERIAL PRIMARY KEY,
    chat_id BIGINT,
    true_count INTEGER,
    quiz_topic TEXT,
    quiz_dif TEXT,
    questions_count INTEGER,
    questions_id INTEGER[], -- Используем массив целых чисел для хранения IDs вопросов
    question_number_stopped INTEGER
);
