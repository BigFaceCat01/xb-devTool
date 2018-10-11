package xb.dev.tools.dao.entity;

import javax.persistence.*;

/**
 * @author Created by huangxb
 * @date 2018-10-11 15:05:45
 */
@Entity
@Table(name = "xb_user", schema = "test", catalog = "")
public class JpaUserEntity {
    /**
     * 新闻id
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Basic
    @Column(name = "user_name")
    private String userName;
    /**
     * 新闻标题
     */
    @Basic
    @Column(name = "create_time")
    private String createTime;
}
