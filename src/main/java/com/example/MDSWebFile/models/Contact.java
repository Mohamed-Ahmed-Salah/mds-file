package com.example.MDSWebFile.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "contact_form")
public class Contact extends Model{
    private String name;
    private String email;
    private String message;
    private String status;
}
