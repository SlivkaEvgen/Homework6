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
@Entity(name = "customers")
public class Customer implements BaseModel<Long> {

    private static final long serialVersionUID = 10000000003L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "city", nullable = false, length = 15)
    private String city;

    @Column(name = "budget", nullable = false, length = 15)
    private Long budget;
}
