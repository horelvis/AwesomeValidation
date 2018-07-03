package com.basgeekball.awesomevalidation.validators;

import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.basgeekball.awesomevalidation.R;
import com.basgeekball.awesomevalidation.ValidationHolder;
import com.basgeekball.awesomevalidation.utility.ValidationCallback;

import java.util.regex.Matcher;

public class TextInputLayoutValidator extends Validator {

    private int mErrorTextAppearance = R.style.AwesomeValidation_TextInputLayout;

    public void setErrorTextAppearance(int styleId) {
        mErrorTextAppearance = styleId;
    }

    private ValidationCallback mValidationCallback = new ValidationCallback() {
        @Override
        public void execute(ValidationHolder validationHolder, Matcher matcher) {
            TextInputLayout textInputLayout = validationHolder.getTextInputLayout();
            textInputLayout.setErrorTextAppearance(mErrorTextAppearance);
            textInputLayout.setErrorEnabled(true);
            if(validationHolder.getErrMsg() == null){
                textInputLayout.setError(" ");
                if(textInputLayout.getChildCount() >= 2) {
                    textInputLayout.getChildAt(1).setVisibility(View.GONE);
                }
            }else {
                textInputLayout.setError(validationHolder.getErrMsg());
            }
        }
    };

    @Override
    public boolean trigger() {
        halt();
        return checkFields(mValidationCallback);
    }

    @Override
    public void halt() {
        for (ValidationHolder validationHolder : mValidationHolderList) {
            if (validationHolder.isSomeSortOfView()) {
                validationHolder.resetCustomError();
            } else {
                TextInputLayout textInputLayout = validationHolder.getTextInputLayout();
                textInputLayout.setErrorEnabled(false);
                textInputLayout.setError(null);
            }
        }
    }

}
