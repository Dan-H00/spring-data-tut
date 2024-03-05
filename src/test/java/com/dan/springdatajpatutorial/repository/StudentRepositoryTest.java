package com.dan.springdatajpatutorial.repository;

import com.dan.springdatajpatutorial.entity.Guardian;
import com.dan.springdatajpatutorial.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudentWithoutGuardian() {
        Student student = Student.builder()
                .emailId("dan@yahoo.com")
                .firstName("Dan")
                .lastName("Hotico")
                .build();

        studentRepository.save(student);
    }

    @Test
    public void saveStudentWithGuardian() {
        Guardian guardian = Guardian.builder()
                .name("Dan")
                .email("dan@yahoo.com")
                .mobile("0732415515")
                .build();

        Student student = Student.builder()
                .firstName("Emma")
                .emailId("emma@yahoo.com")
                .lastName("Loki")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }

    @Test
    public void printAllStudents() {
        List<Student> studentList =
                studentRepository.findAll();

        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstName() {
        List<Student> studentList = studentRepository.findByFirstName("Dan");

        System.out.println(studentList);
    }

    @Test
    public void printStudentByFirstNameContaining() {
        List<Student> studentList = studentRepository.findByFirstNameContaining("a");

        System.out.println(studentList);
    }

    @Test
    public void printStudentBasedOnGuardianName() {
        List<Student> studentList = studentRepository.findByGuardianName("Dan");

        System.out.println(studentList);
    }

    @Test
    public void printStudentBasedOnEmail() {
        Student student = studentRepository.getStudentByEmailAddress("dan@yahoo.com");

        System.out.println(student);
    }

    @Test
    public void printFirstNameBasedOnEmail() {
        String firstName = studentRepository.getFirstNameByEmailAddress("dan@yahoo.com");

        System.out.println(firstName);
    }

    @Test
    public void printStudentBasedOnEmailNative() {
        Student student = studentRepository.getStudentByEmailAddressNative("dan@yahoo.com");

        System.out.println(student);
    }

    @Test
    public void printStudentBasedOnEmailNativeNamedParam() {
        Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("dan@yahoo.com");

        System.out.println(student);
    }

    @Test
    public void updateStudentBasedOnEmail() {
        studentRepository.updateStudentNameByEmail("Ionut", "dan@yahoo.com");
    }
}