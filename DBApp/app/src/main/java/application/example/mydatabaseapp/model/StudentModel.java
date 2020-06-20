package application.example.mydatabaseapp.model;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentModel implements Parcelable {

    String rollno, name, email;

    public StudentModel(String rollno, String name, String email) {
        this.rollno = rollno;
        this.name = name;
        this.email = email;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "rollno='" + rollno + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.rollno);
        dest.writeString(this.name);
        dest.writeString(this.email);
    }

    protected StudentModel(Parcel in) {
        this.rollno = in.readString();
        this.name = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<StudentModel> CREATOR = new Parcelable.Creator<StudentModel>() {
        @Override
        public StudentModel createFromParcel(Parcel source) {
            return new StudentModel(source);
        }

        @Override
        public StudentModel[] newArray(int size) {
            return new StudentModel[size];
        }
    };
}
