package com.webapp.TextBook.validation.loginUserInfoValidation;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.regex.Pattern;
import com.webapp.TextBook.viewModel.loginUserInfo.LoginUserInfo;
import org.springframework.web.servlet.tags.BindErrorsTag;

public class LoginUserInfoValidatorImpl implements ConstraintValidator<LogInUserInfoValidationInterface, LoginUserInfo>{
    private static final Pattern STUDENT_ID_PATTERN= Pattern.compile("^919[0-9]{6}$");
    private static final Pattern S_NUMBER_PREFIX = Pattern.compile("^[s,S][0-9]{6}(@(.*)|)$");
    private static final Pattern S_NUMBER_SUFFIX= Pattern.compile("^.{7}@.*$");
    private boolean haveSuffix;

    @Override
    public void initialize(LogInUserInfoValidationInterface info){
            this.haveSuffix = info.haveSuffix();

    }
    @Override
    public boolean isValid(LoginUserInfo user, ConstraintValidatorContext constraintContext){


        if(LoginUserInfo.username.matches(STUDENT_ID_PATTERN.toString())){
            //add to list
        }
        if(LoginUserInfo.password.matches((S_NUMBER_PREFIX).toString())){
            //add to list
        }
       else{
           boolean suffixFalied = !LoginUserInfo.password.matches(S_NUMBER_SUFFIX.toString());
           //If it dosen't match then it should be true, because it failed
           if(suffixFalied && haveSuffix){
               //add to list
           }
           else if(!suffixFalied && !haveSuffix){
               //Splices out @nwmissouri.edu
               //TODO Nick check this logic. It dosen't make much sense to splice out if it dosen't have a suffix
               LoginUserInfo.password = LoginUserInfo.password.substring(0,LoginUserInfo.password.indexOf('@'));

           }

        }
        //Check binding error list is empty and do some stuff
        return false; //stub method to make everyhing happy for now
    }


}
