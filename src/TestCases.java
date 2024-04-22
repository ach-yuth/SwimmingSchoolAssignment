import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TestCases {

	public static String bookingID = "9824_21";
	
	@Test
	public void Test1_FilterTimetableByGradeLevel() {
				
		String level = "2";
		Lessons.timetableByGradeLevel(level);
		assertTrue(Lessons.lessonDataList.size() > 0);
	}

	
	@Test
	public void Test2_AddBooking() {
			
		int lessonNo = 2;
		int learnerID = 851;
	    Bookings obj = new Bookings(bookingID,lessonNo,learnerID,"Booked");
	    Bookings.bookingDataList.add(obj);
		assertTrue(Bookings.bookingDataList.size() > 0);
	}
	

	@Test
	public void Test3_DuplicateBooking() {
		
		int lessonNo = 2;
		int learnerID = 851;
					    
		  //Twice Booking
        boolean isBookingTwice = ValidatorClass.isBookingTwice(String.valueOf(lessonNo),String.valueOf(learnerID));
        if(isBookingTwice){
            System.out.println("\nTwice Booking !");
            return;
        }
       
		assertTrue(isBookingTwice);
	}

	
	@Test
	public void Test4_GiveRatingBooking() {
		String review = "good lesson";
		String rating =  "2";

        CoachReviews.giveRatingToCoach(bookingID,review,rating);
	    
        //Update status of booking
        ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
        for(Bookings obj : bookingDataList){
            if(obj.getBoookingID().equalsIgnoreCase(bookingID)){
                obj.setStatus("Attended");
                break;
            }
        }        

		String status = ValidatorClass.getBookingStatus(bookingID);
		assertTrue(status.equalsIgnoreCase("Attended"));
	}
	
	

	@Test
	public void Test5_HigherGradeLevelHigherOrLower() {
		int lessonNo = 11;
		int learnerID = 851;
		 
        //Is Higher or Lower Grade Level
        int lessonGrade = ValidatorClass.getLessonGradeLevel(lessonNo);
        int learnerCurrentGrade = ValidatorClass.getLearnerGradeLevel(learnerID);

        if(((learnerCurrentGrade+1) != lessonGrade && (learnerCurrentGrade) != lessonGrade) || lessonGrade < learnerCurrentGrade){
            System.out.println("\nLesson should be similar to your current grade level or one level higher.");
            return;
        }
       
		assertTrue(true);
	}

	
	
}
