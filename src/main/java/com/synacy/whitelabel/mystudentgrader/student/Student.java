package com.synacy.whitelabel.mystudentgrader.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.synacy.whitelabel.mystudentgrader.student.grade.Grade;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Student {

	@Id @GeneratedValue
	private Long id;

	@Setter
	private String name;

	@Setter
	private int age;

	@Setter
	private Gender gender;

	@Setter
	private YearLevel yearLevel;

	@Setter @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
	private List<Grade> grades;
}
