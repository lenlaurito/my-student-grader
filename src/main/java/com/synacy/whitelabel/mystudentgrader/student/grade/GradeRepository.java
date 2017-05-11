package com.synacy.whitelabel.mystudentgrader.student.grade;

import com.synacy.whitelabel.mystudentgrader.student.Student;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface GradeRepository extends PagingAndSortingRepository<Grade, Long> {

	public Grade findByStudentAndId(Student student, Long id);

}
