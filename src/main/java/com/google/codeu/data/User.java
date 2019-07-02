package com.google.codeu.data;

public class User {

    private String email;
    private String aboutMe;
    private String learnCategory;
    private String teachCategory;
    private String school;
    private String name;
    private String age;
    private String skillLevel;

    public User(String email, String aboutMe, String learnCategory, String teachCategory, String school,
                String age, String name, String skillLevel) {
        this.email = email;
        this.aboutMe = aboutMe;
        this.learnCategory = learnCategory;
        this.teachCategory = teachCategory;
        this.school = school;
        this.name = name;
        this.age = age;
        this.skillLevel = skillLevel;
    }

    public String getEmail() {
        return email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getLearnCategory() {
        return learnCategory;
    }

    public String getTeachCategory() {
        return teachCategory;
    }

    public String getSchool() {
        return school;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}