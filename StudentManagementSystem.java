package EECS2030;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import EECS2030.CourseInfo;
import EECS2030.StudentInfo;
import EECS2030.IdAlreadyExistsExceptoin;
import EECS2030.IdNotFoundException;

public class StudentManagementSystem extends java.lang.Object{
	HashMap<Integer, CourseInfo> courses;
	HashMap<String, StudentInfo> students;
	public StudentManagementSystem() {
		courses = new HashMap<>();
		students = new HashMap<>();
				
		
	}
	
	public void addCourse(Integer id, CourseInfo info) throws IdAlreadyExistsExceptoin  {
	//Exception Handling	
	if(courses.containsKey(id)) {
		 throw new IdAlreadyExistsExceptoin("id already exists");
	}
		courses.put(id, info);
	}
	
	public void addStudent(String id, StudentInfo info) throws IdAlreadyExistsExceptoin
	{	//Exception Handling
		if(students.containsKey(id)) {
		throw new IdAlreadyExistsExceptoin("id already exists");
	}
		students.put(id, info);
	}

	public void changeCourse(java.lang.String eid, java.lang.Integer did) throws IdNotFoundException
	{	
		List<Integer> checkid = new ArrayList<Integer>();
		for(Integer n1: courses.keySet()) {
			checkid.add(n1);
		}
		if(!students.containsKey(eid)) {
		throw new IdNotFoundException("ID not found :)");
	}
		else {
			StudentInfo stu1 = students.get(eid);
			if(checkid.contains(did)) {
				stu1.setId(did);
			}
			else {
				throw new IdNotFoundException("ID not found :)");
			}
	}
	}
	public void removeStudent(java.lang.String id) throws IdNotFoundException{
		if(students.containsKey(id)) {
			students.remove(id);
		}
		else {
			throw new IdNotFoundException("ID is not an Existing Student");
		}
	}
	
	public void removeCourse(java.lang.Integer id) throws IdNotFoundException{
		if(courses.containsKey(id)) {
			courses.remove(id);
		}
		else {
			throw new IdNotFoundException("ID is not a Existing Course");
		}
	}
	
	
	public double getAveragePercentage(java.lang.Integer id)throws IdNotFoundException{
		int count=0;
		double sum=0.0;
		double percentage,average;
		
		for(int i=0;i<courses.size();i++) {
			if(!courses.containsKey(id)) {
				throw new IdNotFoundException("ID is not a Existing Course");
			}
			else {
				count++;
				sum +=count;
			}
			
				
		}
		/*if(!courses.containsKey(id)) {
			throw new IdNotFoundException("ID is not a Existing Course");
			}
		while(courses.containsKey(id)) {
			count++;
			sum +=count;
		}
		
	}	*/
		average = sum/count;
		percentage = average *100;
		return percentage;
	}
	
	
	public CourseInfo getCourseInfo(java.lang.String id)throws IdNotFoundException{
		
		if(students.containsKey(id)) {
			StudentInfo si= students.get(id);
			CourseInfo ci= courses.get(si.getId());
			return ci;
			//return courses.get(Integer.parseInt(id));
			
		}
		else {
			throw new IdNotFoundException("'id' is not an existing student id");
		}
		
	}
	
	public java.util.ArrayList<java.lang.String> getStudentNames(java.lang.Integer id){
		
		ArrayList<String> list= new ArrayList<String>();
		
		for( String l:students.keySet()) {
			
			StudentInfo stu= students.get(l);
			if(stu.getId()== id) {
				list.add(stu.getName());
			}
		}
		return list;
	}
	
	public java.lang.String getStudentName(java.lang.String id)throws IdNotFoundException{
		
		if(!students.containsKey(id)) {
			throw new IdNotFoundException("'ID' is not an existing student id");
		}
		StudentInfo stu1= students.get(id);
		return stu1.getName();
	}
	
	
}