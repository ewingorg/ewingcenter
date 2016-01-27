package business.product.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import business.ddl.WebResourcePrice;
import business.product.dao.WebResourcePriceDao;
import business.product.dto.ProductPriceDto;

/**
 * 资源价格服务类
 * 
 * @author tansonlam
 * @createDate 2016年1月27日
 * 
 */
public class WebResourcePriceService {
    /**
     * 查找指定资源的价格设置
     * 
     * @param resourceId
     * @return
     */
    public static List<ProductPriceDto> findByResourceId(Integer resourceId) {
        List<WebResourcePrice> list = WebResourcePriceDao.findByResourceId(resourceId);
        List<ProductPriceDto> dtoList = new ArrayList<ProductPriceDto>();
        for (WebResourcePrice model : list) {
            ProductPriceDto dto = new ProductPriceDto();
            try {
                BeanUtils.copyProperties(dto, model);
                dtoList.add(dto);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dtoList;
    }
}
