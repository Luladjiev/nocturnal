INSERT INTO PROJECTS(ID, TITLE)
VALUES ('DNA', 'DynamicAction'),
       ('EDT', 'EDITED'),
       ('ACL', 'Action List');

INSERT INTO ISSUES(ID, DESCRIPTION, TITLE, PROJECT_ID)
VALUES ('DNA-1', 'Description', 'Issue 1', 'DNA'),
       ('DNA-2', 'Description', 'Issue 2', 'DNA'),
       ('DNA-3', 'Description', 'Issue 3', 'DNA'),
       ('EDT-1', 'Description', 'Issue 1', 'EDT'),
       ('EDT-2', 'Description', 'Issue 2', 'EDT');