package com.company.bankinksp.screen.debtoffer;

import io.jmix.ui.screen.*;
import com.company.bankinksp.entity.DebtOffer;

@UiController("DebtOffer.browse")
@UiDescriptor("debt-offer-browse.xml")
@LookupComponent("debtOffersTable")
public class DebtOfferBrowse extends StandardLookup<DebtOffer> {
}