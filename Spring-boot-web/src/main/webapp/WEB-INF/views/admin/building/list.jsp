<%--
  Created by IntelliJ IDEA.
  User: phamh
  Date: 5/5/2024
  Time: 10:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api/buildings"/>
<c:url var="formUrl" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà</title>
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
                <h1>Danh sách tòa nhà</h1>
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
                                <form:form action="/admin/building-list" modelAttribute="model" method="GET" id="listForm" >
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <label>Tên tòa nhà</label>
<%--                                                <input type="text" class="form-control" name="name" value="${modelSearch.name}"/>--%>
                                                <form:input class="form-control" path="name"/>
                                            </div>
                                            <div class="col-xs-6">
                                                <label>Diện tích sàn</label>
                                                <form:input class="form-control" path="floorArea"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <label>Quận hiện có</label>
                                                <form:select path="district" class="form-control">
                                                    <form:option value="" label="--Chọn quận---"/>
                                                    <form:options items="${districtCode}" />
                                                </form:select>
                                            </div>
                                            <div class="col-xs-5">
                                                <label>Phường</label>
                                                <form:input class="form-control" path="ward"/>
                                            </div>
                                            <div class="col-xs-5">
                                                <label>Đường </label>
                                                <form:input class="form-control" path="street"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label>Số tầng hầm</label>
                                                <form:input class="form-control" path="numberOfBasement"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Hướng</label>
                                                <form:input class="form-control" path="direction"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Hạng</label>
                                                <form:input class="form-control" path="level"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-3">
                                                <label>Diện tích từ</label>
                                                <form:input class="form-control" path="areaFrom"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Diện tích đến</label>
                                                <form:input class="form-control" path="areaTo"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Giá thuê từ</label>
                                                <form:input class="form-control" path="rentPriceFrom"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Giá thuê đến</label>
                                                <form:input class="form-control" path="rentPriceTo"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-5">
                                                <label>Tên quản lý</label>
                                                <form:input class="form-control" path="managerName"/>
                                            </div>
                                            <div class="col-xs-5">
                                                <label>SĐT quản lý</label>
                                                <form:input class="form-control" path="managerPhone"/>
                                            </div>
                                            <div class="col-xs-2">
                                                <label>Nhân viên phụ trách</label>
<%--                                                <select name="staffId">--%>
<%--                                                    <option>---Chọn nhân viên---</option>--%>
<%--                                                    <option value="1">Nguyen Van A</option>--%>
<%--                                                    <option value="2">Nguyen Van B</option>--%>
<%--                                                    <option value="3">Nguyen Van C</option>--%>
<%--                                                </select>--%>
                                                <form:select path="staffId" class="form-control">
                                                    <form:option value="" label="---Chọn nhân viên---"/>
                                                    <form:options items="${staffs}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <form:checkboxes path="typeCode" items="${typeCodes}" />
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
                        <a href="/admin/building-edit">
                            <button class="btn btn-primary" title="Thêm tòa nhà">
                                <svg
                                        xmlns="http://www.w3.org/2000/svg"
                                        width="16"
                                        height="16"
                                        fill="currentColor"
                                        class="bi bi-building-add"
                                        viewBox="0 0 16 16"
                                >
                                    <path
                                            d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"
                                    />
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z"
                                    />
                                    <path
                                            d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                                    />
                                </svg>

                            </button>
                        </a>

                        <button class="btn btn-danger" title="Xóa tòa nhà" id="btnDeleteBuildings">
                            <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    width="16"
                                    height="16"
                                    fill="currentColor"
                                    class="bi bi-building-fill-dash"
                                    viewBox="0 0 16 16"
                            >
                                <path
                                        d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"
                                />
                                <path
                                        d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"
                                />
                            </svg>
                        </button>
                    </div>
                </div>
            </div>
            <div class="hr hr-18 dotted hr-double"></div>
