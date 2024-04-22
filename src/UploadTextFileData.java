import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UploadTextFileData {


    //uploadLearnerTextFile
    public static void uploadLearnerTextFile(){
    	 try (BufferedReader br = new BufferedReader(new FileReader("TestFileToUpload/learners.txt"))) {
             String line;
             while ((line = br.readLine()) != null) {
                 String[] data = line.split(",");
                 int learnerID = Integer.parseInt(data[0]);
                 String name = data[1];
                 int age = Integer.parseInt(data[2]);
                 int grade = Integer.parseInt(data[3]);
                 String contact = data[4];
                 String gender = data[5];
                 
                 Learners obj = new Learners(learnerID, name,age, grade,contact,gender);
                 Learners.learnersDataList.add(obj);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    
    //uploadLessonsTextFile
    public static void uploadLessonsTextFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("TestFileToUpload/lessons.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int lessonNo = Integer.parseInt(data[0]);
                String lesson = data[1];
                String day = data[2];
                String timing = data[3];
                String lessonDate = data[4];
                int totalSeats = Integer.parseInt(data[5]);
                int gradeLevel = Integer.parseInt(data[6]);
                int coachID = Integer.parseInt(data[7]);

                Lessons lessonObj = new Lessons(lessonNo, lesson, day, timing, lessonDate, totalSeats, gradeLevel, coachID);
                Lessons.lessonDataList.add(lessonObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //uploadCoachTextFile
    public static void uploadCoachTextFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("TestFileToUpload/coach.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                int coachID = Integer.parseInt(data[0]);
                String coachName = data[1];
                String contact = data[2];
                
                Coach coachObj = new Coach(coachID, coachName, contact);
                Coach.coachDataList.add(coachObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    //Load data from text file
    public static void loadDataFromTextFile() {
    	uploadLearnerTextFile();
    	uploadLessonsTextFile();
    	uploadCoachTextFile();
    	
    }
}
