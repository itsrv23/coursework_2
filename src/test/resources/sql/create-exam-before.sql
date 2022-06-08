DELETE FROM question;
DELETE FROM exam;

INSERT INTO public.exam ("id", "name")
VALUES
    (1, E'Java'),
    (2, E'Math');

ALTER SEQUENCE public.exam_id_seq
    INCREMENT 1 MINVALUE 1
        MAXVALUE 9223372036854775807 START 1
        RESTART 3 CACHE 1
        NO CYCLE;
