
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

ALTER TABLE public.question
    ADD CONSTRAINT question_fk FOREIGN KEY (exam_id)
        REFERENCES public.exam(id)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
        NOT DEFERRABLE;
