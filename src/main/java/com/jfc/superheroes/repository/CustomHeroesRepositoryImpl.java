package com.jfc.superheroes.repository;

import com.jfc.superheroes.dtos.HeroDto;
import com.jfc.superheroes.entities.HeroesEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@SuppressWarnings("unchecked")
@Repository
public class CustomHeroesRepositoryImpl extends AbstractCustomRepository implements CustomHeroesRepository
{
    @PersistenceContext
    private EntityManager entityManager;

    private StringBuilder getSqlFilter( String heroName, String firstName, String lastName, String power )
    {
        StringBuilder sqlFilter = new StringBuilder();

        if ( heroName != null )
            sqlFilter.append( " AND hr.hero_name LIKE concat('%', :heroName, '%')" );

        if ( firstName != null )
            sqlFilter.append( " AND hr.first_name LIKE concat('%', :firstName, '%')" );

        if ( lastName != null )
            sqlFilter.append( " AND hr.last_name LIKE concat('%', :lastName, '%')" );

        if ( power != null )
            sqlFilter.append( " AND hr.power LIKE concat('%', :power, '%')" );

        return sqlFilter;
    }

    private long getTotal( HeroDto filter)
    {
        StringBuilder sql = getSqlCount().append( getSqlFilter(
                filter.getHeroName(),
                filter.getFirstName(),
                filter.getLastName(),
                filter.getPower()
        ));

        Query query = entityManager.createNativeQuery( sql.toString(), Long.class );
        super.setParameter( query, "heroName", filter.getHeroName() );
        super.setParameter( query, "firstName", filter.getFirstName() );
        super.setParameter( query, "lastName", filter.getLastName() );
        super.setParameter( query, "power", filter.getPower() );

        return ( Long ) query.getSingleResult();
    }

    private StringBuilder getSqlCount()
    {
        return new StringBuilder().append(
                """
                SELECT
                    COUNT( 1 ) 
                FROM
                    heroes as hr
                WHERE
                    1 = 1                         
                """);
    }

    private StringBuilder getSqlQuery()
    {
        return new StringBuilder().append(
        """
        SELECT
           hr.id,
           hr.hero_name,
           hr.first_name,
           hr.last_name,
           hr.power,
           hr.created_at,
           hr.updated_at
        FROM
            heroes as hr
        WHERE
            1 = 1                         
        """);

    }

    @Override
    public Page<HeroesEntity> findAllHeroes(HeroDto filter, Pageable pageable )
    {
        long total = getTotal( filter );

        StringBuilder sql = getSqlQuery().append( getSqlFilter(
                filter.getHeroName(),
                filter.getFirstName(),
                filter.getLastName(),
                filter.getPower()
        ));

        super.setOrderBy( sql, pageable, HeroesEntity.class, "created_at DESC" );

        Query query = entityManager.createNativeQuery( sql.toString(), HeroesEntity.class );
        super.setOffsetAndLimit( query, pageable );
        super.setParameter( query, "heroName", filter.getHeroName() );
        super.setParameter( query, "firstName", filter.getFirstName() );
        super.setParameter( query, "lastName", filter.getLastName() );
        super.setParameter( query, "power", filter.getPower() );

        List<HeroesEntity> content = query.getResultList();

        return new PageImpl<>( content, pageable, total );

    }

}
