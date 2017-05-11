package com.synacy.whitelabel.mystudentgrader.student.grade;

import com.synacy.whitelabel.mystudentgrader.student.Student;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Grade {

	@Id @GeneratedValue
	private Long id;

	@Setter
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;

	@Setter
	private Subject subject;

	@Setter
	private BigDecimal finalGrade;

}
