CREATE TABLE m_person
(
  id       INT PRIMARY KEY AUTO_INCREMENT,
  nom      VARCHAR(20) NOT NULL,
  prenom   VARCHAR(20) NOT NULL,
  civilite VARCHAR(20) NOT NULL,
  mail     VARCHAR(20) NOT NULL,
  age      INT         NOT NULL
);