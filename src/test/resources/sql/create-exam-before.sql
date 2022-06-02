DELETE FROM question;
DELETE FROM exam;

INSERT INTO public.exam ("id", "name")
VALUES
    (1, E'Java'),
    (2, E'Math');

INSERT INTO public.question ("id","exam_id", "question", "answer")
VALUES
    (1, 1, E'Вопрос 1', E'Ответ'),
    (2, 1, E'Вопрос 2', E'Ответ'),
    (3, 2, E'Вопрос 1', E'Ответ');
