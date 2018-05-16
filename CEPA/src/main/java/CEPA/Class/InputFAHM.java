package CEPA.Class;


public class InputFAHM {

    private String meta_id_utente;
    private String meta_id_doctor;
    private String meta_alertContact;
    private String meta_timestamp;
    private String meta_routingKey;
public InputFAHM(){}
public InputFAHM(InputData  pojo){
    meta_id_utente=pojo.getEvent().getMetaData().getId_utente();
    meta_id_doctor=pojo.getEvent().getMetaData().getId_doctor();
    meta_timestamp=pojo.getEvent().getMetaData().getTimestamp();
    meta_alertContact=pojo.getEvent().getMetaData().getAlertContact();
    meta_routingKey=pojo.getEvent().getMetaData().getRoutingKey();
    epileptic_subject=pojo.getEvent().getPayloadData().isEpileptic_subject();
    recent_surgery=pojo.getEvent().getPayloadData().isRecent_surgery();
    systolic_pressure=pojo.getEvent().getPayloadData().getSystolic_pressure();
    diastolic_pressure=pojo.getEvent().getPayloadData().getDiastolic_pressure();
    heart_rate=pojo.getEvent().getPayloadData().getHeart_rate();
    movement_type=pojo.getEvent().getPayloadData().getMovement_type();
    epilettiform_pattern=pojo.getEvent().getPayloadData().isEpilettiform_pattern();
    perc_sat_hemoglobin=pojo.getEvent().getPayloadData().getPerc_sat_hemoglobin();
    hypertensive_patient=pojo.getEvent().getPayloadData().isHypertensive_patient();
    hypotensive_patient=pojo.getEvent().getPayloadData().isHypotensive_patient();
}

    private boolean epileptic_subject;
    private boolean recent_surgery;
    private int systolic_pressure;
    private int diastolic_pressure;
    private int heart_rate;
    private int movement_type;
    private boolean epilettiform_pattern;
    private float perc_sat_hemoglobin;
    private boolean hypertensive_patient;
    private boolean hypotensive_patient;
    public boolean isEpilettiform_pattern() {
        return epilettiform_pattern;
    }
    public void setEpileptic_subject(boolean epileptic_subject) {
        this.epileptic_subject = epileptic_subject;
    }

    public void setRecent_surgery(boolean recent_surgery) {
        this.recent_surgery = recent_surgery;
    }

    public boolean isRecent_surgery() {
        return recent_surgery;
    }

    public boolean isEpileptic_subject() {
        return epileptic_subject;
    }

    public boolean isHypertensive_patient() {
        return hypertensive_patient;
    }

    public boolean isHypotensive_patient() {
        return hypotensive_patient;
    }

    public float getPerc_sat_hemoglobin() {
        return perc_sat_hemoglobin;
    }

    public int getDiastolic_pressure() {
        return diastolic_pressure;
    }

    public int getHeart_rate() {
        return heart_rate;
    }

    public int getMovement_type() {
        return movement_type;
    }

    public int getSystolic_pressure() {
        return systolic_pressure;
    }

    public void setDiastolic_pressure(int diastolic_pressure) {
        this.diastolic_pressure = diastolic_pressure;
    }

    public void setSystolic_pressure(int systolic_pressure) {
        this.systolic_pressure = systolic_pressure;
    }

    public void setEpilettiform_pattern(boolean epilettiform_pattern) {
        this.epilettiform_pattern = epilettiform_pattern;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

    public void setHypertensive_patient(boolean hypertensive_patient) {
        this.hypertensive_patient = hypertensive_patient;
    }

    public void setHypotensive_patient(boolean hypotensive_patient) {
        this.hypotensive_patient = hypotensive_patient;
    }

    public void setMovement_type(int movement_type) {
        this.movement_type = movement_type;
    }

    public void setPerc_sat_hemoglobin(float perc_sat_hemoglobin) {
        this.perc_sat_hemoglobin = perc_sat_hemoglobin;
    }

    public void setMeta_id_utente(String meta_id_utente) {
        this.meta_id_utente = meta_id_utente;
    }

    public void setMeta_id_doctor(String meta_id_doctor) {
        this.meta_id_doctor = meta_id_doctor;
    }

    public void setMeta_timestamp(String meta_timestamp) {
        this.meta_timestamp = meta_timestamp;
    }

    public void setMeta_routingKey(String meta_routingKey) {
        this.meta_routingKey = meta_routingKey;
    }

    public void setMeta_alertContact(String meta_alertContact) {
        this.meta_alertContact = meta_alertContact;
    }

    public String getMeta_timestamp() {
        return meta_timestamp;
    }

    public String getMeta_routingKey() {
        return meta_routingKey;
    }

    public String getMeta_id_utente() {
        return meta_id_utente;
    }

    public String getMeta_id_doctor() {
        return meta_id_doctor;
    }

    public String getMeta_alertContact() {
        return meta_alertContact;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
