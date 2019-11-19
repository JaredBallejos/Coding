#pragma once
#ifndef Network_H
#define Network_H
#include "student.h"

class NetworkStudent : public Student {
public:
	Degree getDegreeProgram() override;
		using Student::Student;

private:
	Degree degreeProgram = NETWORKING;
};

#endif