
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Bookings {

	

    private String boookingID;
    private int bookedFor;
    private int bookedBy;
    private String status;
   
    public static int UPDATEBOOKING = 0;
    public static String BOOKINGID = "";
    
    public static ArrayList <Bookings> bookingDataList = new ArrayList<>();
    
    public Bookings(String boookingID, int bookedFor, int bookedBy, String status) {
		super();
		this.boookingID = boookingID;
		this.bookedFor = bookedFor;
		this.bookedBy = bookedBy;
		this.status = status;
	}


	public static ArrayList<Bookings> getBookingDataList() {
		return bookingDataList;
	}


	public String getBoookingID() {
		return boookingID;
	}


	public int getBookedFor() {
		return bookedFor;
	}


	public int getBookedBy() {
		return bookedBy;
	}


	public String getStatus() {
		return status;
	}
	
	public void setBookedFor(int bookedFor) {
		this.bookedFor = bookedFor;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	//Cancel Booking
    public static void cancel(){
        Scanner sc = new Scanner(System.in);

        //Take BookingID
        System.out.print("\nEnter Booking ID to Cancel Class : ");
        String bookingID = sc.nextLine();
        
        if(bookingID.equalsIgnoreCase("")){
            System.out.println("\nBookingID is required and must be valid");
            return;
        }
        
        //Get Booking Record
        boolean getBookingRecord = ValidatorClass.getBookingRecord(bookingID);
        if(!getBookingRecord){
            System.out.println("\nBookingID is invalid");
            return;
        }
        
        //Is Attended cancelled or attended
        boolean cancelledOrAttended = ValidatorClass.cancelledOrAttended(bookingID);
        if(cancelledOrAttended){
            System.out.println("\nBooking is already cancelled or attended");
            return;
        }
        
        
        //Update booking status
        int lessonNo = 0;
        ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
        for(Bookings obj : bookingDataList){
            if(obj.getBoookingID().equalsIgnoreCase(bookingID)){
                obj.setStatus("Cancelled");
                lessonNo = obj.getBookedFor();
                break;
            }
        }
        
        //Update total seats by adding one
        ValidatorClass.updateTotalSeatsByAdding(lessonNo);
        
        displayBookingDetail(bookingID);
        
        System.out.println("\nSuccess : Booking Cancelled");
        return;
    }
    

    //Attend 
    public static void attend(){
        Scanner sc = new Scanner(System.in);

        //Take BookingID
        System.out.print("\nEnter Booking ID to Attend Class : ");
        String bookingID = sc.nextLine();
        
        if(bookingID.equalsIgnoreCase("")){
            System.out.println("\nBookingID is required and must be valid");
            return;
        }
        
        //Get Bookings record
        boolean getBookingRecord = ValidatorClass.getBookingRecord(bookingID);
        if(!getBookingRecord){
            System.out.println("\nBookingID is invalid");
            return;
        }
        
        //Is Attended cancelled or attended
        boolean cancelledOrAttended = ValidatorClass.cancelledOrAttended(bookingID);
        if(cancelledOrAttended){
            System.out.println("\nBooking is already cancelled or attended");
            return;
        }
        
        displayBookingDetail(bookingID);
        
        //Take Review & rating
        System.out.print("\nWrite a review for the coach : ");
        String review = sc.nextLine();
                
        if(review.equalsIgnoreCase("")){
            do{
                System.out.print("\nYou have to write few lines for the coach to attend class : ");
                review = sc.nextLine();
            }while(review.equalsIgnoreCase(""));
        }

        System.out.print("\nEnter Rating (1 to 5) : ");
        System.out.println("\n\n1 for Very dissatisfied");
        System.out.println("2 for Dissatisfied");
        System.out.println("3 for Ok");
        System.out.println("4 for Satisfied");
        System.out.println("5 for Very Satisfied");
        System.out.print("\n\nEnter your rating : ");
        String rating = sc.nextLine();
                
        if(rating.equalsIgnoreCase("") || !ValidatorClass.validateInput(rating)){
            do{
                System.out.print("\nRating must be valid integer value (1 to 5) : ");
                rating = sc.nextLine();
            }while(rating.equalsIgnoreCase("") || !ValidatorClass.validateInput(rating));
        }

        //Give Rating to Coach
        CoachReviews.giveRatingToCoach(bookingID,review,rating);
        
        //Update status of booking
        int lessonNo = 0;
        int learnerID = 0;
        ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
        for(Bookings obj : bookingDataList){
            if(obj.getBoookingID().equalsIgnoreCase(bookingID)){
                lessonNo = obj.getBookedFor();
                learnerID = obj.getBookedBy();
                obj.setStatus("Attended");
                break;
            }
        }        
        
        //Update total seats by adding one
        ValidatorClass.updateTotalSeatsByAdding(lessonNo);
        
        //Update grade level of learner if attended higher level lesson
        int lessonGrade = ValidatorClass.getLessonGradeLevel(lessonNo);
        int learnerCurrentGrade = ValidatorClass.getLearnerGradeLevel(learnerID);
        ArrayList<Learners> learnerDataList = Learners.getLearnersDataList();
        if(lessonGrade > learnerCurrentGrade){
            for(Learners obj : learnerDataList){
                if(obj.getLearnerID() == learnerID){
                    obj.setCurrentGradeLevel(learnerCurrentGrade+1);
                    break;
                }
            }
        }
                
        System.out.println("\nSuccess : Your rating has been submitted for coach.");
    }
    
    
    
    //Update Booking
    public static void updateBooking(){
        Scanner sc = new Scanner(System.in);

        //Take BookingID
        System.out.print("\nEnter Booking ID to Change Class : ");
        String bookingID = sc.nextLine();
        
        if(bookingID.equalsIgnoreCase("")){
            System.out.println("\nBookingID is required and must be valid");
            return;
        }
        
        //Get Booking Record
        boolean getBookingRecord = ValidatorClass.getBookingRecord(bookingID);
        if(!getBookingRecord){
            System.out.println("\nBookingID is invalid");
            return;
        }
        
        //Is Attended cancelled or attended
        boolean isAlreadyCancelledOrAttended = ValidatorClass.cancelledOrAttended(bookingID);
        if(isAlreadyCancelledOrAttended){
            System.out.println("\nBooking is already cancelled or attended");
            return;
        }
        
        AssignmentMainClass.changeBookingFilterMenu(bookingID);
        return;
    }
     
    

    //Book New Lesson For Learners
    public static void newLessonBooking(){
        
        //Take Lesson no
        if(UPDATEBOOKING == 0){
            System.out.print("\nEnter Lesson No to Book Class : ");
        }else{
            System.out.print("\nEnter Lesson No to Change Class : ");        
        }
        
        Scanner sc = new Scanner(System.in);
        String lessonNo = sc.nextLine();
        
        if(!ValidatorClass.validateInput(lessonNo) || lessonNo.equalsIgnoreCase("")){
            System.out.println("\nLesson No is required field and must be valid digit");
            return;
        }
        
        //Get Lesson record
        boolean getLessonRecord = ValidatorClass.getLessonRecord(lessonNo);
        if(!getLessonRecord){
            System.out.println("\nInvalid Lesson No");
            return;
        }         
        if(UPDATEBOOKING == 0){
            //take learner data
            ArrayList<Learners> learnerDataList = Learners.getLearnersDataList();
    
            System.out.print("\nEnter Learner ID to Book Class : ");
            String learnerID = sc.nextLine();

            if(!ValidatorClass.validateInput(learnerID) || learnerID.equalsIgnoreCase("")){
                System.out.println("\nLearner ID is required field and must be valid digit");
                return;
            }

            //Get Learner Record
            boolean getLearnerRecord = ValidatorClass.getLearnerRecord(Integer.parseInt(learnerID));
            if(!getLearnerRecord){
                System.out.println("\nInvalid Learner ID");
                return;
            }
            
             //Twice Booking
            boolean isBookingTwice = ValidatorClass.isBookingTwice(lessonNo,learnerID);
            if(isBookingTwice){
                System.out.println("\nTwice Booking !");
                return;
            }
            
             //Get total seats left for lesson
            int remaining = ValidatorClass.getTotalSeatsLeft(lessonNo);
            if(remaining < 1){
                System.out.println("\nTotal Seats for selected lessons has been booked.");
                return;
            }
            
            //Is Higher or Lower Grade Level
            int lessonGrade = ValidatorClass.getLessonGradeLevel(Integer.parseInt(lessonNo));
            int learnerCurrentGrade = ValidatorClass.getLearnerGradeLevel(Integer.parseInt(learnerID));

            if(((learnerCurrentGrade+1) != lessonGrade && (learnerCurrentGrade) != lessonGrade) || lessonGrade < learnerCurrentGrade){
                System.out.println("\nLesson should be similar to your current grade level or one level higher.");
                return;
            }
            
            //Add Booking
            Bookings.addBooking(lessonNo, learnerID);
            System.out.println("\nSuccess : Lesson Booked!");
        }else{
            String bookingID = BOOKINGID;
                        
            //get learnerID
            ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
             
            String learnerID = "";
            for(Bookings obj : bookingDataList){
                if(obj.getBoookingID().equalsIgnoreCase(bookingID)){
                    learnerID = String.valueOf(obj.getBookedBy());
                    break;
                }
            }
            
            //Is Attended cancelled or attended
            boolean cancelledOrAttended = ValidatorClass.cancelledOrAttended(bookingID);
            if(cancelledOrAttended){
                System.out.println("\nBooking is already cancelled or attended");
                return;
            }
        
        
            //Twice Booking
            boolean isBookingTwice = ValidatorClass.isBookingTwice(lessonNo,learnerID);
            if(isBookingTwice){
                System.out.println("\nTwice Booking !");
                return;
            }
            
             //Get total seats left for lesson
            int leftSeats = ValidatorClass.getTotalSeatsLeft(lessonNo);
            if(leftSeats < 1){
                System.out.println("\nTotal Seats for selected lessons has been booked.");
                return;
            }
            
            
            //Is Higher or Lower Grade Level
            int lessonGrade = ValidatorClass.getLessonGradeLevel(Integer.parseInt(lessonNo));
            int learnerCurrentGrade = ValidatorClass.getLearnerGradeLevel(Integer.parseInt(learnerID));

            if(((learnerCurrentGrade+1) != lessonGrade && (learnerCurrentGrade) != lessonGrade) || lessonGrade < learnerCurrentGrade){
                System.out.println("\nLesson should be similar to your current grade level or one level higher.");
                return;
            }
                                    
            //Update  Booking
            updateStatus(lessonNo, bookingID);
            System.out.println("\nSuccess : Booked Lesson Updated by you");
            Bookings.UPDATEBOOKING = 0;
            Bookings.BOOKINGID = "";
            return;
        }
    }

    
    
    
    //Learner Bookings
    public static void learnerBookings(){
        Scanner sc = new Scanner(System.in);
  
        
        ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
        ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
        ArrayList<Coach> coachDataList = Coach.getCoachDataList();
       
        Set<Bookings> uniqueBookings = new HashSet<>();

        for(Bookings obj : bookingDataList){
            String uniqueData = String.valueOf(obj.getBoookingID());
            if(!uniqueBookings.contains(uniqueData)){
            	uniqueBookings.add(obj);
            }
        }
        if(uniqueBookings.isEmpty()){
            System.out.println("\nNo Record Found");
            return;
        }
        
        //take learner data
        ArrayList<Learners> learnerDataList = Learners.getLearnersDataList();
        
        System.out.print("\nEnter Learner ID to check booked lessons : ");
        String learnerID = sc.nextLine();
        
        if(!ValidatorClass.validateInput(learnerID) || learnerID.equalsIgnoreCase("")){
            System.out.println("\nLearner ID is required field and must be valid digit");
            return;
        }
        
        //Get Learner record
        boolean getLearnerRecord = ValidatorClass.getLearnerRecord(Integer.parseInt(learnerID));
        if(!getLearnerRecord){
            System.out.println("\nInvalid Learner ID");
            return;
        }
        
         
        System.out.println("\n=========================================================================================================================================================");
        System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-12s %-12s %-20s %-10s \n","BookingID","LessonNo","Lesson","Day",
                "ClassGrade","Timing","ClassOn","Coach","BookdBy","CurrentLearnerGrade","Status");
        System.out.println("=========================================================================================================================================================");

        for(Bookings obj : uniqueBookings){
            if(obj.getBookedBy() == Integer.parseInt(learnerID)){
                String lesson = "";
                int lessonGrade = 0;
                String timing = "";
                String day = "";
                String classOn = "";
                String coachName = "";
                String bookedBy = "";
                int learnerGradeLevel = 0;

                //get learner data 
                for(Learners learnerObj : learnerDataList){
                    if(learnerObj.getLearnerID() == obj.getBookedBy()){
                    	bookedBy = learnerObj.getName();
                    	learnerGradeLevel = learnerObj.getCurrentGradeLevel();
                        break;
                    }
                }

                for(Lessons lessonObj : lessonDataList){
                    //get coach name
                    for(Coach coachObj : coachDataList){
                        if(coachObj.getCoachID() == lessonObj.getCoachID()){
                        	coachName = coachObj.getName();
                            break;
                        }
                    }
                    //get lesson detail
                    if(lessonObj.getLessonNo() == obj.getBookedFor()){
                        lesson = lessonObj.getLesson();
                        lessonGrade = lessonObj.getGradeLevel();
                        timing = lessonObj.getTiming();
                        classOn = lessonObj.getLessonOn();
                        day = lessonObj.getDay();
                        break;
                    }

                }
                System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-12s %-12s %-20s %-10s\n",obj.getBoookingID(),obj.getBookedFor(),
                           lesson,day,lessonGrade,timing,classOn,coachName,bookedBy,learnerGradeLevel,obj.getStatus());
            }

        }
        System.out.println("=========================================================================================================================================================");

        AssignmentMainClass.updateBookingsMenu();
    }
    
    
    

    //all Bookings
    public static void allBookings(){
        ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
        ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
        ArrayList<Learners> learnerDataList = Learners.getLearnersDataList();
        ArrayList<Coach> coachDataList = Coach.getCoachDataList();
        
        Set<Bookings> uniqueBookings = new HashSet<>();

        for(Bookings obj : bookingDataList){
            String uniqueData = String.valueOf(obj.getBoookingID());
            if(!uniqueBookings.contains(uniqueData)){
            	uniqueBookings.add(obj);
            }
        }
       
        
        if(uniqueBookings.isEmpty()){
            System.out.println("\nNo Record Found");
            return;
        }
        
        System.out.println("\n=========================================================================================================================================================");
        System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-12s %-12s %-20s %-10s \n","BookingID","LessonNo","Lesson","Day",
                "ClassGrade","Timing","ClassOn","Coach","BookdBy","CurrentLearnerGrade","Status");
        System.out.println("=========================================================================================================================================================");

        
        for(Bookings obj : uniqueBookings){
            String lesson = "";
            int lessonGrade = 0;
            String timing = "";
            String day = "";
            String classOn = "";
            String coachName = "";
            String bookedBy = "";
            int currentGrade = 0;
            
            //get Learner data
            for(Learners learnerObj : learnerDataList){
                if(learnerObj.getLearnerID() == obj.getBookedBy()){
                	bookedBy = learnerObj.getName();
                    currentGrade = learnerObj.getCurrentGradeLevel();
                    break;
                }
            }
         
            for(Lessons lessonObj : lessonDataList){
                //get coach name
                for(Coach coachObj : coachDataList){
                    if(coachObj.getCoachID() == lessonObj.getCoachID()){
                        coachName = coachObj.getName();
                        break;
                    }
                }
                //get lesson detail
                if(lessonObj.getLessonNo() == obj.getBookedFor()){
                    lesson = lessonObj.getLesson();
                    lessonGrade = lessonObj.getGradeLevel();
                    timing = lessonObj.getTiming();
                    classOn = lessonObj.getLessonOn();
                    day = lessonObj.getDay();
                    break;
                }
                    
            }
            System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-12s %-12s %-20s %-10s\n",obj.getBoookingID(),obj.getBookedFor(),
                    lesson,day,lessonGrade,timing,classOn,coachName,bookedBy,currentGrade,obj.getStatus());
        }
        System.out.println("=========================================================================================================================================================");

    }
    
    
    
    //Add Booking
    public static void addBooking(String lessonNo, String learnerID){
        String bookingID = ValidatorClass.generateRandomBookingID(lessonNo,learnerID);
    
        Bookings obj = new Bookings(bookingID,Integer.parseInt(lessonNo),Integer.parseInt(learnerID),"Booked");
        Bookings.bookingDataList.add(obj);
         
        //Update total seats by decreasing seat by 1
        ValidatorClass.updateTotalSeatsBySubtracting(Integer.parseInt(lessonNo));
    }
    
    
    
    //Update Booking
    public static void updateStatus(String newLessonNo, String bookingID){        
        String oldLessonNo = "";
        for (Bookings bookingObj : bookingDataList) {
            if(bookingObj.getBoookingID().equalsIgnoreCase(bookingID)){
                oldLessonNo = String.valueOf(bookingObj.getBookedFor());
                bookingObj.setStatus("Changed");
                bookingObj.setBookedFor(Integer.parseInt(newLessonNo));
                break;
            }
        }
        
        //Update total seats by adding seat by 1
        ValidatorClass.updateTotalSeatsByAdding(Integer.parseInt(oldLessonNo));
        
        //Update total seats by decreasing seat by 1
        ValidatorClass.updateTotalSeatsBySubtracting(Integer.parseInt(newLessonNo));
    }
 
    
    

    
    //Display Booking record
    public static void displayBookingDetail(String bookingID){
        
        ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
        ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
        ArrayList<Coach> coachDataList = Coach.getCoachDataList();
        ArrayList<Learners> learnerDataList = Learners.getLearnersDataList();
        
        
        System.out.println("\n================================================================================================================================================================================");
        System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-12s %-12s %-12s %-10s\n","BookingID","LessonNo","Lesson","Day",
                "ClassGrade","Timing","ClassOn","Coach","BookdBy","CurrentLearnerGrade","Status");
        System.out.println("================================================================================================================================================================================");

        Set<Bookings> uniqueBookings = new HashSet<>();

        for(Bookings obj : bookingDataList){
            String uniqueData = String.valueOf(obj.getBoookingID());
            if(!uniqueBookings.contains(uniqueData)){
                uniqueBookings.add(obj);
            }
        }
        
        for(Bookings obj : uniqueBookings){
            if(obj.getBoookingID().equalsIgnoreCase(bookingID)){
                String lesson = "";
                int lessonGrade = 0;
                String timing = "";
                String day = "";
                String classOn = "";
                String coachName = "";
                String bookedBy = "";
                int currentGrade = 0;

                //get user name
                for(Learners learnerObj : learnerDataList){
                	if(learnerObj.getLearnerID() == obj.getBookedBy()){
                    	bookedBy = learnerObj.getName();
                        currentGrade = learnerObj.getCurrentGradeLevel();
                        break;
                    }
                }

                for(Lessons lessonObj : lessonDataList){
                    //get coach name
                	for(Coach coachObj : coachDataList){
                        if(coachObj.getCoachID() == lessonObj.getCoachID()){
                            coachName = coachObj.getName();
                            break;
                        }
                    }
                    //get lesson detail
                	if(lessonObj.getLessonNo() == obj.getBookedFor()){
                        lesson = lessonObj.getLesson();
                        lessonGrade = lessonObj.getGradeLevel();
                        timing = lessonObj.getTiming();
                        classOn = lessonObj.getLessonOn();
                        day = lessonObj.getDay();
                        break;
                    }

                }
                   System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-12s %-12s %-12s %-10s \n",obj.getBoookingID(),obj.getBookedFor(),
                           lesson,day,lessonGrade,timing,classOn,coachName,bookedBy,currentGrade,obj.getStatus());
            }

        }
        System.out.println("================================================================================================================================================================================");

    }
    
    
    
    
}
