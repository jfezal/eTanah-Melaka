<%--
    Document   : maklumat_perintah_jualan_view
    Created on : Aug 25, 2010, 1:47:00 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>
<%@ page import="etanah.model.Pengguna"%>
<s:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />

<script type="text/javascript">

    function showReport(){
        $.blockUI({
            message: $('#displayBox'),
            css: {
                top:  ($(window).height() - 50) /2 + 'px',
                left: ($(window).width() - 50) /2 + 'px',
                width: '50px'
            }
        });
        var url = '${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?genReport';
        $.ajax({
            type:"GET",
            url : url,
            dataType : 'html',
            error : function(xhr, ajaxOptions, thrownError) {
                alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
    <%--$("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');--%>
                    $.unblockUI();
                },
                success : function(data) {
                    if(data.indexOf('Laporan telah dijana')==0){
                        alert(data);
                        $('#folder').click();
                    }else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        }

        function showReport1(){
            $.blockUI({
                message: $('#displayBox'),
                css: {
                    top:  ($(window).height() - 50) /2 + 'px',
                    left: ($(window).width() - 50) /2 + 'px',
                    width: '50px'
                }
            });
            var url = '${pageContext.request.contextPath}/lelong/maklumat_lelongan_awam?genReport1';
            $.ajax({
                type:"GET",
                url : url,
                dataType : 'html',
                error : function(xhr, ajaxOptions, thrownError) {
                    alert('Terdapat masalah teknikal. Sila hubungi Pentadbir Sistem.');
    <%--$("#folder_div").html('<div class=errors>Terdapat Masalah Teknikal. Sila Hubungi Pentadbir Sistem.</div><div id=t><a href="#">[ Lihat Terperinci ]</a></div><br/><div id=error class=b>' + xhr.responseText + '</div>');--%>
                    $.unblockUI();
                },
                success : function(data) {
                    if(data.indexOf('Laporan telah dijana')==0){
                        alert(data);
                        $('#folder').click();
                    }else {
                        alert(data);
                        $('#urusan').click();
                    }
                    $.unblockUI();
                }
            });
        }

</script>

<s:form beanclass="etanah.view.stripes.lelong.LelonganAwamActionBean">
    <s:messages/>
    <s:errors/>&nbsp;
    <br>
    <div class="subtitle">
        <fieldset class="aras1">
            <legend>
                Maklumat Perintah Jualan
            </legend>
            <div class="content">
                <p>
                    <label>Tarikh Lelongan Awam:</label>
                    <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="dd/MM/yyyy"/>&nbsp;
                </p>

                <p>
                    <label> Masa Lelongan Awam :</label>
                    <fmt:formatDate value="${actionBean.lelong.tarikhLelong}" pattern="hh:mm aaa"/>&nbsp;
                </p>

                <p>
                    <label> Tempat :</label>
                    <font style="text-transform: uppercase">${actionBean.lelong.tempat}</font> &nbsp;
                </p>

                <p>
                    <label> Harga Rizab (RM): </label>
                    <s:format formatPattern="#,##0.00" value="${actionBean.lelong.hargaRizab}" /> &nbsp;
                </p>

                <p>
                    <label> Harga Rizab Dalam Perkataan : </label>
                    ${actionBean.lelong.ejaanHarga} &nbsp;
                </p>

                <p>
                    <label>Tarikh Akhir Bayaran : </label>
                    <fmt:formatDate value="${actionBean.lelong.tarikhAkhirBayaran}" pattern="dd/MM/yyyy"/>&nbsp;
                    <s:hidden name="tarikhAkhirBayaran" id="tarikhBayar" formatPattern="dd/MM/yyyy"/>
                    <s:hidden name="tarikhLelong" id="tarikhLelong" formatPattern="dd/MM/yyyy"/>
                </p>

            </div>
        </fieldset>
    </div>
<%--    <p align="center">
        <s:button name="genReport" id="report" value="Borang 16H" class="longbtn" onclick="showReport();"/>&nbsp;&nbsp;&nbsp;
        <s:button name="genReport" id="report" value="Pengishtiharan Jualan" class="longbtn" onclick="showReport1();"/>&nbsp;
    </p>--%>
</s:form>