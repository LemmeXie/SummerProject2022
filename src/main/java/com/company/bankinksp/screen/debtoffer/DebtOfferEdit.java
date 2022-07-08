package com.company.bankinksp.screen.debtoffer;

import com.company.bankinksp.entity.Debt;
import io.jmix.ui.component.*;
import io.jmix.ui.screen.*;
import com.company.bankinksp.entity.DebtOffer;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import com.company.bankinksp.controller.Maths;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import static javax.print.attribute.Size2DSyntax.MM;

@UiController("DebtOffer.edit")
@UiDescriptor("debt-offer-edit.xml")
@EditedEntityContainer("debtOfferDc")
public class DebtOfferEdit extends StandardEditor<DebtOffer> {

    //todo вопрос: лучше сделать переменные или постоянно из формы вытаскивать, тк я не знаю, что эффективнее
    @Autowired
    private TextField<Double> paymentSumField;
    @Autowired
    private TextField<Double> debtSumField;
    @Autowired
    private TextField<Integer> paymentScheduleField;
    @Autowired
    private EntityPicker<Debt> debtOffersField;
    @Autowired
    private TextField<Double> interestSumField;
    @Autowired
    private TextField<Double> bodySumField;
    private double monthPayment;
    private double monthInterestPayment;
    private double monthDebtPayment;
    @Autowired
    private DateField<LocalDate> datePaymentField;

    @Autowired
    private Slider<Number> debtSumSlider;

    @Subscribe("debtSumSlider")
    public void onDebtSumSliderValueChange(HasValue.ValueChangeEvent<Number> event) {
        debtSumField.setValue(debtSumSlider.getValue().doubleValue());
        //ниже код: мы заполняем поля подсчета ежемесячных выплат
        if (!debtSumSlider.isEmpty() && !debtOffersField.isEmpty() && !paymentScheduleField.isEmpty()) {
            monthPayment = Maths.getPayment(debtSumField.getValue(), debtOffersField.getValue().getInterestRate(), paymentScheduleField.getValue());
            monthInterestPayment = Maths.getInterestPayment(debtOffersField.getValue().getInterestRate(), debtSumField.getValue());
            monthDebtPayment = monthPayment - monthInterestPayment;
            paymentSumField.setValue(monthPayment);
            bodySumField.setValue(monthDebtPayment);
            interestSumField.setValue(monthInterestPayment);
        }
    }

    @Subscribe("debtSumField")
    public void onDebtSumFieldValueChange(HasValue.ValueChangeEvent<Double> event) { //поля изменяются при изменении основных параметров таких как сумма кредита
        if (debtSumField.getValue() > debtOffersField.getValue().getLimit()) {
            debtSumField.setValue(debtOffersField.getValue().getLimit());
        }
        debtSumSlider.setValue(debtSumField.getValue());
        //ниже код: мы заполняем поля подсчета ежемесячных выплат
        if (!debtSumField.isEmpty() && !debtOffersField.isEmpty() && !paymentScheduleField.isEmpty()) {
            monthPayment = Maths.getPayment(debtSumField.getValue(), debtOffersField.getValue().getInterestRate(), paymentScheduleField.getValue());
            monthInterestPayment = Maths.getInterestPayment(debtOffersField.getValue().getInterestRate(), debtSumField.getValue());
            monthDebtPayment = monthPayment - monthInterestPayment;
            paymentSumField.setValue(monthPayment);
            bodySumField.setValue(monthDebtPayment);
            interestSumField.setValue(monthInterestPayment);
        }
    }


    @Subscribe("paymentScheduleField")
    public void onPaymentScheduleFieldValueChange(HasValue.ValueChangeEvent<Integer> event) { // и срока кредита
        if (!debtSumField.isEmpty() && !debtOffersField.isEmpty() && !paymentScheduleField.isEmpty()) {
            monthPayment = Maths.getPayment(debtSumField.getValue(), debtOffersField.getValue().getInterestRate(), paymentScheduleField.getValue());
            monthInterestPayment = Maths.getInterestPayment(debtOffersField.getValue().getInterestRate(), debtSumField.getValue());
            monthDebtPayment = monthPayment - monthInterestPayment;
            paymentSumField.setValue(monthPayment);
            bodySumField.setValue(monthDebtPayment);
            interestSumField.setValue(monthInterestPayment);
        }
    }

    @Subscribe("debtOffersField")
    public void onDebtOffersFieldValueChange(HasValue.ValueChangeEvent<Debt> event) {//автозаполнение полей красоты ради
        debtSumSlider.setMax(debtOffersField.getValue().getLimit());
        if (debtSumField.isEmpty() && paymentScheduleField.isEmpty()) {
            if (debtOffersField.getValue().getLimit() > 500000.0) {
                debtSumField.setValue(500000.0);
                debtSumSlider.setValue(500000.0);
                paymentScheduleField.setValue(24);
            } else {
                debtSumSlider.setValue(30000.0);
                debtSumField.setValue(30000.0);
                paymentScheduleField.setValue(12);
            }
        }
        else if(debtSumField.getValue()>debtOffersField.getValue().getLimit())
        {
            debtSumField.setValue(debtOffersField.getValue().getLimit()/2);
        }
    }
}