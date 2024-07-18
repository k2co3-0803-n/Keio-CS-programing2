PRAGMA foreign_keys=OFF;


-- 依存関係に基づいてテーブルを作成
DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS teachers;
DROP TABLE IF EXISTS day_of_week;
DROP TABLE IF EXISTS lectures;
DROP TABLE IF EXISTS taking_lectures;
DROP TABLE IF EXISTS teaching_lectures;
DROP VIEW IF EXISTS student_and_lecture_view;
DROP VIEW IF EXISTS teacher_and_lecture_view;
DROP VIEW IF EXISTS lectures_view;



--
-- エンティティ
--
-- 生徒テーブル
CREATE TABLE students (
	id VARCHAR PRIMARY KEY,
	name VARCHAR
);

-- 教員テーブル
CREATE TABLE teachers (
	id VARCHAR PRIMARY KEY,
	name VARCHAR
);

-- 曜日テーブル
CREATE TABLE day_of_week (
	id VARCHAR PRIMARY KEY,
	name VARCHAR
);

-- 講義テーブル
CREATE TABLE lectures (
	id INTEGER PRIMARY KEY AUTOINCREMENT,
	name VARCHAR,
	room VARCHAR,
	day_of_week_id VARCHAR,
	period VARCHAR,
	FOREIGN KEY (day_of_week_id) REFERENCES day_of_week(id)
);

--
-- 関連
--
-- 受講テーブル
CREATE TABLE taking_lectures (
	student_id VARCHAR,
	lecture_id INTEGER,
	FOREIGN KEY (student_id) REFERENCES students(id),
	FOREIGN KEY (lecture_id) REFERENCES lectures(id),
	PRIMARY KEY (student_id, lecture_id)
);

-- 担当テーブル
CREATE TABLE teaching_lectures (
	teacher_id VARCHAR,
	lecture_id INTEGER,
	FOREIGN KEY (teacher_id) REFERENCES teachers(id),
	FOREIGN KEY (lecture_id) REFERENCES lectures(id),
	PRIMARY KEY (teacher_id, lecture_id)
);


--
-- ビューの作成
--
-- 生徒の受講一覧、また講義の受講生と一覧ビュー
CREATE VIEW student_and_lecture_view AS
SELECT 
    l.id AS lecture_id, 
    l.name AS lecture_name, 
    s.id AS student_id, 
    s.name AS student_name
FROM 
    taking_lectures tl
JOIN 
    students s ON tl.student_id = s.id
JOIN 
    lectures l ON tl.lecture_id = l.id;


-- 教員の担当一覧ビュー
CREATE VIEW teacher_and_lecture_view AS
SELECT 
    l.id AS lecture_id, 
    l.name AS lecture_name, 
    t.id AS teacher_id, 
    t.name AS teacher_name
FROM 
    teaching_lectures tl
JOIN 
    teachers t ON tl.teacher_id = t.id
JOIN 
    lectures l ON tl.lecture_id = l.id;

-- 講義の曜日を名前に変換したビュー
CREATE VIEW lectures_view AS
SELECT 
	l.id,
    l.name, 
    l.room, 
    d.name AS day_of_week, 
    l.period
FROM 
    lectures l
JOIN 
    day_of_week d ON l.day_of_week_id = d.id;




PRAGMA foreign_keys = ON;