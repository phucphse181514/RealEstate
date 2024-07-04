<%--
  Created by IntelliJ IDEA.
  User: phamh
  Date: 6/17/2024
  Time: 6:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerAPI" value="/api/customers"/>
<c:url var="formUrl" value="/admin/customer-list"/>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="#">Home</a>
                </li>

                <li>
                    <a href="#">UI &amp; Elements</a>
                </li>
                <li class="active">Content Sliders</li>
            </ul>
            <!-- /.breadcrumb -->

            <div class="nav-search" id="nav-search">
                <form class="form-search">
                <span class="input-icon">
                  <input
                          type="text"
                          placeholder="Search ..."
                          class="nav-search-input"
                          id="nav-search-input"
                          autocomplete="off"
                  />
                  <i class="ace-icon fa fa-search nav-search-icon"></i>
                </span>
                </form>
            </div>
            <!-- /.nav-search -->
        </div>
        <div class="page-content">
            <div class="page-header">
                <h1>Danh sách khách hàng</h1>
            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div
                            class="widget-box"
                            style="font-family: 'Times New Roman', Times, serif"
                    >
                        <div class="widget-header">
                            <h4 class="widget-title">Tìm kiếm</h4>
                            <span class="widget-toolbar">
                      <a href="#" data-action="reload">
                        <i class="ace-icon fa fa-refresh"></i>
                      </a>
                      <a href="#" data-action="collapse">
                        <i class="ace-icon fa fa-chevron-up"></i>
                      </a>
                    </span>
                        </div>

                        <div class="widget-body">
                            <div class="widget-main">
                                <form:form action="/admin/customer-list" modelAttribute="model" method="GET"
                                           id="listForm">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label>Tên khách hàng</label>
                                                <form:input class="form-control" path="fullName"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Số điện thoại</label>
                                                <form:input class="form-control" path="phone"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Email</label>
                                                <form:input class="form-control" path="email"/>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <security:authorize access="hasRole('MANAGER')">
                                            <div class="col-xs-4">
                                                <label>Nhân viên</label>
                                                <form:select path="staffId" class="form-control">
                                                    <form:option value="" label="--Chọn nhân viên---"/>
                                                    <form:options items="${staffmaps}"/>
                                                </form:select>
                                            </div>
                                            </security:authorize>
                                            <div class="col-xs-4">
                                                <label>Tình trạng</label>
                                                <form:select path="status" class="form-control">
                                                    <form:option value="" label="--Chọn tình trạng---"/>
                                                    <form:options items="${customerStatus}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <button class="btn btn-sm btn-primary" id="btnSearch">
                                                    <i
                                                            class="ace-icon glyphicon glyphicon-search"
                                                    ></i>
                                                    Tìm kiếm
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            </form:form>
                        </div>
                    </div>
                    <!-- add building button -->
                    <div class="pull-right">
                        <a href="/admin/customer-edit">
                            <button class="btn btn-primary" title="Thêm khách hàng">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                    <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                                </svg>
                            </button>
                        </a>
                        <security:authorize access="hasRole('MANAGER')">
                        <button class="btn btn-danger" title="Xóa khách hàng" id="btnDeleteCustomers">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-fill-dash" viewBox="0 0 16 16">
                                <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
                                <path d="M2 13c0 1 1 1 1 1h5.256A4.5 4.5 0 0 1 8 12.5a4.5 4.5 0 0 1 1.544-3.393Q8.844 9.002 8 9c-5 0-6 3-6 4"/>
                            </svg>
                        </button>
                    </security:authorize>
                    </div>
                </div>
            </div>
            <div class="hr hr-18 dotted hr-double"></div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="table-responsive">
                        <display:table name="model.listResult" cellspacing="0" cellpadding="0"
                                       requestURI="${formUrl}" partialList="true" sort="external"
                                       size="${model.totalItems}" defaultsort="2" defaultorder="ascending"
                                       id="tableList" pagesize="${model.maxPageItems}"
                                       export="false"
                                       class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                       style="margin: 3em 0 1.5em;">
                            <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                            headerClass="center select-cell">
                                <fieldset>
                                    <input type="checkbox" name="checkList" value="${tableList.id}"
                                           id="checkbox_${tableList.id}" class="check-box-element"/>
                                </fieldset>
                            </display:column>
                            <display:column headerClass="text-left" property="fullName" title="Tên khách hàng"/>
                            <display:column headerClass="text-left" property="phone" title="Số điện thoại"/>
                            <display:column headerClass="text-left" property="email" title="Email"/>
                            <display:column headerClass="text-left" property="demand" title="Nhu cầu"/>
                            <display:column headerClass="text-left" property="createdBy" title="Người thêm"/>
                            <display:column headerClass="text-left" property="createdDate" title="Ngày thêm"/>
                            <display:column headerClass="text-left" property="status" title="Tình trạng"/>
                            <display:column headerClass="col-actions" title="Thao tác">
                                <button
                                        title="Giao Khách hàng"
                                        onclick="assignmentCustomer(${tableList.id})"
                                        class="btn btn-sm btn-success"
                                >
                                    <i
                                            class="ace-icon glyphicon glyphicon-align-justify"
                                    ></i>
                                </button>
                                <a href="/admin/customer-edit-${tableList.id}">
                                    <button
                                            title="Sửa khách hàng"
                                            class="btn btn-sm btn-info"
                                    >
                                        <i class="ace-icon glyphicon glyphicon-edit"></i>
                                    </button>
                                </a>
                                <security:authorize access="hasRole('MANAGER')">
                                    <button
                                            title="Xóa khách hàng"
                                            class="btn btn-sm btn-danger"
                                            onclick="btnDeleteCustomer(${tableList.id})"
                                    >
                                        <i class="ace-icon glyphicon glyphicon-trash"></i>
                                    </button>
                                </security:authorize>
                            </display:column>
                        </display:table>
                    </div>
                </div>
            </div>

            <!-- /.page-header -->
        </div>
    </div>
