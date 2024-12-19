<%--
    Document   : hapus_kira
    Created on : May 22, 2012, 3:00:52 PM
    Author     : khadijah
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/clear-text.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/js/jquery.form.js"></script>

<script type="text/javascript">

    $(document).ready(function() {
        $('#next').attr("disabled", true);//to disable selesai button
    });


    function checkStatus(val){
        
        if(val == ""){
            $('#next').attr("disabled", false);//to enable selesai button
        }else{
            $('#next').attr("disabled", true);//to disable selesai button
        }
    }

     function updateDasar(val){

            var url = '${pageContext.request.contextPath}/hasil/hapus_kira?popupKemaskiniBngn&radio='+val;

            window.open(url, "etanah");
        }

</script>

<div align="center">
    <table style="width:99.2%" bgcolor="green">
        <tr><td width="100%" height="20" ><div style="background-color: green;" align="center">
                    <font style="font-family: Tahoma; font-size: 16px; font-weight: bold;">HASIL: Hapus Kira</font>
                </div></td></tr>
    </table>
</div>
<stripes:useActionBean beanclass="etanah.view.ListUtil" id="listUtil" />
<stripes:form beanclass="etanah.view.stripes.hasil.HapusKiraActionBean" id="semak">
    <stripes:errors />

    <div class="subtitle">
        <fieldset class="aras1">
            <legend>Carian</legend>
            <p>
                <label>ID Hakmilik : </label>
                <stripes:text name="hakmilik.idHakmilik" size="30" id="hakmilik" onblur="this.value=this.value.toUpperCase();"/>
            </p>
            <p>
                <label>Nombor Rujukan : </label>
                <stripes:text name="dasarTuntutanCukai.idDasar" size="30" id="resit" onblur="this.value=this.value.toUpperCase();"/>
            </p>

            <p>
                <label>&nbsp;</label>
                <stripes:submit name="search" value="Cari" class="btn" onclick="return chck()" id="nxt"/>
                <stripes:button name=" " value="Isi Semula" class="btn" onclick="clearText('semak');"/>
            </p>
            <br>
        </fieldset>
    </div>
    <p></p>
    <c:if test="${actionBean.flag}">
        <div class="subtitle">
            <fieldset class="aras1">
                <div id="hiddenSeterus">
                    <legend>Senarai Carian </legend>
                    <div class="content" align="center">
                        <display:table class="tablecloth" name="${actionBean.dasarList}" id="row">
                            <display:column title="Pilih"><div align="center"><stripes:radio name="radio" value="${row.dasarTuntutanCukai.idDasar}" onclick="checkStatus('${row.catatanHapus1}')" id="rBtn"/></div></display:column>
                            <display:column title="Bil."><div align="center">${row_rowNum}.</div></display:column>
                            <display:column title="No Rujukan" property="dasarTuntutanCukai.idDasar"/>
                            <display:column title="ID Hakmilik" property="hakmilik.idHakmilik"/>
                            <display:column title="Cawangan" property="cawangan.name"/>
                            <display:column title="Status" property="status.nama"/>
                            <display:column title="Kemaskini">
                                <c:if test="${row.catatanHapus1 ne null}">
                                    <center>
                                        <img alt='Klik Untuk Kemaskini' border='0' src='${pageContext.request.contextPath}/images/edit.gif' class='rem'
                                             id='rem_${row_rowNum}' onclick="updateDasar('${row.dasarTuntutanCukai.idDasar}')" onmouseover="this.style.cursor='pointer';">
                                    </center>
                                </c:if>
                            </display:column>
                        </display:table>
                    </div>
                </div>
            </fieldset>
        </div>
        <div align="center"><table style="width:99.2%" bgcolor="green">
                <tr>
                    <td align="right">
                        <stripes:submit name="seterus" value="Seterusnya" class="btn" id="next"/>

                    </td>
                </tr>
            </table></div>
        </c:if>
        <%-- <table style="width:99.2%" bgcolor="green">
             <tr>
                 <td align="right">&nbsp;</td>
             </tr>
         </table>--%>
    </stripes:form>
