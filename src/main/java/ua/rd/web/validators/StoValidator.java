package ua.rd.web.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.rd.web.controller.SecondTestObject;

@Component
public class StoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		System.out.println("VALIDATOR SUPPORT"+clazz);
		return clazz.equals(SecondTestObject.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("VALIDATOR");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
		
	}

}
