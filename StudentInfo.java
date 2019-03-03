
package EECS2030;
import java.lang.String;

public class StudentInfo extends java.lang.Object implements java.lang.Comparable<StudentInfo>{
	private String name;
	private double percentage;
	private int id;
	
	public StudentInfo(String name, double percentage, int id) {
		this.name=name;
		this.percentage=percentage;
		this.id= id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setpercentage(double percentage) {
		this.percentage=percentage;
	}	
	
	public void setId(int id) {
		this.id=id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public double getpercentage() {
		return this.percentage;
	}
	
	public int getId() {
		return this.id;
	}
		
	@Override
	public int compareTo(StudentInfo other) {
		// TODO Auto-generated method stub
		return (int) (this.percentage - other.percentage);
	}
	
	@Override
	public boolean equals(Object si) {
		if(this== si) {
			return true;
		}
		if(si== null) {
			return false;
		}
		if(this.getClass() != si.getClass()) {
			return false;
		}
		
		StudentInfo other = (StudentInfo) si;
		if(this.id!= other.id) {
			return false;	
		}
		if(this.percentage!=other.percentage) {
			return false;
		}
		if(this.name== null) {
			if(other.name!= null) {
				return false;
			}
		}
		else if(!this.name.equals(other.name)) {
			return false;
		}
		return true;
	}

}
