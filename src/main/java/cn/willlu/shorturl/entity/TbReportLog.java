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
@Table(name = "tb_report_log")
public class TbReportLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;
    private String url;
    private Integer pv;
    private Integer uv;
    private Date updateAt;
    private Date createAt;
    private Integer deleted;
    private Integer version;
    private String userAgent;
    private String method;

}
