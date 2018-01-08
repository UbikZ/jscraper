package org.ubikz.jscraper.api.dal;

import org.springframework.beans.factory.annotation.Autowired;
import org.ubikz.jscraper.api.dal.model.filter.BaseDalFilter;
import org.ubikz.jscraper.api.dal.model.request.BaseDalRequest;
import org.ubikz.jscraper.database.DatabaseService;
import org.ubikz.jscraper.database.DatabaseUtil;
import org.ubikz.jscraper.database.querybuilder.impl.Select;
import org.ubikz.jscraper.database.querybuilder.parts.impl.WhereChainPart;
import org.ubikz.jscraper.database.reference.IFieldReference;
import org.ubikz.jscraper.database.reference.impl.OperatorReference;
import org.ubikz.jscraper.reference.table.TableReference;
import org.ubikz.jscraper.reference.table.field.CommonReference;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
public abstract class BaseDal<R extends BaseDalRequest, F extends BaseDalFilter> {
    protected DatabaseService ds;
    protected DatabaseUtil.Request<IFieldReference> request;
    protected TableReference table;

    @Autowired
    public BaseDal(DatabaseService ds) {
        this.ds = ds;
        this.request = new DatabaseUtil.Request<>();
    }

    public int create(R request) {
        return ds.insert(insert -> insert
                .table(table)
                .values(parseRequest(request))
                .returning(CommonReference.ID)
        );
    }

    public int edit(R request) {
        return ds.update(update -> update
                .table(table)
                .values(parseRequest(request))
                .where(w -> w.and(p -> p.set(CommonReference.ID, request.getId())))
        );
    }

    public List<Object> createAll(List<R> requestList) {
        return ds.insertMultiple(insert -> insert
                .table(table)
                .values(parseRequestList(requestList))
                .onConflict()
                .onDoNothing()
                .returning(CommonReference.ID)
        );
    }

    public int delete(F filter) {
        return ds.delete(delete -> delete
                .from(table)
                .where(w -> w.and(p -> p.set(CommonReference.ID, filter.getId())))
        );
    }

    public int count(F filter) {
        return ds.count(s -> s = getBaseSelect(filter, true));
    }

    public List<Map<String, Object>> getAll(F filter) {
        return ds.find(s -> s = getBaseSelect(filter));
    }

    public Map<String, Object> getOne(F filter) {
        return ds.findOne(s -> s = getBaseSelect(filter));
    }

    protected List<Map<IFieldReference, Object>> parseRequestList(List<R> requestList) {
        return requestList.stream().map(this::parseRequest).collect(Collectors.toList());
    }

    protected Map<IFieldReference, Object> parseRequest(R request) {
        return this.request.setDefault()
                .add(CommonReference.ID, request.getId())
                .add(CommonReference.ENABLED, request.getEnabled())
                .get();
    }

    protected void parseFilter(F filter, Select select) {
        parseFilter(filter, select, false);
    }

    protected void parseFilter(F filter, Select select, boolean isCount) {
        WhereChainPart whereClauses = new WhereChainPart();

        if (filter.getId() != null) {
            whereClauses.and(w -> w.set(CommonReference.ID, filter.getId()));
        }

        if (filter.getIdList() != null && filter.getIdList().size() > 0) {
            whereClauses.and(w -> w.set(CommonReference.ID, OperatorReference.IN, filter.getIdList()));
        }

        if (filter.getEnabled() != null) {
            whereClauses.and(w -> w.set(CommonReference.ENABLED, filter.getEnabled()));
        }

        if (filter.getStartDate() != null) {
            whereClauses.and(w -> w.set(CommonReference.DATE, OperatorReference.GTE, new Timestamp(filter.getStartDate().getTime())));
        }

        if (filter.getEndDate() != null) {
            whereClauses.and(w -> w.set(CommonReference.DATE, OperatorReference.LTE, new Timestamp(filter.getEndDate().getTime() + (59 + 59 * 60 + 23 * 3600) * 1000)));
        }

        if (!isCount) {
            if (filter.getLimit() != null) {
                select.limit(filter.getLimit());
            }

            if (filter.getOffset() != null) {
                select.offset(filter.getOffset());
            }
        }

        select.where(w -> w = whereClauses);
    }

    protected Select getBaseSelect(F filter) {
        return this.getBaseSelect(filter, false);
    }

    protected Select getBaseSelect(F filter, boolean isCount) {
        Select select = new Select();
        select.from(table);

        if (isCount) {
            select.column("COUNT(DISTINCT id)");
        }

        parseFilter(filter, select, isCount);

        return select;
    }
}
