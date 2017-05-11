package com.synacy.whitelabel.mystudentgrader.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	public Student fetchById(Long id) {
		return studentRepository.findOne(id);
	}

	public List<Student> fetchAll() {
		return (List) studentRepository.findAll();
	}

	public Student createStudent(String name, int age, Gender gender, YearLevel yearLevel) {
		Student student = new Student();
		student.setName(name);
		student.setAge(age);
		student.setGender(gender);
		student.setYearLevel(yearLevel);
		return studentRepository.save(student);
	}

	public Student updateStudent(Student studentToBeUpdated, String name, int age, Gender gender, YearLevel yearLevel) {
		studentToBeUpdated.setName(name);
		studentToBeUpdated.setAge(age);
		studentToBeUpdated.setGender(gender);
		studentToBeUpdated.setYearLevel(yearLevel);
		return studentRepository.save(studentToBeUpdated);
	}

	public void deleteStudent(Student studentToBeDeleted) {
		studentRepository.delete(studentToBeDeleted);
	}
}
