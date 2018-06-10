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
package com.carRental;

import com.carRental.activity.menuProgram.LoginActivity;
import com.dika.Logger;
import kotlin.Unit;

/**
 *
 * @author dika
 */
class App {
    
    public static void main(String [] args) {
        com.dika.System.INSTANCE.boot((com.dika.System system) -> {
            system.setCompanyName("");
            system.setAddressCompanty("");
            system.setPersitenceName("Rental_MobilPU");
            system.setAllowMultipleInstance(false);
            system.setLoggerType(Logger.LoggerType.FULL_VERBOSE);
            system.setFirstActivity(LoginActivity.class);
            return Unit.INSTANCE;
        });
    }
}
