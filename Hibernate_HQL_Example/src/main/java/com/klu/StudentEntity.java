package com.klu;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;
    private int marks;

    public StudentEntity() {}

    public StudentEntity(int id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    // getters and setters
    public int getId()
    { return id; }
    public void setId(int id)
    { this.id = id; }

    public String getName() 
    { return name; }
    public void setName(String name) 
    { this.name = name; }

    public int getMarks()
    { return marks; }
    public void setMarks(int marks)
    { this.marks = marks; }
}
