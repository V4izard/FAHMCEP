package CEPA.Class;

public class Hypoxic {
    public class event
    {
        public event(BaseOutput out){metaData=new metaData(out);
            payloadData=new payloadData(out);}
        public class metaData{
            public  metaData(BaseOutput out){
                setId_utente(out.getMeta_id_utente());
                setOperation_type(out.getMeta_operation_type());
                setRoutingKey(out.getMeta_routingKey());
                setTimestamp(out.getMeta_timestamp());
                setId_doctor(out.getMeta_id_doctor());
            }
            private String id_utente;
            private String id_doctor;
            private String operation_type;
            private String timestamp;
            private String routingKey;

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

            public String getOperation_type() {
                return operation_type;
            }

            public void setOperation_type(String operation_type) {
                this.operation_type = operation_type;
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

        public class payloadData{
            public payloadData(BaseOutput out){
                setDiastolic_pressure(out.getDiastolic_pressure());
                setSystolic_pressure(out.getSystolic_pressure());
                setHeart_rate(out.getHeart_rate());
               setRecent_surgery(out.isRecent_surgery());
               setPerc_sat_hemoglobin_last(out.getPerc_sat_hemoglobin_last());
               setPerc_sat_hemoglobin_middle(out.getPerc_sat_hemoglobin_middle());


            }
            private double systolic_pressure;
            private double diastolic_pressure;
            private double heart_rate;
            private boolean recent_surgery;
            private float perc_sat_hemoglobin_last;
            private double perc_sat_hemoglobin_middle;

            public double getPerc_sat_hemoglobin_middle() {
                return perc_sat_hemoglobin_middle;
            }

            public float getPerc_sat_hemoglobin_last() {
                return perc_sat_hemoglobin_last;
            }

            public void setRecent_surgery(boolean recent_surgery) {
                this.recent_surgery = recent_surgery;
            }

            public void setPerc_sat_hemoglobin_last(float perc_sat_hemoglobin_last) {
                this.perc_sat_hemoglobin_last = perc_sat_hemoglobin_last;
            }

            public void setPerc_sat_hemoglobin_middle(double perc_sat_hemoglobin_middle) {
                this.perc_sat_hemoglobin_middle = perc_sat_hemoglobin_middle;
            }

            public boolean isRecent_surgery() {
                return recent_surgery;
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
        };

        private metaData  metaData;
        private payloadData payloadData;

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


    public Hypoxic(BaseOutput out){event=new event(out);}

    private event event;

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
