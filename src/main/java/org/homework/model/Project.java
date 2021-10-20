package org.homework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "projects")
public class Project implements BaseModel<Long> {

    private static final long serialVersionUID = 10000000001L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "cost", nullable = false, length = 15)
    private Long cost;

    @Column(name = "first_date", nullable = false, length = 50)
    private String firstDate = new Date().toString();

    @Column(name = "company_id", nullable = false, length = 10)
    private Long companyId;

    @Column(name = "customer_id", nullable = false, length = 10)
    private Long customerId;
}
