CREATE TABLE Department (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(50) NOT NULL,
    job_position ARRAY,
    supervisor_id BIGINT
);

CREATE TABLE Employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    personal_id VARCHAR(11) NOT NULL,
    birth_date DATE,
    email VARCHAR(30),
    sex VARCHAR(1) NOT NULL,
    job_position VARCHAR(30),
    department_id BIGINT NOT NULL,
    CONSTRAINT employees_dept_fk FOREIGN KEY (department_id) REFERENCES Department (id)
);