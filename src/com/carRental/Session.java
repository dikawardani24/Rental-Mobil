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

import com.carRental.model.Karyawan;
import com.carRental.model.User;

/**
 *
 * @author dika
 */
public class Session {
    private User user;

    private Session() {
    }

    public static Session getInstance() {
        return SessionHolder.INSTANCE;
    }

    private static class SessionHolder {

        private static final Session INSTANCE = new Session();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Karyawan getKaryawan() {
        return getUser().getKaryawan();
    }
}
