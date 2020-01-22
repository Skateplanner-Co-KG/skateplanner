INSERT INTO users(email, username, password, role, notify) VALUES
('member@example.com', 'member', 'pass', 'MEMBER', false),
('trainer@example.com', 'trainer', 'pass', 'TRAINER', false);

INSERT INTO events(name, type, description, date) VALUES
('test1', 'meta', 'some mysterious event', CURRENT_DATE),
('test2', 'meta', 'another strange event', CURRENT_DATE);