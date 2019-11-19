#include "student.h"
#include <iostream>
#include "degree.h"
#include <string>

using namespace std;

Student::Student() {}

Student::Student(string ID, string firstN, string lastN, string emAd, int years, int* courseDays, Degree degreeProgram) {
	Id = ID;
	FirstN = firstN;
	LastN = lastN;
	EmAd = emAd;
	Years = years;
	CourseDays[0] = courseDays[0];
	CourseDays[1] = courseDays[1];
	CourseDays[2] = courseDays[2];
}

string Student::getID()
{
	return Id;
}

void Student::setID(string ID)
{
	Id = ID;
}

string Student::getFirstN()
{
	return FirstN;
}

void Student::setFirstN(string firstN)
{
	FirstN = firstN;
}

string Student::getLastN()
{
	return LastN;
}

void Student::setLastN(string lastN)
{
	LastN = lastN;
}

int Student::getYears()
{
	return Years;
}

void Student::setYears(int years)
{
	Years = years;
}


int* Student::getCourseDays()
{
	return CourseDays;
}

void Student::setCourseDays(int D1, int D2, int D3)
{
	Student::CourseDays[0] = D1;
	Student::CourseDays[1] = D2;
	Student::CourseDays[2] = D3;
}

Degree Student::getDegreeProg()
{
	return Degree();
}

void Student::setDegreeProgram(Degree DegreeProgram)
{
	degreeProgram = DegreeProgram;
}


string Student::getEmAd()
{
	return EmAd;
}

void Student::setEmAd(string emAd)
{
	EmAd = emAd;
}




void Student::print()
{
	cout << getID() << "\t";
	cout << "First Name: " << getFirstN() << "\t";
	cout << "Last Name: " << getLastN() << "\t";
	cout << "Age: " << getYears() << "\t\t";
	cout << "daysInCourse: " << getCourseDays()[0] << ", " << getCourseDays()[1] << ", " << getCourseDays()[2] << "\t";

	cout << "Degree Program: ";
	if (getDegreeProgram() == SECURITY) {
		cout << "Security";
	}
	else if (getDegreeProgram() == NETWORKING){
		cout << "Networking";
	}
	else {
		cout << "Software";
	}
	cout << "\n";
}

Degree Student::getDegreeProgram()
{
	return SECURITY;
}

Student::~Student()
{
}
