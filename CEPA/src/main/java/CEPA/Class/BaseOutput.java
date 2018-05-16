package CEPA.Class;


public class BaseOutput {

        private String meta_id_utente;
        private String meta_id_doctor;
        private String meta_operation_type;
        private String meta_timestamp;
        private String meta_routingKey;

    public String getMeta_id_doctor() {
        return meta_id_doctor;
    }

    public String getMeta_id_utente() {
        return meta_id_utente;
    }

    public String getMeta_routingKey() {
        return meta_routingKey;
    }

    public String getMeta_timestamp() {
        return meta_timestamp;
    }

    public String getMeta_operation_type() {
        return meta_operation_type;
    }

    public void setMeta_id_utente(String meta_id_utente) {
        this.meta_id_utente = meta_id_utente;
    }

    public void setMeta_id_doctor(String meta_id_doctor) {
        this.meta_id_doctor = meta_id_doctor;
    }

    public void setMeta_routingKey(String meta_routingKey) {
        this.meta_routingKey = meta_routingKey;
    }

    public void setMeta_operation_type(String meta_operation_type) {
        this.meta_operation_type = meta_operation_type;
    }

    public void setMeta_timestamp(String meta_timestamp) {
        this.meta_timestamp = meta_timestamp;
    }
private boolean hypertensive_patient;

    public boolean isHypertensive_patient() {
        return hypertensive_patient;
    }

    public void setHypertensive_patient(boolean hypertensive_patient) {
        this.hypertensive_patient = hypertensive_patient;
    }
   private  float perc_sat_hemoglobin_last;
private boolean recent_surgery;
private double perc_sat_hemoglobin_middle;

    public void setPerc_sat_hemoglobin_middle(double perc_sat_hemoglobin_middle) {
        this.perc_sat_hemoglobin_middle = perc_sat_hemoglobin_middle;
    }

    public void setPerc_sat_hemoglobin_last(float perc_sat_hemoglobin_last) {
        this.perc_sat_hemoglobin_last = perc_sat_hemoglobin_last;
    }

    public void setRecent_surgery(boolean recent_surgery) {
        this.recent_surgery = recent_surgery;
    }

    public float getPerc_sat_hemoglobin_last() {
        return perc_sat_hemoglobin_last;
    }

    public double getPerc_sat_hemoglobin_middle() {
        return perc_sat_hemoglobin_middle;
    }

    public boolean isRecent_surgery() {
        return recent_surgery;
    }

    private double systolic_pressure;
        private double diastolic_pressure;
        private double heart_rate;
    private boolean epileptic_subject;
    private long number_of_events;
public BaseOutput(){}
    public long getNumber_of_events() {
        return number_of_events;
    }

    public void setNumber_of_events(long number_of_events) {
        this.number_of_events = number_of_events;
    }

    public void setEpileptic_subject(boolean epileptic_subject) {
        this.epileptic_subject = epileptic_subject;
    }

    public boolean isEpileptic_subject() {
        return epileptic_subject;
    }

    public double getDiastolic_pressure() {
            return diastolic_pressure;
        }

        public double getHeart_rate() {
            return heart_rate;
        }

        public double getSystolic_pressure() {
            return systolic_pressure;
        }

        public void setHeart_rate(double heart_rate) {
            this.heart_rate = heart_rate;
        }

        public void setSystolic_pressure(double systolic_pressure) {
            this.systolic_pressure = systolic_pressure;
        }

        public void setDiastolic_pressure(double diastolic_pressure) {
            this.diastolic_pressure = diastolic_pressure;
        }





    @Override
    public String toString() {
        return super.toString();
    }
}