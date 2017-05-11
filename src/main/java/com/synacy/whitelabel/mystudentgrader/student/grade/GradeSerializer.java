package com.synacy.whitelabel.mystudentgrader.student.grade;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GradeSerializer extends JsonObjectSerializer<Grade> {


	@Override
	protected void serializeObject(Grade grade, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeNumberField("studentId", grade.getStudent().getId());
		jgen.writeStringField("subject", grade.getSubject().name());
		jgen.writeNumberField("finalGrade", grade.getFinalGrade());
	}
}
