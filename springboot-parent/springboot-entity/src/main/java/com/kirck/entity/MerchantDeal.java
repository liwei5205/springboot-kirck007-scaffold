package com.kirck.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 商户团购表
 * </p>
 *
 * @author kirck007
 * @since 2019-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_merchant_deal")
public class MerchantDeal extends Model<MerchantDeal> {

	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.UUID)
	private String id;
	@TableField("merchant_id")
	private String merchantId;
	@TableField("deal_title")
	private String dealTitle;
	@TableField("price")
	private BigDecimal price;
	@TableField("store_price")
	private BigDecimal storePrice;
	@TableField("dianping_url_id")
	private String dianpingUrlId;
	@TableField("notes")
	private String notes;
	@TableField("create_date")
	private LocalDateTime createDate;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getDealTitle() {
		return dealTitle;
	}

	public void setDealTitle(String dealTitle) {
		this.dealTitle = dealTitle;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(BigDecimal storePrice) {
		this.storePrice = storePrice;
	}

	public String getDianpingUrlId() {
		return dianpingUrlId;
	}

	public void setDianpingUrlId(String dianpingUrlId) {
		this.dianpingUrlId = dianpingUrlId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
}
