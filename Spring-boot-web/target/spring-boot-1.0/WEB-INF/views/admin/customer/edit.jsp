<%--
  Created by IntelliJ IDEA.
  User: phamh
  Date: 6/18/2024
  Time: 5:53 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerEditUrl" value="admin/customer-edit"/>
<html>
<head>
    <title>Thêm sửa khách hàng</title>
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
                <h1>Thông tin khách hàng</h1>
            </div>
            <div class="row" style="font-family: 'Times New Roman', Times, serif; margin-bottom: 70px;">
                <div class="col-xs-12">
                    <form:form modelAttribute="customerEdit" action="/admin/customer-edit" id="form-edit" method="GET">
                        <div class="form-group">
                            <label class="col-xs-3">Tên khách hàng</label>
                            <div class="col-xs-9">
                                <form:input path="fullName" class="form-control"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số điện thoại</label>
                            <div class="col-xs-9">
                                <form:input path="phone" class="form-control"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Email</label>
                            <div class="col-xs-9">
                                <form:input path="email" class="form-control"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tên công ty</label>
                            <div class="col-xs-9">
                                <form:input path="companyName" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Nhu cầu</label>
                            <div class="col-xs-9">
                                <form:input path="demand" class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tình trạng</label>
                            <div class="col-xs-9">
                                <form:select path="status">
                                    <form:options items="${customerStatus}"/>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3"></label>
                            <div class="col-xs-9">
                                <c:if test="${empty customerEdit.id}">
                                    <button type="button" class="btn btn-primary" id="btnAddOrUpdateCustomer">Thêm mới</button>
                                </c:if>
                                <c:if test="${not empty customerEdit.id}">
                                    <security:authorize access="hasRole('MANAGER')">
                                        <button class="btn btn-warning" id="btnAddOrUpdateCustomer">Sửa khách hàng</button>
                                        <form:input path="id" type="hidden" value="${customerEdit.id}"/>
                                    </security:authorize>
                                </c:if>
                                <a href="/admin/customer-list">
                                    <button type="button" class="btn btn-primary">Hủy thao tác</button>
                                </a>
                            </div>

                        </div>
                    </form:form>
                </div>
                <%-- Transaction type--%>
                <c:forEach var="item" items="${transactionType}">
                    <div class="col-xs-12">
                        <div class="col-sm-12">
                            <h3 class="header smaller lighter blue">${item.value}</h3>
                             <button class="btn btn-lg btn-primary" onclick="transactionType('${item.key}', ${customerEdit.id})">
                                <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                            </button>
                        </div>
                        <c:if test="${item.key == 'CSKH' and not empty CSKHList}">
                                <div class="col-xs-12">
                                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>Ngày tạo</th>
                                            <th>Người tạo</th>
                                            <th>Ngày sửa</th>
                                            <th>Người sửa</th>
                                            <th>Chi tiết giao dịch</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="it" items="${CSKHList}">
                                                <tr>
                                                    <td>${it.createdDate}</td>
                                                    <td>${it.createdBy}</td>
                                                    <td>${it.modifiedDate}</td>
                                                    <td>${it.modifiedBy}</td>
                                                    <td>${it.note}</td>
                                                    <td>
                                                        <div class="hidden-sm hidden-xs btn-group">
                                                            <button class="btn btn-xs btn-info" data-toggle="tooltip" title="Sửa thông tin giao dịch" onclick="UpdateTransaction(${it.id}, '${it.note}')">
                                                                <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                            </button>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                        </c:if>
                        <c:if test="${item.key == 'DDX' and not empty DDXList}">
                                <div class="col-xs-12">
                                    <table class="table table-striped table-bordered table-hover">
                                        <thead>
                                        <tr>
                                            <th>Ngày tạo</th>
                                            <th>Người tạo</th>
                                            <th>Ngày sửa</th>
                                            <th>Người sửa</th>
                                            <th>Chi tiết giao dịch</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="it" items="${DDXList}">
                                            <tr>
                                                <td>${it.createdDate}</td>
                                                <td>${it.createdBy}</td>
                                                <td>${it.modifiedDate}</td>
                                                <td>${it.modifiedBy}</td>
                                                <td>${it.note}</td>
                                                <td>
                                                    <div class="hidden-sm hidden-xs btn-group">
                                                        <button class="btn btn-xs btn-info" data-toggle="tooltip" title="Sửa thông tin giao dịch" onclick="UpdateTransaction(${it.id}, '${it.note}')">
                                                            <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                        </c:if>
                    </div>
                </c:forEach>

                <!-- /.page-header -->
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="transactionTypeModal" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    &times;
                </button>
                <h4 class="modal-title">Nhập giao dịch</h4>
            </div>
            <div class="modal-body">
                <div class="form-group has-success">
                    <label for="transactionDetail" class="col-xs-12 col-sm-3 control-label no-padding-right">Chi thiết giao dich</label>
                    <div class="col-xs-12 col-sm-9">
                        <span class="block input-icon input-icon-right">
                            <input type="text" id="transactionDetail" class="width-100">
                        </span>
                    </div>
                </div>
                <input type="hidden" id="customerId" name="customerId" value="">
                <input type="hidden" id="code" name="code" value="">
                <input type="hidden" id="id" name="id" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">
                    Thêm giao dich
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Đóng
                </button>
            </div>
        </div>
    </div>
