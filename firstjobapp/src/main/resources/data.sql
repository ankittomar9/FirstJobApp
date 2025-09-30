-- Clear existing data (optional, be careful in production)
TRUNCATE TABLE review, job, company CASCADE;

-- Reset sequences (for PostgreSQL)
ALTER SEQUENCE IF EXISTS company_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS job_id_seq RESTART WITH 1;
ALTER SEQUENCE IF EXISTS review_id_seq RESTART WITH 1;

-- Insert sample companies
INSERT INTO company (id, name, description) VALUES
(1, 'TechCorp', 'Leading technology solutions provider'),
(2, 'DataSystems', 'Data analytics and cloud services'),
(3, 'WebMasters', 'Web development and design agency');

-- Insert sample jobs
INSERT INTO job (id, title, description, min_salary, max_salary, location, company_id) VALUES
(1, 'Senior Java Developer', 'Looking for experienced Java developers', '80000', '120000', 'Remote', 1),
(2, 'Data Scientist', 'Data analysis and machine learning', '90000', '140000', 'New York', 2),
(3, 'Frontend Developer', 'React.js and modern frontend development', '70000', '110000', 'Remote', 3),
(4, 'DevOps Engineer', 'Cloud infrastructure and CI/CD pipelines', '85000', '130000', 'Remote', 1),
(5, 'UX Designer', 'User experience and interface design', '75000', '115000', 'San Francisco', 3);

-- Insert sample reviews
INSERT INTO review (id, title, description, rating, company_id) VALUES
(1, 'Great place to work', 'Excellent work culture and benefits', 4.5, 1),
(2, 'Good learning experience', 'Good projects but work-life balance could be better', 3.8, 2),
(3, 'Amazing team', 'Supportive environment with great colleagues', 4.7, 1),
(4, 'Challenging projects', 'Great opportunities to work on cutting-edge technology', 4.2, 3),
(5, 'Needs improvement', 'Good people but management could be better', 3.5, 2);
