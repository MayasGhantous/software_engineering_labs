CREATE TABLE Teams (Num INT, Team VARCHAR(20),City VARCHAR(20),Established INT, Coach VARCHAR(30), Wins INT);
INSERT INTO Teams VALUES (1,'Maccabi','Tel-Aviv',1960,'Neven Spahija',12);
INSERT INTO Teams VALUES (2,'Hapoel','Jerusalem',1965,'Dainius Adomaitis',9);
INSERT INTO Teams VALUES (3,'Maccabi','Haifa',1978,'Amit Ben-David',6);
INSERT INTO Teams VALUES (4,'Hapoel','Tel-Aviv',1957,'Dani Franko',8);
INSERT INTO Teams VALUES (5,'Hapoel','Galil-elion',1972,'Barak Peleg',11);
CREATE TABLE Players (PlayerID INT, PlayerName VARCHAR(30), TeamNum INT, Age INT);
INSERT INTO Players VALUES (1,'Gil Benny',5,23);
INSERT INTO Players VALUES (2,'Yoval Zossman',1,22);
INSERT INTO Players VALUES (3,'Iftach Ziv',5,26);
INSERT INTO Players VALUES (4,'Omri Kasspi',1,32);
INSERT INTO Players VALUES (5,'Adam Ariel',2,27);
INSERT INTO Players VALUES (6,'Tamir Blat',2,24);
INSERT INTO Players VALUES (7,'Adi Cohen Saban',2,27);
INSERT INTO Players VALUES (8,'Naor Sharon',3,26);
INSERT INTO Players VALUES (9,'Rom Gefen',3,27);
INSERT INTO Players VALUES (10,'Matan Naor',4,31);
CREATE TABLE Budget (TeamNum INT, Budget int);
INSERT INTO Budget VALUES (1,3500);
INSERT INTO Budget VALUES (2,2100);
INSERT INTO Budget VALUES (3,1500);
INSERT INTO Budget VALUES (4,2000);
INSERT INTO Budget VALUES (5,1700);
CREATE TABLE Houses (TeamNum INT, House VARCHAR(1));
INSERT INTO Houses VALUES (1 ,'B');
INSERT INTO Houses VALUES (2 ,'A');
INSERT INTO Houses VALUES (3 ,'A');
INSERT INTO Houses VALUES (4 ,'B');
INSERT INTO Houses VALUES (5 ,'A');
