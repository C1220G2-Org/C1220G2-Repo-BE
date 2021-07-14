package com.codegym.spring_boot_sprint_1.model.dto;

public class PropertyInRoomDto {
    Long id;
    String name;
    Integer amount;
    Integer amountTotal;

    @Override
    public String toString() {
        return "PropertyDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }

    public PropertyInRoomDto() {
    }

    public PropertyInRoomDto(Long id, String name, Integer amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public PropertyInRoomDto(Long id, String name, Integer amount, Integer amountTotal) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.amountTotal = amountTotal;
    }

    public Integer getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(Integer amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
