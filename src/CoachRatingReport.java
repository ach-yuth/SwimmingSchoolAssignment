import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CoachRatingReport extends GenerateReports {

    @Override
    public void generateReports(){
      
    	String inputMonth = ValidatorClass.takeMonthNumForReport();
        
        ArrayList<CoachReviews> coachReviewsDataList = CoachReviews.getCoachReviewDataList();
        ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
        ArrayList<Coach> coachDataList = Coach.getCoachDataList();
        
         
        HashMap<String, Integer> ratingRecords = new HashMap<>();
        HashMap<String, Integer> totalRecords = new HashMap<>();
        HashMap<String, Double> calculatedRating = new HashMap<>();
       
        System.out.println();
       
        for (CoachReviews obj : coachReviewsDataList){
            for (Lessons lessonObj : lessonDataList){
                
                 //Parse lesson date 
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");                
                
                String monthNumber = "";
                try {
                    LocalDateTime parsedDateTime = LocalDate.parse(lessonObj.getLessonOn(), formatter).atStartOfDay();
                    monthNumber = parsedDateTime.format(DateTimeFormatter.ofPattern("M"));
                } catch (DateTimeParseException e) {
                	System.out.println("Cannot parse lesson date: " + e.getMessage());
                }
                    
                if(lessonObj.getCoachID() == obj.getReviewGivenTo() && monthNumber.equalsIgnoreCase(inputMonth)){
                    int coachID = obj.getReviewGivenTo();
                    int sumrating = obj.getCoachReviews();
                    
                    String coachName = "";
                    for(Coach tObj : coachDataList){
                        if(tObj.getCoachID() == coachID){
                            coachName = tObj.getName();
                            break;
                        }
                    }

                    ratingRecords.put(coachName, ratingRecords.getOrDefault(coachName, 0) + sumrating);
                    totalRecords.put(coachName, totalRecords.getOrDefault(coachName, 0) + 1);
                }
            }
        }

        for (String coachName : ratingRecords.keySet()) {
            int ratingsSum = ratingRecords.get(coachName);
            int total = totalRecords.get(coachName);

            if (total > 0) {
                double calavgRate = (double) ratingsSum / total;
                double rounfRating = Math.round(calavgRate * 10.0) / 10.0; 
                calculatedRating.put(coachName, rounfRating);
            }
        }
        if(!calculatedRating.isEmpty()){
            System.out.println("\n=================================");
            System.out.printf("%-20s %-15s \n", "Coachname", "AvgRating");
            System.out.println("=================================");

            for (String coachName : calculatedRating.keySet()) {
                double averageRating = calculatedRating.get(coachName);
                System.out.printf("%-20s %-15s \n", coachName, averageRating);
            }
            System.out.println("=================================");

        }else{
            System.out.println("No Record Found");
        }
    }
    
}
