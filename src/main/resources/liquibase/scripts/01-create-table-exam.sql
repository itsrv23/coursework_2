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