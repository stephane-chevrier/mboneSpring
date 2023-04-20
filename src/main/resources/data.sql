# Table clientsExperts
DROP TABLE IF EXISTS mbone.clientsExperts;
CREATE TABLE mbone.clientsExperts (
                                      id        int PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                      status    int,
                                      expert_id int,
                                      FOREIGN KEY (expert_id) REFERENCES experts(id) ON DELETE CASCADE,
                                      client_id int,
                                      FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

# Remplissage Table domaines
LOCK TABLES mbone.domaines WRITE;
INSERT INTO mbone.domaines (nom)
VALUES
    ('Physiques'),
    ('Chimie'),
    ('Mathématiques'),
    ('java');
UNLOCK TABLES;

# Remplissage Table niveaux
LOCK TABLES mbone.niveaux WRITE;
INSERT INTO mbone.niveaux (nom)
VALUES
    ('Junior'),
    ('Senior'),
    ('Expert');
UNLOCK TABLES;

# Remplissage Table clients
LOCK TABLES mbone.clients WRITE;
INSERT INTO mbone.clients (user_id, nom, solde)
VALUES
    (2,'Panzani',10000),
    (3,'SNCF',20000),
    (4,'La Poste',15000);
UNLOCK TABLES;

# Remplissage Table experts
LOCK TABLES mbone.experts WRITE;
INSERT INTO mbone.experts (nom, prenom, url_photo, cout)
VALUES
    ('Boué','Pierre','https://media.licdn.com/dms/image/D4D35AQE9MGKIaMPrIg/profile-framedphoto-shrink_100_100/0/1674238052184?e=1682344800&v=beta&t=pRmbDeZicqtGLw5BRxF4Z6b_o8KXJo_dO1XlXWy0KnQ',50),
    ('Peysson','Ludovic','https://media.licdn.com/dms/image/D4E35AQG-9ClIJbrrnw/profile-framedphoto-shrink_200_200/0/1667470158521?e=1682344800&v=beta&t=DGUCcExL3XUvV2VjrpJh4U6me7xzcvdDdLhBSdlQDi4', 200),
    ('Chevrier','Stéphane','https://media.licdn.com/dms/image/C4E03AQFncqRVB29Xmw/profile-displayphoto-shrink_200_200/0/1633035776071?e=1687392000&v=beta&t=tJ_I4VApCcc4ypg0arn5zekqsIYhaQu5Gk3ju0vwZas',500);
UNLOCK TABLES;

# Remplissage Table expertises
LOCK TABLES mbone.expertises WRITE;
INSERT INTO mbone.expertises (expert_id, domaine_id, niveau_id)
VALUES
    (1,1,1),
    (1,2,2),
    (1,3,3),
    (2,2,1),
    (2,3,3),
    (2,4,1),
    (3,1,1),
    (3,3,2),
    (3,4,3);
UNLOCK TABLES;

# Remplissage Table clientsExperts
LOCK TABLES mbone.clientsExperts WRITE;
INSERT INTO mbone.clientsExperts (status, expert_id, client_id)
VALUES
    (0,1,1),
    (0,2,2),
    (0,3,3),
    (1,1,2),
    (1,2,3),
    (1,3,1);
UNLOCK TABLES;
