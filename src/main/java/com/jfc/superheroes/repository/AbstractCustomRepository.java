package com.jfc.superheroes.repository;

import com.jfc.superheroes.utils.Utils;
import jakarta.persistence.Column;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;

public abstract class AbstractCustomRepository
{
    protected Logger log = LoggerFactory.getLogger( getClass() );


    protected String getColumnFromProperty(String property, Class<?> entityClass)
    {
        try
        {
            Field field = entityClass.getDeclaredField( property );
            Column columnAnnotation = field.getDeclaredAnnotation( Column.class );

            return Utils.isNullOrEmpty(columnAnnotation.name())
                    ? property
                    : columnAnnotation.name();
        }
        catch (Exception e)
        {
            log.error("{}: {}", Utils.getCurrentMethodName(), e.toString());
            return null;
        }
    }

    protected void setOrderBy(StringBuilder sql, Pageable pageable, Class<?> entityClass, String orderByDefault)
    {
        StringBuilder sb = new StringBuilder();

        if (!Utils.isNullOrEmpty(pageable))
        {
            if (!Utils.isNullOrEmpty(pageable.getSort()))
            {
                int i=0;
                for (Sort.Order order : pageable.getSort())
                {
                    String column = getColumnFromProperty(order.getProperty(), entityClass);

                    if (column == null)
                        continue;

                    if (i++ == 0)
                        sb.append( " ORDER BY " );
                    else
                        sb.append(", ");

                    sb.append(column).append(" ").append(order.getDirection());
                }
            }
        }

        if (sb.isEmpty())
        {
            if (!Utils.isNullOrEmpty(orderByDefault))
            {
                sb.append( " ORDER BY " );
                sb.append( orderByDefault );
            }
        }

        sql.append( sb );
    }

    protected void setOrderBy(StringBuilder sql, Pageable pageable, Class<?> entityClass)
    {
        setOrderBy(sql, pageable, entityClass, null);
    }

    protected void setParameter(Query query, String paramName, Object paramValue)
    {
        if (paramName != null && paramValue != null)
            query.setParameter(paramName, paramValue);
    }


    protected void setOffsetAndLimit(Query query, Pageable pageable)
    {
        if (Utils.isNullOrEmpty(pageable))
            return;

        query.setFirstResult( (int)pageable.getOffset() );
        query.setMaxResults ( pageable.getPageSize() );
    }

}