<%--            table--%>
<%--            <div--%>
<%--                    class="row"--%>
<%--                    style="font-family: 'Times New Roman', Times, serif">--%>
<%--                <div class="col-xs-12">--%>
<%--                    <table--%>
<%--                            id="buildingList"--%>
<%--                            class="table table-striped table-bordered table-hover"--%>
<%--                    >--%>
<%--                        <thead>--%>
<%--                        <tr>--%>
<%--                            <th class="center">--%>
<%--                                <label class="pos-rel">--%>
<%--                                    <input type="checkbox" class="ace" />--%>
<%--                                    <span class="lbl"></span>--%>
<%--                                </label>--%>
<%--                            </th>--%>
<%--                            <th>Tên tòa nhà</th>--%>
<%--                            <th>Địa chỉ</th>--%>
<%--                            <th>Số tầng hầm</th>--%>
<%--                            <th>Tên quản lý</th>--%>
<%--                            <th>Số điện thoại quản lý</th>--%>
<%--                            <th>D.Tích sàn</th>--%>
<%--                            <th>D.Tích trống</th>--%>
<%--                            <th>D.Tích thuê</th>--%>
<%--                            <th>Giá thuê</th>--%>
<%--                            <th>Phí dịch vụ</th>--%>
<%--                            <th>Phí môi giới</th>--%>
<%--                            <th>Thao tác</th>--%>
<%--                        </tr>--%>
<%--                        </thead>--%>

<%--                        <tbody>--%>
<%--                        <c:forEach var="item" items="${buildings}">--%>
<%--                            <tr>--%>
<%--                                <td class="center">--%>
<%--                                    <label class="pos-rel">--%>
<%--                                        <input type="checkbox" class="ace" value="${item.id}"/>--%>
<%--                                        <span class="lbl"></span>--%>
<%--                                    </label>--%>
<%--                                </td>--%>
<%--                                <td>${item.name}</td>--%>
<%--                                <td>${item.address}</td>--%>
<%--                                <td>${item.numberOfBasement}</td>--%>
<%--                                <td>${item.managerName}</td>--%>
<%--                                <td>${item.managerPhone}</td>--%>
<%--                                <td>${item.floorArea}</td>--%>
<%--                                <td>${item.emptyArea}</td>--%>
<%--                                <td>${item.rentArea}</td>--%>
<%--                                <td>${item.rentPrice}</td>--%>
<%--                                <td>${item.serviceFee}</td>--%>
<%--                                <td>${item.brokerageFee}</td>--%>
<%--                                    <td><div>--%>
<%--                                        <button--%>
<%--                                                title="Giao tòa nhà"--%>
<%--                                                onclick="assignmentBuilding(${item.id})"--%>
<%--                                                class="btn btn-sm btn-success"--%>
<%--                                        >--%>
<%--                                            <i--%>
<%--                                                    class="ace-icon glyphicon glyphicon-align-justify"--%>
<%--                                            ></i>--%>
<%--                                        </button>--%>
<%--                                                <a href="/admin/building-edit-${item.id}">--%>
<%--                                            <button--%>
<%--                                                    title="Sửa tòa nhà"--%>
<%--                                                    class="btn btn-sm btn-info"--%>
<%--                                            >--%>
<%--                                                <i class="ace-icon glyphicon glyphicon-edit"></i>--%>
<%--                                            </button>--%>
<%--                                        </a>--%>
<%--                                        <button--%>
<%--                                                title="Xóa tòa nhà"--%>
<%--                                                class="btn btn-sm btn-danger"--%>
<%--                                                onclick="btnDeleteBuilding(${item.id})"--%>
<%--                                        >--%>
<%--                                            <i class="ace-icon glyphicon glyphicon-trash"></i>--%>
<%--                                        </button>--%>
<%--                                    </div>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </c:forEach>--%>



