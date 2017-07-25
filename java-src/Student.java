import java.util.ArrayList;

public class Student {
	//student information
	double gpa;
	int id;
	String password;
	int grade;
	String fname, lname;
	Class[] schedule;
	int[] index;
	School school;
	double[] grades;
	ArrayList<Absent> absence; 
	
	String[] letters;
	//return student's name, ID, and grade as a string
	public String toString() {
		return fname + " " + lname + ". ID: " + id + ". Grade: " + grade;
	}
	
	public Student(int idid, int gr, String f, String l) {
		gpa = 0;
		id = idid;
		grade = gr;
		fname = f;
		lname = l;
		schedule = new Class[8];
		index = new int[8];
		grades = new double[8];
		letters = new String[8];
		absence = new ArrayList<Absent>();
		System.out.println("New Student: " + this.toString());
	}
	//compute gpa
	public double gpa() {
		gpa = 0;
		for(int i=0;i<8;i++) {
			if(letters[i].equals("A")) gpa+=4;
			if(letters[i].equals("B")) gpa+=3;
			if(letters[i].equals("C")) gpa+=2;
			if(letters[i].equals("D")) gpa+=1;
			if(schedule[i].weighted) gpa++;
		}
		gpa/=8;
		return gpa;
	}
	//updating students grades
	public void update() {
		for(int i=0;i<8;i++) {
			if(schedule[i].percentage) {
				if(grades[i] >= 90) letters[i] = "A";
				else if(grades[i] >= 80) letters[i] = "B";
				else if(grades[i] >= 70) letters[i] = "C";
				else if(grades[i] >= 60) letters[i] = "D";
				else letters[i] = "F";
			} else {
				if(grades[i] >= 3.4) letters[i] = "A";
				else if(grades[i] >= 2.8) letters[i] = "B";
				else if(grades[i] >= 2.2) letters[i] = "C";
				else if(grades[i] >= 1.6) letters[i] = "D";
				else letters[i] = "F";
			}
		}
		gpa();
	}
	
	//calculating students grade by averaging all the learning targets
	public double classgrade(int classnum) {
		double grade=0;
		Class temp = schedule[classnum];
		for(int i=0;i<temp.targets.size();i++) {
			grade+=temp.targets.get(i).grades[index[classnum]];
		}
		grade/=temp.targets.size();
		grades[classnum] = grade;
		return grade;
	}
	
	//adding an absence
	public void addAbsence(int month, int day, int year, int period, boolean excused) {
		absence.add(new Absent(month, day, year, period, excused));
		System.out.println(fname + " " + lname + " had an absence on " + month + "/" + day + "/"+ year + ".");
	}
}