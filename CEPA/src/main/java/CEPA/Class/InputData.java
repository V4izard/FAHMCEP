package CEPA.Class;



public class InputData{

    class event
    {
        class payloadData
        {
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
            public payloadData(){}
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

            public boolean isEpilettiform_pattern() {
                return epilettiform_pattern;
            }

            @Override
            public String toString() {
                return super.toString();
            }

        }

        class metaData{
            private String id_utente;
            private String id_doctor;
            private String alertContact;
            private String timestamp;
            private String routingKey;
            public metaData(){}
            public String getAlertContact() {
                return alertContact;
            }

            public String getId_doctor() {
                return id_doctor;
            }

            public String getId_utente() {
                return id_utente;
            }

            public String getRoutingKey() {
                return routingKey;
            }

            public String getTimestamp() {
                return timestamp;
            }

            public void setId_utente(String id_utente) {
                this.id_utente = id_utente;
            }

            public void setAlertContact(String alertContact) {
                this.alertContact = alertContact;
            }

            public void setId_doctor(String id_doctor) {
                this.id_doctor = id_doctor;
            }

            public void setRoutingKey(String routingKey) {
                this.routingKey = routingKey;
            }

            public void setTimestamp(String timestamp) {
                this.timestamp = timestamp;
            }

            @Override
            public String toString() {
                return super.toString();
            }
        }

        private metaData  metaData;
        private payloadData payloadData;
        public event(){}
        public metaData getMetaData() {
            return metaData;
        }

        public void setMetaData(metaData metaData) {
            this.metaData = metaData;
        }

        public payloadData getPayloadData() {
            return payloadData;
        }

        public void setPayloadData(payloadData payloadData) {
            this.payloadData = payloadData;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
    private  event event;

    public void setEvent(event event) {
        this.event = event;
    }


    public event getEvent() {
        return event;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

