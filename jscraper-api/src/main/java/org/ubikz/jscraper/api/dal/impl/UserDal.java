package org.ubikz.jscraper.api.dal.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.ubikz.jscraper.api.dal.BaseDal;
import org.ubikz.jscraper.api.dal.model.filter.impl.UserDalFilter;
import org.ubikz.jscraper.api.dal.model.request.impl.UserDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.UserReference;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class UserDal extends BaseDal<UserDalRequest, UserDalFilter> {
    private final Logger logger = LoggerFactory.getLogger(UserDal.class);

    public UserDal(DatabaseService databaseService) {
        super(databaseService);
        this.table = TableReference.USER;
    }

    @Override
    protected Map<IFieldReference, Object> parseRequest(UserDalRequest request) {
        Map<IFieldReference, Object> values = super.parseRequest(request);

        if (request.getUsername() != null) {
            values.put(UserReference.USERNAME, request.getUsername());
        }

        if (request.getEmail() != null) {
            values.put(UserReference.EMAIL, request.getEmail());
        }

        if (request.getPassword() != null) {
            try {
                values.put(UserReference.PASSWORD, hashPassword(request.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                logger.error(String.format("Request : Hash of password failed : %s", e));
            }
        }

        if (request.getFirstname() != null) {
            values.put(UserReference.FIRSTNAME, request.getFirstname());
        }

        if (request.getLastname() != null) {
            values.put(UserReference.LASTNAME, request.getLastname());
        }

        if (request.getAdmin() != null) {
            values.put(UserReference.IS_ADMIN, request.getAdmin());
        }

        return values;
    }

    @Override
    protected void parseFilter(UserDalFilter filter, Select select, boolean isCount) {
        super.parseFilter(filter, select, isCount);

        if (filter.getUsername() != null) {
            select.where(w -> w.and(p -> p.set(UserReference.USERNAME, filter.getUsername())));
        }

        if (filter.getEmail() != null) {
            select.where(w -> w.and(p -> p.set(UserReference.EMAIL, filter.getEmail())));
        }

        if (filter.getPassword() != null) {
            AtomicReference<String> password = new AtomicReference<>();
            try {
                password.set(hashPassword(filter.getPassword()));
            } catch (NoSuchAlgorithmException e) {
                logger.error(String.format("Filter : Hash of password failed : %s", e));
            } finally {
                select.where(w -> w.and(p -> p.set(UserReference.PASSWORD, password.get())));
            }
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        return bytesToHex(MessageDigest.getInstance("SHA-256").digest(password.getBytes(StandardCharsets.UTF_8)));
    }

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
