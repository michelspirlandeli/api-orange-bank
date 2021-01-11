package br.com.orange.bank.person.validation;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CpfOrCnpjValidation implements ConstraintValidator<CpfOrCnpj, String> {

    @Override
    public boolean isValid(String CpfOrCnpj, ConstraintValidatorContext constraintValidatorContext) {
        if (StringUtils.hasLength(CpfOrCnpj)) {
            CPFValidator cpf = new CPFValidator();
            cpf.initialize(null);

            CNPJValidator cnpj = new CNPJValidator();
            cnpj.initialize(null);

            return cpf.isValid(CpfOrCnpj, constraintValidatorContext)
                    || cnpj.isValid(CpfOrCnpj, constraintValidatorContext);
        }
        return true;
    }

}
