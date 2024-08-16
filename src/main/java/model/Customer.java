package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Customer {
    private String customerId;
    private String title;
    private String name;
    private String address;
    private String contactNumber;
    private LocalDate dateOfBirth;

}
