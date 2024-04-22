import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CoachReviews {

	

    private String bookignID;
    private String review;
    private int ratings;
    private int reviewGivenTo;
    private int  givenBy;

    public static ArrayList <CoachReviews> coachReviewDataList = new ArrayList<>();

	public CoachReviews(String bookignID, String review, int ratings, int reviewGivenTo, int givenBy) {
		super();
		this.bookignID = bookignID;
		this.review = review;
		this.ratings = ratings;
		this.reviewGivenTo = reviewGivenTo;
		this.givenBy = givenBy;
	}

	public String getBookignID() {
		return bookignID;
	}

	public String getReview() {
		return review;
	}

	public int getCoachReviews() {
		return ratings;
	}

	public int getReviewGivenTo() {
		return reviewGivenTo;
	}

	public int getGivenBy() {
		return givenBy;
	}

	public static ArrayList<CoachReviews> getCoachReviewDataList() {
		return coachReviewDataList;
	}

   	
	 
    //Give Rating to coach
    public static void giveRatingToCoach(String reservationID, String review, String rating){
        ArrayList<Bookings> reservationRecords = Bookings.getBookingDataList();
        ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
        
        int learnerID = 0;
        int coachID = 0;
        for(Bookings obj : reservationRecords){
            if(obj.getBoookingID().equalsIgnoreCase(reservationID)){
                for(Lessons lessonObj : lessonDataList){
                    if(lessonObj.getLessonNo() == obj.getBookedFor()){
                        coachID = lessonObj.getCoachID();
                        break;
                    }
                }
                learnerID = obj.getBookedBy();
                break;
            }
        }
      
        CoachReviews obj = new CoachReviews(reservationID,review,Integer.parseInt(rating),coachID,learnerID);
        CoachReviews.coachReviewDataList.add(obj);
    }
    

    //Given Coach Reviews
    public static void givenCoachReviews(){
        
        Scanner sc = new Scanner(System.in);
      
        ArrayList<CoachReviews> oachReviewDataList = CoachReviews.getCoachReviewDataList();
        ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
        ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
        ArrayList<Coach> coachDataList = Coach.getCoachDataList();
        
        Set<CoachReviews> uniqueCoachReviews = new HashSet<>();

        for(CoachReviews obj : oachReviewDataList){
            String uniqueData = String.valueOf(obj.getBookignID()) + String.valueOf(obj.getGivenBy());
            if(!uniqueCoachReviews.contains(uniqueData)){
                uniqueCoachReviews.add(obj);
            }
        }
        
        if(uniqueCoachReviews.isEmpty()){
            System.out.println("\nNo Record Found");
            return;
        }
        
        //take learner data
        ArrayList<Learners> learnersDataList = Learners.getLearnersDataList();
        
        System.out.print("\nEnter Learner ID to check given ratings to coach : ");
        String learnerID = sc.nextLine();
        
        if(!ValidatorClass.validateInput(learnerID) || learnerID.equalsIgnoreCase("")){
            System.out.println("\nLearner ID is required and must be valid");
            return;
        }
        
        //Get Learner Record
        boolean getLearnerRecord = ValidatorClass.getLearnerRecord(Integer.parseInt(learnerID));
        if(!getLearnerRecord){
            System.out.println("\nLearner ID is not valid");
            return;
        }
        
        System.out.println("\n==================================================================================================================================================================================================");
        System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-15s %-15s %-10s %-20s \n","BookingID","LessonNo","Lesson","Day",
                "ClassGrade","Timing","ClassOn","GivenTo","GivenBy","Rating","Review");
        System.out.println("==================================================================================================================================================================================================");

        for(CoachReviews ratingObj : uniqueCoachReviews){
            if(ratingObj.getGivenBy() == Integer.parseInt(learnerID)){
                for(Bookings obj : bookingDataList){
                    if(ratingObj.getBookignID().equalsIgnoreCase(obj.getBoookingID())){
                        String lesson = "";
                        int classGrade = 0;
                        String timing = "";
                        String day = "";
                        String classOn = "";
                        String coachName = "";
                        String givenBy = "";

                        //get learner data
                        for(Learners learnerObj : learnersDataList){
                            if(learnerObj.getLearnerID() == obj.getBookedBy()){
                            	givenBy = learnerObj.getName();
                                break;
                            }
                        }

                        for(Lessons lessonObj : lessonDataList){
                            //get cach name
                            for(Coach coachObj : coachDataList){
                                if(coachObj.getCoachID() == lessonObj.getCoachID()){
                                	coachName = coachObj.getName();
                                    break;
                                }
                            }
                            //get lesson detail
                            if(lessonObj.getLessonNo() == obj.getBookedFor()){
                                lesson = lessonObj.getLesson();
                                classGrade = lessonObj.getGradeLevel();
                                timing = lessonObj.getTiming();
                                classOn = lessonObj.getLessonOn();
                                day = lessonObj.getDay();
                                break;
                            }

                        }
                         System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-15s %-15s %-10s %-20s \n",obj.getBoookingID(),obj.getBookedFor(),
                                   lesson,day,classGrade,timing,classOn,coachName,givenBy,
                                   ratingObj.getCoachReviews(),ratingObj.getReview());
                    }
                }
            }
        }
        System.out.println("==================================================================================================================================================================================================");

    }
 
   
   
    
    //All Reviews
    public static void allReviews(){
    	
    	Scanner sc = new Scanner(System.in);
    	
    	ArrayList<CoachReviews> oachReviewDataList = CoachReviews.getCoachReviewDataList();
    	ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
    	ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
    	ArrayList<Coach> coachDataList = Coach.getCoachDataList();
    	
    	Set<CoachReviews> uniqueCoachReviews = new HashSet<>();
    	
    	for(CoachReviews obj : oachReviewDataList){
    		String uniqueData = String.valueOf(obj.getBookignID()) + String.valueOf(obj.getGivenBy());
    		if(!uniqueCoachReviews.contains(uniqueData)){
    			uniqueCoachReviews.add(obj);
    		}
    	}
    	
    	if(uniqueCoachReviews.isEmpty()){
    		System.out.println("\nNo Record Found");
    		return;
    	}
    	    	
        System.out.println("\n==================================================================================================================================================================================================");
    	System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-15s %-15s %-10s %-20s \n","BookingID","LessonNo","Lesson","Day",
    			"ClassGrade","Timing","ClassOn","GivenTo","GivenBy","Rating","Review");
        System.out.println("==================================================================================================================================================================================================");
    	
        ArrayList<Learners> learnersDataList = Learners.getLearnersDataList();

        
    	for(CoachReviews ratingObj : uniqueCoachReviews){
			for(Bookings obj : bookingDataList){
				if(ratingObj.getBookignID().equalsIgnoreCase(obj.getBoookingID())){
					String lesson = "";
					int classGrade = 0;
					String timing = "";
					String day = "";
					String classOn = "";
					String coachName = "";
					String givenBy = "";
					
					//get learner data
					for(Learners learnerObj : learnersDataList){
						if(learnerObj.getLearnerID() == obj.getBookedBy()){
							givenBy = learnerObj.getName();
							break;
						}
					}
					
					for(Lessons lessonObj : lessonDataList){
						//get cach name
						for(Coach coachObj : coachDataList){
							if(coachObj.getCoachID() == lessonObj.getCoachID()){
								coachName = coachObj.getName();
								break;
							}
						}
						//get lesson detail
						if(lessonObj.getLessonNo() == obj.getBookedFor()){
							lesson = lessonObj.getLesson();
							classGrade = lessonObj.getGradeLevel();
							timing = lessonObj.getTiming();
							classOn = lessonObj.getLessonOn();
							day = lessonObj.getDay();
							break;
						}
						
					}
					System.out.printf("%-17s %-10s %-20s %-10s %-12s %-10s %-12s %-15s %-15s %-10s %-20s \n",obj.getBoookingID(),obj.getBookedFor(),
							lesson,day,classGrade,timing,classOn,coachName,givenBy,
							ratingObj.getCoachReviews(),ratingObj.getReview());
				}
    		}
    	}
        System.out.println("==================================================================================================================================================================================================");

    }
    
    
    
   
}
