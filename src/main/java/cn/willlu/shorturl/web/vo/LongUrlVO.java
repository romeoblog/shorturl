package cn.willlu.shorturl.web.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author willlu.zheng
 * @date 2019/11/24
 */
@Data
public class LongUrlVO implements Serializable {

    @NotBlank
    private String longUrl;

}
