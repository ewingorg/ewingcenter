package business.product.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import business.ddl.WebResourceSpec;
import business.product.dao.WebResourceSpecDao;
import business.product.dto.ProductSpec;
import business.product.dto.ProductSpecComparatorUtil;
import business.product.dto.ProductSpecGroup;

/**
 * 资源规格服务类
 * 
 * @author tansonlam
 * @createDate 2016年1月27日
 * 
 */
public class WebResourceSpecService {
    /**
     * 获取配置的资源規格,以规格组的结构返回
     * 
     * @param resourceId
     * @return
     */
    public static List<ProductSpecGroup> getConfigureSpecs(Integer resourceId) {
        List<ProductSpecGroup> groupList = new ArrayList<ProductSpecGroup>();
        List<WebResourceSpec> specList = WebResourceSpecDao.getConfigureSpecs(resourceId);
        for (WebResourceSpec spec : specList) {
            if (!StringUtils.isEmpty(spec.rootSpec))
                continue;
            ProductSpec rootSpecDto = new ProductSpec();
            try {
                BeanUtils.copyProperties(rootSpecDto, spec);

                ProductSpecGroup group = new ProductSpecGroup();
                group.setRootSpec(rootSpecDto);
                for (WebResourceSpec childSpec : specList) {
                    if (!StringUtils.isEmpty(childSpec.rootSpec)
                            && childSpec.rootSpec.equals(spec.spec)) {
                        ProductSpec childSpecDto = new ProductSpec();
                        BeanUtils.copyProperties(childSpecDto, childSpec);
                        group.addChildSpec(childSpecDto);
                    }
                }
                groupList.add(group);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        ProductSpecComparatorUtil.sortResGroupList(groupList);
        return groupList;
    }
}
