package com.dan.springdatajpatutorial.repository;

import com.dan.springdatajpatutorial.entity.Course;
import com.dan.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {
        Course course1 = Course.builder()
                .courseTitle("COMP")
                .credits(10)
                .build();

        Course course2 = Course.builder()
                .courseTitle("ST")
                .credits(3)
                .build();

        Teacher teacher = Teacher.builder()
                .firstName("Dan")
                .lastName("Hotico")
                .build();

        teacherRepository.save(teacher);
    }
}