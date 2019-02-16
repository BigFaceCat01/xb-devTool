package ${content.packageName};

<#list content.imports as import>
import ${import};
</#list>


/**
 * @author Created by ${content.author}
 * @date ${content.createTime?string("yyyy-MM-dd HH:mm:ss")}
 */
@FeignClient(value = "${content.service.name}",fallback = ${content.service.fallback}.class)
public interface EsNewsClient {

}