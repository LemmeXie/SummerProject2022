package com.company.bankinksp.screen.debtoffer;

import io.jmix.ui.screen.*;
import com.company.bankinksp.entity.DebtOffer;

@UiController("DebtOffer.edit")
@UiDescriptor("debt-offer-edit.xml")
@EditedEntityContainer("debtOfferDc")
public class DebtOfferEdit extends StandardEditor<DebtOffer> {
}