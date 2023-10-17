package lk.ijse.dep11.ea.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee implements Serializable {
    private String id;
    private String name;
    private String nic;
    private  String address;
    private LocalDate DOB;

    private String branch;
    private String status;
    private String contact;
    private  String password;
    private String role;
    private String username;
    private byte[] byteImg;


}
