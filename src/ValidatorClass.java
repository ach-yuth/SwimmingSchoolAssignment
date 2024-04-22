import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class ValidatorClass {

    public static ArrayList<Bookings> bookingsDataList = Bookings.getBookingDataList();
    public static ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
    public static ArrayList<Coach> coachDataList = Coach.getCoachDataList();
    public static ArrayList<Learners> learnerDataList = Learners.getLearnersDataList();
    


	//Validate Input
    public static boolean validateInput(String str)
   {
       if (str == null || str.isEmpty()) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
   }
   
   
    //--------------------------------  Bookings Validator Methods -----------------------------------------------//

	
    //get booking record
    public static boolean getBookingRecord(String bookingID){
        boolean isFound = false;
        for (Bookings bookingObj : bookingsDataList) {
            if(bookingObj.getBoookingID().equalsIgnoreCase(bookingID)){
                isFound = true;
                break;
            }
        }
        return isFound;
    }
     
    //get booking status
    public static String getBookingStatus(String bookingID){
    	String status = "";
    	for (Bookings bookingObj : bookingsDataList) {
    		if(bookingObj.getBoookingID().equalsIgnoreCase(bookingID)){
    			status = bookingObj.getStatus();
    			break;
    		}
    	}
    	return status;
    }
    
    //Is Booking Twice 
    public static boolean isBookingTwice(String lessonNo, String learnerID){
        boolean isFound = false;
        for (Bookings bookingObj : bookingsDataList) {
            if(String.valueOf(bookingObj.getBookedFor()).equalsIgnoreCase(lessonNo) && 
                    String.valueOf(bookingObj.getBookedBy()).equalsIgnoreCase(learnerID) &&
                    (bookingObj.getStatus().equalsIgnoreCase("Booked") || bookingObj.getStatus().equalsIgnoreCase("Changed"))){
                isFound = true;
                break;
            }
        }
        return isFound;
    }
         
    //Already attended or cancelled booking
    public static boolean cancelledOrAttended(String bookingID){
        boolean isFound = false;
        for (Bookings bookingObj : bookingsDataList) {
            if(bookingObj.getBoookingID().equalsIgnoreCase(bookingID) && (bookingObj.getStatus().equalsIgnoreCase("Cancelled")
                    || bookingObj.getStatus().equalsIgnoreCase("Attended"))){
                isFound = true;
                break;
            }
        }
        return isFound;
    }
   
    //generate Random Booking ID
    public static String generateRandomBookingID(String lessonNo, String learnerID){
        Random random = new Random();
        int randomNo = random.nextInt(100);
        return lessonNo+learnerID+"_"+randomNo;
    }
    
    
    //--------------------------------  Coach Validator Methods -----------------------------------------------//
    
    //Get coach record
    public static boolean getCoachRecord(int coachID){
        boolean isFound = false;
        for (Coach coachObj : coachDataList) {
            if(coachObj.getCoachID() == coachID){
                isFound = true;
                break;
            }
        }
        return isFound;
    }
    
    
    //Take Coach ID
    public static void takeCoachID() {
    	List<Integer> coachIds = new ArrayList<>();

        System.out.println();
        System.out.println("\nSelect any of the coach from below of the coach list : \n");
        for (Coach coachObj : coachDataList) {
           System.out.println("("+coachObj.getCoachID()+") for '"+coachObj.getName()+"' having Contact : "+coachObj.getContact());
            if (!coachIds.contains(coachObj.getCoachID())) {
                coachIds.add(coachObj.getCoachID());
            }
        }
        
        System.out.print("\nEnter Coach ID to Filter Timetable : ");
        Scanner scannerObj = new Scanner(System.in);
        String coachIDD = scannerObj.nextLine();
        
        if(!ValidatorClass.validateInput(coachIDD) || coachIDD.equalsIgnoreCase("")){
            System.out.println("\nCoach ID is required field ");
            return;
        }
        
        //Get Coach record
        boolean getCoachRecord = ValidatorClass.getCoachRecord(Integer.parseInt(coachIDD));
        if(!getCoachRecord){
            System.out.println("\nInvalid Coach ID");
            return;
        }
        
        Lessons.timetableByCoach(coachIDD);
    }
    
    
    //--------------------------------  Learner Validator Methods -----------------------------------------------//


    //Get Learner Record
    public static boolean getLearnerRecord(int learnerID){
        boolean isFound = false;
        for (Learners learnerObj : learnerDataList) {
            if(learnerObj.getLearnerID() == learnerID){
                isFound = true;
                break;
            }
        }
        return isFound;
    }
    
    
     
    //Get learner grade level
    public static int getLearnerGradeLevel(int learnerID){
        int grade = 0;
         for (Learners obj : learnerDataList) {
            if(obj.getLearnerID() == learnerID){
               grade = obj.getCurrentGradeLevel();
                break;
            }
        }
        return grade;
    }
    
    
    
    //--------------------------------  Lessons Validator Methods -----------------------------------------------//


    //Get Lesson Record
    public static boolean getLessonRecord(String lessonNo){
        boolean isFound = false;
        for (Lessons lessonObj : lessonDataList) {
            if(String.valueOf(lessonObj.getLessonNo()).equalsIgnoreCase(lessonNo)){
                isFound = true;
                break;
            }
        }
        return isFound;
    }
    
    
     
    //Get total seats left
    public static int getTotalSeatsLeft(String lessonNo){
        int totalLeft = 0;
        for (Lessons lessonObj : lessonDataList) {
            if(String.valueOf(lessonObj.getLessonNo()).equalsIgnoreCase(lessonNo)){
                totalLeft = lessonObj.getTotalSeats();
                break;
            }
        }
        return totalLeft;
    }
    
    
    //Get class grade
    public static int getLessonGradeLevel(int lessonNo){
        int grade = 0;
         for (Lessons lessonObj : lessonDataList) {
            if(lessonObj.getLessonNo() == lessonNo){
               grade = lessonObj.getGradeLevel();
                break;
            }
        }
        return grade;
    }
    

    
    //Update Seats By Increasing total seats by 1
    public static void updateTotalSeatsByAdding(int lessonNo){
        for (Lessons lessonObj : lessonDataList) {
            if(lessonObj.getLessonNo() == lessonNo){
               int seats = lessonObj.getTotalSeats();
               lessonObj.setTotalSeats(seats+1);
                break;
            }
        }
    }
    
    //Update Seats By decreasing total seats by 1
    public static void updateTotalSeatsBySubtracting(int lessonNo){
        for (Lessons lessonObj : lessonDataList) {
            if(lessonObj.getLessonNo() == lessonNo){
               int seats = lessonObj.getTotalSeats();
               lessonObj.setTotalSeats(seats-1);
                break;
            }
        }
    }
    
    
    //take Grade Level
    public static void takeGradeLevel() {
    	System.out.print("\nEnter Grade Level to Filter Timetable : ");
        Scanner scannerObj = new Scanner(System.in);
        String level = scannerObj.nextLine();
        
        if(!ValidatorClass.validateInput(level) || level.equalsIgnoreCase("") || Integer.parseInt(level) < 0 || Integer.parseInt(level) > 5){
            System.out.println("\nGrade Level is required field and you have to enter valid digit for it ");
            return;
        }
 
        Lessons.timetableByGradeLevel(level);

    }
    
    
    //Take WeekDay
    public static void takeWeekday() {
    	 System.out.print("\nEnter WeekDay to Filter Timetable : ");
         Scanner scannerObj = new Scanner(System.in);
         String weekday = scannerObj.nextLine();
         
         if (weekday.equalsIgnoreCase("") || 
             !(weekday.equalsIgnoreCase("Monday") || weekday.equalsIgnoreCase("mon") ||
               weekday.equalsIgnoreCase("Wednesday") || weekday.equalsIgnoreCase("wed") ||
               weekday.equalsIgnoreCase("Friday") || weekday.equalsIgnoreCase("fri") ||
               weekday.equalsIgnoreCase("Saturday") || weekday.equalsIgnoreCase("sat"))
            ){
             System.out.println("\nDay is required field. Please enter -  (mon, wed, fri, sat)");
             return;
         }
         
         if(weekday.equalsIgnoreCase("mon")){
             weekday = "monday";
         }else if(weekday.equalsIgnoreCase("wed")){
             weekday = "wednesday";
         }else if(weekday.equalsIgnoreCase("fri")){
             weekday = "friday";
         }else if(weekday.equalsIgnoreCase("sat")){
             weekday = "saturday";
         }
         
         Lessons.timetableByDay(weekday);
         
    }
    
    
    
    //Take Month Num for report
    public static String takeMonthNumForReport() {
	  Scanner  sc = new Scanner(System.in);
      System.out.print("\nEnter Month Number : ");
      String inputMonth = sc.nextLine();
      
      if(inputMonth.equalsIgnoreCase("") || !ValidatorClass.validateInput(inputMonth) || (Integer.parseInt(inputMonth) < 1 || 
              Integer.parseInt(inputMonth) > 12)){
          do{
              System.out.print("\nEnter Valid Month Number (1 to 12) : ");
              inputMonth = sc.nextLine();
          }while(inputMonth.equalsIgnoreCase("") || !ValidatorClass.validateInput(inputMonth) || (Integer.parseInt(inputMonth) < 1
                  || Integer.parseInt(inputMonth) > 12));
      }
      return inputMonth;
    }
    
    
    //take Admin password
    public static boolean takeAdminPassword() {
    	 boolean isAdmin = false;
    	 
    	 Scanner  sc = new Scanner(System.in);
         System.out.print("\nEnter Password to Use System as Admin : ");
         String password = sc.nextLine();
         
         if(password.equalsIgnoreCase("")){
             do{
                 System.out.print("\nEnter Password to Use System as Admin : ");
                 password = sc.nextLine();
             }while(password.equalsIgnoreCase(""));
         }
         
         if(password.equalsIgnoreCase("admin@123#")) {
        	 isAdmin = true;
         }
         return isAdmin;
    }
}
