package EECS2030;
//package sms;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Tester {


StudentManagementSystem db;
	
	/*
	 * This method is executed before each test method is executed.
	 */
	@Before
    public void setUp() {
		db = new StudentManagementSystem();
    }
	
	private void addStudents() {
		try {
			db.addStudent("e1", new StudentInfo("Heeyeon"	, 10, 2030));
			db.addStudent("e2", new StudentInfo("Jiyoon"	, 50, 3451)); 
			db.addStudent("e3", new StudentInfo("Jaebin"	, 70, 2030));
			db.addStudent("e4", new StudentInfo("Suyeon"	, 70, 3451));
			db.addStudent("e5", new StudentInfo("Yuna"	    , 50, 1030));
			db.addStudent("e6", new StudentInfo("Sunhye"	, 30, 3451));
			db.addStudent("e7", new StudentInfo("Jihye"	    , 70, 1030));
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		}
	}
	
	private void addcourses() {
		try {
			db.addCourse(2030, new CourseInfo("Object Oriented Programming"	, "BM"));
			db.addCourse(3451, new CourseInfo("SignalsSystems"			, "HT"));
			db.addCourse(1030, new CourseInfo("ComputationalThinking"	, "Franck"));
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		} 
	}
	
	
	@Test
	public void test_Constructor() { 
		assertEquals(db.students.size(), 0);
		assertEquals(db.courses.size(), 0);
	}
	
	@Test
	public void test_addStudent_abnormal() {
		addStudents();
		
		try {
			db.addStudent("e3", new StudentInfo("Jiwon", 400, 2030));
			fail();
		} catch (IdAlreadyExistsExceptoin e) {
			// exception expected
		}
	}

	@Test
	public void test_addStudent_normal() {
		try {
			db.addStudent("e1", new StudentInfo("Heeyeon"	, 10, 2030));
			db.addStudent("e2", new StudentInfo("Jiyoon"	, 50, 3451)); 
			db.addStudent("e3", new StudentInfo("Jaebin"	, 70, 2030));
			db.addStudent("e4", new StudentInfo("Suyeon"	, 70, 3451));
			db.addStudent("e5", new StudentInfo("Yuna"	    , 50, 1030));
			db.addStudent("e6", new StudentInfo("Sunhye"	, 60, 3451));
			db.addStudent("e7", new StudentInfo("Jihye"	    , 70, 1030));
			
			
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		} 
		
		
		assertEquals(7, db.students.size());
		assertEquals(new StudentInfo("Heeyeon"	, 10, 2030), db.students.get("e1"));
		assertEquals(new StudentInfo("Jiyoon"	, 50, 3451), db.students.get("e2"));
		assertEquals(new StudentInfo("Jaebin"	, 70, 2030), db.students.get("e3"));
		assertEquals(new StudentInfo("Suyeon"	, 70, 3451), db.students.get("e4"));
		assertEquals(new StudentInfo("Yuna"	, 50, 1030), db.students.get("e5"));
		assertEquals(new StudentInfo("Sunhye"	, 60, 3451), db.students.get("e6"));
		assertEquals(new StudentInfo("Jihye"	, 70, 1030), db.students.get("e7"));
	}
	
	@Test
	public void test_removeStudent_abnormal() {
		addStudents();
		
		try {
			db.removeStudent("e8");
			fail();
		} catch (IdNotFoundException e) {
			// exception expected 
		}
	}
	
	@Test
	public void test_removeStudent_normal() {
		addStudents();
		
		try {
			db.removeStudent("e3");
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(6, db.students.size());
		assertEquals(new StudentInfo("Heeyeon"	, 10, 2030), db.students.get("e1"));
		assertEquals(new StudentInfo("Jiyoon"	, 50, 3451), db.students.get("e2"));
		assertTrue(db.students.get("e3") == null);
		assertEquals(new StudentInfo("Suyeon"	, 70, 3451), db.students.get("e4"));
		assertEquals(new StudentInfo("Yuna"	, 50, 1030), db.students.get("e5"));
		assertEquals(new StudentInfo("Sunhye"	, 30, 3451), db.students.get("e6"));
		assertEquals(new StudentInfo("Jihye"	, 70, 1030), db.students.get("e7"));
		
		try {
			db.removeStudent("e6");
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(5, db.students.size());
		assertEquals(new StudentInfo("Heeyeon"	, 10, 2030), db.students.get("e1"));
		assertEquals(new StudentInfo("Jiyoon"	, 50, 3451), db.students.get("e2"));
		assertTrue(db.students.get("e3") == null);
		assertEquals(new StudentInfo("Suyeon"	, 70, 3451), db.students.get("e4"));
		assertEquals(new StudentInfo("Yuna"	, 50, 1030), db.students.get("e5"));
		assertTrue(db.students.get("e6") == null);
		assertEquals(new StudentInfo("Jihye"	, 70, 1030), db.students.get("e7"));
	}
	
	@Test
	public void test_addCourse_abnormal() {
		addcourses();
		
		try {
			db.addCourse(3451, new CourseInfo("PUBLIC RELATION", "Vancouver"));
			fail();
		} catch (IdAlreadyExistsExceptoin e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_addCourse_normal() {
		try {
			db.addCourse(2030, new CourseInfo("Object Oriented Programming"	, "BM"));
			db.addCourse(3451, new CourseInfo("SignalsSystems"			, "HT"));
			db.addCourse(1030, new CourseInfo("ComputationalThinking"	, "Franck"));
		} catch (IdAlreadyExistsExceptoin e) {
			// exception not expected
			fail();
		} 
		
		assertEquals(3, db.courses.size());
		assertEquals(new CourseInfo("Object Oriented Programming", "BM"), db.courses.get(2030));
		assertEquals(new CourseInfo("SignalsSystems"		, "HT")	, db.courses.get(3451));
		assertEquals(new CourseInfo("ComputationalThinking"	, "Franck")	, db.courses.get(1030));
	}
	
	@Test
	public void test_removeCourse_abnormal() {
		addcourses();
		
		try {
			db.removeCourse(4);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_removeCourse_normal() {
		addcourses();
		
		try {
			db.removeCourse(3451);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
		assertEquals(2, db.courses.size());
		assertEquals(new CourseInfo("Object Oriented Programming", "BM"), db.courses.get(2030));
		assertTrue(db.courses.get(2) == null);
		assertEquals(new CourseInfo("ComputationalThinking"	, "Franck")	, db.courses.get(1030));
		
		try {
			db.removeCourse(1030);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
		assertEquals(1, db.courses.size());
		assertEquals(new CourseInfo("Object Oriented Programming", "BM"), db.courses.get(2030));
		assertTrue(db.courses.get(3451) == null);
		assertTrue(db.courses.get(1030) == null);
	}
	
	@Test
	public void test_changeCourse_abnormal() {
		addcourses();
		addStudents();
		
		try {
			// invalid student id
			db.changeCourse("e8", 3);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		}
		
		try {
			// invalid course id
			db.changeCourse("e2", 4);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		}
	}
	
	@Test
	public void test_changeCourse_normal() {
		addcourses();
		addStudents();
		
		try {
			db.changeCourse("e2", 1030);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(7, db.students.size());
		/* changed student */
		assertEquals(new StudentInfo("Jiyoon"	, 50, 1030), db.students.get("e2"));
		/* other students remain unchanged */
		assertEquals(new StudentInfo("Heeyeon"	, 10, 2030), db.students.get("e1"));
		assertEquals(new StudentInfo("Jaebin"	, 70, 2030), db.students.get("e3"));
		assertEquals(new StudentInfo("Suyeon"	, 70, 3451), db.students.get("e4"));
		assertEquals(new StudentInfo("Yuna"	, 50, 1030), db.students.get("e5"));
		assertEquals(new StudentInfo("Sunhye"	, 30, 3451), db.students.get("e6"));
		assertEquals(new StudentInfo("Jihye"	, 70, 1030), db.students.get("e7"));
		
		
		
		try {
			db.changeCourse("e7", 2030);
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		}
		
		assertEquals(7, db.students.size());
		/* changed student */
		assertEquals(new StudentInfo("Jihye"	, 70, 2030), db.students.get("e7"));
		/* other students remain unchanged */
		assertEquals(new StudentInfo("Heeyeon"	, 10, 2030), db.students.get("e1"));
		assertEquals(new StudentInfo("Jiyoon"	, 50, 1030), db.students.get("e2"));
		assertEquals(new StudentInfo("Jaebin"	, 70, 2030), db.students.get("e3"));
		assertEquals(new StudentInfo("Suyeon"	, 70, 3451), db.students.get("e4"));
		assertEquals(new StudentInfo("Yuna"	, 50, 1030), db.students.get("e5"));
		assertEquals(new StudentInfo("Sunhye"	, 30, 3451), db.students.get("e6"));
		
	}
	
	@Test
	public void test_getStudentName_abnormal() {
		addcourses();
		addStudents();
		
		try {
			String name = db.getStudentName("e8");
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_getStudentName_normal() {
		addcourses();
		addStudents();
		
		try {
			assertEquals("Heeyeon"	, db.getStudentName("e1"));
			assertEquals("Jiyoon"	, db.getStudentName("e2"));
			assertEquals("Jaebin"	, db.getStudentName("e3"));
			assertEquals("Suyeon"	, db.getStudentName("e4"));
			assertEquals("Yuna"		, db.getStudentName("e5"));
			assertEquals("Sunhye"	, db.getStudentName("e6"));
			assertEquals("Jihye"	, db.getStudentName("e7"));
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
	}
	
	@Test
	public void test_getStudentNames() {
		addcourses();
		addStudents();
		
		ArrayList<String> names;
		names = db.getStudentNames(3451);
		assertEquals(3, names.size());
		assertTrue(names.contains("Jiyoon"));
		assertTrue(names.contains("Suyeon"));
		assertTrue(names.contains("Sunhye"));
		names = db.getStudentNames(2030);
		assertEquals(2, names.size());
		assertTrue(names.contains("Jaebin"));
		assertTrue(names.contains("Heeyeon"));
		names = db.getStudentNames(1030);
		assertEquals(2, names.size());
		assertTrue(names.contains("Yuna"));
		assertTrue(names.contains("Jihye"));
		// non-existing course id
		names = db.getStudentNames(4);
		assertEquals(0, names.size());
	}
	
	@Test
	public void test_getCourseInfo_abnormal() {
		addcourses();
		addStudents();
		
		try {
			CourseInfo name = db.getCourseInfo("e8");
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	@Test
	public void test_getCourseInfo_normal() {
		addcourses();
		addStudents();
		
		try {
			assertEquals(new CourseInfo("Object Oriented Programming", "BM"), db.getCourseInfo("e1"));
			assertEquals(new CourseInfo("SignalsSystems"		, "HT")	, db.getCourseInfo("e2"));
			assertEquals(new CourseInfo("Object Oriented Programming", "BM")	, db.getCourseInfo("e3"));
			assertEquals(new CourseInfo("SignalsSystems"		, "HT")	, db.getCourseInfo("e4"));
			assertEquals(new CourseInfo("ComputationalThinking"	, "Franck"), db.getCourseInfo("e5"));
			assertEquals(new CourseInfo("SignalsSystems"		, "HT")	, db.getCourseInfo("e6"));
			assertEquals(new CourseInfo("ComputationalThinking", "Franck"), db.getCourseInfo("e7"));
			
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
	}
	
	
	/*@Test
	public void test_getSortedStudentInfo_normal() {
		addcourses();
		addStudents();
		
		StudentInfo[] sorted = db.getSortedStudentInfo();
		StudentInfo[] expected = {
				new StudentInfo("Suyeon"	, 70, 3451),
				new StudentInfo("Jaebin"	, 70, 2030),
				new StudentInfo("Jihye"	    , 70, 1030),				
				new StudentInfo("Jiyoon"	, 50, 3451),
				new StudentInfo("Yuna"		, 50, 1030),
				new StudentInfo("Sunhye"	, 30, 3451),
				new StudentInfo("Heeyeon"	, 10, 2030)
		};
		assertEquals(7, sorted.length);
		assertArrayEquals(expected, sorted);
		
	}*/
	
	@Test
	public void test_getAveragePercentage_abnormal() {
		addcourses();
		addStudents();
		
		try {
			double avgPercentage = db.getAveragePercentage(4);
			fail();
		} catch (IdNotFoundException e) {
			// exception expected
		} 
	}
	
	
	@Test
	public void test_getAveragePercentage_normal() {
		addcourses();
		addStudents();
		
		try {
			// precision of getAveragePercentage is within a tolerance of +/- 0.1
			assertEquals((30 + 50 + 70) / 3.0, db.getAveragePercentage(3451), 0.1);
			assertEquals((10 + 70) / 2.0, db.getAveragePercentage(2030), 0.1);
			assertEquals((70 + 50) / 2.0, db.getAveragePercentage(1030), 0.1);
			
		} catch (IdNotFoundException e) {
			// exception not expected
			fail();
		} 
	}
	

	

}