<%--                        </tbody>--%>
<%--                    </table>--%>
<%--                </div>--%>
<%--                <!-- /.span -->--%>
<%--            </div>--%>
<%--            end-table--%>
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
                            <display:column headerClass="text-left" property="name" title="Tên tòa nhà"/>
                            <display:column headerClass="text-left" property="address" title="Địa chỉ"/>
                            <display:column headerClass="text-left" property="numberOfBasement" title="Số tầng hầm"/>
                            <display:column headerClass="text-left" property="managerName" title="Tên quản lý"/>
                            <display:column headerClass="text-left" property="managerPhone" title="Số điện thoại quản lý"/>
                            <display:column headerClass="text-left" property="floorArea" title="D.Tích sàn"/>
                            <display:column headerClass="text-left" property="emptyArea" title="D.Tích trống"/>
                            <display:column headerClass="text-left" property="rentArea" title="D.Tích thuê"/>
                            <display:column headerClass="text-left" property="rentPrice" title="Giá thuê"/>
                            <display:column headerClass="text-left" property="serviceFee" title="Phí dịch vụ"/>
                            <display:column headerClass="text-left" property="brokerageFee" title="Phí môi giới"/>

                            <display:column headerClass="col-actions" title="Thao tác">
                                                                        <button
                                                                                title="Giao tòa nhà"
                                                                                onclick="assignmentBuilding(${tableList.id})"
                                                                                class="btn btn-sm btn-success"
                                                                        >
                                                                            <i
                                                                                    class="ace-icon glyphicon glyphicon-align-justify"
                                                                            ></i>
                                                                        </button>
                                                                                <a href="/admin/building-edit-${tableList.id}">
                                                                            <button
                                                                                    title="Sửa tòa nhà"
                                                                                    class="btn btn-sm btn-info"
                                                                            >
                                                                                <i class="ace-icon glyphicon glyphicon-edit"></i>
                                                                            </button>
                                                                        </a>
                                                                        <button
                                                                                title="Xóa tòa nhà"
                                                                                class="btn btn-sm btn-danger"
                                                                                onclick="btnDeleteBuilding(${tableList.id})"
                                                                        >
                                                                            <i class="ace-icon glyphicon glyphicon-trash"></i>
                                                                        </button>
                            </display:column>
                        </display:table>
                    </div>
                </div>
            </div>
            <!-- /.page-header -->
        </div>
    </div>
</div>
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
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
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-default"
                        id="btnAssignmentBuilding"
                >
                    Giao toà nhà
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
    function assignmentBuilding(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buildingId);
        loadStaffs(buildingId);
            $('#btnAssignmentBuilding').click(function(e){
                    e.preventDefault;
                    var data = {};
                    data['buildingId'] = $('#buildingId').val();
                    var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function(){
                        return $(this).val();
                    }).get();   // .get() chuyeern jquey object sang dạng mảng
                    data["staffs"] = staffs;
                    $.ajax({
                        type: "PUT",
                        url: '${buildingAPI}',
                        data: JSON.stringify(data),
                        contentType: "application/json; charset=utf-8",
                        success: () =>{
                            alert("Giao tòa nhà thành công!")
                            window.location.replace("/admin/building-list")
                        },
                        error: function(){
                            alert("Giao tòa nhà không thành công!")
                        }
                    });
                }
            )
    }
    function loadStaffs(buildingId) {
        $.ajax({
            type: "GET",
            url: '${buildingAPI}/' + buildingId + '/staffs',
            dataType: "json",
            success: function(response){
                var row = '';
                $.each(response.data, function (index, item){
                    row += '<tr>';
                    row += '<td class="center"><input type="checkbox" id="checkbox_' + item.staffId +'" value="' + item.staffId + '" ' + item.checked + ' /></td>'
                    row += '<td>'+ item.fullName +'</td>'
                    row += '</tr>';
                })
                $('#staffList tbody').html(row);
            },
            error: function(response){
                console.log("failed");
                console.log(response);
            }
        });
    }

    $('#btnDeleteBuildings').click(function(e){
        e.preventDefault;
        var data = {}
        var buildingIds = $('#buildingList').find('tbody input[type=checkbox]:checked').map(function() {
                return $(this).val()
            }
        ).get();
        data['ids'] = buildingIds;
        deleteBuilding(data);
    })
    function btnDeleteBuilding(buildingId){
        var data = {};
        var id = [buildingId];
        data['ids'] = id;
        deleteBuilding(data);
    }
    function deleteBuilding(data){
        $.ajax({
            type: "DELETE",
            url: '${buildingAPI}',
            data: JSON.stringify(data['ids']),
            contentType: "application/json",
            dataType : "text",
            success: (response) =>{
                alert(response)
                window.location.replace("/admin/building-list")
            },
            error: function(response){
                alert("Xóa không thành công!")
            }
        });
    }
    $('#btnSearch').click(function (e){
        e.preventDefault();
        $('#listForm').submit();
    })
</script>
</body>
</html>
