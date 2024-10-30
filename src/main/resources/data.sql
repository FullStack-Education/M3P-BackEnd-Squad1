INSERT INTO papeis (id, nome_papel)
VALUES (1, 'ADMIN');

INSERT INTO papeis (id, nome_papel)
VALUES (2, 'ALUNO');

INSERT INTO papeis (id, nome_papel)
VALUES (3, 'PROFESSOR');

INSERT INTO cursos (id, nome)
VALUES (1, 'Curso A');

INSERT INTO cursos (id, nome)
VALUES (2, 'Curso B');

INSERT INTO usuarios (id, login, senha, role, papel_id)
VALUES (1, 'admin', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 0, 1);

INSERT INTO usuarios (id, login, senha, role, papel_id)
VALUES (2, 'aluno', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 4, 2);

INSERT INTO usuarios (id, login, senha, role, papel_id)
VALUES (3, 'professor', '$2a$12$XOOpwCeFKNnelqXt/mfF4e2bUfZbOLns0P2oHy8R.OrgedboiWyU.', 3, 3);

INSERT INTO docentes (id, data_entrada , nome , usuario_id)
VALUES (1, '1990-10-10', 'Paulo César', 2);

INSERT INTO docentes (id, data_entrada , nome , usuario_id)
VALUES (2, '1990-10-10', 'Luiz Otávio', 3);

INSERT INTO turmas (id, nome , curso_id , professor_id)
VALUES (1, 'Turma 01', 1, 1);

INSERT INTO alunos (id, nome, data_nascimento , turma_id , usuario_id)
VALUES (1, 'Sérgio Malandro', '1990-10-10', 1, 2);

INSERT INTO materias (id, nome , curso_id)
VALUES (1, 'Matemática', 1);

INSERT INTO materias (id, nome , curso_id)
VALUES (2, 'Física', 1);

INSERT INTO materias (id, nome , curso_id)
VALUES (3, 'Português', 2);

INSERT INTO notas (id, aluno_id , data , docente_id , materia_id, valor)
VALUES (1, 1, '2024-10-10', 1, 2, 10.0);

INSERT INTO notas (id, aluno_id , data , docente_id , materia_id, valor)
VALUES (2, 1, '2024-10-10', 1, 2, 9.0);

INSERT INTO notas (id, aluno_id , data , docente_id , materia_id, valor)
VALUES (3, 1, '2024-10-10', 1, 2, 9.5);