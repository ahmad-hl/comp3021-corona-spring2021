package pa1.util;


/**
 * An immutable class that encapsulates the cost amount.
 */
// TODO
public class Cost {

    public static int getMedicalLabCost(){
        return Constants.BUILD_MEDICAL_LAB_COST;
    }

    public static int getHospitalCost(){
        return Constants.BUILD_HOSPITAL_COST;
    }

    public static int getDoctorsCost(int numDoctors ){
        return Constants.RECRUITE_DOCTOR_COST * numDoctors;
    }

}
