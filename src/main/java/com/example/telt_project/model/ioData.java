package com.example.telt_project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "io_data")
public class ioData {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long ioElementId;
    
        @Column(name = "avl_data_id")
        private Long avlDataId;
    
        @Column(name = "io_id")
        private Short ioId;
    
        @Column(name = "value")
        private Long value;
    
        // Getters and Setters
        public Long getIoElementId() { return ioElementId; }
        public void setIoElementId(Long ioElementId) { this.ioElementId = ioElementId; }
    
        public Long getAvlDataId() { return avlDataId; }
        public void setAvlDataId(Long avlDataId) { this.avlDataId = avlDataId; }
    
        public Short getIoId() { return ioId; }
        public void setIoId(Short ioId) { this.ioId = ioId; }
    
        public Long getValue() { return value; }
        public void setValue(Long value) { this.value = value; }
    }
