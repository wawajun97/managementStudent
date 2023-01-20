import java.io.*;
import java.util.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Student {
	String name;
	String major;
	String id;
	
	Student(String name, String major, String id){
		this.name = name;
		this.major = major;
		this.id = id;
	}
}

class Subject {
	String Id;
	String A;
	String B;
	String C;
	String D;
	String Ave;
	
	Subject(String Id, String A, String B, String C, String D,String Ave){
		this.Id = Id;
		this.A = A;
		this.B = B;
		this.C = C;
		this.D = D;
		this.Ave = Ave;
	}
}

public class manageStudent {
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		new GUI();
	}

}
