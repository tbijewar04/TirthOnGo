
// package com.tirthongo.model; // Recommended package for data models, or adjust to your preference

// import java.time.LocalDate; // For parsing 'dob' as LocalDate if you want to work with date objects
// import java.time.format.DateTimeFormatter; // For formatting LocalDate back to String

// public class UserProfileModel {

//     // Declare private fields for each key provided
//     private String address;
//     private String dob; // Stored as String (ISO format like "YYYY-MM-DD")
//     private String firstName;
//     private String gender;
//     private String lastName;
//     private String pincode;
//     private String state;
//     private String email;
//     private String mobile; // Corresponds to 'phone' in UI
//     private String profileImageUrl;
//     private String documentId; // The user's UID (document ID in Firestore)

//     // --- Getter and Setter methods for each field ---

//     public String getAddress() {
//         return address;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public String getDob() {
//         return dob;
//     }

//     public void setDob(String dob) {
//         this.dob = dob;
//     }
    
//     // Optional helper: Get DOB as LocalDate
//     public LocalDate getDobAsLocalDate() {
//         return dob != null && !dob.isEmpty() ? LocalDate.parse(dob) : null;
//     }
//     // Optional helper: Set DOB from LocalDate
//     public void setDobFromLocalDate(LocalDate dobDate) {
//         this.dob = dobDate != null ? dobDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
//     }

//     public String getFirstName() {
//         return firstName;
//     }

//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }

//     public String getGender() {
//         return gender;
//     }

//     public void setGender(String gender) {
//         this.gender = gender;
//     }

//     public String getLastName() {
//         return lastName;
//     }

//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }

//     public String getPincode() {
//         return pincode;
//     }

//     public void setPincode(String pincode) {
//         this.pincode = pincode;
//     }

//     public String getState() {
//         return state;
//     }

//     public void setState(String state) {
//         this.state = state;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getMobile() {
//         return mobile;
//     }

//     public void setMobile(String mobile) {
//         this.mobile = mobile;
//     }

//     public String getProfileImageUrl() {
//         return profileImageUrl;
//     }

//     public void setProfileImageUrl(String profileImageUrl) {
//         this.profileImageUrl = profileImageUrl;
//     }

//     public String getDocumentId() {
//         return documentId;
//     }

//     public void setDocumentId(String documentId) {
//         this.documentId = documentId;
//     }
// }





package com.tirthongo.model; // Recommended package for data models, or adjust to your preference

import java.time.LocalDate; // For parsing 'dob' as LocalDate if you want to work with date objects
import java.time.format.DateTimeFormatter; // For formatting LocalDate back to String

public class UserProfileModel {

    // Declare private fields for each key provided
    private String address;
    private String dob; // Stored as String (ISO format like "YYYY-MM-DD")
    private String firstName;
    private String gender;
    private String lastName;
    private String pincode;
    private String state;
    private String email;
    private String mobile; // Corresponds to 'phone' in UI
    private String profileImageUrl;
    private String documentId; // The user's UID (document ID in Firestore)

    // --- Getter and Setter methods for each field ---

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
    
    // Optional helper: Get DOB as LocalDate
    public LocalDate getDobAsLocalDate() {
        return dob != null && !dob.isEmpty() ? LocalDate.parse(dob) : null;
    }
    // Optional helper: Set DOB from LocalDate
    public void setDobFromLocalDate(LocalDate dobDate) {
        this.dob = dobDate != null ? dobDate.format(DateTimeFormatter.ISO_LOCAL_DATE) : null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
