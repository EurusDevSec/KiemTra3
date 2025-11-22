package com.example.cau2;

public class Software {
    private int id;
    private String softId;
    private String softName;
    private String version;
    private String publish;

    public Software() {
    }

    public Software(String softId, String softName, String version, String publish) {
        this.softId = softId;
        this.softName = softName;
        this.version = version;
        this.publish = publish;
    }

    public Software(int id, String softId, String softName, String version, String publish) {
        this.id = id;
        this.softId = softId;
        this.softName = softName;
        this.version = version;
        this.publish = publish;
    }

    // Getters, Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoftId() {
        return softId;
    }

    public void setSoftId(String softId) {
        this.softId = softId;
    }

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        return "ID: " + softId + "\nName: " + softName + "\nVersion: " + version + "\nPublisher: " + publish;
    }
}