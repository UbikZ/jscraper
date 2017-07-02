package com.ubikz.scraper.core.app.dal;

import com.ubikz.scraper.core.app.dal.filter.AbstractDalFilter;
import com.ubikz.scraper.core.app.dal.filter.UserDalFilter;
import com.ubikz.scraper.core.app.dal.request.AbstractDalRequest;
import com.ubikz.scraper.core.app.dal.request.UserDalRequest;
import com.ubikz.scraper.core.provider.db.DBWrapper;
import com.ubikz.scraper.core.provider.db.qb.AbstractQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Repository
public class UserDal extends AbstractDal {
    protected final Logger logger = LoggerFactory.getLogger(UserDal.class);

    /**
     * @param dbWrapper
     */
    public UserDal(DBWrapper dbWrapper) {
        super(dbWrapper);
        this.tableName = "public.user";
    }

    /**
     * @param request
     * @param created
     * @return
     */
    @Override
    protected Map<String, Object> parseRequest(AbstractDalRequest request, boolean created) {
        UserDalRequest userDalRequest = (UserDalRequest) request;
        Map<String, Object> values = super.parseRequest(userDalRequest, created);

        if (userDalRequest.getUsername() != null) {
            values.put("username", userDalRequest.getUsername());
        }

        if (userDalRequest.getEmail() != null) {
            values.put("email", userDalRequest.getEmail());
        }

        if (userDalRequest.getPassword() != null) {
            try {
                values.put("password", this.hashPassword(userDalRequest.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                logger.error("Hash of password failed : " + e);
            }
        }

        if (userDalRequest.getFirstname() != null) {
            values.put("firstname", userDalRequest.getFirstname());
        }

        if (userDalRequest.getLastname() != null) {
            values.put("lastname", userDalRequest.getLastname());
        }

        if (userDalRequest.getAdmin() != null) {
            values.put("is_admin", userDalRequest.getAdmin());
        }

        return values;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    protected void parseFilter(AbstractDalFilter filter, AbstractQuery select, boolean isCount) {
        UserDalFilter userDalFilter = (UserDalFilter) filter;
        super.parseFilter(userDalFilter, select, isCount);

        if (userDalFilter.getUsername() != null) {
            select.where("username", userDalFilter.getUsername());
        }

        if (userDalFilter.getEmail() != null) {
            select.where("email", userDalFilter.getEmail());
        }

        if (userDalFilter.getPassword() != null) {
            String password = null;
            try {
                password = this.hashPassword(userDalFilter.getPassword());
            } catch (NoSuchAlgorithmException e) {
                logger.error("Hash of password failed : " + e);
            } finally {
                select.where("password", password);
            }
        }
    }

    /**
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private final String hashPassword(String password) throws NoSuchAlgorithmException {
        return this.bytesToHex(MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * @param hash
     * @return
     */
    private final String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}