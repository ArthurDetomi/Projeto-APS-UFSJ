package edu.ufsj.utils;

public class CpfUtil {
    public static boolean isValidCpf(String cpf) {
        return cpf.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}");
    }
}
