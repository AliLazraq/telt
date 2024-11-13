package com.example.telt_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "can_data")
public class CanData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long canDataId;

    @Column(name = "avl_data_id")
    private Long avlDataId;

    @Column(name = "can_id")
    private Short canId;

    @Column(name = "value")
    private Long value;

    // Getters and Setters
    public Long getCanDataId() { return canDataId; }
    public void setCanDataId(Long canDataId) { this.canDataId = canDataId; }

    public Long getAvlDataId() { return avlDataId; }
    public void setAvlDataId(Long avlDataId) { this.avlDataId = avlDataId; }

    public Short getCanId() { return canId; }
    public void setCanId(Short canId) { this.canId = canId; }

    public Long getValue() { return value; }
    public void setValue(Long value) { this.value = value; }
}
