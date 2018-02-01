package com.example.alan.resume.base;

/**
 * Function :
 * Modify Date : 2018/2/1
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public abstract class ResumeDelegate extends PermissionCheckerDelegate {

    public <T extends ResumeDelegate> T getParent(){
        return (T)getParentFragment();
    }
}
