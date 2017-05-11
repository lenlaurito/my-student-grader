package com.synacy.whitelabel.mystudentgrader.student.grade;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.synacy.whitelabel.mystudentgrader.student.Student;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter @EqualsAndHashCode(of = {"id"})
@Entity
@JsonSerialize(using = GradeSerializer.class)
public class Grade {

	@Id @GeneratedValue
	private Long id;

	@Setter @NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Student student;

	@Setter @NotNull
	@Enumerated(EnumType.STRING)
	private Subject subject;

	@Setter @NotNull
	private BigDecimal finalGrade;

}
