#include "degree.h"
#include <iostream>
#include "networkStudent.h"
#include "roster.h"
#include <sstream>
#include <string>
#include "securityStudent.h"
#include "softwareStudent.h"
#include "student.h"
#include <vector>

using namespace std;

//Main Function
void main()
{

	const string studentData[] =
	{ "A1,John,Smith,John1989@gm ail.com,20,30,35,40,SECURITY",
	"A2,Suzan,Erickson,Erickson_1990@gmailcom,19,50,30,40,NETWORK",
	"A3,Jack,Napoli,The_lawyer99yahoo.com,19,20,40,33,SOFTWARE",
	"A4,Erin,Black,Erin.black@comcast.net,22,50,58,40,SECURITY",
	"A5,Jared,Ballejos,jball97@wgu.edu,19,10,30,50,SOFTWARE"
	};

	//Roster Instance
	Roster classRoster;

	//Degree Instance
	Degree Program;

	// Students Added
	for (int x = 0; x < 5; x++) {
		stringstream ss(studentData[x]);

		vector<string> r;

		while (ss.good()) {
			string substr;
			getline(ss, substr, ',');
			r.push_back(substr);
		}
		if (r[8] == "SECURITY") {
			Program = Degree::SECURITY;
		}
		if (r[8] == "SOFTWARE") {
			Program = Degree::SOFTWARE;
		}
		if (r[8] == "NETWORK") {
			Program = Degree::NETWORKING;
		}
		classRoster.add(r[0], r[1], r[2], r[3], stoi(r[4]), stoi(r[5]),
			stoi(r[6]), stoi(r[7]), Program);
	}



	//Personal Info 
	cout << "C867: Scripting and Programming Applications" << endl;
	cout << "C++" << endl;
	cout << "Student ID#: 001130602" << endl;
	cout << "Jared Ballejos" << endl;
	cout << endl;

	// Printing Everything

	classRoster.printAll();
	classRoster.printInvalidEmails();
	classRoster.printAverageDaysInCourse("A1");
	classRoster.printByDegreeProgram(SOFTWARE);
	classRoster.remove("A3");
	classRoster.remove("A3");
}

//Destructor
Roster::~Roster() {}


Roster::Roster() {}

// Invalid Email Checker
void Roster::printInvalidEmails() {
	cout << '\n' << "Displaying invalid emails:" << '\n';
	cout << endl;
	for (int x = 0; x < 5; x++) {
		bool At = false;
		bool Period = false;
		bool Space = false;
		string email = "";
		email = (classRosterArray[x])->getEmAd();
		for (char& c : email) {
			if (c == '@') {
				At = true;
			}
			if (c == '.') {
				Period = true;
			}
			if (c == ' ') {
				Space = true;
			}
		}
		if (At == false || Period == false || Space == true) {
			cout << (classRosterArray[x])->getEmAd() << '\n';
		}
	}
	cout << '\n';
}

//Printing Roster
void Roster::printAll() {
	cout << '\n' << "Displaying roster:" << '\n';
	cout << endl;
	for (int x = 0; x < 5; x++) {
		(classRosterArray[x])->print();
	}
	cout << '\t';
}

// Removing Students
void Roster::remove(string ID) {
	bool stuRemove = false;
	for (int x = 0; x < 5; x++) {
		if (classRosterArray[x] != NULL && ID == classRosterArray[x]->getID()) {
			classRosterArray[x] = nullptr;
			stuRemove = true;
			break;
		}
	}
	if (stuRemove == true) {
		cout << '\n' << "Displaying Removed Students:" << '\n';
		cout << endl;
		cout << "Student ID: " << ID << " was not found." << '\n';
	}
}

//Populating Roster Array
void Roster::add(string ID, string firstN, string lastN, string emAd, int years, int D1, int D2, int D3, Degree degreeProgram)
{
	int courseDaysIn[3] = { D1, D2, D3 };

	for (int x = 0; x < 5; x++) {
		if (classRosterArray[x] == nullptr) {
			if (degreeProgram == SOFTWARE) {
				classRosterArray[x] = new SoftwareStudent(ID, firstN, lastN, emAd, years, courseDaysIn, degreeProgram);
			}
			else if (degreeProgram == NETWORKING) {
				classRosterArray[x] = new NetworkStudent(ID, firstN, lastN, emAd, years, courseDaysIn, degreeProgram);
			}
			else {
				classRosterArray[x] = new SecurityStudent(ID, firstN, lastN, emAd, years, courseDaysIn, degreeProgram);
			}

			break;
		}
	}
}

//  Average Days
void Roster::printAverageDaysInCourse(string ID) {
	cout << '\n' << "Displaying Average Days to Complete 3 Courses:" << '\n';
	cout << endl;
	for (int x = 0; x < 5; x++) {
		if ((classRosterArray[x])->getID() == ID) {
			int avg = 0;
			avg = ((classRosterArray[x])->getCourseDays()[0] + (classRosterArray[x])->getCourseDays()[1]
				+ (classRosterArray[x])->getCourseDays()[2]) / 3;
			cout << "The average days it took the student with studentID: " << ID << " to finish 3 courses: " << avg << '\n';
		}
	}
	cout << '\n';
}



// Separating Students into Degree Programs
void Roster::printByDegreeProgram(int degreeProgram) {
	cout << '\n' << "Displaying Students in Selected Degree Program: \n";
	cout << endl;
	for (int x = 0; x < 5; x++) {
		if (classRosterArray[x]->getDegreeProgram() == degreeProgram) {
			classRosterArray[x]->print();
		}
	}
	cout << '\n';
}




