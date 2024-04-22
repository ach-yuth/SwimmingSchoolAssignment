import java.util.Scanner;

public class AssignmentMainClass {

	
	public static void main(String args[]) {
		
		//Load Text file 
		UploadTextFileData.loadDataFromTextFile();
		
		 int selectedOption;
	        do {
	            selectedOption = showMainMenuOptions();
	            switch (selectedOption) {
	                case 1 -> {
	                			boolean isVerified = ValidatorClass.takeAdminPassword();
		                			if(isVerified) {
		                				adminDashboard();
		                			}else {
		                				System.out.println("\nErr - Invalid Password");
		                			}
	                			}
	                case 2 -> learnerDashboard();
	                case 3 -> System.exit(0);
	                default -> System.out.println("\nPlease enter a valid choice (1-3)");
	            }
	        } while (selectedOption != 3);
	        
	}
	

    //Admin Panel Options
    public static int adminPanelOptions(){
        Scanner scannerObj = new Scanner(System.in);
        
        System.out.println("\n\n(Admin Menu) ");
        System.out.println("Select Option from below menu : ");
        System.out.println("1. Timetable");
        System.out.println("2. All Bookings");
        System.out.println("3. Given Reviews By Learners");
        System.out.println("4. Add New Learner");
        System.out.println("5. All Learners");
        System.out.println("6. Monthly Learner's Booking Report");
        System.out.println("7. Monthly Coach's Rating Report");
        System.out.println("8. Return to Previous Menu");
        System.out.print("\nEnter Option : ");
        String inputOption = scannerObj.nextLine();
        while (inputOption.equals("") || !ValidatorClass.validateInput(inputOption))
        {
            System.out.print("\nEnter Valid Option ");
            inputOption = scannerObj.nextLine();
        }
        return Integer.parseInt(inputOption);
    }
    
    
    //Admin Menu
    public static void adminDashboard(){     
        
        int selectedOption;
        do {
            selectedOption = adminPanelOptions();
            switch (selectedOption) {
                case 1 -> Lessons.timetable();
                case 2 -> Bookings.allBookings();
                case 3 -> CoachReviews.allReviews();
                case 4 -> Learners.addLearner();
                case 5 -> Learners.learners();
                case 6 ->{
	                    GenerateReports report = ReportFactoryClass.callreport(selectedOption);
	                    report.generateReports();
                   }
	            case 7 -> {
	            		GenerateReports report = ReportFactoryClass.callreport(selectedOption);
	                    report.generateReports();
                    }
                case 8 -> {
                            return;
                        }
                default -> System.out.println("\nPlease enter a valid choice (1-8)");
            }
        } while (selectedOption != 8);
    }
    
    
    
    //Learner Panel Option
    public static int learnerPanelOption(){
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("\n\n(Learner Menu) ");
        System.out.println("Select Option from below menu : ");
        System.out.println("1. Timetable By Day");
        System.out.println("2. Timetable By Grade Level");
        System.out.println("3. Timetable By Coach");
        System.out.println("4. Booked Lessons");
        System.out.println("5. Given Ratings");
        System.out.println("6. Return to Previous Menu");
        System.out.print("\nEnter Option : ");
        String inputOption = scannerObj.nextLine();
        while (inputOption.equals("") || !ValidatorClass.validateInput(inputOption))
        {
            System.out.print("\nEnter Valid Option ");
            inputOption = scannerObj.nextLine();
        }
        return Integer.parseInt(inputOption);
    }
 
   
   