</div>
<div class="modal fade" id="assignmentCustomerModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    &times;
                </button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table
                        id="staffList"
                        class="table table-striped table-bordered table-hover"
                >
                    <thead>
                    <tr>
                        <th class="center">
                            Chọn
                        </th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>

                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-default"
                        id="btnAssignmentCustomer"
                >
                    Giao khách hàng
                </button>
                <button
                        type="button"
                        class="btn btn-default"
                        data-dismiss="modal"
                >
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    function assignmentCustomer(customerId) {
        $('#assignmentCustomerModal').modal();
        $('#customerId').val(customerId);
        loadStaffs(customerId);
        $('#btnAssignmentCustomer').click(function (e) {
                e.preventDefault;
                var data = {};
                data['customerId'] = $('#customerId').val();
                var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
                    return $(this).val();
                }).get();   // .get() chuyeern jquey object sang dạng mảng
                data["staffs"] = staffs;
                $.ajax({
                    type: "PUT",
                    url: '${customerAPI}',
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    success: () => {
                        alert("Giao khách hàng thành công!")
                        window.location.replace("/admin/customer-list")
                    },
                    error: function () {
                        alert("Giao khách hàng không thành công!")
                    }
                });
            }
        )
    }

    function loadStaffs(customerId) {
        $.ajax({
            type: "GET",
            url: '${customerAPI}/' + customerId + '/staffs',
            dataType: "json",
            success: function (response) {
                var row = '';
                $.each(response.data, function (index, item) {
                    row += '<tr>';
                    row += '<td class="center"><input type="checkbox" id="checkbox_' + item.staffId + '" value="' + item.staffId + '" ' + item.checked + ' /></td>'
                    row += '<td>' + item.fullName + '</td>'
                    row += '</tr>';
                })
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                console.log("failed");
                console.log(response);
            }
        });
    }

    $('#btnDeleteCustomers').click(function (e) {
        e.preventDefault;
        var data = {}
        var customerIds = $('#tableList').find('tbody input[type=checkbox]:checked').map(function () {
                return $(this).val()
            }
        ).get();
        data['ids'] = customerIds;
        deleteCustomer(data);
    })

    function btnDeleteCustomer(customerId) {
        var data = {};
        var id = [customerId];
        data['ids'] = id;
        deleteCustomer(data);
    }

    function deleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: '${customerAPI}',
            data: JSON.stringify(data['ids']),
            contentType: "application/json",
            dataType: "text",
            success: (response) => {
                alert(response)
                window.location.replace("/admin/customer-list")
            },
            error: function (response) {
                alert("Xóa không thành công!")
            }
        });
    }

<%--    $('#btnSearch').click(function (e) {--%>
<%--        e.preventDefault();--%>
<%--        $('#listForm').submit();--%>
<%--    })--%>
</script>
</body>
</html>

