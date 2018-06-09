package com.carRental.activity.menuDataManager.transaksi;

import com.dika.util.CollectionHelper;
import com.dika.view.InputView;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;
import com.dika.view.component.custom.CurrencyFormattedTextField;
import com.dika.view.component.custom.DecimalFormattedTextField;

import javax.swing.text.JTextComponent;
import java.util.List;

public interface DetailPengembalianView extends InputView<Dialog> {
    TextField getIdPengembalianField();
    TextField getTglKembaliField();

    DecimalFormattedTextField getOvertimeField();
    CurrencyFormattedTextField getBiayaOvertimeField();
    CurrencyFormattedTextField getTotalTagihanField();

    Button getCloseButton();

    @Override
    default List<JTextComponent> getTextComponents() {
        return CollectionHelper.INSTANCE.collectAsArrayList(
                getIdPengembalianField(),
                getTglKembaliField(),
                getOvertimeField(),
                getBiayaOvertimeField(),
                getTotalTagihanField()
        );
    }
}
