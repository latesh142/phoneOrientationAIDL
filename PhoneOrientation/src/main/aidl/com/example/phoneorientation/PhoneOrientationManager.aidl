// PhoneOrientationManager.aidl
package com.example.phoneorientation;

// Declare any non-default types here with import statements
import com.example.phoneorientation.PhoneOrientationListener;

interface PhoneOrientationManager {
    void measureOrientation(PhoneOrientationListener listener);
}
