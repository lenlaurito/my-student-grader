package com.synacy.whitelabel.mystudentgrader.student;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.synacy.whitelabel.mystudentgrader.student.grade.Grade;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@EqualsAndHashCode(of = {"id"})
@Entity
public class Student {

	@Id @GeneratedValue
	private Long id;

	@Setter @NotNull
	private String name;

	@Setter
	private int age;

	@Setter @NotNull
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Setter @NotNull
	@Enumerated(EnumType.STRING)
	private YearLevel yearLevel;

	@Setter @JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
	private List<Grade> grades;
}
