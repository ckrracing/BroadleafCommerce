/*
 * Copyright 2008-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.broadleafcommerce.core.catalog.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.client.VisibilityEnum;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Polymorphism;
import org.hibernate.annotations.PolymorphismType;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The Class CategoryProductXrefImpl is the default implmentation of {@link Category}.
 * This entity is only used for executing a named query.
 *
 * If you want to add fields specific to your implementation of BroadLeafCommerce you should extend
 * this class and add your fields.  If you need to make significant changes to the class then you
 * should implement your own version of {@link Category}.
 * <br>
 * <br>
 * This implementation uses a Hibernate implementation of JPA configured through annotations.
 * The Entity references the following tables:
 * BLC_CATEGORY_PRODUCT_XREF,
 *
 * @see {@link Category}, {@link ProductImpl}
 * @author btaylor
 */
@Entity
@Polymorphism(type = PolymorphismType.EXPLICIT)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "BLC_CATEGORY_PRODUCT_XREF")
@AdminPresentationClass(excludeFromPolymorphism = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region="blStandardElements")
public class CategoryProductXrefImpl implements CategoryProductXref {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "CategoryProductId")
    @GenericGenerator(
        name="CategoryProductId",
        strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="CategoryProductXrefImpl"),
            @Parameter(name="entity_name", value="org.broadleafcommerce.core.catalog.domain.CategoryProductXrefImpl")
        }
    )
    @Column(name = "CATEGORY_PRODUCT_ID")
    protected Long id;

    @ManyToOne(targetEntity = CategoryImpl.class, optional=false)
    @JoinColumn(name = "CATEGORY_ID")
    protected Category category = new CategoryImpl();

    /** The product. */
    @ManyToOne(targetEntity = ProductImpl.class, optional=false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product = new ProductImpl();

    /** The display order. */
    @Column(name = "DISPLAY_ORDER")
    @AdminPresentation(visibility = VisibilityEnum.HIDDEN_ALL)
    protected Long displayOrder;

    public Long getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Long displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryProductXrefImpl)) return false;

        CategoryProductXrefImpl that = (CategoryProductXrefImpl) o;

        if (category != null ? !category.equals(that.category) : that.category != null) return false;
        if (displayOrder != null ? !displayOrder.equals(that.displayOrder) : that.displayOrder != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        result = 31 * result + (displayOrder != null ? displayOrder.hashCode() : 0);
        return result;
    }
}
