package uz.pdp.appcodingbat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortInfo;

    @Column(nullable = false)
    private Integer starCount;

    @ManyToOne
    private ProgrammingLanguage programmingLanguage;
}
