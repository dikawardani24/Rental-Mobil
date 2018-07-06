/*
 * Copyright 2018 dika.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.carRental.activity.menuDataManager;

import com.alee.laf.menu.WebMenuItem;
import com.dika.view.View;
import com.dika.view.component.Frame;
import com.dika.view.component.custom.PrintButton;
import com.dika.view.component.custom.SearchButton;
import com.dika.view.custom.PagingTableViewImpl;
import datechooser.beans.DateChooserCombo;

/**
 * @author dika
 */
public interface TransaksiManagerView extends View<Frame> {

    WebMenuItem getPengembalianDetailMenuItem();

    WebMenuItem getPengembaliDetailMenuItem();

    WebMenuItem getPenerimaDetailMenuItem();

    WebMenuItem getDeleteMenuItem();

    DateChooserCombo getEndDateChooser();

    PagingTableViewImpl getPagingTableViewImpl();

    PrintButton getPrintButton();

    SearchButton getSearchButton();

    DateChooserCombo getStartDateChooer();
}
