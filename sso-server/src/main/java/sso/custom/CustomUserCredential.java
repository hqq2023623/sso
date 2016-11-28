package sso.custom;

import org.jasig.cas.authentication.UsernamePasswordCredential;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 验证码
 * Created by lzj on 2016/11/23.
 */
public class CustomUserCredential extends UsernamePasswordCredential {
    private static final long serialVersionUID = -2988130322912201986L;

    @NotNull
    @Size(min = 1, message = "required.captcha")
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
