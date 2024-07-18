PRAGMA foreign_keys=true;

-- Add students
INSERT INTO students (id, name) VALUES ('62217681', 'Taro Tanaka');
INSERT INTO students (id, name) VALUES ('62217682', 'Hanako Yamada');
INSERT INTO students (id, name) VALUES ('62217683', 'Jiro Sato');
INSERT INTO students (id, name) VALUES ('62217684', 'Saburo Suzuki');

-- Add teachers
INSERT INTO teachers (id, name) VALUES ('12300862', 'Taro Yamamoto');
INSERT INTO teachers (id, name) VALUES ('12300863', 'Hanako Yamada');
INSERT INTO teachers (id, name) VALUES ('12300864', 'Taishi Sato');
INSERT INTO teachers (id, name) VALUES ('12300865', 'Goro Suzuki');

-- Add days of the week
INSERT INTO day_of_week (id, name) VALUES ('1', 'Monday');
INSERT INTO day_of_week (id, name) VALUES ('2', 'Tuesday');
INSERT INTO day_of_week (id, name) VALUES ('3', 'Wednesday');
INSERT INTO day_of_week (id, name) VALUES ('4', 'Thursday');
INSERT INTO day_of_week (id, name) VALUES ('5', 'Friday');
INSERT INTO day_of_week (id, name) VALUES ('6', 'Saturday');
INSERT INTO day_of_week (id, name) VALUES ('7', 'Sunday');

-- Add lectures
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('Mathematics I', '101', '1', '1');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('Japanese I', '102', '2', '2');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('English I', '103', '3', '3');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('Chemistry I', '104', '4', '4');
INSERT INTO lectures (name, room, day_of_week_id, period) VALUES ('Social Studies I', '105', '5', '5');

-- Add enrollment relations
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

-- Add teaching relations
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300862', 1);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300863', 2);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300864', 3);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300865', 4);
INSERT INTO teaching_lectures (teacher_id, lecture_id) VALUES ('12300862', 5);
