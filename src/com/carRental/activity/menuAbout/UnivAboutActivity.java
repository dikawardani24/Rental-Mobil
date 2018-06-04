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
package com.carRental.activity.menuAbout;

import com.dika.activity.Activity;
import com.dika.res.HtmlTextRes;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author dika
 */
public class UnivAboutActivity extends Activity<UnivAboutView>{
    private final UnivAboutView view = new UnivAboutView();
    
    @NotNull
    @Override
    public UnivAboutView getView() {
        return view;
    }

    @Override
    protected void initListener(UnivAboutView v) {
        view.getCancelButton().addActionListener(e -> stop());
        view.getjTextPane1().setContentType(HtmlTextRes.INSTANCE.getType());
        view.getjTextPane1().setText(HtmlTextRes.INSTANCE.getUnivAbout());
    }
    
}
