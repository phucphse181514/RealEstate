<%--
  Created by IntelliJ IDEA.
  User: phamh
  Date: 5/5/2024
  Time: 11:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
    <c:url var="buildingEditUrl" value="admin/building-edit"/>
<html>
<head>
    <title>Thêm sửa tòa nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check("breadcrumbs", "fixed");
                } catch (e) {}
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
                <h1>Thông tin tòa nhà</h1>
            </div>
            <div class="row" style="font-family: 'Times New Roman', Times, serif; margin-bottom: 70px;">
                <div class="col-xs-12">
                    <form:form modelAttribute="buildingEdit" action="${buildingEditUrl}" id="form-edit" method="get" >
                        <div class="form-group">
                            <label class="col-xs-3">Tên tòa nhà</label>
                            <div class="col-xs-9">
<%--                                <input class="form-control" type="text" name="name">--%>
                                <form:input path="name" class="form-control"/>
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Quận hiện có</label>
                            <div class="col-xs-9">
                            <form:select path="district" >
                                <form:option value="" label="--Chọn quận---"/>
                                <form:options items="${districtCode}"/>
                            </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phường</label>
                            <div class="col-xs-9">
                                <form:input path="ward" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đường</label>
                            <div class="col-xs-9">
                                <form:input path="street" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Kết cấu</label>
                            <div class="col-xs-9">
                                <form:input path="structure" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Số tầng hầm</label>
                            <div class="col-xs-9">
                                <form:input path="numberOfBasement" class="form-control" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Diện tích sàn</label>
                            <div class="col-xs-9">
                                <form:input path="floorArea" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hướng</label>
                            <div class="col-xs-9">
                                <form:input path="direction" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Hạng</label>
                            <div class="col-xs-9">
                                <form:input path="level" class="form-control" />
                            </div>

                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Diện tích thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentArea" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Giá thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentPrice" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Mô tả giá</label>
                            <div class="col-xs-9">
                                <form:input path="rentPriceDescription" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí dịch vụ</label>
                            <div class="col-xs-9">
                                <form:input path="serviceFee" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ô tô</label>
                            <div class="col-xs-9">
                                <form:input path="carFee" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí ngoài giờ</label>
                            <div class="col-xs-9">
                                <form:input path="overtimeFee" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tiền điện</label>
                            <div class="col-xs-9">
                                <form:input path="electricityFee" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Đặt cọc</label>
                            <div class="col-xs-9">
                                <form:input path="deposit" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thanh toán</label>
                            <div class="col-xs-9">
                                <form:input path="payment" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Thời hạn thuê</label>
                            <div class="col-xs-9">
                                <form:input path="rentTime" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Tên quản lí</label>
                            <div class="col-xs-9">
                                <form:input path="managerName" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">SĐT quản lí</label>
                            <div class="col-xs-9">
                                <form:input path="managerPhone" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Phí môi giới</label>
                            <div class="col-xs-9">
                                <form:input path="brokerageFee" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3">Loại tòa nhà</label>
                            <div class="col-xs-9">
                                <form:checkboxes path="typeCode" items="${typeCodes}" />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-xs-3">Ghi chú</label>
                            <div class="col-xs-9">
                                <form:input path="note" class="form-control" />
                            </div>

                        </div>
                        <div class="form-group">
                            <label class="col-xs-3"></label>
                            <div class="col-xs-9">
                                <c:if test="${empty buildingEdit.id}">
                                <button class="btn btn-primary" id="btnAddOrUpdateBuilding">Thêm mới</button>
                                </c:if>
                                <c:if test="${not empty buildingEdit.id}">
                                    <button class="btn btn-warning" id="btnAddOrUpdateBuilding">Sửa tòa nhà</button>
                                    <form:input path="id" type="hidden" value="${buildingEdit.id}"/>
                                </c:if>
                                <a href="/admin/building-list">
                                    <button type="button" class="btn btn-primary">Hủy thao tác</button>
                                </a>
                            </div>

                        </div>
                    </form:form>
                </div>



                <!-- /.page-header -->
            </div>
        </div>
    </div>
    <!-- /.main-content -->
    <hr>
    <div class="footer">
        <div class="footer-inner">
            <div class="footer-content">
            <span class="bigger-120">
              <span class="blue bolder">Ace</span>
              Application &copy; 2013-2014
            </span>

                &nbsp; &nbsp;
                <span class="action-buttons">
              <a href="#">
                <i
                        class="ace-icon fa fa-twitter-square light-blue bigger-150"
                ></i>
              </a>

              <a href="#">
                <i
                        class="ace-icon fa fa-facebook-square text-primary bigger-150"
                ></i>
              </a>

              <a href="#">
                <i class="ace-icon fa fa-rss-square orange bigger-150"></i>
              </a>
            </span>
            </div>
        </div>
    </div>

    <a
            href="#"
            id="btn-scroll-up"
            class="btn-scroll-up btn btn-sm btn-inverse"
    >
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<script src="assets/js/jquery.2.1.1.min.js"></script>
<script>
    $('#btnAddOrUpdateBuilding') .click(function(e){
        e.preventDefault();
        var data = {}; // khai báo object
        var typeCode = [];
        var formData = $('#form-edit').serializeArray();
        $.each(formData, function(index, item){
            if(item.name != "typeCode"){
                data["" + item.name + ""] = item.value;
            }
            else{
                typeCode.push(item.value);
            }
        })
        data["typeCode"] = typeCode;
        if(typeCode.length == 0){
            return alert("Loại tòa nhà không được thiếu!");
        } else{
            btnAddOrUpdate(data);
        }

    })
    function btnAddOrUpdate(data){
        $.ajax({
            type: "POST",
            url: "/api/buildings/",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success: (response) =>{
                alert(response);
                window.location.replace("/admin/building-list");
            },
            error: function(response){
                console.log("failed");
                console.log(response);
            }
        });
    }

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
            .ace_aside({ container: "#my-modal > .modal-dialog" });

        $(document).one("ajaxloadstart.page", function (e) {
            //in ajax mode, remove before leaving page
            $(".modal.aside").remove();
            $(window).off(".aside");
        });
    });
</script>
</body>
</html>
