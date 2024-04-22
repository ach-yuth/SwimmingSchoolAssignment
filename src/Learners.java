import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Learners {

	

    private int learnerID;
    private String name;
    private int age;
    private int currentGradeLevel;
    private String phoneNo;
    private String gender;
    
    public static ArrayList <Learners> learnersDataList = new ArrayList<>();

	public Learners(int learnerID, String name, int age, int currentGradeLevel, String phoneNo, String gender) {
		super();
		this.learnerID = learnerID;
		this.name = name;
		this.age = age;
		this.currentGradeLevel = currentGradeLevel;
		this.phoneNo = phoneNo;
		this.gender = gender;
	}

	public int getLearnerID() {
		return learnerID;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public int getCurrentGradeLevel() {
		return currentGradeLevel;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public static ArrayList<Learners> getLearnersDataList() {
		return learnersDataList;
	}

	public void setCurrentGradeLevel(int currentGradeLevel) {
		this.currentGradeLevel = currentGradeLevel;
	}

	 
    
    //learners list 
    public static void learners(){
        System.out.println("\n===========================================================================================================");
        System.out.printf("%-10s %-25s %-20s %-15s %-15s %-20s \n","LearnerID","Name","PhoneNo","Age",
                "Gender","CurrentGrade");
        System.out.println("===========================================================================================================");

        Set<Learners> uniqueLearners = new HashSet<>();

        for(Learners obj : learnersDataList){
            String uniqueData = String.valueOf(obj.getLearnerID());
            if(!uniqueLearners.contains(uniqueData)){
                uniqueLearners.add(obj);
            }
        }

        for(Learners obj : uniqueLearners){
              System.out.printf("%-10s %-25s %-20s %-15s %-15s %-20s \n",obj.getLearnerID(),obj.getName(),obj.getPhoneNo(),obj.getAge()+" yrs.",
                obj.getGender(),obj.getCurrentGradeLevel());
        }
        System.out.println("\n===========================================================================================================");

    }
    
    
    //Add Learner
    public static void addLearner(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nEnter below fields to add new Learner : \n");

        System.out.print("\nEnter Name : ");
        String name = sc.nextLine();
        
        if(name.equalsIgnoreCase("")){
            do{
                System.out.print("\nEnter Name : ");
                name = sc.nextLine();
            }while(name.equalsIgnoreCase(""));
        }
        

        System.out.print("\nEnter PhoneNo : ");
        String phoneNo = sc.nextLine();
        
        if(phoneNo.equalsIgnoreCase("")){
            do{
                System.out.print("\nEnter PhoneNo : ");
                phoneNo = sc.nextLine();
            }while(phoneNo.equalsIgnoreCase(""));
        }
        
        System.out.print("\nEnter gender Choice : ");
        System.out.println("\nEnter 1 for Male");
        System.out.println("Enter 2 for Female");
        System.out.print("\nEnter your choice : ");
        String gender = sc.nextLine();
          
        if(gender.equalsIgnoreCase("") || (!gender.equalsIgnoreCase("1") && !gender.equalsIgnoreCase("2"))){
            do{
                System.out.print("\nEnter Valid Choice for Gender : ");
                gender = sc.nextLine();
            }while(gender.equalsIgnoreCase("") || (!gender.equalsIgnoreCase("1") && !gender.equalsIgnoreCase("2")));
        }
        
        System.out.print("\nEnter Age (4 to 11) : ");
        String age = sc.nextLine();
         
        if(age.equalsIgnoreCase("") || !ValidatorClass.validateInput(age) || Integer.parseInt(age) < 4 || Integer.parseInt(age) > 11){
            do{
                System.out.print("\nEnter Age (4 to 11) : ");
                age = sc.nextLine();
            }while(age.equalsIgnoreCase("") || !ValidatorClass.validateInput(age) || Integer.parseInt(age) < 4 || Integer.parseInt(age) > 11);
        }
    

        System.out.print("\nEnter Grade Level (1 to 5) : ");
        String grade = sc.nextLine();
        
         
        if(grade.equalsIgnoreCase("") || !ValidatorClass.validateInput(grade) || (Integer.parseInt(grade) < 1 || Integer.parseInt(grade) > 5)){
            do{
                System.out.print("\nEnter Grade Level (1 to 5) : ");
                grade = sc.nextLine();
            }while(grade.equalsIgnoreCase("") || !ValidatorClass.validateInput(grade) || (Integer.parseInt(grade) < 1 || Integer.parseInt(grade) > 5));
        }
    
        Random random = new Random();
        int learnerID = 100 + random.nextInt(900); 
        
        String genderVal = "";
        if(gender.equalsIgnoreCase("1")) {
        	genderVal = "Male";
        }else {
        	genderVal = "Female";
        }
        
        //Learner Added
        Learners obj = new Learners(learnerID,name,Integer.parseInt(age),Integer.parseInt(grade),phoneNo,genderVal);
        Learners.learnersDataList.add(obj);
        System.out.println("\nSuccess : New Learner Added with - "+learnerID);
        
    }

   
    
    
    
}
