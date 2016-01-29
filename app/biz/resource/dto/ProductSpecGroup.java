package biz.resource.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecGroup {
    private ProductSpec rootSpec;
    private List<ProductSpec> childSpec = new ArrayList<ProductSpec>();

    public ProductSpec getRootSpec() {
        return rootSpec;
    }

    public void setRootSpec(ProductSpec rootSpec) {
        this.rootSpec = rootSpec;
    }

    public List<ProductSpec> getChildSpec() {
        return childSpec;
    }

    public void setChildSpec(List<ProductSpec> childSpecList) {
        this.childSpec = childSpecList;
    }

    public void addChildSpec(ProductSpec childSpec) {
        this.childSpec.add(childSpec);
    }
}
