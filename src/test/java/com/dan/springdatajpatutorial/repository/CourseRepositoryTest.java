package com.dan.springdatajpatutorial.repository;

import com.dan.springdatajpatutorial.entity.Course;
import com.dan.springdatajpatutorial.entity.Student;
import com.dan.springdatajpatutorial.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourses() {
        List<Course> courseList = courseRepository.findAll();

        System.out.println(courseList);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Loki")
                .lastName("Emma")
                .build();

        Course course1 = Course.builder()
                .courseTitle("assembly")
                .credits(3)
                .teacher(teacher)
                .build();

        courseRepository.save(course1);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWith3Records = PageRequest.of(0, 3);
        Pageable secondPageWith2Records = PageRequest.of(1, 2);

        List<Course> courseList = courseRepository.findAll(secondPageWith2Records).getContent();

        long totalElements = courseRepository.findAll(secondPageWith2Records).getTotalElements();
        long totalPages = courseRepository.findAll(secondPageWith2Records).getTotalPages();

        System.out.println("elem = " + totalElements);
        System.out.println("pages = " + totalPages);
        System.out.println(courseList);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(0, 5, Sort.by("courseTitle"));
        Pageable sortByCreditsDesc = PageRequest.of(0, 5, Sort.by("credits").descending());
        Pageable sortByTitleAndCreditsDesc = PageRequest.of(0, 5, Sort.by("courseTitle").descending().and(Sort.by("credits")));

        List<Course> courseListSortedByTitle = courseRepository.findAll(sortByTitle).getContent();
        List<Course> courseListSortedByCreditsDesc = courseRepository.findAll(sortByCreditsDesc).getContent();
        List<Course> courseListSortedByTitleDescCredits = courseRepository.findAll(sortByTitleAndCreditsDesc).getContent();

        System.out.println(courseListSortedByTitle);
        System.out.println(courseListSortedByCreditsDesc);
        System.out.println(courseListSortedByTitleDescCredits);
    }

    @Test
    public void printFindByTitleContaining() {
        Pageable firstPageTenRecords = PageRequest.of(0 ,10);

        List<Course> courses = courseRepository.findByCourseTitleContaining("a", firstPageTenRecords).getContent();

        System.out.println(courses);
    }

    @Test
    public void saveCourseWithStudentAndTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Coco")
                .lastName("Chanel")
                .build();

        Student student = Student.builder()
                .firstName("Ionut")
                .lastName("Cercel")
                .emailId("ionut.cercel@yahoo.com")
                .build();

        Course course = Course.builder()
                .courseTitle("AI")
                .credits(12)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }
}