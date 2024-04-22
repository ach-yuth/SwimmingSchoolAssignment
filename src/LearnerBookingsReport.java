import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class LearnerBookingsReport extends GenerateReports{


    @Override
    public void generateReports(){
    	  String inputMonth = ValidatorClass.takeMonthNumForReport();
    	  
          ArrayList<Bookings> bookingDataList = Bookings.getBookingDataList();
          ArrayList<Lessons> lessonDataList = Lessons.getLessonDataList();
          ArrayList<Learners> learnerDataList = Learners.getLearnersDataList();
         
          System.out.println();

          HashMap<Integer, int[]> learnerData = new HashMap<>();
          
          Set<String> uniqueLearnerCode = new HashSet<>();             
          for (int j = 0; j < bookingDataList.size(); j++) {

              for (int i = 0; i < learnerDataList.size(); i++) {

                  String monthNumber = "";
                  for (Lessons lessonObj : lessonDataList) {

                      if(lessonObj.getLessonNo() == bookingDataList.get(j).getBookedFor()){

                          //Parse lesson date
                          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");                

                          try {
                              LocalDateTime parsedDateTime = LocalDate.parse(lessonObj.getLessonOn(), formatter).atStartOfDay();
                              monthNumber = parsedDateTime.format(DateTimeFormatter.ofPattern("M"));
                          } catch (DateTimeParseException e) {
                              System.out.println("Cannot parse lesson date: " + e.getMessage());
                          }
                          break;
                      }
                  }

                  String uniqueKey = String.valueOf(learnerDataList.get(i).getLearnerID()) + bookingDataList.get(j).getBoookingID();

                  if (!uniqueLearnerCode.contains(uniqueKey)) {
                      uniqueLearnerCode.add(uniqueKey);

                      if (learnerDataList.get(i).getLearnerID() == bookingDataList.get(j).getBookedBy() && monthNumber.equalsIgnoreCase(inputMonth)) {

                          int uniqueCode = learnerDataList.get(i).getLearnerID();
                          int[] counter = learnerData.getOrDefault(uniqueCode, new int[3]);

                          if (bookingDataList.get(j).getStatus().equalsIgnoreCase("Booked") 
                                  || bookingDataList.get(j).getStatus().equalsIgnoreCase("Changed")) {
                              counter[0]++;
                          }
                          if (bookingDataList.get(j).getStatus().equalsIgnoreCase("Attended")) {
                              counter[1]++;
                          }
                          if (bookingDataList.get(j).getStatus().equalsIgnoreCase("Cancelled")) {
                              counter[2]++;
                          }
                          learnerData.put(uniqueCode, counter);
                      }
                  }
              }
          }
          
          if(!learnerData.isEmpty()){
              for (Map.Entry<Integer, int[]> entry : learnerData.entrySet()) {
                  int learnerID = entry.getKey();
                  int[] counter = entry.getValue();

                  //Learner data
                  String learnername = "";
                  int id = 0;
                  for (int i = 0; i < learnerDataList.size(); i++) {
                     if(learnerDataList.get(i).getLearnerID() == learnerID){
                         learnername = learnerDataList.get(i).getName();
                         id = learnerDataList.get(i).getLearnerID();
                         break;
                     }
                  }

                  System.out.println("\nLearner ID : "+ id);
                  System.out.println("Learner Name : "+ learnername+"\n");
                  
                  System.out.println("\nBooked Lessons : "+counter[0]);
                  System.out.println("Attended Lessons : "+counter[1]);
                  System.out.println("Cancelled Lessons : "+counter[2]);
                  
                  System.out.println("\nLesson Details as follows : ");
                   
                  System.out.println("\n================================================================================================================================");
                  System.out.printf("%-13s %-30s %-20s %-25s %-12s %-12s \n",
                          "LessonNo", "Lesson", "Timing", "ClassOn", "Day", "Status");
                  System.out.println("================================================================================================================================");

                  for (int j = 0; j < bookingDataList.size(); j++) {
                      
                      if(bookingDataList.get(j).getBookedBy() == learnerID){

                          //LessonData
                          String lessonname = "";
                          String timing = "";
                          String day = "";
                          String classOn = "";
                          int lessonNo = 0;
                          for (Lessons lessonObj : lessonDataList) {
                              if(lessonObj.getLessonNo() == bookingDataList.get(j).getBookedFor()){
                                  lessonname = lessonObj.getLesson();
                                  lessonNo = lessonObj.getLessonNo();
                                  timing = lessonObj.getTiming();
                                  classOn = lessonObj.getLessonOn();
                                  day = lessonObj.getDay();
                                  break;
                              }
                          }
                          System.out.printf("%-13s %-30s %-20s %-25s %-12s  %-12s \n",
                        		  lessonNo , lessonname, timing, classOn,  day, bookingDataList.get(j).getStatus());
                      }
                  }   
                  System.out.println("================================================================================================================================");

              }
          }else{
               System.out.println("No Record Found");
          }
    }
}
