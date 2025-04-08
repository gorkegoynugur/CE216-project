package com.example.ce216_project;

import java.util.List;

public class HistoricalArtifact {
    private static int counter = 1;
    private int artifactId;
    private String name;
    private String category;
    private String origin;
    private String discoverySite;
    private String material;
    private String dateOfDiscovery;
    private String location;
    private double artifactWidth, artifactLength, artifactHeight, artifactWeight;
    private List<String> labels;
    private String imageFilePath;

    public HistoricalArtifact() {
        this.artifactId = counter++;
        this.name = "Unnamed";
        this.category = "Uncategorized";
        this.origin = "Unknown";
        this.discoverySite = "Undiscovered";
        this.material = "Undefined";
        this.dateOfDiscovery = "Not Available";
        this.location = "Not Displayed";
    }

    public HistoricalArtifact(int artifactId, String name, String category, String origin,
                              String discoverySite, String material, String dateOfDiscovery, String location,
                              double artifactWidth, double artifactLength, double artifactHeight, double artifactWeight, List<String> labels) {
        this.artifactId = artifactId;
        this.name = name;
        this.category = category;
        this.origin = origin;
        this.discoverySite = discoverySite;
        this.material = material;
        this.dateOfDiscovery = dateOfDiscovery;
        this.location = location;
        this.artifactWidth = artifactWidth;
        this.artifactLength = artifactLength;
        this.artifactHeight = artifactHeight;
        this.artifactWeight = artifactWeight;
        this.labels = labels;
    }

    public int getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(int artifactId) {
        this.artifactId = artifactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDiscoverySite() {
        return discoverySite;
    }

    public void setDiscoverySite(String discoverySite) {
        this.discoverySite = discoverySite;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDateOfDiscovery() {
        return dateOfDiscovery;
    }

    public void setDateOfDiscovery(String dateOfDiscovery) {
        this.dateOfDiscovery = dateOfDiscovery;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getArtifactWidth() {
        return artifactWidth;
    }

    public void setArtifactWidth(double artifactWidth) {
        this.artifactWidth = artifactWidth;
    }

    public double getArtifactLength() {
        return artifactLength;
    }

    public void setArtifactLength(double artifactLength) {
        this.artifactLength = artifactLength;
    }

    public double getArtifactHeight() {
        return artifactHeight;
    }

    public void setArtifactHeight(double artifactHeight) {
        this.artifactHeight = artifactHeight;
    }

    public double getArtifactWeight() {
        return artifactWeight;
    }

    public void setArtifactWeight(double artifactWeight) {
        this.artifactWeight = artifactWeight;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }
}

