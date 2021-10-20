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
@Entity(name = "skills")
public class Skill implements BaseModel<Long> {

    private static final long serialVersionUID = 10000000000L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity", nullable = false, length = 15)
    private String activity;

    @Column(name = "level", nullable = false, length = 15)
    private String level;
}
