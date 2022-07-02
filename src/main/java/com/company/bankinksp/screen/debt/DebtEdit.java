package com.company.bankinksp.screen.debt;

import io.jmix.ui.screen.*;
import com.company.bankinksp.entity.Debt;

@UiController("Debt.edit")
@UiDescriptor("debt-edit.xml")
@EditedEntityContainer("debtDc")
public class DebtEdit extends StandardEditor<Debt> {
}