<%-- 
    Document   : komisyenjualan_n9
    Created on : Oct 20, 2010, 8:31:26 PM
    Author     : mazurahayati.yusop
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="/WEB-INF/jsp/include/page_header.jspf" %>

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
        var url = '${pageContext.request.contextPath}/lelong/printah_jual_negeri9?genReport';
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

<s:form beanclass="etanah.view.stripes.lelong.PerintahJualanNegeri9ActionBean">
    <p>
        <s:messages />
        <s:errors />&nbsp;
    </p>
    <div align="justify">
        <fieldset class="aras1">
            <legend>
                Komisyen Jualan
            </legend><br>

            <div class="content">
                <div class="subtitle">
                    <div class="content">
                        <p>
                            <label> Harga Bidaan (RM): </label>
                            <s:format formatPattern="#,##0.00" value="${actionBean.hargaBidaan}" /> &nbsp;
                        </p>
                        <p>
                            <label>Komisyen Jualan (RM): </label>
                            <s:format formatPattern="#,##0.00" value="${actionBean.totalKomisyen}" /> &nbsp;
                        </p>
                        <p>
                            <label>Cukai Tanah (RM): </label>
                            <s:format formatPattern="#,##0.00" value="${actionBean.cukaitanah}" /> &nbsp;
                        </p>
                        <p>
                            <label>Jumlah Yang Perlu Di Bayar (RM): </label>
                            <s:format formatPattern="#,##0.00" value="${actionBean.jumlah}" /> &nbsp;
                        </p>
                    </div>
                </div>
            </div>
<%--            <c:if test="${edit}">
                <p align="center">
                    <s:button name="genReport" id="report" value="Cetak Borang 16Q" class="longbtn" onclick="showReport();"/>&nbsp;
                </p>
            </c:if>--%>
        </fieldset>
    </div>
</s:form>


