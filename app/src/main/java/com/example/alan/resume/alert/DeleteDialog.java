package com.example.alan.resume.alert;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.alan.resume.R;
import com.example.alan.resume.util.DimenUtil;

/**
 * Function :
 * Modify Date : 2018/2/9
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class DeleteDialog {


    static int LOADER_SIZE_SCALE = 8;

    private static AppCompatDialog dialog;
    private static TextView mTextCancel;
    private static TextView mTextSure;
    private static long position;

    public static long getPosition() {
        return position;
    }

    public static void setPosition(long position) {
        DeleteDialog.position = position;
    }

    public static IDeleteItemBack getDeleteItemBack() {
        return deleteItemBack;
    }

    public static void setDeleteItemBack(IDeleteItemBack deleteItemBack) {
        DeleteDialog.deleteItemBack = deleteItemBack;
    }

    private static IDeleteItemBack deleteItemBack;


    public static void createLoading(Context context) {

        dialog = new AppCompatDialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_delete_item, null);
        dialog.setContentView(view);

        mTextSure = view.findViewById(R.id.tv_dialog_sure);
        mTextCancel = view.findViewById(R.id.tv_dialog_cancel);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//            lp.width = deviceWidth / 2;
//            lp.height = deviceHeight / 3;
            lp.gravity = Gravity.CENTER;
        }

        mTextSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteItemBack!=null){
                    deleteItemBack.delete(getPosition());
                }
                hideDialog();
            }
        });

        mTextCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideDialog();
            }
        });
    }


    public static void showDialog() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public static void hideDialog() {
        if (dialog != null) {
            dialog.hide();
        }
    }
}
