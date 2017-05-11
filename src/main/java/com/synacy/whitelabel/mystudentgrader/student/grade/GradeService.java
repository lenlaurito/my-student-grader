package com.synacy.whitelabel.mystudentgrader.student.grade;

import com.synacy.whitelabel.mystudentgrader.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class GradeService {

	@Autowired
	GradeRepository gradeRepository;

	public Grade fetchByStudentAndId(Student student, Long id) {
		return gradeRepository.findByStudentAndId(student, id);
	}

	public List<Grade> fetchAllGradesOfStudent(Student student) {
		return student.getGrades();
	}

	public Grade createGrade(Student student, Subject subject, BigDecimal finalGrade) {
		Grade grade = new Grade();
		grade.setStudent(student);
		grade.setSubject(subject);
		grade.setFinalGrade(finalGrade);
		return gradeRepository.save(grade);
	}

	public Grade updateGrade(Grade grade, Student student, Subject subject, BigDecimal finalGrade) {
		grade.setStudent(student);
		grade.setSubject(subject);
		grade.setFinalGrade(finalGrade);
		return gradeRepository.save(grade);
	}

	public void deleteGrade(Grade grade) {
		gradeRepository.delete(grade);
	}
}
