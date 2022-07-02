package com.company.bankinksp.screen.debt;

import io.jmix.ui.screen.*;
import com.company.bankinksp.entity.Debt;

@UiController("Debt.browse")
@UiDescriptor("debt-browse.xml")
@LookupComponent("debtsTable")
public class DebtBrowse extends StandardLookup<Debt> {
}