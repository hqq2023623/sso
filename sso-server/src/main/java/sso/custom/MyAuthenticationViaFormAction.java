package sso.custom;

import org.jasig.cas.authentication.Credential;
import org.jasig.cas.web.flow.AuthenticationViaFormAction;
import org.jasig.cas.web.support.WebUtils;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.util.StringUtils;
import org.springframework.webflow.execution.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MyAuthenticationViaFormAction extends AuthenticationViaFormAction {

    public final String validatorCaptcha(final RequestContext context, final Credential credential, final MessageContext messageContext) {
        final HttpServletRequest request = WebUtils.getHttpServletRequest(context);
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute("verifyCode");
        session.removeAttribute("verifyCode");

        CustomUserCredential upc = (CustomUserCredential) credential;
        String submitAuthcodeCaptcha = upc.getCaptcha();

        if (!StringUtils.hasText(submitAuthcodeCaptcha) || !StringUtils.hasText(submitAuthcodeCaptcha)) {
            messageContext.addMessage(new MessageBuilder().error().code("required.captcha").build());
            return "error";
        }
        if (submitAuthcodeCaptcha.equalsIgnoreCase(captcha)) {
            return "success";
        }
        messageContext.addMessage(new MessageBuilder().error().code("error.authentication.captcha.bad").build());
        return "error";
    }
}