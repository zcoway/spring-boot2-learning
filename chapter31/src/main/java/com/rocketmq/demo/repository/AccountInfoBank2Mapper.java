package com.rocketmq.demo.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rocketmq.demo.model.dataobject.AccountInfoDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ganguowei
 * @since 2020-04-09
 */
public interface AccountInfoBank2Mapper extends BaseMapper<AccountInfoDO> {
    @Update("update account_info2 set account_balance=account_balance+#{amount} where account_no=#{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") Double amount);

    @Select("select count(1) from de_duplication2 where tx_no = #{txNo}")
    int isExistTx(String txNo);

    @Insert("insert into de_duplication2 values(#{txNo},now());")
    int addTx(String txNo);
}
