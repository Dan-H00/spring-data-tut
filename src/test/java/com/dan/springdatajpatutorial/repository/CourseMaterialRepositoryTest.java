package com.dan.springdatajpatutorial.repository;

import com.dan.springdatajpatutorial.entity.Course;
import com.dan.springdatajpatutorial.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {
        Course course = Course.builder()
                .courseTitle("C#")
                .credits(5)
                .build();

        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.youtube.com")
                .course(course)
                .build();

        courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterialsList = courseMaterialRepository.findAll();

        System.out.println(courseMaterialsList);
    }
}