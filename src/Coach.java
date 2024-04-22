import java.util.ArrayList;

public class Coach {


    private int coachID;
    private String name;
    private String contact;

    public static ArrayList <Coach> coachDataList = new ArrayList<>();

	public Coach(int coachID, String name, String contact) {
		super();
		this.coachID = coachID;
		this.name = name;
		this.contact = contact;
	}

	public int getCoachID() {
		return coachID;
	}

	public String getName() {
		return name;
	}

	public String getContact() {
		return contact;
	}

	public static ArrayList<Coach> getCoachDataList() {
		return coachDataList;
	}

    
    
}
