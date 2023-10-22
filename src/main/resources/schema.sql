CREATE  TABLE IF NOT EXISTS employee(
                                        id VARCHAR(10) PRIMARY KEY ,
                                        name VARCHAR(100) NOT NULL ,
                                        DOB DATE,
                                        nic VARCHAR(14) NOT NULL UNIQUE ,
                                        contact VARCHAR(15) NOT NULL ,
                                        password VARCHAR(30) NOT NULL ,
                                        username VARCHAR(30) NOT NULL UNIQUE ,
                                        role VARCHAR(30) not null ,
                                        status VARCHAR(30),
                                        address VARCHAR(50),
                                        branch VARCHAR(50)

);

-- DROP TABLE profile_picture;
-- DROP TABLE employee;

CREATE TABLE IF NOT EXISTS profile_picture(
                                              id SERIAL PRIMARY KEY ,
                                              employee_id VARCHAR(10),
                                              profile_picture BYTEA,
                                              CONSTRAINT fk2_enrollment FOREIGN KEY (employee_id) REFERENCES employee(id)

);
