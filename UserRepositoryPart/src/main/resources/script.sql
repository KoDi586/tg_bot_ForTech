CREATE TABLE IF NOT EXISTS public.user_model(
    user_id SERIAL PRIMARY KEY,
    chat_id BIGINT,
    true_count INTEGER,
    quiz_topic TEXT,
    quiz_dif TEXT,
    questions_count INTEGER,
    question_number_stopped INTEGER
);

CREATE TABLE IF NOT EXISTS public.question(
    id SERIAL PRIMARY KEY,
    question_text TEXT NOT NULL,
    variants TEXT[], -- PostgreSQL поддерживает массивы, используем массив строк
    answer TEXT,
    user_model_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_model_id) REFERENCES user_model (user_id) ON DELETE SET NULL
);


select um1_0.id,um1_0.chat_id,um1_0.question_number_stopped,um1_0.questions_count,um1_0.quiz_dif,um1_0.quiz_topic,um1_0.true_count from public.user um1_0
select um_0.id from "user" um_0