#pragma once
#ifndef Software_H
#define Software_H
#include "student.h"

class SoftwareStudent : public Student {
public:
	Degree getDegreeProgram() override;
	using Student::Student;
private:
	Degree degreeProgram = SOFTWARE;
};

#endif