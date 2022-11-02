package com.example.demo.enumdemo.dao;

import com.example.demo.enumdemo.model.AccountBlackList;
import com.example.demo.enumdemo.model.QAccountBlackList;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountBlackListRepo extends JpaRepository<AccountBlackList, Integer>, QuerydslPredicateExecutor<AccountBlackList>,
        QuerydslBinderCustomizer<QAccountBlackList> {

    AccountBlackList findByUserId(String userId);

   /**
     * 自定义查询，所有 string 类型模糊匹配
     * @param bindings
     * @param root
     */
    @Override
    default void customize(QuerydslBindings bindings, QAccountBlackList root) {
        bindings.bind(String.class).first(
                (SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase
        );
    }
}
