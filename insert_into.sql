PRAGMA foreign_keys=true;

-- 生徒追加
INSERT INTO students (id, name) VALUES ('62217681', '田中太郎');
INSERT INTO students (id, name) VALUES ('62217682', '山田花子');
INSERT INTO students (id, name) VALUES ('62217683', '佐藤次郎');
INSERT INTO students (id, name) VALUES ('62217684', '鈴木三郎');


-- 教員追加
INSERT INTO teachers (id, name) VALUES ('12300862', '山本太郎');
INSERT INTO teachers (id, name) VALUES ('12300863', '山田花子');
INSERT INTO teachers (id, name) VALUES ('12300864', '佐藤太子');
INSERT INTO teachers(id, name) VALUES ('12300865', '鈴木五郎');

-- 曜日追加
INSERT INTO day_of_week (id, name) VALUES ('1', '月曜日');
INSERT INTO day_of_week (id, name) VALUES ('2', '火曜日');
INSERT INTO day_of_week (id, name) VALUES ('3', '水曜日');
INSERT INTO day_of_week (id, name) VALUES ('4', '木曜日');
INSERT INTO day_of_week (id, name) VALUES ('5', '金曜日');
INSERT INTO day_of_week (id, name) VALUES ('6', '土曜日');
INSERT INTO day_of_week (id, name) VALUES ('7', '日曜日');

-- 講義追加
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('数学I', '101', '1', '1');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('国語I', '102', '2', '2');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('英語I', '103', '3', '3');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('化学I', '104', '4', '4');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('社会I', '105', '5', '5');

-- 受講関係追加
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217681', 1);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217681', 2);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217681', 3);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217681', 4);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217681', 5);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217682', 1);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217682', 2);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217682', 3);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217682', 4);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217682', 5);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217683', 1);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217683', 2);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217683', 3);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217683', 4);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217683', 5);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217684', 1);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217684', 2);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217684', 3);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217684', 4);
INSERT INTO taking_lectures (student_id, lecture_id) VALUES ('62217684', 5);

--　担当関係追加
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300862', 1);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300863', 2);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300864', 3);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300865', 4);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300862', 5);
