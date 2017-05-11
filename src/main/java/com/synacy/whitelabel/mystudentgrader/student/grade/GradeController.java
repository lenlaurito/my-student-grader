package com.synacy.whitelabel.mystudentgrader.student.grade;

import com.synacy.whitelabel.mystudentgrader.ResourceNotFoundException;
import com.synacy.whitelabel.mystudentgrader.student.Student;
import com.synacy.whitelabel.mystudentgrader.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/student/{studentId}/grade")
public class GradeController {

	@Autowired
	StudentService studentService;

	@Autowired
	GradeService gradeService;

	@RequestMapping(method = RequestMethod.GET, value = "/{gradeId}")
	public Grade fetchGradeOfStudent(@PathVariable(value = "studentId") Long studentId,
	                                 @PathVariable(value = "gradeId") Long gradeId) {
		Student student = studentService.fetchById(studentId);
		if (student == null) {
			throw new ResourceNotFoundException("Student does not exist");
		}
		Grade grade = gradeService.fetchByStudentAndId(student, gradeId);
		if (grade == null) {
			throw new ResourceNotFoundException("Grade does not exist for student");
		}
		return grade;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Grade> fetchAllGradesOfStudent(@PathVariable(value = "studentId") Long studentId) {
		Student student = studentService.fetchById(studentId);
		if (student == null) {
			throw new ResourceNotFoundException("Student does not exist");
		}
		return gradeService.fetchAllGradesOfStudent(student);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Grade createGradeForStudent(@PathVariable(value = "studentId") Long studentId,
	                                   @RequestBody Grade gradeRequest) {
		Student student = studentService.fetchById(studentId);
		if (student == null) {
			throw new ResourceNotFoundException("Student does not exist");
		}
		return gradeService.createGrade(student, gradeRequest.getSubject(), gradeRequest.getFinalGrade());
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{gradeId}")
	public Grade updateGradeOfStudent(@PathVariable(value = "studentId") Long studentId,
	                                  @PathVariable(value = "gradeId") Long gradeId,
	                                  @RequestBody Grade gradeRequest) {
		Student student = studentService.fetchById(studentId);
		if (student == null) {
			throw new ResourceNotFoundException("Student does not exist");
		}
		Grade grade = gradeService.fetchByStudentAndId(student, gradeId);
		if (grade == null) {
			throw new ResourceNotFoundException("Grade does not exist for student");
		}
		return gradeService.updateGrade(grade, student, gradeRequest.getSubject(), gradeRequest.getFinalGrade());
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{gradeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteGradeOfStudent(@PathVariable(value = "studentId") Long studentId,
	                                 @PathVariable(value = "gradeId") Long gradeId) {
		Student student = studentService.fetchById(studentId);
		if (student == null) {
			throw new ResourceNotFoundException("Student does not exist");
		}
		Grade grade = gradeService.fetchByStudentAndId(student, gradeId);
		if (grade == null) {
			throw new ResourceNotFoundException("Grade does not exist for student");
		}
		gradeService.deleteGrade(grade);
	}

}
