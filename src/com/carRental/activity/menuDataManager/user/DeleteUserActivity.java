package com.carRental.activity.menuDataManager.user;

import com.carRental.Session;
import com.carRental.activity.menuDataManager.UserManagerActivity;
import com.carRental.model.Karyawan;
import com.carRental.model.User;
import com.carRental.service.UserServiceImpl;
import com.dika.SystemException;
import com.dika.activity.Activity;
import com.dika.view.component.Button;
import com.dika.view.component.Dialog;
import com.dika.view.component.TextField;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class DeleteUserActivity extends Activity<DeleteUserView> implements DeleteUserView {
    private final DeleteUserView deleteUserView = new DeleteUserViewImpl();
    private User user;

    public void setUser(User user) {
        SwingUtilities.invokeLater(()-> {
            this.user = user;
            Karyawan karyawan = user.getKaryawan();
            getIdKaryawanField().setText(String.valueOf(karyawan.getId()));
            getUsernameField().setText(user.getUsername());
            getNamaField().setText(karyawan.getNama());
            getJenkelField().setText(karyawan.getJenisKelamin());
        });
    }
    
    private boolean grantedToDelete() {
        if (user == null) {
            showInfo("Tidak ada data user untuk dihapus");
            return false;
        }

        if (user.equals(Session.getInstance().getUser())) {
            showFailed("User ini sedang login, menghapus user ini tidak diijinkan");
            return false;
        }
        
        return true;
    }
    
    private void deleteUser() {
        if(!grantedToDelete()) return;
        
        execute(new UserServiceImpl(), userService -> {
            try {
                userService.destroy(user);
                Activity<?> parent = getParent();
                if (parent != null && parent instanceof UserManagerActivity) {
                    ((UserManagerActivity) parent).refresh();
                }
                showSucceed("Data user berhasil dihapus");
                stop();
            } catch (SystemException e) {
                showFailed("Data user gagal dihapus");
            }
            return userService;
        });
    }

    @Override
    protected void initListener(DeleteUserView deleteUserView) {
        getCancelButton().addActionListener(evt -> stop());
        getDeleteButton().addActionListener(evt -> deleteUser());
    }

    @NotNull
    @Override
    public DeleteUserView getView() {
        return deleteUserView;
    }

    @Override
    public TextField getIdKaryawanField() {
        return deleteUserView.getIdKaryawanField();
    }

    @Override
    public TextField getNamaField() {
        return deleteUserView.getNamaField();
    }

    @Override
    public TextField getJenkelField() {
        return deleteUserView.getJenkelField();
    }

    @Override
    public TextField getUsernameField() {
        return deleteUserView.getUsernameField();
    }

    @Override
    public Button getDeleteButton() {
        return deleteUserView.getDeleteButton();
    }

    @Override
    public Button getCancelButton() {
        return deleteUserView.getCancelButton();
    }

    @Override
    public Dialog getRoot() {
        return deleteUserView.getRoot();
    }
}
