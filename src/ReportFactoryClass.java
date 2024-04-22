
public class ReportFactoryClass {

    public static GenerateReports callreport(int ReportType) {
        switch (ReportType) {
            case 6 -> {
                return new LearnerBookingsReport();
            }
            case 7 -> {
                return new CoachRatingReport();
            }
            default -> throw new IllegalArgumentException("Invalid report ReportType");
        }
    }    
}
