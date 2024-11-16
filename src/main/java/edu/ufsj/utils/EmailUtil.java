package edu.ufsj.utils;

public class EmailUtil {

	public static boolean isEmailValid(String email) {
		if (email == null) {
			return true;
		}
		return email.matches("[a-z0-9.]+@[a-z0-9.]+.[a-z]+");
	}

}
