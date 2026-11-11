INSERT INTO users (username, email, password)
VALUES
    ('testuser01', 'testuser01@example.test', 'password'),
    ('testuser02', 'testuser02@example.test', 'password'),
    ('testuser03', 'testuser03@example.test', 'password'),
    ('testuser04', 'testuser04@example.test', 'password'),
    ('testuser05', 'testuser05@example.test', 'password'),
    ('testuser06', 'testuser06@example.test', 'password'),
    ('testuser07', 'testuser07@example.test', 'password'),
    ('testuser08', 'testuser08@example.test', 'password'),
    ('testuser09', 'testuser09@example.test', 'password'),
    ('testuser10', 'testuser10@example.test', 'password'),
    ('testuser11', 'testuser11@example.test', 'password'),
    ('testuser12', 'testuser12@example.test', 'password'),
    ('testuser13', 'testuser13@example.test', 'password'),
    ('testuser14', 'testuser14@example.test', 'password'),
    ('testuser15', 'testuser15@example.test', 'password'),
    ('testuser16', 'testuser16@example.test', 'password'),
    ('testuser17', 'testuser17@example.test', 'password'),
    ('testuser18', 'testuser18@example.test', 'password'),
    ('testuser19', 'testuser19@example.test', 'password'),
    ('testuser20', 'testuser20@example.test', 'password'),
    ('testuser21', 'testuser21@example.test', 'password'),
    ('testuser22', 'testuser22@example.test', 'password'),
    ('testuser23', 'testuser23@example.test', 'password'),
    ('testuser24', 'testuser24@example.test', 'password'),
    ('testuser25', 'testuser25@example.test', 'password'),
    ('testuser26', 'testuser26@example.test', 'password'),
    ('testuser27', 'testuser27@example.test', 'password'),
    ('testuser28', 'testuser28@example.test', 'password'),
    ('testuser29', 'testuser29@example.test', 'password'),
    ('testuser30', 'testuser30@example.test', 'password'),
    ('testuser31', 'testuser31@example.test', 'password'),
    ('testuser32', 'testuser32@example.test', 'password'),
    ('testuser33', 'testuser33@example.test', 'password'),
    ('testuser34', 'testuser34@example.test', 'password'),
    ('testuser35', 'testuser35@example.test', 'password'),
    ('testuser36', 'testuser36@example.test', 'password'),
    ('testuser37', 'testuser37@example.test', 'password'),
    ('testuser38', 'testuser38@example.test', 'password'),
    ('testuser39', 'testuser39@example.test', 'password'),
    ('testuser40', 'testuser40@example.test', 'password'),
    ('testuser41', 'testuser41@example.test', 'password'),
    ('testuser42', 'testuser42@example.test', 'password'),
    ('testuser43', 'testuser43@example.test', 'password'),
    ('testuser44', 'testuser44@example.test', 'password'),
    ('testuser45', 'testuser45@example.test', 'password'),
    ('testuser46', 'testuser46@example.test', 'password'),
    ('testuser47', 'testuser47@example.test', 'password'),
    ('testuser48', 'testuser48@example.test', 'password'),
    ('testuser49', 'testuser49@example.test', 'password'),
    ('testuser50', 'testuser50@example.test', 'password');

INSERT INTO profiles (
    user_id, first_name, last_name, user_age, gender, match_gender, city,
    is_student, school, college_major, shared_groceries, is_pet_friendly,
    delegated_chores, cleanliness_preference, social_preference, guest_preference
)
SELECT users.id, profile_data.first_name, profile_data.last_name, profile_data.user_age,
       profile_data.gender, profile_data.match_gender, profile_data.city,
       profile_data.is_student, profile_data.school, profile_data.college_major,
       profile_data.shared_groceries, profile_data.is_pet_friendly,
       profile_data.delegated_chores, profile_data.cleanliness_preference,
       profile_data.social_preference, profile_data.guest_preference
