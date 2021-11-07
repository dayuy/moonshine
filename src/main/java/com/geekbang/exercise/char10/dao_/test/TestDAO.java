package com.geekbang.exercise.char10.dao_.test;

import com.geekbang.exercise.char10.dao_.dao.ActorDao_;
import com.geekbang.exercise.char10.dao_.domain.Actor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {
    @Test
    public void testActorDao() {
        ActorDao_ actorDao_ = new ActorDao_();
        List<Actor> actors = actorDao_.queryMulti("select * from actress where user_name = ?", Actor.class, "mary");
        System.out.println("=====查询结果====");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        Actor actor = actorDao_.querySingle("select * from actress where user_name = ?", Actor.class, "mary");
        System.out.println("=====查询单个结果====");
        System.out.println(actor);

        Object o = actorDao_.queryScalar("select phone from actress where user_name = ?", "mary");
        System.out.println("====查询单行单列=====");
        System.out.println(o);

        int updateRow = actorDao_.update("update actress set phone = ? where user_name = ?", "136800000", "mary");
        System.out.println(updateRow > 0 ? "修改成功" : "没有改动");
    }

}
