package com.codegym.spring_boot_sprint_1.model;

import javax.persistence.*;

@Entity
@Table()
public class StatisticsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String count;

    public StatisticsDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "StatisticsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
