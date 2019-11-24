package cn.willlu.shorturl.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author willlu.zheng
 * @date 2019/11/24
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "tb_short_url")
public class TbShortUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String longUrl;
    private String shortUrl;
    private Integer type;
    private Integer status;
    private Date updateAt;
    private Date createAt;
    private Integer deleted;
    private Integer version;
}
