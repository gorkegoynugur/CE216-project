package com.example.ce216_project.model;

import java.util.List;

public class Artifact {
    private String artifactId;
    private String artifactName;
    private String category;
    private String civilization;
    private String discoveryLocation;
    private String composition;
    private String discoveryDate;
    private String currentPlace;
    private double width;
    private double length;
    private double height;
    private double weight;
    private List<String> tags;
    private String imagePath;

    // Constructor
    public Artifact() {}

    // Getters and Setters
    public String getArtifactId() { return artifactId; }
    public void setArtifactId(String artifactId) { this.artifactId = artifactId; }

    public String getArtifactName() { return artifactName; }
    public void setArtifactName(String artifactName) { this.artifactName = artifactName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getCivilization() { return civilization; }
    public void setCivilization(String civilization) { this.civilization = civilization; }

    public String getDiscoveryLocation() { return discoveryLocation; }
    public void setDiscoveryLocation(String discoveryLocation) { this.discoveryLocation = discoveryLocation; }

    public String getComposition() { return composition; }
    public void setComposition(String composition) { this.composition = composition; }

    public String getDiscoveryDate() { return discoveryDate; }
    public void setDiscoveryDate(String discoveryDate) { this.discoveryDate = discoveryDate; }

    public String getCurrentPlace() { return currentPlace; }
    public void setCurrentPlace(String currentPlace) { this.currentPlace = currentPlace; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getLength() { return length; }
    public void setLength(double length) { this.length = length; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public List<String> getTags() { return tags; }
    public void setTags(List<String> tags) { this.tags = tags; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public String getDimensions() {
        return width + " x " + length + " x " + height;
    }

}