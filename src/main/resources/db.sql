-- create database coursework2;

CREATE TABLE IF NOT EXISTS public.exam (
                                           id BIGSERIAL NOT NULL,
                                           name VARCHAR(100) NOT NULL UNIQUE,
                                           PRIMARY KEY(id)
)
    WITH (oids = false);

COMMENT ON TABLE public.exam
    IS 'Экзамены';

COMMENT ON COLUMN public.exam.id
    IS 'id';

COMMENT ON COLUMN public.exam.name
    IS 'Экзамен';

CREATE TABLE IF NOT EXISTS public.question (
                                               id BIGSERIAL NOT NULL,
                                               exam_id BIGINT NOT NULL,
                                               question TEXT NOT NULL,
                                               answer VARCHAR(100) NOT NULL,
                                               deleted BOOLEAN DEFAULT false NOT NULL,
                                               PRIMARY KEY(id)
)
    WITH (oids = false);

COMMENT ON TABLE public.question
    IS 'Вопросы к экзаменам';

COMMENT ON COLUMN public.question.id
    IS 'id';

COMMENT ON COLUMN public.question.exam_id
    IS 'id экзамена';

COMMENT ON COLUMN public.question.question
    IS 'Вопрос';

COMMENT ON COLUMN public.question.answer
    IS 'Ответ';

COMMENT ON COLUMN public.question.deleted
    IS 'Удален';

CREATE UNIQUE INDEX question_uniq_examid_question_answer ON public.question
    USING btree ("question", "answer", "exam_id");

ALTER TABLE public.question
    ADD CONSTRAINT question_fk FOREIGN KEY (exam_id)
        REFERENCES public.exam(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;

delete from public.question where id > 0;
delete from public.exam where id > 0;

ALTER SEQUENCE public.exam_id_seq
    INCREMENT 1 MINVALUE 1
        MAXVALUE 9223372036854775807 START 1
        RESTART 1 CACHE 1
        NO CYCLE;

ALTER SEQUENCE public.question_id_seq
    INCREMENT 1 MINVALUE 1
        MAXVALUE 9223372036854775807 START 1
        RESTART 1 CACHE 1
        NO CYCLE;


INSERT INTO public.exam ("id", "name")
VALUES
    (1, E'Java'),
    (2, E'Math');

INSERT INTO public.question ("exam_id", "question", "answer")
VALUES
    (1, E'Вопрос 1', E'Ответ'),
    (1, E'Вопрос 2', E'Ответ'),
    (1, E'Вопрос 3', E'Ответ'),
    (1, E'Вопрос 4', E'Ответ'),
    (1, E'Вопрос 5', E'Ответ'),
    (1, E'Вопрос 6', E'Ответ'),
    (1, E'Вопрос 7', E'Ответ'),
    (1, E'Вопрос 8', E'Ответ'),
    (1, E'Вопрос 9', E'Ответ'),
    (1, E'Вопрос 10', E'Ответ'),
    (2, E'Вопрос 1', E'Ответ');
