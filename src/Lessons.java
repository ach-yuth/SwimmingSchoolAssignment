import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lessons {


    private int lessonNo;
    private String lesson;
    private String day;
    private String timing;
    private String lessonOn;
    private int totalSeats;
    private int gradeLevel;
    private int coachID;


    public static ArrayList <Lessons> lessonDataList = new ArrayList<>();
    
    public static ArrayList<Lessons> lessonrDataList = Lessons.getLessonDataList();
    public static ArrayList<Coach> coachDataList = Coach.getCoachDataList();


	public Lessons(int lessonNo, String lesson, String day, String timing, String lessonOn, int totalSeats,
			int gradeLevel, int coachID) {
		super();
		this.lessonNo = lessonNo;
		this.lesson = lesson;
		this.day = day;
		this.timing = timing;
		this.lessonOn = lessonOn;
		this.totalSeats = totalSeats;
		this.gradeLevel = gradeLevel;
		this.coachID = coachID;
	}


	public int getLessonNo() {
		return lessonNo;
	}


	public String getLesson() {
		return lesson;
	}


	public String getDay() {
		return day;
	}


	public String getTiming() {
		return timing;
	}


	public String getLessonOn() {
		return lessonOn;
	}


	public int getTotalSeats() {
		return totalSeats;
	}


	public int getGradeLevel() {
		return gradeLevel;
	}


	public int getCoachID() {
		return coachID;
	}


	public static ArrayList<Lessons> getLessonDataList() {
		return lessonDataList;
	}


	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

    
	 
    //timetable
    public static void timetable(){
   
		System.out.println("\n===================================================================================================================================");
        System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n","LessonNo","Lesson","Day","Timing",
                "Grade Level","Total Seats","Class On","Coach");
		System.out.println("===================================================================================================================================");

        
        Set<Lessons> uniqueLessons = new HashSet<>();

        for(Lessons obj : lessonrDataList){
            String uniqueData = String.valueOf(obj.getLessonNo());
            if(!uniqueLessons.contains(uniqueData)){
                uniqueLessons.add(obj);
            }
        }
        for(Lessons obj : uniqueLessons){
            String coachName =  "";
            for(Coach coachObj : coachDataList){                
                if(coachObj.getCoachID() == obj.getCoachID()){
                	coachName = coachObj.getName();
                    break;
                }
                
            }
            System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n",obj.getLessonNo(),obj.getLesson(),obj.getDay(),obj.getTiming(),
                    obj.getGradeLevel(),obj.getTotalSeats(),obj.getLessonOn(),coachName);
        }
		System.out.println("===================================================================================================================================");

    }
    
    

    //timetable by day
    public static void timetableByDay(String weekday){
               
    	if(!weekday.equalsIgnoreCase("")) {
    		System.out.println("\n===================================================================================================================================");
	        System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n","LessonNo","Lesson","Day","Timing",
	                "Grade Level","Total Seats","Class On","Coach");
    		System.out.println("===================================================================================================================================");

	        Set<Lessons> uniqueLessons = new HashSet<>();

	        for(Lessons obj : lessonrDataList){
	            String uniqueData = String.valueOf(obj.getLessonNo());
	            if(!uniqueLessons.contains(uniqueData)){
	                uniqueLessons.add(obj);
	            }
	        }

	        for(Lessons obj : uniqueLessons){
	            if(obj.getDay().equalsIgnoreCase(weekday)){
	            	 String coachName =  "";
	                 for(Coach coachObj : coachDataList){                
	                     if(coachObj.getCoachID() == obj.getCoachID()){
	                     	coachName = coachObj.getName();
	                         break;
	                     }
	                     
	                 }
	                System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n",obj.getLessonNo(),obj.getLesson(),obj.getDay(),obj.getTiming(),
	                        obj.getGradeLevel(),obj.getTotalSeats(),obj.getLessonOn(),coachName);
	            }
	        }
    		System.out.println("===================================================================================================================================");

	        Bookings.newLessonBooking();
    	}
    }
    
    
    //timetable by grade level
    public static void timetableByGradeLevel(String level){

    	if(!level.equalsIgnoreCase("")) {

    		System.out.println("\n===================================================================================================================================");
            System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n","LessonNo","Lesson","Day","Timing",
                    "Grade Level","Total Seats","Class On","Coach");
    		System.out.println("===================================================================================================================================");

            Set<Lessons> uniqueLessons = new HashSet<>();

            for(Lessons obj : lessonrDataList){
	            String uniqueData = String.valueOf(obj.getLessonNo());
	            if(!uniqueLessons.contains(uniqueData)){
	                uniqueLessons.add(obj);
	            }
	        }
            
            for(Lessons obj : uniqueLessons){
                if(String.valueOf(obj.getGradeLevel()).equalsIgnoreCase(level)){
                	String coachName =  "";
                    for(Coach coachObj : coachDataList){                
                        if(coachObj.getCoachID() == obj.getCoachID()){
                        	coachName = coachObj.getName();
                            break;
                        }
                        
                    }
                    System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n",obj.getLessonNo(),obj.getLesson(),obj.getDay(),obj.getTiming(),
                            obj.getGradeLevel(),obj.getTotalSeats(),obj.getLessonOn(),coachName);
                }
            }
    		System.out.println("===================================================================================================================================");

            Bookings.newLessonBooking();
    	}

    }
    
    
    //timetable by coach
    public static void timetableByCoach(String coachIDD){
                
        if(!coachIDD.equalsIgnoreCase("")) {
    		System.out.println("\n===================================================================================================================================");
             System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n","LessonNo","Lesson","Day","Timing",
                     "Grade Level","No Of Seats","Class On","Coach");
     		System.out.println("===================================================================================================================================");

             Set<Lessons> uniqueLessons = new HashSet<>();

             for(Lessons obj : lessonrDataList){
                 String uniqueData = String.valueOf(obj.getLessonNo());
                 if(!uniqueLessons.contains(uniqueData)){
                     uniqueLessons.add(obj);
                 }
             }

             for(Lessons obj : uniqueLessons){
                 if(String.valueOf(obj.getCoachID()).equalsIgnoreCase(coachIDD)){
                 	String coachName =  "";
                     for(Coach coachObj : coachDataList){                
                         if(coachObj.getCoachID() == obj.getCoachID()){
                         	coachName = coachObj.getName();
                             break;
                         }
                         
                     }
                     System.out.printf("%-10s %-30s %-15s %-15s %-15s %-15s %-15s %-30s \n",obj.getLessonNo(),obj.getLesson(),obj.getDay(),obj.getTiming(),
                             obj.getGradeLevel(),obj.getTotalSeats(),obj.getLessonOn(),coachName);
                 }
             }
     		System.out.println("===================================================================================================================================");

             Bookings.newLessonBooking();
        }
    }
    
    
    
    
}
