package com.javaclass.Springboot.learning.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.javaclass.Springboot.learning.entity.Department;

import static com.javaclass.Springboot.learning.entity.Department.*;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DepartmentRepositoryTest {
@Autowired
private DepartmentRepository departmentRepository;
@Autowired
private TestEntityManager entityManager;
    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .departmentName("Mechanical Engineering")
                .departmentCode("ME-011")
                .departmentAddress("Delhi")
                .departmentId(0).build();
        entityManager.persistAndGetId(department);
    }
    @Test
  public void whenFindById_thenReturnDepartment(){
    Department department = departmentRepository.findById(1l).get();
    assertEquals(department.getDepartmentName(),"Mechanical Engineering");
}
}