package com.synacy.whitelabel.mystudentgrader.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/student")
public class StudentController {

	@Autowired
	StudentService studentService;

	@RequestMapping(method = RequestMethod.GET, value = "/{studentId}")
	public Student fetchStudent(@PathVariable(value="studentId") Long studentId) {
		return studentService.fetchById(studentId);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity fetchAllStudents(@RequestParam(value = "offset", required = false) Integer offset,
	                                       @RequestParam(value = "max", required = false) Integer max) {
		if (offset == null && max == null) {
			List<Student> allStudents = studentService.fetchAll();
			return ResponseEntity.ok().body(allStudents);
		}
		else {
			//We can add checking here to validate that both max and offset are specified
			Page<Student> paginatedStudents = studentService.fetchPaginatedStudents(offset, max);
			return ResponseEntity.ok().body(paginatedStudents);
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Student createNewStudent(@RequestBody Student studentRequest) {
		return  studentService.createStudent(studentRequest.getName(), studentRequest.getAge(),
				studentRequest.getGender(), studentRequest.getYearLevel());
	}

	@RequestMapping(method = RequestMethod.PUT, value="/{studentId}")
	public Student updateStudent(@PathVariable(value="studentId") Long studentId,
	                             @RequestBody Student studentRequest) {
		Student student = studentService.fetchById(studentId);
		return studentService.updateStudent(student, studentRequest.getName(), studentRequest.getAge(),
				studentRequest.getGender(), studentRequest.getYearLevel());
	}

	@RequestMapping(method = RequestMethod.DELETE, value="/{studentId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteStudent(@PathVariable(value="studentId") Long studentId) {
		Student student = studentService.fetchById(studentId);
		studentService.deleteStudent(student);
	}
}
