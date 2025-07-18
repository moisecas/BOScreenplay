package com.tuorg.actors;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.Cast;

public class ActorBase {
    public static final Cast THE_CAST = Cast.ofStandardActors();
    public static Actor named(String name) {
        return THE_CAST.actorNamed(name);
    }
}
