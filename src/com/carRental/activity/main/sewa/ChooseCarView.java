package com.carRental.activity.main.sewa;

import com.alee.laf.menu.WebMenuItem;
import com.dika.view.View;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.custom.PagingTableView;

public interface ChooseCarView extends View<Dialog> {
    WebMenuItem getChooseCarMenuItem();
    PagingTableView getPagingTableView();
    Button getCancelButton();
}
