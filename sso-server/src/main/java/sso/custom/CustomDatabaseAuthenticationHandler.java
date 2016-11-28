package sso.custom;

import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.principal.Principal;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * 自定义数据认证
 * Created by lzj on 2016/11/23.
 */
public class CustomDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    @NotNull
    private String sql;

    @Override
    protected final Principal authenticateUsernamePasswordInternal(final String username, final String password)
            throws GeneralSecurityException, PreventedException {
        try {
            final String encryptedPassword = CustomPasswordEncoder.strToMD5(password + username);
            final String dbPassword = getJdbcTemplate().queryForObject(this.sql, String.class, username);
            if (!dbPassword.equals(encryptedPassword)) {
                throw new FailedLoginException("Password does not match value on record.");
            }
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {
            throw new PreventedException("SQL exception while executing query for " + username, e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new SimplePrincipal(username);
    }

    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }

    public static void main(String[] args) throws Exception {
        //C7504681C2642A69
        System.out.println(CustomPasswordEncoder.strToMD5("lzjm123"));

    }

}
