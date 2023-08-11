package com.project.emergencyaircraft;

public class NotificationItem {
    public  String id ;
    private String emergencyType;
    private String moreInfo;
    private String eventSpinner;

    private String date;
    private String time;
    private String other;
    private String damages;
    private String nomExploitant;
    private String numeroVol;
    private String typeAeronef;

    public NotificationItem(String id, String emergencyType, String moreInfo, String eventSpinner, String date, String time, String other, String damages, String nomExploitant, String numeroVol, String typeAeronef, String provenance, String destination, String heureEstimee, String immatriculation, String positionExact) {
        this.id = id;
        this.emergencyType = emergencyType;
        this.moreInfo = moreInfo;
        this.eventSpinner = eventSpinner;
        this.date = date;
        this.time = time;
        this.other = other;
        this.damages = damages;
        this.nomExploitant = nomExploitant;
        this.numeroVol = numeroVol;
        this.typeAeronef = typeAeronef;
        this.provenance = provenance;
        this.destination = destination;
        this.heureEstimee = heureEstimee;
        this.immatriculation = immatriculation;
        PositionExact = positionExact;
    }

    private String provenance;
    private String destination;
    private String heureEstimee;
    private String immatriculation;
    private String PositionExact;

    public NotificationItem(String emergencyType, String moreInfo, String eventSpinner, String date, String time, String other, String damages, String nomExploitant, String numeroVol, String typeAeronef, String provenance, String destination, String heureEstimee, String immatriculation, String positionExact) {
        this.emergencyType = emergencyType;
        this.moreInfo = moreInfo;
        this.eventSpinner = eventSpinner;
        this.date = date;
        this.time = time;
        this.other = other;
        this.damages = damages;
        this.nomExploitant = nomExploitant;
        this.numeroVol = numeroVol;
        this.typeAeronef = typeAeronef;
        this.provenance = provenance;
        this.destination = destination;
        this.heureEstimee = heureEstimee;
        this.immatriculation = immatriculation;
        PositionExact = positionExact;
    }

    public NotificationItem() {

    }

    public String getEmergencyType() {
        return emergencyType;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setEmergencyType(String emergencyType) {
        this.emergencyType = emergencyType;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getEventSpinner() {
        return eventSpinner;
    }

    public void setEventSpinner(String eventSpinner) {
        this.eventSpinner = eventSpinner;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public String getNomExploitant() {
        return nomExploitant;
    }

    public void setNomExploitant(String nomExploitant) {
        this.nomExploitant = nomExploitant;
    }

    public String getNumeroVol() {
        return numeroVol;
    }

    public void setNumeroVol(String numeroVol) {
        this.numeroVol = numeroVol;
    }

    public String getTypeAeronef() {
        return typeAeronef;
    }

    public void setTypeAeronef(String typeAeronef) {
        this.typeAeronef = typeAeronef;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHeureEstimee() {
        return heureEstimee;
    }

    public void setHeureEstimee(String heureEstimee) {
        this.heureEstimee = heureEstimee;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getPositionExact() {
        return PositionExact;
    }

    public void setPositionExact(String positionExact) {
        PositionExact = positionExact;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getDamages() {
        return damages;
    }

    public void setDamages(String damages) {
        this.damages = damages;
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Type d'urgence : ").append(emergencyType).append("\n");
//        builder.append("More Info: ").append(moreInfo).append("\n");
        builder.append("Événement : ").append(eventSpinner).append("\n");
        builder.append("Date : ").append(date).append("\n");
        builder.append("Heure : ").append(time).append("\n");

        if ("Impliquant un aeronef".equals(emergencyType)) {
            builder.append("Nom Exploitant: ").append(nomExploitant).append("\n");
            builder.append("Numéro Vol: ").append(numeroVol).append("\n");
            builder.append("Type Aéronef: ").append(typeAeronef).append("\n");
            builder.append("Provenance: ").append(provenance).append("\n");
            builder.append("Destination: ").append(destination).append("\n");
            builder.append("Immatriculation: ").append(immatriculation).append("\n");
        } else if ("N\\'impliquant pas un aeronef".equals(emergencyType)) {
            builder.append("Heure Estimée: ").append(heureEstimee).append("\n");
            builder.append("Position Exacte: ").append(PositionExact).append("\n");
        }
        builder.append("Étendu des dommages : ").append(damages);
        builder.append("Autres informations : ").append(other).append("\n");


        return builder.toString();
    }
}
