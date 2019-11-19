#ifndef ROSTER_H
#define ROSTER_H
#include <array>
#include "student.h"

using namespace std;

class Roster {
public:
	Roster();

	virtual ~Roster();
	
	void printInvalidEmails();

	void printAll();

	void remove(string ID);

	void add(string ID, string firstN, string lastN, string emAd, int years, int D1, int D2, int D3, Degree degreeProgram);
	
	void printAverageDaysInCourse(string);

	void printByDegreeProgram(int degreeProgram);

	int addIndex = 0;
	
	
	Student* classRosterArray[5] = { nullptr, nullptr, nullptr, nullptr, nullptr };
};
#endif // ROSTER_H