</div>
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script>
    $(document).ready(function () {
        $('#btnAddOrUpdateCustomer').click(function (e) {
            console.log('Button clicked');
            e.preventDefault();
            var data = {}; // Declare object
            var formData = $('#form-edit').serializeArray();
            $.each(formData, function (index, item) {
                data[item.name] = item.value;
            });
            if (data["fullName"].length === 0) {
                return alert("Tên khách hàng không được thiếu!");
            } else if (data["phone"].length === 0) {
                return alert("SĐT khách hàng không được thiếu!");
            } else {
                btnAddOrUpdate(data);
            }
        });

        function btnAddOrUpdate(data) {
            $.ajax({
                type: "POST",
                url: "/api/customers/",
                data: JSON.stringify(data),
                dataType: "text",
                contentType: "application/json; charset=utf-8",
                success: function(response) {
                    alert(response);
                    window.location.replace("/admin/customer-list");
                },
                error: function(response) {
                    console.log("Failed");
                    console.log(response);
                }
            });
        }

        $('#btnAddOrUpdateTransaction').click(function (e) {
            e.preventDefault();
            var data = {};
            data['id'] = $('#id').val();
            data['customerId'] = $('#customerId').val();
            data['code'] = $('#code').val();
            data['transactionDetail'] = $('#transactionDetail').val();
            addTransaction(data);
        });

        function addTransaction(data) {
            $.ajax({
                type: "POST",
                url: "/api/customers/transaction/",
                data: JSON.stringify(data),
                dataType: "text",
                contentType: "application/json; charset=utf-8",
                success: function(response) {
                    alert("Add transaction success!");
                    window.location.replace("customer-edit-" + data.customerId);
                },
                error: function(response) {
                    console.log("Failed");
                    console.log("Add transaction unsuccessfully!");
                }
            });
        }

        function transactionType(code, customerId) {
            $('#transactionTypeModal').modal();
            $('#customerId').val(customerId);
            $('#code').val(code);
            $('#id').val("");
        }

        function UpdateTransaction(id, note) {
            $('#transactionDetail').val(note);
            $('#transactionTypeModal').modal();
            $('#id').val(id);
        }
    });
</script>
<!-- <![endif]-->

<!--[if IE]>
<script src="assets/js/jquery.1.11.1.min.js"></script>
<![endif]-->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery ||
    document.write(
        "<script src='assets/js/jquery.min.js'>" + "<" + "/script>"
    );
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery ||
    document.write(
            "<script src='assets/js/jquery1x.min.js'>" + "<" + "/script>"
    );
</script>
<![endif]-->
<script type="text/javascript">
    if ("ontouchstart" in document.documentElement)
        document.write(
            "<script src='assets/js/jquery.mobile.custom.min.js'>" +
            "<" +
            "/script>"
        );
</script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->

<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">
    jQuery(function ($) {
        $(".modal.aside").ace_aside();

        $("#aside-inside-modal")
            .addClass("aside")
            .ace_aside({container: "#my-modal > .modal-dialog"});

        $(document).one("ajaxloadstart.page", function (e) {
            //in ajax mode, remove before leaving page
            $(".modal.aside").remove();
            $(window).off(".aside");
        });
    });
</script>
</body>
</html>
