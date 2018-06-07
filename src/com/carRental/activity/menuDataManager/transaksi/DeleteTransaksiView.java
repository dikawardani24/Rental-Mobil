package com.carRental.activity.menuDataManager.transaksi;

import com.dika.view.View;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.Label;

public interface DeleteTransaksiView extends View<Dialog> {
    Label getIdTransaksiLabel();

    Button getConfirmedButton();
    Button getUnconfirmedButton();
}