FROM users
JOIN (
    SELECT 'testuser01' AS username, 'Avery' AS first_name, 'Morgan' AS last_name, 22 AS user_age, 'F' AS gender, 1 AS match_gender, 'Cincinnati' AS city, 1 AS is_student, 'University of Washington' AS school, 'Biology' AS college_major, 1 AS shared_groceries, 1 AS is_pet_friendly, 1 AS delegated_chores, 4 AS cleanliness_preference, 3 AS social_preference, 2 AS guest_preference
    UNION ALL SELECT 'testuser02', 'Jordan', 'Lee', 25, 'M', 1, 'Cincinnati', 0, NULL, NULL, 0, 1, 0, 3, 4, 3
    UNION ALL SELECT 'testuser03', 'Riley', 'Patel', 20, 'N', 0, 'Cincinnati', 1, 'San Francisco State University', 'Computer Science', 1, 0, 1, 5, 2, 1
    UNION ALL SELECT 'testuser04', 'Casey', 'Nguyen', 28, 'F', 1, 'Cincinnati', 0, NULL, NULL, 1, 1, 1, 2, 5, 3
    UNION ALL SELECT 'testuser05', 'Taylor', 'Brooks', 23, 'M', 1, 'Cincinnati', 1, 'University of Colorado Boulder', 'Environmental Studies', 0, 1, 0, 4, 3, 2
    UNION ALL SELECT 'testuser06', 'Quinn', 'Rivera', 31, 'O', 0, 'Cincinnati', 0, NULL, NULL, 1, 0, 1, 3, 2, 1
    UNION ALL SELECT 'testuser07', 'Morgan', 'Kim', 19, 'F', 1, 'Cincinnati', 1, 'Northeastern University', 'Design', 1, 1, 1, 5, 4, 3
    UNION ALL SELECT 'testuser08', 'Drew', 'Johnson', 27, 'M', 1, 'Cincinnati', 0, NULL, NULL, 0, 0, 0, 2, 3, 2
    UNION ALL SELECT 'testuser09', 'Skyler', 'Garcia', 24, 'N', 0, 'Cincinnati', 1, 'University of California, Los Angeles', 'Psychology', 1, 1, 0, 4, 5, 3
    UNION ALL SELECT 'testuser10', 'Jamie', 'Wilson', 35, 'F', 1, 'Cincinnati', 0, NULL, NULL, 0, 1, 1, 3, 2, 1
    UNION ALL SELECT 'testuser11', 'Alex', 'Chen', 21, 'M', 1, 'Cincinnati', 1, 'Seattle University', 'Mathematics', 1, 0, 1, 5, 3, 2
    UNION ALL SELECT 'testuser12', 'Cameron', 'Davis', 29, 'O', 0, 'Cincinnati', 0, NULL, NULL, 0, 1, 0, 1, 4, 3
    UNION ALL SELECT 'testuser13', 'Reese', 'Thompson', 26, 'F', 1, 'Cincinnati', 1, 'San Diego State University', 'Public Health', 1, 1, 1, 4, 4, 2
    UNION ALL SELECT 'testuser14', 'Logan', 'Martinez', 33, 'M', 1, 'Cincinnati', 0, NULL, NULL, 1, 0, 1, 3, 1, 1
    UNION ALL SELECT 'testuser15', 'Parker', 'Robinson', 18, 'N', 0, 'Cincinnati', 1, 'University of Wisconsin-Madison', 'History', 0, 1, 0, 2, 5, 3
    UNION ALL SELECT 'testuser16', 'Finley', 'Clark', 30, 'F', 1, 'Cincinnati', 0, NULL, NULL, 1, 1, 0, 5, 2, 2
    UNION ALL SELECT 'testuser17', 'Rowan', 'Lewis', 22, 'M', 1, 'Cincinnati', 1, 'Georgia State University', 'Economics', 0, 0, 1, 3, 3, 1
    UNION ALL SELECT 'testuser18', 'Emerson', 'Walker', 37, 'O', 0, 'Cincinnati', 0, NULL, NULL, 1, 1, 1, 4, 4, 3
    UNION ALL SELECT 'testuser19', 'Dakota', 'Hall', 24, 'F', 1, 'Cincinnati', 1, 'University of Texas at Austin', 'Architecture', 1, 0, 0, 2, 3, 2
    UNION ALL SELECT 'testuser20', 'Harper', 'Allen', 27, 'M', 1, 'Cincinnati', 0, NULL, NULL, 0, 1, 1, 5, 5, 3
    UNION ALL SELECT 'testuser21', 'Sage', 'Young', 20, 'N', 0, 'Cincinnati', 1, 'University of Colorado Boulder', 'Sociology', 1, 1, 1, 4, 2, 1
    UNION ALL SELECT 'testuser22', 'Elliot', 'King', 32, 'M', 1, 'Cincinnati', 0, NULL, NULL, 1, 0, 0, 3, 4, 2
    UNION ALL SELECT 'testuser23', 'Blake', 'Wright', 25, 'F', 1, 'Cincinnati', 1, 'Boston University', 'Communications', 0, 1, 1, 1, 3, 3
    UNION ALL SELECT 'testuser24', 'Robin', 'Scott', 39, 'O', 0, 'Cincinnati', 0, NULL, NULL, 1, 1, 0, 5, 1, 1
    UNION ALL SELECT 'testuser25', 'Kendall', 'Green', 23, 'F', 1, 'Cincinnati', 1, 'University of Minnesota', 'Chemistry', 0, 0, 1, 3, 5, 2
    UNION ALL SELECT 'testuser26', 'Micah', 'Adams', 28, 'M', 1, 'Cincinnati', 0, NULL, NULL, 1, 1, 1, 4, 3, 3
    UNION ALL SELECT 'testuser27', 'Shiloh', 'Baker', 19, 'N', 0, 'Cincinnati', 1, 'University of Southern California', 'Film', 0, 1, 0, 2, 4, 2
    UNION ALL SELECT 'testuser28', 'Frankie', 'Nelson', 34, 'F', 1, 'Cincinnati', 0, NULL, NULL, 1, 0, 1, 5, 3, 1
    UNION ALL SELECT 'testuser29', 'Remy', 'Carter', 21, 'M', 1, 'Cincinnati', 1, 'University of Wisconsin-Madison', 'Political Science', 1, 1, 0, 4, 2, 3
    UNION ALL SELECT 'testuser30', 'Arden', 'Mitchell', 26, 'O', 0, 'Cincinnati', 0, NULL, NULL, 0, 0, 1, 3, 5, 2
    UNION ALL SELECT 'testuser31', 'Jules', 'Perez', 30, 'F', 1, 'Cincinnati', 1, 'DePaul University', 'Education', 1, 1, 1, 2, 4, 1
    UNION ALL SELECT 'testuser32', 'Kai', 'Roberts', 22, 'M', 1, 'Cincinnati', 1, 'University of Texas at Austin', 'Engineering', 0, 0, 0, 5, 2, 2
    UNION ALL SELECT 'testuser33', 'Marley', 'Turner', 36, 'N', 0, 'Cincinnati', 0, NULL, NULL, 1, 1, 0, 4, 3, 3
    UNION ALL SELECT 'testuser34', 'Noel', 'Phillips', 24, 'F', 1, 'Cincinnati', 1, 'Vanderbilt University', 'Music', 0, 1, 1, 3, 5, 3
    UNION ALL SELECT 'testuser35', 'Ari', 'Campbell', 29, 'M', 1, 'Cincinnati', 0, NULL, NULL, 1, 0, 1, 1, 2, 1
    UNION ALL SELECT 'testuser36', 'Tatum', 'Parker', 18, 'O', 0, 'Cincinnati', 1, 'Northeastern University', 'Biochemistry', 1, 1, 0, 4, 4, 2
    UNION ALL SELECT 'testuser37', 'Lennon', 'Evans', 33, 'F', 1, 'Cincinnati', 0, NULL, NULL, 0, 0, 1, 5, 1, 1
    UNION ALL SELECT 'testuser38', 'River', 'Edwards', 27, 'M', 1, 'Cincinnati', 1, 'University of California, San Diego', 'Marine Biology', 1, 1, 1, 2, 3, 2
    UNION ALL SELECT 'testuser39', 'Wren', 'Collins', 20, 'N', 0, 'Cincinnati', 1, 'New York University', 'Literature', 0, 1, 0, 3, 4, 3
    UNION ALL SELECT 'testuser40', 'Ellis', 'Stewart', 31, 'F', 1, 'Cincinnati', 0, NULL, NULL, 1, 0, 0, 4, 5, 2
    UNION ALL SELECT 'testuser41', 'Briar', 'Sanchez', 23, 'M', 1, 'Cincinnati', 1, 'University of Washington', 'Informatics', 0, 1, 1, 5, 3, 1
    UNION ALL SELECT 'testuser42', 'Luca', 'Morris', 38, 'O', 0, 'Cincinnati', 0, NULL, NULL, 1, 1, 0, 2, 2, 2
    UNION ALL SELECT 'testuser43', 'Alden', 'Rogers', 25, 'F', 1, 'Cincinnati', 1, 'Loyola University Chicago', 'Social Work', 1, 0, 1, 3, 5, 3
    UNION ALL SELECT 'testuser44', 'Sasha', 'Reed', 28, 'N', 0, 'Cincinnati', 0, NULL, NULL, 0, 1, 1, 4, 1, 1
    UNION ALL SELECT 'testuser45', 'Milan', 'Cook', 19, 'M', 1, 'Cincinnati', 1, 'University of Wisconsin-Madison', 'Physics', 1, 1, 0, 2, 4, 2
    UNION ALL SELECT 'testuser46', 'Cleo', 'Morgan', 35, 'F', 1, 'Cincinnati', 0, NULL, NULL, 0, 0, 1, 5, 4, 3
    UNION ALL SELECT 'testuser47', 'Indigo', 'Bell', 26, 'O', 0, 'Cincinnati', 1, 'University of Texas at Austin', 'Linguistics', 1, 1, 1, 3, 2, 2
    UNION ALL SELECT 'testuser48', 'August', 'Murphy', 22, 'M', 1, 'Cincinnati', 1, 'University of California, Los Angeles', 'Economics', 0, 0, 0, 4, 3, 1
    UNION ALL SELECT 'testuser49', 'Phoenix', 'Bailey', 40, 'N', 0, 'Cincinnati', 0, NULL, NULL, 1, 1, 0, 1, 5, 3
    UNION ALL SELECT 'testuser50', 'Eden', 'Cooper', 24, 'F', 1, 'Cincinnati', 1, 'Boston University', 'Data Science', 0, 1, 1, 5, 2, 2
) AS profile_data ON users.username = profile_data.username;