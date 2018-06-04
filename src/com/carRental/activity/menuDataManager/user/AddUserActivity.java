package com.carRental.activity.menuDataManager.user;

import com.carRental.activity.menuDataManager.UserManagerActivity;
import com.carRental.model.Karyawan;
import com.carRental.model.User;
import com.carRental.service.KaryawanServiceImpl;
import com.carRental.service.UserServiceImpl;
import com.dika.SystemException;
import com.dika.activity.Activity;
import com.dika.activity.InputActivity;
import com.dika.security.MD5Encryption;
import com.dika.util.NumberHelper;
import com.dika.view.component.*;

import org.jetbrains.annotations.NotNull;

public final class AddUserActivity extends InputActivity<AddUserView> implements AddUserView {
    private final AddUserView addUserView = new AddUserViewImpl();
    private Karyawan karyawan;

    private void saveNewUser() {
        if (!validateInput()) {
            return;
        }

        User user = new User();
        user.setPassword(encryptPassword());
        user.setUsername(getUsernameField().getText());
        user.setKaryawan(karyawan);
        execute(new UserServiceImpl(), userService -> {
            try {
                userService.create(user);
                Activity<?> parent = getParent();
                if (parent != null && parent instanceof UserManagerActivity) {
                    ((UserManagerActivity) parent).refresh();
                }
                clear();
                showSucceed("Data user berhasil disimpan");
            } catch (SystemException e) {
                showFailed("Data user gagal disimpan");
            }

            return userService;
        });
    }

    private void findKaryawan() {
        Integer id = NumberHelper.INSTANCE.toInt(getIdKaryawanField().getText());

        if (id <= 0) {
            showInfo("ID Karyawan yang anda input tidak valid");
            return;
        }

        execute(new KaryawanServiceImpl(), karyawanService -> {
            try {
                karyawan =  karyawanService.findBy(id);
                getNamaField().setText(karyawan.getNama());
                getJenkelField().setText(karyawan.getJenisKelamin());
            } catch (SystemException e) {
                showFailed("Tidak menemukan karyawan dengan id : "+id);
            }

            return karyawanService;
        });
    }

    private String encryptPassword() {
        String password = new String(getPasswordField().getPassword());
        MD5Encryption encryption = new MD5Encryption();
        return encryption.secure(password);
    }

    private boolean isKaryawanHasAccount() {
        return execute(new UserServiceImpl(), userService -> {
            try {
                userService.findBy(karyawan);
                return true;
            } catch (SystemException e) {
                return false;
            }
        });
    }

    private boolean isUsernameExist() {
        String username = getUsernameField().getText();
        return execute(new UserServiceImpl(), userService -> {
            try {
                userService.findBy(username);
                return true;
            } catch (SystemException e) {
                return false;
            }
        });
    }

    private boolean isPasswordMatched() {
        String password = new String(getPasswordField().getPassword());
        String retypePassword = new String(getRetypePasswordField().getPassword());
        return password.equals(retypePassword);
    }

    @Override
    protected boolean validateInput() {
        if (!super.validateInput()) return false;

        if (karyawan == null) {
            showNotifOn(getSearchButton(), "Tekan tombol untuk mencari data karyawan");
            return false;
        }

        if (isKaryawanHasAccount()) {
            showFailed("Karyawan ini sudah terdaftar sebagai user");
            return false;
        }

        if (isUsernameExist()) {
            showInfo("Username yang anda gunakan sudah tidak digunakan orang lain");
            return false;
        }

        if (!isPasswordMatched()) {
            showNotifOn(getRetypePasswordField(), "Ketikkan ulang password tidak cocok");
            return false;
        }

        return true;
    }

    @Override
    protected void initListener(AddUserView addUserView) {
        getSearchButton().addActionListener(evt -> findKaryawan());
        getCancelButton().addActionListener(evt -> stop());
        getClearButton().addActionListener(evt -> clear());
        getSaveButton().addActionListener(evt -> saveNewUser());
        
        getShowPasswordCheckBox().addActionListener(evt -> {
            boolean show = getShowPasswordCheckBox().isSelected();
            getPasswordField().showPassword(show);
            getRetypePasswordField().showPassword(show);
        });
    }

    @NotNull
    @Override
    public AddUserView getView() {
        return addUserView;
    }

    @Override
    public TextField getIdKaryawanField() {
        return addUserView.getIdKaryawanField();
    }

    @Override
    public TextField getNamaField() {
        return addUserView.getNamaField();
    }

    @Override
    public TextField getJenkelField() {
        return addUserView.getJenkelField();
    }

    @Override
    public TextField getUsernameField() {
        return addUserView.getUsernameField();
    }

    @Override
    public PasswordField getPasswordField() {
        return addUserView.getPasswordField();
    }

    @Override
    public PasswordField getRetypePasswordField() {
        return addUserView.getRetypePasswordField();
    }

    @Override
    public CheckBox getShowPasswordCheckBox() {
        return addUserView.getShowPasswordCheckBox();
    }

    @Override
    public Button getSearchButton() {
        return addUserView.getSearchButton();
    }

    @Override
    public Button getClearButton() {
        return addUserView.getClearButton();
    }

    @Override
    public Button getCancelButton() {
        return addUserView.getCancelButton();
    }

    @Override
    public Button getSaveButton() {
        return addUserView.getSaveButton();
    }

    @Override
    public Dialog getRoot() {
        return addUserView.getRoot();
    }
}
