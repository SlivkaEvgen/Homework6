package org.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "developers")
public class Developer implements BaseModel<Long> {

    private static final long serialVersionUID = 10000000002L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "age", nullable = false, length = 15)
    private Long age;

    @Column(name = "gender", nullable = false, length = 15)
    private String gender;

    @Column(name = "email", nullable = false, length = 15)
    private String email;

    @Column(name = "salary", nullable = false, length = 15)
    private Long salary;

    @Column(name = "company_id", nullable = false, length = 15)
    private Long companyId;
}
