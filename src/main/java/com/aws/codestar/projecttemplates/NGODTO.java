package com.aws.codestar.projecttemplates;

public class NGODTO {
private String ngoName;
private int mobileNumber;
private int preferences;
private String ngoAddress;
public String getNgoName() {
	return ngoName;
}
public void setNgoName(String ngoName) {
	this.ngoName = ngoName;
}
public int getMobileNumber() {
	return mobileNumber;
}
public void setMobileNumber(int mobileNumber) {
	this.mobileNumber = mobileNumber;
}
public int getPreferences() {
	return preferences;
}
public void setPreferences(int preferences) {
	this.preferences = preferences;
}
public String getNgoAddress() {
	return ngoAddress;
}
public void setNgoAddress(String ngoAddress) {
	this.ngoAddress = ngoAddress;
}

@Override
public String toString() {
	return "NGTDto [ngoName=" + ngoName + ", mobileNumber=" + mobileNumber + ", preferences=" + preferences
			+ ", ngoAddress=" + ngoAddress + "]";
}

}
