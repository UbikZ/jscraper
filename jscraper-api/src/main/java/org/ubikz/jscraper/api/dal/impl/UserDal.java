package org.ubikz.jscraper.api.dal.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.AbstractDalFilter;
import org.ubikz.jscraper.api.dal.model.filter.impl.UserDalFilter;
import org.ubikz.jscraper.api.dal.model.request.AbstractDalRequest;
import org.ubikz.jscraper.api.dal.model.request.impl.UserDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.AbstractQuery;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Repository
public class UserDal extends BaseDal {
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_IS_ADMIN = "is_admin";
    protected final Logger logger = LoggerFactory.getLogger(UserDal.class);

    /**
     * @param databaseService
     */
    public UserDal(DatabaseService databaseService) {
        super(databaseService);
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
            values.put(COLUMN_USERNAME, userDalRequest.getUsername());
        }

        if (userDalRequest.getEmail() != null) {
            values.put(COLUMN_EMAIL, userDalRequest.getEmail());
        }

        if (userDalRequest.getPassword() != null) {
            try {
                values.put(COLUMN_PASSWORD, this.hashPassword(userDalRequest.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                logger.error(String.format("Request : Hash of password failed : %s", e));
            }
        }

        if (userDalRequest.getFirstname() != null) {
            values.put(COLUMN_FIRSTNAME, userDalRequest.getFirstname());
        }

        if (userDalRequest.getLastname() != null) {
            values.put(COLUMN_LASTNAME, userDalRequest.getLastname());
        }

        if (userDalRequest.getAdmin() != null) {
            values.put(COLUMN_IS_ADMIN, userDalRequest.getAdmin());
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
            select.where(COLUMN_USERNAME, userDalFilter.getUsername());
        }

        if (userDalFilter.getEmail() != null) {
            select.where(COLUMN_EMAIL, userDalFilter.getEmail());
        }

        if (userDalFilter.getPassword() != null) {
            String password = null;
            try {
                password = this.hashPassword(userDalFilter.getPassword());
            } catch (NoSuchAlgorithmException e) {
                logger.error(String.format("Filter : Hash of password failed : %s", e));
            } finally {
                select.where(COLUMN_PASSWORD, password);
            }
        }
    }

    /**
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String hashPassword(String password) throws NoSuchAlgorithmException {
        return this.bytesToHex(MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * @param hash
     * @return
     */
    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}