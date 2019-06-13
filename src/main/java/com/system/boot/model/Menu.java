/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
/*
 - Copyright 1999-2016 Shanghai XiangTu Network Technology Co. Limit
*/
package com.system.boot.model;

import com.esotericsoftware.kryo.DefaultSerializer;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import java.io.Serializable;

/**
* Menu设置Kryo的CompatibleFieldSerializer
* Auto generated by Stanley Li
*/
@DefaultSerializer(CompatibleFieldSerializer.class)
public class Menu implements Serializable {
    /**
     * t_menu.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * 菜单名称
     * t_menu.menu_name
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * 前端路径
     * t_menu.url
     *
     * @mbggenerated
     */
    private String url;

    /**
     * 图标icon
     * t_menu.icon
     *
     * @mbggenerated
     */
    private String icon;

    /**
     * t_menu.hasThird
     *
     * @mbggenerated
     */
    private String hasthird;

    /**
     * 是否为一级菜单，是：0 ，否：填写上一级菜单的id
     * t_menu.parent_menu
     *
     * @mbggenerated
     */
    private Long parentMenu;

    /**
     * 创建人id
     * t_menu.creator_id
     *
     * @mbggenerated
     */
    private Long creatorId;

    /**
     * 创建人姓名
     * t_menu.creator_name
     *
     * @mbggenerated
     */
    private String creatorName;

    /**
     * 菜单排序
     * t_menu.sort_number
     *
     * @mbggenerated
     */
    private Integer sortNumber;

    /**
     * 是否删除，0：否，1：是
     * t_menu.deleted
     *
     * @mbggenerated
     */
    private Short deleted;

    /**
     * 创建时间
     * t_menu.created_time
     *
     * @mbggenerated
     */
    private Long createdTime;

    /**
     * 修改时间
     * t_menu.updated_time
     *
     * @mbggenerated
     */
    private Long updatedTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method returns the value of the database column t_menu.id
     *
     * @return the value of t_menu.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method sets the value of the database column t_menu.id
     *
     * @param id the value for t_menu.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 菜单名称
     * This method returns the value of the database column t_menu.menu_name
     *
     * @return the value of t_menu.menu_name
     *
     * @mbggenerated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 菜单名称
     * This method sets the value of the database column t_menu.menu_name
     *
     * @param menuName the value for t_menu.menu_name
     *
     * @mbggenerated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 前端路径
     * This method returns the value of the database column t_menu.url
     *
     * @return the value of t_menu.url
     *
     * @mbggenerated
     */
    public String getUrl() {
        return url;
    }

    /**
     * 前端路径
     * This method sets the value of the database column t_menu.url
     *
     * @param url the value for t_menu.url
     *
     * @mbggenerated
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 图标icon
     * This method returns the value of the database column t_menu.icon
     *
     * @return the value of t_menu.icon
     *
     * @mbggenerated
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 图标icon
     * This method sets the value of the database column t_menu.icon
     *
     * @param icon the value for t_menu.icon
     *
     * @mbggenerated
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * This method returns the value of the database column t_menu.hasThird
     *
     * @return the value of t_menu.hasThird
     *
     * @mbggenerated
     */
    public String getHasthird() {
        return hasthird;
    }

    /**
     * This method sets the value of the database column t_menu.hasThird
     *
     * @param hasthird the value for t_menu.hasThird
     *
     * @mbggenerated
     */
    public void setHasthird(String hasthird) {
        this.hasthird = hasthird;
    }

    /**
     * 是否为一级菜单，是：0 ，否：填写上一级菜单的id
     * This method returns the value of the database column t_menu.parent_menu
     *
     * @return the value of t_menu.parent_menu
     *
     * @mbggenerated
     */
    public Long getParentMenu() {
        return parentMenu;
    }

    /**
     * 是否为一级菜单，是：0 ，否：填写上一级菜单的id
     * This method sets the value of the database column t_menu.parent_menu
     *
     * @param parentMenu the value for t_menu.parent_menu
     *
     * @mbggenerated
     */
    public void setParentMenu(Long parentMenu) {
        this.parentMenu = parentMenu;
    }

    /**
     * 创建人id
     * This method returns the value of the database column t_menu.creator_id
     *
     * @return the value of t_menu.creator_id
     *
     * @mbggenerated
     */
    public Long getCreatorId() {
        return creatorId;
    }

