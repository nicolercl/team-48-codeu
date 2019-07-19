package com.google.codeu.data;
import java.util.HashSet;

public class User {

    private String email;
    private String aboutMe;
    private String learnCategory;
    private String teachCategory;
    private String school;
    private String name;
    private String age;
    private String skillLevel;
    private HashSet<String> likedMessages;

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
        this.likedMessages = new HashSet<String>();
    }

    public User(String email, String aboutMe, String learnCategory, String teachCategory, String school,
                String age, String name, String skillLevel, HashSet<String> likedMessages) {
        this.email = email;
        this.aboutMe = aboutMe;
        this.learnCategory = learnCategory;
        this.teachCategory = teachCategory;
        this.school = school;
        this.name = name;
        this.age = age;
        this.skillLevel = skillLevel;
        this.likedMessages = new HashSet<String>();
        this.likedMessages.addAll(likedMessages);
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

    public HashSet<String> getLikedMessages(){
        return this.likedMessages;
    }

    public Boolean inLikedMessages(String id) {
        return this.likedMessages.contains(id);
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setLearnCategory(String learnCategory) {
        this.learnCategory = learnCategory;
    }

    public void setTeachCategory(String teachCategory) {
        this.teachCategory = teachCategory;
    }

    public void setSchool(String school){
        this.school = school;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(String age){
        this.age = age;
    }

    public void setSkillLevel(String skillLevel){
        this.skillLevel = skillLevel;
    }

    public void setLikedMessages(HashSet<String> likedMessages){
        this.likedMessages = new HashSet<String>();
        this.likedMessages.addAll(likedMessages);
    }

    public void updateLikedMessages(String id) {
        if (this.likedMessages.contains(id)){
            this.likedMessages.remove(id);
        }
        else {
            this.likedMessages.add(id);
        }
    }

}