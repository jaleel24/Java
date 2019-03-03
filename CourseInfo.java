package EECS2030;

public class CourseInfo {
	private String name;
	private String instructor;
	
	public CourseInfo(String name, String instructor) {
		this.name = name;
		this.instructor = instructor;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setinstructor(String instructor) {
		this.instructor = instructor;	
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getinstructor() {
		return this.instructor;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		CourseInfo other = (CourseInfo) obj;
		if (instructor == null) {
			if (other.instructor != null)
				return false;
		} else if (!instructor.equals(other.instructor))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
