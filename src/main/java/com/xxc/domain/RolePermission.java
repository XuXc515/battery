package com.xxc.domain;

import java.io.Serializable;

public class RolePermission implements Serializable {

    private Integer id;
    private Integer roleId;
    private Integer permissionId;
    private String status;
    private Integer deleteStatus;

    private Role role;
    private Permission permission;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_role_permission.id
     *
     * @return the value of tb_role_permission.id
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_role_permission.id
     *
     * @param id the value for tb_role_permission.id
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_role_permission.role_id
     *
     * @return the value of tb_role_permission.role_id
     * @mbggenerated
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_role_permission.role_id
     *
     * @param roleId the value for tb_role_permission.role_id
     * @mbggenerated
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_role_permission.permission_id
     *
     * @return the value of tb_role_permission.permission_id
     * @mbggenerated
     */
    public Integer getPermissionId() {
        return permissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_role_permission.permission_id
     *
     * @param permissionId the value for tb_role_permission.permission_id
     * @mbggenerated
     */
    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_role_permission.delete_status
     *
     * @return the value of tb_role_permission.delete_status
     * @mbggenerated
     */
    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_role_permission.delete_status
     *
     * @param deleteStatus the value for tb_role_permission.delete_status
     * @mbggenerated
     */
    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", status='" + status + '\'' +
                ", deleteStatus=" + deleteStatus +
                ", role=" + role +
                ", permission=" + permission +
                '}';
    }
}