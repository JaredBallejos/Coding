#pragma once
#ifndef Security_H
#define Security_H
#include "student.h"

class SecurityStudent : public Student {
public:
	Degree getDegreeProgram() override;
	using Student::Student;
private:
	Degree degreeProgram = SECURITY;
};

#endif