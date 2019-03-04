package com.example.schooltrainer2;

import java.util.List;
import java.util.Vector;

// com.example.schooltrainer2.Exercise class manages visualization and persistence
// of the different exercise types.
// It does not have business logic.

public class Exercise {
    public String sName = "";
    public String sDescription = "";

    public Exercise(String Name, String Description) {
        sName = Name.trim();
        sDescription = Description.trim();
    }
}
