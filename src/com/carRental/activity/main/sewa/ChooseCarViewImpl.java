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
package com.carRental.activity.main.sewa;

import com.alee.laf.menu.WebMenuItem;
import com.dika.res.SmallIconRes;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.custom.PagingTableView;

import javax.swing.*;

/**
 *
 * @author dika
 */
public class ChooseCarViewImpl extends Dialog implements ChooseCarView {

    /**
     * Creates new form ChooseCarViewImpl
     */
    public ChooseCarViewImpl() {
        super();
        initComponents(); 
        createContextMenuOnTable();
    }
    
    private void createContextMenuOnTable() {
        JPopupMenu tableDataMenu = new JPopupMenu("Table Menu");
        tableDataMenu.add(pilihMenuItem);
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
        pagingTableViewImpl1 = new com.dika.view.custom.PagingTableViewImpl();
        com.dika.view.component.custom.HeaderLabel headerLabel1 = new com.dika.view.component.custom.HeaderLabel();
        cancelButton1 = new com.dika.view.component.custom.CancelButton();

        headerLabel1.setText("Pilih Mobil");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pagingTableViewImpl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headerLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pagingTableViewImpl1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
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
    
    private final WebMenuItem pilihMenuItem = new WebMenuItem("Pilih Mobil", SmallIconRes.INSTANCE.getConfirmIcon());
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.dika.view.component.custom.CancelButton cancelButton1;
    private com.dika.view.custom.PagingTableViewImpl pagingTableViewImpl1;
    // End of variables declaration//GEN-END:variables

    @Override
    public WebMenuItem getChooseCarMenuItem() {
        return pilihMenuItem;
    }

    @Override
    public PagingTableView getPagingTableView() {
        return pagingTableViewImpl1;
    }

    @Override
    public Button getCancelButton() {
        return cancelButton1;
    }

    @Override
    public Dialog getRoot() {
        return this;
    }
}
