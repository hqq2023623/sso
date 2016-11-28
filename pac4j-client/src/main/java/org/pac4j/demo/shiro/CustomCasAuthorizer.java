package org.pac4j.demo.shiro;

import org.pac4j.core.authorization.generator.AuthorizationGenerator;
import org.pac4j.core.profile.CommonProfile;

public class CustomCasAuthorizer<U extends CommonProfile> implements AuthorizationGenerator<U> {

    public static final String DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME = "longTermAuthenticationRequestTokenUsed";

    // default name of the CAS attribute for remember me authentication (CAS 3.4.10+)
    private String rememberMeAttributeName = DEFAULT_REMEMBER_ME_ATTRIBUTE_NAME;

    public CustomCasAuthorizer() {
    }

    public CustomCasAuthorizer(String rememberMeAttributeName) {
        this.rememberMeAttributeName = rememberMeAttributeName;
    }

    @Override
    public void generate(U profile) {
        profile.addRole("asdbasdb-lzj");
        profile.addPermission("xzkcjb-owqe");
    }

}