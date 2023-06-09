package com.javaclass.Springboot.learning.service;

import com.javaclass.Springboot.learning.entity.Department;
import com.javaclass.Springboot.learning.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentServiceTest {
    @Autowired
    private DepartmentService departmentService;
    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department = Department.builder().departmentName("ECE")
                .departmentAddress("Hyderabad")
                .departmentCode("36")
                .departmentId(1l)
                .build();
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("ECE")).thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid Department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFounf() {
        String departmentName = "ECE";
        Department found = departmentService.fetchDepartmentByName(departmentName);
        assertEquals(departmentName, found.getDepartmentName());
    }
}