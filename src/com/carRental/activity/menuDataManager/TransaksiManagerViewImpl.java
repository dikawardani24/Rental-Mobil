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
import com.dika.res.SmallIconRes;
import com.dika.view.component.Frame;
import com.dika.view.component.custom.PrintButton;
import com.dika.view.component.custom.SearchButton;
import com.dika.view.custom.PagingTableViewImpl;
import datechooser.beans.DateChooserCombo;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

/**
 *
 * @author dika
 */
public final class TransaksiManagerViewImpl extends Frame implements TransaksiManagerView{

    /**
     * Creates new form TransaksiManagerViewImpl
     */
    public TransaksiManagerViewImpl() {
        initComponents();
        setTitle("Manager Data Transaksi");
        createContextMenuOnTable();
    }
    
    private void createContextMenuOnTable() {
        SmallIconRes res = SmallIconRes.INSTANCE;
        penerimaanDetailMenuItem = new WebMenuItem("Detail Penerimaan", res.getSeeIcon());
        pengembalianDetailMenuItem= new WebMenuItem("Detail Pengembalian", res.getSeeIcon());
        penerimaDetailMenuItem= new WebMenuItem("Detail Penerima", res.getUser20Icon());
        pengembaliDetailMenuItem= new WebMenuItem("Detail Penerimaan", res.getUser20Icon());
        deleteMenuItem = new WebMenuItem("Hapus", res.getCancelIcon());

        JPopupMenu tableDataMenu = new JPopupMenu("Table Menu");
        tableDataMenu.add(penerimaanDetailMenuItem);
        tableDataMenu.add(pengembalianDetailMenuItem);
        tableDataMenu.add(new JSeparator());
        tableDataMenu.add(penerimaDetailMenuItem);
        tableDataMenu.add(pengembaliDetailMenuItem);
        tableDataMenu.add(new JSeparator());
        tableDataMenu.add(deleteMenuItem);
        pagingTableViewImpl1.getTable().setComponentPopupMenu(tableDataMenu);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        com.dika.view.component.Panel panel1 = new com.dika.view.component.Panel();
        com.dika.view.component.custom.HeaderLabel headerLabel1 = new com.dika.view.component.custom.HeaderLabel();
        pagingTableViewImpl1 = new com.dika.view.custom.PagingTableViewImpl();
        printButton1 = new com.dika.view.component.custom.PrintButton();
        searchButton1 = new com.dika.view.component.custom.SearchButton();
        com.dika.view.component.Label label1 = new com.dika.view.component.Label();
        com.dika.view.component.Label label2 = new com.dika.view.component.Label();
        endDateChooser = new datechooser.beans.DateChooserCombo();
        startDateChooer = new datechooser.beans.DateChooserCombo();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        headerLabel1.setText("Transaksi Data Manager");

        label1.setText("sampai");

        label2.setText("Cari dari");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagingTableViewImpl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(0, 137, Short.MAX_VALUE)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startDateChooer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(printButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(endDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(startDateChooer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(pagingTableViewImpl1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private WebMenuItem penerimaanDetailMenuItem;
    private WebMenuItem pengembalianDetailMenuItem;
    private WebMenuItem penerimaDetailMenuItem;
    private WebMenuItem pengembaliDetailMenuItem;
    private WebMenuItem deleteMenuItem;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private datechooser.beans.DateChooserCombo endDateChooser;
    private com.dika.view.custom.PagingTableViewImpl pagingTableViewImpl1;
    private com.dika.view.component.custom.PrintButton printButton1;
    private com.dika.view.component.custom.SearchButton searchButton1;
    private datechooser.beans.DateChooserCombo startDateChooer;
    // End of variables declaration//GEN-END:variables
    @Override
    public WebMenuItem getPenerimaanDetailMenuItem() {
        return penerimaanDetailMenuItem;
    }

    @Override
    public WebMenuItem getPengembalianDetailMenuItem() {
        return pengembalianDetailMenuItem;
    }

    @Override
    public WebMenuItem getPenerimaDetailMenuItem() {
        return penerimaDetailMenuItem;
    }

    @Override
    public WebMenuItem getPengembaliDetailMenuItem() {
        return pengembaliDetailMenuItem;
    }

    @Override
    public WebMenuItem getDeleteMenuItem() {
        return deleteMenuItem;
    }

    @Override
    public DateChooserCombo getEndDateChooser() {
        return endDateChooser;
    }

    @Override
    public PagingTableViewImpl getPagingTableViewImpl() {
        return pagingTableViewImpl1;
    }

    @Override
    public PrintButton getPrintButton() {
        return printButton1;
    }

    @Override
    public SearchButton getSearchButton() {
        return searchButton1;
    }

    @Override
    public DateChooserCombo getStartDateChooer() {
        return startDateChooer;
    }
    
    @Override
    public Frame getRoot() {
        return this;
    }
}
