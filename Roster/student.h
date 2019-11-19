#pragma once
#ifndef STUDENT_H
#define STUDENT_H
#include <string>
#include "degree.h"
using namespace std;

class Student
{
public:
	//Constructor Function
	Student();
	Student(string ID, string firstN, string lastN, string emAd, int years, int* courseDays, Degree DegreeProgram);
	//Accessors
	Degree getDegreeProg();
	int* getCourseDays();
	string getFirstN();
	string getLastN();
	string getID();
	int getYears();
	string getEmAd();
	//Mutators
	void setDegreeProgram(Degree);
	void setCourseDays(int D1, int D2, int D3);
	void setFirstN(string FirstN);
	void setLastN(string LastN);
	void setID(string Id);
	void setYears(int Years);
	void setEmAd(string EmAd);
	//Virtual
	virtual void print();
	virtual Degree getDegreeProgram();
	//Destructor
	~Student();
private:
	int Years, CourseDays[3];
	string EmAd, LastN, FirstN, Id;
	Degree degreeProgram;
};
#endif