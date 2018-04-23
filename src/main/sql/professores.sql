CREATE TABLE professores (
    id BIGINT NOT NULL 
    GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
    nome VARCHAR(255) NOT NULL,
    matricula INT NOT NULL,
    area VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO professores (nome, matricula, area)
VALUES ('Tomaz', 124356, 'Reconhecimento de fala');
INSERT INTO professores (nome, matricula, area)
VALUES ('Mariana', 654321, 'Interação Humano Computador');
INSERT INTO professores (nome, matricula, area)
VALUES ('Lúcia', 5555555, 'Redes neurais');