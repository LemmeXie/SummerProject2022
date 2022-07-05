package com.company.bankinksp.controller;

import static java.lang.Math.pow;

public class Maths {

    public static double getPayment(double creditBalance, double interest, double limit) {
        return creditBalance*(interest/(100*12))/(1-pow(1+(interest/(100*12)),-limit));
    }
    public static double getInterestPayment(double interest, double creditBalance) // считаем сколько из месячного платежа идет на погашение процентов
    {
        return interest/(100*12)*creditBalance;
    }
}
