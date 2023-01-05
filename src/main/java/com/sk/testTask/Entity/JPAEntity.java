package com.sk.testTask.Entity;

import com.sk.testTask.MappingObjects.Counter;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sk_example_table")
@TypeDef(name = "json",
        typeClass = JsonType.class)
public class JPAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Type(type = "json")
    @Column(name="obj",columnDefinition = "jsonb")
    private Counter counter;
}
