CREATE  TABLE IF NOT EXISTS employee(
                                        id VARCHAR(10) PRIMARY KEY ,
                                        name VARCHAR(100) NOT NULL ,
                                        DOB DATE,
                                        nic VARCHAR(14) NOT NULL UNIQUE ,
                                        contact VARCHAR(15) NOT NULL ,
                                        password VARCHAR(30) NOT NULL ,
                                        username VARCHAR(30) NOT NULL ,
                                        role VARCHAR(30) not null ,
                                        status VARCHAR(30),
                                        address VARCHAR(50)

);



CREATE TABLE IF NOT EXISTS profile_picture(
                                              id SERIAL PRIMARY KEY ,
                                              employee_nic VARCHAR(14),
                                              profile_picture BYTEA,
                                              CONSTRAINT fk2_enrollment FOREIGN KEY (employee_nic) REFERENCES employee(nic)

);
