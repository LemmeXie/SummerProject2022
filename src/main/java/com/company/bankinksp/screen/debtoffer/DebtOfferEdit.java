package com.company.bankinksp.screen.debtoffer;

import com.company.bankinksp.entity.Debt;
import io.jmix.ui.component.DateField;
import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.component.HasValue;
import io.jmix.ui.component.TextField;
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

    @Subscribe("debtSumField")
    public void onDebtSumFieldValueChange(HasValue.ValueChangeEvent<Double> event) { //поля изменяются при изменении основных параметров таких как сумма кредита
        if (!(debtSumField.getValue() > debtOffersField.getValue().getLimit())) {
            if (!debtSumField.isEmpty() && !debtOffersField.isEmpty() && !paymentScheduleField.isEmpty()) {
                monthPayment = Maths.getPayment(debtSumField.getValue(), debtOffersField.getValue().getInterestRate(), paymentScheduleField.getValue());
                monthInterestPayment = Maths.getInterestPayment(debtOffersField.getValue().getInterestRate(), debtSumField.getValue());
                monthDebtPayment = monthPayment - monthInterestPayment;
                paymentSumField.setValue(monthPayment);
                bodySumField.setValue(monthDebtPayment);
                interestSumField.setValue(monthInterestPayment);
            }
        }
        else
        {
            //todo здесь бы алерт возвращать, пока не знаю как
        }
    }

    //todo сделать невозможным ввод суммы кредита выше лимита, вроде пока всё

    @Subscribe("paymentScheduleField")
    public void onPaymentScheduleFieldValueChange(HasValue.ValueChangeEvent<Integer> event) { // и срока кредита
        if (!(debtSumField.getValue() > debtOffersField.getValue().getLimit())) {
            if (!debtSumField.isEmpty() && !debtOffersField.isEmpty() && !paymentScheduleField.isEmpty()) {
                monthPayment = Maths.getPayment(debtSumField.getValue(), debtOffersField.getValue().getInterestRate(), paymentScheduleField.getValue());
                monthInterestPayment = Maths.getInterestPayment(debtOffersField.getValue().getInterestRate(), debtSumField.getValue());
                monthDebtPayment = monthPayment - monthInterestPayment;
                paymentSumField.setValue(monthPayment);
                bodySumField.setValue(monthDebtPayment);
                interestSumField.setValue(monthInterestPayment);
            }
        }
        else
        {
            //todo здесь бы алерт возвращать, пока не знаю как
        }
    }

    @Subscribe("debtOffersField") //todo ? можно конечно еще дату вставлять, но там чет совсем плохо с форматом dd-mm-yyyy (аж 4 строчки)
    public void onDebtOffersFieldValueChange(HasValue.ValueChangeEvent<Debt> event) {//автозаполнение полей красоты ради
        if (debtOffersField.getValue().getLimit()>500000.0) {
            debtSumField.setValue(500000.0);
            paymentScheduleField.setValue(24);
        }
        else {
            debtSumField.setValue(30000.0);
            paymentScheduleField.setValue(12);
        }
    }
}