    /**
     * 创建人id
     * This method sets the value of the database column t_menu.creator_id
     *
     * @param creatorId the value for t_menu.creator_id
     *
     * @mbggenerated
     */
    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 创建人姓名
     * This method returns the value of the database column t_menu.creator_name
     *
     * @return the value of t_menu.creator_name
     *
     * @mbggenerated
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 创建人姓名
     * This method sets the value of the database column t_menu.creator_name
     *
     * @param creatorName the value for t_menu.creator_name
     *
     * @mbggenerated
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 菜单排序
     * This method returns the value of the database column t_menu.sort_number
     *
     * @return the value of t_menu.sort_number
     *
     * @mbggenerated
     */
    public Integer getSortNumber() {
        return sortNumber;
    }

    /**
     * 菜单排序
     * This method sets the value of the database column t_menu.sort_number
     *
     * @param sortNumber the value for t_menu.sort_number
     *
     * @mbggenerated
     */
    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    /**
     * 是否删除，0：否，1：是
     * This method returns the value of the database column t_menu.deleted
     *
     * @return the value of t_menu.deleted
     *
     * @mbggenerated
     */
    public Short getDeleted() {
        return deleted;
    }

    /**
     * 是否删除，0：否，1：是
     * This method sets the value of the database column t_menu.deleted
     *
     * @param deleted the value for t_menu.deleted
     *
     * @mbggenerated
     */
    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    /**
     * 创建时间
     * This method returns the value of the database column t_menu.created_time
     *
     * @return the value of t_menu.created_time
     *
     * @mbggenerated
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * 创建时间
     * This method sets the value of the database column t_menu.created_time
     *
     * @param createdTime the value for t_menu.created_time
     *
     * @mbggenerated
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 修改时间
     * This method returns the value of the database column t_menu.updated_time
     *
     * @return the value of t_menu.updated_time
     *
     * @mbggenerated
     */
    public Long getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 修改时间
     * This method sets the value of the database column t_menu.updated_time
     *
     * @param updatedTime the value for t_menu.updated_time
     *
     * @mbggenerated
     */
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getIcon() == null ? other.getIcon() == null : this.getIcon().equals(other.getIcon()))
            && (this.getHasthird() == null ? other.getHasthird() == null : this.getHasthird().equals(other.getHasthird()))
            && (this.getParentMenu() == null ? other.getParentMenu() == null : this.getParentMenu().equals(other.getParentMenu()))
            && (this.getCreatorId() == null ? other.getCreatorId() == null : this.getCreatorId().equals(other.getCreatorId()))
            && (this.getCreatorName() == null ? other.getCreatorName() == null : this.getCreatorName().equals(other.getCreatorName()))
            && (this.getSortNumber() == null ? other.getSortNumber() == null : this.getSortNumber().equals(other.getSortNumber()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()))
            && (this.getCreatedTime() == null ? other.getCreatedTime() == null : this.getCreatedTime().equals(other.getCreatedTime()))
            && (this.getUpdatedTime() == null ? other.getUpdatedTime() == null : this.getUpdatedTime().equals(other.getUpdatedTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getIcon() == null) ? 0 : getIcon().hashCode());
        result = prime * result + ((getHasthird() == null) ? 0 : getHasthird().hashCode());
        result = prime * result + ((getParentMenu() == null) ? 0 : getParentMenu().hashCode());
        result = prime * result + ((getCreatorId() == null) ? 0 : getCreatorId().hashCode());
        result = prime * result + ((getCreatorName() == null) ? 0 : getCreatorName().hashCode());
        result = prime * result + ((getSortNumber() == null) ? 0 : getSortNumber().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        result = prime * result + ((getCreatedTime() == null) ? 0 : getCreatedTime().hashCode());
        result = prime * result + ((getUpdatedTime() == null) ? 0 : getUpdatedTime().hashCode());
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", menuName=").append(menuName);
        sb.append(", url=").append(url);
        sb.append(", icon=").append(icon);
        sb.append(", hasthird=").append(hasthird);
        sb.append(", parentMenu=").append(parentMenu);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", creatorName=").append(creatorName);
        sb.append(", sortNumber=").append(sortNumber);
        sb.append(", deleted=").append(deleted);
        sb.append(", createdTime=").append(createdTime);
        sb.append(", updatedTime=").append(updatedTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}