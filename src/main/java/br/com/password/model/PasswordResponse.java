package br.com.password.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PasswordResponse {

    private Boolean checkedPassword;

    private ArrayList<String> errorPassword = new ArrayList<>();

    public Boolean getCheckedPassword() {
        return checkedPassword;
    }

    public void setCheckedPassword(Boolean checkedPassword) {
        this.checkedPassword = checkedPassword;
    }

    public ArrayList<String> getErrorPassword() {
        return errorPassword;
    }

    public PasswordResponse() {
    }

    public PasswordResponse(Boolean checkedPassword) {
        this.checkedPassword = checkedPassword;
    }

    public PasswordResponse checkResponse() {
        this.setCheckedPassword(this.getErrorPassword().isEmpty());
        return this;
    }

    @Override
    public String toString() {
        return "PasswordResponse{" +
                "checkedPassword=" + checkedPassword +
                ", errorPassword=" + errorPassword +
                '}';
    }
}
