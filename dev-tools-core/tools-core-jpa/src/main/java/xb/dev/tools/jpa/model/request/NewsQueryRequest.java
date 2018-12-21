package xb.dev.tools.jpa.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-21 17:48:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NewsQueryRequest extends PageRequest implements Serializable {
}
