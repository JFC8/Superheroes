package com.jfc.superheroes.exceptions;

import com.jfc.superheroes.utils.exceptions.ObjectNotFoundException;

public class HeroNotFoundException extends ObjectNotFoundException
{
    public static final String ERROR_CODE = "hero_not_found";

    public HeroNotFoundException(String id)
    {
        super( ERROR_CODE, "hero", id );
    }
}