    //Learner Menu
    public static void learnerDashboard(){     
        int selectedOption;
        do {
            selectedOption = learnerPanelOption();
            switch (selectedOption) {
                case 1 -> ValidatorClass.takeWeekday();
                case 2 -> ValidatorClass.takeGradeLevel();
                case 3 -> ValidatorClass.takeCoachID();
                case 4 -> Bookings.learnerBookings();
                case 5 -> CoachReviews.givenCoachReviews();
                case 6 -> {
                            return;
                        }
                default -> System.out.println("\nPlease enter a valid choice (1-6)");
            }
        } while (selectedOption != 6);
    }
 
    
    //Show Main Menu
    public static int showMainMenuOptions(){     
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("\n\nSelect Option from below menu : ");
        System.out.println("1. Use As Admin");
        System.out.println("2. Use As Learner");
        System.out.println("3. Exit");
        System.out.print("\nEnter Option : ");

        String inputOption = scannerObj.nextLine();
        while (inputOption.equals("") || !ValidatorClass.validateInput(inputOption))
        {
            System.out.print("\nEnter Valid Option ");
            inputOption = scannerObj.nextLine();
        }
        return Integer.parseInt(inputOption);
    }
 
    
    //Update Bookings Menu
    public static void updateBookingsMenu(){
    	        
        int selectedOption;
        do {
            selectedOption = updateBookingsMenuOption();
            
            if(selectedOption != 0) {
            	 switch (selectedOption) {
	            	 case 1 -> {
		          				Bookings.cancel();
		          				return;
	            	 			}
	                 case 2 -> {
	                 			Bookings.updateBooking();
	                             return;
	                         }
	                 case 3 -> { 
	                             Bookings.attend();
	                             return;
	                           }
	                 default -> System.out.println("\nPlease enter a valid choice (1-4)");
	             }
            }else {
            	return;
            }
           
        } while (selectedOption != 4);
        return;
    }
    

    //Update Bookings Menu Option
    public static int updateBookingsMenuOption(){
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("\n");
        System.out.println("1. Cancel");
        System.out.println("2. Change");
        System.out.println("3. Attend");
        
    	String inputOption = "0";
    	
        System.out.print("\nDo you want to do any of the above operation (y/n) : ");
        String yesOrNo = scannerObj.nextLine();
        if(yesOrNo.equalsIgnoreCase("yes") || yesOrNo.equalsIgnoreCase("Y")) {
        	 System.out.print("\nEnter Option : ");

             inputOption = scannerObj.nextLine();
             while (inputOption.equals("") || !ValidatorClass.validateInput(inputOption))
             {
                 System.out.print("\nEnter Valid Option ");
                 inputOption = scannerObj.nextLine();
             }
        }
        return Integer.parseInt(inputOption);
    }

    

    //Update Bookings Filter Menu 
    public static void changeBookingFilterMenu(String bookingID){
        
        Bookings.UPDATEBOOKING = 1;
        Bookings.BOOKINGID = bookingID;
        
        int selectedOption;
        do {
            selectedOption = changeBookingFilterMenuOption();
            switch (selectedOption) {
	            case 1 -> ValidatorClass.takeWeekday();
	            case 2 -> ValidatorClass.takeGradeLevel();
	            case 3 -> ValidatorClass.takeCoachID();
                case 4 -> {
		                	 Bookings.UPDATEBOOKING = 0;
		                     Bookings.BOOKINGID = "";
                     
                            return;
                        }
                default -> System.out.println("\nPlease enter a valid choice (1-4)");
            }
        } while (selectedOption != 4);
        return;
    }
    
        
    //Update Bookings Filter Menu Option
    public static int changeBookingFilterMenuOption(){
        
        Scanner scannerObj = new Scanner(System.in);

        System.out.println("\n\nSelect Option from below menu to filter timetable to change class : ");
        System.out.println("1. Timetable By Day");
        System.out.println("2. Timetable By Grade Level");
        System.out.println("3. Timetable By Coach");
        System.out.println("4. Return to Previous Menu");
        System.out.print("\nEnter Option : ");
        String inputOption = scannerObj.nextLine();
        while (inputOption.equals("") || !ValidatorClass.validateInput(inputOption))
        {
            System.out.print("\nEnter Valid Option ");
            inputOption = scannerObj.nextLine();
        }
        return Integer.parseInt(inputOption);
    }
    
    
	
}
