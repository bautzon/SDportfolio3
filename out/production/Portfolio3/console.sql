DROP TABLE IF EXISTS Cities;
CREATE TABLE IF NOT EXISTS Cities(
    city TEXT PRIMARY KEY,
    zipCode INTEGER,
    country TEXT
);
DROP TABLE IF EXISTS Students;
CREATE TABLE IF NOT EXISTS Students(
   studentID INTEGER NOT NULL PRIMARY KEY,
   firstName TEXT NOT NULL,
   lastName TEXT NOT NULL,
   GPA FLOAT,
   city TEXT,
   FOREIGN KEY (city) REFERENCES Cities(city) ON DELETE RESTRICT ON UPDATE CASCADE
);
DROP TABLE IF EXISTS Registration;
CREATE TABLE IF NOT EXISTS Registration(
    studentID INTEGER NOT NULL,
    courseID INTEGER NOT NULL,
    grade INTEGER,
    FOREIGN KEY (courseID) REFERENCES Course(courseID) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (studentID) REFERENCES Students(studentID) ON DELETE RESTRICT ON UPDATE CASCADE
);
DROP TABLE IF EXISTS Course;
CREATE TABLE IF NOT EXISTS Course(
    courseID INTEGER NOT NULL PRIMARY KEY,
    courseName TEXT NOT NULL,
    semester TEXT,
    courseGPA FLOAT,
    teacherID INTEGER NOT NULL,
    FOREIGN KEY (teacherID) REFERENCES Teacher(teacherID) ON DELETE RESTRICT ON UPDATE CASCADE
);
DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(
    teacherID INTEGER NOT NULL PRIMARY KEY,
    firstName TEXT NOT NULL,
    lastName TEXT
);


INSERT INTO Cities VALUES ('Nykøbing F', 4800, 'Danmark');
INSERT INTO Cities VALUES ('Karlskrona', null, 'Sverige');
INSERT INTO Cities VALUES ('Billund', 7190, 'Danmark');
INSERT INTO Cities VALUES ('Sorø', 4180, 'Danmark');
INSERT INTO Cities VALUES ('Eskildstrup', 4863, 'Danmark');
INSERT INTO Cities VALUES ('Odense', 5000, 'Danmark');
INSERT INTO Cities VALUES ('Stockholm', null, 'Sverige');
INSERT INTO Cities VALUES ('Tølløse', 4340, 'Danmark');
INSERT INTO Cities VALUES ('Jyllinge', 4040, 'Danmark');



INSERT INTO Students VALUES (1,'Aisha', 'Lincoln', null, 'Nykøbing F');
INSERT INTO Students VALUES (2, 'Anya', 'Nielsen', null, 'Nykøbing F');
INSERT INTO Students VALUES (3, 'Alfred', 'Jensen', null, 'Karlskrona');
INSERT INTO Students VALUES (4, 'Berta', 'Bertelsen', null, 'Billund');
INSERT INTO Students VALUES (5, 'Albert', 'Antonsen', null, 'Sorø');
INSERT INTO Students VALUES (6, 'Eske', 'Eriksen', null, 'Eskildstrup');
INSERT INTO Students VALUES (7, 'Olaf', 'Olesen', null, 'Odense');
INSERT INTO Students VALUES (8, 'Salma', 'Simonsen', null, 'Stockholm');
INSERT INTO Students VALUES (9, 'Theis', 'Thomassen', null, 'Tølløse');
INSERT INTO Students VALUES (10, 'Janet', 'Jensen', null, 'Jyllinge');



--GPA = SELECT avg(grade) FROM Registration GROUP BY studentID;

INSERT INTO Course VALUES (1,'SD','Autumn 2019',null,1);
INSERT INTO Course VALUES (2,'SD','Spring 2020',null,1);
INSERT INTO Course VALUES (3,'ES1','Autumn 2019',null,2);



INSERT INTO Teacher VALUES (1, 'Line', null);
INSERT INTO Teacher VALUES (2, 'Ebbe', null);



INSERT INTO Registration VALUES(1,1,12);
INSERT INTO Registration VALUES(1,3,10);
INSERT INTO Registration VALUES(2,2,null);
INSERT INTO Registration VALUES(2,3,12);
INSERT INTO Registration VALUES(3,1,7);
INSERT INTO Registration VALUES(3,3,10);
INSERT INTO Registration VALUES(4,2,null);
INSERT INTO Registration VALUES(4,3,2);
INSERT INTO Registration VALUES(5,1,10);
INSERT INTO Registration VALUES(5,3,7);
INSERT INTO Registration VALUES(6,2,null);
INSERT INTO Registration VALUES(6,3,10);
INSERT INTO Registration VALUES(7,1,4);
INSERT INTO Registration VALUES(7,3,12);
INSERT INTO Registration VALUES(8,2,null);
INSERT INTO Registration VALUES(8,3,12);
INSERT INTO Registration VALUES(9,1,12);
INSERT INTO Registration VALUES(9,3,12);
INSERT INTO Registration VALUES(10,2,null);
INSERT INTO Registration VALUES(10,3,7);


SELECT city, zipCode, country FROM Cities;

SELECT studentID, firstName, lastName, GPA, city FROM Students;

--SELECT Students.studentID, firstName, lastName, GPA, city FROM Students LEFT JOIN Registration ON Students.studentID = Registration.studentID;
SELECT studentID,courseID,grade FROM Registration;

SELECT courseID, courseName, semester, courseGPA,teacherID FROM Course;

SELECT teacherID, firstName, lastName FROM Teacher;
--SELECT avg(grade) FROM Registration GROUP BY studentID